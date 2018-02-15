package de.sensordigitalmediagermany.lemonbasic.generic;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;

import java.net.*;

public class CategoryActivity extends ContentBaseActivity
{
    private static final String LOGTAG = CategoryActivity.class.getSimpleName();

    protected TextView naviLeftButton;
    protected RelativeLayout buyButtonCenter;
    protected GenericButton buyButton;

    protected DownloadAllManager downloadAllManager;

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

        buyButtonCenter = new RelativeLayout(this);
        buyButtonCenter.setGravity(Gravity.CENTER_VERTICAL + Gravity.END);
        buyButtonCenter.setVisibility(View.GONE);
        Simple.setMarginRightDip(buyButtonCenter, Defines.PADDING_SMALL);

        naviFrame.addView(buyButtonCenter);

        buyButton = new GenericButton(this);
        buyButton.setFullWidth(false);

        buyButtonCenter.addView(buyButton);

        if (Simple.isTablet())
        {
            naviFrame.setOrientation(LinearLayout.HORIZONTAL);
            Simple.setSizeDip(buyButtonCenter, Simple.MP, Simple.MP, 1.0f);
        }
        else
        {
            naviFrame.setOrientation(LinearLayout.VERTICAL);
            Simple.setSizeDip(buyButtonCenter, Simple.MP, Simple.WC);
            Simple.setMarginBottomDip(buyButtonCenter, Defines.PADDING_SMALL);
        }

        assetsAdapter.setAssets(Globals.categoryContents);

        updateContent();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        Log.d(LOGTAG, "onPause...");

        if (downloadAllManager != null)
        {
            downloadAllManager.requestCancel();
            downloadAllManager = null;
        }
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
        if (Defines.isGiveAway)
        {
            JSONArray unCachedContent = ContentHandler.getUnCachedContent(Globals.categoryContents);

            if (unCachedContent.length() == 0)
            {
                buyButtonCenter.setVisibility(View.GONE);
            }
            else
            {
                buyButtonCenter.setVisibility(View.VISIBLE);

                Simple.setPaddingDip(buyButton,
                        Defines.PADDING_XLARGE, Defines.PADDING_SMALL,
                        Defines.PADDING_XLARGE, Defines.PADDING_SMALL);

                buyButton.setText(R.string.course_buy_load);

                buyButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        downloadAllManager = new DownloadAllManager();
                        downloadAllManager.askDownloadSpecificContent(topFrame, Globals.categoryContents);
                    }
                });
            }
        }

        assetGrid.updateContent();
    }
}