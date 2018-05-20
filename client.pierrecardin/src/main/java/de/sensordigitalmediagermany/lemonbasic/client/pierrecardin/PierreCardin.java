package de.sensordigitalmediagermany.lemonbasic.client.pierrecardin;

import android.graphics.Color;
import android.util.Log;

import java.lang.reflect.Method;

@SuppressWarnings({"unchecked", "unused"})
public class PierreCardin
{
    private static final String LOGTAG = PierreCardin.class.getSimpleName();

    // @formatter:off
    private static final int COLOR_PCADIN_CONTENT = 0xffffffff;
    private static final int COLOR_PCADIN_GRAY    = 0xffb4b4b4;
    private static final int COLOR_PCADIN_LTGRAY  = 0xfff5f5f5;
    // @formatter:on

    // @formatter:off
    private static final String GOTHAM_BOLD          = "fonts/Gotham-Bold.otf";
    private static final String GOTHAM_LIGHT         = "fonts/Gotham-Light.otf";
    private static final String GOTHAM_MEDIUM        = "fonts/Gotham-Medium.otf";
    private static final String GOTHAMNARROW_LIGHT   = "fonts/GothamNarrow-Light.otf";
    private static final String ROONEY_LIGHT         = "fonts/Rooney-Light.otf";
    private static final String ROONEY_MEDIUM        = "fonts/Rooney-Medium.otf";
    private static final String ROONEY_REGULAR       = "fonts/Rooney-Regular.otf";
    private static final String FUTURA_LIGHT_REG     = "fonts/Futura-Light-Regular.otf";
    private static final String FUTURA_BOOK          = "fonts/Futura-Book.otf";
    private static final String SOURCE_SERIF_LIGHT   = "fonts/SourceSerifPro-Light.otf";
    private static final String SOURCE_SERIF_BOLD    = "fonts/SourceSerifPro-Bold.otf";
    // @formatter:on

