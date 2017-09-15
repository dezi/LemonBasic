package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Rect;

public class Screens
{
    public static int getCloseButtonRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_ipad_ralbers_kreuz
                : R.drawable.lem_t_ipad_ralbers_kreuz
                ;
    }

    public static int getMainScreenRes()
    {
        return Simple.isTablet()
                ? R.drawable.lem_t_ipad_ralbers_splashscreen
                : R.drawable.lem_t_ipho_ralbers_splashscreen
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
        return Simple.isTablet()
                ? R.drawable.lem_t_ipad_ralbers_menueoben
                : R.drawable.lem_t_ipad_ralbers_menueoben
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
}
