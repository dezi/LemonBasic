package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.IntentFilter;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.app.Application;
import android.content.Context;
import android.view.ViewGroup;
import android.os.Handler;
import android.util.Log;

import java.io.File;

import de.sensordigitalmediagermany.lemonbasic.purchase.IabBroadcastReceiver;
import de.sensordigitalmediagermany.lemonbasic.purchase.IabHelper;
import de.sensordigitalmediagermany.lemonbasic.purchase.IABResult;
import de.sensordigitalmediagermany.lemonbasic.purchase.Inventory;

public class ApplicationBase extends Application implements IabBroadcastReceiver.IabBroadcastListener
{
    private static final String LOGTAG = ApplicationBase.class.getSimpleName();

    public static final Handler handler = new Handler();
    public static SharedPreferences prefs;
    public static File cacheDir;

    public static IabHelper iabHelper;
    public static IabBroadcastReceiver iabBroadcastReceiver;

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

        initIAB();
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

    //region IAB setup.

    private void initIAB()
    {
        if (Defines.APP_STORE_PUBLIC_KEY.equals("undefined!"))
        {
            //
            // No in app purchases defined.
            //

            return;
        }

        iabHelper = new IabHelper(this, Defines.APP_STORE_PUBLIC_KEY);
        iabHelper.enableDebugLogging(true);

        Log.d(LOGTAG, "initIAB: Starting setup.");

        iabHelper.startSetup(new IabHelper.OnIabSetupFinishedListener()
        {
            public void onIabSetupFinished(IABResult result)
            {
                Log.d(LOGTAG, "onIabSetupFinished: Setup finished.");

                if (!result.isSuccess())
                {
                    Log.e(LOGTAG, "onIabSetupFinished: Problem setting up in-app billing: " + result);

                    return;
                }

                //
                // Important: Dynamically register for broadcast messages about updated purchases.
                // We register the receiver here instead of as a <receiver> in the Manifest
                // because we always call getPurchases() at startup, so therefore we can ignore
                // any broadcasts sent while the app isn't running.
                //

                iabBroadcastReceiver = new IabBroadcastReceiver(ApplicationBase.this);
                IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
                registerReceiver(iabBroadcastReceiver, broadcastFilter);

                Log.d(LOGTAG, "onIabSetupFinished: Setup successful. Querying inventory.");

                try
                {
                    iabHelper.queryInventoryAsync(mGotInventoryListener);
                }
                catch (IabHelper.IabAsyncInProgressException ex)
                {
                    Log.e(LOGTAG, "onIabSetupFinished: Error querying inventory. Another async operation in progress.");
                }
            }
        });
    }

    @Override
    public void receivedBroadcast()
    {
        Log.d(LOGTAG, "receivedBroadcast: Querying inventory.");

        try
        {
            iabHelper.queryInventoryAsync(mGotInventoryListener);
        }
        catch (IabHelper.IabAsyncInProgressException e)
        {
            Log.e(LOGTAG, "receivedBroadcast: Error querying inventory. Another async operation in progress.");
        }
    }

    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener()
    {
        public void onQueryInventoryFinished(IABResult result, Inventory inventory)
        {
            Log.d(LOGTAG, "onQueryInventoryFinished:");

            if (iabHelper == null) return;

            if (result.isFailure())
            {
                Log.e(LOGTAG, "onQueryInventoryFinished: Failed to query inventory: " + result);

                return;
            }

            Log.d(LOGTAG, "onQueryInventoryFinished: Query inventory was successful.");
        }
    };


    //endregion
}
