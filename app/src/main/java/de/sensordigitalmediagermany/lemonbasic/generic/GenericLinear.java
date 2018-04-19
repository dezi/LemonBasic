package de.sensordigitalmediagermany.lemonbasic.generic;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

public class GenericLinear extends LinearLayout implements GenericFocus
{
    private boolean focusable = false;
    private int backgroundColor = Color.TRANSPARENT;

    public GenericLinear(Context context)
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

    public void setWeight(float weight)
    {
        ViewGroup.LayoutParams lp = getLayoutParams();

        int width = (lp == null) ? Simple.MP : lp.width;
        int height = (lp == null) ? Simple.WC : lp.height;

        Simple.setSizeDip(this, width, height, weight);
    }

    @Override
    public boolean getIsFocusable()
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
