package de.sensordigitalmediagermany.lemonbasic.generic;

import android.graphics.Color;
import android.util.Log;

import java.lang.reflect.Method;

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "UnusedAssignment", "unchecked"})
public class Defines
{
    private static final String LOGTAG = Defines.class.getSimpleName();

    //
    // Debug stuff.
    //

    public static final boolean isDezi = false;

    public static final String DEBUG_VERSION = "1.1 (24)";

    //
    // Client resolution. Checks via reflection, which client module is available.
    //

    // @formatter:off
    protected static final boolean isPierreCardin = getInitClientClass("pierrecardin.PierreCardin");
    protected static final boolean isRainerAlbers = getInitClientClass("raineralbers.RainerAlbers");
    protected static final boolean isMusterfirma  = getInitClientClass("musterfirma.Musterfirma");
    // @formatter:on

    //
    // App version resolution.
    //

    // @formatter:off
    public static final boolean isBasic   = isPierreCardin || isMusterfirma;
    public static final boolean isTrainer = isRainerAlbers;
    // @formatter:on

    //
    // Rest API database access selectors.
    //

    // @formatter:off
    public static String APIURL = "undefined!";
    public static String SYSTEM_USER_NAME  = "undefined!";
    public static String SYSTEM_USER_PARAM = isBasic ? "systemuserName" : isTrainer ? "trainerName" : "undefined!";
    public static String APP_STORE_PUBLIC_KEY = "undefined!";
    // @formatter:on

    //
    // Client specific styles and variants.
    //

    // @formatter:off
    public static final boolean isGiveAway           = isPierreCardin || isMusterfirma;
    public static final boolean isLoginButton        = isRainerAlbers || isMusterfirma;
    public static final boolean isSimpleLogin        = isPierreCardin || isMusterfirma;
    public static final boolean isRegistration       = isRainerAlbers || isMusterfirma;
    public static final boolean isUserMenu           = isRainerAlbers;
    public static final boolean isTabBar             = isPierreCardin || isMusterfirma;
    public static final boolean isTopBanner          = isPierreCardin || isMusterfirma;
    public static final boolean isTopBannerFull      = isMusterfirma;
    public static final boolean isTopBannerButton    = isPierreCardin;
    public static final boolean isCategoryMenu       = isRainerAlbers;
    public static final boolean isRoundedAsset       = isRainerAlbers || isMusterfirma;
    public static final boolean isOverlayAsset       = isPierreCardin;
    public static final boolean isSectionDividers    = isPierreCardin || isMusterfirma;
    public static final boolean isFlatEdits          = isPierreCardin || isMusterfirma;
    public static final boolean isDialogTextCenter   = isRainerAlbers || isMusterfirma;
    public static final boolean isCompactDetails     = isPierreCardin;
    public static final boolean isCompactSettings    = isPierreCardin;
    public static final boolean isDefaultButtonTrans = isPierreCardin;
    public static final boolean isHintsAllCaps       = isPierreCardin;
    public static final boolean isButtonAllCaps      = isPierreCardin;
    public static final boolean isHeadersAllCaps     = isPierreCardin || isRainerAlbers || isMusterfirma;
    public static final boolean isInfosAllCaps       = isPierreCardin;
    public static final boolean isSliderAllCaps      = isMusterfirma;
    public static final boolean isUserAllCaps        = isMusterfirma;
    public static final boolean isAskDownload        = isPierreCardin;
    public static final boolean isCourseIcon         = isRainerAlbers || isMusterfirma;
    public static final boolean isLoadedIcon         = isRainerAlbers || isMusterfirma;
    public static final boolean isStatusIcon         = isPierreCardin;
    public static final boolean isKaysNumbers        = isPierreCardin || isRainerAlbers || isMusterfirma;
    public static final boolean isKaysHorzScroll     = isPierreCardin || isMusterfirma;
    public static final boolean isAutoRefresh        = isPierreCardin;
    public static final boolean isDeleteCache        = isPierreCardin;
    public static final boolean isLoadAll            = isPierreCardin || isMusterfirma;
    public static final boolean isPDFExternal        = isPierreCardin;
    public static final boolean isFAQMenu            = isPierreCardin;
    public static final boolean isImpressumMenu      = isPierreCardin;
    public static final boolean isCategoryDownload   = false;
    public static final boolean isPDFZoomable        = false;
    public static final boolean isSoundSettings      = false;
    public static final boolean isAutoRefreshInfo    = false;
    public static final boolean isSolidButton        = isMusterfirma;
    public static final boolean isTypeIconTopLeft    = isMusterfirma;
    public static final boolean isBasicLayout = isMusterfirma;
    public static final boolean isDetailFeedback     = isMusterfirma;
    // @formatter:on

