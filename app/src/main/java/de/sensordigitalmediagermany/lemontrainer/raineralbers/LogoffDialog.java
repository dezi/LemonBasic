package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LogoffDialog extends DialogView
{
    private static final String LOGTAG = LogoffDialog.class.getSimpleName();

    public LogoffDialog(Context context)
    {
        super(context);

        setCloseButton(true, null);

        setTitleText(R.string.logoff_title);

        String infostr = Simple.getTrans(context, Defines.logoff_info) + " " + Simple.getTrans(context, R.string.logoff_info2);

        setInfoText(infostr);

        setNegativeButton(R.string.button_cancel, null);

        setPositiveButton(R.string.logoff_logoff, new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ContentHandler.deleteAllCachedFiles();

                SettingsHandler.killSettings();

                Simple.startActivityTop(getContext(), MainActivity.class);
            }
        });
    }
}
