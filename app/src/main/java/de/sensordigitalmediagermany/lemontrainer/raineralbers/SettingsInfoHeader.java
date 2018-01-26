package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class SettingsInfoHeader extends GenericText
{
    public SettingsInfoHeader(Context context)
    {
        super(context);

        setTextColor(Color.BLACK);
        setAllCaps(Defines.isInfosAllCaps);
        setMarginTopDip(Defines.PADDING_SMALL);
    }

    @Override
    public int getFontSize()
    {
        return Defines.FS_SETTINGS_INFO;
    }

    @Override
    public String getFontName()
    {
        return Defines.FONT_SETTINGS_HEADER;
    }

    @Override
    public float getLetterSpacing()
    {
        return Defines.LETTERSPACE_GENERIC_HEADER;
    }
}
