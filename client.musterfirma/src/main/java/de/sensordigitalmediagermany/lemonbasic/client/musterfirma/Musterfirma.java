package de.sensordigitalmediagermany.lemonbasic.client.musterfirma;

import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;

import java.lang.reflect.Method;

@SuppressWarnings({"unchecked", "unused"})
public class Musterfirma
{
    private static final String LOGTAG = Musterfirma.class.getSimpleName();

    // @formatter:off
    private static final int COLOR_MUFIRM_RED         = 0xffe20000;
    private static final int COLOR_MUFIRM_GREEN       = 0xff8cc800;
    private static final int COLOR_MUFIRM_YELLOW      = 0xffffc700;
    private static final int COLOR_MUFIRM_NAVIBAR     = 0xffff00ff;

    // background
    private static final int COLOR_MUFIRM_BACKGROUND   = 0xffffffff;
    private static final int COLOR_MUFIRM_GREYCOLOR1   = 0xfff5f5f5;
    private static final int COLOR_MUFIRM_GREYCOLOR2   = 0xffE6E6E6;
    private static final int COLOR_MUFIRM_GREYCOLOR3   = 0xffb4b4b4;
    private static final int COLOR_MUFIRM_GREYCOLOR4   = 0xff6e6e6e;

    private static final int COLOR_MUFIRM_BLUECOLOR1   = 0xff0078dc;
    private static final int COLOR_MUFIRM_BLUECOLOR2   = 0xff0094ff;
    private static final int COLOR_MUFIRM_BLUECOLOR3   = 0xff0087eb;
    // @formatter:on

    // @formatter:off
    private static final String OPENSANS_BOLD        = "fonts/OpenSans-Bold.ttf";
    private static final String OPENSANS_LIGHT       = "fonts/OpenSans-Light.ttf";
    private static final String OPENSANS_MEDIUM      = "fonts/OpenSans-Regular.ttf";
    private static final String OPENSANS_REGULAR     = "fonts/OpenSans-Regular.ttf";
    private static final String OPENSANSNARROW_LIGHT = "fonts/OpenSans-Light.ttf";
    // @formatter:on

