package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.MotionEvent;
import android.view.Gravity;
import android.view.View;
import android.util.Log;

import org.json.JSONObject;

public class SliderControl extends FrameLayout
{
    private static final String LOGTAG = SliderControl.class.getSimpleName();

    protected OnChangedListener onChanged;
    protected float currentPosition;

    protected LinearLayout sliderBox;
    protected RelativeLayout sliderLeft;
    protected RelativeLayout sliderRight;

    protected LinearLayout knobBox;
    protected RelativeLayout knobLeft;
    protected RelativeLayout knobKnob;
    protected RelativeLayout knobRight;

    public SliderControl(Context context)
    {
        super(context);

        Simple.setSizeDip(this, Simple.MP, Simple.MP);
        Simple.setPaddingDip(this, Defines.PADDING_SMALL, 0, Defines.PADDING_SMALL, 0);

        int radiusdipse[] = new int[4];

        //
        // Slider.
        //

        RelativeLayout sliderCenter = new RelativeLayout(getContext());
        sliderCenter.setGravity(Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(sliderCenter, Simple.MP, Simple.MP);
        addView(sliderCenter);

        sliderBox = new LinearLayout(getContext());
        sliderBox.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(sliderBox, Simple.MP, Defines.SLIDER_BARS_SIZE);

        sliderCenter.addView(sliderBox);

        sliderLeft = new RelativeLayout(getContext());
        Simple.setSizeDip(sliderLeft, Simple.WC, Simple.MP, 0f);

        radiusdipse[0] = Defines.CORNER_RADIUS_ASSETS;
        radiusdipse[1] = 0;
        radiusdipse[2] = 0;
        radiusdipse[3] = Defines.CORNER_RADIUS_ASSETS;

        Simple.setRoundedCorners(sliderLeft, radiusdipse, Defines.COLOR_SENSOR_DKBLUE, true);

        sliderBox.addView(sliderLeft);

        sliderRight = new RelativeLayout(getContext());
        Simple.setSizeDip(sliderRight, Simple.WC, Simple.MP, 1f);

        radiusdipse[0] = 0;
        radiusdipse[1] = Defines.CORNER_RADIUS_ASSETS;
        radiusdipse[2] = Defines.CORNER_RADIUS_ASSETS;
        radiusdipse[3] = 0;

        Simple.setRoundedCorners(sliderRight, radiusdipse, Color.GRAY, true);

        sliderBox.addView(sliderRight);

        //
        // Knob.
        //

        RelativeLayout knobCenter = new RelativeLayout(getContext());
        knobCenter.setGravity(Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(knobCenter, Simple.MP, Simple.MP);
        addView(knobCenter);

        knobBox = new LinearLayout(getContext());
        knobBox.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(knobBox, Simple.MP, Defines.SLIDER_KNOB_SIZE);

        knobCenter.addView(knobBox);

        knobLeft = new RelativeLayout(getContext());
        Simple.setSizeDip(knobLeft, Simple.WC, Simple.MP, 0f);

        knobBox.addView(knobLeft);

        knobKnob = new RelativeLayout(getContext());
        Simple.setSizeDip(knobKnob, Defines.SLIDER_KNOB_SIZE, Defines.SLIDER_KNOB_SIZE);
        Simple.setRoundedCorners(knobKnob, Defines.SLIDER_KNOB_SIZE, Color.WHITE, true);

        knobBox.addView(knobKnob);

        knobRight = new RelativeLayout(getContext());
        Simple.setSizeDip(knobRight, Simple.WC, Simple.MP, 1f);

        knobBox.addView(knobRight);

        setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    int total = getWidth();

                    int current = Math.round(event.getX());
                    if (current < 0) current = 0;
                    if (current > total) current = total;

                    //Log.d(LOGTAG, "onTouch: total=" + total + " current=" + current);

                    setCurrentPosition(current / (float) total);

                    if (onChanged != null) onChanged.OnChanged(SliderControl.this, currentPosition);
                }

                return true;
            }
        });

        setCurrentPosition(0.25f);
    }

    public void setOnChangedListener(OnChangedListener onChanged)
    {
        this.onChanged = onChanged;
    }

    public void setCurrentPosition(float currentPosition)
    {
        this.currentPosition = currentPosition;

        sliderBox.removeAllViews();
        knobBox.removeAllViews();

        Simple.setSizeDip(sliderLeft, Simple.WC, Simple.MP, currentPosition);
        Simple.setSizeDip(sliderRight, Simple.WC, Simple.MP, 1f - currentPosition);

        Simple.setSizeDip(knobLeft, Simple.WC, Simple.MP, currentPosition);
        Simple.setSizeDip(knobRight, Simple.WC, Simple.MP, 1f - currentPosition);

        sliderBox.addView(sliderLeft);
        sliderBox.addView(sliderRight);

        knobBox.addView(knobLeft);
        knobBox.addView(knobKnob);
        knobBox.addView(knobRight);
    }

    public interface OnChangedListener
    {
        void OnChanged(View view, float currentPosition);
    }
}
