package de.sensordigitalmediagermany.lemonbasic.generic;

import android.graphics.Typeface;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

public class ContentActivity extends ContentBaseActivity
{
    private static final String LOGTAG = ContentActivity.class.getSimpleName();

    protected TextView naviLeftButton;
    protected TextView naviRightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        showNavigationPath(Simple.getTrans(this, R.string.tabbar_home));

        naviLeftButton = new TextView(this);
        naviLeftButton.setText(R.string.content_navibar_left_show_all);
        naviLeftButton.setAllCaps(true);
        naviLeftButton.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
        naviLeftButton.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        naviLeftButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(naviLeftButton, Defines.FS_NAVI_MENU);
        Simple.setSizeDip(naviLeftButton, Simple.MP, Defines.NAVIGATION_HEIGHT, 0.5f);
        Simple.setPaddingDip(naviLeftButton, Defines.PADDING_LARGE, 0, 0, 0);
        Simple.setLetterSpacing(naviLeftButton, Defines.FS_NAVIGATION_LSSPACE);

        naviLeftButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Globals.showMyContent = !Globals.showMyContent;

                if (Globals.showMyContent)
                {
                    naviLeftButton.setText(R.string.content_navibar_left_show_my);
                    assetsAdapter.setAssets(ContentHandler.getFilteredContent());
                }
                else
                {
                    naviLeftButton.setText(R.string.content_navibar_left_show_all);
                    assetsAdapter.setAssets(ContentHandler.getFilteredContent());
                }

                assetGrid.updateContent();
            }
        });

        naviFrame.addView(naviLeftButton);

        naviRightButton = new TextView(this);
        naviRightButton.setText(R.string.content_navibar_right_themes);
        naviRightButton.setAllCaps(true);
        naviRightButton.setGravity(Gravity.CENTER_VERTICAL + Gravity.END);
        naviRightButton.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        naviRightButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(naviRightButton, Defines.FS_NAVI_MENU);
        Simple.setSizeDip(naviRightButton, Simple.MP, Defines.NAVIGATION_HEIGHT, 0.5f);
        Simple.setPaddingDip(naviRightButton, 0, 0, Defines.PADDING_LARGE, 0);
        Simple.setLetterSpacing(naviRightButton, Defines.FS_NAVIGATION_LSSPACE);

        naviRightButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showCategoryMenu();
            }
        });

        naviFrame.addView(naviRightButton);

        naviLeftButton.setVisibility(Defines.isGiveAway ? View.GONE : View.VISIBLE);
        naviRightButton.setVisibility(Defines.isCategoryMenu ? View.VISIBLE : View.GONE);

        Globals.showMyContent = false;

        updateContent();

        ApplicationBase.handler.postDelayed(doAutomaticRefresh, Defines.AUTO_REFRESH_SECONDS * 1000);
    }

    private void purchases()
    {

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vVFnaH14P/p9q3P12k5gOClOpt/JlS066YNbT/2D7d8kVluFtuGhDofnbJiSEBFHhTmQNoIhN2tDB+qJ2aG7EhcnULxiNXp2GYaYOQ4ygGgmZbqaoTRr0Hz2wfMihci7sgM81Mzlhuq78TcEo/5+eMa4Eaxec5S90tYruXh05XIPKOlQEoBOKDb+WQTCIrxYn9IK3h/v3fsmfrmZhjTYX+bVvZAheIumbN73ITh7PgTFn8slqAJvaczDzrlCrI+iLEl59L2zGgSqBpN+bJRInDju1+zPMSZALLfCR8z9bRglCVTgHSWYG3uDtLbcvqIb6LEJ/AROpFn3uFrPmGZUQIDAQAB";

    }

    private final Runnable doAutomaticRefresh = new Runnable()
    {
        @Override
        public void run()
        {
            ApplicationBase.handler.removeCallbacks(doAutomaticRefresh);

            ContentHandler.refreshAllContent(topFrame, new Runnable()
            {
                @Override
                public void run()
                {
                    if (ContentHandler.wasDataChanged())
                    {
                        updateContent();

                        if (Defines.isAutoRefreshInfo)
                        {
                            DialogView.errorAlert(topFrame,
                                    R.string.alert_data_changed_title,
                                    R.string.alert_data_changed_info);
                        }
                    }
                }
            });

            ApplicationBase.handler.postDelayed(doAutomaticRefresh, Defines.AUTO_REFRESH_SECONDS * 1000);
        }
    };

    @Override
    public void updateContent()
    {
        Log.d(LOGTAG, "updateContent: start...");

        categoryContent.removeAllViews();
        categoryContent.addView(topBanners);

        if (Defines.isTopBanner)
        {
            topBanners.setAssets(ContentHandler.getBannerContent());
        }

        if (Defines.isCategoryMenu)
        {
            assetsAdapter.setAssets(ContentHandler.getFilteredContent());
            assetGrid.updateContent();
        }
        else
        {
            assetGrid.setVisibility(View.GONE);
            contentScroll.setVisibility(View.VISIBLE);
            categoryContent.setCategories(Globals.displayCategories, topFrame);
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();

        Log.d(LOGTAG, "onResume...");

        doAutomaticRefresh.run();
    }

    private void showCategoryMenu()
    {
        CategoryMenu categoryPopup = new CategoryMenu(this, onCategoryMenuClick);
        categoryPopup.setTopMargin(Simple.pxToDip(headerImage.getHeight() + naviFrame.getHeight()));

        if (Globals.displayCategories != null)
        {
            for (int inx = 0; inx < Globals.displayCategories.length(); inx++)
            {
                JSONObject catjson = Json.getObject(Globals.displayCategories, inx);
                if (catjson == null) continue;

                if (Json.getInt(catjson, "_count") == 0) continue;

                String category = Json.getString(catjson, "name");

                Log.d(LOGTAG, "showCategoryMenu: category=" + category);

                categoryPopup.addOption(category);
            }
        }

        topFrame.addView(categoryPopup);
    }

    private final Runnable onCategoryMenuClick = new Runnable()
    {
        @Override
        public void run()
        {
            assetsAdapter.setAssets(ContentHandler.getFilteredContent());
            assetGrid.updateContent();
        }
    };
}
