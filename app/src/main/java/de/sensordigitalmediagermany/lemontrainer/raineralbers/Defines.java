package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;

@SuppressWarnings({"WeakerAccess"})
public class Defines
{
    //
    // Debug stuff.
    //

    public static final boolean isDezi = true;

    public static final String DEBUG_VERSION = "1.0 (1) 12.01.2018 13:30 ";

    //
    // Rest API database access selectors.
    //

    // @formatter:off
    public static String SYSTEM_USER_NAME  = "undefinied";
    public static String SYSTEM_USER_PARAM = "undefinied!";

    public static String APIURL = "undefinied";

    // @formatter:on

    //
    // Client specific defines.
    //

    // @formatter:off
    protected static final boolean isPierCardin   = true;
    protected static final boolean isRainerAlbers = false;
    // @formatter:on

    //
    // Client specific styles and variants.
    //

    // @formatter:off
    public static final boolean isBasic            = isPierCardin;
    public static final boolean isTrainer          = isRainerAlbers;

    public static final boolean isGiveAway         = isPierCardin || isRainerAlbers;
    public static final boolean isLoginButton      = isRainerAlbers;
    public static final boolean isSimpleLogin      = isPierCardin;
    public static final boolean isTabBar           = isPierCardin;
    public static final boolean isTopBanner        = isPierCardin;
    public static final boolean isCategoryMenu     = isRainerAlbers;
    public static final boolean isRoundedAsset     = isRainerAlbers;
    public static final boolean isOverlayAsset     = isPierCardin;
    public static final boolean isCompanyAvailable = isRainerAlbers;
    public static final boolean isSectionDividers  = isPierCardin;
    public static final boolean isFlatEdits        = isPierCardin;
    public static final boolean isDialogTextCenter = isRainerAlbers;
    public static final boolean isCompactDetails   = isPierCardin;
    public static final boolean isCompactSettings  = isPierCardin;
    public static final boolean isHintsAllCaps     = isPierCardin;
    public static final boolean isButtonAllCaps    = isPierCardin;
    public static final boolean isInfosAllCaps     = isPierCardin;
    public static final boolean isDeleteCache      = isDezi;

    public static final boolean isPDFZoomable      = false;
    // @formatter:on

    //
    // Variable string ids.
    //

    public static int logoff_info = R.string.logoff_info_neutral; // static!

    //
    // Fixed values.
    //

    public static final int TRAINING_NUM_QUESTIONS = 4;
    public static final int SLIDER_MAX_COLUMNS = 6;

    public static final int ASSETS_NUM_COLUMNS = Simple.isTablet() ? 4 : 2;
    public static final int RESULTS_NUM_COLUMNS = Simple.isTablet() ? 8 : 6;

    public static final int MIN_EMS_DIALOGS = Simple.isTablet() ? 12 : 10;
    public static final int MAX_EMS_DIALOGS = Simple.isTablet() ? 20 : 14;

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
    public static final int COLOR_SENSOR_TABBAR      = 0xfff5f5f5;
    public static final int COLOR_SENSOR_BUTTONTEXT  = 0xfff5f5f5;

    public static final int COLOR_PCADIN_CONTENT     = 0xffffffff;
    public static final int COLOR_PCADIN_GRAY        = 0xffb4b4b4;
    public static final int COLOR_PCADIN_LTGRAY      = 0xfff5f5f5;

    public static final int COLOR_BUTTON_TOUCHED     = 0x88888888;
    public static final int COLOR_BACKGROUND_DIM     = 0x77000000;
    public static final int COLOR_QUESTIONS_SEP      = 0x11000000;

    public static       int COLOR_NAVIBAR           = COLOR_SENSOR_NAVIBAR;    // static!
    public static       int COLOR_TABBAR            = COLOR_SENSOR_TABBAR;     // static!
    public static       int COLOR_CONTENT           = COLOR_SENSOR_CONTENT;    // static!
    public static       int COLOR_FRAMES            = COLOR_SENSOR_CONTENT;    // static!
    public static       int COLOR_DIALOG_BACK       = COLOR_SENSOR_DIALOGS;    // static!
    public static       int COLOR_DIALOG_TITLE      = Color.WHITE;             // static!
    public static       int COLOR_DIALOG_INFOS      = Color.WHITE;             // static!
    public static       int COLOR_BUTTON_TEXT       = COLOR_SENSOR_BUTTONTEXT; // static!
    public static       int COLOR_BUTTON_BACK       = COLOR_SENSOR_LTBLUE;     // static!
    public static       int COLOR_ALERT_BACK        = COLOR_SENSOR_DIALOGS;    // static!
    public static       int COLOR_SETTINGS_HEADERS  = COLOR_SENSOR_DKBLUE;     // static!
    public static       int COLOR_SETTINGS_LIST     = COLOR_SENSOR_NAVIBAR;    // static!
    public static       int COLOR_SETTINGS_LIST_SEL = COLOR_SENSOR_LTBLUE;     // static!
    public static       int COLOR_DETAIL_TITLE      = COLOR_SENSOR_LTBLUE;     // static!
    // @formatter:on

