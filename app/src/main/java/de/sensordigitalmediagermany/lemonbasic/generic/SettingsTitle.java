package de.sensordigitalmediagermany.lemonbasic.generic;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("AppCompatCustomView")
public class SettingsTitle extends GenericText
{
    public SettingsTitle(Context context)
    {
        super(context);

        setSingleLine();
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
