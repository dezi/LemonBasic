package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.os.Build;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.io.File;

public class ViewPDFFrame extends ViewZoomFrame
{
    private static final String LOGTAG = ViewPDFFrame.class.getSimpleName();

    private final ArrayList<Integer> renderDat;
    private final Rect display;

    private ImageView[] imageViews;
    private Bitmap[] pageBitmaps;
    private int[] pageWidths;
    private int[] pageHeights;

    private PdfRenderer renderer;
    private int numPages;
    private Thread worker;
    private boolean attached;

    private float scaleFactor = -1f;
    private boolean isAvailable;

    public ViewPDFFrame(Context context)
    {
        super(context);

        display = new Rect(0, 0,
                Simple.getDeviceWidth(context),
                Simple.getDeviceHeight(context));

        Log.d(LOGTAG, "ViewPDFFrame: display"
                + " l=" + display.left
                + " t=" + display.top
                + " r=" + display.right
                + " b=" + display.bottom);

        renderDat = new ArrayList<>();

        isAvailable = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }

    @Override
    protected void onVertScrollChanged()
    {
        int stop = vertScroll.getScrollY();
        int sbot = stop + display.height();

        for (int inx = 0; inx < numPages; inx++)
        {
            int itop = imageViews[inx].getTop();
            int ibot = itop + imageViews[inx].getHeight();

            boolean vis = ! ((itop > sbot) || (ibot < stop));

            if ((pageBitmaps[inx] == null) && vis)
            {
                synchronized (renderDat)
                {
                    if (! renderDat.contains(inx))
                    {
                        Log.d(LOGTAG, "onVertScrollChanged: inx=" + inx + " load");

                        renderDat.add(inx);
                    }
                }
            }

            if ((pageBitmaps[inx] != null) && ! vis)
            {
                Log.d(LOGTAG, "onVertScrollChanged: inx=" + inx + " drop");

                Bitmap bitmap = pageBitmaps[inx];

                imageViews[inx].setImageBitmap(null);
                pageBitmaps[inx] = null;

                bitmap.recycle();
            }
        }
    }

    @Override
    protected void onZoomFactorChanged(float newZoomFactor)
    {
        for (int inx = 0; inx < numPages; inx++)
        {
            int displayWidth = (int) (pageWidths[inx] * scaleFactor * zoomFactor);
            int displayHeight = (int) (pageHeights[inx] * scaleFactor * zoomFactor);

            imageViews[inx].setLayoutParams(new LinearLayout.LayoutParams(displayWidth, displayHeight));
        }
    }

    @Override
    protected void onZoomEnded(float newZoomFactor)
    {
        for (int inx = 0; inx < numPages; inx++)
        {
            synchronized (renderDat)
            {
                if ((pageBitmaps[inx] != null) && !renderDat.contains(inx))
                {
                    Log.d(LOGTAG, "nukeBitmaps: inx=" + inx + " load");

                    renderDat.add(inx);
                }
            }
        }

        onVertScrollChanged();
    }

    @Override
    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();

        attached = true;