    //
    // Fixed values.
    //

    public static final int AUTO_REFRESH_SECONDS = 5 * 60;
    public static final int SETTINGS_MAX_ENTRIES = 100;

    public static final int TRAINING_NUM_QUESTIONS = 4;
    public static final int SLIDER_MAX_COLUMNS = 6;

    public static final int ASSETS_NUM_COLUMNS = Simple.isTablet() ? 4 : 2;
    public static final int RESULTS_NUM_COLUMNS = Simple.isTablet() ? 8 : 6;

    public static final int MIN_EMS_DIALOGS = Simple.isTablet() ? 16 : 16;
    public static final int MAX_EMS_DIALOGS = Simple.isTablet() ? 20 : 16;

    public static final int CONTENT_TYPE_PDF = 1;
    public static final int CONTENT_TYPE_VIDEO = 2;
    public static final int CONTENT_TYPE_AUDIO = 3;
    public static final int CONTENT_TYPE_ZIP = 4;

    //
    // Colors.
    //

    // @formatter:off
    public static final int COLOR_SENSOR_LTBLUE      = 0xff657995;
    public static final int COLOR_SENSOR_DKBLUE      = 0xff3b4455;
    public static final int COLOR_SENSOR_GREEN       = 0xff54b23b;

    public static final int COLOR_SENSOR_DIALOGS     = 0xcc3b4455;
    public static final int COLOR_SENSOR_NAVIBAR     = 0xffedf0f4;
    public static final int COLOR_SENSOR_CONTENT     = 0xffb6c4d2;
    public static final int COLOR_SENSOR_FRAMES      = 0xffedeef1;
    public static final int COLOR_SENSOR_TABBAR      = 0xfff5f5f5;
    public static final int COLOR_SENSOR_BUTTONTEXT  = 0xfff5f5f5;

    public static final int COLOR_BUTTON_TOUCHED     = 0x88888888;
    public static final int COLOR_BACKGROUND_DIM     = 0x77000000;
    public static final int COLOR_QUESTIONS_SEP      = 0x11000000;
    public static final int COLOR_TV_FOCUS           = 0xffffcc00;

