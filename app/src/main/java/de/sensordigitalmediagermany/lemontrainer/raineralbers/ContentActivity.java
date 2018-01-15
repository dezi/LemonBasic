package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Typeface;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.util.Iterator;

public class ContentActivity extends ContentBaseActivity
{
    private static final String LOGTAG = ContentActivity.class.getSimpleName();

    protected TextView naviLeftButton;
    protected TextView naviRightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        naviLeftButton = new TextView(this);
        naviLeftButton.setText(R.string.content_navibar_left_show_all);
        naviLeftButton.setAllCaps(true);
        naviLeftButton.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
        naviLeftButton.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        naviLeftButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(naviLeftButton, Defines.FS_NAVI_MENU);
        Simple.setSizeDip(naviLeftButton, Simple.MP, Defines.NAVIGATION_HEIGHT, 0.5f);
        Simple.setPaddingDip(naviLeftButton, Defines.PADDING_LARGE, 0, 0, 0);
        Simple.setLetterSpacing(naviLeftButton, Defines.FS_NAVIGATION_LSPACE);

        naviLeftButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Globals.showMyContent = ! Globals.showMyContent;

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

                assetsAdapter.notifyDataSetChanged();
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
        Simple.setLetterSpacing(naviRightButton, Defines.FS_NAVIGATION_LSPACE);

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

        if (Defines.isTopBanner)
        {
            topBanners.setAssets(topFrame, ContentHandler.getBannerContent());
        }

        if (Defines.isCategoryMenu)
        {
            assetsAdapter.setAssets(ContentHandler.getFilteredContent());
        }
        else
        {
            assetGrid.setVisibility(View.GONE);
            categoryScroll.setVisibility(View.VISIBLE);

            if (Globals.displayCategories != null)
            {
                for (int inx = 0; inx < Globals.displayCategories.length(); inx++)
                {
                    JSONObject catjson = Json.getObject(Globals.displayCategories, inx);
                    if (catjson == null) continue;

                    String category = Json.getString(catjson, "name");
                    int categoryCount = Json.getInt(catjson, "_count");

                    Log.d(LOGTAG, "onCreate: category=" + category + " count=" + categoryCount);

                    if (categoryCount == 0) continue;

                    ContentSlider cs = new ContentSlider(this, topFrame);
                    cs.setAssets(category, ContentHandler.getFilteredContent(false, category));

                    categoryContent.addView(cs);
                }
            }
        }
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
            assetsAdapter.notifyDataSetChanged();
        }
    };
}
