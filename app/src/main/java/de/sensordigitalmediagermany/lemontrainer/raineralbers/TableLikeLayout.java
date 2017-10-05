package de.sensordigitalmediagermany.lemontrainer.raineralbers;

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
        this(context,
                Typeface.createFromAsset(context.getAssets(), Defines.GOTHAMNARROW_LIGHT),
                Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_BOLD)
        );
    }

    public TableLikeLayout(Context context, boolean plain)
    {
        this(context,
                Typeface.createFromAsset(context.getAssets(), Defines.GOTHAMNARROW_LIGHT), plain
                        ? Typeface.createFromAsset(context.getAssets(), Defines.GOTHAMNARROW_LIGHT)
                        : Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_BOLD)
        );
    }

    public TableLikeLayout(Context context, Typeface leftFace, Typeface rightFace)
    {
        super(context);

        setOrientation(HORIZONTAL);

        leftText = new TextView(getContext());
        leftText.setMinWidth(Simple.dipToPx((int) Math.round(Defines.FS_DETAIL_SPECS * 3.5)));
        leftText.setTextColor(Color.BLACK);
        leftText.setTypeface(leftFace);
        Simple.setTextSizeDip(leftText, Defines.FS_DETAIL_SPECS);
        Simple.setSizeDip(leftText, Simple.WC, Simple.WC);

        addView(leftText);

        TextView colonText = new TextView(getContext());
        colonText.setText(":");
        colonText.setMinEms(1);
        colonText.setTextColor(Color.BLACK);
        colonText.setGravity(Gravity.CENTER_HORIZONTAL);
        colonText.setTypeface(leftFace);
        Simple.setTextSizeDip(colonText, Defines.FS_DETAIL_SPECS);
        Simple.setSizeDip(colonText, Simple.WC, Simple.WC);

        addView(colonText);

        rightText = new TextView(getContext());
        rightText.setTextColor(Color.BLACK);
        rightText.setTypeface(rightFace);
        Simple.setTextSizeDip(rightText, Defines.FS_DETAIL_SPECS);
        Simple.setSizeDip(rightText, Simple.WC, Simple.WC, 1f);

        addView(rightText);
    }

    public void setLeftText(int textresid)
    {
        leftText.setText(textresid);
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