    public static int COLOR_NAVIBAR           = COLOR_SENSOR_NAVIBAR;    // client!
    public static int COLOR_TABBAR            = COLOR_SENSOR_TABBAR;     // client!
    public static int COLOR_TABBAR_ACT_TEXT   = COLOR_SENSOR_TABBAR;     // client!
    public static int COLOR_TABBAR_ACT_ICON   = COLOR_SENSOR_TABBAR;     // client!
    public static int COLOR_CONTENT           = COLOR_SENSOR_CONTENT;    // client!
    public static int COLOR_FRAMES            = COLOR_SENSOR_FRAMES;     // client!
    public static int COLOR_ASSETS            = Color.WHITE;             // client!
    public static int COLOR_DIALOG_BACK       = COLOR_SENSOR_DIALOGS;    // client!
    public static int COLOR_DIALOG_TITLE      = Color.WHITE;             // client!
    public static int COLOR_DIALOG_INFOS      = Color.WHITE;             // client!
    public static int COLOR_BUTTON_TEXT       = COLOR_SENSOR_BUTTONTEXT; // client!
    public static int COLOR_BUTTON_DIALOG     = COLOR_SENSOR_BUTTONTEXT; // client!
    public static int COLOR_BUTTON_BACK       = COLOR_SENSOR_LTBLUE;     // client!
    public static int COLOR_BUTTON_DISABLED   = Color.LTGRAY;            // client!
    public static int COLOR_ALERT_BACK        = COLOR_SENSOR_DIALOGS;    // client!
    public static int COLOR_SETTINGS_HEADERS  = COLOR_SENSOR_DKBLUE;     // client!
    public static int COLOR_SETTINGS_LIST     = COLOR_SENSOR_NAVIBAR;    // client!
    public static int COLOR_SETTINGS_LIST_SEL = COLOR_SENSOR_LTBLUE;     // client!
    public static int COLOR_DETAIL_TITLE      = COLOR_SENSOR_LTBLUE;     // client!
    public static int COLOR_PROGRESS_DONE     = Color.GREEN;             // client!
    public static int COLOR_PROGRESS_NEED     = Color.YELLOW;            // client!
    public static int COLOR_TOPBANNER_BG      = Color.TRANSPARENT;       // client!
    public static int COLOR_CATEGORY_HEAD     = Color.BLACK;             // client!
    public static int COLOR_SCALED_TEXT       = Color.WHITE;             // client!
    public static int COLOR_SEPA_LINE         = Color.BLACK;             // client!
    // @formatter:on

    //
    // Corner radius.
    //

    // @formatter:off
    public static int CORNER_RADIUS_BUTTON    =  3; // client!
    public static int CORNER_RADIUS_FRAMES    =  8; // client!
    public static int CORNER_RADIUS_BIGBUT    =  8; // client!
    public static int CORNER_RADIUS_OVERLAY   = 10; // client!
    public static int CORNER_RADIUS_DIALOG    = 16; // client!
    public static int CORNER_RADIUS_ASSETS    = 16; // client!
    public static int CORNER_RADIUS_TOPBANNER =  0; // client!
    // @formatter:on

    //
    // Available Typefaces.
    //

    // @formatter:off
    public static final String GOTHAM_BOLD          = "fonts/Gotham-Bold.otf";
    public static final String GOTHAM_LIGHT         = "fonts/Gotham-Light.otf";
    public static final String GOTHAM_MEDIUM        = "fonts/Gotham-Medium.otf";
    public static final String GOTHAMNARROW_LIGHT   = "fonts/GothamNarrow-Light.otf";
    public static final String ROONEY_LIGHT         = "fonts/Rooney-Light.otf";
    public static final String ROONEY_MEDIUM        = "fonts/Rooney-Medium.otf";
    public static final String ROONEY_REGULAR       = "fonts/Rooney-Regular.otf";
    public static final String FUTURA_LIGHT_REG     = "fonts/Futura-Light-Regular.otf";
    public static final String FUTURA_BOOK          = "fonts/Futura-Book.otf";
    public static final String SOURCE_SERIF_LIGHT   = "fonts/SourceSerifPro-Light.otf";
    public static final String SOURCE_SERIF_BOLD    = "fonts/SourceSerifPro-Bold.otf";
    // @formatter:on

    //
    // Used Typefaces.
    //

