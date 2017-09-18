package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.graphics.Color;
import android.os.Bundle;

public class ContentActivity extends FullScreenActivity
{
    protected LinearLayout contentFrame;
    protected FrameLayout headerFrame;
    protected ImageView backButtonImage;
    protected ImageView profileButtonImage;
    protected ImageView headerImage;
    protected FrameLayout naviFrame;
    protected ImageView naviImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        contentFrame = new LinearLayout(this);
        contentFrame.setOrientation(LinearLayout.VERTICAL);
        contentFrame.setBackgroundColor(Defines.COLOR_SENSOR_CONTENT);
        Simple.setSizeDip(contentFrame, Simple.MP, Simple.MP);

        topFrame.addView(contentFrame);

        headerFrame = new FrameLayout(this);
        Simple.setSizeDip(headerFrame, Simple.MP, Simple.WC);

        contentFrame.addView(headerFrame);

        int hdresid = Screens.getContentScreenHeaderRes();

        headerImage = new ImageView(this);
        headerImage.setLayoutParams(Simple.getFittedHorzLayout(topFrame, hdresid));
        headerImage.setImageResource(hdresid);
        headerImage.setScaleType(ImageView.ScaleType.FIT_CENTER);

        headerFrame.addView(headerImage);

        int bbresid = Screens.getContentScreenButtonBackOffRes();

        backButtonImage = new ImageView(this);
        backButtonImage.setImageResource(bbresid);
        backButtonImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        backButtonImage.setLayoutParams(Simple.getScaledHorzLayout(headerImage, Screens.getContentScreenButtonBackRect(), hdresid));

        headerFrame.addView(backButtonImage);

        ScaledButton backButton = new ScaledButton(this);
        backButton.setContentHorz(headerImage, Screens.getContentScreenButtonBackRect(), hdresid);

        backButton.setOnButtonClicked(new Runnable()
        {
            @Override
            public void run()
            {
                Simple.startActivityTop(ContentActivity.this, MainActivity.class);
            }
        });

        headerFrame.addView(backButton);

        int pfresid = Screens.getContentScreenButtonProfileRes();

        profileButtonImage = new ImageView(this);
        profileButtonImage.setImageResource(pfresid);
        profileButtonImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        profileButtonImage.setLayoutParams(Simple.getScaledHorzLayout(headerImage, Screens.getContentScreenButtonProfileRect(), hdresid));

        headerFrame.addView(profileButtonImage);

        ScaledButton profileButton = new ScaledButton(this);
        profileButton.setContentHorz(headerImage, Screens.getContentScreenButtonProfileRect(), hdresid);

        profileButton.setOnButtonClicked(new Runnable()
        {
            @Override
            public void run()
            {
            }
        });

        headerFrame.addView(profileButton);
    }
}
