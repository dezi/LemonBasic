package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;

import android.widget.EditText;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

@SuppressLint("AppCompatCustomView")
public class GenericEdit extends EditText
{
    public GenericEdit(Context context)
    {
        super(context);

        setMatchParent(true);
        setTypeface(getDefaultTypeface());

        Simple.setPaddingDip(this,Defines.PADDING_SMALL);
        Simple.setTextSizeDip(this, getFontSize());
        Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);
    }

    public int getFontSize()
    {
        return Defines.FS_GENERIC_EDIT;
    }

    public String getFontName()
    {
        return Defines.FONT_GENERIC_EDIT;
    }

    public Typeface getDefaultTypeface()
    {
        return TypeFaces.getTypeface(getContext(), getFontName());
    }

    public void setHintSpecial(int resid)
    {
        String hint = Simple.getTrans(getContext(), resid);
        if (Defines.isHintsAllCaps) hint = hint.toUpperCase();

        setHint(hint);
    }

    public void setMatchParent(boolean set)
    {
        Simple.setSizeDip(this, set ? Simple.MP : Simple.WC, Simple.WC);
    }

    @Override
    public void setInputType(int type)
    {
        super.setInputType(type);

        //
        // For some reason setInputType fucks up
        // the setTypeface setting. Damm it.
        //

        setTypeface(getDefaultTypeface());
    }
}
