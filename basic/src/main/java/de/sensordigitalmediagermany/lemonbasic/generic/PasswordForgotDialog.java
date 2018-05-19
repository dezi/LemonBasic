package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.widget.LinearLayout;
import android.text.InputType;
import android.view.ViewGroup;
import android.view.View;
import android.util.Log;

import org.json.JSONObject;

public class PasswordForgotDialog extends DialogView
{
    private static final String LOGTAG = PasswordForgotDialog.class.getSimpleName();

    protected DialogEdit userEmail;

    public PasswordForgotDialog(Context context)
    {
        super(context);

        setCloseButton(true, restartLogin);

        setTitleText(R.string.password_forgot_title);

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);

        userEmail = new DialogEdit(getContext());
        userEmail.setHint(R.string.password_forgot_hint_email);
        userEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        userEmail.requestFocus();

        dialogItems.addView(userEmail);

        setPositiveButton(R.string.password_forgot_request, new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                requestPassword();
            }
        });

        setCustomView(dialogItems);
    }

    private void requestPassword()
    {
        JSONObject params = new JSONObject();

        Json.put(params, "email", userEmail.getText().toString());

        Json.put(params, Defines.SYSTEM_USER_PARAM, Defines.SYSTEM_USER_NAME);

        RestApi.getPostThreaded("passwordForgotten", params, new RestApi.RestApiResultListener()
        {
            @Override
            public void OnRestApiResult(String what, JSONObject params, JSONObject result)
            {
                ViewGroup topframe = ApplicationBase.getCurrentTopframe(PasswordForgotDialog.this.getContext());

                if ((result != null) && Json.equals(result, "Status", "OK") &&
                        (Json.getInt(result, "accountFound") == 1))
                {
                    DialogView.errorAlert(topframe,
                            R.string.password_forgot_title,
                            R.string.password_forgot_success,
                            restartLogin);

                    return;
                }

                DialogView.errorAlert(topframe,
                        R.string.password_forgot_title,
                        R.string.password_forgot_fail,
                        restartLogin);
            }
        });
    }

    private final OnClickListener restartLogin = new OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            ViewGroup topframe = ApplicationBase.getCurrentTopframe(PasswordForgotDialog.this.getContext());
            if (topframe != null) topframe.addView(new LoginDialog(getContext()));
        }
    };
}
