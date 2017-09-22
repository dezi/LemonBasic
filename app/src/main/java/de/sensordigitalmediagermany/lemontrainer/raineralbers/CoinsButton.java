package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.NumberFormat;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.Gravity;
import android.view.View;

import java.util.Locale;

public class CoinsButton extends LinearLayout
{
    protected PacketSelectedListener onSelectedListener;

    public CoinsButton(Context context, final AppStorePacket packet, PacketSelectedListener listener)
    {
        super(context);

        onSelectedListener = listener;

        setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(this, Simple.WC, Simple.WC);
        Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_GREEN, true);
        Simple.setMarginDip(this, Defines.PADDING_TINY);
        Simple.setPaddingDip(this, Defines.PADDING_NORMAL);

        setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Simple.setRoundedCorners(CoinsButton.this, Defines.CORNER_RADIUS_BIGBUT, Color.WHITE, true);
                }

                if ((event.getAction() == MotionEvent.ACTION_UP) || (event.getAction() == MotionEvent.ACTION_CANCEL))
                {
                    Simple.setRoundedCorners(CoinsButton.this, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_GREEN, true);
                }

                return false;
            }
        });

        setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (onSelectedListener != null)
                {
                    onSelectedListener.OnThemeSelected(packet);
                }
            }
        });

        String coins = "" + packet.coins;

        TextView buttonCoins = new TextView(getContext());
        buttonCoins.setText(coins);
        buttonCoins.setSingleLine();
        buttonCoins.setAllCaps(true);
        buttonCoins.setTextColor(Color.WHITE);
        buttonCoins.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        buttonCoins.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setTextSizeDip(buttonCoins, Defines.FS_COINS_COINS);
        Simple.setSizeDip(buttonCoins, Defines.COINS_BUTTON_WIDTH, Simple.WC);
        Simple.setMarginTopDip(buttonCoins, Defines.PADDING_SMALL);

        addView(buttonCoins);

        TextView buttonCtext = new TextView(getContext());
        buttonCtext.setText(R.string.buy_coins_coins);
        buttonCtext.setAllCaps(true);
        buttonCtext.setTextColor(Color.WHITE);
        buttonCtext.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        buttonCtext.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setTextSizeDip(buttonCtext, Defines.FS_COINS_BUTTONS);
        Simple.setSizeDip(buttonCtext, Simple.MP, Simple.WC);
        Simple.setMarginBottomDip(buttonCtext, Defines.PADDING_SMALL);

        addView(buttonCtext);

        RelativeLayout separator = new RelativeLayout(getContext());
        separator.setBackgroundColor(Color.WHITE);
        Simple.setSizeDip(separator, Simple.MP, 1);
        Simple.setMarginTopDip(separator, Defines.PADDING_SMALL);
        Simple.setMarginBottomDip(separator, Defines.PADDING_SMALL);

        addView(separator);

        TextView buttonCents = new TextView(getContext());
        buttonCents.setText(Simple.getTrans(getContext(), R.string.buy_coins_price, Simple.formatMoney(packet.cents)));
        buttonCents.setAllCaps(true);
        buttonCents.setTextColor(Color.WHITE);
        buttonCents.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAMNARROW_LIGHT));
        buttonCents.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        Simple.setTextSizeDip(buttonCents, Defines.FS_COINS_PRICE);
        Simple.setSizeDip(buttonCents, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(buttonCents, Defines.PADDING_SMALL);
        Simple.setMarginBottomDip(buttonCents, Defines.PADDING_NORMAL);

        addView(buttonCents);
    }

    public interface PacketSelectedListener
    {
        void OnThemeSelected(AppStorePacket packet);
    }
}
