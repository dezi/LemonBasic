package de.sensordigitalmediagermany.lemonbasic.generic;

import android.app.Application;
import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

        UiModeManager uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);

        if (uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION)
        {
            Log.d(LOGTAG, "Running on a TV Device");
        }
        else
        {
            Log.d(LOGTAG, "Running on a non-TV Device");
        }
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

    public static AppCompatActivity getCurrentActivity(Context context)
    {
        return ((ApplicationBase) context.getApplicationContext()).currentActivity;
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
