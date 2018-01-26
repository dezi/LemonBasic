package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;

@SuppressLint("AppCompatCustomView")
public class SettingsTitle extends GenericText
{
    public SettingsTitle(Context context)
    {
        super(context);

        setAllCaps(Defines.isInfosAllCaps);
        setTextColor(Defines.COLOR_SETTINGS_HEADERS);
    }

    @Override
    public int getFontSize()
    {
        return Defines.FS_SETTINGS_TITLE;
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