    // @formatter:off
    public static String FONT_DIALOG_TITLE     = GOTHAM_BOLD;          // client!
    public static String FONT_DIALOG_INFOS     = GOTHAM_LIGHT;         // client!
    public static String FONT_DIALOG_EDITS     = GOTHAM_LIGHT;         // client!
    public static String FONT_DIALOG_BUTTON    = GOTHAM_BOLD;          // client!
    public static String FONT_GENERIC_BUTTON   = GOTHAM_BOLD;          // client!
    public static String FONT_GENERIC_EDIT     = GOTHAM_LIGHT;         // client!
    public static String FONT_ASSET_TITLE      = GOTHAM_BOLD;          // client!
    public static String FONT_ASSET_SUMMARY    = GOTHAMNARROW_LIGHT;   // client!
    public static String FONT_SLIDER_CAT       = GOTHAMNARROW_LIGHT;   // client!
    public static String FONT_SLIDER_ALL       = GOTHAMNARROW_LIGHT;   // client!
    public static String FONT_SCALED_BUTTON    = ROONEY_MEDIUM;        // client!
    public static String FONT_TABBAR_ENTRY     = ROONEY_MEDIUM;        // client!
    public static String FONT_CATEGORY_TITLE   = GOTHAM_BOLD;          // client!
    public static String FONT_SETTINGS_HEADER  = GOTHAM_BOLD;          // client!
    public static String FONT_SETTINGS_SUBHEAD = GOTHAM_MEDIUM;        // client!
    public static String FONT_SETTINGS_INFOS   = GOTHAMNARROW_LIGHT;   // client!
    public static String FONT_SETTINGS_VERSION = GOTHAMNARROW_LIGHT;   // client!
    public static String FONT_SETTINGS_LIST    = GOTHAMNARROW_LIGHT;   // client!
    public static String FONT_DETAILS_HEADER   = GOTHAM_MEDIUM;        // client!
    public static String FONT_DETAILS_SUBHEAD  = ROONEY_REGULAR;       // client!
    public static String FONT_DETAILS_TITLE    = ROONEY_REGULAR;       // client!
    public static String FONT_DETAILS_INFOS    = ROONEY_LIGHT;         // client!
    // @formatter:on

    //
    // Elements.
    //

    // @formatter:off
    // @formatter:on

    //
    // Fontsizes.
    //

    // @formatter:off
    public static int FS_DIALOG_TITLE       = Simple.isTablet() ? 18 : 16;
    public static int FS_DIALOG_EDIT        = Simple.isTablet() ? 18 : 16;
    public static int FS_DIALOG_BUTTON      = Simple.isTablet() ? 16 : 14;
    public static int FS_DIALOG_INFO        = Simple.isTablet() ? 16 : 14;

    public static int FS_GENERIC_BUTTON     = Simple.isTablet() ? 16 : 14;
    public static int FS_GENERIC_EDIT       = Simple.isTablet() ? 20 : 18;

    public static int FS_SCALED_BUTTON      = Simple.isTablet() ? 18 : 16; // client!
    public static int FS_NAVI_MENU          = Simple.isTablet() ? 20 : 14;
    public static int FS_CATEGORY_TITLE     = Simple.isTablet() ? 26 : 18;
    public static int FS_POPUP_MENU         = Simple.isTablet() ? 18 : 16;
    public static int FS_DEBUG_VERSION      = Simple.isTablet() ? 13 : 12; // client!

    public static int FS_TABBAR_ENTRY       = Simple.isTablet() ? 20 : 18;

    public static int FS_SLIDER_CATEGORY    = Simple.isTablet() ? 18 : 16;
    public static int FS_SLIDER_SHOWMORE    = Simple.isTablet() ? 14 : 12;

    public static int FS_BANNER_TITLE       = Simple.isTablet() ? 20 : 18; // client!
    public static int FS_BANNER_INFO        = Simple.isTablet() ? 20 : 18; // client!
    public static int FS_BANNER_BUTTON      = Simple.isTablet() ? 16 : 14;

    public static int FS_ASSET_TITLE        = Simple.isTablet() ? 13 : 12; // client!
    public static int FS_ASSET_INFO         = Simple.isTablet() ? 13 : 12; // client!
    public static int FS_ASSET_OWNED        = Simple.isTablet() ? 12 : 11;

