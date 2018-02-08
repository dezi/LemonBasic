package de.sensordigitalmediagermany.lemonbasic.generic;

import android.view.View;

public interface GenericFocus
{
    void setFocusable(boolean focusable);
    boolean getFocusable();

    void setBackgroundColor(int color);
    int getBackgroundColor();

    View.OnFocusChangeListener getOnFocusChangeListener();
    void setOnFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener);
}
