package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;

public class BuyCoinsDialog extends DialogView
{
    private static final String LOGTAG = BuyCoinsDialog.class.getSimpleName();

    protected final ArrayList<CoinsButton> coinButtons = new ArrayList<>();
    protected boolean wasMore;

    public BuyCoinsDialog(Context context)
    {
        this(context, false);
    }

    public BuyCoinsDialog(Context context, boolean more)
    {
        super(context);

        wasMore = more;

        setCloseButton(true, null);

        AppStorePacket packets[] = AppStorePacket.getAppStorePackets();

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        TextView titleView = new TextView(getContext());
        titleView.setText(more ? R.string.buy_coins_more : R.string.buy_coins_title);
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

        for (AppStorePacket packet : packets)
        {
            CoinsButton button = new CoinsButton(getContext(), packet, coinButtons);

            coinsFrame.addView(button);
            coinButtons.add(button);
        }

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
                for (CoinsButton button : coinButtons)
                {
                    if (button.isSelected())
                    {
                        buyPacket(button.getPacket());

                        return;
                    }
                }

                ApplicationBase.handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        DialogView.errorAlert((ViewGroup) BuyCoinsDialog.this.getParent(),
                                R.string.alert_buy_coins_title,
                                R.string.alert_buy_coins_noselect);
                    }
                });
            }
        });

        requestCenter.addView(requestButton);

        setCustomView(dialogItems);
    }

    protected void buyPacket(final AppStorePacket paket)
    {
        final ViewGroup parent = (ViewGroup) BuyCoinsDialog.this.getParent();

        JSONObject params = new JSONObject();

        Json.put(params, "UDID", Globals.UDID);
        Json.put(params, "accountId", Globals.accountId);
        Json.put(params, "coins", paket.coins);
        Json.put(params, "transactionId", Simple.getUUID());
        Json.put(params, "transactionDate", Simple.getNowDateSQL());
        Json.put(params, "originalTransactionId", "-");

        Json.put(params, Defines.SYSTEM_USER_PARAM, Defines.SYSTEM_USER_NAME);

        RestApi.getPostThreaded("saveAppStoreTransaction", params, new RestApi.RestApiResultListener()
        {
            @Override
            public void OnRestApiResult(String what, JSONObject params, JSONObject result)
            {
                if ((result != null) && Json.equals(result, "Status", "OK"))
                {
                    int errorcode = Json.getInt(result, "errorcode");

                    if (errorcode == 0)
                    {
                        Globals.coins += paket.coins;
                        Globals.coinsAdded = paket.coins;

                        if (parent != null)
                        {
                            parent.removeView(BuyCoinsDialog.this);

                            parent.addView(new RedeemedDialog(parent.getContext(), true, new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    boughtPacket(parent);
                                }
                            }));
                        }

                        return;
                    }
                }

                Log.d(LOGTAG, "saveAppStoreTransaction: " + result);

                DialogView.errorAlert((ViewGroup) BuyCoinsDialog.this.getParent(),
                        R.string.alert_buy_coins_title,
                        R.string.alert_buy_coins_fail);
            }
        });
    }

    protected void boughtPacket(ViewGroup parent)
    {
        if (wasMore)
        {
            parent.addView(new BuyConfirmDialog(parent.getContext()));
        }
    }
}
