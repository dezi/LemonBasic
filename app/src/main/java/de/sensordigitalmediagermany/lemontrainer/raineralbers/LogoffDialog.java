package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.view.View;

public class LogoffDialog extends DialogView
{
    public LogoffDialog(Context context)
    {
        super(context);

        setCloseButton(true);

        setTitleText(R.string.logoff_title);

        String infostr = Simple.getTrans(context, Defines.logoff_info)
                + " "
                + Simple.getTrans(context, R.string.logoff_info2);

        setInfoText(infostr);

        setNegativeButton(R.string.button_cancel);

        setPositiveButton(R.string.logoff_logoff, new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ContentHandler.deleteAllCachedFiles();

                RestApi.nukeSavedQueries(getContext());

                SettingsHandler.killSettings();

                AssetsImageManager.nukeCache(getContext());

                Simple.startActivityTop(getContext(), MainActivity.class);
            }
        });
    }
}
