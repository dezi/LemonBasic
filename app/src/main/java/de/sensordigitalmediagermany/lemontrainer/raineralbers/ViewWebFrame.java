package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.TargetApi;
import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.InputStream;
import java.io.File;

public class ViewWebFrame extends FrameLayout
{
    private final static String LOGTAG = ViewWebFrame.class.getSimpleName();

    private final static String DUMMYHOSTNAME = "xzzylemondummy42";

    private WebView webview;
    private ZipFile zipFile;

    @SuppressLint("SetJavaScriptEnabled")
    public ViewWebFrame(Context context)
    {
        super(context);

        setBackgroundColor(Color.BLACK);

        webview = new WebView(getContext());

        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setDatabaseEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setSupportMultipleWindows(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webview.setWebViewClient(new UriWebViewClient());

        webview.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg)
            {
                Log.d(LOGTAG, "WebChromeClient: onCreateWindow");

                /*
                WebView.HitTestResult result = view.getHitTestResult();
                String data = result.getExtra();
                Context context = view.getContext();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
                context.startActivity(browserIntent);
                */

                return false;
            }
        });

        addView(webview);
    }

    @Override
    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();

        loadMedia();
    }

    @Override
    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();

        unloadMedia();
    }

    private void loadMedia()
    {
        try
        {
            File cacheFile = ContentHandler.getCachedFile(Globals.displayContent);

            if (cacheFile != null)
            {
                zipFile = new ZipFile(cacheFile);

                /*
                Enumeration<? extends ZipEntry> entries = zipFile.entries();

                while (entries.hasMoreElements())
                {
                    ZipEntry entry = entries.nextElement();

                    String fileName = entry.getName();

                    Log.d(LOGTAG, "file unzip : " + fileName);
                }
                */

                //
                // Important:
                //
                // We do NOT use file:// scheme here but a dummy host name,
                // because some cross domain issues with iframe access are
                // not validated correct in file mode.
                //
                // Basicly this is a bug in Chrome, which evaluates protocol and port
                // to a null object when in file-scheme and believes, a cross domain
                // access takes place. So it is denied.
                //

                webview.loadUrl("http://" + DUMMYHOSTNAME + "/content/index.html");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void unloadMedia()
    {
        if (zipFile != null)
        {
            try
            {
                zipFile.close();
                zipFile = null;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private class UriWebViewClient extends WebViewClient
    {
        @Override
        @SuppressWarnings("deprecation")
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            Log.d(LOGTAG, "shouldOverrideUrlLoading url=" + url);

            return handleUri(Uri.parse(url));
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
        {
            Log.d(LOGTAG, "shouldOverrideUrlLoading request=" + request);

            return handleUri(request.getUrl());
        }

        private boolean handleUri(Uri uri)
        {
            Log.d(LOGTAG, "handleUri uri=" + uri.toString());

            return false;
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request)
        {
            Uri uri = request.getUrl();

            Log.d(LOGTAG, "shouldInterceptRequest: uri=" + uri.toString());

            if (! uri.getHost().equals(DUMMYHOSTNAME)) return null;

            try
            {
                String path = uri.getPath();

                if (path.startsWith("/")) path = path.substring(1);

                Log.d(LOGTAG, "shouldInterceptRequest: path=" + path);

                String mt = null;

                if (path.endsWith(".js")) mt = "text/javascript";
                if (path.endsWith(".css")) mt = "text/css";
                if (path.endsWith(".png")) mt = "image/png";
                if (path.endsWith(".jpg")) mt = "image/jpeg";
                if (path.endsWith(".mp4")) mt = "video/mp4";
                if (path.endsWith(".mp3")) mt = "audio/mpeg";
                if (path.endsWith(".pdf")) mt = "application/pdf";
                if (path.endsWith(".ttf")) mt = "application/x-font-ttf";
                if (path.endsWith(".woff")) mt = "application/x-font-woff";
                if (path.endsWith(".html")) mt = "text/html";

                if (mt == null) Log.d(LOGTAG, "shouldInterceptRequest: unknown-mt:" + path);

                ZipEntry entry = zipFile.getEntry(path);
                InputStream entryStream = zipFile.getInputStream(entry);

                return new WebResourceResponse(mt, "UTF-8", entryStream);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            return null;
        }
   }
}
