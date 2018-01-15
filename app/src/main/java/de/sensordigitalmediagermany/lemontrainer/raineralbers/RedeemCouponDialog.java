package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

public class RedeemCouponDialog extends DialogView
{
    private static final String LOGTAG = RedeemCouponDialog.class.getSimpleName();

    protected EditText couponCode;

    public RedeemCouponDialog(Context context)
    {
        super(context);

        setCloseButton(true, null);

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        TextView titleView = new TextView(getContext());
        titleView.setText(R.string.redeem_coupon_title);
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

        couponCode = new EditText(getContext());
        couponCode.setInputType(InputType.TYPE_CLASS_TEXT);
        couponCode.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(couponCode, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(couponCode, Defines.PADDING_LARGE);
        Simple.setPaddingDip(couponCode,Defines.PADDING_SMALL);
        Simple.setSizeDip(couponCode, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(couponCode, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(couponCode);

        LinearLayout buttonArea = new LinearLayout(getContext());
        buttonArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(buttonArea, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(buttonArea, Defines.PADDING_NORMAL);
        Simple.setMarginBottomDip(buttonArea, Defines.PADDING_SMALL);

        dialogItems.addView(buttonArea);

        TextView cancelButton = new TextView(getContext());
        cancelButton.setText(R.string.button_cancel);
        cancelButton.setTextColor(Color.WHITE);
        cancelButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        cancelButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(cancelButton, Simple.MP, Simple.MP, 0.5f);
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

        TextView redeemButton = new TextView(getContext());
        redeemButton.setText(R.string.redeem_coupon_redeem);
        redeemButton.setTextColor(Color.WHITE);
        redeemButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        redeemButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(redeemButton, Simple.MP, Simple.MP, 0.5f);
        Simple.setTextSizeDip(redeemButton, Defines.FS_DIALOG_BUTTON);
        Simple.setMarginLeftDip(redeemButton, Defines.PADDING_SMALL);
        Simple.setPaddingDip(redeemButton, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(redeemButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_SENSOR_LTBLUE, true);

        redeemButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ApplicationBase.hideActionBar(getContext());

                final ViewGroup parent = (ViewGroup) RedeemCouponDialog.this.getParent();

                JSONObject params = new JSONObject();

                Json.put(params, "accountId", Globals.accountId);
                Json.put(params, "couponCode", couponCode.getText().toString());

                Json.put(params, Defines.SYSTEM_USER_PARAM, Defines.SYSTEM_USER_NAME);

                RestApi.getPostThreaded("redeemCoupon", params, new RestApi.RestApiResultListener()
                {
                    @Override
                    public void OnRestApiResult(String what, JSONObject params, JSONObject result)
                    {
                        if ((result != null) && Json.equals(result, "Status", "OK"))
                        {
                            Globals.coins = Json.getInt(result, "coinCredit");
                            Globals.coinsAdded = Json.getInt(result, "coinsRedeemed");

                            if (parent != null)
                            {
                                parent.removeView(RedeemCouponDialog.this);

                                parent.addView(new RedeemedDialog(parent.getContext()));
                            }

                            return;
                        }

                        Log.d(LOGTAG, "redeemCoupon: " + result);

                        DialogView.errorAlert((ViewGroup) RedeemCouponDialog.this.getParent(),
                                R.string.alert_redeem_coupon_title,
                                R.string.alert_redeem_coupon_fail);
                    }
                });
            }
        });

        buttonArea.addView(redeemButton);

        setCustomView(dialogItems);
    }
}