    //
    // Corner radius.
    //

    // @formatter:off
    public static       int CORNER_RADIUS_BUTTON  =  3; // static!
    public static       int CORNER_RADIUS_FRAMES  =  8; // static!
    public static final int CORNER_RADIUS_BIGBUT  =  8;
    public static final int CORNER_RADIUS_OVERLAY = 10;
    public static       int CORNER_RADIUS_DIALOG  = 16; // static!
    public static final int CORNER_RADIUS_ASSETS  = 16;
    // @formatter:on

    //
    // Available Typefaces.
    //

    // @formatter:off
    public static final String GOTHAM_BOLD          = "fonts/Gotham-Bold.otf";
    public static final String GOTHAM_LIGHT         = "fonts/Gotham-Light.otf";
    public static final String GOTHAM_MEDIUM        = "fonts/Gotham-Medium.otf";
    public static final String ROONEY_LIGHT         = "fonts/Rooney-Light.otf";
    public static final String ROONEY_MEDIUM        = "fonts/Rooney-Medium.otf";
    public static final String ROONEY_REGULAR       = "fonts/Rooney-Regular.otf";
    public static final String GOTHAMNARROW_LIGHT   = "fonts/GothamNarrow-Light.otf";
    public static final String FUTURA_LIGHT_REG     = "fonts/Futura-Light-Regular.otf";
    public static final String FUTURA_BOOK          = "fonts/Futura-Book.otf";
    // @formatter:on

    //
    // Used Typefaces.
    //

    // @formatter:off
    public static String FONT_DIALOG_TITLE     = GOTHAM_BOLD;          // static!
    public static String FONT_DIALOG_INFOS     = GOTHAM_LIGHT;         // static!
    public static String FONT_DIALOG_EDITS     = GOTHAM_LIGHT;         // static!
    public static String FONT_DIALOG_BUTTON    = GOTHAM_BOLD;          // static!
    public static String FONT_ASSET_TITLE      = GOTHAM_BOLD;          // static!
    public static String FONT_ASSET_SUMMARY    = GOTHAMNARROW_LIGHT;   // static!
    public static String FONT_SLIDER_ALL       = GOTHAMNARROW_LIGHT;   // static!
    public static String FONT_SCALED_BUTTON    = ROONEY_MEDIUM;        // static!
    public static String FONT_TABBAR_ENTRY     = ROONEY_MEDIUM;        // static!
    public static String FONT_CATEGORY_TITLE   = GOTHAM_BOLD;          // static!
    public static String FONT_SETTINGS_HEADER  = GOTHAM_BOLD;          // static!
    public static String FONT_SETTINGS_SUBHEAD = GOTHAM_MEDIUM;        // static!
    public static String FONT_SETTINGS_INFOS   = GOTHAMNARROW_LIGHT;   // static!
    public static String FONT_SETTINGS_VERSION = GOTHAMNARROW_LIGHT;   // static!
    public static String FONT_SETTINGS_ASSETS  = GOTHAMNARROW_LIGHT;   // static!
    public static String FONT_SETTINGS_LIST    = GOTHAMNARROW_LIGHT;   // static!
    public static String FONT_DETAILS_HEADER   = GOTHAM_MEDIUM;        // static!
    public static String FONT_DETAILS_SUBHEAD  = ROONEY_REGULAR;       // static!
    public static String FONT_DETAILS_TITLE    = ROONEY_REGULAR;       // static!
    public static String FONT_DETAILS_INFOS    = ROONEY_LIGHT;         // static!
    // @formatter:on

    //
    // Fontsizes.
    //

    // @formatter:off
    public static final int FS_DIALOG_TITLE       = Simple.isTablet() ? 20 : 16;
    public static final int FS_DIALOG_EDIT        = Simple.isTablet() ? 20 : 16;
    public static final int FS_DIALOG_BUTTON      = Simple.isTablet() ? 20 : 16;
    public static final int FS_DIALOG_INFO        = Simple.isTablet() ? 18 : 16;

