package de.sensordigitalmediagermany.lemonbasic.generic;

import android.widget.HorizontalScrollView;
import android.content.Context;

public class GenericScrollHorz extends HorizontalScrollView
{
    public GenericScrollHorz(Context context)
    {
        super(context);

        //
        // Focus screws things up on android TV.
        //

        setFocusable(false);
    }
}