    public static int FS_COINS_COINS        = Simple.isTablet() ? 56 : 36;
    public static int FS_COINS_PRICE        = Simple.isTablet() ? 26 : 22;
    public static int FS_COINS_BUTTONS      = Simple.isTablet() ? 22 : 20;

    public static int FS_DETAIL_HEADER      = Simple.isTablet() ? 16 : 14; // client!
    public static int FS_DETAIL_SUBHEAD     = Simple.isTablet() ? 24 : 22; // client!
    public static int FS_DETAIL_TITLE       = Simple.isTablet() ? 16 : 14; // client!
    public static int FS_DETAIL_INFOS       = Simple.isTablet() ? 15 : 12; // client!
    public static int FS_DETAIL_SPECS       = Simple.isTablet() ? 15 : 12; // client!

    public static int FS_BUY_TITLE          = Simple.isTablet() ? 16 : 14;
    public static int FS_BUY_HEADER         = Simple.isTablet() ? 24 : 22;
    public static int FS_BUY_PRICE          = Simple.isTablet() ? 48 : 40;

    public static int FS_SETTINGS_TITLE     = Simple.isTablet() ? 17 : 16;
    public static int FS_SETTINGS_INFO      = Simple.isTablet() ? 15 : 14; // client!
    public static int FS_SETTINGS_BACK      = Simple.isTablet() ? 15 : 12;
    public static int FS_SETTINGS_LIST      = Simple.isTablet() ? 22 : 20; // client!
    public static int FS_SETTINGS_MORE      = Simple.isTablet() ? 30 : 28; // client!

    public static int FS_TRAINING_TITLE     = Simple.isTablet() ? 46 : 40;
    public static int FS_TRAINING_INFO      = Simple.isTablet() ? 20 : 18;
    public static int FS_TRAINING_START     = Simple.isTablet() ? 28 : 24;

    public static int FS_QUESTIONS_TITLE    = Simple.isTablet() ? 18 : 16;
    public static int FS_QUESTIONS_QUESTION = Simple.isTablet() ? 36 : 32;
    public static int FS_QUESTIONS_ANSWER   = Simple.isTablet() ? 20 : 16;

    public static int FS_OVERVIEW_NUMBER    = Simple.isTablet() ? 24 : 22;

    // @formatter:on

    //
    // Multiplication factor fontsize to line-height
    // for assets info/subtitle display.
    //

    // @formatter:off
    public static final float FS_ASSET_INFO_LSMULT  = Simple.isTablet() ? 1.20f : 1.10f;
    public static final float FS_DIALOGS_LSMULT     = Simple.isTablet() ? 1.20f : 1.10f;
    public static final float FS_CONFIRMED_LSMULT   = Simple.isTablet() ? 1.50f : 1.30f;

    public static final float LETTERSPACE_GENERIC_BUTTON = 0.06f;
    public static final float LETTERSPACE_GENERIC_HEADER = 0.06f;

    public static final float FS_NAVIGATION_LSSPACE = 0.08f;
    // @formatter:on

    //
    // Estimation of the overall aspect ratio of asset
    // thumbnails to precompute an equal cell height.
    //

    // @formatter:off
    public static float ASSET_THUMBNAIL_ASPECT = 1.9f; // client!
    public static float ASSET_SETTINGS_ASPECT  = 3.5f; // client!
    public static float ASSET_DETAIL_ASPECT    = 3.0f; // client!
    public static float ASSET_COURSE_ASPECT    = 3.5f; // client!
    public static float ASSET_BANNER_ASPECT    = 3.2f; // client!
    // @formatter:on

    //
    // Misc.
    //

    // @formatter:off
    public static final int MINWIDTH_CATEGORY_POPUP = Simple.isTablet() ? 350 : 300;
    public static final int MINWIDTH_PROFILE_POPUP  = Simple.isTablet() ? 350 : 300;
    // @formatter:on

