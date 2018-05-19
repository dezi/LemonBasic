package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

public class PasswordChangeDialog extends DialogView
{
    private static final String LOGTAG = PasswordChangeDialog.class.getSimpleName();

    protected EditText oldPassword;
    protected EditText passWord1;
    protected EditText passWord2;

    protected boolean forced;

    public PasswordChangeDialog(Context context)
    {
        this(context, false);
    }

    public PasswordChangeDialog(Context context, final boolean forced)
    {
        super(context);

        this.forced = forced;

        setCloseButton(! forced, null);

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        TextView titleView = new TextView(getContext());
        titleView.setText(forced ? R.string.password_change_title_forced : R.string.password_change_title);
        titleView.setAllCaps(true);
        titleView.setTextColor(Defines.COLOR_DIALOG_TITLE);
        titleView.setGravity(Defines.isDialogTextCenter ? Gravity.CENTER_HORIZONTAL : Gravity.START);
        titleView.setTypeface(titleFont);
        Simple.setTextSizeDip(titleView, Defines.FS_DIALOG_TITLE);
        Simple.setSizeDip(titleView, Simple.MP, Simple.WC);

        Simple.setPaddingDip(titleView, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);

        dialogItems.addView(titleView);

        oldPassword = new EditText(getContext());
        oldPassword.setHint(getHint(R.string.password_change_hint_oldpassword));
        oldPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        oldPassword.setTypeface(editsFont);
        Simple.setTextSizeDip(oldPassword, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(oldPassword, Defines.PADDING_LARGE);
        Simple.setPaddingDip(oldPassword,Defines.PADDING_SMALL);
        Simple.setSizeDip(oldPassword, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(oldPassword, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(oldPassword);

        passWord1 = new EditText(getContext());
        passWord1.setHint(getHint(R.string.password_change_hint_newpassword));
        passWord1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passWord1.setTypeface(editsFont);
        Simple.setTextSizeDip(passWord1, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(passWord1, Defines.PADDING_LARGE);
        Simple.setPaddingDip(passWord1,Defines.PADDING_SMALL);
        Simple.setSizeDip(passWord1, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(passWord1, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(passWord1);

        passWord2 = new EditText(getContext());
        passWord2.setHint(getHint(R.string.password_change_hint_newrepeat));
        passWord2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passWord2.setTypeface(editsFont);
        Simple.setTextSizeDip(passWord2, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(passWord2, Defines.PADDING_LARGE);
        Simple.setPaddingDip(passWord2,Defines.PADDING_SMALL);
        Simple.setSizeDip(passWord2, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(passWord2, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(passWord2);

        TextView requestButton = new TextView(getContext());
        requestButton.setText(getHint(R.string.password_change_request));
        requestButton.setTextColor(Color.WHITE);
        requestButton.setTypeface(TypeFaces.getTypeface(getContext(), Defines.FONT_DIALOG_BUTTON));
        requestButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(requestButton, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(requestButton, Defines.FS_DIALOG_BUTTON);
        Simple.setPaddingDip(requestButton, Defines.PADDING_SMALL);
        Simple.setMarginTopDip(requestButton, Defines.PADDING_LARGE);
        Simple.setMarginBottomDip(requestButton, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(requestButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, true);

        requestButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ViewGroup topFrame = (ViewGroup) PasswordChangeDialog.this.getParent();

                if (! Simple.equals(oldPassword.getText().toString(), Globals.passWord))
                {
                    DialogView.errorAlert(topFrame,
                            R.string.alert_change_password_diff_title,
                            R.string.alert_change_password_diff_info);

                    return;
                }

                if (Simple.isEmpty(passWord1.getText().toString()) ||
                        (passWord1.getText().toString().length() < 6))
                {
                    DialogView.errorAlert(topFrame,
                            R.string.alert_register_password_bad_title,
                            R.string.alert_register_password_bad_info);

                    return;
                }

                if (! Simple.equals(passWord1.getText().toString(), passWord2.getText().toString()))
                {
                    DialogView.errorAlert(topFrame,
                            R.string.alert_register_password_diff_title,
                            R.string.alert_register_password_diff_info);

                    return;
                }

                changePassword();
            }
        });

        dialogItems.addView(requestButton);

        setCustomView(dialogItems);
    }

    private void changePassword()
    {
        JSONObject params = new JSONObject();

        Json.put(params, "accountId", Globals.accountId);
        Json.put(params, "oldPassword", Globals.passWord);
        Json.put(params, "newPassword", passWord1.getText().toString());

        Json.put(params, Defines.SYSTEM_USER_PARAM, Defines.SYSTEM_USER_NAME);

        RestApi.getPostThreaded("changePassword", params, new RestApi.RestApiResultListener()
        {
            @Override
            public void OnRestApiResult(String what, JSONObject params, JSONObject result)
            {
                if ((result != null) && Json.equals(result, "Status", "OK"))
                {
                    ViewGroup topframe = dismissDialog();

                    Globals.passWord = passWord1.getText().toString();
                    SettingsHandler.saveSettings();

                    String pwchanged = "pwchanged:" + Globals.accountId;
                    SettingsHandler.setSharedPrefBoolean(pwchanged, true);

                    DialogView.errorAlert(topframe,
                            R.string.alert_change_password_success_title,
                            R.string.alert_change_password_success_info,
                            new OnClickListener()
                            {
                                @Override
                                public void onClick(View view)
                                {
                                    if (forced)
                                    {
                                        ApplicationBase.handler.postDelayed(new Runnable()
                                        {
                                            @Override
                                            public void run()
                                            {
                                                Simple.startActivityFinish(getContext(), ContentActivity.class);
                                            }
                                        }, 100);
                                    }
                                }
                            });

                    return;
                }

                DialogView.errorAlert((ViewGroup) getParent(),
                        R.string.alert_change_password_fail_title,
                        R.string.alert_change_password_fail_info);
            }
        });
    }
}
