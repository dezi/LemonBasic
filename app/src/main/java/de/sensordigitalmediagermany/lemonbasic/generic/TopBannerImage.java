package de.sensordigitalmediagermany.lemonbasic.generic;

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

public class TopBannerImage extends FrameLayout
{
    private static final String LOGTAG = TopBannerImage.class.getSimpleName();

    private int imageWidth;
    private int imageHeight;

    private ImageView contentImage;
    private LinearLayout infoContent;
    private TextView infoTitle;
    private TextView infoSummary;

    public TopBannerImage(Context context)
    {
        this(context, -1, -1);
    }

    public TopBannerImage(Context context, int imageWidth, int imageHeight)
    {
        super(context);

        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;

        contentImage = new ImageView(context);
        contentImage.setScaleType(ImageView.ScaleType.FIT_XY);

        Simple.setSizeDip(this, Simple.pxToDip(imageWidth), Simple.pxToDip(imageHeight));
        Simple.setSizeDip(contentImage, Simple.pxToDip(imageWidth), Simple.pxToDip(imageHeight));

        Log.d(LOGTAG, "imageWidth=" + imageWidth + " imageHeight=" + imageHeight);

        addView(contentImage);

        int wid = Simple.isTablet() ? Simple.pxToDip(imageWidth / 2) : Simple.pxToDip(imageWidth * 3 / 4);
        int pad = Simple.isTablet() ? Defines.PADDING_XLARGE * 2 : Defines.PADDING_XLARGE;

        RelativeLayout infoCenter = new RelativeLayout(context);
        infoCenter.setGravity(Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(infoCenter, wid, Simple.MP);
        Simple.setPaddingDip(infoCenter, pad, 0, 0, 0);

        addView(infoCenter);

        infoContent = new LinearLayout(context);
        infoContent.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(infoContent, Simple.MP, Simple.WC);

        infoCenter.addView(infoContent);

        Typeface titleFont = Typeface.createFromAsset(getContext().getAssets(),Defines.FONT_ASSET_TITLE);
        Typeface summaryFont = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_ASSET_SUMMARY);
        Typeface buttonFont = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_DIALOG_BUTTON);

        infoTitle = new TextView(context);
        infoTitle.setSingleLine(true);
        infoTitle.setAllCaps(true);
        infoTitle.setEllipsize(TextUtils.TruncateAt.END);
        infoTitle.setTextColor(Color.WHITE);
        infoTitle.setTypeface(titleFont);

        Simple.setTextSizeDip(infoTitle, Defines.FS_BANNER_TITLE);

        infoContent.addView(infoTitle);

        infoSummary = new TextView(context);
        infoSummary.setAllCaps(true);
        infoSummary.setMinLines(1);
        infoSummary.setMaxLines(3);
        infoSummary.setEllipsize(TextUtils.TruncateAt.END);
        infoSummary.setTextColor(Color.WHITE);
        infoSummary.setTypeface(summaryFont);

        Simple.setTextSizeDip(infoSummary, Defines.FS_BANNER_INFO);
        Simple.setMarginTopDip(infoSummary, Defines.PADDING_TINY);

        infoContent.addView(infoSummary);

        TextView moreButton = new TextView(context);
        moreButton.setAllCaps(true);
        moreButton.setBackgroundColor(Color.BLACK);
        moreButton.setTextColor(Color.WHITE);
        moreButton.setTypeface(buttonFont);
        moreButton.setText(R.string.banner_more_button);

        Simple.setSizeDip(moreButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(moreButton, Defines.FS_BANNER_BUTTON);
        Simple.setPaddingDip(moreButton, Defines.PADDING_NORMAL, Defines.PADDING_TINY, Defines.PADDING_NORMAL, Defines.PADDING_TINY);
        Simple.setMarginTopDip(moreButton, Defines.PADDING_SMALL);

        infoContent.addView(moreButton);
    }

    public void setAsset(final JSONObject asset)
    {
        final boolean isCourse = Json.getBoolean(asset, "_isCourse");

        String contentTitle = Json.getString(asset, "title");
        String contentInfo = Json.getString(asset, "sub_title");
        String contentHeader = Json.getString(asset, "description_header");
        String contentDescription = Json.getString(asset, "description");

        String bannerUrl = Json.getString(asset, "banner_image_url");
        if (bannerUrl == null) bannerUrl = Json.getString(asset, "detail_image_url");

        infoTitle.setText(contentTitle);
        infoSummary.setText(contentInfo);

        contentImage.setImageDrawable(
                AssetsImageManager.getDrawableOrFetch(
                        getContext(),
                        contentImage, bannerUrl,
                        imageWidth, imageHeight, false));

        infoContent.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Globals.displayContent = asset;

                if (isCourse)
                {
                    Simple.startActivity(getContext(), CourseActivity.class);
                }
                else
                {
                    Simple.startActivity(getContext(), DetailActivity.class);
                }
            }
        });
    }
}
