package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.os.Bundle;

public class FullScreenActivity extends AppCompatActivity
{
    @SuppressLint("InlinedApi")
    private final int uiOptions
            = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    protected View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(Simple.isTablet()
                ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        );

        setUiFlags();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        setUiFlags();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);

        setUiFlags();
    }

    @Override
    public void setContentView(View contentView)
    {
        super.setContentView(contentView);
        this.contentView = contentView;
    }

    public View getContentView()
    {
        return contentView;
    }

    private void setUiFlags()
    {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);
    }
}
