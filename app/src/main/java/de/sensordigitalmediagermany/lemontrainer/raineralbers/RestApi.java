package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.support.annotation.Nullable;

import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import org.json.JSONObject;


public class RestApi
{
    private static final String LOGTAG = RestApi.class.getSimpleName();

    public static void getPostThreaded(String what,
                                       JSONObject params,
                                       RestApiResultListener callback)
    {
        getPostThreaded(what, params, true, callback);
    }

    public static void getPostThreaded(final String what,
                                       final JSONObject params,
                                       final boolean callbackOnUIThread,
                                       final RestApiResultListener callback
    )
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    final JSONObject result = getPost(what, params);

                    if (callback != null)
                    {
                        if (callbackOnUIThread)
                        {
                            ApplicationBase.handler.post(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    callback.OnRestApiResult(what, params, result);
                                }
                            });
                        }
                        else
                        {
                            callback.OnRestApiResult(what, params, result);
                        }
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        thread.start();
    }

    @Nullable
    public static JSONObject getPost(String what, JSONObject params)
    {
        try
        {
            String src = Defines.APIURL + what + ".php";
            String dat = getPostDataString(params);

            URL url = new URL(src);

            Log.d(LOGTAG, "src=" + src);
            Log.d(LOGTAG, "dat=" + dat);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setConnectTimeout(4000);
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();

            OutputStream os = connection.getOutputStream();
            os.write(dat.getBytes("UTF-8"));
            os.flush();
            os.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP)
            {
                //
                // A cross protocoll redirect happened from
                // HTTP to HTTPS or vice versa.
                //

                String location = connection.getHeaderField("Location");

                Log.d(LOGTAG, "getContentFromServer: 302=" + location);
                Log.d(LOGTAG, "getContentFromServer: old=" + src);

                url = new URL(location);

                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(4000);
                connection.setUseCaches(false);
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.connect();
            }

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
            {
                //
                // File cannot be loaded.
                //

                Log.d(LOGTAG, "getContentFromServer: ERR=" + connection.getResponseCode());

                return null;
            }

            //
            // Fetch file.
            //

            int length = connection.getContentLength();
            InputStream input = connection.getInputStream();
            byte[] buffer;
            int total = 0;

            if (length > 0)
            {
                buffer = new byte[ length ];

                for (int xfer; total < length; total += xfer)
                {
                    xfer = input.read(buffer, total, length - total);
                }
            }
            else
            {
                byte[] chunk = new byte[ 32 * 1024 ];

                buffer = new byte[ 0 ];

                for (int xfer; ; total += xfer)
                {
                    xfer = input.read(chunk, 0, chunk.length);
                    if (xfer <= 0) break;

                    byte[] temp = new byte[ buffer.length + xfer ];
                    System.arraycopy(buffer, 0, temp, 0, buffer.length);
                    System.arraycopy(chunk, 0, temp, buffer.length, xfer);
                    buffer = temp;
                }
            }

            input.close();

            String result = new String(buffer);

            Log.d(LOGTAG, "getPost result=" + result);

            return Json.fromStringObject(result);
        }
        catch (Exception ex)
        {
            Log.d(LOGTAG, ex.toString());
        }

        return null;
    }

    public static String getPostDataString(JSONObject params)
    {
        String postData = "";

        Iterator<String> keysIterator = params.keys();

        while (keysIterator.hasNext())
        {
            String key = keysIterator.next();
            Object raw = Json.get(params, key);

            if (raw == null) continue;

            if (postData.length() > 0) postData += "&";

            try
            {
                postData = postData
                        + URLEncoder.encode(key, "UTF-8")
                        + "="
                        + URLEncoder.encode(raw.toString(), "UTF-8");

            }
            catch (Exception ex)
            {
                Log.d(LOGTAG, ex.toString());
            }
        }

        return postData;
    }

    public interface RestApiResultListener
    {
        void OnRestApiResult(String what, JSONObject params, JSONObject result);
    }
}
