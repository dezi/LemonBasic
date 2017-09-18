package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Typeface;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;

import java.util.Iterator;

public class ContentActivity extends FullScreenActivity
{
    private static final String LOGTAG = ContentActivity.class.getSimpleName();

    protected LinearLayout contentFrame;
    protected FrameLayout headerFrame;
    protected ImageView headerImage;
    protected ImageView backButtonImage;
    protected ImageView profileButtonImage;
    protected LinearLayout naviFrame;
    protected TextView naviLeftButton;
    protected TextView naviRightButton;

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

        naviFrame = new LinearLayout(this);
        naviFrame.setOrientation(LinearLayout.HORIZONTAL);
        naviFrame.setBackgroundColor(Defines.COLOR_SENSOR_NAVIBAR);
        Simple.setSizeDip(naviFrame, Simple.MP, Defines.NAVIGATION_HEIGHT);

        contentFrame.addView(naviFrame);

        naviLeftButton = new TextView(this);
        naviLeftButton.setText(R.string.content_navibar_left_show_all);
        naviLeftButton.setAllCaps(true);
        naviLeftButton.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
        naviLeftButton.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        naviLeftButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(naviLeftButton, Defines.FS_NAVI_MENU);
        Simple.setSizeDip(naviLeftButton, Simple.MP, Simple.MP, 0.5f);
        Simple.setPaddingDip(naviLeftButton, Defines.PADDING_LARGE, 0, 0, 0);

        naviFrame.addView(naviLeftButton);

        naviRightButton = new TextView(this);
        naviRightButton.setText(R.string.content_navibar_right_themes);
        naviRightButton.setAllCaps(true);
        naviRightButton.setGravity(Gravity.CENTER_VERTICAL + Gravity.END);
        naviRightButton.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        naviRightButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(naviRightButton, Defines.FS_NAVI_MENU);
        Simple.setSizeDip(naviRightButton, Simple.MP, Simple.MP, 0.5f);
        Simple.setPaddingDip(naviRightButton, 0, 0, Defines.PADDING_LARGE, 0);

        naviRightButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showThemesMenu();
            }
        });

        naviFrame.addView(naviRightButton);
    }

    private void showThemesMenu()
    {
        PopupMenu themesPopup = new PopupMenu(this);
        themesPopup.setTitle(R.string.themes_popup_title);
        themesPopup.setTopMargin(Simple.pxToDip(headerImage.getHeight() + naviFrame.getHeight()));

        Iterator<String> keysIterator = Globals.displayCategories.keys();

        while (keysIterator.hasNext())
        {
            String category = keysIterator.next();

            Log.d(LOGTAG, "showThemesMenu: category=" + category);

            themesPopup.addOption(category);
        }

        topFrame.addView(themesPopup);
    }
}
