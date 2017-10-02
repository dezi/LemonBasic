package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Iterator;

public class ContentBaseActivity extends FullScreenActivity
{
    private static final String LOGTAG = ContentBaseActivity.class.getSimpleName();

    protected LinearLayout contentFrame;
    protected FrameLayout headerFrame;
    protected ImageView headerImage;
    protected ImageView backButtonImage;
    protected ImageView profileButtonImage;
    protected ScaledButton profileButton;
    protected FrameLayout imageFrame;
    protected ImageView contentImage;
    protected ImageView typeIcon;
    protected LinearLayout naviFrame;
    protected GridView assetGrid;
    protected AssetsAdapter assetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        contentFrame = new LinearLayout(this);
        contentFrame.setOrientation(LinearLayout.VERTICAL);
        contentFrame.setBackgroundColor(Defines.COLOR_SENSOR_CONTENT);
        Simple.setSizeDip(contentFrame, Simple.MP, Simple.MP);

        topFrame.addView(contentFrame);

        //
        // Header and profile frame.
        //

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
                Simple.startActivityTop(ContentBaseActivity.this, MainActivity.class);
            }
        });

        headerFrame.addView(backButton);

        int pfresid = Screens.getContentScreenButtonProfileRes();

        profileButtonImage = new ImageView(this);
        profileButtonImage.setImageResource(pfresid);
        profileButtonImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        profileButtonImage.setLayoutParams(Simple.getScaledHorzLayout(headerImage, Screens.getContentScreenButtonProfileRect(), hdresid));

        headerFrame.addView(profileButtonImage);

        profileButton = new ScaledButton(this);
        profileButton.setContentHorz(headerImage, Screens.getContentScreenButtonProfileRect(), hdresid);

        if (Globals.accountId > 0)
        {
            String username = Globals.firstName + " " + Globals.lastName;
            profileButton.setButtonText(Defines.PADDING_XLARGE, username);

            profileButton.setOnButtonClicked(new Runnable()
            {
                @Override
                public void run()
                {
                    showProfileMenu();
                }
            });
        }

        headerFrame.addView(profileButton);

        //
        // Frame for image and image type icon.
        //
        
        imageFrame = new FrameLayout(this);

        contentFrame.addView(imageFrame);

        contentImage = new ImageView(this);
        contentImage.setScaleType(ImageView.ScaleType.FIT_XY);

        imageFrame.addView(contentImage);

        RelativeLayout iconCenter = new RelativeLayout(this);
        iconCenter.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(iconCenter, Simple.MP, Simple.MP);

        imageFrame.addView(iconCenter);

        typeIcon = new ImageView(this);
        typeIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);

        iconCenter.addView(typeIcon);

        //
        // Navigation and multi purpose frame.
        //

        naviFrame = new LinearLayout(this);
        naviFrame.setOrientation(LinearLayout.HORIZONTAL);
        naviFrame.setBackgroundColor(Defines.COLOR_SENSOR_NAVIBAR);

        contentFrame.addView(naviFrame);

        //
        // Asset grid.
        //

        assetGrid = new GridView(this);

        assetGrid.setNumColumns(Defines.ASSETS_NUM_COLUMNS);
        assetGrid.setColumnWidth(GridView.AUTO_FIT);
        assetGrid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        assetGrid.setVerticalSpacing(0);
        assetGrid.setHorizontalSpacing(0);
        assetGrid.setBackgroundColor(Defines.COLOR_SENSOR_CONTENT);
        Simple.setPaddingDip(assetGrid, Defines.PADDING_SMALL);
        Simple.setSizeDip(assetGrid, Simple.MP, Simple.MP);

        contentFrame.addView(assetGrid);

        assetsAdapter = new AssetsAdapter();
        assetGrid.setAdapter(assetsAdapter);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        Log.d(LOGTAG, "onResume: notifyDataSetChanged...");

        assetsAdapter.notifyDataSetChanged();
    }

    private void showProfileMenu()
    {
        ProfileMenu profilePopup = new ProfileMenu(this);
        profilePopup.setTopMargin(Simple.pxToDip(headerImage.getHeight() * 2 / 3));

        topFrame.addView(profilePopup);
    }
}
