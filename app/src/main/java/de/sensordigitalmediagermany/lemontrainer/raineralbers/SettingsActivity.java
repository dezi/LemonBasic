package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.GridView;
import android.view.ViewGroup;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class SettingsActivity extends ContentBaseActivity
{
    private static final String LOGTAG = SettingsActivity.class.getSimpleName();

    protected LinearLayout bodyHorz;
    protected LinearLayout leftArea;
    protected LinearLayout rightArea;
    protected SettingsInfoHeader contentSizeMB;

    protected JSONArray actContent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setBackButton(true);

        showNavigationPath(Simple.getTrans(this, R.string.tabbar_profile));

        Typeface headerTF = Typeface.createFromAsset(getAssets(), Defines.FONT_SETTINGS_HEADER);
        Typeface subheadTF = Typeface.createFromAsset(getAssets(), Defines.FONT_SETTINGS_SUBHEAD);
        Typeface versionTF = Typeface.createFromAsset(getAssets(), Defines.FONT_SETTINGS_VERSION);

        //
        // Remove asset grid and tab bar for re-arrangement.
        //

        ((ViewGroup) assetGrid.getParent()).removeView(assetGrid);
        ((ViewGroup) tabBar.getParent()).removeView(tabBar);

        //region Navigation title.

        if (! Defines.isTabBar)
        {
            naviFrame.setOrientation(LinearLayout.HORIZONTAL);
            Simple.setSizeDip(naviFrame, Simple.MP, Defines.NAVIGATION_HEIGHT);

            TextView naviLeftButton = new TextView(this);
            naviLeftButton.setText(R.string.settings_title);
            naviLeftButton.setAllCaps(true);
            naviLeftButton.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
            naviLeftButton.setTextColor(Defines.COLOR_SETTINGS_HEADERS);
            naviLeftButton.setTypeface(headerTF);
            Simple.setTextSizeDip(naviLeftButton, Defines.FS_NAVI_MENU);
            Simple.setSizeDip(naviLeftButton, Simple.MP, Simple.MP, 0.5f);
            Simple.setPaddingDip(naviLeftButton, Defines.PADDING_LARGE, 0, 0, 0);

            naviFrame.addView(naviLeftButton);

            TextView naviRightButton = new TextView(this);
            naviRightButton.setText(Defines.DEBUG_VERSION);
            naviRightButton.setAllCaps(true);
            naviRightButton.setGravity(Gravity.CENTER_VERTICAL + Gravity.END);
            naviRightButton.setTextColor(Defines.COLOR_SETTINGS_HEADERS);
            naviRightButton.setTypeface(versionTF);
            Simple.setTextSizeDip(naviRightButton, Defines.FS_DEBUG_VERSION);
            Simple.setSizeDip(naviRightButton, Simple.MP, Simple.MP, 0.5f);
            Simple.setPaddingDip(naviRightButton, 0, 0, Defines.PADDING_LARGE, 0);

            naviFrame.addView(naviRightButton);
        }

        //endregion Navigation title.

        //region Body frames.

        bodyHorz = new LinearLayout(this);
        Simple.setSizeDip(bodyHorz, Simple.MP, Simple.MP, 1.0f);

        if (Simple.isTablet())
        {
            bodyHorz.setOrientation(LinearLayout.HORIZONTAL);
        }
        else
        {
            bodyHorz.setOrientation(LinearLayout.VERTICAL);

        }

        contentFrame.addView(bodyHorz);

        //region Left area.

        leftArea = new LinearLayout(this);
        leftArea.setOrientation(LinearLayout.VERTICAL);
        leftArea.setBackgroundColor(Defines.COLOR_FRAMES);
        Simple.setPaddingDip(leftArea, Defines.PADDING_NORMAL);

        if (Simple.isTablet())
        {
            Simple.setMarginDip(leftArea,
                    Defines.PADDING_LARGE, Defines.PADDING_SMALL,
                    Defines.PADDING_LARGE / 2, Defines.PADDING_LARGE);

            Simple.setSizeDip(leftArea, Simple.MP, Simple.MP, 0.6f);
        }
        else
        {
            Simple.setMarginDip(leftArea,
                    Defines.PADDING_NORMAL, 0,
                    Defines.PADDING_NORMAL, 0);

            Simple.setSizeDip(leftArea, Simple.MP, Simple.WC);
        }

        bodyHorz.addView(leftArea);

        //region Left top area.

        LinearLayout leftTopArea = new LinearLayout(this);

        if (Simple.isTablet())
        {
            leftTopArea.setOrientation(LinearLayout.VERTICAL);

            Simple.setMarginTopDip(leftTopArea, Defines.PADDING_SMALL);
            Simple.setSizeDip(leftTopArea, Simple.MP, Defines.FS_SETTINGS_TITLE * 3);
        }
        else
        {
            leftTopArea.setOrientation(LinearLayout.HORIZONTAL);

            Simple.setSizeDip(leftTopArea, Simple.MP, Simple.WC);
        }

        leftArea.addView(leftTopArea);

        SettingsTitle settingsTitle = new SettingsTitle(this);
        settingsTitle.setText(R.string.settings_data);
        settingsTitle.setFullWidth(Simple.isTablet());

        leftTopArea.addView(settingsTitle);

        if (Defines.isTabBar)
        {
            String version = Simple.getTrans(this, R.string.settings_version) + " " + Defines.DEBUG_VERSION;

            SettingsInfoText systemVersion = new SettingsInfoText(this);
            systemVersion.setText(version);
            systemVersion.setTextSizeDip(Defines.FS_DEBUG_VERSION);
            systemVersion.setMarginTopDip(Defines.PADDING_ZERO);
            systemVersion.setGravity(Gravity.CENTER_VERTICAL + Gravity.END);
            systemVersion.setFullWidth(! Simple.isTablet());
            systemVersion.setFullHeight(true);

            leftTopArea.addView(systemVersion);
        }

        //endregion Left top area.

        //region Left personal data.

        if (Defines.isSectionDividers) leftArea.addView(createSeparator());

        SettingsInfoHeader nameSection = new SettingsInfoHeader(this);
        nameSection.setText(R.string.settings_name);

        leftArea.addView(nameSection);

        String userName = Globals.firstName + " " + Globals.lastName;

        SettingsInfoText nameEdit = new SettingsInfoText(this);
        nameEdit.setText(userName);

        leftArea.addView(nameEdit);

        if ((Globals.company != null) && ! Globals.company.isEmpty())
        {
            if (Defines.isSectionDividers) leftArea.addView(createSeparator());

            SettingsInfoHeader companySection = new SettingsInfoHeader(this);
            companySection.setText(R.string.settings_company);

            leftArea.addView(companySection);

            SettingsInfoText companyEdit = new SettingsInfoText(this);
            companyEdit.setText(Globals.company);

            leftArea.addView(companyEdit);
        }

        if (Defines.isSectionDividers) leftArea.addView(createSeparator());

        SettingsInfoHeader emailSection = new SettingsInfoHeader(this);
        emailSection.setText(R.string.settings_email);

        leftArea.addView(emailSection);

        SettingsInfoText emailEdit = new SettingsInfoText(this);
        emailEdit.setText(Globals.emailAddress);

        leftArea.addView(emailEdit);

        if (Defines.isSectionDividers) leftArea.addView(createSeparator());

        //endregion Left personal data.

        //region Left sound section.

        if (Defines.isSoundSettings)
        {
            leftArea.addView(new SettingsSound(this));
        }

        //endregion Left sound section.

        //region Left clear cache button.

        if (Defines.isDeleteCache)
        {
            GenericButton cacheButton = new GenericButton(this);
            cacheButton.setText(R.string.settings_clearcache);
            cacheButton.setMarginTopDip(Defines.PADDING_LARGE);

            cacheButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ContentHandler.deleteAllCachedFiles();

                    while (actContent.length() > 0)
                    {
                        Json.remove(actContent, 0);
                    }

                    AssetsImageManager.nukeCache(SettingsActivity.this);

                    RestApi.nukeSavedQueries(SettingsActivity.this);

                    updateContent();
                }
            });

            leftArea.addView(cacheButton);
        }

        //endregion Left clear cache button.

        //region Left logoff button.

        LinearLayout logoffArea = new LinearLayout(this);
        Simple.setMarginTopDip(logoffArea, Defines.PADDING_LARGE);

        if (Defines.isCompactSettings)
        {
            Simple.setSizeDip(logoffArea, Simple.MP, Simple.WC);
        }
        else
        {
            Simple.setSizeDip(logoffArea, Simple.MP, Simple.MP, 1.0f);
        }

        if (Simple.isTablet())
        {
            logoffArea.setOrientation(LinearLayout.VERTICAL);
        }
        else
        {
            logoffArea.setGravity(Gravity.BOTTOM);
            logoffArea.setOrientation(LinearLayout.HORIZONTAL);
        }

        leftArea.addView(logoffArea);

        GenericButton logoffButton = new GenericButton(this);
        logoffButton.setText(R.string.settings_logoff);

        if (Simple.isTablet())
        {
            logoffButton.setFullWidth(true);
        }
        else
        {
            logoffButton.setWeight(0.5f);
        }

        logoffButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                topFrame.addView(new LogoffDialog(SettingsActivity.this));
            }
        });

        logoffArea.addView(logoffButton);

        GenericButton passwordButton = new GenericButton(this);
        passwordButton.setText(R.string.settings_change_password);
        passwordButton.setInvers(true);

        passwordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                topFrame.addView(new PasswordChangeDialog(SettingsActivity.this));
            }
        });

        if (Simple.isTablet())
        {
            passwordButton.setFullWidth(true);

            Simple.setMarginTopDip(passwordButton, Defines.PADDING_LARGE);
        }
        else
        {
            passwordButton.setWeight(0.5f);
            passwordButton.setMarginLeftDip(Defines.PADDING_NORMAL);
        }

        logoffArea.addView(passwordButton);

        //endregion Left logoff button.

        //endregion Left area.

        //region Right area.

        rightArea = new LinearLayout(this);
        rightArea.setOrientation(LinearLayout.VERTICAL);
        rightArea.setBackgroundColor(Defines.COLOR_FRAMES);
        Simple.setPaddingDip(rightArea, Defines.PADDING_NORMAL);

        if (Simple.isTablet())
        {
            Simple.setMarginDip(rightArea,
                    Defines.PADDING_LARGE / 2, Defines.PADDING_SMALL,
                    Defines.PADDING_LARGE, Defines.PADDING_LARGE);

            Simple.setSizeDip(rightArea, Simple.MP, Simple.MP, 0.4f);
        }
        else
        {
            Simple.setMarginDip(rightArea, Defines.PADDING_NORMAL);

            Simple.setSizeDip(rightArea, Simple.MP, Simple.WC);
        }

        bodyHorz.addView(rightArea);

        //region Right top area.

        LinearLayout rightTopArea = new LinearLayout(this);
        rightTopArea.setOrientation(LinearLayout.HORIZONTAL);

        if (Simple.isTablet())
        {
            Simple.setMarginTopDip(rightTopArea, Defines.PADDING_SMALL);
            Simple.setSizeDip(rightTopArea, Simple.MP, Defines.FS_SETTINGS_TITLE * 3);
        }
        else
        {
            Simple.setSizeDip(rightTopArea, Simple.MP, Simple.WC);
        }

        rightArea.addView(rightTopArea);

        SettingsTitle contentTitle = new SettingsTitle(this);
        contentTitle.setText(R.string.settings_contents);
        contentTitle.setWeight(0.58f);

        rightTopArea.addView(contentTitle);

        LinearLayout contentSizeFrame = new LinearLayout(this);
        contentSizeFrame.setOrientation(LinearLayout.HORIZONTAL);

        SettingsInfoHeader contentSizeText = new SettingsInfoHeader(this);
        contentSizeText.setText(R.string.settings_used_storage);
        contentSizeText.setMarginTopDip(Defines.PADDING_ZERO);
        contentSizeText.setWeight(1.0f);

        contentSizeFrame.addView(contentSizeText);

        contentSizeMB = new SettingsInfoHeader(this);
        contentSizeMB.setMarginTopDip(Defines.PADDING_ZERO);
        contentSizeMB.setFullWidth(false);

        contentSizeFrame.addView(contentSizeMB);

        if (Defines.isSectionDividers) rightArea.addView(createSeparator());

        if (Defines.isSectionDividers)
        {
            Simple.setPaddingDip(contentSizeFrame, 0, Defines.PADDING_SMALL, 0, Defines.PADDING_TINY);

            Simple.setSizeDip(contentSizeFrame, Simple.MP, Simple.WC);

            rightArea.addView(contentSizeFrame);
        }
        else
        {
            contentSizeText.setTextColor(Color.WHITE);
            contentSizeMB.setTextColor(Color.WHITE);

            Simple.setPaddingDip(contentSizeFrame,
                    Defines.PADDING_NORMAL, Defines.PADDING_SMALL,
                    Defines.PADDING_NORMAL, Defines.PADDING_SMALL);

            Simple.setSizeDip(contentSizeFrame, Simple.MP, Simple.WC, 0.42f);
            Simple.setRoundedCorners(contentSizeFrame, Defines.CORNER_RADIUS_BIGBUT, Color.LTGRAY, true);

            rightTopArea.addView(contentSizeFrame);
        }

        if (Defines.isSectionDividers) rightArea.addView(createSeparator());

        //endregion Right top area.

        //region Right content area.

        if (Defines.isSectionDividers)
        {
            Simple.setMarginTopDip(assetGrid, Defines.PADDING_LARGE);
            assetGrid.setVerticalSpacing(Simple.dipToPx(Defines.PADDING_NORMAL));
        }
        else
        {
            Simple.setMarginTopDip(assetGrid, 0);
            assetGrid.setVerticalSpacing(Simple.dipToPx(Defines.PADDING_SMALL));

            TextView contentSection = new TextView(this);
            contentSection.setText(R.string.settings_your_contents);
            contentSection.setAllCaps(true);
            contentSection.setTextColor(Color.BLACK);
            contentSection.setTypeface(subheadTF);
            Simple.setSizeDip(contentSection, Simple.MP, Simple.WC);
            Simple.setMarginTopDip(contentSection, Defines.PADDING_SMALL);
            Simple.setMarginBottomDip(contentSection, Defines.PADDING_SMALL);
            Simple.setTextSizeDip(contentSection, Defines.FS_SETTINGS_INFO);
            Simple.setLetterSpacing(contentSection, Defines.FS_NAVIGATION_LSSPACE);

            rightArea.addView(contentSection);
        }

        assetGrid.setNumColumns(1);
        assetGrid.setColumnWidth(GridView.AUTO_FIT);
        assetGrid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        assetGrid.setHorizontalSpacing(0);
        assetGrid.setBackgroundColor(Defines.COLOR_FRAMES);
        Simple.setPaddingDip(assetGrid, 0);

        rightArea.addView(assetGrid);

        //endregion Right content area.

        //endregion Right area.

        //endregion Body frames

        actContent = ContentHandler.getCachedContent();

        assetsAdapter.setSettings(true);
        assetsAdapter.setAssets(actContent);
        assetsAdapter.setOnAssetClickedHandler(onAssetClickedHandler);

        //
        // Add tab bar again.
        //

        contentFrame.addView(tabBar);

        updateContent();
    }

    private FrameLayout createSeparator()
    {
        FrameLayout divider = new FrameLayout(this);
        divider.setBackgroundColor(Color.BLACK);
        Simple.setSizeDip(divider, Simple.MP, 1);

        if (Simple.isTablet())
        {
            Simple.setMarginTopDip(divider, Defines.PADDING_NORMAL);
            Simple.setMarginBottomDip(divider, Defines.PADDING_SMALL);
        }
        else
        {
            Simple.setMarginTopDip(divider, Defines.PADDING_SMALL);
            Simple.setMarginBottomDip(divider, Defines.PADDING_TINY);
        }

        return divider;
    }

    private final AssetsAdapter.OnAssetClickedHandler onAssetClickedHandler = new AssetsAdapter.OnAssetClickedHandler()
    {
        @Override
        public void OnAssetClicked(final JSONObject content)
        {
            for (int inx = 0; inx < actContent.length(); inx++)
            {
                JSONObject item = Json.getObject(actContent, inx);
                if (item == null) continue;

                Json.put(item, "_isSelected", (content == item));
            }

            assetsAdapter.notifyDataSetChanged();

            ApplicationBase.handler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    SettingsDetail detailView = new SettingsDetail(SettingsActivity.this, content);

                    if (Defines.isCompactSettings)
                    {
                        if (Simple.isTablet())
                        {
                            rightArea.removeView(assetGrid);
                            rightArea.addView(detailView);
                        }
                        else
                        {
                            bodyHorz.removeView(leftArea);
                            bodyHorz.removeView(rightArea);
                            bodyHorz.addView(detailView);
                        }
                    }
                    else
                    {
                        bodyHorz.removeView(rightArea);
                        bodyHorz.addView(detailView);
                    }

                    Json.put(content, "_isSelected", false);
                }
            }, 100);
        }
    };

    private void updateContent()
    {
        long total = 0;

        for (int inx = 0; inx < actContent.length(); inx++)
        {
            JSONObject item = Json.getObject(actContent, inx);
            if (item == null) continue;

            long file_size = Json.getLong(item, "file_size");
            total += file_size;
        }

        contentSizeMB.setText(Simple.formatBytes(total));

        assetsAdapter.notifyDataSetChanged();
    }

    public void removeContent(JSONObject content)
    {
        for (int inx = 0; inx < actContent.length(); inx++)
        {
            JSONObject item = Json.getObject(actContent, inx);

            if (item == content)
            {
                Json.remove(actContent, inx);

                break;
            }
        }

        updateContent();
    }

    public void reAttachRightArea()
    {
        if (Defines.isCompactSettings)
        {
            if (Simple.isTablet())
            {
                if (assetGrid.getParent() == null)
                {
                    rightArea.addView(assetGrid);
                }
            }
            else
            {
                if (leftArea.getParent() == null)
                {
                    bodyHorz.addView(leftArea);
                }

                if (rightArea.getParent() == null)
                {
                    bodyHorz.addView(rightArea);
                }
            }
        }
        else
        {
            if (rightArea.getParent() == null)
            {
                bodyHorz.addView(rightArea);
            }
        }
    }
}