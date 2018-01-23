package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;

import android.content.Context;
import android.widget.TextView;
import android.graphics.Typeface;
import android.graphics.Color;
import android.view.Gravity;
import android.os.Build;

@SuppressLint("AppCompatCustomView")
public class GenericButton extends TextView
{
    private static Typeface defaultTypeface;

    protected final int fontSize;
    protected final String fontName;
    protected final float letterSpacing;

    public GenericButton(Context context)
    {
        super(context);

        fontSize = Defines.FS_GENERIC_BUTTON;
        fontName = Defines.FONT_GENERIC_BUTTON;
        letterSpacing = Defines.LETTERSPACE_GENERIC_BUTTON;

        setInvers(false);
        setMatchParent(true);
        setTypeface(getDefaultTypeface());
        setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        setAllCaps(Defines.isButtonAllCaps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            setLetterSpacing(letterSpacing);
        }

        Simple.setPaddingDip(this, 0, Defines.PADDING_SMALL, 0, Defines.PADDING_SMALL);
        Simple.setTextSizeDip(this, fontSize);
    }

    @Override
    public void setTextColor(int color)
    {
        if (Defines.COLOR_BUTTON_BACK == Color.BLACK)
        {
            super.setTextColor(Color.BLACK);
            Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, false);
        }
        else
        {
            super.setTextColor(Color.WHITE);
            Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, true);
        }
    }

    public Typeface getDefaultTypeface()
    {
        if (defaultTypeface == null)
        {
            defaultTypeface = TypeFaces.getTypeface(getContext(), fontName);
        }

        return defaultTypeface;
    }

    public void setInvers(boolean set)
    {
        if (set)
        {
            if (Defines.COLOR_BUTTON_BACK == Color.BLACK)
            {
                super.setTextColor(Color.WHITE);
                Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, true);
            }
            else
            {
                super.setTextColor(Color.BLACK);
                Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, true);
            }
        }
        else
        {
            if (Defines.COLOR_BUTTON_BACK == Color.BLACK)
            {
                super.setTextColor(Color.BLACK);
                Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, false);
            }
            else
            {
                super.setTextColor(Color.WHITE);
                Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, false);
            }
        }
    }

    public void setMatchParent(boolean set)
    {
        Simple.setSizeDip(this, set ? Simple.MP : Simple.WC, Simple.WC);
    }

    public void setWeight(float weight)
    {
        Simple.setSizeDip(this, Simple.MP, Simple.WC, weight);
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
