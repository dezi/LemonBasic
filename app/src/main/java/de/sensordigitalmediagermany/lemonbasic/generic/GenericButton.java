package de.sensordigitalmediagermany.lemonbasic.generic;

import android.annotation.SuppressLint;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Typeface;
import android.graphics.Color;
import android.view.Gravity;

@SuppressLint("AppCompatCustomView")
public class GenericButton extends TextView implements GenericFocus
{
    private boolean focusable = false;
    private int backgroundColor = Color.TRANSPARENT;

    private boolean isDefaultButton;

    public GenericButton(Context context)
    {
        super(context);

        setFocusable(true);
        setDefaultButton(false);
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

    public Typeface getDefaultTypeface()
    {
        return TypeFaces.getTypeface(getContext(), getFontName());
    }

    public void setDefaultButton(boolean set)
    {
        isDefaultButton = set;

        if (isDefaultButton)
        {
            backgroundColor = Defines.COLOR_BUTTON_BACK;
            super.setTextColor((Defines.COLOR_BUTTON_BACK == Color.BLACK) ? Color.WHITE : Color.BLACK);
        }
        else
        {
            backgroundColor = Color.TRANSPARENT;
            super.setTextColor((Defines.COLOR_BUTTON_BACK == Color.BLACK) ? Color.BLACK : Color.WHITE);
        }

        Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BUTTON, backgroundColor, Defines.COLOR_BUTTON_BACK);
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

        Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BUTTON, backgroundColor, Defines.COLOR_BUTTON_BACK);
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

        if (focusable && Simple.isTV())
        {
            setOnFocusChangeListener(new View.OnFocusChangeListener()
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

                        Simple.setRoundedCorners(view, Defines.CORNER_RADIUS_BUTTON, backgroundColor, Defines.COLOR_TV_FOCUS);

                        //
                        // Save focused view for possible restore.
                        //

                        Generic.saveFocused(view);
                    }
                    else
                    {
                        //
                        // Make neutral again.
                        //

                        Simple.setRoundedCorners(view, Defines.CORNER_RADIUS_BUTTON, backgroundColor, Defines.COLOR_BUTTON_BACK);
                    }
                }
            });
        }
        else
        {
            setOnFocusChangeListener(null);
        }
    }
}
