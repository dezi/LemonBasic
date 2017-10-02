package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;
import android.util.Log;

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

        naviFrame.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(naviFrame, Simple.MP, Defines.NAVIGATION_HEIGHT);

        naviLeftButton = new TextView(this);
        naviLeftButton.setAllCaps(true);
        naviLeftButton.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
        naviLeftButton.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        naviLeftButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(naviLeftButton, Defines.FS_NAVI_MENU);
        Simple.setSizeDip(naviLeftButton, Simple.MP, Simple.MP, 0.5f);
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
        Simple.setSizeDip(naviRightButton, Simple.MP, Simple.MP, 0.5f);
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

        Globals.showMyContent = false;
        naviLeftButton.setText(R.string.content_navibar_left_show_all);
        assetsAdapter.setAssets(ContentHandler.getFilteredContent());
    }

    private void showCategoryMenu()
    {
        CategoryMenu categoryPopup = new CategoryMenu(this, onCategoryMenuClick);
        categoryPopup.setTopMargin(Simple.pxToDip(headerImage.getHeight() + naviFrame.getHeight()));

        Iterator<String> keysIterator = Globals.displayCategories.keys();

        while (keysIterator.hasNext())
        {
            String category = keysIterator.next();

            Log.d(LOGTAG, "showCategoryMenu: category=" + category);

            categoryPopup.addOption(category);
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
