package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.os.Bundle;

public class MainActivity extends FullScreenActivity
{
    private static final String LOGTAG = MainActivity.class.getSimpleName();

    protected FrameLayout topFrame;
    protected ImageView splashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        topFrame = new FrameLayout(this);
        topFrame.setLayoutParams(new FrameLayout.LayoutParams(Simple.MP, Simple.MP));
        topFrame.setBackgroundColor(Defines.COLOR_SENSOR_BLUE);

        setContentView(topFrame);

        splashScreen = new ImageView(this);
        splashScreen.setImageResource(Screens.getSplashScreenRes());
        splashScreen.setScaleType(ImageView.ScaleType.FIT_CENTER);
        splashScreen.setBackgroundColor(Color.BLACK);
        Simple.setSizeDip(splashScreen, Simple.MP, Simple.MP);

        topFrame.addView(splashScreen);

        ApplicationBase.handler.postDelayed(showMainScreen, 2000);
    }

    protected final Runnable showMainScreen = new Runnable()
    {
        @Override
        public void run()
        {
            int msresid = Screens.getMainScreenRes();

            splashScreen.setImageResource(msresid);

            ScaledButton loginButton = new ScaledButton(contentView.getContext());
            loginButton.setContent(Screens.getMainScreenButtonLoginRect(), msresid);

            topFrame.addView(loginButton);

            ScaledButton contenButton = new ScaledButton(contentView.getContext());
            contenButton.setContent(Screens.getMainScreenButtonContentRect(), msresid);

            topFrame.addView(contenButton);
        }
    };
}
