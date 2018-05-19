package de.sensordigitalmediagermany.lemonbasic.generic;

import android.support.annotation.Nullable;
import android.graphics.Rect;

@SuppressWarnings({"WeakerAccess"})
public class Screens
{
    public static int notifyIconLargeRes = Simple.isTablet()
            ? R.mipmap.lem_t_demo
            : R.mipmap.lem_t_demo;

    public static int getNotifyIconLargeRes()
    {
        return notifyIconLargeRes;
    }

    public static int notifyIconSmallRes = Simple.isTablet()
            ? R.drawable.lem_t_iany_generic_notification
            : R.drawable.lem_t_iany_generic_notification;

    public static int getNotifyIconSmallRes()
    {
        return notifyIconSmallRes;
    }

    public static int closeButtonRes = Simple.isTablet()
            ? R.drawable.lem_t_iany_generic_kreuz
            : R.drawable.lem_t_iany_generic_kreuz;

    public static int getCloseButtonRes()
    {
        return closeButtonRes;
    }

    public static int confirmedIconRes = Simple.isTablet()
            ? R.drawable.lem_t_iany_generic_buy_confirmed
            : R.drawable.lem_t_iany_generic_buy_confirmed;

    public static int getConfirmedIconRes()
    {
        return confirmedIconRes;
    }

    public static int readMarkerRes = Simple.isTablet()
            ? R.drawable.lem_t_iany_generic_haken
            : R.drawable.lem_t_iany_generic_haken;

    public static int getReadMarkerRes()
    {
        return readMarkerRes;
    }

    public static int courseMarkerRes = Simple.isTablet()
            ? R.drawable.lem_t_iany_generic_course_symbol
            : R.drawable.lem_t_iany_generic_course_symbol;

    public static int getCourseMarkerRes()
    {
        return courseMarkerRes;
    }

    public static int getStatusNewMarkerRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_iany_generic_inhalt_new
                : R.drawable.lem_t_iany_generic_inhalt_new
                ;
    }

    public static int getStatusUpdateMarkerRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_iany_generic_inhalt_update
                : R.drawable.lem_t_iany_generic_inhalt_update
                ;
    }

    public static int getStatusFailMarkerRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_iany_generic_inhalt_fehler
                : R.drawable.lem_t_iany_generic_inhalt_fehler
                ;
    }

    public static int splashScreenRes = -1;

    public static int getSplashScreenRes()
    {
        return splashScreenRes;
    }

    public static int mainScreenRes = -1;

    public static int getMainScreenRes()
    {
        return mainScreenRes;
    }

    public static Rect getMainScreenButtonRegisterRect()
    {
        return Simple.isTablet()
                ? new Rect(100, 22, 400, 82)
                : new Rect( 30, 22, 212, 82)
                ;
    }

    public static Rect getMainScreenButtonContentRect()
    {
        return Simple.isTablet()
                ? new Rect(136, 1288, 704, 1398)
                : new Rect( 70,  850, 570,  920)
                ;
    }

    public static int contentScreenHeaderRes = Simple.isTablet()
            ? R.drawable.lem_t_ipad_generic_menueoben
            : R.drawable.lem_t_ipho_generic_menueoben;

    public static int getContentScreenHeaderRes()
    {
        return contentScreenHeaderRes;
    }

    public static int arrowWhiteLeftOnRes = Simple.isTablet()
            ? R.drawable.lem_t_iany_generic_pfeillinks_weiss
            : R.drawable.lem_t_iany_generic_pfeillinks_weiss;

    public static int getArrowWhiteLeftOnRes()
    {
        return arrowWhiteLeftOnRes;
    }

    public static int arrowDarkLeftOnRes = Simple.isTablet()
            ? R.drawable.lem_t_iany_generic_pfeillinks_dunkel
            : R.drawable.lem_t_iany_generic_pfeillinks_dunkel;

    public static int getArrowDarkLeftOnRes()
    {
        return arrowDarkLeftOnRes;
    }

    public static int getArrowBannerLeftRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_iany_generic_banner_arrow_left
                : R.drawable.lem_t_iany_generic_banner_arrow_left
                ;
    }

    public static int getArrowBannerRightRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_iany_generic_banner_arrow_right
                : R.drawable.lem_t_iany_generic_banner_arrow_right
                ;
    }

    public static int contentScreenButtonBackOnRes = Simple.isTablet()
            ? R.drawable.lem_t_iany_generic_back_on
            : R.drawable.lem_t_iany_generic_back_on;

    public static int getContentScreenButtonBackOnRes()
    {
        return contentScreenButtonBackOnRes;
    }

    public static int contentScreenButtonBackOffRes = Simple.isTablet()
            ? R.drawable.lem_t_iany_generic_back_off
            : R.drawable.lem_t_iany_generic_back_off;

    public static int getContentScreenButtonBackOffRes()
    {
        return contentScreenButtonBackOffRes;
    }

    public static Rect getContentScreenBackIconRect()
    {
        if (Defines.isPierreCardin)
        {
            return Simple.isTablet()
                    ? new Rect(44, 26, 94, 76)
                    : new Rect(30, 24, 70, 64)
                    ;
        }

        return Simple.isTablet()
                ? new Rect(30, 22, 90, 82)
                : new Rect(30, 22, 90, 82)
                ;
    }

    public static Rect getContentScreenBackButtonRect()
    {
        if (Defines.isPierreCardin)
        {
            return Simple.isTablet()
                    ? new Rect(24, 6, 114, 96)
                    : new Rect(10, 10, 90, 90)
                    ;
        }

        return Simple.isTablet()
                ? new Rect(10, 10, 90, 90)
                : new Rect(10, 10, 90, 90)
                ;
    }

    public static int contentScreenButtonProfileRes = Simple.isTablet()
            ? R.drawable.lem_t_ipad_generic_profile
            : R.drawable.lem_t_ipho_generic_profile;

    public static int getContentScreenButtonProfileRes()
    {
        return contentScreenButtonProfileRes;
    }

    @Nullable
    public static Rect getContentScreenButtonProfileRect()
    {
        if (Defines.isPierreCardin)
        {
            return Simple.isTablet()
                    ? new Rect(1100, 39, 1500, 99)
                    : null
                    ;
        }

        return Simple.isTablet()
                ? new Rect(100, 22, 500, 82)
                : new Rect(100, 22, 160, 82)
                ;
    }

    @Nullable
    public static Rect getContentScreenNavigationRect()
    {
        return null;
    }
}
