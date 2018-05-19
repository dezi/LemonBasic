package de.sensordigitalmediagermany.lemonbasic.generic;

import android.annotation.SuppressLint;

import android.content.Context;

@SuppressLint("AppCompatCustomView")
public class SettingsInfoText extends GenericText
{
    public SettingsInfoText(Context context)
    {
        super(context);

        setAllCaps(Defines.isInfosAllCaps);
        setMarginTopDip(Defines.PADDING_TINY);

        if (! Defines.isFlatEdits)
        {
            setBackgroundColor(Defines.COLOR_NAVIBAR);
            Simple.setPaddingDip(this, Defines.PADDING_SMALL);
        }
    }

    @Override
    public int getFontSize()
    {
        return Defines.FS_GENERIC_EDIT;
    }

    @Override
    public String getFontName()
    {
        return Defines.FONT_SETTINGS_INFOS;
    }
}
