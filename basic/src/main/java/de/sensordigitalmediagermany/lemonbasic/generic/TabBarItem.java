package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabBarItem extends RelativeLayout
{
    private LinearLayout itemBox;
    private ImageView iconView;
    private TextView textView;

    public TabBarItem(Context context)
    {
        super(context);
    }

    public TabBarItem(Context context, int dipheight)
    {
        super(context);

        setGravity(Gravity.CENTER_VERTICAL + Gravity.CENTER_HORIZONTAL);

        Simple.setSizeDip(this, Simple.MP, dipheight, 1.0f);

        itemBox = new GenericLinear(context);
        itemBox.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(itemBox, Simple.WC, Simple.MP);

        addView(itemBox);

        iconView = new ImageView(context);
        iconView.setScaleType(ImageView.ScaleType.FIT_XY);
        Simple.setSizeDip(iconView, dipheight, dipheight);
        Simple.setPaddingDip(iconView, Defines.PADDING_SMALL);

        itemBox.addView(iconView);

        textView = new TextView(context);
        textView.setVisibility(Simple.isTablet() ? VISIBLE : GONE);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setSingleLine(true);
        textView.setAllCaps(true);
        textView.setTextColor(Defines.COLOR_BUTTON_TEXT);
        textView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_TABBAR_ENTRY));
        Simple.setSizeDip(textView, Simple.WC, Simple.MP);
        Simple.setTextSizeDip(textView, Defines.FS_TABBAR_ENTRY);
        Simple.setPaddingDip(textView, 0, 0, Defines.PADDING_SMALL, 0);

        itemBox.addView(textView);
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener)
    {
        itemBox.setOnClickListener(onClickListener);
    }

    public void setContent(int iconresid, int textresid)
    {
        textView.setText(textresid);

        iconView.setImageResource(iconresid);
    }

    public void tintContent(int iconColor, int textColor)
    {
        textView.setTextColor(textColor);
        iconView.setColorFilter(iconColor);
    }
}
