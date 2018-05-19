package de.sensordigitalmediagermany.lemonbasic.client.raineralbers;

import android.util.Log;

import java.lang.reflect.Method;

@SuppressWarnings({"unchecked"})
public class RainerAlbers
{
    private static final String LOGTAG = RainerAlbers.class.getSimpleName();

    private static Class defines;

    private static Method simpleIsTablet;
    private static Method simpleIsWideScreen;

    static
    {
        try
        {
            defines = Class.forName("de.sensordigitalmediagermany.lemonbasic.generic.Defines");

            Class simple = Class.forName("de.sensordigitalmediagermany.lemonbasic.generic.Simple");

            simpleIsTablet = simple.getMethod("isTablet");
            simpleIsWideScreen = simple.getMethod("isWideScreen");
        }
        catch (Exception ex)
        {
            Log.e(LOGTAG,"initialize: class failed!");
        }
    }

    public static void initialize()
    {
        Log.d(LOGTAG, "initialize:");

        set("SYSTEM_USER_NAME", "RAINERALBERS");
        set("APIURL", "https://lemon-mobile-learning.com/lemon-trainer/ws/");
        set("APP_STORE_PUBLIC_KEY", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vVFnaH14P/p9q3P12k5gOClOpt/JlS066YNbT/2D7d8kVluFtuGhDofnbJiSEBFHhTmQNoIhN2tDB+qJ2aG7EhcnULxiNXp2GYaYOQ4ygGgmZbqaoTRr0Hz2wfMihci7sgM81Mzlhuq78TcEo/5+eMa4Eaxec5S90tYruXh05XIPKOlQEoBOKDb+WQTCIrxYn9IK3h/v3fsmfrmZhjTYX+bVvZAheIumbN73ITh7PgTFn8slqAJvaczDzrlCrI+iLEl59L2zGgSqBpN+bJRInDju1+zPMSZALLfCR8z9bRglCVTgHSWYG3uDtLbcvqIb6LEJ/AROpFn3uFrPmGZUQIDAQAB");

        set("ASSET_SETTINGS_ASPECT", isWideScreen() ? 5.00f : isTablet() ? 3.5f : 2.0f);
    }

    private static void set(String field, Object value)
    {
        try
        {
            defines.getField(field).set(null, value);

            Log.d(LOGTAG,"set: field=" + field + " value=" + value + " ok");
        }
        catch (Exception ignore)
        {
            Log.e(LOGTAG,"set: field=" + field + " value=" + value + " failed!");
        }
    }

    private static boolean isTablet()
    {
        try
        {
            Boolean res = (Boolean) simpleIsTablet.invoke(null);
            return (res != null) && res;
        }
        catch (Exception ignore)
        {
            Log.e(LOGTAG,"isTablet: failed!");
        }

        return false;
    }

    private static boolean isWideScreen()
    {
        try
        {
            Boolean res = (Boolean) simpleIsWideScreen.invoke(null);
            return (res != null) && res;
        }
        catch (Exception ignore)
        {
            Log.e(LOGTAG,"isWideScreen: failed!");
        }

        return false;
    }
}
