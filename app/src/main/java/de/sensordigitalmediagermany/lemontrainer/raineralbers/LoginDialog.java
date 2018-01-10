package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

public class LoginDialog extends DialogView
{
    private static final String LOGTAG = LoginDialog.class.getSimpleName();

    protected EditText userEmail;
    protected EditText passWord;

    public LoginDialog(Context context)
    {
        super(context);

        //
        // Only show close button if there
        // is a login button on splash screen.
        //

        setCloseButton(Defines.isLoginButton, null);

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        if (Defines.isSimpleLogin)
        {
            marginView.setBackground(null);

            dialogItems.setOrientation(LinearLayout.HORIZONTAL);
        }

        if (! Defines.isSimpleLogin)
        {
            TextView titleView = new TextView(getContext());
            titleView.setText(R.string.login_title);
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
        }

        userEmail = new EditText(getContext());
        userEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        userEmail.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(userEmail, Defines.FS_DIALOG_EDIT);
        Simple.setRoundedCorners(userEmail, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        if (Defines.isSimpleLogin)
        {
            userEmail.setMinEms(12);
            userEmail.setHint(getHint(R.string.login_hint_emailpc));
            Simple.setSizeDip(userEmail, Simple.WC, Simple.WC);
            Simple.setMarginRightDip(userEmail, Defines.PADDING_NORMAL);
            Simple.setPaddingDip(userEmail, Defines.PADDING_NORMAL);
        }
        else
        {
            userEmail.setHint(getHint(R.string.login_hint_email));
            Simple.setSizeDip(userEmail, Simple.MP, Simple.WC);
            Simple.setMarginTopDip(userEmail, Defines.PADDING_LARGE);
            Simple.setPaddingDip(userEmail,Defines.PADDING_SMALL);
        }

        dialogItems.addView(userEmail);

        passWord = new EditText(getContext());
        passWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passWord.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(passWord, Defines.FS_DIALOG_EDIT);
        Simple.setRoundedCorners(passWord, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        if (Defines.isSimpleLogin)
        {
            passWord.setMinEms(6);
            passWord.setHint(getHint(R.string.login_hint_password));
            Simple.setSizeDip(passWord, Simple.WC, Simple.WC);
            Simple.setMarginRightDip(passWord, Defines.PADDING_NORMAL);
            Simple.setPaddingDip(passWord, Defines.PADDING_NORMAL);
        }
        else
        {
            passWord.setHint(getHint(R.string.login_hint_password));
            Simple.setMarginTopDip(passWord, Defines.PADDING_SMALL);
            Simple.setPaddingDip(passWord,Defines.PADDING_SMALL);
            Simple.setSizeDip(passWord, Simple.MP, Simple.WC);
        }

        dialogItems.addView(passWord);

        if (Defines.isSimpleLogin)
        {
            ImageView loginButton = new ImageView(getContext());
            loginButton.setImageResource(R.drawable.lem_t_iany_generic_login_pfeil);
            loginButton.setScaleType(ImageView.ScaleType.FIT_START);
            Simple.setSizeDip(loginButton, Simple.WC, Simple.MP);

            loginButton.setOnClickListener(loginClick);

            dialogItems.addView(loginButton);
        }
        else
        {
            TextView loginButton = new TextView(getContext());
            loginButton.setText(getButtonText(R.string.login_login));
            loginButton.setTextColor(Color.WHITE);
            loginButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
            loginButton.setGravity(Gravity.CENTER_HORIZONTAL);
            Simple.setSizeDip(loginButton, Simple.MP, Simple.WC);
            Simple.setTextSizeDip(loginButton, Defines.FS_DIALOG_BUTTON);
            Simple.setPaddingDip(loginButton, Defines.PADDING_SMALL);
            Simple.setMarginTopDip(loginButton, Defines.PADDING_NORMAL);
            Simple.setMarginBottomDip(loginButton, Defines.PADDING_NORMAL);
            Simple.setRoundedCorners(loginButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, true);

            loginButton.setOnClickListener(loginClick);

            dialogItems.addView(loginButton);
        }

        if (! Defines.isSimpleLogin)
        {
            TextView alreadyRegistered = new TextView(getContext());
            alreadyRegistered.setText(R.string.login_already_registered);
            alreadyRegistered.setTextColor(Color.WHITE);
            alreadyRegistered.setGravity(Gravity.CENTER_HORIZONTAL);
            alreadyRegistered.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
            Simple.setSizeDip(alreadyRegistered, Simple.MP, Simple.WC);
            Simple.setTextSizeDip(alreadyRegistered, Defines.FS_DIALOG_INFO);

            dialogItems.addView(alreadyRegistered);

            TextView passForgotten = new TextView(getContext());
            passForgotten.setText(R.string.login_password_forgotten);
            passForgotten.setTextColor(Color.WHITE);
            passForgotten.setGravity(Gravity.CENTER_HORIZONTAL);
            passForgotten.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
            passForgotten.setPaintFlags(passForgotten.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            Simple.setSizeDip(passForgotten, Simple.MP, Simple.WC);
            Simple.setTextSizeDip(passForgotten, Defines.FS_DIALOG_INFO);

            passForgotten.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ViewGroup topFrame = (ViewGroup) LoginDialog.this.getParent();

                    dismissDialog();

                    topFrame.addView(new PasswordForgotDialog(LoginDialog.this.getContext()));
                }
            });

            dialogItems.addView(passForgotten);

            TextView registerButton = new TextView(getContext());
            registerButton.setText(R.string.login_register);
            registerButton.setTextColor(Color.WHITE);
            registerButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
            registerButton.setGravity(Gravity.CENTER_HORIZONTAL);
            Simple.setSizeDip(registerButton, Simple.MP, Simple.WC);
            Simple.setTextSizeDip(registerButton, Defines.FS_DIALOG_BUTTON);
            Simple.setMarginTopDip(registerButton, Defines.PADDING_NORMAL);
            Simple.setPaddingDip(registerButton, Defines.PADDING_SMALL);
            Simple.setRoundedCorners(registerButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, true);

            registerButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ViewGroup topFrame = (ViewGroup) LoginDialog.this.getParent();

                    dismissDialog();

                    topFrame.addView(new RegisterDialog(LoginDialog.this.getContext()));
                }
            });

            dialogItems.addView(registerButton);
        }

        setCustomView(dialogItems);
    }

    private final OnClickListener loginClick = new OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if (Simple.isEmpty(userEmail.getText().toString()) ||
                    Simple.isEmpty(passWord.getText().toString()) ||
                    !Simple.isEmailValid(userEmail.getText().toString()))
            {
                DialogView.errorAlert((ViewGroup) LoginDialog.this.getParent(),
                        R.string.alert_login_bad_title,
                        R.string.alert_login_bad_info);

                return;
            }

            Globals.emailAddress = userEmail.getText().toString();
            Globals.passWord = passWord.getText().toString();
            Globals.UDID = Simple.getUUID();

            SettingsHandler.realLoginAction((ViewGroup) LoginDialog.this.getParent(), loginSuccess, loginFailure);
        }
    };

    private final Runnable loginSuccess = new Runnable()
    {
        @Override
        public void run()
        {

            DialogView.errorAlert((ViewGroup) LoginDialog.this.getParent(),
                    R.string.login,
                    R.string.login_success,
                    new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    Simple.startActivityTop(LoginDialog.this.getContext(), ContentActivity.class);
                }
            });
        }
    };

    private final Runnable loginFailure = new Runnable()
    {
        @Override
        public void run()
        {
            DialogView.errorAlert((ViewGroup) LoginDialog.this.getParent(),
                    R.string.alert_login_bad_title,
                    R.string.alert_login_bad_info);
        }
    };
}
