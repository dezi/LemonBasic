package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

public class SettingsActivity extends ContentBaseActivity
{
    private static final String LOGTAG = ContentActivity.class.getSimpleName();

    protected TextView naviLeftButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //
        // Remove asset grid for re-arrangement.
        //

        ((ViewGroup) assetGrid.getParent()).removeView(assetGrid);

        //region Navigation title.

        naviFrame.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(naviFrame, Simple.MP, Defines.NAVIGATION_HEIGHT);

        naviLeftButton = new TextView(this);
        naviLeftButton.setText(R.string.settings_title);
        naviLeftButton.setAllCaps(true);
        naviLeftButton.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
        naviLeftButton.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        naviLeftButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(naviLeftButton, Defines.FS_NAVI_MENU);
        Simple.setSizeDip(naviLeftButton, Simple.MP, Simple.MP, 0.5f);
        Simple.setPaddingDip(naviLeftButton, Defines.PADDING_LARGE, 0, 0, 0);

        naviFrame.addView(naviLeftButton);

        //endregion Navigation title.

        //region Body frames.

        LinearLayout bodyHorz = new LinearLayout(this);
        bodyHorz.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(bodyHorz, Simple.MP, Simple.MP);

        contentFrame.addView(bodyHorz);

        //region Left area.

        LinearLayout leftArea = new LinearLayout(this);
        leftArea.setOrientation(LinearLayout.VERTICAL);
        leftArea.setBackgroundColor(Defines.COLOR_SENSOR_CONTENT);
        Simple.setSizeDip(leftArea, Simple.MP, Simple.MP, 0.6f);

        Simple.setPaddingDip(leftArea,
                Defines.PADDING_LARGE, Defines.PADDING_SMALL,
                Defines.PADDING_LARGE, Defines.PADDING_LARGE);

        bodyHorz.addView(leftArea);

        //region Left top area.

        LinearLayout leftTopArea = new LinearLayout(this);
        leftTopArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(leftTopArea, Simple.MP, Defines.FS_SETTINGS_TITLE * 3);

        leftArea.addView(leftTopArea);

        TextView settingsTitle = new TextView(this);
        settingsTitle.setText(R.string.settings_data);
        settingsTitle.setSingleLine();
        settingsTitle.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
        settingsTitle.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        settingsTitle.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        settingsTitle.setAllCaps(true);
        Simple.setTextSizeDip(settingsTitle, Defines.FS_SETTINGS_TITLE);
        Simple.setSizeDip(settingsTitle, Simple.MP, Simple.MP);
        Simple.setLetterSpacing(settingsTitle, Defines.FS_NAVIGATION_LSPACE);

        leftTopArea.addView(settingsTitle);

        //endregion Left top area.

        //region Left profile image.

        RelativeLayout profileFrame = new RelativeLayout(this);
        profileFrame.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(profileFrame, Simple.MP, Simple.WC);

        leftArea.addView(profileFrame);

        FrameLayout profileImageHolder = new FrameLayout(this);
        Simple.setSizeDip(profileImageHolder, Defines.PROFILE_IMAGE_SIZE, Defines.PROFILE_IMAGE_SIZE);

        profileFrame.addView(profileImageHolder);

        ImageView profileImage = new ImageView(this);
        profileImage.setImageResource(R.drawable.lem_t_iany_genric_profil_platzhalter_225_2x);
        profileImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Simple.setSizeDip(profileImage, Simple.MP, Simple.MP);

        profileImageHolder.addView(profileImage);