    public static       int FS_SCALED_BUTTON      = Simple.isTablet() ? 18 : 16; // static!
    public static final int FS_NAVI_MENU          = Simple.isTablet() ? 20 : 14;
    public static final int FS_CATEGORY_TITLE     = Simple.isTablet() ? 26 : 22;
    public static final int FS_POPUP_MENU         = Simple.isTablet() ? 18 : 16;
    public static       int FS_DEBUG_VERSION      = Simple.isTablet() ? 13 : 12; // static!

    public static final int FS_TABBAR_ENTRY       = Simple.isTablet() ? 20 : 18;

    public static final int FS_SLIDER_CATEGORY    = Simple.isTablet() ? 20 : 18;
    public static final int FS_SLIDER_SHOWMORE    = Simple.isTablet() ? 14 : 13;

    public static       int FS_BANNER_TITLE       = Simple.isTablet() ? 20 : 18; // static!
    public static       int FS_BANNER_INFO        = Simple.isTablet() ? 20 : 18; // static!
    public static final int FS_BANNER_BUTTON      = Simple.isTablet() ? 16 : 14;

    public static       int FS_ASSET_TITLE        = Simple.isTablet() ? 13 : 12; // static!
    public static       int FS_ASSET_INFO         = Simple.isTablet() ? 13 : 12; // static!
    public static final int FS_ASSET_OWNED        = Simple.isTablet() ? 12 : 11;

    public static final int FS_COINS_COINS        = Simple.isTablet() ? 56 : 48;
    public static final int FS_COINS_PRICE        = Simple.isTablet() ? 26 : 22;
    public static final int FS_COINS_BUTTONS      = Simple.isTablet() ? 22 : 20;

    public static       int FS_DETAIL_HEADER      = Simple.isTablet() ? 16 : 14; // static!
    public static       int FS_DETAIL_SUBHEAD     = Simple.isTablet() ? 24 : 22; // static!
    public static       int FS_DETAIL_TITLE       = Simple.isTablet() ? 16 : 14; // static!
    public static       int FS_DETAIL_INFOS       = Simple.isTablet() ? 15 : 12; // static!
    public static       int FS_DETAIL_SPECS       = Simple.isTablet() ? 15 : 12; // static!

    public static final int FS_BUY_TITLE          = Simple.isTablet() ? 16 : 14;
    public static final int FS_BUY_HEADER         = Simple.isTablet() ? 24 : 22;
    public static final int FS_BUY_PRICE          = Simple.isTablet() ? 48 : 40;

    public static final int FS_SETTINGS_TITLE     = Simple.isTablet() ? 17 : 16;
    public static final int FS_SETTINGS_INFO      = Simple.isTablet() ? 15 : 14;
    public static final int FS_SETTINGS_EDIT      = Simple.isTablet() ? 20 : 18;
    public static final int FS_SETTINGS_BUTTON    = Simple.isTablet() ? 14 : 12;
    public static       int FS_SETTINGS_LIST      = Simple.isTablet() ? 22 : 20; // static!
    public static       int FS_SETTINGS_MORE      = Simple.isTablet() ? 30 : 28; // static!

    public static final int FS_TRAINING_TITLE     = Simple.isTablet() ? 46 : 40;
    public static final int FS_TRAINING_INFO      = Simple.isTablet() ? 20 : 18;
    public static final int FS_TRAINING_START     = Simple.isTablet() ? 28 : 24;

    public static final int FS_QUESTIONS_TITLE    = Simple.isTablet() ? 18 : 16;
    public static final int FS_QUESTIONS_QUESTION = Simple.isTablet() ? 36 : 32;
    public static final int FS_QUESTIONS_ANSWER   = Simple.isTablet() ? 20 : 16;

    public static final int FS_OVERVIEW_NUMBER    = Simple.isTablet() ? 24 : 22;

    // @formatter:on

    //
    // Multiplication factor fontsize to line-height
    // for assets info/subtitle display.
    //

    // @formatter:off
    public static final float FS_DIALOGS_LSMULT     = 1.30f;
    public static final float FS_CONFIRMED_LSMULT   = 1.50f;
    public static final float FS_ASSET_INFO_LSMULT  = 1.30f;
    public static final float FS_NAVIGATION_LSSPACE = 0.08f;
    // @formatter:on

    //
    // Estimation of the overall aspect ratio of asset
    // thumbnails to precompute an equal cell height.
    //

