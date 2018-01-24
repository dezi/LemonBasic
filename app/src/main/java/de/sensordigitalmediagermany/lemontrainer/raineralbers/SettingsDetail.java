package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View;
import android.util.Log;

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
        Simple.setSizeDip(this, Simple.MP, Simple.MP, 0.4f);

        if (Defines.isCompactSettings)
        {
            if (! Simple.isTablet())
            {
                setBackgroundColor(Defines.COLOR_FRAMES);

                Simple.setSizeDip(this, Simple.MP, Simple.WC);

                Simple.setMarginDip(this, Defines.PADDING_LARGE);

                Simple.setPaddingDip(this,
                        Defines.PADDING_LARGE, Defines.PADDING_SMALL,
                        Defines.PADDING_LARGE, Defines.PADDING_LARGE);
            }
        }
        else
        {
            setBackgroundColor(Defines.COLOR_CONTENT);

            Simple.setPaddingDip(this,
                    Defines.PADDING_LARGE, Defines.PADDING_SMALL,
                    Defines.PADDING_LARGE, Defines.PADDING_LARGE);
        }

        Typeface headerTF = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_SETTINGS_HEADER);
        Typeface infosTF = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_SETTINGS_INFOS);
        Typeface buttonsTF = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_DIALOG_BUTTON);

        //region Top area.

        LinearLayout topArea = new LinearLayout(getContext());
        topArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(topArea, Simple.MP, Simple.WC);
        Simple.setPaddingDip(topArea, 0, Defines.PADDING_SMALL, 0, Defines.PADDING_SMALL);

        addView(topArea);

        ImageView backButtonImage = new ImageView(getContext());
        backButtonImage.setImageResource(DefinesScreens.getArrowDarkLeftOnRes());
        backButtonImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Simple.setSizeDip(backButtonImage, Defines.SETTINGS_BACK_SIZE, Simple.MP);

        if (Defines.isCompactSettings)
        {
            Simple.setPaddingDip(backButtonImage, Defines.PADDING_TINY);
        }

        backButtonImage.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goBack();
            }
        });

        topArea.addView(backButtonImage);

        if ((! Simple.isTablet()) || ! Defines.isCompactSettings)
        {
            TextView contentTitle = new TextView(getContext());
            contentTitle.setText(R.string.settings_detail_title);
            contentTitle.setSingleLine();
            contentTitle.setGravity(Gravity.CENTER_VERTICAL + Gravity.START);
            contentTitle.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
            contentTitle.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
            contentTitle.setAllCaps(true);
            Simple.setTextSizeDip(contentTitle, Defines.FS_SETTINGS_TITLE);
            Simple.setSizeDip(contentTitle, Simple.MP, Simple.MP);
            Simple.setLetterSpacing(contentTitle, Defines.FS_NAVIGATION_LSSPACE);
            Simple.setMarginLeftDip(contentTitle, Defines.PADDING_NORMAL);

            topArea.addView(contentTitle);
        }

        //endregion Top area.

        //region Title.

        if (! Defines.isCompactSettings)
        {
            TextView titleSection = new TextView(getContext());
            titleSection.setText(Json.getString(content, "category"));
            titleSection.setAllCaps(true);
            titleSection.setTextColor(Color.BLACK);
            titleSection.setTypeface(headerTF);
            Simple.setSizeDip(titleSection, Simple.MP, Simple.WC);
            Simple.setMarginTopDip(titleSection, Defines.PADDING_TINY);
            Simple.setMarginBottomDip(titleSection, Defines.PADDING_SMALL);
            Simple.setTextSizeDip(titleSection, Defines.FS_SETTINGS_INFO);
            Simple.setLetterSpacing(titleSection, Defines.FS_NAVIGATION_LSSPACE);

            addView(titleSection);
        }

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

        //region Misc area with specs and delete.

        LinearLayout miscArea = new LinearLayout(getContext());
        miscArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(miscArea, Simple.MP, Simple.MP);
        Simple.setMarginTopDip(miscArea, Defines.PADDING_NORMAL);

        if (Defines.isCompactDetails && ! Simple.isTablet())
        {
            miscArea.setBackgroundColor(Color.WHITE);

            Simple.setSizeDip(miscArea, Simple.MP, Simple.WC);
            Simple.setMarginTopDip(miscArea, Defines.PADDING_ZERO);
            Simple.setPaddingDip(miscArea, Defines.PADDING_NORMAL);
        }

        addView(miscArea);

        //region Technical specs area.

        String title = Json.getString(content, "title");
        String theme = Json.getString(content, "sub_title");
        int content_type = Json.getInt(content, "content_type");
        int file_duration = Json.getInt(content, "file_duration");
        long file_size = Json.getLong(content, "file_size");

        LinearLayout specsArea = new LinearLayout(getContext());
        specsArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(specsArea, Simple.MP, Simple.MP);

        if (! Defines.isCompactSettings)
        {
            specsArea.setBackgroundColor(Color.WHITE);
            Simple.setPaddingDip(specsArea, Defines.PADDING_NORMAL);
        }

        miscArea.addView(specsArea);

        TableLikeLayout titleView = new TableLikeLayout(getContext(), headerTF, headerTF);

        titleView.setLeftText(R.string.settings_specs_title);

        titleView.setRightText(title);

        specsArea.addView(titleView);

        specsArea.addView(createSeparator());

        if (! Defines.isCompactSettings)
        {
            TableLikeLayout themeView = new TableLikeLayout(getContext(), infosTF, infosTF);

            themeView.setLeftText(R.string.settings_specs_theme);

            themeView.setRightText(theme);

            specsArea.addView(themeView);

            specsArea.addView(createSeparator());
        }

        TableLikeLayout fileView = new TableLikeLayout(getContext(), infosTF, infosTF);
        fileView.setLeftText(R.string.settings_specs_file);

        int typeResid = R.string.detail_specs_type_unknown;

        if (content_type == Defines.CONTENT_TYPE_PDF) typeResid = R.string.detail_specs_type_pdf;
        if (content_type == Defines.CONTENT_TYPE_VIDEO) typeResid = R.string.detail_specs_type_video;
        if (content_type == Defines.CONTENT_TYPE_ZIP) typeResid = R.string.detail_specs_type_zip;

        fileView.setRightText(typeResid);

        specsArea.addView(fileView);

        specsArea.addView(createSeparator());

        TableLikeLayout quantView = new TableLikeLayout(getContext(), infosTF, infosTF);
        quantView.setLeftText(R.string.settings_specs_quantity);

        quantView.setRightText("-");

        if (content_type == Defines.CONTENT_TYPE_PDF)
        {
            quantView.setRightText(Simple.getTrans(getContext(),
                    (file_duration == 1)
                            ? R.string.detail_specs_quantity_onepage
                            : R.string.detail_specs_quantity_pages,
                    String.valueOf(file_duration)));
        }

        if ((content_type == Defines.CONTENT_TYPE_AUDIO) || (content_type == Defines.CONTENT_TYPE_VIDEO))
        {
            int minutes = 1 + (file_duration / 60);

            quantView.setRightText(Simple.getTrans(getContext(),
                    R.string.settings_specs_quantity_duration,
                    String.valueOf(minutes)));
        }

        specsArea.addView(quantView);

        specsArea.addView(createSeparator());

        TableLikeLayout sizeView = new TableLikeLayout(getContext(), infosTF, infosTF);
        sizeView.setLeftText(R.string.settings_specs_size);

        sizeView.setRightText(Simple.formatBytes(file_size));

        specsArea.addView(sizeView);

        specsArea.addView(createSeparator());

        if (! Defines.isCompactSettings)
        {
            TableLikeLayout seenView = new TableLikeLayout(getContext(), infosTF, infosTF);
            seenView.setLeftText(R.string.settings_specs_seen);

            seenView.setRightText(Simple.getTrans(getContext(),
                    R.string.settings_specs_seen_date,
                    "11.11.2011"));

            specsArea.addView(seenView);

            specsArea.addView(createSeparator());
        }

        //region Delete button.

        RelativeLayout deleteArea = new RelativeLayout(getContext());
        deleteArea.setGravity(Gravity.END + Gravity.TOP);
        Simple.setSizeDip(deleteArea, Simple.MP, Simple.MP);
        Simple.setMarginTopDip(deleteArea, Defines.PADDING_SMALL);

        specsArea.addView(deleteArea);

        TextView deleteButton = new TextView(getContext());
        deleteButton.setText(R.string.settings_detail_delete);
        deleteButton.setAllCaps(Defines.isButtonAllCaps);
        deleteButton.setTextColor(Color.WHITE);
        deleteButton.setTypeface(buttonsTF);
        Simple.setSizeDip(deleteButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(deleteButton, Defines.FS_SETTINGS_BUTTON);

        if (Defines.isCompactSettings)
        {
            if (Simple.isTablet())
            {
            }
            else
            {
                Simple.setSizeDip(deleteButton, Simple.MP, Simple.WC);
                deleteButton.setGravity(Gravity.CENTER_HORIZONTAL);
            }

            Simple.setRoundedCorners(deleteButton, Defines.CORNER_RADIUS_BUTTON, Color.BLACK, true);
        }
        else
        {
            deleteArea.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
            Simple.setRoundedCorners(deleteButton, Defines.CORNER_RADIUS_BUTTON, Color.RED, true);
        }

        Simple.setPaddingDip(deleteButton,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL);

        deleteButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AppCompatActivity activity = ApplicationBase.getCurrentActivity(view.getContext());

                if (activity instanceof FullScreenActivity)
                {
                    ViewGroup topframe = ((FullScreenActivity) activity).topFrame;

                    DialogView.yesnoAlert(topframe, R.string.alert_settings_delete_title,
                            Simple.getTrans(view.getContext(), R.string.alert_settings_delete_info),
                            new OnClickListener()
                            {
                                @Override
                                public void onClick(View view)
                                {
                                    deleteContent();
                                }
                            });
                }
            }
        });

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

    private RelativeLayout createSeparator()
    {
        RelativeLayout separ = new RelativeLayout(getContext());
        separ.setBackgroundColor(Defines.isCompactSettings ? Color.BLACK : Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 1);

        if (Simple.isTablet())
        {
            Simple.setMarginDip(separ, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);
        }
        else
        {
            Simple.setMarginDip(separ, 0, Defines.PADDING_NORMAL, 0, Defines.PADDING_NORMAL);
        }

        return separ;
    }

    private final Runnable displayImage = new Runnable()
    {
        @Override
        public void run()
        {
            int imageWidth = SettingsDetail.this.getWidth();
            int imageHeight = Math.round(imageWidth / Defines.ASSET_SETTINGS_ASPECT);

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

    private void deleteContent()
    {
        boolean ok = ContentHandler.deleteCachedFile(content);

        AppCompatActivity activity = ApplicationBase.getCurrentActivity(getContext());

        if (activity instanceof SettingsActivity)
        {
            ViewGroup topframe = ((SettingsActivity) activity).topFrame;

            DialogView.errorAlert(topframe, R.string.alert_settings_delete_oktitle,
                    Simple.getTrans(getContext(), ok
                            ? R.string.alert_settings_delete_ok
                            : R.string.alert_settings_delete_fail));

            if (ok)
            {
                ((SettingsActivity) activity).removeContent(content);

                goBack();
            }
        }
    }

    private void goBack()
    {
        ((ViewGroup) getParent()).removeView(SettingsDetail.this);

        AppCompatActivity activity = ApplicationBase.getCurrentActivity(getContext());

        if (activity instanceof SettingsActivity)
        {
            ((SettingsActivity) activity).reAttachRightArea();
        }
    }
}
