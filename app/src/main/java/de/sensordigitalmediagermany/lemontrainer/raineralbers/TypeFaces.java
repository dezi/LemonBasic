package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

public class TypeFaces
{
    private static Map<String, Typeface> typefaces  = new HashMap<>();

    public static Typeface getTypeface(Context context, String name)
    {
        if (typefaces.get(name) == null)
        {
            typefaces.put(name, Typeface.createFromAsset(context.getAssets(), name));
        }

        return typefaces.get(name);
    }
}
