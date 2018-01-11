package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Rect;
import android.support.annotation.Nullable;

@SuppressWarnings({"WeakerAccess"})
public class Screens
{
    public static int getCloseButtonRes()
    {
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
        return Simple.isTablet()
                ? -1
                : R.drawable.lem_t_ipho_ralbers_splashscreen
                ;
    }

    public static int getMainScreenRes()
    {
        if (Defines.isPierCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_ipad_piercadin_startscreen
                    : R.drawable.lem_t_ipho_piercadin_startscreen
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
        if (Defines.isPierCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_ipad_piercadin_menuoben
                    : R.drawable.lem_t_ipad_piercadin_menuoben
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_ipad_ralbers_menueoben
                : R.drawable.lem_t_ipho_ralbers_menueoben
                ;
    }

    public static int getArrowWhiteLeftOnRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_iany_ralbers_pfeillinks_weiss
                : R.drawable.lem_t_iany_ralbers_pfeillinks_weiss
                ;
    }

    public static int getArrowDarkLeftOnRes()
    {
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
        if (Defines.isPierCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_iany_piercadin_back_on
                    : R.drawable.lem_t_iany_piercadin_back_on
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_iany_ralbers_back_on
                : R.drawable.lem_t_iany_ralbers_back_on
                ;
    }

    public static int getContentScreenButtonBackOffRes()
    {
        if (Defines.isPierCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_iany_piercadin_back_off
                    : R.drawable.lem_t_iany_piercadin_back_off
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_iany_ralbers_back_off
                : R.drawable.lem_t_iany_ralbers_back_off
                ;
    }

    public static Rect getContentScreenButtonBackRect()
    {
        if (Defines.isPierCardin)
        {
            return Simple.isTablet()
                    ? new Rect(30, 39, 90, 99)
                    : new Rect(30, 39, 90, 99)
                    ;
        }

        return Simple.isTablet()
                ? new Rect(30, 22, 90, 82)
                : new Rect(30, 22, 90, 82)
                ;
    }

    public static int getContentScreenButtonProfileRes()
    {
        if (Defines.isPierCardin)
        {
            return Simple.isTablet()
                    ? R.drawable.lem_t_ipad_piercadin_profile
                    : R.drawable.lem_t_ipad_piercadin_profile
                    ;
        }

        return Simple.isTablet()
                ? R.drawable.lem_t_ipad_ralbers_profile
                : R.drawable.lem_t_ipho_ralbers_profile
                ;
    }

    public static Rect getContentScreenButtonProfileRect()
    {
        if (Defines.isPierCardin)
        {
            return Simple.isTablet()
                    ? new Rect(1100, 39, 1500, 99)
                    : new Rect(300, 22, 360, 82)
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
        if (Defines.isPierCardin)
        {
            return Simple.isTablet()
                    ? new Rect(100, 39, 800, 99)
                    : new Rect(100, 39, 280, 99)
                    ;
        }

        return null;
    }
}
