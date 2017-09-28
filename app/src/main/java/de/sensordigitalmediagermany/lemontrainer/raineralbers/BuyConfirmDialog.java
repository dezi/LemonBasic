package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

public class BuyConfirmDialog extends DialogView
{
    private static final String LOGTAG = BuyConfirmDialog.class.getSimpleName();

    protected EditText passWord;

    public BuyConfirmDialog(Context context)
    {
        super(context);

        setCloseButton(true, null);

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        TextView titleView = new TextView(getContext());
        titleView.setText(R.string.buy_confirm_title);
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

        passWord = new EditText(getContext());
        passWord.setHint(R.string.buy_confirm_password);
        passWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passWord.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(passWord, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(passWord, Defines.PADDING_LARGE);
        Simple.setPaddingDip(passWord,Defines.PADDING_SMALL);
        Simple.setSizeDip(passWord, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(passWord, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(passWord);

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

        TextView buyButton = new TextView(getContext());
        buyButton.setText(R.string.buy_confirm_buy);
        buyButton.setTextColor(Color.WHITE);
        buyButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        buyButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(buyButton, Simple.MP, Simple.MP, 0.5f);
        Simple.setTextSizeDip(buyButton, Defines.FS_DIALOG_BUTTON);
        Simple.setMarginLeftDip(buyButton, Defines.PADDING_SMALL);
        Simple.setPaddingDip(buyButton, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(buyButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_SENSOR_LTBLUE, true);

        buyButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (Simple.equals(passWord.getText().toString(), Globals.passWord))
                {
                    buyContent();

                    return;
                }

                DialogView.errorAlert((ViewGroup) BuyConfirmDialog.this.getParent(),
                        R.string.alert_buy_confirm_password_title,
                        R.string.alert_buy_confirm_password_fail);
            }
        });

        buttonArea.addView(buyButton);

        setCustomView(dialogItems);
    }

    private void buyContent()
    {
        final ViewGroup parent = (ViewGroup) BuyConfirmDialog.this.getParent();

        JSONObject params = new JSONObject();

        Json.put(params, "UDID", Globals.UDID);
        Json.put(params, "accountId", Globals.accountId);
        Json.put(params, "productId", Json.getInt(Globals.displayContent, "id"));
        Json.put(params, "isCourse", Json.getBoolean(Globals.displayContent, "_isCourse") ? 1 : 0);
        Json.put(params, "trainerName", Defines.TRAINER_NAME);

        RestApi.getPostThreaded("buyContent", params, new RestApi.RestApiResultListener()
        {
            @Override
            public void OnRestApiResult(String what, JSONObject params, JSONObject result)
            {
                if ((result != null) && Json.equals(result, "Status", "OK"))
                {
                    int errorcode = Json.getInt(result, "errorcode");

                    if (errorcode == 0)
                    {
                        if (parent != null)
                        {
                            parent.removeView(BuyConfirmDialog.this);

                            parent.addView(new BuyConfirmedDialog(parent.getContext()));
                        }

                        return;
                    }
                }

                Log.d(LOGTAG, "buyContent: " + result);

                DialogView.errorAlert((ViewGroup) BuyConfirmDialog.this.getParent(),
                        R.string.alert_buy_content_title,
                        R.string.alert_buy_content_fail);
            }
        });
    }
}
