package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;

public class SettingsDetail extends LinearLayout
{
    private static final String LOGTAG = SettingsDetail.class.getSimpleName();

    protected JSONObject content;

    protected FrameLayout imageFrame;
    protected ImageView contentImage;
    protected ImageView typeIcon;

    public SettingsDetail(Context context)
    {
        this(context, null);
    }

    public SettingsDetail(Context context, JSONObject content)
    {
        super(context);

        this.content = content;

        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(Defines.COLOR_SENSOR_CONTENT);
        Simple.setSizeDip(this, Simple.MP, Simple.MP, 0.4f);

        Simple.setPaddingDip(this,
                Defines.PADDING_LARGE, Defines.PADDING_SMALL,
                Defines.PADDING_LARGE, Defines.PADDING_LARGE);

        //region Top area.

        LinearLayout topArea = new LinearLayout(getContext());
        topArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(topArea, Simple.MP, Defines.FS_SETTINGS_TITLE * 3);

        addView(topArea);

        ImageView backButtonImage = new ImageView(getContext());
        backButtonImage.setImageResource(Screens.getArrowDarkLeftOnRes());
        backButtonImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Simple.setSizeDip(backButtonImage, Simple.WC, Simple.MP);
        Simple.setPaddingDip(backButtonImage, Defines.PADDING_SMALL);
        Simple.setMarginRightDip(backButtonImage, Defines.PADDING_NORMAL);

        topArea.addView(backButtonImage);

        TextView contentTitle = new TextView(getContext());
        contentTitle.setText(R.string.settings_detail_title);
        contentTitle.setSingleLine();
        contentTitle.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
        contentTitle.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        contentTitle.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        contentTitle.setAllCaps(true);
        Simple.setTextSizeDip(contentTitle, Defines.FS_SETTINGS_TITLE);
        Simple.setSizeDip(contentTitle, Simple.MP, Simple.MP);
        Simple.setLetterSpacing(contentTitle, Defines.FS_NAVIGATION_LSPACE);

        topArea.addView(contentTitle);

        //endregion Top area.

        //region Title.

        TextView titleSection = new TextView(getContext());
        titleSection.setText(Json.getString(content, "category"));
        titleSection.setAllCaps(true);
        titleSection.setTextColor(Color.BLACK);
        titleSection.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setSizeDip(titleSection, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(titleSection, Defines.PADDING_TINY);
        Simple.setMarginBottomDip(titleSection, Defines.PADDING_SMALL);
        Simple.setTextSizeDip(titleSection, Defines.FS_SETTINGS_INFO);
        Simple.setLetterSpacing(titleSection, Defines.FS_NAVIGATION_LSPACE);

        addView(titleSection);

        //endregion Title.

        //region Image and image type icon.

        imageFrame = new FrameLayout(getContext());

        addView(imageFrame);

        contentImage = new ImageView(getContext());
        contentImage.setScaleType(ImageView.ScaleType.FIT_XY);

        imageFrame.addView(contentImage);

        RelativeLayout iconCenter = new RelativeLayout(getContext());
        iconCenter.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(iconCenter, Simple.MP, Simple.MP);

        imageFrame.addView(iconCenter);

        typeIcon = new ImageView(getContext());
        typeIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);

        iconCenter.addView(typeIcon);

        //endregion Image and image type icon.

        //region Misc area with specs and buy.

        LinearLayout miscArea = new LinearLayout(getContext());
        miscArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(miscArea, Simple.MP, Simple.MP);
        Simple.setMarginTopDip(miscArea, Defines.PADDING_NORMAL);

        addView(miscArea);

        //region Technical specs area.

        String title = Json.getString(content, "title");
        String theme = Json.getString(content, "sub_title");
        int content_type = Json.getInt(content, "content_type");
        int file_duration = Json.getInt(content, "file_duration");
        long file_size = Json.getLong(content, "file_size");
        long mbytes = file_size / (1000 * 1024);

        LinearLayout specsArea = new LinearLayout(getContext());
        specsArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(specsArea, Simple.MP, Simple.MP);
        Simple.setPaddingDip(specsArea, Defines.PADDING_NORMAL);
        Simple.setRoundedCorners(specsArea, Defines.CORNER_RADIUS_BIGBUT, Color.WHITE, true);

        miscArea.addView(specsArea);

        RelativeLayout separ;

        TableLikeLayout titleView = new TableLikeLayout(getContext(),
            Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_BOLD),
                Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_BOLD));

        titleView.setLeftText(R.string.settings_specs_title);

        titleView.setRightText(title);

        specsArea.addView(titleView);

        separ = new RelativeLayout(getContext());
        separ.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 2);
        Simple.setMarginDip(separ, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);

        specsArea.addView(separ);

        TableLikeLayout themeView = new TableLikeLayout(getContext(), true);
        themeView.setLeftText(R.string.settings_specs_theme);

        themeView.setRightText(theme);

        specsArea.addView(themeView);

        separ = new RelativeLayout(getContext());
        separ.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 2);
        Simple.setMarginDip(separ, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);

        specsArea.addView(separ);

        TableLikeLayout fileView = new TableLikeLayout(getContext(), true);
        fileView.setLeftText(R.string.settings_specs_file);

        fileView.setRightText(content_type == 2
                ? R.string.detail_specs_type_video
                : R.string.detail_specs_type_unknown);

        specsArea.addView(fileView);

        separ = new RelativeLayout(getContext());
        separ.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 2);
        Simple.setMarginDip(separ, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);

        specsArea.addView(separ);

        TableLikeLayout quantView = new TableLikeLayout(getContext(), true);
        quantView.setLeftText(R.string.settings_specs_quantity);

        if (content_type == 2)
        {
            int minutes = 1 + (file_duration / 60);

            quantView.setRightText(Simple.getTrans(getContext(),
                    R.string.settings_specs_quantity_duration,
                    String.valueOf(minutes)));
        }
        else
        {
            quantView.setRightText("-");
        }

        specsArea.addView(quantView);

        separ = new RelativeLayout(getContext());
        separ.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 2);
        Simple.setMarginDip(separ, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);

        specsArea.addView(separ);

        TableLikeLayout sizeView = new TableLikeLayout(getContext(), true);
        sizeView.setLeftText(R.string.settings_specs_size);

        sizeView.setRightText(Simple.getTrans(getContext(),
                R.string.settings_specs_size_mb,
                Simple.formatDecimal(mbytes)));

        specsArea.addView(sizeView);

        separ = new RelativeLayout(getContext());
        separ.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 2);
        Simple.setMarginDip(separ, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);

        specsArea.addView(separ);

        TableLikeLayout seenView = new TableLikeLayout(getContext(), true);
        seenView.setLeftText(R.string.settings_specs_seen);

        seenView.setRightText(Simple.getTrans(getContext(),
                R.string.settings_specs_seen_date,
                "11.11.2011"));

        specsArea.addView(seenView);

        separ = new RelativeLayout(getContext());
        separ.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 2);
        Simple.setMarginDip(separ, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);

        specsArea.addView(separ);

        //region Delete button.

        RelativeLayout deleteArea = new RelativeLayout(getContext());
        deleteArea.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(deleteArea, Simple.MP, Simple.MP);
        Simple.setMarginTopDip(deleteArea, Defines.PADDING_LARGE);

        specsArea.addView(deleteArea);

        TextView deleteButton = new TextView(getContext());
        deleteButton.setText(R.string.settings_detail_delete);
        deleteButton.setTextColor(Color.WHITE);
        deleteButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(deleteButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(deleteButton, Defines.FS_DIALOG_BUTTON);
        Simple.setRoundedCorners(deleteButton, Defines.CORNER_RADIUS_BIGBUT, Color.RED, true);

        Simple.setPaddingDip(deleteButton,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL);

        deleteArea.addView(deleteButton);

        //endregion Delete button.

        //endregion Technical specs area.

        //endregion Misc area with specs and delete.
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);

        ApplicationBase.handler.post(displayImage);
    }

    private final Runnable displayImage = new Runnable()
    {
        @Override
        public void run()
        {
            int imageWidth = SettingsDetail.this.getWidth();
            int imageHeight = Math.round(imageWidth / Defines.FS_ASSET_SETTINGS_ASPECT);

            Simple.setSizeDip(imageFrame, Simple.MP, Simple.pxToDip(imageHeight));
            Simple.setSizeDip(contentImage, Simple.MP, Simple.pxToDip(imageHeight));

            Log.d(LOGTAG, "displayImage: imageWidth=" + imageWidth + " imageHeight=" + imageHeight);

            String detailUrl = Json.getString(content, "detail_image_url");

            contentImage.setImageDrawable(
                    AssetsImageManager.getDrawableOrFetch(
                            SettingsDetail.this.getContext(),
                            contentImage, detailUrl,
                            imageWidth, imageHeight, false));
        }
    };
}
