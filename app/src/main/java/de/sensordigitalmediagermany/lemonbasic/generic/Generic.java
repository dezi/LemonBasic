package de.sensordigitalmediagermany.lemonbasic.generic;

import android.view.View;

public class Generic
{
    private static final String LOGTAG = Generic.class.getSimpleName();

    public static boolean canFocus(View view)
    {
        return (view instanceof GenericFocus) && ((GenericFocus) view).getFocusable();
    }

    public final static View.OnFocusChangeListener genericOnFocusChangeListener = new View.OnFocusChangeListener()
    {
        @Override
        public void onFocusChange(View view, boolean hasFocus)
        {
            GenericFocus gf = view instanceof GenericFocus ? (GenericFocus) view : null;

            if (hasFocus)
            {
                //
                // Dismiss any keyboard.
                //

                Simple.hideSoftKeyBoard(view);

                //
                // Display focus frame around image.
                //

                if (gf != null)
                {
                    Simple.setRoundedCorners(view, 0, gf.getBackgroundColor(), Defines.COLOR_TV_FOCUS);
                }
            }
            else
            {
                //
                // Make neutral again.
                //

                if (gf != null)
                {
                    view.setBackgroundColor(gf.getBackgroundColor());
                }
            }
        }
    };

    public static void setupFocusChange(View view, boolean focusable)
    {
        if (view instanceof GenericFocus)
        {
            if (focusable && Simple.isTV())
            {
                int padneed = Simple.dipToPx(2);

                int padleft = view.getPaddingLeft();
                int padtop = view.getPaddingTop();
                int padright = view.getPaddingRight();
                int padbottom = view.getPaddingBottom();

                if (padleft < padneed) padleft = padneed;
                if (padtop < padneed) padtop = padneed;
                if (padright < padneed) padright = padneed;
                if (padbottom < padneed) padbottom = padneed;

                view.setPadding(padleft, padtop, padright, padbottom);

                view.setOnFocusChangeListener(genericOnFocusChangeListener);
            }
            else
            {
                view.setOnFocusChangeListener(null);
            }
        }
    }
}
