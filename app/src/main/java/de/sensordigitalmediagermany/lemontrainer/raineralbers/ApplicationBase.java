package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.os.Handler;
import android.util.Log;

public class ApplicationBase extends Application
{
    private static final String LOGTAG = ApplicationBase.class.getSimpleName();

    public static final Handler handler = new Handler();
    public static SharedPreferences prefs;

    @Override
    public void onCreate()
    {
        Log.d(LOGTAG, "onCreate...");

        super.onCreate();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }
}
