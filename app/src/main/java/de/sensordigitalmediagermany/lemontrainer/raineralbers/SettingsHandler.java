package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.support.annotation.Nullable;

import android.view.ViewGroup;
import android.util.Log;
import android.os.Build;

import java.util.Locale;

import org.json.JSONObject;

@SuppressWarnings({"WeakerAccess"})
public class SettingsHandler
{
    final static String LOGTAG = SettingsHandler.class.getSimpleName();

    public static int getSharedPrefInt(String key)
    {
        try
        {
            return ApplicationBase.prefs.getInt(key, 0);
        }
        catch (Exception ex)
        {
            return 0;
        }
    }

    public static boolean getSharedPrefBoolean(String key)
    {
        try
        {
            return ApplicationBase.prefs.getBoolean(key, false);
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    @Nullable
    public static String getSharedPrefString(String key)
    {
        try
        {
            return ApplicationBase.prefs.getString(key, null);
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    public static void setSharedPrefString(String key, String value)
    {
        ApplicationBase.prefs.edit().putString(key, value).apply();
    }

    public static void setSharedPrefInt(String key, int value)
    {
        ApplicationBase.prefs.edit().putInt(key, value).apply();
    }

    public static void setSharedPrefBoolean(String key, boolean value)
    {
        ApplicationBase.prefs.edit().putBoolean(key, value).apply();
    }

    public static void removeSharedPref(String key)
    {
        ApplicationBase.prefs.edit().remove(key).apply();
    }

    public static void loadSettings()
    {
        Globals.UDID = getSharedPrefString("session.UDID");

        Globals.emailAddress = getSharedPrefString("user.emailAddress");
        Globals.passWord = getSharedPrefString("user.passWord");
        Globals.firstName = getSharedPrefString("user.firstName");
        Globals.lastName = getSharedPrefString("user.lastName");
        Globals.company = getSharedPrefString("user.company");

        Globals.accountId = getSharedPrefInt("user.accountId");

        Log.d(LOGTAG, "loadSettings: firstName=" + Globals.firstName +  " lastName=" + Globals.lastName);
    }

    public static void saveSettings()
    {
        setSharedPrefString("session.UDID", Globals.UDID);

        setSharedPrefString("user.emailAddress", Globals.emailAddress);
        setSharedPrefString("user.passWord", Globals.passWord);
        setSharedPrefString("user.firstName", Globals.firstName);
        setSharedPrefString("user.lastName", Globals.lastName);
        setSharedPrefString("user.company", Globals.company);

        setSharedPrefInt("user.accountId", Globals.accountId);
    }

    public static void killSettings()
    {
        removeSharedPref("session.UDID");

        removeSharedPref("user.emailAddress");
        removeSharedPref("user.passWord");

        removeSharedPref("user.firstName");
        removeSharedPref("user.lastName");
        removeSharedPref("user.company");

        removeSharedPref("user.accountId");

        loadSettings();
    }

    public static void realLoginAction(final ViewGroup rootView, final Runnable loginSuccess, final Runnable loginFailure)
    {
        JSONObject params = new JSONObject();

        Json.put(params, "UDID", Globals.UDID);
        Json.put(params, "email", Globals.emailAddress);
        Json.put(params, "password", Globals.passWord);
        Json.put(params, "trainerName", Defines.TRAINER_NAME);

        Json.put(params, "deviceKind", 2);
        Json.put(params, "deviceType", "ANDROID" + " " + (Simple.isTablet() ? "TABLET" : "PHONE"));
        Json.put(params, "platform", Build.MANUFACTURER + " " + Build.MODEL);
        Json.put(params, "version", Simple.getAppVersion(rootView.getContext()));
        Json.put(params, "language", Globals.language);

        RestApi.getPostThreaded("checkForLogin", params, new RestApi.RestApiResultListener()
        {
            @Override
            public void OnRestApiResult(String what, JSONObject params, JSONObject result)
            {
                if ((result != null) && Json.equals(result, "Status", "OK"))
                {
                    int accountId = Json.getInt(result, "accountId");
                    JSONObject data = Json.getObject(result, "Data");

                    if ((accountId > 0) && (data != null))
                    {
                        Globals.accountId = accountId;

                        Globals.firstName = Json.getString(data, "firstname");
                        Globals.lastName = Json.getString(data, "lastname");

                        Globals.coins = Json.getInt(data, "coin_credit");
                        Globals.admin = Json.getInt(data, "is_admin");
                        Globals.state = Json.getInt(data, "state");

                        Globals.customerContents = Json.getArray(data, "CustomerContents");

                        SettingsHandler.saveSettings();

                        loginSuccess.run();

                        return;
                    }
                }

                loginFailure.run();
            }
        });
    }
}
