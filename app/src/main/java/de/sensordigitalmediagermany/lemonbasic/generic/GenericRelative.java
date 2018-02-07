package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

public class GenericRelative extends LinearLayout
{
    public GenericRelative(Context context)
    {
        super(context);
    }

    @Override
    public void setFocusable(boolean focusable)
    {
        super.setFocusable(focusable);

        if (focusable)
        {
            Simple.setPaddingDip(this, 2);

            setOnFocusChangeListener(new OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View view, boolean hasFocus)
                {
                    if (Simple.isTV())
                    {
                        if (hasFocus)
                        {
                            //
                            // Dismiss any keyboard.
                            //

                            Simple.hideSoftKeyBoard(view);

                            //
                            // Display focus frame around image.
                            //

                            Simple.setRoundedCorners(view, 0, Color.TRANSPARENT, Defines.COLOR_TV_FOCUS);
                        }
                        else
                        {
                            //
                            // Make neutral again.
                            //

                            view.setBackgroundColor(Color.TRANSPARENT);
                        }
                    }
                }
            });
        }
        else
        {
            Simple.setPaddingDip(this, 0);

            setOnFocusChangeListener(null);
        }
    }
}
