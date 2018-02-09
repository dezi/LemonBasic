package de.sensordigitalmediagermany.lemonbasic.generic;

import android.annotation.SuppressLint;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.Display;
import android.view.View;
import android.os.Bundle;

import java.util.ArrayList;

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

        //ApplicationBase.handler.postDelayed(focusDump, 2000);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        ApplicationBase.clearCurrentActivity(this);

        ApplicationBase.handler.removeCallbacks(focusDump);
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

    private final Runnable focusDump = new Runnable()
    {
        @Override
        public void run()
        {
            Log.d(LOGTAG, "focusDump: view=" + FullScreenActivity.this.getCurrentFocus());

            if (FullScreenActivity.this.getCurrentFocus() != null)
            {
                //FullScreenActivity.this.getCurrentFocus().setBackgroundColor(0x88880000);
            }

            ApplicationBase.handler.postDelayed(focusDump, 2000);
        }
    };

    private ArrayList<View> focusableViews;
    private View excludeView;

    public void saveFocusableViews(View exclude)
    {
        excludeView = exclude;

        focusableViews = new ArrayList<>();

        saveFocusableViewsRecurse(topFrame);
    }

    public void restoreFocusableViews()
    {
        if (focusableViews != null)
        {
            for (View view : focusableViews)
            {
                //Log.d(LOGTAG, "restoreFocusableViewsRunner: view=" + view);

                view.setFocusable(true);
            }

            focusableViews = null;
        }
    }

    private void saveFocusableViewsRecurse(View view)
    {
        if (view instanceof ViewGroup)
        {
            ViewGroup vg = (ViewGroup) view;

            for (int inx = 0; inx < vg.getChildCount(); inx++)
            {
                View child = vg.getChildAt(inx);

                if (child == excludeView) continue;

                if (Generic.canFocus(child))
                {
                    //Log.d(LOGTAG, "saveFocusableViewsRecurse: view=" + view);

                    focusableViews.add(child);
                    child.setFocusable(false);
                }

                saveFocusableViewsRecurse(child);
            }
        }
    }
}
