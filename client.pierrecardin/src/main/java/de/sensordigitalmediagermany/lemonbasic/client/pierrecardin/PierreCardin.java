package de.sensordigitalmediagermany.lemonbasic.client.pierrecardin;

import android.graphics.Color;
import android.util.Log;

import java.lang.reflect.Method;

@SuppressWarnings({"unchecked", "unused"})
public class PierreCardin
{
    private static final String LOGTAG = PierreCardin.class.getSimpleName();

    // @formatter:off
    private static final int COLOR_PIERRECARDIN_LTBLUE      = 0xff657995;
    private static final int COLOR_PIERRECARDIN_DKBLUE      = 0xff3b4455;
    private static final int COLOR_PIERRECARDIN_DIALOGS     = 0xcc3b4455;
    private static final int COLOR_PIERRECARDIN_NAVIBAR     = 0xffedf0f4;
    private static final int COLOR_PIERRECARDIN_CONTENT     = 0xffb6c4d2;
    private static final int COLOR_PIERRECARDIN_FRAMES      = 0xffedeef1;
    private static final int COLOR_PIERRECARDIN_TABBAR      = 0xfff5f5f5;
    private static final int COLOR_PIERRECARDIN_BUTTONTEXT  = 0xfff5f5f5;
    // @formatter:on

    // @formatter:off
    private static final String GOTHAM_BOLD          = "fonts/Gotham-Bold.otf";
    private static final String GOTHAM_LIGHT         = "fonts/Gotham-Light.otf";
    private static final String GOTHAM_MEDIUM        = "fonts/Gotham-Medium.otf";
    private static final String GOTHAMNARROW_LIGHT   = "fonts/GothamNarrow-Light.otf";
    private static final String ROONEY_LIGHT         = "fonts/Rooney-Light.otf";
    private static final String ROONEY_MEDIUM        = "fonts/Rooney-Medium.otf";
    private static final String ROONEY_REGULAR       = "fonts/Rooney-Regular.otf";
    // @formatter:on

    public static void initialize()
    {
        Log.d(LOGTAG, "initialize:");

        set("SYSTEM_USER_NAME", "RAINERALBERS");

        set("APIURL", "https://lemon-mobile-learning.com/lemon-trainer/ws/");

        // @formatter:off

        setResId("notifyIconLargeRes", isTablet()
                ? R.mipmap.lem_t_pierre_cardin
                : R.mipmap.lem_t_pierre_cardin);

        setResId("notifyIconSmallRes", isTablet()
                ? R.drawable.lem_t_iany_pierrecardin_notification
                : R.drawable.lem_t_iany_pierrecardin_notification);

        setResId("mainScreenRes", isWideScreen()
                ? R.drawable.lem_t_wide_pierrecardin_startscreen
                : isTablet()
                ? R.drawable.lem_t_ipad_pierrecardin_startscreen
                : R.drawable.lem_t_ipho_pierrecardin_startscreen);

        setResId("closeButtonRes", isTablet()
                ? R.drawable.lem_t_iany_pierrecardin_kreuz
                : R.drawable.lem_t_iany_pierrecardin_kreuz);

        setResId("contentScreenButtonProfileRes", isTablet()
                ? R.drawable.lem_t_ipad_pierrecardin_profile
                : -1);

        setResId("contentScreenHeaderRes", isTablet()
                ? R.drawable.lem_t_ipad_pierrecardin_menuoben
                : R.drawable.lem_t_ipho_pierrecardin_menuoben);

        setResId("arrowWhiteLeftOnRes", isTablet()
                ? R.drawable.lem_t_iany_pierrecardin_pfeillinks_weiss
                : R.drawable.lem_t_iany_pierrecardin_pfeillinks_weiss);

        setResId("arrowDarkLeftOnRes", isTablet()
                ? R.drawable.lem_t_iany_pierrecardin_pfeillinks_dunkel
                : R.drawable.lem_t_iany_pierrecardin_pfeillinks_dunkel);

        setResId("contentScreenButtonBackOnRes", isTablet()
                ? R.drawable.lem_t_iany_pierrecardin_back_on
                : R.drawable.lem_t_iany_pierrecardin_back_on);

        setResId("contentScreenButtonBackOffRes", isTablet()
                ? R.drawable.lem_t_iany_pierrecardin_back_off
                : R.drawable.lem_t_iany_pierrecardin_back_off);

        // @formatter:on
    }

    //region Private static commom implementation.

    private static Class defines;
    private static Class screens;
    private static Method simpleIsTablet;
    private static Method simpleIsWideScreen;

    static
    {
        try
        {
            defines = Class.forName("de.sensordigitalmediagermany.lemonbasic.generic.Defines");
            screens = Class.forName("de.sensordigitalmediagermany.lemonbasic.generic.Screens");

            Class simple = Class.forName("de.sensordigitalmediagermany.lemonbasic.generic.Simple");

            simpleIsTablet = simple.getMethod("isTablet");
            simpleIsWideScreen = simple.getMethod("isWideScreen");
        }
        catch (Exception ex)
        {
            Log.e(LOGTAG,"initialize: class failed!");
        }
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

    private static void setResId(String field, int resId)
    {
        try
        {
            screens.getField(field).set(null, resId);

            Log.d(LOGTAG,"set: field=" + field + " resId=" + resId + " ok");
        }
        catch (Exception ignore)
        {
            Log.e(LOGTAG,"set: field=" + field + " resId=" + resId + " failed!");
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

    //endregion Private static commom implementation.
}
