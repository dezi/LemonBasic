package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.Gravity;
import android.view.View;

import java.util.ArrayList;

public class CoinsButton extends LinearLayout
{
    protected boolean isSelected;
    protected AppStorePacket packet;

    protected TextView buttonCoins;
    protected TextView buttonCents;
    protected TextView buttonCtext;

    protected RelativeLayout separator;

    protected ArrayList<CoinsButton> allButtons;

    public CoinsButton(Context context)
    {
        this(context, null, null);
    }

    public CoinsButton(Context context, final AppStorePacket packet, final ArrayList<CoinsButton> allButtons)
    {
        super(context);

        this.packet = packet;
        this.allButtons = allButtons;

        setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(this, Simple.WC, Simple.WC);
        Simple.setRoundedCorners(this, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_GREEN, true);
        Simple.setMarginDip(this, Defines.PADDING_TINY);
        Simple.setPaddingDip(this, Defines.PADDING_NORMAL);

        String coins = "" + packet.coins;

        buttonCoins = new TextView(getContext());
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

        buttonCtext = new TextView(getContext());
        buttonCtext.setText(R.string.buy_coins_coins);
        buttonCtext.setAllCaps(true);
        buttonCtext.setTextColor(Color.WHITE);
        buttonCtext.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        buttonCtext.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setTextSizeDip(buttonCtext, Defines.FS_COINS_BUTTONS);
        Simple.setSizeDip(buttonCtext, Simple.MP, Simple.WC);
        Simple.setMarginBottomDip(buttonCtext, Defines.PADDING_SMALL);

        addView(buttonCtext);

        separator = new RelativeLayout(getContext());
        separator.setBackgroundColor(Color.WHITE);
        Simple.setSizeDip(separator, Simple.MP, 1);
        Simple.setMarginTopDip(separator, Defines.PADDING_SMALL);
        Simple.setMarginBottomDip(separator, Defines.PADDING_SMALL);

        addView(separator);

        buttonCents = new TextView(getContext());
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

        setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    if (allButtons != null)
                    {
                        for (CoinsButton other : allButtons)
                        {
                            if (other != CoinsButton.this)
                            {
                                other.buttonCoins.setTextColor(Color.WHITE);
                                other.buttonCtext.setTextColor(Color.WHITE);
                                other.buttonCents.setTextColor(Color.WHITE);

                                other.separator.setBackgroundColor(Color.WHITE);

                                Simple.setRoundedCorners(other, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_GREEN, true);

                                other.isSelected = false;
                            }
                        }
                    }

                    buttonCoins.setTextColor(Color.BLACK);
                    buttonCtext.setTextColor(Color.BLACK);
                    buttonCents.setTextColor(Color.BLACK);

                    separator.setBackgroundColor(Color.BLACK);

                    Simple.setRoundedCorners(CoinsButton.this, Defines.CORNER_RADIUS_BIGBUT, Color.WHITE, true);

                    isSelected = true;
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL)
                {
                    buttonCoins.setTextColor(Color.WHITE);
                    buttonCtext.setTextColor(Color.WHITE);
                    buttonCents.setTextColor(Color.WHITE);

                    separator.setBackgroundColor(Color.WHITE);

                    Simple.setRoundedCorners(CoinsButton.this, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_GREEN, true);

                    isSelected = true;
                }

                return false;
            }
        });

        setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //
                // Dummy to prevent exit of dialog...
                //
            }
        });
    }

    public boolean isSelected()
    {
        return isSelected;
    }

    public AppStorePacket getPacket()
    {
        return packet;
    }
}
