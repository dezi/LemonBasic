package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;

public class BuyCoinsDialog extends DialogView
{
    private static final String LOGTAG = BuyCoinsDialog.class.getSimpleName();

    public BuyCoinsDialog(Context context)
    {
        super(context);

        setCloseButton(true, null);

        AppStorePacket packets[] = AppStorePacket.getAppStorePackets();

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        TextView titleView = new TextView(getContext());
        titleView.setText(R.string.buy_coins_title);
        titleView.setAllCaps(true);
        titleView.setTextColor(Color.WHITE);
        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleView.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(titleView, Defines.FS_DIALOG_TITLE);
        Simple.setSizeDip(titleView, Simple.MP, Simple.WC);
        Simple.setMarginBottomDip(titleView, Defines.PADDING_NORMAL);

        dialogItems.addView(titleView);

        LinearLayout coinsFrame = new LinearLayout(getContext());
        coinsFrame.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(coinsFrame, Simple.WC, Simple.WC);
        Simple.setMarginTopDip(coinsFrame, Defines.PADDING_NORMAL);
        Simple.setMarginBottomDip(coinsFrame, Defines.PADDING_NORMAL);

        dialogItems.addView(coinsFrame);

        coinsFrame.addView(new CoinsButton(getContext(), packets[ 0 ], null));
        coinsFrame.addView(new CoinsButton(getContext(), packets[ 1 ], null));
        coinsFrame.addView(new CoinsButton(getContext(), packets[ 2 ], null));

        RelativeLayout requestCenter = new RelativeLayout(getContext());
        requestCenter.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(requestCenter, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(requestCenter, Defines.PADDING_NORMAL);
        Simple.setMarginBottomDip(requestCenter, Defines.PADDING_TINY);

        dialogItems.addView(requestCenter);

        TextView requestButton = new TextView(getContext());
        requestButton.setText(R.string.buy_coins_buy);
        requestButton.setTextColor(Color.WHITE);
        requestButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(requestButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(requestButton, Defines.FS_DIALOG_BUTTON);
        Simple.setPaddingDip(requestButton, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(requestButton, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_LTBLUE, true);

        requestButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        requestCenter.addView(requestButton);

        setCustomView(dialogItems);
    }
}
