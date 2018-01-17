package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

@SuppressLint("Registered")
public class ContentBaseActivity extends FullScreenActivity
{
    private static final String LOGTAG = ContentBaseActivity.class.getSimpleName();

    protected LinearLayout contentFrame;
    protected FrameLayout headerFrame;
    protected ImageView headerImage;
    protected ImageView backButtonImage;
    protected ScaledButton backButton;
    protected ImageView profileButtonImage;
    protected ScaledButton profileButton;
    protected ScaledButton navigationButton;
    protected FrameLayout imageFrame;
    protected ImageView contentImage;
    protected ImageView typeIcon;
    protected RelativeLayout downloadCenter;
    protected ProgressBar downloadProgress;
    protected LinearLayout naviFrame;
    protected TopBanners topBanners;
    protected ScrollView categoryScroll;
    protected LinearLayout categoryContent;
    protected GridView assetGrid;
    protected AssetsAdapter assetsAdapter;
    protected TabBar tabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        contentFrame = new LinearLayout(this);
        contentFrame.setOrientation(LinearLayout.VERTICAL);
        contentFrame.setBackgroundColor(Defines.COLOR_CONTENT);
        Simple.setSizeDip(contentFrame, Simple.MP, Simple.MP);

        topFrame.addView(contentFrame);

        //
        // Header and profile frame.
        //

        headerFrame = new FrameLayout(this);
        Simple.setSizeDip(headerFrame, Simple.MP, Simple.WC);

        contentFrame.addView(headerFrame);

        int hdresid = DefinesScreens.getContentScreenHeaderRes();

        headerImage = new ImageView(this);
        headerImage.setLayoutParams(Simple.getFittedHorzLayout(topFrame, hdresid));
        headerImage.setImageResource(hdresid);
        headerImage.setScaleType(ImageView.ScaleType.FIT_CENTER);

        headerFrame.addView(headerImage);

        int bbresid = DefinesScreens.getContentScreenButtonBackOffRes();

        backButtonImage = new ImageView(this);
        backButtonImage.setImageResource(bbresid);
        backButtonImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        backButtonImage.setLayoutParams(Simple.getScaledHorzLayout(headerImage, DefinesScreens.getContentScreenButtonBackRect(), hdresid));

        headerFrame.addView(backButtonImage);

        backButton = new ScaledButton(this);
        backButton.setContentHorz(headerImage, DefinesScreens.getContentScreenButtonBackRect(), hdresid);

        headerFrame.addView(backButton);

        //
        // Profile.
        //

        int pfresid = DefinesScreens.getContentScreenButtonProfileRes();

        profileButtonImage = new ImageView(this);
        profileButtonImage.setImageResource(pfresid);
        profileButtonImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        profileButtonImage.setLayoutParams(Simple.getScaledHorzLayout(headerImage, DefinesScreens.getContentScreenButtonProfileRect(), hdresid));

        headerFrame.addView(profileButtonImage);

        profileButton = new ScaledButton(this);
        profileButton.setContentHorz(headerImage, DefinesScreens.getContentScreenButtonProfileRect(), hdresid);

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
        // Non mandatory navigation.
        //

        Rect naviRect = DefinesScreens.getContentScreenNavigationRect();

        if (naviRect != null)
        {
            navigationButton = new ScaledButton(this);
            navigationButton.setContentHorz(headerImage, naviRect, hdresid);

            headerFrame.addView(navigationButton);
        }

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

        downloadCenter = new RelativeLayout(this);
        downloadCenter.setVisibility(View.GONE);
        downloadCenter.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.BOTTOM);
        Simple.setSizeDip(downloadCenter, Simple.MP, Simple.MP);
        Simple.setPaddingDip(downloadCenter, Defines.PADDING_XLARGE);

        imageFrame.addView(downloadCenter);

        downloadProgress = new ProgressBar(this);
        downloadProgress.setWidthDip(Defines.PROGRESS_BAR_WIDTH);
        downloadProgress.setColors(Defines.COLOR_PROGRESS_DONE, Defines.COLOR_PROGRESS_NEED);

        downloadCenter.addView(downloadProgress);

        //
        // Navigation and multi purpose frame.
        //

        naviFrame = new LinearLayout(this);
        naviFrame.setOrientation(LinearLayout.HORIZONTAL);
        naviFrame.setBackgroundColor(Defines.COLOR_NAVIBAR);

        contentFrame.addView(naviFrame);

        //
        // Banner area.
        //

        topBanners = new TopBanners(this);

        contentFrame.addView(topBanners);

        //
        // Category sliders scrollview.
        //

        categoryScroll = new ScrollView(this);
        categoryScroll.setVisibility(View.GONE);
        Simple.setPaddingDip(categoryScroll, Defines.PADDING_SMALL);
        Simple.setSizeDip(categoryScroll, Simple.MP, Simple.MP, 1.0f);

        contentFrame.addView(categoryScroll);

        categoryContent = new LinearLayout(this);
        categoryContent.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(categoryContent, Simple.MP, Simple.WC);

        categoryScroll.addView(categoryContent);

        //
        // Asset grid.
        //

        assetGrid = new GridView(this);

        assetGrid.setNumColumns(Defines.ASSETS_NUM_COLUMNS);
        assetGrid.setColumnWidth(GridView.AUTO_FIT);
        assetGrid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        assetGrid.setVerticalSpacing(Defines.PADDING_NORMAL);
        assetGrid.setHorizontalSpacing(Defines.PADDING_NORMAL);
        assetGrid.setBackgroundColor(Defines.COLOR_CONTENT);
        Simple.setPaddingDip(assetGrid, Defines.PADDING_LARGE);
        Simple.setSizeDip(assetGrid, Simple.MP, Simple.MP, 1.0f);

        contentFrame.addView(assetGrid);

        assetsAdapter = new AssetsAdapter();
        assetGrid.setAdapter(assetsAdapter);

        //
        // Tab bar.
        //

        tabBar = new TabBar(this);

        contentFrame.addView(tabBar);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        Log.d(LOGTAG, "onResume: notifyDataSetChanged...");

        assetsAdapter.notifyDataSetChanged();
    }

    protected void showNavigationPath(String category)
    {
        showNavigationPath(category, null);
    }

    protected void showNavigationPath(String category, String title)
    {
        if (navigationButton != null)
        {
            String navipath = "";

            if (category != null)
            {
                navipath = category;
            }

            if ((title != null) && ! title.equals(navipath))
            {
                navipath += " | " + title;
            }

            navigationButton.setButtonText(Defines.PADDING_TINY, navipath);
        }
    }

    private void showProfileMenu()
    {
        ProfileMenu profilePopup = new ProfileMenu(this);
        profilePopup.setTopMargin(Simple.pxToDip(headerImage.getHeight() * 2 / 3));

        topFrame.addView(profilePopup);
    }

    protected void setBackButton(boolean enable)
    {
        if (enable)
        {
            backButtonImage.setImageResource(DefinesScreens.getContentScreenButtonBackOnRes());

            backButton.setOnButtonClicked(new Runnable()
            {
                @Override
                public void run()
                {
                    onBackPressed();
                }
            });
        }
        else
        {
            backButtonImage.setImageResource(DefinesScreens.getContentScreenButtonBackOffRes());

            backButton.setOnButtonClicked(null);
        }
    }
}
