package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.view.View;
import android.util.Log;

public class SettingsSound extends LinearLayout
{
    private static final String LOGTAG = SettingsSound.class.getSimpleName();

    public SettingsSound(Context context)
    {
        super(context);

        setOrientation(VERTICAL);

        Simple.setSizeDip(this, Simple.MP, Simple.WC);

        SettingsInfoHeader soundSection = new SettingsInfoHeader(context);
        soundSection.setText(R.string.settings_sound);

        addView(soundSection);

        LinearLayout onoffArea = new LinearLayout(context);
        onoffArea.setOrientation(LinearLayout.HORIZONTAL);
        onoffArea.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(onoffArea, Simple.MP, Simple.WC);
        Simple.setPaddingDip(onoffArea, Defines.PADDING_SMALL);
        Simple.setMarginTopDip(onoffArea, Defines.PADDING_TINY);

        addView(onoffArea);

        SettingsInfoHeader onoffText = new SettingsInfoHeader(context);
        onoffText.setText(R.string.settings_sound_button);
        onoffText.setMarginTopDip(Defines.PADDING_ZERO);
        onoffText.setFullHeight(true);
        onoffText.setWeight(1.0f);

        onoffArea.addView(onoffText);

        final OnOffControl onoffControl = new OnOffControl(context);

        onoffControl.setOnChangedListener(new OnOffControl.OnChangedListener()
        {
            @Override
            public void OnChanged(View view, boolean on)
            {
                Log.d(LOGTAG, "onoffControl: OnChanged: on=" + on);
            }
        });

        onoffArea.addView(onoffControl);

        onoffArea.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onoffControl.setOn(! onoffControl.getOn());
            }
        });

        LinearLayout volumeArea = new LinearLayout(context);
        volumeArea.setOrientation(LinearLayout.HORIZONTAL);
        volumeArea.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(volumeArea, Simple.MP, Simple.WC);
        Simple.setPaddingDip(volumeArea, Defines.PADDING_SMALL);
        Simple.setMarginTopDip(volumeArea, Defines.PADDING_TINY);

        addView(volumeArea);

        SettingsInfoHeader volumeText = new SettingsInfoHeader(context);
        volumeText.setText(R.string.settings_sound_volume);
        volumeText.setMarginTopDip(Defines.PADDING_ZERO);
        volumeText.setFullWidth(false);
        volumeText.setFullHeight(true);

        volumeArea.addView(volumeText);

        SliderControl volumeControl = new SliderControl(context);

        volumeControl.setOnChangedListener(new SliderControl.OnChangedListener()
        {
            @Override
            public void OnChanged(View view, float currentPosition)
            {
                Log.d(LOGTAG, "volumeControl: OnChanged: current=" + currentPosition);
            }
        });

        volumeArea.addView(volumeControl);
    }
}
