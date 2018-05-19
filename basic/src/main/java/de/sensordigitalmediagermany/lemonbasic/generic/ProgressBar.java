package de.sensordigitalmediagermany.lemonbasic.generic;

import android.graphics.Color;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ProgressBar extends LinearLayout
{
    protected RelativeLayout progressBar;
    protected RelativeLayout forceBar;

    public ProgressBar(Context context)
    {
        super(context);

        setOrientation(HORIZONTAL);

        Simple.setSizeDip(this, Simple.MP, Defines.PROGRESS_BAR_SIZE);

        progressBar = new RelativeLayout(getContext());
        progressBar.setBackgroundColor(Defines.COLOR_SENSOR_DKBLUE);
        Simple.setSizeDip(progressBar, Simple.WC, Simple.MP, 0f);

        addView(progressBar);

        forceBar = new RelativeLayout(getContext());
        forceBar.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(forceBar, Simple.WC, Simple.MP, 1f);

        addView(forceBar);
    }

    public void setWidthDip(int width)
    {
        Simple.setSizeDip(this, width, Defines.PROGRESS_BAR_SIZE);
    }

    public void setColors(int currentColor, int backgroundColor)
    {
        progressBar.setBackgroundColor(currentColor);
        forceBar.setBackgroundColor(backgroundColor);
    }

    public void setProgress(int current, int total)
    {
        setProgressLong(current, total);
    }

    public void setProgressLong(long current, long total)
    {
        if (total > 0)
        {
            float actval = current / (float) total;

            Simple.setSizeDip(progressBar, Simple.WC, Simple.MP, actval);
            Simple.setSizeDip(forceBar, Simple.WC, Simple.MP, 1f - actval);

            removeAllViews();

            addView(progressBar);
            addView(forceBar);
        }
    }
}
