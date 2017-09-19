package de.sensordigitalmediagermany.lemontrainer.raineralbers;

@SuppressWarnings({"WeakerAccess"})
public class Defines
{
    //
    // Debug stuff.
    //

    public static final boolean isDezi = false;

    public static final String TRAINER_NAME = "RAINERALBERS";

    //
    // Access urls.
    //

    public static final String APIURL = "https://lemon-mobile-learning.com/lemon-trainer/ws/";

    //
    // Fixed values.
    //

    public static final int MINWIDTH_CATEGORY_POPUP = Simple.isTablet() ? 350 : 300;
    public static final int MINWIDTH_PROFILE_POPUP = Simple.isTablet() ? 350 : 300;

    //
    // Colors.
    //

    public static final int COLOR_SENSOR_LTBLUE = 0xff657995;
    public static final int COLOR_SENSOR_DKBLUE = 0xff3b4455;
    public static final int COLOR_SENSOR_GREEN = 0xff54b23b;

    public static final int COLOR_SENSOR_DIALOGS = 0xcc3b4455;
    public static final int COLOR_SENSOR_NAVIBAR = 0xffedf0f4;
    public static final int COLOR_SENSOR_CONTENT = 0xffb6c4d2;

    public static final int COLOR_BUTTON_TOUCHED = 0x88888888;
    public static final int COLOR_BACKGROUND_DIM = 0x77000000;

    //
    // Corner radius for ALL buttons.
    //

    public static final int CORNER_RADIUS_BUTTON =  3; // Important!
    public static final int CORNER_RADIUS_DIALOG = 16; // Important!

    //
    // Typefaces.
    //

    public static final String GOTHAM_BOLD = "fonts/Gotham-Bold.otf";
    public static final String GOTHAM_LIGHT = "fonts/Gotham-Light.otf";

    //
    // Fontsizes.
    //

    // @formatter:off
    public static final int FS_DIALOG_TITLE  = Simple.isTablet() ? 20 : 16;
    public static final int FS_DIALOG_EDIT   = Simple.isTablet() ? 20 : 16;
    public static final int FS_DIALOG_BUTTON = Simple.isTablet() ? 20 : 16;
    public static final int FS_DIALOG_INFO   = Simple.isTablet() ? 16 : 14;

    public static final int FS_NAVI_MENU     = Simple.isTablet() ? 22 : 20;
    public static final int FS_POPUP_MENU    = Simple.isTablet() ? 18 : 16;
    // @formatter:on

    //
    // Misc.
    //

    // @formatter:off
    public static final int PADDING_TINY    = Simple.isTablet() ?  4 :  4;
    public static final int PADDING_SMALL   = Simple.isTablet() ? 10 :  8;
    public static final int PADDING_MEDIUM  = Simple.isTablet() ? 14 : 12;
    public static final int PADDING_NORMAL  = Simple.isTablet() ? 16 : 14;
    public static final int PADDING_LARGE   = Simple.isTablet() ? 20 : 16;
    public static final int PADDING_XLARGE  = Simple.isTablet() ? 40 : 30;
    // @formatter:on

    public static final int CLOSE_ICON_SIZE = Simple.isTablet() ? 24 : 20; // Important!
    public static final int NAVIGATION_HEIGHT = Simple.isTablet() ? 40 : 40;
}
