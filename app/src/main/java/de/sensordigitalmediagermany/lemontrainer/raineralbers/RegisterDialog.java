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
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

public class RegisterDialog extends DialogView
{
    private static final String LOGTAG = RegisterDialog.class.getSimpleName();

    protected EditText userName;
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

        userName = new EditText(getContext());
        userName.setHint(R.string.register_hint_name);
        userName.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        userName.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(userName, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(userName, Defines.PADDING_NORMAL);
        Simple.setPaddingDip(userName,Defines.PADDING_SMALL);
        Simple.setSizeDip(userName, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(userName, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(userName);

        userCompany = new EditText(getContext());
        userCompany.setHint(R.string.register_hint_company);
        userCompany.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
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
        Simple.setSizeDip(registerButton, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(registerButton, Defines.FS_DIALOG_BUTTON);
        Simple.setPaddingDip(registerButton, Defines.PADDING_SMALL);
        Simple.setMarginTopDip(registerButton, Defines.PADDING_NORMAL);
        Simple.setMarginBottomDip(registerButton, Defines.PADDING_NORMAL);
        Simple.setRoundedCorners(registerButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_SENSOR_LTBLUE, true);

        registerButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

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
}
