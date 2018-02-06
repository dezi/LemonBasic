package de.sensordigitalmediagermany.lemonbasic.generic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class GenericImage extends ImageView
{
    public GenericImage(Context context)
    {
        super(context);

        if (Simple.isTV())
        {
            //
            // Image needs to be able to display the focus.
            //

            setFocusable(true);

            Simple.setPaddingDip(this,2);

            setOnFocusChangeListener(new OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View view, boolean hasfocus)
                {
                    if (hasfocus)
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

                        setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            });
        }
    }
}
