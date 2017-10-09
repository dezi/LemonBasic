package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.view.SoundEffectConstants;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.MotionEvent;
import android.view.Gravity;
import android.view.View;

public class OnOffControl extends FrameLayout
{
    private static final String LOGTAG = OnOffControl.class.getSimpleName();

    protected OnChangedListener onChanged;
    protected float currentPosition;

    protected LinearLayout sliderBox;
    protected RelativeLayout sliderLeft;
    protected RelativeLayout sliderRight;

    protected LinearLayout knobBox;
    protected RelativeLayout knobLeft;
    protected RelativeLayout knobKnob;
    protected RelativeLayout knobRight;

    public OnOffControl(Context context)
    {
        super(context);

        Simple.setSizeDip(this, Simple.WC, Simple.MP);
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
        Simple.setSizeDip(sliderBox, Defines.ONOFF_WIDTH_SIZE, Defines.ONOFF_KNOB_SIZE);

        sliderCenter.addView(sliderBox);

        sliderLeft = new RelativeLayout(getContext());
        Simple.setSizeDip(sliderLeft, Simple.WC, Simple.MP, 0f);

        radiusdipse[0] = Defines.ONOFF_KNOB_SIZE;
        radiusdipse[1] = 0;
        radiusdipse[2] = 0;
        radiusdipse[3] = Defines.ONOFF_KNOB_SIZE;

        Simple.setRoundedCorners(sliderLeft, radiusdipse, Color.GRAY, true);

        sliderBox.addView(sliderLeft);

        sliderRight = new RelativeLayout(getContext());
        Simple.setSizeDip(sliderRight, Simple.WC, Simple.MP, 1f);

        radiusdipse[0] = 0;
        radiusdipse[1] = Defines.ONOFF_KNOB_SIZE;
        radiusdipse[2] = Defines.ONOFF_KNOB_SIZE;
        radiusdipse[3] = 0;

        Simple.setRoundedCorners(sliderRight, radiusdipse, Defines.COLOR_SENSOR_DKBLUE, true);

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
        Simple.setSizeDip(knobBox, Defines.ONOFF_WIDTH_SIZE, Defines.ONOFF_KNOB_SIZE);

        knobCenter.addView(knobBox);

        knobLeft = new RelativeLayout(getContext());
        Simple.setSizeDip(knobLeft, Simple.WC, Simple.MP, 0f);

        knobBox.addView(knobLeft);

        knobKnob = new RelativeLayout(getContext());
        Simple.setSizeDip(knobKnob, Defines.SLIDER_KNOB_SIZE, Defines.ONOFF_KNOB_SIZE);
        Simple.setRoundedCorners(knobKnob, Defines.ONOFF_KNOB_SIZE, Color.WHITE, Color.BLACK);

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
                }

                if ((event.getAction() == MotionEvent.ACTION_UP) || (event.getAction() == MotionEvent.ACTION_CANCEL))
                {
                    setCurrentPosition((currentPosition <= 0.5f) ? 0f : 1f);

                    view.playSoundEffect(SoundEffectConstants.CLICK);

                    if (onChanged != null) onChanged.OnChanged(OnOffControl.this, currentPosition < 0.5f);
                }

                return true;
            }
        });

        setCurrentPosition(1f);
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

        float sliderMax = currentPosition;
        if (sliderMax < 0.25f) sliderMax = 0.25f;
        if (sliderMax > 0.75f) sliderMax = 0.75f;

        Simple.setSizeDip(sliderLeft, Simple.WC, Simple.MP, sliderMax);
        Simple.setSizeDip(sliderRight, Simple.WC, Simple.MP, 1f - sliderMax);

        Simple.setSizeDip(knobLeft, Simple.WC, Simple.MP, currentPosition);
        Simple.setSizeDip(knobRight, Simple.WC, Simple.MP, 1f - currentPosition);

        sliderBox.addView(sliderLeft);
        sliderBox.addView(sliderRight);

        knobBox.addView(knobLeft);
        knobBox.addView(knobKnob);
        knobBox.addView(knobRight);
    }

    public boolean getOn()
    {
        return (currentPosition > 0.75f);
    }

    public void setOn(boolean on)
    {
        setCurrentPosition(on ? 1f : 0f);
    }

    public interface OnChangedListener
    {
        void OnChanged(View view, boolean on);
    }
}
