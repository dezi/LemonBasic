package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.os.Bundle;

import org.json.JSONObject;

public class MainActivity extends FullScreenActivity
{
    private static final String LOGTAG = MainActivity.class.getSimpleName();

    protected ImageView splashScreen;
    protected boolean contentButtonClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Simple.setThreadPolicy();

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

        SettingsHandler.loadSettings();

        ApplicationBase.handler.post(getData);
    }

    protected final Runnable getData = new Runnable()
    {
        @Override
        public void run()
        {
            ContentHandler.getAllContent(topFrame, new Runnable()
            {
                @Override
                public void run()
                {
                    if (Globals.accountId > 0)
                    {
                        SettingsHandler.realLoginAction(topFrame, loginSuccess, loginFailure);
                    }
                    else
                    {
                        topFrame.addView(new LoginDialog(MainActivity.this));
                    }

                    if (contentButtonClicked)
                    {
                        Simple.startActivityFinish(MainActivity.this, ContentActivity.class);
                    }
                }
            });
        }
    };

    protected final Runnable showMainScreen = new Runnable()
    {
        @Override
        public void run()
        {
            int msresid = Screens.getMainScreenRes();

            splashScreen.setImageResource(msresid);

            if (Defines.isLoginButton)
            {
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
                        if (Globals.contentsLoaded)
                        {
                            Simple.startActivityFinish(MainActivity.this, ContentActivity.class);
                        } else
                        {
                            contentButtonClicked = true;
                        }
                    }
                });

                topFrame.addView(contentButton);
            }
        }
    };

    private final Runnable loginSuccess = new Runnable()
    {
        @Override
        public void run()
        {
            String pwchanged = "pwchanged:" + Globals.accountId;

            if (! SettingsHandler.getSharedPrefBoolean(pwchanged))
            {
                topFrame.addView(new PasswordChangeDialog(MainActivity.this, true));
            }
            else
            {
                ApplicationBase.handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Simple.startActivityFinish(MainActivity.this, ContentActivity.class);
                    }
                }, 750);
            }
        }
    };

    private final Runnable loginFailure = new Runnable()
    {
        @Override
        public void run()
        {
            DialogView.errorAlert(topFrame,
                    R.string.alert_login_bad_title,
                    R.string.alert_login_bad_info,
                    new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            topFrame.addView(new LoginDialog(MainActivity.this));
                        }
                    });
        }
    };
}