        if (isAvailable)
        {
            loadMedia();

            worker = new Thread(rendererThread);
            worker.start();
        }
    }

    @Override
    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();

        attached = false;

        if (isAvailable)
        {
            if (worker != null)
            {
                try
                {
                    worker.join();
                }
                catch (Exception ignore)
                {
                }

                worker = null;
            }

            unloadMedia();
        }
    }

    private void loadMedia()
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return;

        try
        {
            File cacheFile = ContentHandler.getCachedFile(Globals.displayContent);

            if (cacheFile != null)
            {
                ParcelFileDescriptor pdfStream = ParcelFileDescriptor.open(cacheFile, ParcelFileDescriptor.MODE_READ_ONLY);
                renderer = new PdfRenderer(pdfStream);

                int screenWidth = Simple.getDeviceWidth(getContext());
                Log.d(LOGTAG, "loadPDF: screenWidth=" + screenWidth);

                numPages = renderer.getPageCount();
                imageViews = new ImageView[numPages];
                pageBitmaps = new Bitmap[numPages];
                pageWidths = new int[numPages];
                pageHeights = new int[numPages];

                //
                // Preflight scale factor and create image views.
                //

                for (int inx = 0; inx < numPages; inx++)
                {
                    PdfRenderer.Page page = renderer.openPage(inx);

                    pageWidths[inx] = page.getWidth();
                    pageHeights[inx] = page.getHeight();

                    page.close();

                    float pscale = screenWidth / (float) pageWidths[inx];

                    if ((scaleFactor < 0.0) || (pscale < scaleFactor)) scaleFactor = pscale;

                    ImageView imageView = new ImageView(getContext());
                    imageView.setBackgroundColor(Color.WHITE);
                    addContentView(imageView);

                    imageViews[inx] = imageView;
                }

                //
                // Layout image views.
                //

                for (int inx = 0; inx < numPages; inx++)
                {
                    Log.d(LOGTAG, "loadPDF: page=" + inx + " w=" + pageWidths[inx] + " h=" + pageHeights[inx]);

                    int displayWidth = (int) (pageWidths[inx] * scaleFactor * zoomFactor);
                    int displayHeight = (int) (pageHeights[inx] * scaleFactor * zoomFactor);

                    imageViews[inx].setLayoutParams(new LinearLayout.LayoutParams(displayWidth, displayHeight));
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        //
        // Render two pages upfront. The call
        // is posted to complete screen layout and
        // get correct dimensions of image views.
        //

        getHandler().post(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (renderDat)
                {
                    renderDat.add(0);
                    renderDat.add(1);
                }
            }
        });
    }

    private void unloadMedia()
    {
        if (imageViews != null)
        {
            for (int inx = 0; inx < imageViews.length; inx++)
            {
                if (imageViews[ inx ] != null)
                {
                    imageViews[inx].setImageBitmap(null);
                    imageViews[inx] = null;
                }
            }

            imageViews = null;
        }

        if (pageBitmaps != null)
        {
            for (int inx = 0; inx < pageBitmaps.length; inx++)
            {
                if (pageBitmaps[ inx ] != null)
                {
                    pageBitmaps[ inx ].recycle();
                    pageBitmaps[ inx ] = null;
                }
            }

            pageBitmaps = null;
        }
    }

    private final Runnable rendererThread = new Runnable()
    {
        @Override
        public void run()
        {
            Log.d(LOGTAG, "rendererThread: start...");

            while (attached)
            {
                int renderMe = -1;

                synchronized (renderDat)
                {
                    if (renderDat.size() > 0)
                    {
                        renderMe = renderDat.remove(0);
                    }
                }

                if ((renderMe < 0) || (renderMe >= pageBitmaps.length))
                {
                    //
                    // Nothing to do.
                    //

                    Simple.sleep(1);

                    continue;
                }

                Log.d(LOGTAG, "rendererThread: renderMe=" + renderMe);

                final int inx = renderMe;

                final Bitmap oldBitmap = pageBitmaps[inx];

                if (oldBitmap != null)
                {
                    if ((oldBitmap.getWidth() == imageViews[inx].getWidth()) &&
                            (oldBitmap.getHeight() == imageViews[inx].getHeight()))
                    {
                        //
                        // Present with same dimensions.
                        //

                        continue;
                    }
                }

                Log.d(LOGTAG, "rendererThread:"
                        + " width=" + imageViews[inx].getWidth()
                        + " height=" + imageViews[inx].getHeight()
                );

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    try
                    {
                        final Bitmap newBitmap = Bitmap.createBitmap(
                                imageViews[inx].getWidth(),
                                imageViews[inx].getHeight(),
                                Bitmap.Config.ARGB_8888);

                        PdfRenderer.Page page = renderer.openPage(inx);
                        page.render(newBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                        page.close();

                        getHandler().post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                imageViews[inx].setImageBitmap(newBitmap);
                                pageBitmaps[inx] = newBitmap;

                                if (oldBitmap != null) oldBitmap.recycle();
                            }
                        });
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }

            Log.d(LOGTAG, "rendererThread: done.");
        }
    };
}
