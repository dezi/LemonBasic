package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TableLikeLayout extends LinearLayout
{
    protected TextView leftText;
    protected TextView rightText;

    public TableLikeLayout(Context context)
    {
        this(context, null, null, false);
    }

    public TableLikeLayout(Context context, Typeface leftFace, Typeface rightFace)
    {
        this(context, leftFace, rightFace, false);
    }

    public TableLikeLayout(Context context, Typeface leftFace, Typeface rightFace, boolean vertical)
    {
        super(context);

        setOrientation(vertical ? VERTICAL : HORIZONTAL);

        if (vertical || ! Defines.isCompactSettings)
        {
            leftText = new TextView(getContext());
            leftText.setMinWidth(Simple.dipToPx((int) Math.round(Defines.FS_DETAIL_SPECS * 3.7)));
            leftText.setTextColor(Color.BLACK);
            leftText.setTypeface(leftFace);
            leftText.setAllCaps(Defines.isInfosAllCaps);
            Simple.setTextSizeDip(leftText, Defines.FS_DETAIL_SPECS);
            Simple.setSizeDip(leftText, Simple.WC, Simple.WC);

            addView(leftText);
        }

        if ((! Defines.isCompactSettings) && ! vertical)
        {
            TextView colonText = new TextView(getContext());
            colonText.setText(":");
            colonText.setMinEms(1);
            colonText.setTextColor(Color.BLACK);
            colonText.setGravity(Gravity.CENTER_HORIZONTAL);
            colonText.setTypeface(leftFace);
            Simple.setTextSizeDip(colonText, Defines.FS_DETAIL_SPECS);
            Simple.setSizeDip(colonText, Simple.WC, Simple.WC);

            addView(colonText);
        }

        rightText = new TextView(getContext());
        rightText.setTextColor(Color.BLACK);
        rightText.setTypeface(rightFace);
        rightText.setAllCaps(Defines.isInfosAllCaps);
        Simple.setTextSizeDip(rightText, Defines.FS_DETAIL_SPECS);
        Simple.setSizeDip(rightText, Simple.WC, Simple.WC, 1f);

        addView(rightText);
    }

    public void setLeftText(int textresid)
    {
        if (leftText != null) leftText.setText(textresid);
    }

    public void setRightText(int textresid)
    {
        rightText.setText(textresid);
    }

    public void setRightText(String text)
    {
        rightText.setText(text);
    }
}
