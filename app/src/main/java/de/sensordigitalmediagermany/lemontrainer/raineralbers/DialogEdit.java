package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;

public class DialogEdit extends GenericEdit
{
    public DialogEdit(Context context)
    {
        super(context);
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

