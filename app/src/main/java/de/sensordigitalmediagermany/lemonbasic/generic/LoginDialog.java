package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
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

    protected DialogEdit userEmail;
    protected DialogEdit passWord;

    public LoginDialog(Context context)
    {
        super(context);

        //
        // Only show close button if there
        // is a login button on splash screen.
        //

        setCloseButton(Defines.isLoginButton, null);

        if (Defines.isSimpleLogin)
        {
            //
            // Transparent dialog with no title.
            //

            marginView.setBackground(null);
            Simple.setPaddingDip(marginView, 0);
        }
        else
        {
            setTitleText(R.string.login_title);
        }

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);

        if (Defines.isSimpleLogin)
        {
            //
            // Horizontal layout on tablets, vertical on phones.
            //

            dialogItems.setOrientation(Simple.isTablet() ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
        }

        userEmail = new DialogEdit(getContext());
        userEmail.setMinEms(Simple.isTablet() ? 12 : 9);
        userEmail.setHintSpecial(R.string.login_hint_email);
        userEmail.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        if (Defines.isSimpleLogin)
        {
            if (Simple.isTablet())
            {
                Simple.setMarginRightDip(userEmail, Defines.PADDING_NORMAL);
            }
            else
            {
                //
                // Yield space in upper view for the login button
                // following the password edit.
                //

                Simple.setMarginRightDip(userEmail, Defines.PADDING_NORMAL * 5);
            }

            userEmail.setMatchParent(false);
            Simple.setPaddingDip(userEmail, Defines.PADDING_NORMAL);
        }
        else
        {
            Simple.setMarginTopDip(userEmail, Defines.PADDING_LARGE);
        }

        dialogItems.addView(userEmail);

        //
        // In simple login the login button follows the passwort edit.
        //

        LinearLayout passAndButtonBox = new LinearLayout(getContext());
        passAndButtonBox.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(passAndButtonBox, Simple.MP, Simple.WC);
        dialogItems.addView(passAndButtonBox);

        if (Defines.isSimpleLogin)
        {
            if (! Simple.isTablet())
            {
                //
                // User and password edit ist arranged vertical.
                //

                Simple.setMarginTopDip(passAndButtonBox, Defines.PADDING_LARGE);

                //
                // Shift login to upper part of screen.
                //

                Simple.setMarginBottomDip(passAndButtonBox, Defines.PADDING_XLARGE * 9);
            }
        }

        passWord = new DialogEdit(getContext());
        passWord.setMinEms(Simple.isTablet() ? 6 : 9);
        passWord.setHintSpecial(R.string.login_hint_password);
        passWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        if (Defines.isSimpleLogin)
        {
            passWord.setMatchParent(false);
            Simple.setPaddingDip(passWord, Defines.PADDING_NORMAL);
        }
        else
        {
            Simple.setMarginTopDip(passWord, Defines.PADDING_LARGE);
        }

        passAndButtonBox.addView(passWord);

        if (Defines.isSimpleLogin)
        {
            ImageView loginButton = new FocusableImageView(getContext());
            loginButton.setImageResource(R.drawable.lem_t_iany_generic_login_pfeil);
            loginButton.setScaleType(ImageView.ScaleType.FIT_START);

            Simple.setSizeDip(loginButton, Simple.WC, Simple.MP);
            Simple.setMarginLeftDip(loginButton, Defines.PADDING_NORMAL);

            loginButton.setOnClickListener(loginClick);

            passAndButtonBox.addView(loginButton);
        }
        else
        {
            DialogButton loginButton = new DialogButton(getContext());
            loginButton.setInvers(true);
            loginButton.setText(R.string.login_login);
            loginButton.setMarginTopDip(Defines.PADDING_NORMAL);

            loginButton.setOnClickListener(loginClick);

            dialogItems.addView(loginButton);

            TextView alreadyRegistered = new TextView(getContext());
            alreadyRegistered.setText(R.string.login_already_registered);
            alreadyRegistered.setTextColor(Color.WHITE);
            alreadyRegistered.setGravity(Gravity.CENTER_HORIZONTAL);
            alreadyRegistered.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
            Simple.setSizeDip(alreadyRegistered, Simple.MP, Simple.WC);
            Simple.setTextSizeDip(alreadyRegistered, Defines.FS_DIALOG_INFO);
            Simple.setMarginTopDip(alreadyRegistered, Defines.PADDING_NORMAL);

            dialogItems.addView(alreadyRegistered);

            TextView passForgotten = new TextView(getContext());
            passForgotten.setText(R.string.login_password_forgotten);
            passForgotten.setTextColor(Color.WHITE);
            passForgotten.setGravity(Gravity.CENTER_HORIZONTAL);
            passForgotten.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
            passForgotten.setPaintFlags(passForgotten.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            Simple.setSizeDip(passForgotten, Simple.MP, Simple.WC);
            Simple.setTextSizeDip(passForgotten, Defines.FS_DIALOG_INFO);
            Simple.setMarginTopDip(passForgotten, Defines.PADDING_NORMAL);

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

            DialogButton registerButton = new DialogButton(getContext());
            registerButton.setText(R.string.login_register);
            registerButton.setMarginTopDip(Defines.PADDING_NORMAL);

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
                    if (SettingsHandler.isPasswordChangeRequired())
                    {
                        ViewGroup topFrame = (ViewGroup) LoginDialog.this.getParent();

                        topFrame.addView(new PasswordChangeDialog(topFrame.getContext(), true));
                    }
                    else
                    {
                        ContentHandler.getUserContentAndStart((ViewGroup) LoginDialog.this.getParent());
                    }
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
