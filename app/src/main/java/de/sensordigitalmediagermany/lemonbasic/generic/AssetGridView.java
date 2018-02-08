package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.widget.GridView;

public class AssetGridView extends GridView
{
    public AssetGridView(Context context)
    {
        super(context);

        setFocusable(false);
    }

    public int getTotalHeight()
    {
        int height = 0;

        if (getChildCount() > 0)
        {
            int items = getAdapter().getCount();
            int numcols = getNumColumns();
            int numrows = (int) Math.ceil(items / numcols);
            int itemheight = getChildAt(0).getHeight();

            height = (numrows * itemheight) + ((numrows - 1) * getVerticalSpacing());
        }

        return height;
    }
}
