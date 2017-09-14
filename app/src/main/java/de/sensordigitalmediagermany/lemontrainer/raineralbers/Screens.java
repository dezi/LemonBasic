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
}
