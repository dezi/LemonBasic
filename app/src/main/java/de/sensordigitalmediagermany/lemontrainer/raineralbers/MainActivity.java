package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FullScreenActivity
{
    private static final String LOGTAG = MainActivity.class.getSimpleName();

    private static boolean isAlreadySplash;

    protected ImageView splashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        splashScreen = new ImageView(this);
        splashScreen.setScaleType(ImageView.ScaleType.FIT_CENTER);
        splashScreen.setBackgroundColor(Color.BLACK);

        Simple.setSizeDip(splashScreen, Simple.MP, Simple.MP);

        topFrame.addView(splashScreen);

        if (isAlreadySplash)
        {
            showMainScreen.run();
        }
        else
        {
            splashScreen.setImageResource(Screens.getSplashScreenRes());
            isAlreadySplash = true;

            ApplicationBase.handler.postDelayed(showMainScreen, 2000);
        }
    }

    protected final Runnable showMainScreen = new Runnable()
    {
        @Override
        public void run()
        {
            int msresid = Screens.getMainScreenRes();

            splashScreen.setImageResource(msresid);

            ScaledButton loginButton = new ScaledButton(topFrame.getContext());
            loginButton.setContent(topFrame, Screens.getMainScreenButtonLoginRect(), msresid);

            loginButton.setOnButtonClicked(new Runnable()
            {
                @Override
                public void run()
                {
                    showRegisterDialog();
                }
            });

            topFrame.addView(loginButton);

            ScaledButton contentButton = new ScaledButton(topFrame.getContext());
            contentButton.setContent(topFrame, Screens.getMainScreenButtonContentRect(), msresid);

            contentButton.setOnButtonClicked(new Runnable()
            {
                @Override
                public void run()
                {
                    Simple.startActivityFinish(MainActivity.this, ContentActivity.class);
                }
            });

            topFrame.addView(contentButton);
        }
    };

    protected void showRegisterDialog()
    {
        DialogView loginDialog = new DialogView(this);

        loginDialog.setCloseButton(true, null);

        LinearLayout dialogItems = new LinearLayout(this);
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        dialogItems.setMinimumWidth(500);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);

        TextView titleView = new TextView(this);
        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleView.setText(R.string.register_title);
        titleView.setTextColor(Color.WHITE);
        Simple.setSizeDip(titleView, Simple.MP, Simple.MP);

        dialogItems.addView(titleView);

        loginDialog.setCustomView(dialogItems);

        topFrame.addView(loginDialog);
    }
}