        TextView profileEditButton = new TextView(this);
        profileEditButton.setText(R.string.settings_edit_image);
        profileEditButton.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.BOTTOM);
        profileEditButton.setTextColor(Color.WHITE);
        profileEditButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(profileEditButton, Simple.MP, Simple.MP);
        Simple.setTextSizeDip(profileEditButton, Defines.FS_SETTINGS_UPLOAD);
        Simple.setPaddingDip(profileEditButton, Defines.PADDING_SMALL);

        profileImageHolder.addView(profileEditButton);

        //endregion Left profile image.

        //region Left personal data.

        TextView nameSection = new TextView(this);
        nameSection.setText(R.string.settings_name);
        nameSection.setAllCaps(true);
        nameSection.setTextColor(Color.BLACK);
        nameSection.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setSizeDip(nameSection, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(nameSection, Defines.PADDING_TINY);
        Simple.setTextSizeDip(nameSection, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(nameSection, Defines.FS_NAVIGATION_LSPACE);

        leftArea.addView(nameSection);

        String userName = Globals.firstName + " " + Globals.lastName;

        EditText nameEdit = new EditText(this);
        nameEdit.setText(userName);
        nameEdit.setFocusable(false);
        nameEdit.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        nameEdit.setBackgroundColor(Defines.COLOR_SENSOR_NAVIBAR);
        nameEdit.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAMNARROW_LIGHT));
        Simple.setTextSizeDip(nameEdit, Defines.FS_SETTINGS_EDIT);
        Simple.setSizeDip(nameEdit, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(nameEdit, Defines.PADDING_TINY);
        Simple.setPaddingDip(nameEdit,Defines.PADDING_SMALL);

        leftArea.addView(nameEdit);

        TextView companySection = new TextView(this);
        companySection.setText(R.string.settings_company);
        companySection.setAllCaps(true);
        companySection.setTextColor(Color.BLACK);
        companySection.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setSizeDip(companySection, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(companySection, Defines.PADDING_SMALL);
        Simple.setTextSizeDip(companySection, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(companySection, Defines.FS_NAVIGATION_LSPACE);

        leftArea.addView(companySection);

        EditText companyEdit = new EditText(this);
        companyEdit.setText(Globals.company);
        companyEdit.setFocusable(false);
        companyEdit.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        companyEdit.setBackgroundColor(Defines.COLOR_SENSOR_NAVIBAR);
        companyEdit.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAMNARROW_LIGHT));
        Simple.setTextSizeDip(companyEdit, Defines.FS_SETTINGS_EDIT);
        Simple.setSizeDip(companyEdit, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(companyEdit, Defines.PADDING_TINY);
        Simple.setPaddingDip(companyEdit,Defines.PADDING_SMALL);

        leftArea.addView(companyEdit);

        TextView emailSection = new TextView(this);
        emailSection.setText(R.string.settings_email);
        emailSection.setAllCaps(true);
        emailSection.setTextColor(Color.BLACK);
        emailSection.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setSizeDip(emailSection, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(emailSection, Defines.PADDING_SMALL);
        Simple.setTextSizeDip(emailSection, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(emailSection, Defines.FS_NAVIGATION_LSPACE);

        leftArea.addView(emailSection);

        EditText emailEdit = new EditText(this);
        emailEdit.setText(Globals.emailAddress);
        emailEdit.setFocusable(false);
        emailEdit.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        emailEdit.setBackgroundColor(Defines.COLOR_SENSOR_NAVIBAR);
        emailEdit.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAMNARROW_LIGHT));
        Simple.setTextSizeDip(emailEdit, Defines.FS_SETTINGS_EDIT);
        Simple.setSizeDip(emailEdit, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(emailEdit, Defines.PADDING_TINY);
        Simple.setPaddingDip(emailEdit,Defines.PADDING_SMALL);

        leftArea.addView(emailEdit);

        TextView passwordSection = new TextView(this);
        passwordSection.setText(R.string.settings_password);
        passwordSection.setAllCaps(true);
        passwordSection.setTextColor(Color.BLACK);
        passwordSection.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
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
        passwordEdit.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAMNARROW_LIGHT));
        Simple.setTextSizeDip(passwordEdit, Defines.FS_SETTINGS_EDIT);
        Simple.setSizeDip(passwordEdit, Simple.WC, Simple.WC, 1.0f);
        Simple.setPaddingDip(passwordEdit,Defines.PADDING_SMALL);

        passwordArea.addView(passwordEdit);

        TextView changePasswordButton = new TextView(this);
        changePasswordButton.setText(R.string.settings_change_password);
        changePasswordButton.setTextColor(Color.WHITE);
        changePasswordButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        changePasswordButton.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(changePasswordButton, Simple.WC, Simple.MP);
        Simple.setMarginDip(changePasswordButton, Defines.MARGIN_BUTTON);
        Simple.setTextSizeDip(changePasswordButton, Defines.FS_SETTINGS_BUTTON);
        Simple.setPaddingDip(changePasswordButton, Defines.PADDING_NORMAL, 0, Defines.PADDING_NORMAL, 0);
        Simple.setRoundedCorners(changePasswordButton, Defines.CORNER_RADIUS_BUTTON, Color.BLACK, true);

        passwordArea.addView(changePasswordButton);

        //endregion Left personal data.

        //region Left sound section.

        TextView soundSection = new TextView(this);
        soundSection.setText(R.string.settings_sound);
        soundSection.setAllCaps(true);
        soundSection.setTextColor(Color.BLACK);
        soundSection.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
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
        onoffText.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setSizeDip(onoffText, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(onoffText, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(onoffText, Defines.FS_NAVIGATION_LSPACE);
        Simple.setPaddingDip(onoffText, Defines.PADDING_SMALL);

        onoffArea.addView(onoffText);

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
        volumeText.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setSizeDip(volumeText, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(volumeText, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(volumeText, Defines.FS_NAVIGATION_LSPACE);
        Simple.setPaddingDip(volumeText, Defines.PADDING_SMALL);

        volumeArea.addView(volumeText);

        //endregion Left sound section.

        //region Left logoff button.

        RelativeLayout logoffArea = new RelativeLayout(this);
        logoffArea.setGravity(Gravity.BOTTOM);
        Simple.setSizeDip(logoffArea, Simple.MP, Simple.MP, 1.0f);
        Simple.setMarginTopDip(logoffArea, Defines.PADDING_SMALL);

        leftArea.addView(logoffArea);

        TextView logoffButton = new TextView(this);
        logoffButton.setText(R.string.settings_logoff);
        logoffButton.setTextColor(Color.WHITE);
        logoffButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        logoffButton.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(logoffButton, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(logoffButton, Defines.FS_SETTINGS_BUTTON);
        Simple.setPaddingDip(logoffButton, 0, Defines.PADDING_SMALL, 0, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(logoffButton, Defines.CORNER_RADIUS_BUTTON, Color.BLACK, true);

        logoffArea.addView(logoffButton);

        //endregion Left logoff button.

        //endregion Left area.

        //region Right area.

        LinearLayout rightArea = new LinearLayout(this);
        rightArea.setOrientation(LinearLayout.VERTICAL);
        rightArea.setBackgroundColor(Defines.COLOR_SENSOR_CONTENT);
        Simple.setSizeDip(rightArea, Simple.MP, Simple.MP, 0.4f);

        Simple.setPaddingDip(rightArea,
                Defines.PADDING_LARGE, Defines.PADDING_SMALL,
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
        contentTitle.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
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
        contentSizeText.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(contentSizeText, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(contentSizeText, Defines.FS_SETTINGS_TITLE);

        contentSizeFrame.addView(contentSizeText);

        TextView contentSizeMB = new TextView(this);
        contentSizeMB.setSingleLine();
        contentSizeMB.setGravity(Gravity.CENTER_VERTICAL + Gravity.END);
        contentSizeMB.setTextColor(Color.WHITE);
        contentSizeMB.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(contentSizeMB, Simple.MP, Simple.WC, 1.0f);
        Simple.setTextSizeDip(contentSizeMB, Defines.FS_SETTINGS_TITLE);

        contentSizeFrame.addView(contentSizeMB);

        //endregion Right top area.

        //region Right content area.

        TextView contentSection = new TextView(this);
        contentSection.setText(R.string.settings_your_contents);
        contentSection.setAllCaps(true);
        contentSection.setTextColor(Color.BLACK);
        contentSection.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
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

        JSONArray content = ContentHandler.getFilteredContent(true, null, true);

        assetsAdapter.setHorizontal(true);
        assetsAdapter.setAssets(content);

        long total = 0;

        for (int inx = 0; inx < content.length(); inx++)
        {
            JSONObject item = Json.getObject(content, inx);
            if (item == null) continue;

            long file_size = Json.getLong(item, "file_size");
            total += file_size / (1000 * 1024);
        }

        contentSizeMB.setText(Simple.getTrans(this,
                R.string.settings_used_storage_mb,
                Simple.formatDecimal(total)));
    }
}