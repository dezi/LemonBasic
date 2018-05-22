package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Color;

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

    @Override
    public void setDefaultButton(boolean set)
    {
        if (Defines.isSolidButton)
        {
            int backgroundColor;

            if (set)
            {
                backgroundColor = Defines.COLOR_BUTTON_BACK;
                super.setTextColor(Color.WHITE);
            }
            else
            {
                backgroundColor = Color.WHITE;
                super.setTextColor(Defines.COLOR_BUTTON_DIALOG);
            }

            Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BUTTON, backgroundColor, Defines.COLOR_BUTTON_DIALOG);
        }
        else
        {
            super.setDefaultButton(set);
        }
    }
}
