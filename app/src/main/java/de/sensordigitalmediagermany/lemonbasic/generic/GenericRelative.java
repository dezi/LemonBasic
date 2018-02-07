package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

public class GenericRelative extends RelativeLayout implements GenericFocus
{
    private boolean focusable = false;
    private int backgroundColor = Color.TRANSPARENT;

    public GenericRelative(Context context)
    {
        super(context);
    }

    @Override
    public int getBackgroundColor()
    {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int color)
    {
        backgroundColor = color;

        super.setBackgroundColor(color);
    }

    @Override
    public boolean getFocusable()
    {
        return focusable;
    }

    @Override
    public void setFocusable(boolean focusable)
    {
        this.focusable = focusable;

        super.setFocusable(focusable);

        Generic.setupFocusChange(this, focusable);
    }

    @Override
    public void setOnClickListener(View.OnClickListener onClickListener)
    {
        super.setOnClickListener(onClickListener);

        setFocusable(onClickListener != null);
    }
}
