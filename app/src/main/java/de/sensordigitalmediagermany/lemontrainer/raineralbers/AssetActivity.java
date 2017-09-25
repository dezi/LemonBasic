package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.os.Bundle;
import android.util.Log;

public class AssetActivity extends ContentBaseActivity
{
    private static final String LOGTAG = AssetActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.d(LOGTAG, "onCreate: content=" + Json.toPretty(Globals.displayContent));

        naviFrame.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(naviFrame, Simple.MP, Simple.MP);

        Simple.setPaddingDip(naviFrame,
                Defines.PADDING_XLARGE, Defines.PADDING_NORMAL,
                Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);

        if (Globals.displayContent == null) return;

        String courseTitle = Json.getString(Globals.displayContent, "title");
        String courseInfo = Json.getString(Globals.displayContent, "sub_title");
        String courseHeader = Json.getString(Globals.displayContent, "description_header");
        String courseDescription = Json.getString(Globals.displayContent, "description");
        String detailUrl = Json.getString(Globals.displayContent, "detail_image_url");

        int price = Json.getInt(Globals.displayContent, "price");

        int imageWidth = topFrame.getLayoutParams().width;
        int imageHeight = Math.round(imageWidth / Defines.FS_ASSET_DETAIL_ASPECT);

        Simple.setSizeDip(imageFrame, Simple.MP, Simple.pxToDip(imageHeight));
        Simple.setSizeDip(contentImage, Simple.MP, Simple.pxToDip(imageHeight));

        Log.d(LOGTAG, "onCreate: imageWidth=" + imageWidth + " imageHeight=" + imageHeight);

        contentImage.setImageDrawable(
                AssetsImageManager.getDrawableOrFetch(
                        this,
                        contentImage, detailUrl,
                        imageWidth, imageHeight, false));

        TextView ctView = new TextView(this);
        ctView.setText(courseTitle);
        ctView.setAllCaps(true);
        ctView.setTextColor(Defines.COLOR_SENSOR_LTBLUE);
        ctView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setTextSizeDip(ctView, Defines.FS_COURSE_TITLE);
        Simple.setSizeDip(ctView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(ctView, Defines.PADDING_SMALL);

        naviFrame.addView(ctView);

        TextView ciView = new TextView(this);
        ciView.setText(courseInfo);
        ciView.setTextColor(Color.BLACK);
        ciView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.ROONEY_REGULAR));
        Simple.setTextSizeDip(ciView, Defines.FS_COURSE_HEADER);
        Simple.setSizeDip(ciView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(ciView, Defines.PADDING_TINY);

        naviFrame.addView(ciView);

        LinearLayout infoArea = new LinearLayout(this);
        infoArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(infoArea, Simple.MP, Simple.MP);

        Simple.setMarginTopDip(infoArea,Defines.PADDING_LARGE);
        Simple.setMarginRightDip(infoArea,Defines.PADDING_LARGE);
        Simple.setMarginBottomDip(infoArea,Defines.PADDING_LARGE);

        naviFrame.addView(infoArea);

        ScrollView descScroll = new ScrollView(this);
        Simple.setSizeDip(descScroll, Simple.WC, Simple.MP, 1f);
        Simple.setPaddingDip(descScroll, Defines.PADDING_NORMAL);
        Simple.setRoundedCorners(descScroll, Defines.CORNER_RADIUS_DIALOG, Color.WHITE, true);

        infoArea.addView(descScroll);

        LinearLayout descFrame = new LinearLayout(this);
        descFrame.setOrientation(LinearLayout.VERTICAL);

        descScroll.addView(descFrame);

        TextView chView = new TextView(this);
        chView.setText(courseHeader);
        chView.setTextColor(Color.BLACK);
        chView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.ROONEY_REGULAR));
        Simple.setTextSizeDip(chView, Defines.FS_COURSE_HEADER);
        Simple.setSizeDip(chView, Simple.MP, Simple.WC);

        descFrame.addView(chView);

        if (Defines.isDezi) courseDescription += " " + Simple.getLoreIpsum();

        TextView cdView = new TextView(this);
        cdView.setText(courseDescription);
        cdView.setTextColor(Color.BLACK);
        cdView.setMinLines(2);
        cdView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.ROONEY_LIGHT));
        Simple.setTextSizeDip(cdView, Defines.FS_COURSE_DESC);
        Simple.setSizeDip(cdView, Simple.WC, Simple.WC);
        Simple.setMarginTopDip(cdView, Defines.PADDING_NORMAL);

        descFrame.addView(cdView);

        LinearLayout miscArea = new LinearLayout(this);
        miscArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(miscArea, Simple.WC, Simple.MP);
        Simple.setMarginLeftDip(miscArea, Defines.PADDING_NORMAL);

        infoArea.addView(miscArea);

        LinearLayout specsArea = new LinearLayout(this);
        specsArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(specsArea, Simple.MP, Simple.WC, 1f);
        Simple.setRoundedCorners(specsArea, Defines.CORNER_RADIUS_BIGBUT, Color.WHITE, true);

        miscArea.addView(specsArea);

        LinearLayout buyloadArea = new LinearLayout(this);
        buyloadArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(buyloadArea, Simple.WC, Simple.WC, 0f);
        Simple.setMarginTopDip(buyloadArea, Defines.PADDING_LARGE);

        miscArea.addView(buyloadArea);

        ImageView downloadImage = new ImageView(this);
        downloadImage.setImageResource(R.drawable.lem_t_iany_ralbers_cloud_download);
        downloadImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Simple.setSizeDip(downloadImage, Defines.CLOUD_ICON_SIZE, Simple.MP);
        Simple.setPaddingDip(downloadImage, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(downloadImage, Defines.CORNER_RADIUS_BIGBUT, Color.WHITE, true);

        buyloadArea.addView(downloadImage);

        String buyText = (price > 0)
                ? Simple.getTrans(this, R.string.content_buy_price, String.valueOf(price))
                : Simple.getTrans(this, R.string.content_buy_gratis);

        TextView buyButton = new TextView(this);
        buyButton.setText(buyText);
        buyButton.setTextColor(Color.WHITE);
        buyButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(buyButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(buyButton, Defines.FS_DIALOG_BUTTON);
        Simple.setRoundedCorners(buyButton, Defines.CORNER_RADIUS_BIGBUT, Color.BLACK, true);
        Simple.setMarginLeftDip(buyButton, Defines.PADDING_LARGE);

        Simple.setPaddingDip(buyButton,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL);

        buyloadArea.addView(buyButton);
    }
}
