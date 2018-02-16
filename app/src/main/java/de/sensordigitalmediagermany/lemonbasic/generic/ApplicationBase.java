package de.sensordigitalmediagermany.lemonbasic.generic;

import android.app.Activity;
import android.app.Application;
import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.io.File;

import static android.content.ContentValues.TAG;

public class ApplicationBase extends Application
{
    private static final String LOGTAG = ApplicationBase.class.getSimpleName();

    public static final Handler handler = new Handler();
    public static SharedPreferences prefs;
    public static File cacheDir;

    private AppCompatActivity currentActivity;

    @Override
    public void onCreate()
    {
        Log.d(LOGTAG, "onCreate...");

        super.onCreate();

        cacheDir = getCacheDir();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Configuration configuration = getResources().getConfiguration();

        Log.d(LOGTAG, "onCreate: fontScale=" + configuration.fontScale);

        Simple.checkFeatures(this);
    }

    public static void setCurrentActivity(AppCompatActivity currentActivity)
    {
        ((ApplicationBase) currentActivity.getApplicationContext()).currentActivity = currentActivity;
    }

    public static void clearCurrentActivity(AppCompatActivity currentActivity)
    {
        ApplicationBase self = (ApplicationBase) currentActivity.getApplicationContext();

        if (self.currentActivity == currentActivity)
        {
            self.currentActivity = null;
        }
    }

    @Nullable
    public static AppCompatActivity getCurrentActivity(Context context)
    {
        return ((ApplicationBase) context.getApplicationContext()).currentActivity;
    }

    @Nullable
    public static ViewGroup getCurrentTopframe(Context context)
    {
        AppCompatActivity activity = getCurrentActivity(context);

        if (activity instanceof FullScreenActivity)
        {
            return ((FullScreenActivity) activity).topFrame;
        }

        return null;
    }

    public static void hideActionBar(Context context)
    {
        final AppCompatActivity self = ApplicationBase.getCurrentActivity(context);

        if (self instanceof FullScreenActivity)
        {
            ((FullScreenActivity) self).setUiFlags();
        }
    }
}