    // @formatter:off
    public static final int PADDING_ZERO     = Simple.isTablet() ?  0 :  0;
    public static final int PADDING_TINY     = Simple.isTablet() ?  4 :  2;
    public static final int PADDING_SMALL    = Simple.isTablet() ? 10 :  8;
    public static final int PADDING_MEDIUM   = Simple.isTablet() ? 14 : 12;
    public static final int PADDING_NORMAL   = Simple.isTablet() ? 16 : 14;
    public static final int PADDING_LARGE    = Simple.isTablet() ? 20 : 16;
    public static final int PADDING_XLARGE   = Simple.isTablet() ? 40 : 30;
    public static final int PADDING_TRAINING = Simple.isTablet() ? 90 : 10;

    public static final int MARGIN_POPUP     = Simple.isTablet() ? 16 :  0;
    // @formatter:on

    // @formatter:off
    public static       int SETTINGS_IMAGE_SIZE = Simple.isTablet() ? 100 :  90; // client!
    public static       int COURSE_ICON_SIZE    = Simple.isTablet() ?  64 :  56; // client!
    public static final int RATE_ICON_SIZE      = Simple.isTablet() ?  32 :  24;
    public static final int READ_ICON_SIZE      = Simple.isTablet() ?  24 :  20;
    public static final int STATUS_ICON_SIZE    = Simple.isTablet() ?  36 :  24;
    public static final int CLOSE_ICON_SIZE     = Simple.isTablet() ?  20 :  18;
    public static final int SETTINGS_BACK_SIZE  = Simple.isTablet() ?  24 :  20;
    public static final int QUESTION_CHECK_SIZE = Simple.isTablet() ?  30 :  24;
    public static final int BANNER_ARROW_WIDTH  = Simple.isTablet() ?  25 :  16;
    public static final int LOADED_ICON_SIZE    = Simple.isTablet() ?  40 :  30;
    public static final int CLOUD_ICON_SIZE     = Simple.isTablet() ?  50 :  40;
    public static final int NAVIGATION_HEIGHT   = Simple.isTablet() ?  40 :  40;
    public static final int TYPE_ICON_SIZE      = Simple.isTablet() ? 128 :  48;
    public static final int CONFIRMED_ICON_SIZE = Simple.isTablet() ? 128 : 100;
    public static final int COINS_BUTTON_WIDTH  = Simple.isTablet() ? 145 : 130;

    public static final int TABBAR_HEIGHT       = Simple.isWideScreen()? 48 : Simple.isTablet() ?  60 :  48;
    // @formatter:on

    // @formatter:off
    public static final int SLIDER_BARS_SIZE      = Simple.isTablet() ?   3 :   3;
    public static final int SLIDER_KNOB_SIZE      = Simple.isTablet() ?  30 :  30;
    public static final int ONOFF_WIDTH_SIZE      = Simple.isTablet() ?  60 :  60;
    public static final int ONOFF_KNOB_SIZE       = Simple.isTablet() ?  30 :  30;
    public static final int PROGRESS_BAR_SIZE     = Simple.isTablet() ?   6 :   6;
    public static final int PROGRESS_BAR_WIDTH    = Simple.isTablet() ? 300 : 200;
    public static final int PROGRESS_DIALOG_WIDTH = Simple.isTablet() ? 500 : 250;
    // @formatter:on

    static
    {
        invokeInitClientClass();
    }

    private static Method initializeClient;

    private static boolean getInitClientClass(String client)
    {
        try
        {
            Class defines = Class.forName("de.sensordigitalmediagermany.lemonbasic.client." + client);
            initializeClient = defines.getMethod("initialize");

            Log.d(LOGTAG, "getInitClientClass: client=" + client + " yes.");

            return true;
        }
        catch (Exception ignore)
        {
        }

        Log.d(LOGTAG, "getInitClientClass: client=" + client + " no.");

        return false;
    }

    public static void invokeInitClientClass()
    {
        try
        {
            initializeClient.invoke(null);
        }
        catch (Exception ignore)
        {
        }
    }
}
