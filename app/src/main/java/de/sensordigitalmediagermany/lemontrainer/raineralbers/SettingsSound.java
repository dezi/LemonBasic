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

        TextView soundSection = new TextView(context);
        soundSection.setText(R.string.settings_sound);
        soundSection.setAllCaps(true);
        soundSection.setTextColor(Color.BLACK);
        soundSection.setTypeface(TypeFaces.getTypeface(context, Defines.FONT_SETTINGS_HEADER));
        Simple.setSizeDip(soundSection, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(soundSection, Defines.PADDING_SMALL);
        Simple.setTextSizeDip(soundSection, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(soundSection, Defines.FS_NAVIGATION_LSSPACE);

        addView(soundSection);

        LinearLayout onoffArea = new LinearLayout(context);
        onoffArea.setOrientation(LinearLayout.HORIZONTAL);
        onoffArea.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(onoffArea, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(onoffArea, Defines.PADDING_TINY);

        addView(onoffArea);

        TextView onoffText = new TextView(context);
        onoffText.setText(R.string.settings_sound_button);
        onoffText.setAllCaps(true);
        onoffText.setTextColor(Color.BLACK);
        onoffText.setTypeface(TypeFaces.getTypeface(context, Defines.FONT_SETTINGS_HEADER));
        Simple.setSizeDip(onoffText, Simple.MP, Simple.WC, 1.0f);
        Simple.setTextSizeDip(onoffText, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(onoffText, Defines.FS_NAVIGATION_LSSPACE);
        Simple.setPaddingDip(onoffText, Defines.PADDING_SMALL);

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
        Simple.setMarginTopDip(volumeArea, Defines.PADDING_TINY);

        addView(volumeArea);

        TextView volumeText = new TextView(context);
        volumeText.setText(R.string.settings_sound_volume);
        volumeText.setAllCaps(true);
        volumeText.setTextColor(Color.BLACK);
        volumeText.setTypeface(TypeFaces.getTypeface(context, Defines.FONT_SETTINGS_HEADER));
        Simple.setSizeDip(volumeText, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(volumeText, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(volumeText, Defines.FS_NAVIGATION_LSSPACE);
        Simple.setPaddingDip(volumeText, Defines.PADDING_SMALL);

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
