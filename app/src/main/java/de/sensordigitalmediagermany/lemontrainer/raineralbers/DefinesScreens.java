package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.support.annotation.Nullable;
import android.graphics.Rect;

@SuppressWarnings({"WeakerAccess"})
public class DefinesScreens extends Defines
{
    public static int getNotifyIconSmallRes()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_iany_pierrecardin_notification
                    : R.drawable.lem_t_iany_pierrecardin_notification
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_iany_generic_notification
                : R.drawable.lem_t_iany_generic_notification
                ;
    }

    public static int getNotifyIconLargeRes()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? R.mipmap.lem_t_pierre_cardin
                    : R.mipmap.lem_t_pierre_cardin
                    ;
        }

        if (isRainerAlbers)
        {
            return Simple.isTablet()
                    ? R.mipmap.lem_t_rainer_albers
                    : R.mipmap.lem_t_rainer_albers
                    ;
        }

        return Simple.isTablet()
                ? R.mipmap.lem_t_demo
                : R.mipmap.lem_t_demo
                ;
    }

    public static int getCloseButtonRes()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_iany_pierrecardin_kreuz
                    : R.drawable.lem_t_iany_pierrecardin_kreuz
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_iany_ralbers_kreuz
                : R.drawable.lem_t_iany_ralbers_kreuz
                ;
    }

    public static int getConfirmedIconRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_iany_ralbers_buy_confirmed
                : R.drawable.lem_t_iany_ralbers_buy_confirmed
                ;
    }

    public static int getReadMarkerRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_iany_ralbers_haken
                : R.drawable.lem_t_iany_ralbers_haken
                ;
    }

    public static int getCourseMarkerRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_iany_ralbers_course_symbol
                : R.drawable.lem_t_iany_ralbers_course_symbol
                ;
    }

    public static int getSplashScreenRes()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? -1
                    : -1
                    ;
        }

        return Simple.isTablet()
                ? -1
                : R.drawable.lem_t_ipho_ralbers_splashscreen
                ;
    }

    public static int getMainScreenRes()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_ipad_pierrecardin_startscreen
                    : R.drawable.lem_t_ipho_pierrecardin_startscreen
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_ipad_ralbers_startscreen
                : R.drawable.lem_t_ipho_ralbers_startscreen
                ;
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

    public static int getContentScreenHeaderRes()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_ipad_pierrecardin_menuoben
                    : R.drawable.lem_t_ipho_pierrecardin_menuoben
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_ipad_ralbers_menueoben
                : R.drawable.lem_t_ipho_ralbers_menueoben
                ;
    }

    public static int getArrowWhiteLeftOnRes()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_iany_pierrecardin_pfeillinks_weiss
                    : R.drawable.lem_t_iany_pierrecardin_pfeillinks_weiss
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_iany_ralbers_pfeillinks_weiss
                : R.drawable.lem_t_iany_ralbers_pfeillinks_weiss
                ;
    }

    public static int getArrowDarkLeftOnRes()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_iany_pierrecardin_pfeillinks_dunkel
                    : R.drawable.lem_t_iany_pierrecardin_pfeillinks_dunkel
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_iany_ralbers_pfeillinks_dunkel
                : R.drawable.lem_t_iany_ralbers_pfeillinks_dunkel
                ;
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

    public static int getContentScreenButtonBackOnRes()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_iany_pierrecardin_back_on
                    : R.drawable.lem_t_iany_pierrecardin_back_on
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_iany_ralbers_back_on
                : R.drawable.lem_t_iany_ralbers_back_on
                ;
    }

    public static int getContentScreenButtonBackOffRes()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_iany_pierrecardin_back_off
                    : R.drawable.lem_t_iany_pierrecardin_back_off
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_iany_ralbers_back_off
                : R.drawable.lem_t_iany_ralbers_back_off
                ;
    }

    public static Rect getContentScreenBackIconRect()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? new Rect(40, 24, 96, 80)
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
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? new Rect(10, 10, 90, 90)
                    : new Rect(10, 10, 90, 90)
                    ;
        }

        return Simple.isTablet()
                ? new Rect(10, 10, 90, 90)
                : new Rect(10, 10, 90, 90)
                ;
    }

    public static int getContentScreenButtonProfileRes()
    {
        if (isPierreCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_ipad_pierrecardin_profile
                    : -1
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_ipad_ralbers_profile
                : R.drawable.lem_t_ipho_ralbers_profile
                ;
    }

    @Nullable
    public static Rect getContentScreenButtonProfileRect()
    {
        if (isPierreCardin)
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
        if (isPierreCardin)
        {
            /*
            return Simple.isTablet()
                    ? new Rect(100, 39, 800, 99)
                    : new Rect(70, 25, 300, 75)
                    ;
            */

            return null;
        }

        return null;
    }
}
