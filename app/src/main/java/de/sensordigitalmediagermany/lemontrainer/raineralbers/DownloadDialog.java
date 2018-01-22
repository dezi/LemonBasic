package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View;

public class DownloadDialog extends DialogView
{
    private static final String LOGTAG = DownloadDialog.class.getSimpleName();

    private ProgressBar downloadProgress;
    private TextView percentView;
    private TextView sizeView;

    private boolean cancel;

    public DownloadDialog(Context context)
    {
        super(context);

        setTitleText(R.string.download_title);

        setNegativeButton(R.string.download_cancel, new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                cancel = true;
            }
        });

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);

        Simple.setPaddingDip(dialogItems, 0, 0, 0, Defines.PADDING_LARGE);

        LinearLayout progressItems = new LinearLayout(getContext());
        progressItems.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(progressItems, Simple.MP, Simple.WC);
        Simple.setMarginBottomDip(progressItems, Defines.PADDING_NORMAL);

        dialogItems.addView(progressItems);

        percentView = new TextView(getContext());
        percentView.setTextColor(Defines.COLOR_DIALOG_INFOS);
        percentView.setTypeface(infosFont);
        percentView.setAllCaps(Defines.isInfosAllCaps);
        Simple.setSizeDip(percentView, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(percentView, Defines.FS_DIALOG_INFO);

        progressItems.addView(percentView);

        sizeView = new TextView(getContext());
        sizeView.setTextColor(Defines.COLOR_DIALOG_INFOS);
        sizeView.setTypeface(infosFont);
        sizeView.setGravity(Gravity.END);
        sizeView.setAllCaps(Defines.isInfosAllCaps);
        Simple.setSizeDip(sizeView, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(sizeView, Defines.FS_DIALOG_INFO);

        progressItems.addView(sizeView);

        downloadProgress = new ProgressBar(getContext());
        downloadProgress.setWidthDip(Defines.PROGRESS_DIALOG_WIDTH);
        downloadProgress.setColors(Defines.COLOR_PROGRESS_DONE, Defines.COLOR_PROGRESS_NEED);

        dialogItems.addView(downloadProgress);

        setCustomView(dialogItems);
    }

    public boolean setProgressLong(long current, long total)
    {
        if (total > 0)
        {
            long percent = current * 100 / total;
            String percentstr = percent + " %";

            percentView.setText(percentstr);

            String ckb = Simple.formatDecimal(current / 1024);
            String tmb = Simple.formatDecimal(total / (1024 * 1000));

            String sizestr = Simple.getTrans(getContext(), R.string.download_size, ckb, tmb);

            sizeView.setText(sizestr);
        }

        downloadProgress.setProgressLong(current, total);

        return cancel;
    }
}
