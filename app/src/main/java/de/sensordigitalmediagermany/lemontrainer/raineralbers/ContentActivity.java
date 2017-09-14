package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.os.Bundle;

public class ContentActivity extends FullScreenActivity
{
    protected LinearLayout contentFrame;
    protected ImageView headerImage;
    protected ImageView naviImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        contentFrame = new LinearLayout(this);
        contentFrame.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(contentFrame, Simple.MP, Simple.MP);

        topFrame.addView(contentFrame);

        int hdresid = Screens.getContentScreenHeaderMPfeilRes();

        headerImage = new ImageView(this);
        headerImage.setLayoutParams(Simple.getFittedHorzLayout(topFrame,Screens.getContentScreenHeaderMPfeilRes()));
        headerImage.setImageResource(hdresid);
        headerImage.setScaleType(ImageView.ScaleType.FIT_CENTER);

        contentFrame.addView(headerImage);

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

        topFrame.addView(backButton);

        ScaledButton profileButton = new ScaledButton(this);
        profileButton.setContent(headerImage, Screens.getContentScreenButtonProfileRect(), hdresid);

        profileButton.setOnButtonClicked(new Runnable()
        {
            @Override
            public void run()
            {
            }
        });

        topFrame.addView(profileButton);

        int nvresid = Screens.getContentScreenNaviAllContentRes();

        naviImage = new ImageView(this);
        naviImage.setLayoutParams(Simple.getFittedHorzLayout(topFrame,Screens.getContentScreenNaviAllContentRes()));
        naviImage.setImageResource(nvresid);
        naviImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        naviImage.setBackgroundColor(Color.WHITE);

        contentFrame.addView(naviImage);

        ScaledButton naviLeftButton = new ScaledButton(this);
        naviLeftButton.setContent(naviImage, Screens.getContentScreenNaviButtonLeftRect(), nvresid);
        naviLeftButton.setBackgroundColor(0x88880000);

        naviLeftButton.setOnButtonClicked(new Runnable()
        {
            @Override
            public void run()
            {
            }
        });

        topFrame.addView(naviLeftButton);

        ScaledButton naviRightButton = new ScaledButton(this);
        naviRightButton.setContent(naviImage, Screens.getContentScreenNaviButtonRightRect(), nvresid);
        naviRightButton.setBackgroundColor(0x88880000);

        naviRightButton.setOnButtonClicked(new Runnable()
        {
            @Override
            public void run()
            {
            }
        });

        topFrame.addView(naviRightButton);
    }
}
