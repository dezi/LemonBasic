package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PasswordChangeDialog extends DialogView
{
    private static final String LOGTAG = PasswordChangeDialog.class.getSimpleName();

    protected EditText oldPassword;
    protected EditText passWord1;
    protected EditText passWord2;

    public PasswordChangeDialog(Context context)
    {
        super(context);

        setCloseButton(true, null);

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        TextView titleView = new TextView(getContext());
        titleView.setText(R.string.password_change_title);
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

        oldPassword = new EditText(getContext());
        oldPassword.setHint(R.string.password_change_hint_oldpassword);
        oldPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        oldPassword.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(oldPassword, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(oldPassword, Defines.PADDING_LARGE);
        Simple.setPaddingDip(oldPassword,Defines.PADDING_SMALL);
        Simple.setSizeDip(oldPassword, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(oldPassword, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(oldPassword);

        passWord1 = new EditText(getContext());
        passWord1.setHint(R.string.password_change_hint_newpassword);
        passWord1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passWord1.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(passWord1, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(passWord1, Defines.PADDING_LARGE);
        Simple.setPaddingDip(passWord1,Defines.PADDING_SMALL);
        Simple.setSizeDip(passWord1, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(passWord1, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(passWord1);

        passWord2 = new EditText(getContext());
        passWord2.setHint(R.string.password_change_hint_newrepeat);
        passWord2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passWord2.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(passWord2, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(passWord2, Defines.PADDING_LARGE);
        Simple.setPaddingDip(passWord2,Defines.PADDING_SMALL);
        Simple.setSizeDip(passWord2, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(passWord2, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(passWord2);

        TextView requestButton = new TextView(getContext());
        requestButton.setText(R.string.password_change_request);
        requestButton.setTextColor(Color.WHITE);
        requestButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        requestButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(requestButton, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(requestButton, Defines.FS_DIALOG_BUTTON);
        Simple.setPaddingDip(requestButton, Defines.PADDING_SMALL);
        Simple.setMarginTopDip(requestButton, Defines.PADDING_NORMAL);
        Simple.setMarginBottomDip(requestButton, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(requestButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_SENSOR_LTBLUE, true);

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

                dismissDialog();

                DialogView.errorAlert(topFrame,
                        R.string.alert_not_implemented_title,
                        R.string.alert_not_implemented_info);
            }
        });

        dialogItems.addView(requestButton);

        setCustomView(dialogItems);
    }
}
