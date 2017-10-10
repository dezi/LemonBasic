package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.content.Context;
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

    public void setProgress(int current, int total)
    {
        float actval = current / (float) total;

        Simple.setSizeDip(progressBar, Simple.WC, Simple.MP, actval);
        Simple.setSizeDip(forceBar, Simple.WC, Simple.MP, 1f - actval);

        removeAllViews();

        addView(progressBar);
        addView(forceBar);
    }
}
