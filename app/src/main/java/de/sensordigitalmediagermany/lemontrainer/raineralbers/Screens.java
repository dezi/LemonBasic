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

    public static int getContentScreenHeader()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_ipho_header_mpfeil
                : R.drawable.lem_t_ipho_header_mpfeil
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
                ? new Rect(5, 5, 315, 55)
                : new Rect(5, 5, 315, 55)
                ;
    }

    public static Rect getContentScreenNaviButtonRightRect()
    {
        return Simple.isTablet()
                ? new Rect(325, 5, 635, 55)
                : new Rect(325, 5, 635, 55)
                ;
    }
}
