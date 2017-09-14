package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Rect;

public class Screens
{
    public static int getSplashScreenRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_ipho_splashscreen0
                : R.drawable.lem_t_ipho_splashscreen0
                ;
    }

    public static int getMainScreenRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_ipad_splashscreen
                : R.drawable.lem_t_ipho_splashscreen
                ;
    }

    public static Rect getMainScreenButtonLoginRect()
    {
        return Simple.isTablet()
                ? new Rect(100, 22, 400, 82)
                : new Rect( 30, 22, 212, 82)
                ;
    }

    public static Rect getMainScreenButtonContentRect()
    {
        return Simple.isTablet()
                ? new Rect(1419, 1168, 1854, 1279)
                : new Rect(  70,  850,  570,  920)
                ;
    }

    public static int getContentScreenHeaderOPfeilRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_ipho_header_opfeil
                : R.drawable.lem_t_ipho_header_opfeil
                ;
    }

    public static int getContentScreenHeaderOPfeilActiveRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_ipho_header_aktiv_opfeil
                : R.drawable.lem_t_ipho_header_aktiv_opfeil
                ;
    }

    public static int getContentScreenHeaderMPfeilRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_ipho_header_mpfeil
                : R.drawable.lem_t_ipho_header_mpfeil
                ;
    }

    public static int getContentScreenHeaderMPfeilActiveRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_ipho_header_aktiv_mpfeil
                : R.drawable.lem_t_ipho_header_aktiv_mpfeil
                ;
    }

    public static Rect getContentScreenButtonBackRect()
    {
        return Simple.isTablet()
                ? new Rect(30, 22,  90, 82)
                : new Rect(30, 22,  90, 82)
                ;
    }

    public static Rect getContentScreenButtonProfileRect()
    {
        return Simple.isTablet()
                ? new Rect(100, 22,  160, 82)
                : new Rect(100, 22,  160, 82)
                ;
    }

    public static int getContentScreenNaviAllContentRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_ipho_header_navizeile1
                : R.drawable.lem_t_ipho_header_navizeile1
                ;
    }

    public static int getContentScreenNaviMyContentRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_ipho_header_navizeile2
                : R.drawable.lem_t_ipho_header_navizeile2
                ;
    }

    public static Rect getContentScreenNaviButtonLeftRect()
    {
        return Simple.isTablet()
                ? new Rect(10, 10, 300, 40)
                : new Rect(10, 10, 300, 40)
                ;
    }

    public static Rect getContentScreenNaviButtonRightRect()
    {
        return Simple.isTablet()
                ? new Rect(330, 10, 630, 40)
                : new Rect(330, 10, 630, 40)
                ;
    }
}
