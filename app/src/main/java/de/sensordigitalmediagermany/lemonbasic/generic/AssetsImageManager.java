package de.sensordigitalmediagermany.lemonbasic.generic;

import android.support.annotation.Nullable;

import android.content.Context;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
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
    private static final Map<String, Boolean> tried = new HashMap<>();

    private static Thread worker;

    public static void nukeCache(Context context)
    {
        File[] nukes = context.getCacheDir().listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File file)
            {
                return file.toString().startsWith("cache_");
            }
        });

        for (File nuke : nukes)
        {
            Log.d(LOGTAG, "nukeCache: file=" + nuke.toString() + " del=" + nuke.delete());
        }

        cache.clear();
        tried.clear();
    }

    public static Drawable getDrawableOrFetch(Context context, ImageView iv, String url,
                                              int ivwidth, int ivheight, boolean rounded)
    {
        Boolean triedit;

        synchronized (cache)
        {
            String cacheTag = url + "|" + ivwidth + "|" + ivheight + "|" + rounded;

            Drawable image = cache.get(cacheTag);

            if (image != null) return image;

            triedit = tried.get(cacheTag);
        }

        if ((triedit == null) || ! triedit) fetchAssetImage(context, iv, url, ivwidth, ivheight, rounded);

        return null;
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

                Drawable drawable = getDrawableFromFile(qd.cx, qd.url, qd.ivwidth, qd.ivheight, qd.rounded);

                if (drawable == null)
                {
                    drawable = getDrawableFromHTTP(qd.cx, qd.url, qd.ivwidth, qd.ivheight, qd.rounded);
                }

                if (drawable != null)
                {
                    final ImageView imageView = qd.iv;
                    final Drawable drawableFinal = drawable;

                    ApplicationBase.handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            imageView.setImageDrawable(drawableFinal);
                        }
                    });
                }
            }

            worker = null;
        }
    };

    @Nullable
    private static Drawable getDrawableFromFile(Context context, String urlstring,
                                                int ivwidth, int ivheight, boolean rounded)
    {
        File cacheFile = getCacheFile(context, urlstring);

        if ((cacheFile != null) && cacheFile.exists())
        {
            Bitmap myBitmap = Simple.getBitmapFromFile(context, cacheFile);
            if (myBitmap == null) return null;

            int cornerRadius = rounded ? Defines.CORNER_RADIUS_ASSETS : 0;
            Bitmap rcBitmap = Simple.makeRoundedTopCornersBitmap(myBitmap, cornerRadius, ivwidth, ivheight);

            myBitmap.recycle();

            Drawable drawable = new BitmapDrawable(context.getResources(), rcBitmap);

            synchronized (cache)
            {
                String cacheTag = urlstring + "|" + ivwidth + "|" + ivheight + "|" + rounded;

                cache.put(cacheTag, drawable);
                tried.put(cacheTag, true);
            }

            Log.d(LOGTAG, "Asset Image file url=" + urlstring);

            return drawable;
        }

        return null;
    }

    @Nullable
    private static Drawable getDrawableFromHTTP(Context context, String urlstring,
                                        int ivwidth, int ivheight, boolean rounded)
    {
        if (urlstring == null) return null;

        try
        {
            URL url = new URL(Simple.urlEncodeFuckedUpDirty(urlstring));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStream inputStream = conn.getInputStream();
            byte[] rawimage = Simple.getAllInputBytes(inputStream);
            inputStream.close();

            if (rawimage != null)
            {
                Bitmap myBitmap = BitmapFactory.decodeByteArray(rawimage, 0, rawimage.length);
                if (myBitmap == null) return null;

                File cacheFile = getCacheFile(context, urlstring);
                Simple.putFileBytes(cacheFile, rawimage);

                int cornerRadius = rounded ? Defines.CORNER_RADIUS_ASSETS : 0;
                Bitmap rcBitmap = Simple.makeRoundedTopCornersBitmap(myBitmap, cornerRadius, ivwidth, ivheight);

                myBitmap.recycle();

                Drawable drawable = new BitmapDrawable(context.getResources(), rcBitmap);

                synchronized (cache)
                {
                    String cacheTag = urlstring + "|" + ivwidth + "|" + ivheight + "|" + rounded;

                    cache.put(cacheTag, drawable);
                    tried.put(cacheTag, true);
                }

                Log.d(LOGTAG, "Asset Image http url=" + urlstring);

                return drawable;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        synchronized (cache)
        {
            String cacheTag = urlstring + "|" + ivwidth + "|" + ivheight + "|" + rounded;

            if (Simple.isOnline(context))
            {
                tried.put(cacheTag, true);
            }
        }

        return null;
    }

    @Nullable
    private static File getCacheFile(Context context, String url)
    {
        try
        {
            String filename = "cache_" + url.substring(url.lastIndexOf('/') + 1, url.length());

            return new File(context.getCacheDir(), filename);
        }
        catch (Exception ignore)
        {
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
