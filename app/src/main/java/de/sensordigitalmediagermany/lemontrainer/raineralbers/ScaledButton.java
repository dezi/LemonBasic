package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.widget.RelativeLayout;
import android.widget.FrameLayout;
import android.content.Context;
import android.graphics.Rect;

public class ScaledButton extends RelativeLayout
{
    public ScaledButton(Context context)
    {
        super(context);
    }

    public void setContent(Rect rect, int imageresid)
    {
        FrameLayout.LayoutParams lp = Simple.getScaledLayout(getContext(), rect, imageresid);

        setLayoutParams(lp);
        setBackgroundColor(0x88880000);
    }
}
