package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.text.TextUtils;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;

public class DetailActivity extends ContentBaseActivity
{
    private static final String LOGTAG = DetailActivity.class.getSimpleName();

    protected TextView buyButton;
    protected ImageView downloadButton;

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

        String contentTitle = Json.getString(Globals.displayContent, "title");
        String contentInfo = Json.getString(Globals.displayContent, "sub_title");
        String contentHeader = Json.getString(Globals.displayContent, "description_header");
        String contentDescription = Json.getString(Globals.displayContent, "description");
        String detailUrl = Json.getString(Globals.displayContent, "detail_image_url");

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
        typeIcon.setImageResource(R.drawable.lem_t_iany_genric_type_film_gross);

        //endregion Image and type area.

        //region Header and title area.

        TextView ctView = new TextView(this);
        ctView.setText(contentTitle);
        ctView.setAllCaps(true);
        ctView.setTextColor(Defines.COLOR_SENSOR_LTBLUE);
        ctView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setTextSizeDip(ctView, Defines.FS_COURSE_TITLE);
        Simple.setSizeDip(ctView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(ctView, Defines.PADDING_SMALL);

        naviFrame.addView(ctView);

        TextView ciView = new TextView(this);
        ciView.setText(contentInfo);
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

        Simple.setMarginTopDip(infoArea, Defines.PADDING_LARGE);
        Simple.setMarginRightDip(infoArea, Defines.PADDING_LARGE);
        Simple.setMarginBottomDip(infoArea, Defines.PADDING_LARGE);

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
        chView.setText(contentHeader);
        chView.setTextColor(Color.BLACK);
        chView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.ROONEY_REGULAR));
        Simple.setTextSizeDip(chView, Defines.FS_COURSE_HEADER);
        Simple.setSizeDip(chView, Simple.MP, Simple.WC);

        descFrame.addView(chView);

        if (Defines.isDezi) contentDescription += " " + Simple.getLoreIpsum();

        TextView cdView = new TextView(this);
        cdView.setText(contentDescription);
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
        int file_duration = Json.getInt(Globals.displayContent, "file_duration");
        long file_size = Json.getLong(Globals.displayContent, "file_size");
        long mbytes = file_size / (1000 * 1024);

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

        sizeView.setRightText(Simple.getTrans(this,
                R.string.detail_specs_size_mb,
                Simple.formatDecimal(mbytes)));

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

        downloadButton = new ImageView(this);
        downloadButton.setImageResource(R.drawable.lem_t_iany_ralbers_cloud_download);
        downloadButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        downloadButton.setVisibility(View.GONE);
        Simple.setSizeDip(downloadButton, Defines.CLOUD_ICON_SIZE, Simple.MP);
        Simple.setPaddingDip(downloadButton, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(downloadButton, Defines.CORNER_RADIUS_BIGBUT, Color.WHITE, true);
        Simple.setMarginRightDip(downloadButton, Defines.PADDING_LARGE);

        buyloadArea.addView(downloadButton);

        buyButton = new TextView(this);
        buyButton.setTextColor(Color.WHITE);
        buyButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(buyButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(buyButton, Defines.FS_DIALOG_BUTTON);
        Simple.setRoundedCorners(buyButton, Defines.CORNER_RADIUS_BIGBUT, Color.BLACK, true);

        Simple.setPaddingDip(buyButton,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL);

        buyloadArea.addView(buyButton);

        //endregion Download and buy area.

        //endregion Misc area with specs and buy.

        //endregion Information area.

        updateContent();
    }

    public void updateContent()
    {
        int contentId = Json.getInt(Globals.displayContent, "id");
        int price = Json.getInt(Globals.displayContent, "price");
        boolean bought = ContentHandler.isContentBought(contentId);

        String buyText = (price > 0)
                ? Simple.getTrans(this, R.string.detail_buy_price, String.valueOf(price))
                : Simple.getTrans(this, R.string.detail_buy_gratis);

        if (bought)
        {
            buyText = ContentHandler.isCachedFile(Globals.displayContent)
                    ? Simple.getTrans(this, R.string.detail_buy_loaded)
                    : Simple.getTrans(this, R.string.detail_buy_bought);
        }

        buyButton.setText(buyText);

        if (bought)
        {
            buyButton.setOnClickListener(null);

            if (! ContentHandler.isCachedFile(Globals.displayContent))
            {
                downloadButton.setVisibility(View.VISIBLE);

                downloadButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(final View view)
                    {
                        downloadContent(DetailActivity.this);
                    }
                });
            }
        }
        else
        {
            buyButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    topFrame.addView(new BuyContentDialog(DetailActivity.this));
                }
            });
        }
    }

    private void downloadContent(final Context context)
    {
        Simple.setRoundedCorners(downloadButton, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_LTBLUE, true);

        AssetsDownloadManager.getContentOrFetch(Globals.displayContent,
                new AssetsDownloadManager.OnFileLoadedHandler()
                {
                    public void OnFileLoaded(JSONObject content, File file)
                    {
                        AppCompatActivity activity = ApplicationBase.getCurrentActivity(context);

                        if (activity instanceof FullScreenActivity)
                        {
                            int titleRes = (file == null)
                                    ? R.string.detail_download_failed
                                    : R.string.detail_download_complete;

                            String text = Json.getString(content, "sub_title");

                            DialogView.errorAlert(((FullScreenActivity) activity).topFrame, titleRes, text);
                        }

                        if (file == null)
                        {
                            Simple.setRoundedCorners(downloadButton, Defines.CORNER_RADIUS_BIGBUT, Color.RED, true);
                        }
                        else
                        {
                            downloadButton.setVisibility(View.GONE);
                            buyButton.setText(Simple.getTrans(DetailActivity.this, R.string.detail_buy_loaded));
                        }
                    }
                });

    }
}
