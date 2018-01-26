package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Typeface;

@SuppressLint("AppCompatCustomView")
public abstract class GenericText extends TextView
{
    public GenericText(Context context)
    {
        super(context);

        setFullWidth(true);
        setTypeface(getDefaultTypeface());
        setGravity(Gravity.CENTER_VERTICAL);

        Simple.setLetterSpacing(this, getLetterSpacing());
        Simple.setTextSizeDip(this, getFontSize());
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
}
