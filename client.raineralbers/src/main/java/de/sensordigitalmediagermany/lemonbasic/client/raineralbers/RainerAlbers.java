package de.sensordigitalmediagermany.lemonbasic.client.raineralbers;

import android.graphics.Color;
import android.util.Log;

import java.lang.reflect.Method;

@SuppressWarnings({"unchecked", "unused"})
public class RainerAlbers
{
    private static final String LOGTAG = RainerAlbers.class.getSimpleName();

    // @formatter:off
    private static final int COLOR_RALBERS_LTBLUE      = 0xff657995;
    private static final int COLOR_RALBERS_DKBLUE      = 0xff3b4455;
    private static final int COLOR_RALBERS_DIALOGS     = 0xcc3b4455;
    private static final int COLOR_RALBERS_NAVIBAR     = 0xffedf0f4;
    private static final int COLOR_RALBERS_CONTENT     = 0xffb6c4d2;
    private static final int COLOR_RALBERS_FRAMES      = 0xffedeef1;
    private static final int COLOR_RALBERS_TABBAR      = 0xfff5f5f5;
    private static final int COLOR_RALBERS_BUTTONTEXT  = 0xfff5f5f5;
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

        set("APP_STORE_PUBLIC_KEY", ""
                + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vVFnaH14P/p9q3P"
                + "12k5gOClOpt/JlS066YNbT/2D7d8kVluFtuGhDofnbJiSEBFHhTmQNoIhN2t"
                + "DB+qJ2aG7EhcnULxiNXp2GYaYOQ4ygGgmZbqaoTRr0Hz2wfMihci7sgM81Mz"
                + "lhuq78TcEo/5+eMa4Eaxec5S90tYruXh05XIPKOlQEoBOKDb+WQTCIrxYn9I"
                + "K3h/v3fsmfrmZhjTYX+bVvZAheIumbN73ITh7PgTFn8slqAJvaczDzrlCrI+"
                + "iLEl59L2zGgSqBpN+bJRInDju1+zPMSZALLfCR8z9bRglCVTgHSWYG3uDtLb"
                + "cvqIb6LEJ/AROpFn3uFrPmGZUQIDAQAB");

        // @formatter:off

        set("COLOR_NAVIBAR"           , COLOR_RALBERS_NAVIBAR);
        set("COLOR_TABBAR"            , COLOR_RALBERS_TABBAR);
        set("COLOR_CONTENT"           , COLOR_RALBERS_CONTENT);
        set("COLOR_FRAMES"            , COLOR_RALBERS_FRAMES);
        set("COLOR_DIALOG_BACK"       , COLOR_RALBERS_DIALOGS);
        set("COLOR_DIALOG_TITLE"      , Color.WHITE);
        set("COLOR_DIALOG_INFOS"      , Color.WHITE);
        set("COLOR_BUTTON_TEXT"       , COLOR_RALBERS_BUTTONTEXT);
        set("COLOR_BUTTON_BACK"       , COLOR_RALBERS_LTBLUE);
        set("COLOR_ALERT_BACK"        , COLOR_RALBERS_DIALOGS);
        set("COLOR_SETTINGS_HEADERS"  , COLOR_RALBERS_DKBLUE);
        set("COLOR_SETTINGS_LIST"     , COLOR_RALBERS_NAVIBAR);
        set("COLOR_SETTINGS_LIST_SEL" , COLOR_RALBERS_LTBLUE);
        set("COLOR_DETAIL_TITLE"      , COLOR_RALBERS_LTBLUE);
        set("COLOR_PROGRESS_DONE"     , Color.GREEN);
        set("COLOR_PROGRESS_NEED"     , Color.YELLOW);

        set("ASSET_SETTINGS_ASPECT"   , isWideScreen() ? 5.00f : isTablet() ? 3.5f : 2.0f);
        set("ASSET_THUMBNAIL_ASPECT"  , 1.9f);
        set("ASSET_DETAIL_ASPECT"     , 3.0f);
        set("ASSET_COURSE_ASPECT"     , 3.5f);
        set("ASSET_BANNER_ASPECT"     , 3.2f);

        set("FONT_DIALOG_TITLE"       , GOTHAM_BOLD);
        set("FONT_DIALOG_INFOS"       , GOTHAM_LIGHT);
        set("FONT_DIALOG_EDITS"       , GOTHAM_LIGHT);
        set("FONT_DIALOG_BUTTON"      , GOTHAM_BOLD);

        set("FONT_GENERIC_BUTTON"     , GOTHAM_BOLD);
        set("FONT_GENERIC_EDIT"       , GOTHAM_LIGHT);

        set("FONT_ASSET_TITLE"        , GOTHAM_BOLD);
        set("FONT_ASSET_SUMMARY"      , GOTHAMNARROW_LIGHT);
        set("FONT_SLIDER_ALL"         , GOTHAMNARROW_LIGHT);
        set("FONT_SCALED_BUTTON"      , ROONEY_MEDIUM);
        set("FONT_TABBAR_ENTRY"       , ROONEY_MEDIUM);
        set("FONT_CATEGORY_TITLE"     , GOTHAM_BOLD);
        set("FONT_SETTINGS_HEADER"    , GOTHAM_BOLD);
        set("FONT_SETTINGS_SUBHEAD"   , GOTHAM_MEDIUM);
        set("FONT_SETTINGS_INFOS"     , GOTHAMNARROW_LIGHT);
        set("FONT_SETTINGS_VERSION"   , GOTHAMNARROW_LIGHT);
        set("FONT_SETTINGS_LIST"      , GOTHAMNARROW_LIGHT);
        set("FONT_DETAILS_HEADER"     , GOTHAM_MEDIUM);
        set("FONT_DETAILS_SUBHEAD"    , ROONEY_REGULAR);
        set("FONT_DETAILS_TITLE"      , ROONEY_REGULAR);
        set("FONT_DETAILS_INFOS"      , ROONEY_LIGHT);

        // @formatter:on
    }

    //region Private static commom implementation.

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

    //endregion Private static commom implementation.
}
