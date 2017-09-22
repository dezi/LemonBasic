package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

public class ProfileMenu extends RelativeLayout
{
    private static final String LOGTAG = ProfileMenu.class.getSimpleName();

    protected LinearLayout popupMargin;
    protected LinearLayout popupShape;
    protected LinearLayout popupFrame;

    @SuppressLint("RtlHardcoded")
    public ProfileMenu(Context context)
    {
        super(context);

        setGravity(Gravity.LEFT);
        setBackgroundColor(Color.TRANSPARENT);
        Simple.setSizeDip(this, Simple.MP, Simple.MP);

        setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                    ViewGroup parent = (ViewGroup) ProfileMenu.this.getParent();

                    if (parent != null)
                    {
                        parent.removeView(ProfileMenu.this);
                    }
            }
        });

        popupMargin = new LinearLayout(getContext());

        popupMargin.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(popupMargin, Simple.WC, Simple.WC);

        addView(popupMargin);

        popupShape = new LinearLayout(getContext());
        popupShape.setOrientation(LinearLayout.VERTICAL);
        popupShape.setBackgroundResource(R.drawable.lem_t_iany_ralbers_menuelinks);
        Simple.setSizeDip(popupShape, Simple.WC, Simple.WC);
        Simple.setMarginLeftDip(popupShape, Defines.MARGIN_POPUP);

        popupMargin.addView(popupShape);

        popupFrame = new LinearLayout(getContext());
        popupFrame.setOrientation(LinearLayout.VERTICAL);
        popupFrame.setMinimumWidth(Simple.dipToPx(Defines.MINWIDTH_PROFILE_POPUP));
        Simple.setSizeDip(popupFrame, Simple.WC, Simple.WC);
        Simple.setPaddingDip(popupFrame, Defines.PADDING_LARGE);

        popupShape.addView(popupFrame);

        LinearLayout titleFrame = new LinearLayout(getContext());
        titleFrame.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setPaddingDip(titleFrame, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(titleFrame, Defines.CORNER_RADIUS_BUTTON, Color.GRAY, true);

        popupFrame.addView(titleFrame);

        TextView creditsView = new TextView(getContext());
        creditsView.setText(R.string.profile_popup_credits);
        creditsView.setSingleLine();
        creditsView.setTextColor(Color.WHITE);
        creditsView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(creditsView, Defines.FS_POPUP_MENU);

        titleFrame.addView(creditsView);

        TextView countView = new TextView(getContext());
        countView.setText("12.345");
        countView.setSingleLine();
        countView.setGravity(Gravity.RIGHT);
        countView.setTextColor(Color.WHITE);
        countView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(countView, Defines.FS_POPUP_MENU);
        Simple.setSizeDip(countView, Simple.MP, Simple.WC, 1.0f);
        Simple.setMarginRightDip(countView, Defines.PADDING_TINY);
        Simple.setMarginLeftDip(countView, Defines.PADDING_TINY);

        titleFrame.addView(countView);

        TextView coinsView = new TextView(getContext());
        coinsView.setText(R.string.profile_popup_coins);
        coinsView.setSingleLine();
        coinsView.setGravity(Gravity.RIGHT);
        coinsView.setTextColor(Color.WHITE);
        coinsView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(coinsView, Defines.FS_POPUP_MENU);

        titleFrame.addView(coinsView);

        addOption(R.string.profile_popup_buy);
        addOption(R.string.profile_popup_voucher);
        addOption(R.string.profile_popup_settings);
        addOption(R.string.profile_popup_faq);
        addOption(R.string.profile_popup_impressum);
    }

    public void setTopMargin(int topmargin)
    {
        Simple.setPaddingDip(popupMargin, 0, topmargin, 0, 0);
    }

    public void addOption(final int optionresid)
    {
        RelativeLayout separator = new RelativeLayout(getContext());
        Simple.setSizeDip(separator, Simple.MP, Defines.PADDING_SMALL);

        popupFrame.addView(separator);

        TextView optionView = new TextView(getContext());
        optionView.setText(optionresid);
        optionView.setGravity(Gravity.CENTER_HORIZONTAL);
        optionView.setTextColor(Color.WHITE);
        optionView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(optionView, Defines.FS_POPUP_MENU);
        Simple.setPaddingDip(optionView, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(optionView, Defines.CORNER_RADIUS_BUTTON, Color.BLACK, true);

        optionView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onButtonClicked(optionresid);
            }
        });

        popupFrame.addView(optionView);
    }

    private void onButtonClicked(int optionresid)
    {
        if (optionresid == R.string.profile_popup_buy)
        {
            ViewGroup parent = (ViewGroup) ProfileMenu.this.getParent();

            if (parent != null)
            {
                parent.removeView(ProfileMenu.this);

                parent.addView(new BuyCoinsDialog(parent.getContext()));
            }
        }
    }
}
