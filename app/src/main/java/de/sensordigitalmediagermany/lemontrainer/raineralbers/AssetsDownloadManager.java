package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.support.annotation.Nullable;

import android.util.Log;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.File;
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

    private static QueueData current;
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

    public static boolean connectDownload(JSONObject content, OnDownloadProgressHandler onDownloadProgressHandler)
    {
        return connectDownload(content, null, onDownloadProgressHandler);
    }

    public static boolean connectDownload(JSONObject content,
                                          OnFileLoadedHandler onFileLoadedHandler,
                                          OnDownloadProgressHandler onDownloadProgressHandler)
    {
        String urlstring = Simple.urlEncodeFuckedUpDirty(Json.getString(content, "content_url"));
        String name = Json.getString(content, "content_file_name");

        if ((urlstring != null) && (name != null))
        {
            synchronized (queue)
            {
                QueueData qd = current;

                if ((qd != null) && Simple.equals(name, Json.getString(qd.content, "content_file_name")))
                {
                    qd.onFileLoadedHandler = onFileLoadedHandler;
                    qd.onDownloadProgressHandler = onDownloadProgressHandler;

                    return true;
                }
                else
                {
                    for (QueueData qdc : queue)
                    {
                        if (Simple.equals(name, Json.getString(qdc.content, "content_file_name")))
                        {
                            qdc.onFileLoadedHandler = onFileLoadedHandler;
                            qdc.onDownloadProgressHandler = onDownloadProgressHandler;

                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static void getContentOrFetch(JSONObject content,
                                         OnFileLoadedHandler onFileLoadedHandler,
                                         OnDownloadProgressHandler onDownloadProgressHandler)
    {
        String urlstring = Simple.urlEncodeFuckedUpDirty(Json.getString(content, "content_url"));
        String name = Json.getString(content, "content_file_name");

        if ((urlstring != null) && (name != null))
        {
            synchronized (cache)
            {
                File file = cache.get(name);

                if (file != null)
                {
                    if (onFileLoadedHandler != null)
                        onFileLoadedHandler.OnFileLoaded(content, file);

                    return;
                }
            }
        }
        else
        {
            if (onFileLoadedHandler != null) onFileLoadedHandler.OnFileLoaded(content, null);

            return;
        }

        fetchContentData(content, onFileLoadedHandler, onDownloadProgressHandler);
    }

    private static void fetchContentData(JSONObject content,
                                         OnFileLoadedHandler onFileLoadedHandler,
                                         OnDownloadProgressHandler onDownloadProgressHandler)
    {
        synchronized (queue)
        {
            queue.add(new QueueData(content, onFileLoadedHandler, onDownloadProgressHandler));
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
                synchronized (queue)
                {
                    if (queue.size() == 0) break;

                    current = queue.remove(0);
                }

                final QueueData qd = current;

                if (qd.onFileLoadedHandler != null)
                {
                    final File file = getFile(qd);

                    ApplicationBase.handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            qd.onFileLoadedHandler.OnFileLoaded(qd.content, file);
                        }
                    });
                }

                current = null;
            }

            worker = null;
        }
    };

    @Nullable
    private static File getFile(QueueData qd)
    {
        File tempfile = null;
        File realfile = null;

        try
        {
            String urlstring = Simple.urlEncodeFuckedUpDirty(Json.getString(qd.content, "content_url"));
            String name = Json.getString(qd.content, "content_file_name");

            long total = Json.getLong(qd.content, "file_size");
            long current = 0;

            if ((urlstring != null) && (name != null))
            {
                String temp = name + ".tmp";

                realfile = new File(ContentHandler.getStorageDir(), name);
                tempfile = new File(ContentHandler.getStorageDir(), temp);

                URL url = new URL(urlstring);

                Log.d(LOGTAG, "getFile: load url=" + urlstring);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream inputStream = conn.getInputStream();

                FileOutputStream outputStream = new FileOutputStream(tempfile);

                boolean cancel = false;
                byte[] chunk = new byte[8192];
                int xfer;

                while ((xfer = inputStream.read(chunk)) > 0)
                {
                    outputStream.write(chunk, 0, xfer);

                    current += xfer;

                    OnDownloadProgressHandler qdph = qd.onDownloadProgressHandler;

                    if (qdph != null)
                    {
                        cancel = qdph.OnDownloadProgress(qd.content, current, total);
                    }

                    if (cancel) break;
                }

                outputStream.close();
                inputStream.close();

                if (! cancel)
                {
                    if (tempfile.renameTo(realfile))
                    {
                        synchronized (cache)
                        {
                            cache.put(name, realfile);
                        }

                        Log.d(LOGTAG, "getFile: done url=" + urlstring);

                        return realfile;
                    }
                }
            }
        }
        catch (Exception ex)
        {
            Log.d(LOGTAG, ex.toString());
        }

        OnDownloadProgressHandler qdph = qd.onDownloadProgressHandler;
        if (qdph != null) qdph.OnDownloadProgress(qd.content, 0, 0);

        if (tempfile != null)
        {
            Log.d(LOGTAG, "getFile: delete temp=" + tempfile.delete());
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
        public OnDownloadProgressHandler onDownloadProgressHandler;

        public QueueData(JSONObject content,
                         OnFileLoadedHandler onFileLoadedHandler,
                         OnDownloadProgressHandler onDownloadProgressHandler)
        {
            this.content = content;
            this.onFileLoadedHandler = onFileLoadedHandler;
            this.onDownloadProgressHandler = onDownloadProgressHandler;
        }
    }

    public interface OnDownloadProgressHandler
    {
        boolean OnDownloadProgress(JSONObject content, long current, long total);
    }

    public interface OnFileLoadedHandler
    {
        void OnFileLoaded(JSONObject content, File file);
    }
}
