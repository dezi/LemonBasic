package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;

@SuppressWarnings({"WeakerAccess"})
public class Defines
{
    //
    // Debug stuff.
    //

    public static final boolean isDezi = true;
    public static final boolean isPierCardin = true;

    public static final boolean isGiveAway = true;
    public static final boolean isCategoryMenu = false;
    public static final boolean isTopBanner = true;

    public static final String DEBUG_VERSION = "09.11.2017 11:00 (0.95)";

    public static final String TRAINER_NAME = "RAINERALBERS";

    //
    // Access urls.
    //

    public static final String APIURL = "https://lemon-mobile-learning.com/lemon-trainer/ws/";

    //
    // Fixed values.
    //

    public static final int TRAINING_NUM_QUESTIONS = 4;

    public static final int ASSETS_NUM_COLUMNS = Simple.isTablet() ? 4 : 2;
    public static final int RESULTS_NUM_COLUMNS = Simple.isTablet() ? 8 : 6;

    public static final int CONTENT_TYPE_PDF = 1;
    public static final int CONTENT_TYPE_VIDEO = 2;
    public static final int CONTENT_TYPE_ZIP = 4;

    //
    // Colors.
    //

    // @formatter:off
    public static final int COLOR_SENSOR_LTBLUE      = 0xff657995;
    public static final int COLOR_SENSOR_DKBLUE      = 0xff3b4455;
    public static final int COLOR_SENSOR_GREEN       = 0xff54b23b;

    public static final int COLOR_SENSOR_DIALOGS     = 0xcc3b4455;
    public static final int COLOR_SENSOR_ALERTS      = 0xff3b4455;
    public static final int COLOR_SENSOR_NAVIBAR     = 0xffedf0f4;
    public static final int COLOR_SENSOR_CONTENT     = 0xffb6c4d2;
    public static final int COLOR_SENSOR_TABBAR      = 0xfff5f5f5;
    public static final int COLOR_SENSOR_BUTTONTEXT  = 0xfff5f5f5;

    public static final int COLOR_PCADIN_CONTENT     = 0xffffffff;
    public static final int COLOR_PCADIN_BUTTONTEXT  = 0xffb4b4b4;

    public static final int COLOR_BUTTON_TOUCHED     = 0x88888888;
    public static final int COLOR_BACKGROUND_DIM     = 0x77000000;
    public static final int COLOR_QUESTIONS_SEP      = 0x11000000;

    public static       int COLOR_CONTENT    = COLOR_SENSOR_CONTENT;    // static!
    public static       int COLOR_BUTTONTEXT = COLOR_SENSOR_BUTTONTEXT; // static!
    // @formatter:on

    //
    // Corner radius.
    //

    // @formatter:off
    public static final int CORNER_RADIUS_BUTTON  =  3;
    public static final int CORNER_RADIUS_BIGBUT  =  8;
    public static final int CORNER_RADIUS_OVERLAY = 10;
    public static final int CORNER_RADIUS_DIALOG  = 16;
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
    public static final String FUTURA_BOLD          = "fonts/Futura-Bold.ttf";
    public static final String FUTURA_LIGHT         = "fonts/Futura-Light.otf";
    public static final String FUTURA_LIGHT_REG     = "fonts/Futura-Light-Regular.otf";
    public static final String FUTURA_BOOK          = "fonts/Futura-Book.ttf";
    public static final String FUTURA_MEDIUM        = "fonts/Futura-Medium.ttf";
    public static final String FUTURA_HEAVY         = "fonts/Futura-Heavy.ttf";
    // @formatter:on

    //
    // Used Typefaces.
    //

    // @formatter:off
    public static String FONT_DIALOG_BUTTON = GOTHAM_BOLD;          // static!
    public static String FONT_ASSET_TITLE   = GOTHAM_BOLD;          // static!
    public static String FONT_ASSET_SUMMARY = GOTHAMNARROW_LIGHT;   // static!
    // @formatter:on

    //
    // Fontsizes.
    //

    // @formatter:off
    public static final int FS_DIALOG_TITLE       = Simple.isTablet() ? 20 : 16;
    public static final int FS_DIALOG_EDIT        = Simple.isTablet() ? 20 : 16;
    public static final int FS_DIALOG_BUTTON      = Simple.isTablet() ? 20 : 16;
    public static final int FS_DIALOG_INFO        = Simple.isTablet() ? 18 : 16;

    public static final int FS_BUTTON_IMAGE       = Simple.isTablet() ? 18 : 16;
    public static final int FS_NAVI_MENU          = Simple.isTablet() ? 20 : 14;
    public static final int FS_POPUP_MENU         = Simple.isTablet() ? 18 : 16;
    public static final int FS_DEBUG_VERSION      = Simple.isTablet() ? 13 : 12;

    public static       int FS_BANNER_TITLE       = Simple.isTablet() ? 20 : 18; // static!
    public static       int FS_BANNER_INFO        = Simple.isTablet() ? 20 : 18; // static!
    public static final int FS_BANNER_BUTTON      = Simple.isTablet() ? 16 : 14;

    public static       int FS_ASSET_TITLE        = Simple.isTablet() ? 13 : 12; // static!
    public static       int FS_ASSET_INFO         = Simple.isTablet() ? 13 : 12; // static!
    public static final int FS_ASSET_OWNED        = Simple.isTablet() ? 12 : 11;

