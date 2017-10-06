package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.support.annotation.Nullable;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssetsDownloadManager
{
    private static final String LOGTAG = AssetsDownloadManager.class.getSimpleName();

    private static final ArrayList<QueueData> queue = new ArrayList<>();
    private static final Map<String, File> cache = initializeCache();

    private static Thread worker;

    private static Map<String, File> initializeCache()
    {
        Map<String, File> newcache = new HashMap<>();

        File cacheDir = ContentHandler.getStorageDir();

        File[] files = cacheDir.listFiles();

        for (int inx = 0; inx < files.length; ++inx)
        {
            if (! files[ inx ].isFile()) continue;

            newcache.put(files[ inx ].getName(), files[ inx ]);
        }

        return newcache;
    }

    public static void getContentOrFetch(JSONObject content, OnFileLoadedHandler onFileLoadedHandler)
    {
        synchronized (cache)
        {
            String urlstring = Json.getString(content, "content_url");
            String name = Json.getString(content, "content_file_name");

            if ((urlstring != null) && (name != null))
            {
                File file = cache.get(name);

                if (file != null)
                {
                    onFileLoadedHandler.OnFileLoaded(content, file);

                    return;
                }
            }
            else
            {
                onFileLoadedHandler.OnFileLoaded(content, null);

                return;
            }
        }

        fetchProfileImage(content, onFileLoadedHandler);
    }

    private static void fetchProfileImage(JSONObject content, OnFileLoadedHandler onFileLoadedHandler)
    {
        synchronized (queue)
        {
            queue.add(new QueueData(content, onFileLoadedHandler));
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
                final QueueData qd;

                synchronized (queue)
                {
                    if (queue.size() == 0) break;

                    qd = queue.remove(0);
                }

                final File file = getFile(qd.content);

                    ApplicationBase.handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            qd.onFileLoadedHandler.OnFileLoaded(qd.content, file);
                        }
                    });
            }

            worker = null;
        }
    };

    @Nullable
    private static File getFile(JSONObject content)
    {
        try
        {
            String urlstring = Json.getString(content, "content_url");
            String name = Json.getString(content, "content_file_name");

            if ((urlstring != null) && (name != null))
            {
                URL url = new URL(urlstring);

                Log.d(LOGTAG, "getFile: load url=" + urlstring);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream inputStream = conn.getInputStream();

                File file = new File(ContentHandler.getStorageDir(), name);
                FileOutputStream outputStream = new FileOutputStream(file);

                byte[] chunk = new byte[8192];
                int xfer;

                try
                {
                    while ((xfer = inputStream.read(chunk)) > 0)
                    {
                        outputStream.write(chunk, 0, xfer);
                    }
                }
                catch (IOException ex)
                {
                    Log.d(LOGTAG, ex.toString());

                    return null;
                }

                outputStream.close();
                inputStream.close();

                synchronized (cache)
                {
                    cache.put(name, file);
                }

                Log.d(LOGTAG, "getFile: done url=" + urlstring);

                return file;
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

    public static void removeFromCache(JSONObject content)
    {
        String name = Json.getString(content, "content_file_name");

        if (name != null)
        {
            synchronized (cache)
            {
                cache.remove(name);
            }
        }
    }

    private static class QueueData
    {
        public JSONObject content;
        public OnFileLoadedHandler onFileLoadedHandler;

        public QueueData(JSONObject content, OnFileLoadedHandler onFileLoadedHandler)
        {
            this.content = content;
            this.onFileLoadedHandler = onFileLoadedHandler;
        }
    }

    public interface OnFileLoadedHandler
    {
        void OnFileLoaded(JSONObject content, File file);
    }
}
