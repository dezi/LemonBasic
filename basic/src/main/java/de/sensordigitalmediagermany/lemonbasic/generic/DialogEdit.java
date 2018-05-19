package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;

public class DialogEdit extends GenericEdit
{
    public DialogEdit(Context context)
    {
        super(context);

        setMinEms(Simple.isTablet() ? 12 : 9);
    }

    @Override
    public int getFontSize()
    {
        return Defines.FS_DIALOG_EDIT;
    }

    @Override
    public String getFontName()
    {
        return Defines.FONT_DIALOG_EDITS;
    }
}

