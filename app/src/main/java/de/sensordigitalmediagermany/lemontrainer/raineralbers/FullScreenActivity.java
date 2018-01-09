package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;

import android.support.v7.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.content.Context;
import android.widget.FrameLayout;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.Display;
import android.view.View;
import android.os.Bundle;
import android.util.Log;

@SuppressLint("Registered")
public class FullScreenActivity extends AppCompatActivity
{
    private static final String LOGTAG = FullScreenActivity.class.getSimpleName();

    @SuppressLint("InlinedApi")
    private final int uiOptions
            = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    protected FrameLayout topFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(Simple.isTablet()
                ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        );

        //
        // Remove stupid menu bar.
        //

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        //
        // Get current screen dimensions in full screen mode.
        //

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getRealMetrics(metrics);

        topFrame = new FrameLayout(this);
        topFrame.setLayoutParams(new FrameLayout.LayoutParams(metrics.widthPixels, metrics.heightPixels));

        setContentView(topFrame);

        setUiFlags();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        setUiFlags();

        ApplicationBase.setCurrentActivity(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        ApplicationBase.clearCurrentActivity(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        ApplicationBase.clearCurrentActivity(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);

        setUiFlags();
    }

    public void setUiFlags()
    {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);
    }
}
