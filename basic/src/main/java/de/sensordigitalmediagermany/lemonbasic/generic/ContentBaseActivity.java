package de.sensordigitalmediagermany.lemonbasic.generic;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;
import android.util.Log;

@SuppressLint("Registered")
public class ContentBaseActivity extends FullScreenActivity
{
    private static final String LOGTAG = ContentBaseActivity.class.getSimpleName();

    protected LinearLayout contentFrame;
    protected FrameLayout headerFrame;
    protected ImageView headerImage;
    protected ImageView backButtonImage;
    protected ScaledButton backButton;
    protected ScaledButton navigationButton;
    protected FrameLayout imageFrame;
    protected ImageView contentImage;
    protected ImageView typeIcon;
    protected RelativeLayout downloadCenter;
    protected ProgressBar downloadProgress;
    protected LinearLayout naviFrame;
    protected TopBanners topBanners;
    protected ScrollView contentScroll;
    protected CategoryContent categoryContent;
    protected GenericGridView assetGrid;
    protected AssetsAdapter assetsAdapter;
    protected TabBar tabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        contentFrame = new LinearLayout(this);
        contentFrame.setFocusable(false);
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
        backButtonImage.setLayoutParams(Simple.getScaledHorzLayout(headerImage, Screens.getContentScreenBackIconRect(), hdresid));
        backButtonImage.setVisibility(View.GONE);

        headerFrame.addView(backButtonImage);

        backButton = new ScaledButton(this);

        backButton.setVisibility(View.GONE);
        backButton.setContentHorz(headerImage, Screens.getContentScreenBackButtonRect(), hdresid);

        headerFrame.addView(backButton);

        //
        // Profile.
        //

        int pfresid = Screens.getContentScreenButtonProfileRes();

        if (pfresid > 0)
        {
            ImageView profileButtonImage = new ImageView(this);
            profileButtonImage.setImageResource(pfresid);
            profileButtonImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
            profileButtonImage.setLayoutParams(Simple.getScaledHorzLayout(headerImage, Screens.getContentScreenButtonProfileRect(), hdresid));

            headerFrame.addView(profileButtonImage);

            ScaledButton profileButton = new ScaledButton(this);

            profileButton.setContentHorz(headerImage, Screens.getContentScreenButtonProfileRect(), hdresid);

            if (Globals.accountId > 0)
            {
                if (Simple.isTablet())
                {
                    String username = Globals.firstName + " " + Globals.lastName;
                    profileButton.setButtonText(Defines.PADDING_XLARGE, username);
                }

                if (Defines.isUserMenu)
                {
                    profileButton.setOnButtonClicked(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            showProfileMenu();
                        }
                    });
                }
            }

            headerFrame.addView(profileButton);
        }

        //
        // Non mandatory navigation.
        //

        Rect naviRect = Screens.getContentScreenNavigationRect();

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
        naviFrame.setFocusable(false);
        naviFrame.setOrientation(LinearLayout.HORIZONTAL);
        naviFrame.setBackgroundColor(Defines.COLOR_NAVIBAR);

        contentFrame.addView(naviFrame);

        //
        // Category sliders scrollview.
        //

        contentScroll = new ScrollView(this);
        contentScroll.setFocusable(false);
        contentScroll.setVisibility(View.GONE);
        Simple.setSizeDip(contentScroll, Simple.MP, Simple.MP, 1.0f);
        Simple.setPaddingDip(contentScroll, Defines.PADDING_SMALL);

        contentFrame.addView(contentScroll);

        categoryContent = new CategoryContent(this);

        contentScroll.addView(categoryContent);

        //
        // Banner area.
        //

        topBanners = new TopBanners(this);

        categoryContent.addView(topBanners);

        //
        // Asset grid.
        //

        assetGrid = new GenericGridView(this);

        assetGrid.setNumColumns(Defines.ASSETS_NUM_COLUMNS);
        assetGrid.setVerticalSpacing(Defines.PADDING_LARGE);
        assetGrid.setHorizontalSpacing(Defines.PADDING_LARGE);
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

        updateContent();
    }

    public void updateContent()
    {
        Log.d(LOGTAG, "updateContent: needs overiding...");
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
            backButtonImage.setImageResource(Screens.getContentScreenButtonBackOnRes());
            backButtonImage.setVisibility(View.VISIBLE);

            backButton.setOnButtonClicked(new Runnable()
            {
                @Override
                public void run()
                {
                    /*
                    Intent i = new Intent(Intent.ACTION_MAIN);
                    i.addCategory(Intent.CATEGORY_HOME);
                    startActivity(i);
                    */

                    /*
                    Intent intent = new Intent ("com.android.systemui.recent.action.TOGGLE_RECENTS");

                    intent.setComponent (new ComponentName(
                            "com.android.systemui",
                            "com.android.systemui.recent.RecentsActivity"));

                    startActivity (intent);
                    */

                    onBackPressed();
                }
            });
            backButton.setVisibility(View.VISIBLE);
            backButton.setFocusable(true);
        }
        else
        {
            backButtonImage.setImageResource(Screens.getContentScreenButtonBackOffRes());
            backButtonImage.setVisibility(View.GONE);

            backButton.setOnButtonClicked(null);
            backButton.setVisibility(View.GONE);
            backButton.setFocusable(false);
        }
    }
}
