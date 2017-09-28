package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.InputType;
import android.view.ViewGroup;
import android.view.Gravity;
import android.view.View;
import android.os.Build;
import android.util.Log;

import org.json.JSONObject;

public class RegisterDialog extends DialogView
{
    private static final String LOGTAG = RegisterDialog.class.getSimpleName();

    protected EditText firstName;
    protected EditText lastName;
    protected EditText userCompany;
    protected EditText userEmail;
    protected EditText passWord1;
    protected EditText passWord2;

    public RegisterDialog(Context context)
    {
        super(context);

        setCloseButton(true, null);

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        TextView titleView = new TextView(getContext());
        titleView.setText(R.string.register_title);
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

        firstName = new EditText(getContext());
        firstName.setHint(R.string.register_hint_firstname);
        firstName.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        firstName.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(firstName, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(firstName, Defines.PADDING_NORMAL);
        Simple.setPaddingDip(firstName,Defines.PADDING_SMALL);
        Simple.setSizeDip(firstName, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(firstName, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(firstName);

        lastName = new EditText(getContext());
        lastName.setHint(R.string.register_hint_lastname);
        lastName.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        lastName.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(lastName, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(lastName, Defines.PADDING_NORMAL);
        Simple.setPaddingDip(lastName,Defines.PADDING_SMALL);
        Simple.setSizeDip(lastName, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(lastName, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(lastName);

        userCompany = new EditText(getContext());
        userCompany.setHint(R.string.register_hint_company);
        userCompany.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        userCompany.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(userCompany, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(userCompany, Defines.PADDING_NORMAL);
        Simple.setPaddingDip(userCompany,Defines.PADDING_SMALL);
        Simple.setSizeDip(userCompany, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(userCompany, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(userCompany);

        userEmail = new EditText(getContext());
        userEmail.setHint(R.string.register_hint_email);
        userEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        userEmail.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(userEmail, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(userEmail, Defines.PADDING_NORMAL);
        Simple.setPaddingDip(userEmail,Defines.PADDING_SMALL);
        Simple.setSizeDip(userEmail, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(userEmail, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(userEmail);

        passWord1 = new EditText(getContext());
        passWord1.setHint(R.string.register_hint_password1);
        passWord1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passWord1.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(passWord1, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(passWord1, Defines.PADDING_NORMAL);
        Simple.setPaddingDip(passWord1,Defines.PADDING_SMALL);
        Simple.setSizeDip(passWord1, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(passWord1, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(passWord1);

        passWord2 = new EditText(getContext());
        passWord2.setHint(R.string.register_hint_password2);
        passWord2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passWord2.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(passWord2, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(passWord2, Defines.PADDING_NORMAL);
        Simple.setPaddingDip(passWord2,Defines.PADDING_SMALL);
        Simple.setSizeDip(passWord2, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(passWord2, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(passWord2);

        TextView registerButton = new TextView(getContext());
        registerButton.setText(R.string.register_register);
        registerButton.setGravity(Gravity.CENTER_HORIZONTAL);
        registerButton.setTextColor(Color.WHITE);
        registerButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        registerButton.setOnClickListener(onRegisterClicked);
        Simple.setSizeDip(registerButton, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(registerButton, Defines.FS_DIALOG_BUTTON);
        Simple.setPaddingDip(registerButton, Defines.PADDING_SMALL);
        Simple.setMarginTopDip(registerButton, Defines.PADDING_NORMAL);
        Simple.setMarginBottomDip(registerButton, Defines.PADDING_NORMAL);
        Simple.setRoundedCorners(registerButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_SENSOR_LTBLUE, true);

        dialogItems.addView(registerButton);

        RelativeLayout loginCenter = new RelativeLayout(getContext());
        loginCenter.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(loginCenter, Simple.MP, Simple.WC);

        dialogItems.addView(loginCenter);

        LinearLayout loginStuff = new LinearLayout(getContext());
        loginStuff.setOrientation(Simple.isTablet() ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
        Simple.setSizeDip(loginStuff, Simple.isTablet() ? Simple.WC : Simple.MP, Simple.WC);
        loginCenter.addView(loginStuff);

        TextView loginText = new TextView(getContext());
        loginText.setText(R.string.login_already_registered);
        loginText.setTextColor(Color.WHITE);
        loginText.setGravity(Gravity.CENTER_HORIZONTAL);
        loginText.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setSizeDip(loginText, Simple.isTablet() ? Simple.WC : Simple.MP, Simple.WC);
        Simple.setTextSizeDip(loginText, Defines.FS_DIALOG_INFO);
        Simple.setPaddingDip(loginText, 0, 0, Defines.PADDING_TINY, 0);

        loginStuff.addView(loginText);

        TextView loginButton = new TextView(getContext());
        loginButton.setText(R.string.register_login_here);
        loginButton.setTextColor(Color.WHITE);
        loginButton.setGravity(Gravity.CENTER_HORIZONTAL);
        loginButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        loginButton.setPaintFlags(loginButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Simple.setSizeDip(loginButton, Simple.isTablet() ? Simple.WC : Simple.MP, Simple.WC);
        Simple.setTextSizeDip(loginButton, Defines.FS_DIALOG_INFO);

        loginButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ViewGroup topFrame = (ViewGroup) RegisterDialog.this.getParent();

                dismissDialog();

                topFrame.addView(new LoginDialog(RegisterDialog.this.getContext()));
            }
        });

        loginStuff.addView(loginButton);

        setCustomView(dialogItems);
    }

    private final OnClickListener onRegisterClicked = new OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            ViewGroup topFrame = (ViewGroup) RegisterDialog.this.getParent();

            if (Simple.isEmpty(lastName.getText().toString()) || Simple.isEmpty(firstName.getText().toString()))
            {
                DialogView.errorAlert(topFrame,
                        R.string.alert_register_username_missing_title,
                        R.string.alert_register_username_missing_info);

                return;
            }

            if (Simple.isEmpty(userEmail.getText().toString()) ||
                    ! Simple.isEmailValid(userEmail.getText().toString()))
            {
                DialogView.errorAlert(topFrame,
                        R.string.alert_register_email_bad_title,
                        R.string.alert_register_email_bad_info);

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

            Globals.firstName = firstName.getText().toString();
            Globals.lastName = lastName.getText().toString();
            Globals.company = userCompany.getText().toString();
            Globals.emailAddress = userEmail.getText().toString();
            Globals.passWord = passWord1.getText().toString();
            Globals.UDID = Simple.getUUID();

            saveAction();
        }
    };

    private void saveAction()
    {
        JSONObject params = new JSONObject();

        Json.put(params, "UDID", Globals.UDID);
        Json.put(params, "email", Globals.emailAddress);
        Json.put(params, "password", Globals.passWord);
        Json.put(params, "firstname", Globals.firstName);
        Json.put(params, "lastname", Globals.lastName);
        Json.put(params, "trainerName", Defines.TRAINER_NAME);

        Json.put(params, "deviceKind", 2);
        Json.put(params, "deviceType", Build.MANUFACTURER + " " + Build.MODEL);
        Json.put(params, "platform", "ANDROID" + " " + (Simple.isTablet() ? "TABLET" : "PHONE"));
        Json.put(params, "version", Simple.getAppVersion(RegisterDialog.this.getContext()));
        Json.put(params, "language", Globals.language);

        RestApi.getPostThreaded("registerUser", params, new RestApi.RestApiResultListener()
        {
            @Override
            public void OnRestApiResult(String what, JSONObject params, JSONObject result)
            {
                if ((result != null) && Json.equals(result, "Status", "OK"))
                {
                    int errorcode = Json.getInt(result, "errorcode");

                    if (errorcode == 0)
                    {
                        Globals.accountId = Json.getInt(result, "accountId");

                        Globals.state = 1;
                        Globals.coins = 0;
                        Globals.admin = 0;

                        SettingsHandler.saveSettings();

                        registrationSuccess();

                        return;
                    }
                }

                Log.d(LOGTAG, "registerUser: " + result);

                DialogView.errorAlert((ViewGroup) RegisterDialog.this.getParent(),
                        R.string.alert_register_title,
                        R.string.alert_register_fail);
            }
        });
    }

    private void registrationSuccess()
    {
        DialogView.errorAlert((ViewGroup) RegisterDialog.this.getParent(),
                R.string.alert_register_title,
                R.string.alert_register_ok,
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Simple.startActivity(RegisterDialog.this.getContext(), ContentActivity.class);
                    }
                });
    }
}
