package de.sensordigitalmediagermany.lemonbasic.generic;

import android.annotation.SuppressLint;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Typeface;

@SuppressLint("AppCompatCustomView")
public abstract class GenericText extends TextView implements GenericFocus
{
    private boolean focusable = false;
    private int backgroundColor = Color.TRANSPARENT;

    public GenericText(Context context)
    {
        super(context);

        setFullWidth(true);
        setTypeface(getDefaultTypeface());
        setGravity(Gravity.CENTER_VERTICAL);

        Simple.setLetterSpacing(this, getLetterSpacing());
        Simple.setTextSizeDip(this, getFontSize());
    }

    @Override
    public void setOnClickListener(View.OnClickListener onClickListener)
    {
        super.setOnClickListener(onClickListener);

        setFocusable(true);
    }

    public abstract int getFontSize();

    public abstract String getFontName();

    public float getLetterSpacing()
    {
        return 0f;
    }

    public Typeface getDefaultTypeface()
    {
        return TypeFaces.getTypeface(getContext(), getFontName());
    }

    public void setFullWidth(boolean set)
    {
        ViewGroup.LayoutParams lp = getLayoutParams();
        Simple.setSizeDip(this, set ? Simple.MP : Simple.WC, (lp == null) ? Simple.WC : lp.height);
    }

    public void setFullHeight(boolean set)
    {
        ViewGroup.LayoutParams lp = getLayoutParams();
        Simple.setSizeDip(this, (lp == null) ? Simple.MP : lp.width, set ? Simple.MP : Simple.WC);
    }

    public void setWeight(float weight)
    {
        ViewGroup.LayoutParams lp = getLayoutParams();

        int width = (lp == null) ? Simple.MP : lp.width;
        int height = (lp == null) ? Simple.WC : lp.height;

        Simple.setSizeDip(this, width, height, weight);
    }

    public void setTextSizeDip(int size)
    {
        Simple.setTextSizeDip(this, size);
    }

    public void setPaddingDip(int pad)
    {
        Simple.setPaddingDip(this, pad);
    }

    public void setMarginLeftDip(int margin)
    {
        Simple.setMarginLeftDip(this, margin);
    }

    public void setMarginTopDip(int margin)
    {
        Simple.setMarginTopDip(this, margin);
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
    public boolean getIsFocusable()
    {
        return focusable;
    }

    @Override
    public void setFocusable(boolean focusable)
    {
        this.focusable = focusable;

        super.setFocusable(focusable);

        setOnFocusChangeListener(new OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasfocus)
            {
                if (Simple.isTV())
                {
                    if (hasfocus)
                    {
                        Simple.setRoundedCorners(view, 0, backgroundColor, Defines.COLOR_TV_FOCUS);

                        Generic.saveFocused(view);
                    }
                    else
                    {
                        view.setBackgroundColor(backgroundColor);
                    }
                }
            }
        });
    }
}
