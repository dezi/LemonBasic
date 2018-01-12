package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.text.InputType;
import android.view.ViewGroup;
import android.view.View;
import android.view.Gravity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class SettingsActivity extends ContentBaseActivity
{
    private static final String LOGTAG = SettingsActivity.class.getSimpleName();

    protected LinearLayout bodyHorz;
    protected LinearLayout rightArea;
    protected TextView contentSizeMB;

    protected JSONArray actContent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setBackButton(true);

        Typeface headerTF = Typeface.createFromAsset(getAssets(), Defines.FONT_SETTINGS_HEADER);
        Typeface subheadTF = Typeface.createFromAsset(getAssets(), Defines.FONT_SETTINGS_SUBHEAD);
        Typeface infosTF = Typeface.createFromAsset(getAssets(), Defines.FONT_SETTINGS_INFOS);
        Typeface versionTF = Typeface.createFromAsset(getAssets(), Defines.FONT_SETTINGS_VERSION);
        Typeface buttonsTF = Typeface.createFromAsset(getAssets(), Defines.FONT_DIALOG_BUTTON);

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
            naviLeftButton.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
            naviLeftButton.setTypeface(headerTF);
            Simple.setTextSizeDip(naviLeftButton, Defines.FS_NAVI_MENU);
            Simple.setSizeDip(naviLeftButton, Simple.MP, Simple.MP, 0.5f);
            Simple.setPaddingDip(naviLeftButton, Defines.PADDING_LARGE, 0, 0, 0);

            naviFrame.addView(naviLeftButton);

            TextView naviRightButton = new TextView(this);
            naviRightButton.setText(Defines.DEBUG_VERSION);
            naviRightButton.setAllCaps(true);
            naviRightButton.setGravity(Gravity.CENTER_VERTICAL + Gravity.END);
            naviRightButton.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
            naviRightButton.setTypeface(versionTF);
            Simple.setTextSizeDip(naviRightButton, Defines.FS_DEBUG_VERSION);
            Simple.setSizeDip(naviRightButton, Simple.MP, Simple.MP, 0.5f);
            Simple.setPaddingDip(naviRightButton, 0, 0, Defines.PADDING_LARGE, 0);

            naviFrame.addView(naviRightButton);
        }

        //endregion Navigation title.

        //region Body frames.

        bodyHorz = new LinearLayout(this);
        bodyHorz.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(bodyHorz, Simple.MP, Simple.MP, 1.0f);

        contentFrame.addView(bodyHorz);

        //region Left area.

        LinearLayout leftArea = new LinearLayout(this);
        leftArea.setOrientation(LinearLayout.VERTICAL);
        leftArea.setBackgroundColor(Defines.COLOR_FRAMES);
        Simple.setSizeDip(leftArea, Simple.MP, Simple.MP, 0.6f);
        Simple.setPaddingDip(leftArea, Defines.PADDING_NORMAL);

        Simple.setMarginDip(leftArea,
                Defines.PADDING_LARGE, Defines.PADDING_SMALL,
                Defines.PADDING_LARGE / 2, Defines.PADDING_LARGE);


        bodyHorz.addView(leftArea);

        //region Left top area.

        LinearLayout leftTopArea = new LinearLayout(this);
        leftTopArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(leftTopArea, Simple.MP, Defines.FS_SETTINGS_TITLE * 3);

        leftArea.addView(leftTopArea);

        TextView settingsTitle = new TextView(this);
        settingsTitle.setText(R.string.settings_data);
        settingsTitle.setSingleLine();
        settingsTitle.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
        settingsTitle.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        settingsTitle.setTypeface(headerTF);
        settingsTitle.setAllCaps(true);
        Simple.setTextSizeDip(settingsTitle, Defines.FS_SETTINGS_TITLE);
        Simple.setSizeDip(settingsTitle, Simple.MP, Simple.MP);
        Simple.setLetterSpacing(settingsTitle, Defines.FS_NAVIGATION_LSPACE);

        leftTopArea.addView(settingsTitle);

        if (Defines.isTabBar)
        {
            String version = Simple.getTrans(this, R.string.settings_version) + " " + Defines.DEBUG_VERSION;

            TextView systemVersion = new TextView(this);
            systemVersion.setText(version);
            systemVersion.setAllCaps(true);
            systemVersion.setGravity(Gravity.CENTER_VERTICAL + Gravity.END);
            systemVersion.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
            systemVersion.setTypeface(versionTF);
            Simple.setTextSizeDip(systemVersion, Defines.FS_DEBUG_VERSION);
            Simple.setSizeDip(systemVersion, Simple.WC, Simple.WC);

            leftArea.addView(systemVersion);
        }

        //endregion Left top area.

        //region Left profile image.

        /*
        RelativeLayout profileFrame = new RelativeLayout(this);
        profileFrame.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(profileFrame, Simple.MP, Simple.WC);

        leftArea.addView(profileFrame);

        FrameLayout profileImageHolder = new FrameLayout(this);
        Simple.setSizeDip(profileImageHolder, Defines.PROFILE_IMAGE_SIZE, Defines.PROFILE_IMAGE_SIZE);

        profileFrame.addView(profileImageHolder);

        ImageView profileImage = new ImageView(this);
        profileImage.setImageResource(R.drawable.lem_t_iany_generic_profil_platzhalter_225_2x);
        profileImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Simple.setSizeDip(profileImage, Simple.MP, Simple.MP);

        profileImageHolder.addView(profileImage);

        TextView profileEditButton = new TextView(this);
        profileEditButton.setText(R.string.settings_edit_image);
        profileEditButton.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.BOTTOM);
        profileEditButton.setTextColor(Color.WHITE);
        profileEditButton.setTypeface(buttonsTF);
        Simple.setSizeDip(profileEditButton, Simple.MP, Simple.MP);
        Simple.setTextSizeDip(profileEditButton, Defines.FS_SETTINGS_UPLOAD);
        Simple.setPaddingDip(profileEditButton, Defines.PADDING_SMALL);

        profileImageHolder.addView(profileEditButton);
        */

        //endregion Left profile image.

        //region Left personal data.

        if (Defines.isSectionDividers)
        {
            FrameLayout divider = new FrameLayout(this);
            divider.setBackgroundColor(Color.BLACK);
            Simple.setSizeDip(divider, Simple.MP, 1);
            Simple.setMarginTopDip(divider, Defines.PADDING_NORMAL);
            Simple.setMarginBottomDip(divider, Defines.PADDING_SMALL);
            leftArea.addView(divider);
        }

        TextView nameSection = new TextView(this);
        nameSection.setText(R.string.settings_name);
        nameSection.setAllCaps(true);
        nameSection.setTextColor(Color.BLACK);
        nameSection.setTypeface(headerTF);
        Simple.setSizeDip(nameSection, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(nameSection, Defines.PADDING_TINY);
        Simple.setTextSizeDip(nameSection, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(nameSection, Defines.FS_NAVIGATION_LSPACE);

        leftArea.addView(nameSection);

        String userName = Globals.firstName + " " + Globals.lastName;

        TextView nameEdit = new TextView(this);
        nameEdit.setText(userName);
        nameEdit.setAllCaps(Defines.isInfosAllCaps);
        nameEdit.setTypeface(infosTF);
        Simple.setTextSizeDip(nameEdit, Defines.FS_SETTINGS_EDIT);
        Simple.setSizeDip(nameEdit, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(nameEdit, Defines.PADDING_TINY);

        if (! Defines.isFlatEdits)
        {
            nameEdit.setBackgroundColor(Defines.COLOR_SENSOR_NAVIBAR);
            Simple.setPaddingDip(nameEdit,Defines.PADDING_SMALL);
        }

        leftArea.addView(nameEdit);

        if (Defines.isCompanyAvailable)
        {
            if (Defines.isSectionDividers)
            {
                FrameLayout divider = new FrameLayout(this);
                divider.setBackgroundColor(Color.BLACK);
                Simple.setSizeDip(divider, Simple.MP, 1);
                Simple.setMarginTopDip(divider, Defines.PADDING_NORMAL);
                Simple.setMarginBottomDip(divider, Defines.PADDING_SMALL);
                leftArea.addView(divider);
            }

            TextView companySection = new TextView(this);
            companySection.setText(R.string.settings_company);
            companySection.setAllCaps(true);
            companySection.setTextColor(Color.BLACK);
            companySection.setTypeface(headerTF);
            Simple.setSizeDip(companySection, Simple.MP, Simple.WC);
            Simple.setMarginTopDip(companySection, Defines.PADDING_SMALL);
            Simple.setTextSizeDip(companySection, Defines.FS_SETTINGS_INFO);
            Simple.setLetterSpacing(companySection, Defines.FS_NAVIGATION_LSPACE);

            leftArea.addView(companySection);

            TextView companyEdit = new TextView(this);
            companyEdit.setText(Globals.company);
            companyEdit.setAllCaps(Defines.isInfosAllCaps);
            companyEdit.setTypeface(infosTF);
            Simple.setTextSizeDip(companyEdit, Defines.FS_SETTINGS_EDIT);
            Simple.setSizeDip(companyEdit, Simple.MP, Simple.WC);
            Simple.setMarginTopDip(companyEdit, Defines.PADDING_TINY);

            if (! Defines.isFlatEdits)
            {
                companyEdit.setBackgroundColor(Defines.COLOR_SENSOR_NAVIBAR);
                Simple.setPaddingDip(companyEdit, Defines.PADDING_SMALL);
            }

            leftArea.addView(companyEdit);
        }

        if (Defines.isSectionDividers)
        {
            FrameLayout divider = new FrameLayout(this);
            divider.setBackgroundColor(Color.BLACK);
            Simple.setSizeDip(divider, Simple.MP, 1);
            Simple.setMarginTopDip(divider, Defines.PADDING_NORMAL);
            Simple.setMarginBottomDip(divider, Defines.PADDING_SMALL);
            leftArea.addView(divider);
        }

        TextView emailSection = new TextView(this);
        emailSection.setText(R.string.settings_email);
        emailSection.setAllCaps(true);
        emailSection.setTextColor(Color.BLACK);
        emailSection.setTypeface(headerTF);
        Simple.setSizeDip(emailSection, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(emailSection, Defines.PADDING_SMALL);
        Simple.setTextSizeDip(emailSection, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(emailSection, Defines.FS_NAVIGATION_LSPACE);

        leftArea.addView(emailSection);

        TextView emailEdit = new TextView(this);
        emailEdit.setText(Globals.emailAddress);
        emailEdit.setAllCaps(Defines.isInfosAllCaps);
        emailEdit.setTypeface(infosTF);
        Simple.setTextSizeDip(emailEdit, Defines.FS_SETTINGS_EDIT);
        Simple.setSizeDip(emailEdit, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(emailEdit, Defines.PADDING_TINY);

        if (! Defines.isFlatEdits)
        {
            emailEdit.setBackgroundColor(Defines.COLOR_SENSOR_NAVIBAR);
            Simple.setPaddingDip(emailEdit,Defines.PADDING_SMALL);
        }

        leftArea.addView(emailEdit);

        if (Defines.isSectionDividers)
        {
            FrameLayout divider = new FrameLayout(this);
            divider.setBackgroundColor(Color.BLACK);
            Simple.setSizeDip(divider, Simple.MP, 1);
            Simple.setMarginTopDip(divider, Defines.PADDING_NORMAL);
            Simple.setMarginBottomDip(divider, Defines.PADDING_SMALL);
            leftArea.addView(divider);
        }

        /*
        TextView passwordSection = new TextView(this);
        passwordSection.setText(R.string.settings_password);
        passwordSection.setAllCaps(true);
        passwordSection.setTextColor(Color.BLACK);
        passwordSection.setTypeface(headersTF);
        Simple.setSizeDip(passwordSection, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(passwordSection, Defines.PADDING_SMALL);
        Simple.setTextSizeDip(passwordSection, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(passwordSection, Defines.FS_NAVIGATION_LSPACE);

        leftArea.addView(passwordSection);

        LinearLayout passwordArea = new LinearLayout(this);
        passwordArea.setOrientation(LinearLayout.HORIZONTAL);
        passwordArea.setBackgroundColor(Defines.COLOR_SENSOR_NAVIBAR);
        Simple.setSizeDip(passwordArea, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(passwordArea, Defines.PADDING_TINY);

        leftArea.addView(passwordArea);

        EditText passwordEdit = new EditText(this);
        passwordEdit.setText("????????");
        passwordEdit.setFocusable(false);
        passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordEdit.setBackgroundColor(Defines.COLOR_SENSOR_NAVIBAR);
        passwordEdit.setTypeface(infosTF);
        Simple.setTextSizeDip(passwordEdit, Defines.FS_SETTINGS_EDIT);
        Simple.setSizeDip(passwordEdit, Simple.WC, Simple.WC, 1.0f);
        Simple.setPaddingDip(passwordEdit,Defines.PADDING_SMALL);

        passwordArea.addView(passwordEdit);

        TextView changePasswordButton = new TextView(this);
        changePasswordButton.setText(R.string.settings_change_password);
        changePasswordButton.setTextColor(Color.WHITE);
        changePasswordButton.setTypeface(buttonsTF);
        changePasswordButton.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(changePasswordButton, Simple.WC, Simple.MP);
        Simple.setMarginDip(changePasswordButton, Defines.MARGIN_BUTTON);
        Simple.setTextSizeDip(changePasswordButton, Defines.FS_SETTINGS_BUTTON);
        Simple.setPaddingDip(changePasswordButton, Defines.PADDING_NORMAL, 0, Defines.PADDING_NORMAL, 0);
        Simple.setRoundedCorners(changePasswordButton, Defines.CORNER_RADIUS_BUTTON, Color.BLACK, true);

        passwordArea.addView(changePasswordButton);

        changePasswordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                topFrame.addView(new PasswordChangeDialog(SettingsActivity.this));
            }
        });
        */

        //endregion Left personal data.

        //region Left sound section.

        /*
        TextView soundSection = new TextView(this);
        soundSection.setText(R.string.settings_sound);
        soundSection.setAllCaps(true);
        soundSection.setTextColor(Color.BLACK);
        soundSection.setTypeface(headersTF);
        Simple.setSizeDip(soundSection, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(soundSection, Defines.PADDING_SMALL);
        Simple.setTextSizeDip(soundSection, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(soundSection, Defines.FS_NAVIGATION_LSPACE);

        leftArea.addView(soundSection);

        LinearLayout onoffArea = new LinearLayout(this);
        onoffArea.setOrientation(LinearLayout.HORIZONTAL);
        onoffArea.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(onoffArea, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(onoffArea, Defines.PADDING_TINY);

        leftArea.addView(onoffArea);

        TextView onoffText = new TextView(this);
        onoffText.setText(R.string.settings_sound_button);
        onoffText.setAllCaps(true);
        onoffText.setTextColor(Color.BLACK);
        onoffText.setTypeface(headersTF);
        Simple.setSizeDip(onoffText, Simple.MP, Simple.WC, 1.0f);
        Simple.setTextSizeDip(onoffText, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(onoffText, Defines.FS_NAVIGATION_LSPACE);
        Simple.setPaddingDip(onoffText, Defines.PADDING_SMALL);

        onoffArea.addView(onoffText);

        OnOffControl onoffControl = new OnOffControl(this);

        onoffControl.setOnChangedListener(new OnOffControl.OnChangedListener()
        {
            @Override
            public void OnChanged(View view, boolean on)
            {
                Log.d(LOGTAG, "onoffControl: OnChanged: on=" + on);
            }
        });

        onoffArea.addView(onoffControl);

        onoffArea.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onoffControl.setOn(! onoffControl.getOn());
            }
        });

        LinearLayout volumeArea = new LinearLayout(this);
        volumeArea.setOrientation(LinearLayout.HORIZONTAL);
        volumeArea.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(volumeArea, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(volumeArea, Defines.PADDING_TINY);

        leftArea.addView(volumeArea);

        TextView volumeText = new TextView(this);
        volumeText.setText(R.string.settings_sound_volume);
        volumeText.setAllCaps(true);
        volumeText.setTextColor(Color.BLACK);
        volumeText.setTypeface(headersTF);
        Simple.setSizeDip(volumeText, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(volumeText, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(volumeText, Defines.FS_NAVIGATION_LSPACE);
        Simple.setPaddingDip(volumeText, Defines.PADDING_SMALL);

        volumeArea.addView(volumeText);

        SliderControl volumeControl = new SliderControl(this);

        volumeControl.setOnChangedListener(new SliderControl.OnChangedListener()
        {
            @Override
            public void OnChanged(View view, float currentPosition)
            {
                Log.d(LOGTAG, "volumeControl: OnChanged: current=" + currentPosition);
            }
        });

        volumeArea.addView(volumeControl);
        */

        //endregion Left sound section.

        //region Left logoff button.

        LinearLayout logoffArea = new LinearLayout(this);
        logoffArea.setOrientation(LinearLayout.VERTICAL);
        logoffArea.setGravity(Gravity.BOTTOM);
        Simple.setSizeDip(logoffArea, Simple.MP, Simple.MP, 1.0f);
        Simple.setMarginTopDip(logoffArea, Defines.PADDING_SMALL);

        leftArea.addView(logoffArea);

        TextView logoffButton = new TextView(this);
        logoffButton.setText(R.string.settings_logoff);
        logoffButton.setTextColor(Color.WHITE);
        logoffButton.setTypeface(buttonsTF);
        logoffButton.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        logoffButton.setAllCaps(Defines.isButtonAllCaps);
        Simple.setSizeDip(logoffButton, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(logoffButton, Defines.FS_SETTINGS_BUTTON);
        Simple.setPaddingDip(logoffButton, 0, Defines.PADDING_SMALL, 0, Defines.PADDING_SMALL);
        Simple.setMarginTopDip(logoffButton, Defines.PADDING_LARGE);

        if (Defines.COLOR_BUTTON_BACK == Color.BLACK)
        {
            logoffButton.setTextColor(Color.BLACK);
            Simple.setRoundedCorners(logoffButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, false);
        }
        else
        {
            logoffButton.setTextColor(Color.WHITE);
            Simple.setRoundedCorners(logoffButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, true);
        }

        logoffButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SettingsHandler.killSettings();

                Simple.startActivityTop(SettingsActivity.this, MainActivity.class);
            }
        });

        logoffArea.addView(logoffButton);

        TextView passwordButton = new TextView(this);
        passwordButton.setText(R.string.settings_change_password);
        passwordButton.setTextColor(Color.WHITE);
        passwordButton.setTypeface(buttonsTF);
        passwordButton.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        passwordButton.setAllCaps(Defines.isButtonAllCaps);
        Simple.setSizeDip(passwordButton, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(passwordButton, Defines.FS_SETTINGS_BUTTON);
        Simple.setPaddingDip(passwordButton, 0, Defines.PADDING_SMALL, 0, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(passwordButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, true);
        Simple.setMarginTopDip(passwordButton, Defines.PADDING_LARGE);

        passwordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                topFrame.addView(new PasswordChangeDialog(SettingsActivity.this));
            }
        });

        logoffArea.addView(passwordButton);

        //endregion Left logoff button.

        //endregion Left area.

        //region Right area.

        rightArea = new LinearLayout(this);
        rightArea.setOrientation(LinearLayout.VERTICAL);
        rightArea.setBackgroundColor(Defines.COLOR_FRAMES);
        Simple.setSizeDip(rightArea, Simple.MP, Simple.MP, 0.4f);
        Simple.setPaddingDip(rightArea, Defines.PADDING_NORMAL);

        Simple.setMarginDip(rightArea,
                Defines.PADDING_LARGE / 2, Defines.PADDING_SMALL,
                Defines.PADDING_LARGE, Defines.PADDING_LARGE);

        bodyHorz.addView(rightArea);

        //region Right top area.

        LinearLayout rightTopArea = new LinearLayout(this);
        rightTopArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(rightTopArea, Simple.MP, Defines.FS_SETTINGS_TITLE * 3);

        rightArea.addView(rightTopArea);

        TextView contentTitle = new TextView(this);
        contentTitle.setText(R.string.settings_contents);
        contentTitle.setSingleLine();
        contentTitle.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
        contentTitle.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        contentTitle.setTypeface(headerTF);
        contentTitle.setAllCaps(true);
        Simple.setTextSizeDip(contentTitle, Defines.FS_SETTINGS_TITLE);
        Simple.setSizeDip(contentTitle, Simple.MP, Simple.MP, 0.58f);
        Simple.setLetterSpacing(contentTitle, Defines.FS_NAVIGATION_LSPACE);

        rightTopArea.addView(contentTitle);

        LinearLayout contentSizeFrame = new LinearLayout(this);
        contentSizeFrame.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(contentSizeFrame, Simple.MP, Simple.WC, 0.42f);
        Simple.setRoundedCorners(contentSizeFrame, Defines.CORNER_RADIUS_BIGBUT, Color.LTGRAY, true);

        Simple.setPaddingDip(contentSizeFrame,
                Defines.PADDING_NORMAL, Defines.PADDING_SMALL,
                Defines.PADDING_NORMAL, Defines.PADDING_SMALL);

        rightTopArea.addView(contentSizeFrame);

        TextView contentSizeText = new TextView(this);
        contentSizeText.setText(R.string.settings_used_storage);
        contentSizeText.setSingleLine();
        contentSizeText.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
        contentSizeText.setTextColor(Color.WHITE);
        contentSizeText.setTypeface(headerTF);
        Simple.setSizeDip(contentSizeText, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(contentSizeText, Defines.FS_SETTINGS_TITLE);

        contentSizeFrame.addView(contentSizeText);

        contentSizeMB = new TextView(this);
        contentSizeMB.setSingleLine();
        contentSizeMB.setGravity(Gravity.CENTER_VERTICAL + Gravity.END);
        contentSizeMB.setTextColor(Color.WHITE);
        contentSizeMB.setTypeface(headerTF);
        Simple.setSizeDip(contentSizeMB, Simple.MP, Simple.WC, 1.0f);
        Simple.setTextSizeDip(contentSizeMB, Defines.FS_SETTINGS_TITLE);

        contentSizeFrame.addView(contentSizeMB);

        //endregion Right top area.

        //region Right content area.

        TextView contentSection = new TextView(this);
        contentSection.setText(R.string.settings_your_contents);
        contentSection.setAllCaps(true);
        contentSection.setTextColor(Color.BLACK);
        contentSection.setTypeface(subheadTF);
        Simple.setSizeDip(contentSection, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(contentSection, Defines.PADDING_SMALL);
        Simple.setMarginBottomDip(contentSection, Defines.PADDING_SMALL);
        Simple.setTextSizeDip(contentSection, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(contentSection, Defines.FS_NAVIGATION_LSPACE);

        rightArea.addView(contentSection);

        assetGrid.setNumColumns(1);
        assetGrid.setColumnWidth(GridView.AUTO_FIT);
        assetGrid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        assetGrid.setVerticalSpacing(Simple.dipToPx(Defines.PADDING_TINY * 2));
        assetGrid.setHorizontalSpacing(0);
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

                    bodyHorz.removeView(rightArea);
                    bodyHorz.addView(detailView);

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
            total += file_size / (1000 * 1024);
        }

        contentSizeMB.setText(Simple.getTrans(this,
                R.string.settings_used_storage_mb,
                (total < 1) ? "< 1" : Simple.formatDecimal(total)));

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
        if (rightArea.getParent() == null)
        {
            bodyHorz.addView(rightArea);
        }
    }
}