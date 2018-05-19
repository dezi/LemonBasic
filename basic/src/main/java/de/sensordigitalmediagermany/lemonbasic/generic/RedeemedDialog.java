package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RedeemedDialog extends DialogView
{
    private static final String LOGTAG = RedeemedDialog.class.getSimpleName();

    public RedeemedDialog(Context context)
    {
        this(context, false);
    }

    public RedeemedDialog(Context context, boolean purchase)
    {
        this(context, purchase, null);
    }

    public RedeemedDialog(Context context, boolean purchase, final Runnable onFinished)
    {
        super(context);

        setCloseButton(true, null);

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        TextView titleView = new TextView(getContext());
        titleView.setText(purchase ? R.string.redeemed_purchase : R.string.redeemed_title);
        titleView.setAllCaps(true);
        titleView.setTextColor(Color.WHITE);
        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleView.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(titleView, Defines.FS_DIALOG_TITLE);
        Simple.setSizeDip(titleView, Simple.MP, Simple.WC);

        Simple.setPaddingDip(titleView,
                Defines.PADDING_LARGE, Defines.PADDING_TINY,
                Defines.PADDING_LARGE, Defines.PADDING_TINY
                );

        dialogItems.addView(titleView);

        TextView redeemedInfo = new TextView(getContext());
        redeemedInfo.setText(R.string.redeemed_info);
        redeemedInfo.setTextColor(Color.WHITE);
        redeemedInfo.setGravity(Gravity.CENTER_HORIZONTAL);
        redeemedInfo.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setSizeDip(redeemedInfo, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(redeemedInfo, Defines.FS_DIALOG_INFO);

        dialogItems.addView(redeemedInfo);

        LinearLayout coinsFrame = new LinearLayout(getContext());
        coinsFrame.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(coinsFrame, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(coinsFrame, Defines.PADDING_NORMAL);
        Simple.setMarginBottomDip(coinsFrame, Defines.PADDING_NORMAL);
        Simple.setRoundedCorners(coinsFrame, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_GREEN, true);

        dialogItems.addView(coinsFrame);

        TextView numberCoins = new TextView(getContext());
        numberCoins.setText(Simple.formatDecimal(Globals.coinsAdded));
        numberCoins.setTextColor(Color.WHITE);
        numberCoins.setGravity(Gravity.CENTER_HORIZONTAL);
        numberCoins.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(numberCoins, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(numberCoins, Defines.FS_COINS_COINS);

        coinsFrame.addView(numberCoins);

        TextView buttonCtext = new TextView(getContext());
        buttonCtext.setText(R.string.buy_coins_coins);
        buttonCtext.setAllCaps(true);
        buttonCtext.setTextColor(Color.WHITE);
        buttonCtext.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        buttonCtext.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setTextSizeDip(buttonCtext, Defines.FS_COINS_BUTTONS);
        Simple.setSizeDip(buttonCtext, Simple.MP, Simple.WC);
        Simple.setMarginBottomDip(buttonCtext, Defines.PADDING_SMALL);

        coinsFrame.addView(buttonCtext);

        TextView closeButton = new TextView(getContext());
        closeButton.setText(R.string.button_close);
        closeButton.setTextColor(Color.WHITE);
        closeButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        closeButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(closeButton, Simple.MP, Simple.MP);
        Simple.setTextSizeDip(closeButton, Defines.FS_DIALOG_BUTTON);
        Simple.setPaddingDip(closeButton, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(closeButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_SENSOR_LTBLUE, true);

        closeButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dismissDialog();

                if (onFinished != null)
                {
                    ApplicationBase.handler.post(onFinished);
                }
            }
        });

        dialogItems.addView(closeButton);

        setCustomView(dialogItems);
    }
}
