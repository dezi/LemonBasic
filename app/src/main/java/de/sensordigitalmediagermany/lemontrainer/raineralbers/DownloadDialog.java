package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.view.View;

public class DownloadDialog extends DialogView
{
    private static final String LOGTAG = DownloadDialog.class.getSimpleName();

    private ProgressBar downloadProgress;

    public DownloadDialog(Context context)
    {
        super(context);

        setTitleText(R.string.download_title);

        setNegativeButton(R.string.download_cancel, new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        downloadProgress = new ProgressBar(getContext());
        downloadProgress.setWidthDip(Defines.PROGRESS_DIALOG_WIDTH);
        downloadProgress.setColors(Color.BLACK, Color.WHITE);

        dialogItems.addView(downloadProgress);

        setCustomView(dialogItems);
    }

    public void setProgressLong(long current, long total)
    {
        downloadProgress.setProgressLong(current, total);
    }
}
