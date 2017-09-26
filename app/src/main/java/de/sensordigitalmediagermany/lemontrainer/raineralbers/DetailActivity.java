package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.text.TextUtils;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends ContentBaseActivity
{
    private static final String LOGTAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //
        // Extend navigation frame to fill screen.
        //

        naviFrame.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(naviFrame, Simple.MP, Simple.MP);

        Simple.setPaddingDip(naviFrame,
                Defines.PADDING_XLARGE, Defines.PADDING_NORMAL,
                Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);

        if (Globals.displayContent == null) return;

        //
        // Derive data from JSON.
        //

        String courseTitle = Json.getString(Globals.displayContent, "title");
        String courseInfo = Json.getString(Globals.displayContent, "sub_title");
        String courseHeader = Json.getString(Globals.displayContent, "description_header");
        String courseDescription = Json.getString(Globals.displayContent, "description");
        String detailUrl = Json.getString(Globals.displayContent, "detail_image_url");

        int price = Json.getInt(Globals.displayContent, "price");

        //region Image and type area.

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

        Simple.setSizeDip(typeIcon, Defines.TYPE_ICON_SIZE, Defines.TYPE_ICON_SIZE);
        typeIcon.setImageResource(R.drawable.lem_t_iany_ralbers_type_video);

        //endregion Image and type area.

        //region Header and title area.

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

        //endregion Header and title area.

        //region Information area.

        LinearLayout infoArea = new LinearLayout(this);
        infoArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(infoArea, Simple.MP, Simple.MP);

        Simple.setMarginTopDip(infoArea,Defines.PADDING_LARGE);
        Simple.setMarginRightDip(infoArea,Defines.PADDING_LARGE);
        Simple.setMarginBottomDip(infoArea,Defines.PADDING_LARGE);

        naviFrame.addView(infoArea);

        //region Detail description area.

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

        //endregion Detail description area.

        //region Misc area with specs and buy.

        LinearLayout miscArea = new LinearLayout(this);
        miscArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(miscArea, Simple.WC, Simple.MP);
        Simple.setMarginLeftDip(miscArea, Defines.PADDING_NORMAL);

        infoArea.addView(miscArea);

        //region Technical specs area.

        int content_type = Json.getInt(Globals.displayContent, "content_type");
        int file_size = Json.getInt(Globals.displayContent, "file_size");
        int file_duration = Json.getInt(Globals.displayContent, "file_duration");

        String suitable_for = Json.getString(Globals.displayContent, "suitable_for");

        if (Defines.isDezi && (suitable_for == null))
        {
            suitable_for = Simple.getLoreIpsum();
        }

        LinearLayout specsArea = new LinearLayout(this);
        specsArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(specsArea, Simple.MP, Simple.WC, 1f);
        Simple.setPaddingDip(specsArea, Defines.PADDING_NORMAL);
        Simple.setRoundedCorners(specsArea, Defines.CORNER_RADIUS_BIGBUT, Color.WHITE, true);

        miscArea.addView(specsArea);

        RelativeLayout separ;

        TableLikeLayout fileView = new TableLikeLayout(this);
        fileView.setLeftText(R.string.detail_specs_file);

        fileView.setRightText(content_type == 2
                ? R.string.detail_specs_type_video
                : R.string.detail_specs_type_unknown);

        specsArea.addView(fileView);

        separ = new RelativeLayout(this);
        separ.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 2);
        Simple.setMarginDip(separ, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);

        specsArea.addView(separ);

        TableLikeLayout quantView = new TableLikeLayout(this);
        quantView.setLeftText(R.string.detail_specs_quantity);

        if (content_type == 2)
        {
            int minutes = 1 + (file_duration / 60);

            quantView.setRightText(Simple.getTrans(this,
                    R.string.detail_specs_quantity_duration,
                    String.valueOf(minutes)));
        }
        else
        {
            quantView.setRightText("-");
        }

        specsArea.addView(quantView);

        separ = new RelativeLayout(this);
        separ.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 2);
        Simple.setMarginDip(separ, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);

        specsArea.addView(separ);

        TableLikeLayout sizeView = new TableLikeLayout(this);
        sizeView.setLeftText(R.string.detail_specs_size);

        int mbytes = file_size / (1000 * 1024);

        sizeView.setRightText(Simple.getTrans(this,
                R.string.detail_specs_size_mb,
                String.valueOf(mbytes)));

        specsArea.addView(sizeView);

        separ = new RelativeLayout(this);
        separ.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 2);
        Simple.setMarginDip(separ, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);

        specsArea.addView(separ);

        TextView suitableView = new TextView(this);
        suitableView.setText(suitable_for);
        suitableView.setMinLines(2);
        suitableView.setEllipsize(TextUtils.TruncateAt.END);
        suitableView.setTextColor(Color.BLACK);
        suitableView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(suitableView, Defines.FS_DETAIL_SPECS);

        specsArea.addView(suitableView);

        //endregion Technical specs area.

        //region Download and buy area.

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
                ? Simple.getTrans(this, R.string.detail_buy_price, String.valueOf(price))
                : Simple.getTrans(this, R.string.detail_buy_gratis);

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

        buyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                topFrame.addView(new BuyContentDialog(DetailActivity.this));
            }
        });

        buyloadArea.addView(buyButton);

        //endregion Download and buy area.

        //endregion Misc area with specs and buy.

        //endregion Information area.
    }
}
