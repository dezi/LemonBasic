package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class GenericScrollVert extends ScrollView implements GenericFocus
{
    private boolean focusable = false;
    private int backgroundColor = Color.TRANSPARENT;

    public GenericScrollVert(Context context)
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

    public void setSizeDependendFocusable(boolean focusable)
    {
        if (focusable)
        {
            ApplicationBase.handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    boolean wantfocus = false;

                    if (getChildCount() > 0)
                    {
                        if (getHeight() < getChildAt(0).getHeight())
                        {
                            wantfocus = true;
                        }
                    }

                    setFocusable(wantfocus);
                }
            });
        }
        else
        {
            setFocusable(false);
        }
    }
}
