package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.support.annotation.Nullable;

import android.content.Context;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssetsImageManager
{
    private static final String LOGTAG = AssetsImageManager.class.getSimpleName();

    private static final ArrayList<QueueData> queue = new ArrayList<>();
    private static final Map<String, Drawable> cache = new HashMap<>();

    private static Thread worker;

    public static Drawable getDrawableOrFetch(Context context, ImageView iv, String url,
                                              int ivwidth, int ivheight, boolean rounded)
    {
        synchronized (cache)
        {
            String cacheTag = url + "|" + ivwidth + "|" + ivheight + "|" + rounded;

            Drawable image = cache.get(cacheTag);

            if (image != null) return image;
        }

        fetchAssetImage(context, iv, url, ivwidth, ivheight, rounded);

        return Simple.getDrawableFromResources(context, R.drawable.lem_t_iany_ralbers_loading_placeholder);
    }

    private static void fetchAssetImage(Context context, ImageView iv, String url,
                                        int ivwidth, int ivheight, boolean rounded)
    {
        synchronized (queue)
        {
            queue.add(new QueueData(context, iv, url, ivwidth, ivheight, rounded));
        }

        if (worker == null)
        {
            worker = new Thread(threadWorker);
            worker.start();
        }
    }

    private final static Runnable threadWorker = new Runnable()
    {
        @Override
        public void run()
        {
            while (true)
            {
                QueueData qd;

                synchronized (queue)
                {
                    if (queue.size() == 0) break;

                    qd = queue.remove(0);
                }

                final Drawable drawable = getDrawable(qd.cx, qd.url, qd.ivwidth, qd.ivheight, qd.rounded);

                if (drawable != null)
                {
                    final ImageView imageView = qd.iv;

                    ApplicationBase.handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            imageView.setImageDrawable(drawable);
                        }
                    });
                }
            }

            worker = null;
        }
    };

    @Nullable
    private static Drawable getDrawable(Context context, String urlstring,
                                        int ivwidth, int ivheight, boolean rounded)
    {
        try
        {
            URL url = new URL(urlstring);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStream inputStream = conn.getInputStream();
            byte[] rawimage = Simple.getAllInputBytes(inputStream);
            inputStream.close();

            if (rawimage != null)
            {
                Bitmap myBitmap = BitmapFactory.decodeByteArray(rawimage, 0, rawimage.length);
                if (myBitmap == null) return null;

                float aspectX = myBitmap.getWidth() / (float) ivwidth;
                float aspectY = myBitmap.getHeight() / (float) ivheight;

                int cornerRadius = rounded ? Math.round(Defines.CORNER_RADIUS_ASSETS * ((aspectX + aspectY) / 2)) : 0;

                float displayAspect = ivwidth / (float) ivheight;

                Bitmap rcBitmap = Simple.makeRoundedTopCornersBitmap(myBitmap, cornerRadius, displayAspect);

                myBitmap.recycle();

                Drawable drawable = new BitmapDrawable(context.getResources(), rcBitmap);

                synchronized (cache)
                {
                    String cacheTag = urlstring + "|" + ivwidth + "|" + ivheight + "|" + rounded;

                    cache.put(cacheTag, drawable);
                }

                return drawable;
            }
        }
        catch (FileNotFoundException ignore)
        {
        }
        catch (Exception ex)
        {
            Log.d(LOGTAG, ex.toString());
        }

        return null;
    }

    private static class QueueData
    {
        final public Context cx;
        final public ImageView iv;
        final public String url;
        final public int ivwidth;
        final public int ivheight;
        final public boolean rounded;

        public QueueData(Context cx, ImageView iv, String url, int ivwidth, int ivheight, boolean rounded)
        {
            this.cx = cx;
            this.iv = iv;
            this.url = url;
            this.ivwidth = ivwidth;
            this.ivheight = ivheight;
            this.rounded = rounded;
        }
    }
}