    public static void initialize()
    {
        Log.d(LOGTAG, "initialize:");

        set("SYSTEM_USER_NAME", "PIERRECARDIN");
        set("APIURL", "https://lemon-mobile-learning.com/lemon-basic/ws/");

        // @formatter:off

        set("SETTINGS_IMAGE_SIZE"      , isTablet() ? 60 : 40);

        set("COLOR_NAVIBAR"           , COLOR_PCADIN_GRAY);
        set("COLOR_TABBAR"            , COLOR_PCADIN_LTGRAY);
        set("COLOR_CONTENT"           , COLOR_PCADIN_CONTENT);
        set("COLOR_FRAMES"            , COLOR_PCADIN_LTGRAY);
        set("COLOR_DIALOG_BACK"       , COLOR_PCADIN_LTGRAY);
        set("COLOR_DIALOG_TITLE"      , Color.BLACK);
        set("COLOR_DIALOG_INFOS"      , Color.BLACK);
        set("COLOR_BUTTON_TEXT"       , COLOR_PCADIN_GRAY);
        set("COLOR_BUTTON_BACK"       , Color.BLACK);
        set("COLOR_ALERT_BACK"        , COLOR_PCADIN_LTGRAY);
        set("COLOR_SETTINGS_HEADERS"  , Color.BLACK);
        set("COLOR_SETTINGS_LIST"     , Color.WHITE);
        set("COLOR_SETTINGS_LIST_SEL" , Color.BLACK);
        set("COLOR_DETAIL_TITLE"      , Color.BLACK);
        set("COLOR_PROGRESS_DONE"     , Color.BLACK);
        set("COLOR_PROGRESS_NEED"     , Color.WHITE);

        set("ASSET_SETTINGS_ASPECT"   , isWideScreen() ? 5.00f : isTablet() ? 3.00f : 3.00f);
        set("ASSET_THUMBNAIL_ASPECT"  , isTablet() ? 1.30f : 1.00f);
        set("ASSET_DETAIL_ASPECT"     , isWideScreen() ? 4.00f : isTablet() ? 3.20f : 3.20f);
        set("ASSET_COURSE_ASPECT"     , isTablet() ? 5.90f : 3.40f);
        set("ASSET_BANNER_ASPECT"     , isWideScreen() ? 4.80f : isTablet() ? 3.20f : 2.20f);

        set("FONT_DIALOG_TITLE"       , FUTURA_BOOK);
        set("FONT_DIALOG_INFOS"       , SOURCE_SERIF_LIGHT);
        set("FONT_DIALOG_EDITS"       , FUTURA_LIGHT_REG);
        set("FONT_DIALOG_BUTTON"      , FUTURA_BOOK);

        set("FONT_GENERIC_BUTTON"     , FUTURA_BOOK);
        set("FONT_GENERIC_EDIT"       , FUTURA_LIGHT_REG);
        set("FONT_ASSET_TITLE"        , FUTURA_LIGHT_REG);
        set("FONT_ASSET_SUMMARY"      , FUTURA_LIGHT_REG);
        set("FONT_SLIDER_ALL"         , FUTURA_LIGHT_REG);
        set("FONT_SCALED_BUTTON"      , FUTURA_LIGHT_REG);
        set("FONT_TABBAR_ENTRY"       , FUTURA_LIGHT_REG);
        set("FONT_CATEGORY_TITLE"     , FUTURA_LIGHT_REG);
        set("FONT_SETTINGS_HEADER"    , FUTURA_BOOK);
        set("FONT_SETTINGS_SUBHEAD"   , FUTURA_BOOK);
        set("FONT_SETTINGS_INFOS"     , FUTURA_LIGHT_REG);
        set("FONT_SETTINGS_VERSION"   , FUTURA_LIGHT_REG);
        set("FONT_SETTINGS_LIST"      , FUTURA_BOOK);
        set("FONT_DETAILS_HEADER"     , FUTURA_BOOK);
        set("FONT_DETAILS_SUBHEAD"    , FUTURA_LIGHT_REG);
        set("FONT_DETAILS_TITLE"      , SOURCE_SERIF_BOLD);
        set("FONT_DETAILS_INFOS"      , SOURCE_SERIF_LIGHT);

        set("FS_DIALOG_TITLE"         , isTablet() ? 20 : 16);
        set("FS_DIALOG_EDIT"          , isTablet() ? 18 : 16);
        set("FS_DIALOG_BUTTON"        , isTablet() ? 16 : 14);
        set("FS_DIALOG_INFO"          , isTablet() ? 16 : 14);
        set("FS_GENERIC_BUTTON"       , isTablet() ? 16 : 14);
        set("FS_GENERIC_EDIT"         , isTablet() ? 20 : 18);
        set("FS_SCALED_BUTTON"        , isTablet() ? 14 : 13);
        set("FS_NAVI_MENU"            , isTablet() ? 20 : 14);
        set("FS_CATEGORY_TITLE"       , isTablet() ? 26 : 18);
        set("FS_POPUP_MENU"           , isTablet() ? 18 : 16);
        set("FS_DEBUG_VERSION"        , isTablet() ? 10 :  8);
        set("FS_TABBAR_ENTRY"         , isTablet() ? 20 : 18);
        set("FS_SLIDER_CATEGORY"      , isTablet() ? 18 : 16);
        set("FS_SLIDER_SHOWMORE"      , isTablet() ? 14 : 12);
        set("FS_BANNER_TITLE"         , isTablet() ? 20 : 18);
        set("FS_BANNER_INFO"          , isTablet() ? 26 : 20);
        set("FS_BANNER_BUTTON"        , isTablet() ? 16 : 14);
        set("FS_ASSET_TITLE"          , isTablet() ? 13 : 11);
        set("FS_ASSET_INFO"           , isTablet() ? 18 : 16);
        set("FS_ASSET_OWNED"          , isTablet() ? 12 : 11);
        set("FS_COINS_COINS"          , isTablet() ? 56 : 36);
        set("FS_COINS_PRICE"          , isTablet() ? 26 : 22);
        set("FS_COINS_BUTTONS"        , isTablet() ? 22 : 20);
        set("FS_DETAIL_HEADER"        , isTablet() ? 16 : 14);
        set("FS_DETAIL_SUBHEAD"       , isTablet() ? 30 : 20);
        set("FS_DETAIL_TITLE"         , isTablet() ? 15 : 12);
        set("FS_DETAIL_INFOS"         , isTablet() ? 13 : 11);
        set("FS_DETAIL_SPECS"         , isTablet() ? 15 : 12);
        set("FS_BUY_TITLE"            , isTablet() ? 16 : 14);
        set("FS_BUY_HEADER"           , isTablet() ? 24 : 22);
        set("FS_BUY_PRICE"            , isTablet() ? 48 : 40);
        set("FS_SETTINGS_TITLE"       , isTablet() ? 17 : 16);
        set("FS_SETTINGS_INFO"        , isTablet() ? 15 : 14);
        set("FS_SETTINGS_BACK"        , isTablet() ? 15 : 12);
        set("FS_SETTINGS_LIST"        , isTablet() ? 16 : 14);
        set("FS_SETTINGS_MORE"        , isTablet() ? 24 : 22);
        set("FS_TRAINING_TITLE"       , isTablet() ? 46 : 40);
        set("FS_TRAINING_INFO"        , isTablet() ? 20 : 18);
        set("FS_TRAINING_START"       , isTablet() ? 28 : 24);
        set("FS_QUESTIONS_TITLE"      , isTablet() ? 18 : 16);
        set("FS_QUESTIONS_QUESTION"   , isTablet() ? 36 : 32);
        set("FS_QUESTIONS_ANSWER"     , isTablet() ? 20 : 16);
        set("FS_OVERVIEW_NUMBER"      , isTablet() ? 24 : 22);

        set("CORNER_RADIUS_BUTTON"    , 0);
        set("CORNER_RADIUS_FRAMES"    , 0);
        set("CORNER_RADIUS_BIGBUT"    , 0);
        set("CORNER_RADIUS_OVERLAY"   , 0);
        set("CORNER_RADIUS_DIALOG"    , 0);
        set("CORNER_RADIUS_ASSETS"    , 0);

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
