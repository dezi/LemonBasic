package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;

public class DialogButton extends GenericButton
{
    public DialogButton(Context context)
    {
        super(context);

        //
        // We assume two buttons in dialog.
        // If there is only one, the weight
        // is meaningsless.
        //

        setWeight(0.5f);
    }

    @Override
    public int getFontSize()
    {
        return Defines.FS_DIALOG_BUTTON;
    }

    @Override
    public String getFontName()
    {
        return Defines.FONT_DIALOG_BUTTON;
    }
}
