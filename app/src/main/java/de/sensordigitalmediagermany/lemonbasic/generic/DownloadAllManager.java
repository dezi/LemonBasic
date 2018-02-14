package de.sensordigitalmediagermany.lemonbasic.generic;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class DownloadAllManager
{
    private static final String LOGTAG = DownloadAllManager.class.getSimpleName();

    private ViewGroup rootframe;

    private JSONArray uncachedItems;
    private long uncachedTotal;

    private DownloadDialog downloadDialog;
    private boolean downloadCancel;

    private long downloadTotal;
    private long downloadBytes;
    private int downloadItems;

    private long downloadProgressRunCurrent;

    public void askDownloadAllContent(ViewGroup rootframe)
    {
        askDownloadAllContent(rootframe, null);
    }

    public void askDownloadAllContent(ViewGroup rootframe, JSONObject content)
    {
        this.rootframe = rootframe;

        uncachedItems = ContentHandler.getUnCachedContent(content);
        uncachedTotal = ContentHandler.getTotalFileSize(uncachedItems);

        Log.d(LOGTAG, "askDownloadAllContent: uncachedItems=" + uncachedItems.length() + " uncachedTotal=" + uncachedTotal);

        if (uncachedItems.length() == 0)
        {
            DialogView.errorAlert(rootframe,
                    R.string.ask_download_all_done_title,
                    R.string.ask_download_all_done_info);

            return;
        }

        if (Simple.isOnline(rootframe.getContext()))
        {
            String infoText = Simple.getTrans(rootframe.getContext(),
                    (uncachedItems.length() == 1)
                            ? R.string.ask_download_all_info_onecontent
                            : R.string.ask_download_all_info,
                    String.valueOf(uncachedItems.length()),
                    Simple.formatBytes(uncachedTotal));

            DialogView askdialog = new DialogView(rootframe.getContext());

            askdialog.setCloseButton(true, null);

            askdialog.setTitleText(R.string.ask_download_all_title);
            askdialog.setInfoText(infoText);

            askdialog.setNegativeButton(R.string.button_cancel);

            askdialog.setPositiveButton(R.string.ask_download_all_load, new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    downloadAllContent();
                }
            });

            if (! Simple.isTablet())
            {
                askdialog.setButtonsVertical(true);
            }

            askdialog.positiveButton.requestFocus();

            rootframe.addView(askdialog);
        }
        else
        {
            DialogView.errorAlert(rootframe,
                    R.string.alert_no_internet_title,
                    R.string.alert_no_internet_info);
        }
    }

    public void requestCancel()
    {
        downloadCancel = true;
    }

    private void downloadAllContent()
    {
        downloadCancel = false;

        downloadTotal = uncachedTotal;
        downloadBytes = 0;
        downloadItems = 0;

        downloadDialog = new DownloadDialog(rootframe.getContext());
        rootframe.addView(downloadDialog);

        for (int inx = 0; inx < uncachedItems.length(); inx++)
        {
            JSONObject downloadcontent = Json.getObject(uncachedItems, inx);
            if (downloadcontent == null) continue;

            AssetsDownloadManager.getContentOrFetch(downloadcontent, onFileLoadedHandler, onDownloadProgressHandler);
        }
    }

    private final AssetsDownloadManager.OnFileLoadedHandler onFileLoadedHandler
            = new AssetsDownloadManager.OnFileLoadedHandler()
    {
        @Override
        public void OnFileLoaded(JSONObject content, File file)
        {
            long file_size = Json.getLong(content, "file_size");

            Log.d(LOGTAG, "onFileLoadedHandler: file=" + file + " size=" + file_size);

            downloadBytes += file_size;
            downloadItems += 1;

            if (downloadItems == uncachedItems.length())
            {
                downloadDialog.dismissDialog();
                downloadDialog = null;

                checkForAssetGritUpdate();
            }
        }
    };

    private final AssetsDownloadManager.OnDownloadProgressHandler onDownloadProgressHandler
            = new AssetsDownloadManager.OnDownloadProgressHandler()
    {
        @Override
        public boolean OnDownloadProgress(JSONObject content, long current, long total)
        {
            downloadProgressRunCurrent = current;

            ApplicationBase.handler.removeCallbacks(downloadProgressRun);
            ApplicationBase.handler.post(downloadProgressRun);

            if (downloadCancel)
            {
                ApplicationBase.handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (downloadDialog != null)
                        {
                            downloadDialog.dismissDialog();
                            downloadDialog = null;
                        }

                        checkForAssetGritUpdate();
                    }
                }, 250);
            }

            return downloadCancel;
        }
    };

    private final Runnable downloadProgressRun = new Runnable()
    {
        @Override
        public void run()
        {
            if (downloadDialog != null)
            {
                downloadCancel = downloadDialog.setProgressLong(downloadProgressRunCurrent + downloadBytes, downloadTotal);
            }
        }
    };

    private void checkForAssetGritUpdate()
    {
        Activity activity = ApplicationBase.getCurrentActivity(rootframe.getContext());

        if (activity instanceof SettingsActivity)
        {
            ((SettingsActivity) activity).reloadContent();
            ((SettingsActivity) activity).updateContent();
        }
        else
        {
            if (activity instanceof ContentBaseActivity)
            {
                ((ContentBaseActivity) activity).assetGrid.updateContent();
            }
        }
    }
}
