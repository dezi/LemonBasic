package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.view.Gravity;
import android.view.View;

public class CategoryMenu extends RelativeLayout
{
    private static final String LOGTAG = CategoryMenu.class.getSimpleName();

    protected LinearLayout popupMargin;
    protected LinearLayout popupShape;
    protected LinearLayout popupFrame;
    protected TextView titleView;

    @SuppressLint("RtlHardcoded")
    public CategoryMenu(Context context)
    {
        super(context);

        setGravity(Gravity.RIGHT);
        setBackgroundColor(Color.TRANSPARENT);
        Simple.setSizeDip(this, Simple.MP, Simple.MP);

        setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                    ViewGroup parent = (ViewGroup) CategoryMenu.this.getParent();

                    if (parent != null)
                    {
                        parent.removeView(CategoryMenu.this);
                    }
            }
        });

        popupMargin = new LinearLayout(getContext());

        popupMargin.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(popupMargin, Simple.WC, Simple.WC);

        addView(popupMargin);

        popupShape = new LinearLayout(getContext());
        popupShape.setOrientation(LinearLayout.VERTICAL);
        popupShape.setBackgroundResource(R.drawable.lem_t_iany_ralbers_menuerechts);
        Simple.setSizeDip(popupShape, Simple.WC, Simple.WC);
        Simple.setMarginRightDip(popupShape, Defines.PADDING_NORMAL);

        popupMargin.addView(popupShape);

        popupFrame = new LinearLayout(getContext());
        popupFrame.setOrientation(LinearLayout.VERTICAL);
        popupFrame.setMinimumWidth(Simple.dipToPx(Defines.MINWIDTH_CATEGORY_POPUP));
        Simple.setSizeDip(popupFrame, Simple.WC, Simple.WC);
        Simple.setPaddingDip(popupFrame, Defines.PADDING_LARGE);

        popupShape.addView(popupFrame);

        titleView = new TextView(getContext());
        titleView.setText(R.string.category_popup_title);
        titleView.setAllCaps(true);
        titleView.setTextColor(Color.BLACK);
        titleView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(titleView, Defines.FS_POPUP_MENU);

        popupFrame.addView(titleView);
    }

    public void setTopMargin(int topmargin)
    {
        Simple.setPaddingDip(popupMargin, 0, topmargin, 0, 0);
    }

    public void addOption(String option)
    {
        RelativeLayout separator = new RelativeLayout(getContext());
        separator.setBackgroundColor(Color.WHITE);
        Simple.setSizeDip(separator, Simple.MP, 1);
        Simple.setMarginTopDip(separator, Defines.PADDING_SMALL);
        Simple.setMarginBottomDip(separator, Defines.PADDING_SMALL);

        popupFrame.addView(separator);

        TextView optionView = new TextView(getContext());
        optionView.setText(option);
        optionView.setAllCaps(true);
        optionView.setTextColor(Color.WHITE);
        optionView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(optionView, Defines.FS_POPUP_MENU);

        popupFrame.addView(optionView);
    }
}
