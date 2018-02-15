package de.sensordigitalmediagermany.lemonbasic.generic;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.os.Bundle;

public class CategoryActivity extends ContentBaseActivity
{
    private static final String LOGTAG = CategoryActivity.class.getSimpleName();

    protected TextView naviLeftButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setBackButton(true);

        Simple.setMarginDip(naviFrame, Defines.PADDING_NORMAL, 0, Defines.PADDING_NORMAL, 0);

        naviLeftButton = new TextView(this);
        naviLeftButton.setAllCaps(true);
        naviLeftButton.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
        naviLeftButton.setTextColor(Color.WHITE);
        naviLeftButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.FONT_CATEGORY_TITLE));
        Simple.setTextSizeDip(naviLeftButton, Defines.FS_CATEGORY_TITLE);
        Simple.setSizeDip(naviLeftButton, Simple.WC, Simple.WC);
        Simple.setLetterSpacing(naviLeftButton, Defines.FS_NAVIGATION_LSSPACE);

        if (Simple.isTablet())
        {
            Simple.setPaddingDip(naviLeftButton,
                    Defines.PADDING_LARGE, Defines.PADDING_LARGE,
                    Defines.PADDING_LARGE, Defines.PADDING_TINY);
        }
        else
        {
            //
            // Compressed layout on phones.
            //

            Simple.setPaddingDip(naviLeftButton,
                    Defines.PADDING_LARGE, Defines.PADDING_NORMAL,
                    Defines.PADDING_LARGE, Defines.PADDING_NORMAL);
        }

        naviFrame.addView(naviLeftButton);

        if (navigationButton != null)
        {
            navigationButton.setButtonText(Defines.PADDING_TINY, Globals.category);
        }

        naviLeftButton.setText(Globals.category);
        assetsAdapter.setAssets(Globals.categoryContents);

        updateContent();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        Log.d(LOGTAG, "onDestroy:");

        Globals.category = null;
        Globals.categoryContents = null;
    }

    @Override
    public void updateContent()
    {
        assetGrid.updateContent();
    }
}