    // @formatter:off
    public static       float ASSET_THUMBNAIL_ASPECT = 1.9f; // static!
    public static       float ASSET_SETTINGS_ASPECT  = 2.5f; // static!
    public static       float ASSET_DETAIL_ASPECT    = 3.0f; // static!
    public static final float ASSET_COURSE_ASPECT    = 3.5f;
    public static final float ASSET_BANNER_ASPECT    = 3.2f;
    // @formatter:on

    //
    // Misc.
    //

    // @formatter:off
    public static final int MINWIDTH_CATEGORY_POPUP = Simple.isTablet() ? 350 : 300;
    public static final int MINWIDTH_PROFILE_POPUP  = Simple.isTablet() ? 350 : 300;
    // @formatter:on

    // @formatter:off
    public static final int PADDING_TINY     = Simple.isTablet() ?  4 :  4;
    public static final int PADDING_SMALL    = Simple.isTablet() ? 10 :  8;
    public static final int PADDING_MEDIUM   = Simple.isTablet() ? 14 : 12;
    public static final int PADDING_NORMAL   = Simple.isTablet() ? 16 : 14;
    public static final int PADDING_LARGE    = Simple.isTablet() ? 20 : 16;
    public static final int PADDING_XLARGE   = Simple.isTablet() ? 40 : 30;
    public static final int PADDING_TRAINING = Simple.isTablet() ? 90 : 10;

    public static final int MARGIN_BUTTON    = Simple.isTablet() ?  6 :  4;
    public static final int MARGIN_POPUP     = Simple.isTablet() ? 16 :  0;
    // @formatter:on

    // @formatter:off
    public static       int SETTINGS_IMAGE_SIZE = Simple.isTablet() ? 100 :  90; // static!
    public static final int READ_ICON_SIZE      = Simple.isTablet() ?  24 :  20;
    public static final int CLOSE_ICON_SIZE     = Simple.isTablet() ?  24 :  20;
    public static final int SETTINGS_BACK_SIZE  = Simple.isTablet() ?  24 :  20;
    public static final int QUESTION_CHECK_SIZE = Simple.isTablet() ?  30 :  24;
    public static final int BANNER_ARROW_WIDTH  = Simple.isTablet() ?  25 :  25;
    public static final int STATUS_ICON_SIZE    = Simple.isTablet() ?  40 :  30;
    public static final int CLOUD_ICON_SIZE     = Simple.isTablet() ?  50 :  40;
    public static final int COURSE_ICON_SIZE    = Simple.isTablet() ?  64 :  56;
    public static final int NAVIGATION_HEIGHT   = Simple.isTablet() ?  40 :  40;
    public static final int TABBAR_HEIGHT       = Simple.isTablet() ?  60 :  60;
    public static final int TYPE_ICON_SIZE      = Simple.isTablet() ? 128 : 100;
    public static final int CONFIRMED_ICON_SIZE = Simple.isTablet() ? 128 : 100;
    public static final int COINS_BUTTON_WIDTH  = Simple.isTablet() ? 145 : 130;
    // @formatter:on

    // @formatter:off
    public static final int SLIDER_BARS_SIZE      = Simple.isTablet() ?   3 :   3;
    public static final int SLIDER_KNOB_SIZE      = Simple.isTablet() ?  30 :  30;
    public static final int ONOFF_WIDTH_SIZE      = Simple.isTablet() ?  60 :  60;
    public static final int ONOFF_KNOB_SIZE       = Simple.isTablet() ?  30 :  30;
    public static final int PROGRESS_BAR_SIZE     = Simple.isTablet() ?   6 :   6;
    public static final int PROGRESS_BAR_WIDTH    = Simple.isTablet() ? 300 : 200;
    public static final int PROGRESS_DIALOG_WIDTH = Simple.isTablet() ? 500 : 300;
    // @formatter:on