    public static void initialize()
    {
        Log.d(LOGTAG, "initialize:");

        set("SYSTEM_USER_NAME", "MUSTERFIRMA");
        set("APIURL", "https://lemon-mobile-learning.com/lemon-basic/ws/");

        // @formatter:off

        set("SETTINGS_IMAGE_SIZE", isTablet() ? 100 : 90);
        set("COURSE_ICON_SIZE",    isTablet() ?  80 : 64);

        set("COLOR_NAVIBAR"           , COLOR_MUFIRM_GREYCOLOR2);
        set("COLOR_TABBAR"            , COLOR_MUFIRM_GREYCOLOR1);
        set("COLOR_TABBAR_ACT_TEXT"   , COLOR_MUFIRM_BLUECOLOR2);
        set("COLOR_TABBAR_ACT_ICON"   , COLOR_MUFIRM_BLUECOLOR2);
        set("COLOR_CONTENT"           , COLOR_MUFIRM_BACKGROUND);
        set("COLOR_FRAMES"            , COLOR_MUFIRM_GREYCOLOR1);
        set("COLOR_ASSETS"            , COLOR_MUFIRM_GREYCOLOR2);
        set("COLOR_DIALOG_BACK"       , COLOR_MUFIRM_BLUECOLOR3);
        set("COLOR_DIALOG_TITLE"      , Color.WHITE);
        set("COLOR_DIALOG_INFOS"      , Color.WHITE);

        set("COLOR_SCALED_TEXT"       , Color.WHITE);
        set("COLOR_BUTTON_TEXT"       , COLOR_MUFIRM_GREYCOLOR3);
        set("COLOR_BUTTON_DIALOG"     , COLOR_MUFIRM_BLUECOLOR2);
        set("COLOR_BUTTON_BACK"       , COLOR_MUFIRM_GREEN);

        set("COLOR_ALERT_BACK"        , COLOR_MUFIRM_BLUECOLOR3);
        set("COLOR_SETTINGS_HEADERS"  , COLOR_MUFIRM_BLUECOLOR1);
        set("COLOR_SETTINGS_LIST"     , Color.TRANSPARENT);
        set("COLOR_SETTINGS_LIST_SEL" , COLOR_MUFIRM_BLUECOLOR2);
        set("COLOR_DETAIL_TITLE"      , COLOR_MUFIRM_BLUECOLOR2);
        set("COLOR_PROGRESS_DONE"     , COLOR_MUFIRM_GREEN);
        set("COLOR_PROGRESS_NEED"     , Color.YELLOW);
        set("COLOR_TOPBANNER_BG"      , COLOR_MUFIRM_BLUECOLOR2);
        set("COLOR_CATEGORY_HEAD"     , Color.BLACK);
        set("COLOR_SEPA_LINE"         , COLOR_MUFIRM_BLUECOLOR2);

        set("ASSET_SETTINGS_ASPECT"   , isWideScreen() ? 5.0f : isTablet() ? 3.5f : 2.0f);
        set("ASSET_THUMBNAIL_ASPECT"  , 1.9f);
        set("ASSET_DETAIL_ASPECT"     , isWideScreen() ? 3.8f : isTablet() ? 3.2f : 2.0f);
        set("ASSET_COURSE_ASPECT"     , 3.5f);
        set("ASSET_BANNER_ASPECT"     , isTablet() ? 3.6f : 2.0f);

        set("FONT_DIALOG_TITLE"       , OPENSANS_BOLD);
        set("FONT_DIALOG_INFOS"       , OPENSANS_LIGHT);
        set("FONT_DIALOG_EDITS"       , OPENSANS_LIGHT);
        set("FONT_DIALOG_BUTTON"      , OPENSANS_BOLD);
        set("FONT_GENERIC_BUTTON"     , OPENSANS_BOLD);
        set("FONT_GENERIC_EDIT"       , OPENSANS_LIGHT);
        set("FONT_ASSET_TITLE"        , OPENSANS_BOLD);
        set("FONT_ASSET_SUMMARY"      , OPENSANSNARROW_LIGHT);
        set("FONT_SLIDER_CAT"         , OPENSANS_BOLD);
        set("FONT_SLIDER_ALL"         , OPENSANS_MEDIUM);
        set("FONT_SCALED_BUTTON"      , OPENSANS_MEDIUM);
        set("FONT_TABBAR_ENTRY"       , OPENSANS_MEDIUM);
        set("FONT_CATEGORY_TITLE"     , OPENSANS_BOLD);
        set("FONT_SETTINGS_HEADER"    , OPENSANS_BOLD);
        set("FONT_SETTINGS_SUBHEAD"   , OPENSANS_MEDIUM);
        set("FONT_SETTINGS_INFOS"     , OPENSANSNARROW_LIGHT);
        set("FONT_SETTINGS_VERSION"   , OPENSANSNARROW_LIGHT);
        set("FONT_SETTINGS_LIST"      , OPENSANSNARROW_LIGHT);
        set("FONT_DETAILS_HEADER"     , OPENSANS_MEDIUM);
        set("FONT_DETAILS_SUBHEAD"    , OPENSANS_REGULAR);
        set("FONT_DETAILS_TITLE"      , OPENSANS_REGULAR);
        set("FONT_DETAILS_INFOS"      , OPENSANS_LIGHT);

        set("FS_DIALOG_TITLE"         , isTablet() ? 18 : 16);
        set("FS_DIALOG_EDIT"          , isTablet() ? 18 : 16);
        set("FS_DIALOG_BUTTON"        , isTablet() ? 16 : 14);
        set("FS_DIALOG_INFO"          , isTablet() ? 16 : 14);
        set("FS_GENERIC_BUTTON"       , isTablet() ? 16 : 13);
        set("FS_GENERIC_EDIT"         , isTablet() ? 20 : 18);
        set("FS_SCALED_BUTTON"        , isTablet() ? 16 : 14);
        set("FS_NAVI_MENU"            , isTablet() ? 20 : 14);
        set("FS_CATEGORY_TITLE"       , isTablet() ? 26 : 18);
        set("FS_POPUP_MENU"           , isTablet() ? 18 : 16);
        set("FS_DEBUG_VERSION"        , isTablet() ? 12 : 10);
        set("FS_TABBAR_ENTRY"         , isTablet() ? 20 : 18);
        set("FS_SLIDER_CATEGORY"      , isTablet() ? 18 : 16);
        set("FS_SLIDER_SHOWMORE"      , isTablet() ? 14 : 12);
        set("FS_BANNER_TITLE"         , isTablet() ? 20 : 18);
        set("FS_BANNER_INFO"          , isTablet() ? 20 : 18);
        set("FS_BANNER_BUTTON"        , isTablet() ? 16 : 14);
        set("FS_ASSET_TITLE"          , isTablet() ? 13 : 12);
        set("FS_ASSET_INFO"           , isTablet() ? 13 : 12);
        set("FS_ASSET_OWNED"          , isTablet() ? 12 : 11);
        set("FS_COINS_COINS"          , isTablet() ? 56 : 36);
        set("FS_COINS_PRICE"          , isTablet() ? 26 : 22);
        set("FS_COINS_BUTTONS"        , isTablet() ? 22 : 20);
        set("FS_DETAIL_HEADER"        , isTablet() ? 16 : 14);
        set("FS_DETAIL_SUBHEAD"       , isTablet() ? 24 : 22);
        set("FS_DETAIL_TITLE"         , isTablet() ? 16 : 14);
        set("FS_DETAIL_INFOS"         , isTablet() ? 15 : 12);
        set("FS_DETAIL_SPECS"         , isTablet() ? 15 : 12);
        set("FS_BUY_TITLE"            , isTablet() ? 16 : 14);
        set("FS_BUY_HEADER"           , isTablet() ? 24 : 22);
        set("FS_BUY_PRICE"            , isTablet() ? 48 : 40);
        set("FS_SETTINGS_TITLE"       , isTablet() ? 17 : 16);
        set("FS_SETTINGS_INFO"        , isTablet() ? 15 : 14);
        set("FS_SETTINGS_BACK"        , isTablet() ? 15 : 12);
        set("FS_SETTINGS_LIST"        , isTablet() ? 22 : 20);
        set("FS_SETTINGS_MORE"        , isTablet() ? 30 : 28);
        set("FS_TRAINING_TITLE"       , isTablet() ? 46 : 40);
        set("FS_TRAINING_INFO"        , isTablet() ? 20 : 18);
        set("FS_TRAINING_START"       , isTablet() ? 28 : 24);
        set("FS_QUESTIONS_TITLE"      , isTablet() ? 18 : 16);
        set("FS_QUESTIONS_QUESTION"   , isTablet() ? 36 : 32);
        set("FS_QUESTIONS_ANSWER"     , isTablet() ? 20 : 16);
        set("FS_OVERVIEW_NUMBER"      , isTablet() ? 24 : 22);

        set("CORNER_RADIUS_BUTTON"    ,  3);
        set("CORNER_RADIUS_FRAMES"    ,  8);
        set("CORNER_RADIUS_BIGBUT"    , 10);
        set("CORNER_RADIUS_OVERLAY"   , 10);
        set("CORNER_RADIUS_DIALOG"    , 16);
        set("CORNER_RADIUS_ASSETS"    , 16);
        set("CORNER_RADIUS_TOPBANNER" , 20);

        setResId("notifyIconLargeRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.mipmap.ic_launcher
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.mipmap.ic_launcher);

        setResId("splashScreenRes", isTablet()
                ? -1
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_ipho_mufirma_splashscreen);

        setResId("mainScreenRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_ipad_mufirma_startscreen
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_ipho_mufirma_splashscreen);

        setResId("closeButtonRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_kreuz
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_kreuz);

        setResId("confirmedIconRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_buy_confirmed
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_buy_confirmed);

        setResId("readMarkerRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_haken
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_haken);

        setResId("courseMarkerRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_course_symbol
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_course_symbol);

        setResId("contentScreenButtonProfileRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_ipad_mufirma_profile
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_ipho_mufirma_profile);

        setRect("contentScreenButtonProfileRect",isTablet()
                ? new Rect(1555, 22, 1900, 82)
                : new Rect(493, 10, 553, 70));

        setResId("contentScreenHeaderRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_ipad_mufirma_menueoben
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_ipho_mufirma_menueoben);

        setResId("arrowWhiteLeftOnRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_pfeillinks_weiss
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_pfeillinks_weiss);

        setResId("arrowDarkLeftOnRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_pfeillinks_dunkel
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_ralbers_pfeillinks_dunkel);

        setResId("contentScreenButtonBackOnRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_mufiram_back_on
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_mufiram_back_on);

        setResId("contentScreenButtonBackOffRes", isTablet()
                ? de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_mufiram_back_off
                : de.sensordigitalmediagermany.lemonbasic.client.musterfirma.R.drawable.lem_t_iany_mufiram_back_off);

        setRect("contentScreenBackIconRect", isTablet()
                ? new Rect(30, 22, 90, 82)
                : new Rect(30, 10, 90, 70));

        setRect("contentScreenBackButtonRect",isTablet()
                ? new Rect(10, 10, 90, 90)
                : new Rect(10, 10, 90, 70));

        setRect("contentScreenNavigationRect",isTablet()
                ? new Rect(100, 10, 700, 90)
                : null);

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
            ignore.printStackTrace();
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
