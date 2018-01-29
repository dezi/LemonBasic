package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BuyContentDialog extends DialogView
{
    private static final String LOGTAG = BuyContentDialog.class.getSimpleName();

    protected EditText couponCode;

    public BuyContentDialog(Context context)
    {
        super(context);

        setCloseButton(true, null);

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        TextView titleView = new TextView(getContext());
        titleView.setText(R.string.buy_content_title);
        titleView.setAllCaps(true);
        titleView.setTextColor(Color.WHITE);
        titleView.setSingleLine(Simple.isTablet());
        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleView.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(titleView, Defines.FS_DIALOG_TITLE);
        Simple.setSizeDip(titleView, Simple.WC, Simple.WC);

        Simple.setPaddingDip(titleView,
                Defines.PADDING_LARGE, Defines.PADDING_TINY,
                Defines.PADDING_LARGE, Defines.PADDING_TINY
                );

        dialogItems.addView(titleView);

        LinearLayout roundedFrame = new LinearLayout(getContext());
        roundedFrame.setOrientation(LinearLayout.VERTICAL);
        Simple.setRoundedCorners(roundedFrame, Defines.CORNER_RADIUS_DIALOG, Color.WHITE, true);
        Simple.setSizeDip(roundedFrame, Simple.MP, Simple.WC);
        Simple.setPaddingDip(roundedFrame, Defines.PADDING_LARGE);
        Simple.setMarginTopDip(roundedFrame, Defines.PADDING_NORMAL);
        Simple.setMarginBottomDip(roundedFrame, Defines.PADDING_SMALL);

        dialogItems.addView(roundedFrame);

        String contentTitle = Json.getString(Globals.displayContent, "title");
        String contentInfo = Json.getString(Globals.displayContent, "sub_title");

        final int price = Json.getInt(Globals.displayContent, "price");

        TextView ctView = new TextView(getContext());
        ctView.setText(contentTitle);
        ctView.setAllCaps(true);
        ctView.setTextColor(Defines.COLOR_SENSOR_LTBLUE);
        ctView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_MEDIUM));
        ctView.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(ctView, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(ctView, Defines.FS_BUY_TITLE);
        Simple.setSizeDip(ctView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(ctView, Defines.PADDING_SMALL);

        roundedFrame.addView(ctView);

        TextView ciView = new TextView(getContext());
        ciView.setText(contentInfo);
        ciView.setTextColor(Color.BLACK);
        ciView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.ROONEY_REGULAR));
        ciView.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(ciView, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(ciView, Defines.FS_BUY_HEADER);
        Simple.setSizeDip(ciView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(ciView, Defines.PADDING_TINY);

        roundedFrame.addView(ciView);

        String buyText = (price > 0)
                ? Simple.getTrans(getContext(), R.string.buy_content_price, String.valueOf(price))
                : Simple.getTrans(getContext(), R.string.detail_buy_gratis);

        TextView priceView = new TextView(getContext());
        priceView.setText(buyText);
        priceView.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        priceView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        priceView.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(priceView, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(priceView, Defines.FS_BUY_PRICE);
        Simple.setSizeDip(priceView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(priceView, Defines.PADDING_TINY);

        roundedFrame.addView(priceView);

        LinearLayout buttonArea = new LinearLayout(getContext());
        buttonArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(buttonArea, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(buttonArea, Defines.PADDING_NORMAL);
        Simple.setMarginBottomDip(buttonArea, Defines.PADDING_SMALL);

        dialogItems.addView(buttonArea);

        TextView cancelButton = new TextView(getContext());
        cancelButton.setText(R.string.button_cancel);
        cancelButton.setSingleLine(true);
        cancelButton.setTextColor(Color.WHITE);
        cancelButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        cancelButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(cancelButton, Simple.WC, Simple.WC, 0.5f);
        Simple.setTextSizeDip(cancelButton, Defines.FS_DIALOG_BUTTON);
        Simple.setMarginRightDip(cancelButton, Defines.PADDING_SMALL);
        Simple.setPaddingDip(cancelButton, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(cancelButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_SENSOR_LTBLUE, true);

        cancelButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dismissDialog();
            }
        });

        buttonArea.addView(cancelButton);

        TextView buyButton = new TextView(getContext());
        buyButton.setText(R.string.buy_content_buy);
        buyButton.setSingleLine(true);
        buyButton.setTextColor(Color.WHITE);
        buyButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        buyButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(buyButton, Simple.WC, Simple.WC, 0.5f);
        Simple.setTextSizeDip(buyButton, Defines.FS_DIALOG_BUTTON);
        Simple.setMarginLeftDip(buyButton, Defines.PADDING_SMALL);
        Simple.setPaddingDip(buyButton, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(buyButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_SENSOR_LTBLUE, true);

        buyButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ViewGroup parent = (ViewGroup) BuyContentDialog.this.getParent();

                if (parent != null)
                {
                    parent.removeView(BuyContentDialog.this);

                    if (price > Globals.coins)
                    {
                        parent.addView(new BuyCoinsDialog(parent.getContext(), true));
                    }
                    else
                    {
                        parent.addView(new BuyConfirmDialog(parent.getContext()));
                    }
                }
            }
        });

        buttonArea.addView(buyButton);

        setCustomView(dialogItems);
    }
}