    public static final int FS_COINS_COINS        = Simple.isTablet() ? 56 : 48;
    public static final int FS_COINS_PRICE        = Simple.isTablet() ? 26 : 22;
    public static final int FS_COINS_BUTTONS      = Simple.isTablet() ? 22 : 20;

    public static final int FS_COURSE_TITLE       = Simple.isTablet() ? 16 : 14;
    public static final int FS_COURSE_HEADER      = Simple.isTablet() ? 24 : 22;
    public static final int FS_COURSE_DESC        = Simple.isTablet() ? 15 : 12;

    public static final int FS_BUY_TITLE          = Simple.isTablet() ? 16 : 14;
    public static final int FS_BUY_HEADER         = Simple.isTablet() ? 24 : 22;
    public static final int FS_BUY_PRICE          = Simple.isTablet() ? 48 : 40;

    public static final int FS_DETAIL_SPECS       = Simple.isTablet() ? 15 : 12;

    public static final int FS_SETTINGS_TITLE     = Simple.isTablet() ? 17 : 16;
    public static final int FS_SETTINGS_INFO      = Simple.isTablet() ? 15 : 14;
    public static final int FS_SETTINGS_EDIT      = Simple.isTablet() ? 20 : 18;
    public static final int FS_SETTINGS_BUTTON    = Simple.isTablet() ? 14 : 12;
    public static final int FS_SETTINGS_LIST      = Simple.isTablet() ? 22 : 20;
    public static final int FS_SETTINGS_MORE      = Simple.isTablet() ? 30 : 28;

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
    public static final float FS_CONFIRMED_LSMULT   = 1.50f;
    public static final float FS_ASSET_INFO_LSMULT  = 1.30f;
    public static final float FS_NAVIGATION_LSPACE  = 0.08f;
    // @formatter:on

    //
    // Estimation of the overall aspect ratio of asset
    // thumbnails to precompute an equal cell height.
    //

    // @formatter:off
    public static       float FS_ASSET_THUMBNAIL_ASPECT = 1.9f; // static!
    public static final float FS_ASSET_DETAIL_ASPECT    = 3.0f;
    public static final float FS_ASSET_SETTINGS_ASPECT  = 2.5f;
    public static final float FS_ASSET_BANNER_ASPECT    = 3.0f;
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
    public static final int READ_ICON_SIZE      = Simple.isTablet() ?  24 :  20;
    public static final int CLOSE_ICON_SIZE     = Simple.isTablet() ?  24 :  20;
    public static final int QUESTION_CHECK_SIZE = Simple.isTablet() ?  30 :  24;
    public static final int BANNER_ARROW_WIDTH  = Simple.isTablet() ?  25 :  25;
    public static final int CLOUD_ICON_SIZE     = Simple.isTablet() ?  50 :  40;
    public static final int COURSE_ICON_SIZE    = Simple.isTablet() ?  64 :  56;
    public static final int NAVIGATION_HEIGHT   = Simple.isTablet() ?  40 :  40;
    public static final int PROFILE_IMAGE_SIZE  = Simple.isTablet() ? 100 :  90;
    public static final int ASSET_IMAGE_SIZE    = Simple.isTablet() ? 100 :  90;
    public static final int TYPE_ICON_SIZE      = Simple.isTablet() ? 128 : 100;
    public static final int CONFIRMED_ICON_SIZE = Simple.isTablet() ? 128 : 100;
    public static final int COINS_BUTTON_WIDTH  = Simple.isTablet() ? 145 : 130;
    // @formatter:on

    // @formatter:off
    public static final int SLIDER_BARS_SIZE   = Simple.isTablet() ?   3 :   3;
    public static final int SLIDER_KNOB_SIZE   = Simple.isTablet() ?  30 :  30;
    public static final int ONOFF_WIDTH_SIZE   = Simple.isTablet() ?  60 :  60;
    public static final int ONOFF_KNOB_SIZE    = Simple.isTablet() ?  30 :  30;
    public static final int PROGRESS_BAR_SIZE  = Simple.isTablet() ?   6 :   6;
    public static final int PROGRESS_BAR_WIDTH = Simple.isTablet() ? 300 : 200;
    // @formatter:on

    static
    {
        if (isPierCardin)
        {
            //
            // Tuneups fpr Pier Cadin style.
            //

            // @formatter:off

            COLOR_CONTENT    = COLOR_PCADIN_CONTENT;
            COLOR_BUTTONTEXT = COLOR_PCADIN_BUTTONTEXT;

            FS_BANNER_TITLE  = Simple.isTablet() ? 20 : 18;
            FS_BANNER_INFO   = Simple.isTablet() ? 26 : 22;

            FONT_DIALOG_BUTTON = FUTURA_BOLD;
            FONT_ASSET_TITLE   = FUTURA_LIGHT_REG;
            FONT_ASSET_SUMMARY = FUTURA_LIGHT_REG;

            FS_ASSET_TITLE = Simple.isTablet() ? 14 : 13;
            FS_ASSET_INFO  = Simple.isTablet() ? 20 : 18;

            FS_ASSET_THUMBNAIL_ASPECT = 1.184f;

            // @formatter:on
        }
    }
}
