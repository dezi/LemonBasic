package de.sensordigitalmediagermany.lemonbasic.generic;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("AppCompatCustomView")
public class DialogInfo extends GenericText
{
    public DialogInfo(Context context)
    {
        super(context);

        setAllCaps(Defines.isInfosAllCaps);
        setTextColor(Defines.COLOR_DIALOG_INFOS);
        setMarginTopDip(Defines.PADDING_TINY);
    }

    @Override
    public int getFontSize()
    {
        return Defines.FS_DIALOG_INFO;
    }

    @Override
    public String getFontName()
    {
        return Defines.FONT_DIALOG_INFOS;
    }
}
