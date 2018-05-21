package de.sensordigitalmediagermany.lemonbasic.client.raineralbers;

import android.graphics.Color;
import android.graphics.Rect;
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

        set("COLOR_NAVIBAR", COLOR_RALBERS_NAVIBAR);
        set("COLOR_TABBAR", COLOR_RALBERS_TABBAR);
        set("COLOR_CONTENT", COLOR_RALBERS_CONTENT);
        set("COLOR_FRAMES", COLOR_RALBERS_FRAMES);
        set("COLOR_ASSETS", Color.WHITE);
        set("COLOR_DIALOG_BACK", COLOR_RALBERS_DIALOGS);
        set("COLOR_DIALOG_TITLE", Color.WHITE);
        set("COLOR_DIALOG_INFOS", Color.WHITE);
        set("COLOR_BUTTON_TEXT", COLOR_RALBERS_BUTTONTEXT);
        set("COLOR_BUTTON_BACK", COLOR_RALBERS_LTBLUE);
        set("COLOR_ALERT_BACK", COLOR_RALBERS_DIALOGS);
        set("COLOR_SETTINGS_HEADERS", COLOR_RALBERS_DKBLUE);
        set("COLOR_SETTINGS_LIST", COLOR_RALBERS_NAVIBAR);
        set("COLOR_SETTINGS_LIST_SEL", COLOR_RALBERS_LTBLUE);
        set("COLOR_DETAIL_TITLE", COLOR_RALBERS_LTBLUE);
        set("COLOR_PROGRESS_DONE", Color.GREEN);
        set("COLOR_PROGRESS_NEED", Color.YELLOW);

        set("ASSET_SETTINGS_ASPECT", isWideScreen() ? 5.00f : isTablet() ? 3.5f : 2.0f);
        set("ASSET_THUMBNAIL_ASPECT", 1.9f);
        set("ASSET_DETAIL_ASPECT", 3.0f);
        set("ASSET_COURSE_ASPECT", 3.5f);
        set("ASSET_BANNER_ASPECT", 3.2f);

        set("FONT_DIALOG_TITLE", GOTHAM_BOLD);
        set("FONT_DIALOG_INFOS", GOTHAM_LIGHT);
        set("FONT_DIALOG_EDITS", GOTHAM_LIGHT);
        set("FONT_DIALOG_BUTTON", GOTHAM_BOLD);
        set("FONT_GENERIC_BUTTON", GOTHAM_BOLD);
        set("FONT_GENERIC_EDIT", GOTHAM_LIGHT);
        set("FONT_ASSET_TITLE", GOTHAM_BOLD);
        set("FONT_ASSET_SUMMARY", GOTHAMNARROW_LIGHT);
        set("FONT_SLIDER_ALL", GOTHAMNARROW_LIGHT);
        set("FONT_SCALED_BUTTON", ROONEY_MEDIUM);
        set("FONT_TABBAR_ENTRY", ROONEY_MEDIUM);
        set("FONT_CATEGORY_TITLE", GOTHAM_BOLD);
        set("FONT_SETTINGS_HEADER", GOTHAM_BOLD);
        set("FONT_SETTINGS_SUBHEAD", GOTHAM_MEDIUM);
        set("FONT_SETTINGS_INFOS", GOTHAMNARROW_LIGHT);
        set("FONT_SETTINGS_VERSION", GOTHAMNARROW_LIGHT);
        set("FONT_SETTINGS_LIST", GOTHAMNARROW_LIGHT);
        set("FONT_DETAILS_HEADER", GOTHAM_MEDIUM);
        set("FONT_DETAILS_SUBHEAD", ROONEY_REGULAR);
        set("FONT_DETAILS_TITLE", ROONEY_REGULAR);
        set("FONT_DETAILS_INFOS", ROONEY_LIGHT);

        set("FS_DIALOG_TITLE", isTablet() ? 18 : 16);
        set("FS_DIALOG_EDIT", isTablet() ? 18 : 16);
        set("FS_DIALOG_BUTTON", isTablet() ? 16 : 14);
        set("FS_DIALOG_INFO", isTablet() ? 16 : 14);
        set("FS_GENERIC_BUTTON", isTablet() ? 16 : 14);
        set("FS_GENERIC_EDIT", isTablet() ? 20 : 18);
        set("FS_SCALED_BUTTON", isTablet() ? 18 : 16);
        set("FS_NAVI_MENU", isTablet() ? 20 : 14);
        set("FS_CATEGORY_TITLE", isTablet() ? 26 : 18);
        set("FS_POPUP_MENU", isTablet() ? 18 : 16);
        set("FS_DEBUG_VERSION", isTablet() ? 13 : 12);
        set("FS_TABBAR_ENTRY", isTablet() ? 20 : 18);
        set("FS_SLIDER_CATEGORY", isTablet() ? 18 : 16);
        set("FS_SLIDER_SHOWMORE", isTablet() ? 14 : 12);
        set("FS_BANNER_TITLE", isTablet() ? 20 : 18);
        set("FS_BANNER_INFO", isTablet() ? 20 : 18);
        set("FS_BANNER_BUTTON", isTablet() ? 16 : 14);
        set("FS_ASSET_TITLE", isTablet() ? 13 : 12);
        set("FS_ASSET_INFO", isTablet() ? 13 : 12);
        set("FS_ASSET_OWNED", isTablet() ? 12 : 11);
        set("FS_COINS_COINS", isTablet() ? 56 : 36);
        set("FS_COINS_PRICE", isTablet() ? 26 : 22);
        set("FS_COINS_BUTTONS", isTablet() ? 22 : 20);
        set("FS_DETAIL_HEADER", isTablet() ? 16 : 14);
        set("FS_DETAIL_SUBHEAD", isTablet() ? 24 : 22);
        set("FS_DETAIL_TITLE", isTablet() ? 16 : 14);
        set("FS_DETAIL_INFOS", isTablet() ? 15 : 12);
        set("FS_DETAIL_SPECS", isTablet() ? 15 : 12);
        set("FS_BUY_TITLE", isTablet() ? 16 : 14);
        set("FS_BUY_HEADER", isTablet() ? 24 : 22);
        set("FS_BUY_PRICE", isTablet() ? 48 : 40);
        set("FS_SETTINGS_TITLE", isTablet() ? 17 : 16);
        set("FS_SETTINGS_INFO", isTablet() ? 15 : 14);
        set("FS_SETTINGS_BACK", isTablet() ? 15 : 12);
        set("FS_SETTINGS_LIST", isTablet() ? 22 : 20);
        set("FS_SETTINGS_MORE", isTablet() ? 30 : 28);
        set("FS_TRAINING_TITLE", isTablet() ? 46 : 40);
        set("FS_TRAINING_INFO", isTablet() ? 20 : 18);
        set("FS_TRAINING_START", isTablet() ? 28 : 24);
        set("FS_QUESTIONS_TITLE", isTablet() ? 18 : 16);
        set("FS_QUESTIONS_QUESTION", isTablet() ? 36 : 32);
        set("FS_QUESTIONS_ANSWER", isTablet() ? 20 : 16);
        set("FS_OVERVIEW_NUMBER", isTablet() ? 24 : 22);

        set("CORNER_RADIUS_BUTTON", 3);
        set("CORNER_RADIUS_FRAMES", 8);
        set("CORNER_RADIUS_BIGBUT", 8);
        set("CORNER_RADIUS_OVERLAY", 10);
        set("CORNER_RADIUS_DIALOG", 16);
        set("CORNER_RADIUS_ASSETS", 16);

        setResId("notifyIconLargeRes", isTablet()
                ? R.mipmap.lem_t_rainer_albers
                : R.mipmap.lem_t_rainer_albers);

        setResId("splashScreenRes", isTablet()
                ? -1
                : R.drawable.lem_t_ipho_ralbers_splashscreen);

        setResId("mainScreenRes", isTablet()
                ? R.drawable.lem_t_ipad_ralbers_startscreen
                : R.drawable.lem_t_ipho_ralbers_startscreen);

        setResId("closeButtonRes", isTablet()
                ? R.drawable.lem_t_iany_ralbers_kreuz
                : R.drawable.lem_t_iany_ralbers_kreuz);

        setResId("confirmedIconRes", isTablet()
                ? R.drawable.lem_t_iany_ralbers_buy_confirmed
                : R.drawable.lem_t_iany_ralbers_buy_confirmed);

        setResId("readMarkerRes", isTablet()
                ? R.drawable.lem_t_iany_ralbers_haken
                : R.drawable.lem_t_iany_ralbers_haken);

        setResId("courseMarkerRes", isTablet()
                ? R.drawable.lem_t_iany_ralbers_course_symbol
                : R.drawable.lem_t_iany_ralbers_course_symbol);

        setResId("contentScreenButtonProfileRes", isTablet()
                ? R.drawable.lem_t_ipad_ralbers_profile
                : R.drawable.lem_t_ipho_ralbers_profile);

        setRect("contentScreenButtonProfileRect", isTablet()
                ? new Rect(100, 22, 500, 82)
                : new Rect(100, 22, 160, 82));

        setResId("contentScreenHeaderRes", isTablet()
                ? R.drawable.lem_t_ipad_ralbers_menueoben
                : R.drawable.lem_t_ipho_ralbers_menueoben);

        setResId("arrowWhiteLeftOnRes", isTablet()
                ? R.drawable.lem_t_iany_ralbers_pfeillinks_weiss
                : R.drawable.lem_t_iany_ralbers_pfeillinks_weiss);

        setResId("arrowDarkLeftOnRes", isTablet()
                ? R.drawable.lem_t_iany_ralbers_pfeillinks_dunkel
                : R.drawable.lem_t_iany_ralbers_pfeillinks_dunkel);

        setResId("contentScreenButtonBackOnRes", isTablet()
                ? R.drawable.lem_t_iany_ralbers_back_on
                : R.drawable.lem_t_iany_ralbers_back_on);

        setResId("contentScreenButtonBackOffRes", isTablet()
                ? R.drawable.lem_t_iany_ralbers_back_off
                : R.drawable.lem_t_iany_ralbers_back_off);

        setRect("contentScreenBackIconRect", isTablet()
                ? new Rect(30, 22, 90, 82)
                : new Rect(30, 22, 90, 82));

        setRect("contentScreenBackButtonRect", isTablet()
                ? new Rect(10, 10, 90, 90)
                : new Rect(10, 10, 90, 90));

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

    private static void setRect(String field, Rect rect)
    {
        try
        {
            screens.getField(field).set(null, rect);

            Log.d(LOGTAG,"set: field=" + field + " rect=" + rect + " ok");
        }
        catch (Exception ignore)
        {
            Log.e(LOGTAG,"set: field=" + field + " rect=" + rect + " failed!");
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
