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

        int hdresid = Screens.getContentScreenHeaderMPfeilRes();

        headerImage = new ImageView(this);
        headerImage.setLayoutParams(Simple.getFittedHorzLayout(topFrame,Screens.getContentScreenHeaderMPfeilRes()));
        headerImage.setImageResource(hdresid);
        headerImage.setScaleType(ImageView.ScaleType.FIT_CENTER);

        headerFrame.addView(headerImage);

        ScaledButton backButton = new ScaledButton(this);
        backButton.setContent(headerImage, Screens.getContentScreenButtonBackRect(), hdresid);

        backButton.setOnButtonClicked(new Runnable()
        {
            @Override
            public void run()
            {
                Simple.startActivityTop(ContentActivity.this, MainActivity.class);
            }
        });

        headerFrame.addView(backButton);

        ScaledButton profileButton = new ScaledButton(this);
        profileButton.setContent(headerImage, Screens.getContentScreenButtonProfileRect(), hdresid);

        profileButton.setOnButtonClicked(new Runnable()
        {
            @Override
            public void run()
            {
            }
        });

        headerFrame.addView(profileButton);

        naviFrame = new FrameLayout(this);
        Simple.setSizeDip(naviFrame, Simple.MP, Simple.WC);

        contentFrame.addView(naviFrame);

        int nvresid = Screens.getContentScreenNaviAllContentRes();

        naviImage = new ImageView(this);
        naviImage.setLayoutParams(Simple.getFittedHorzLayout(topFrame,Screens.getContentScreenNaviAllContentRes()));
        naviImage.setImageResource(nvresid);
        naviImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        naviImage.setBackgroundColor(Color.WHITE);

        naviFrame.addView(naviImage);

        ScaledButton naviLeftButton = new ScaledButton(this);
        naviLeftButton.setContent(naviImage, Screens.getContentScreenNaviButtonLeftRect(), nvresid);

        naviLeftButton.setOnButtonClicked(new Runnable()
        {
            @Override
            public void run()
            {
            }
        });

        naviFrame.addView(naviLeftButton);

        ScaledButton naviRightButton = new ScaledButton(this);
        naviRightButton.setContent(naviImage, Screens.getContentScreenNaviButtonRightRect(), nvresid);

        naviRightButton.setOnButtonClicked(new Runnable()
        {
            @Override
            public void run()
            {
            }
        });

        naviFrame.addView(naviRightButton);
    }
}
