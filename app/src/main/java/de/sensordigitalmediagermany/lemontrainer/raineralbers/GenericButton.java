package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Typeface;
import android.graphics.Color;
import android.view.Gravity;

@SuppressLint("AppCompatCustomView")
public class GenericButton extends TextView
{
    public GenericButton(Context context)
    {
        super(context);

        setInvers(false);
        setSingleLine(true);
        setFullWidth(true);
        setTypeface(getDefaultTypeface());
        setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        setAllCaps(Defines.isButtonAllCaps);

        Simple.setTextSizeDip(this, getFontSize());
        Simple.setLetterSpacing(this, getLetterSpacing());
        Simple.setPaddingDip(this, Defines.PADDING_SMALL);
    }

    public int getFontSize()
    {
        return Defines.FS_GENERIC_BUTTON;
    }

    public String getFontName()
    {
        return Defines.FONT_GENERIC_BUTTON;
    }

    public float getLetterSpacing()
    {
        return Defines.LETTERSPACE_GENERIC_BUTTON;
    }

    @Override
    public void setSingleLine(boolean singleLine)
    {
        super.setSingleLine(singleLine);

        //
        // For some reason setSingleLine fucks up
        // the setAllCaps setting. Damm it.
        //

        setAllCaps(Defines.isButtonAllCaps);
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
        return TypeFaces.getTypeface(getContext(), getFontName());
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

    public void setFullWidth(boolean set)
    {
        ViewGroup.LayoutParams lp = getLayoutParams();
        Simple.setSizeDip(this, set ? Simple.MP : Simple.WC, (lp == null) ? Simple.WC : lp.height);
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
