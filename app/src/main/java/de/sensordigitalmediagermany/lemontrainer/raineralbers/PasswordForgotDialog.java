package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View;
import android.text.InputType;

public class PasswordForgotDialog extends DialogView
{
    private static final String LOGTAG = PasswordForgotDialog.class.getSimpleName();

    protected EditText userEmail;

    public PasswordForgotDialog(Context context)
    {
        super(context);

        setCloseButton(true, null);

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        TextView titleView = new TextView(getContext());
        titleView.setText(R.string.password_forgot_title);
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

        userEmail = new EditText(getContext());
        userEmail.setHint(R.string.password_forgot_hint_email);
        userEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        userEmail.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_LIGHT));
        Simple.setTextSizeDip(userEmail, Defines.FS_DIALOG_EDIT);
        Simple.setMarginTopDip(userEmail, Defines.PADDING_LARGE);
        Simple.setPaddingDip(userEmail,Defines.PADDING_SMALL);
        Simple.setSizeDip(userEmail, Simple.MP, Simple.WC);
        Simple.setRoundedCorners(userEmail, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        dialogItems.addView(userEmail);

        TextView requestButton = new TextView(getContext());
        requestButton.setText(R.string.login_login);
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
            }
        });

        dialogItems.addView(requestButton);

        setCustomView(dialogItems);
    }
}