    static
    {
        if (isBasic) SYSTEM_USER_PARAM = "systemuserName";
        if (isTrainer) SYSTEM_USER_PARAM = "trainerName";

        if (isRainerAlbers)
        {
            SYSTEM_USER_NAME = "RAINERALBERS";

            APIURL = "https://lemon-mobile-learning.com/lemon-trainer/ws/";

            logoff_info = R.string.logoff_info_rainer_albers;
        }

        if (isPierCardin)
        {
            SYSTEM_USER_NAME = "PIERRECARDIN";

            APIURL = "https://lemon-mobile-learning.com/lemon-basic/ws/";

            logoff_info = R.string.logoff_info_pier_cadin;

            //
            // Tuneups for Pier Cadin style.
            //

            // @formatter:off

            CORNER_RADIUS_BUTTON = 0;
            CORNER_RADIUS_DIALOG = 0;
            CORNER_RADIUS_FRAMES = 0;

            COLOR_NAVIBAR           = COLOR_PCADIN_GRAY;
            COLOR_TABBAR            = COLOR_PCADIN_LTGRAY;
            COLOR_CONTENT           = COLOR_PCADIN_CONTENT;
            COLOR_FRAMES            = COLOR_PCADIN_LTGRAY;
            COLOR_DIALOG_BACK       = COLOR_PCADIN_LTGRAY;
            COLOR_DIALOG_TITLE      = Color.BLACK;
            COLOR_DIALOG_INFOS      = Color.BLACK;
            COLOR_BUTTON_TEXT       = COLOR_PCADIN_GRAY;
            COLOR_BUTTON_BACK       = Color.BLACK;
            COLOR_ALERT_BACK        = COLOR_PCADIN_LTGRAY;
            COLOR_SETTINGS_HEADERS  = Color.BLACK;
            COLOR_SETTINGS_LIST     = Color.WHITE;
            COLOR_SETTINGS_LIST_SEL = Color.BLACK;
            COLOR_DETAIL_TITLE      = Color.BLACK;

            FONT_DIALOG_TITLE       = FUTURA_BOOK;
            FONT_DIALOG_INFOS       = FUTURA_LIGHT_REG;
            FONT_DIALOG_EDITS       = FUTURA_LIGHT_REG;
            FONT_DIALOG_BUTTON      = FUTURA_BOOK;
            FONT_ASSET_TITLE        = FUTURA_LIGHT_REG;
            FONT_ASSET_SUMMARY      = FUTURA_LIGHT_REG;
            FONT_SLIDER_ALL         = FUTURA_LIGHT_REG;
            FONT_SCALED_BUTTON      = FUTURA_LIGHT_REG;
            FONT_TABBAR_ENTRY       = FUTURA_LIGHT_REG;
            FONT_CATEGORY_TITLE     = FUTURA_LIGHT_REG;
            FONT_SETTINGS_HEADER    = FUTURA_BOOK;
            FONT_SETTINGS_SUBHEAD   = FUTURA_BOOK;
            FONT_SETTINGS_INFOS     = FUTURA_LIGHT_REG;
            FONT_SETTINGS_VERSION   = FUTURA_LIGHT_REG;
            FONT_SETTINGS_ASSETS    = FUTURA_LIGHT_REG;
            FONT_SETTINGS_LIST      = FUTURA_BOOK;
            FONT_DETAILS_HEADER     = FUTURA_BOOK;
            FONT_DETAILS_SUBHEAD    = FUTURA_LIGHT_REG;
            FONT_DETAILS_TITLE      = FUTURA_BOOK;
            FONT_DETAILS_INFOS      = FUTURA_LIGHT_REG;

            FS_BANNER_TITLE         = Simple.isTablet() ? 20 : 18;
            FS_BANNER_INFO          = Simple.isTablet() ? 26 : 22;
            FS_SCALED_BUTTON        = Simple.isTablet() ? 14 : 13;
            FS_ASSET_TITLE          = Simple.isTablet() ? 14 : 13;
            FS_ASSET_INFO           = Simple.isTablet() ? 20 : 18;
            FS_DEBUG_VERSION        = Simple.isTablet() ? 10 :  8;
            FS_SETTINGS_LIST        = Simple.isTablet() ? 16 : 14;
            FS_SETTINGS_MORE        = Simple.isTablet() ? 24 : 22;
            FS_DETAIL_HEADER        = Simple.isTablet() ? 16 : 14;
            FS_DETAIL_SUBHEAD       = Simple.isTablet() ? 24 : 22;
            FS_DETAIL_TITLE         = Simple.isTablet() ? 15 : 12;
            FS_DETAIL_INFOS         = Simple.isTablet() ? 15 : 12;
            FS_DETAIL_SPECS         = Simple.isTablet() ? 15 : 12;

            SETTINGS_IMAGE_SIZE     = Simple.isTablet() ? 60 : 40;

            ASSET_THUMBNAIL_ASPECT  = 1.30f;
            ASSET_SETTINGS_ASPECT   = 3.00f;
            ASSET_DETAIL_ASPECT     = 3.20f;

            // @formatter:on
        }
    }
}
