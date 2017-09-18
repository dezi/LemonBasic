package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.widget.ImageView;
import android.os.Bundle;

public class MainActivity extends FullScreenActivity
{
    private static final String LOGTAG = MainActivity.class.getSimpleName();

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

        if (Screens.getSplashScreenRes() < 0)
        {
            showMainScreen.run();
        }
        else
        {
            splashScreen.setImageResource(Screens.getSplashScreenRes());

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
            loginButton.setContent(topFrame, Screens.getMainScreenButtonRegisterRect(), msresid);

            loginButton.setOnButtonClicked(new Runnable()
            {
                @Override
                public void run()
                {
                    topFrame.addView(new LoginDialog(MainActivity.this));
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

            topFrame.addView(contentButton);        }
    };
}
