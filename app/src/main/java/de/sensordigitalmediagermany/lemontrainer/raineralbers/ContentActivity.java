package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ContentActivity extends FullScreenActivity
{
    protected FrameLayout topFrame;
    protected LinearLayout contentFrame;
    protected ImageView headerImage;
    protected ImageView naviImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        topFrame = new FrameLayout(this);
        topFrame.setLayoutParams(new FrameLayout.LayoutParams(Simple.MP, Simple.MP));
        topFrame.setBackgroundColor(Defines.COLOR_SENSOR_BLUE);

        setContentView(topFrame);

        contentFrame = new LinearLayout(this);
        contentFrame.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(contentFrame, Simple.MP, Simple.MP);

        topFrame.addView(contentFrame);

        headerImage = new ImageView(this);
        headerImage.setLayoutParams(Simple.getFittedLayout(this,Screens.getContentScreenHeaderMPfeilRes()));
        headerImage.setImageResource(Screens.getContentScreenHeaderMPfeilRes());
        headerImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        headerImage.setBackgroundColor(0x88880000);
        contentFrame.addView(headerImage);

        naviImage = new ImageView(this);
        naviImage.setLayoutParams(Simple.getFittedLayout(this,Screens.getContentScreenNaviAllContentRes()));
        naviImage.setImageResource(Screens.getContentScreenNaviAllContentRes());
        naviImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        naviImage.setBackgroundColor(Color.WHITE);

        contentFrame.addView(naviImage);
    }
}
