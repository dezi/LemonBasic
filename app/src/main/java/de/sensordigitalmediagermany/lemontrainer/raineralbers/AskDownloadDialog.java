package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.view.View;

public class AskDownloadDialog extends DialogView
{
    private static final String LOGTAG = AskDownloadDialog.class.getSimpleName();

    public AskDownloadDialog(Context context)
    {
        super(context);

        setCloseButton(true, null);

        setTitleText(R.string.ask_download_title);

        setInfoText(R.string.ask_download_info);

        setNegativeButton(R.string.ask_download_load_and_view, null);

        setPositiveButton(R.string.ask_download_only_load, new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });
    }
}
