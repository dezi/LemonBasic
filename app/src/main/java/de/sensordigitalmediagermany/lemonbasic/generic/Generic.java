package de.sensordigitalmediagermany.lemonbasic.generic;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class Generic
{
    private static final String LOGTAG = Generic.class.getSimpleName();

    public static boolean canFocus(View view)
    {
        return (view instanceof GenericFocus) && ((GenericFocus) view).getFocusable();
    }

    public static void setupFocusChange(View view, boolean focusable)
    {
        if (view instanceof GenericFocus)
        {
            final GenericFocus gf = (GenericFocus) view;

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

                view.setOnFocusChangeListener(new View.OnFocusChangeListener()
                {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus)
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

                            Simple.setRoundedCorners(view, 0, gf.getBackgroundColor(), Defines.COLOR_TV_FOCUS);

                            if (view.getParent() instanceof AssetGridView)
                            {
                                //
                                // Special handling for fucked up
                                // scroll behaviour in GridView.
                                //

                                AssetGridView gridview = (AssetGridView) view.getParent();

                                int itemheight = view.getHeight() + gridview.getVerticalSpacing();

                                int stop = gridview.getScrollY();
                                int sbot = stop + gridview.getHeight();

                                stop += itemheight;
                                sbot -= itemheight / 10;

                                int itop = view.getTop();
                                int ibot = view.getBottom();

                                Log.d(LOGTAG, "onFocusChange: gridview=" + view);
                                Log.d(LOGTAG, "onFocusChange: parent.height=" + gridview.getHeight());
                                Log.d(LOGTAG, "onFocusChange: parent.scrolly=" + gridview.getScrollY());
                                Log.d(LOGTAG, "onFocusChange: view.top=" + view.getTop());
                                Log.d(LOGTAG, "onFocusChange: view.height=" + view.getHeight());
                                Log.d(LOGTAG, "onFocusChange: parent.ascount=" + gridview.getAdapter().getCount());
                                Log.d(LOGTAG, "onFocusChange: parent.cghcount=" + gridview.getChildCount());

                                if (itop < stop)
                                {
                                    int maxscroll = Math.min(itemheight, gridview.getScrollY());

                                    gridview.scrollBy(0, -maxscroll);
                                }
                                else
                                {
                                    if (ibot > sbot)
                                    {
                                        int restscroll = gridview.getTotalHeight() - ibot;
                                        int maxscroll = Math.min(itemheight, restscroll);

                                        gridview.scrollBy(0, +maxscroll);
                                    }
                                }
                            }
                        }
                        else
                        {
                            //
                            // Make neutral again.
                            //

                            view.setBackgroundColor(gf.getBackgroundColor());
                        }
                    }
                });
            }
            else
            {
                view.setOnFocusChangeListener(null);
            }
        }
    }
}
