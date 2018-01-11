package de.sensordigitalmediagermany.lemontrainer.raineralbers;

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
    protected boolean shouldDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //
        // Extend navigation frame to fill screen.
        //

        assetGrid.setVisibility(View.GONE);
        naviFrame.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(naviFrame, Simple.MP, Simple.MP, 1.0f);

        Simple.setPaddingDip(naviFrame,
                Defines.PADDING_XLARGE, Defines.PADDING_NORMAL,
                Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);

        setBackButton(true);

        if (Globals.displayContent == null) return;

        //
        // Derive data from JSON.
        //

        String contentTitle = Json.getString(Globals.displayContent, "title");
        String contentInfo = Json.getString(Globals.displayContent, "sub_title");
        String contentHeader = Json.getString(Globals.displayContent, "description_header");
        String contentDescription = Json.getString(Globals.displayContent, "description");
        String detailUrl = Json.getString(Globals.displayContent, "detail_image_url");
        int content_type = Json.getInt(Globals.displayContent, "content_type");

        //
        // Setup navigation path.
        //

        if (navigationButton != null)
        {
            String navipath = "";

            if (Globals.category != null)
            {
                navipath = Globals.category + " | ";
            }

            navipath += contentTitle;

            navigationButton.setButtonText(Defines.PADDING_TINY, navipath);
        }

        //region Image and type area.

        int imageWidth = topFrame.getLayoutParams().width;
        int imageHeight = Math.round(imageWidth / Defines.ASSET_DETAIL_ASPECT);

        Simple.setSizeDip(imageFrame, Simple.MP, Simple.pxToDip(imageHeight));
        Simple.setSizeDip(contentImage, Simple.MP, Simple.pxToDip(imageHeight));

        Log.d(LOGTAG, "onCreate: imageWidth=" + imageWidth + " imageHeight=" + imageHeight);

        contentImage.setImageDrawable(
                AssetsImageManager.getDrawableOrFetch(
                        this,
                        contentImage, detailUrl,
                        imageWidth, imageHeight, false));

        Simple.setSizeDip(typeIcon, Defines.TYPE_ICON_SIZE, Defines.TYPE_ICON_SIZE);

        int iconResid = 0;

        if (content_type == Defines.CONTENT_TYPE_PDF) iconResid = R.drawable.lem_t_iany_generic_type_pdf_gross;
        if (content_type == Defines.CONTENT_TYPE_VIDEO) iconResid = R.drawable.lem_t_iany_generic_type_film_gross;
        if (content_type == Defines.CONTENT_TYPE_ZIP) iconResid = R.drawable.lem_t_iany_generic_type_film_gross;

        if (iconResid > 0) typeIcon.setImageResource(iconResid);

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

        int file_duration = Json.getInt(Globals.displayContent, "file_duration");
        long file_size = Json.getLong(Globals.displayContent, "file_size");
        long mbytes = file_size / (1000 * 1024);

        String suitable_for = Json.getString(Globals.displayContent, "suitable_for");

        LinearLayout specsArea = new LinearLayout(this);
        specsArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(specsArea, Simple.MP, Simple.WC, 1f);
        Simple.setPaddingDip(specsArea, Defines.PADDING_NORMAL);
        Simple.setRoundedCorners(specsArea, Defines.CORNER_RADIUS_BIGBUT, Color.WHITE, true);

        miscArea.addView(specsArea);

        RelativeLayout separ;

        TableLikeLayout fileView = new TableLikeLayout(this);
        fileView.setLeftText(R.string.detail_specs_file);

        Log.d(LOGTAG, "content_type=" + content_type);

        int typeResid = R.string.detail_specs_type_unknown;

        if (content_type == Defines.CONTENT_TYPE_PDF) typeResid = R.string.detail_specs_type_pdf;
        if (content_type == Defines.CONTENT_TYPE_VIDEO) typeResid = R.string.detail_specs_type_video;
        if (content_type == Defines.CONTENT_TYPE_ZIP) typeResid = R.string.detail_specs_type_zip;

        fileView.setRightText(typeResid);

        specsArea.addView(fileView);

        separ = new RelativeLayout(this);
        separ.setBackgroundColor(Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 2);
        Simple.setMarginDip(separ, 0, Defines.PADDING_TINY, 0, Defines.PADDING_TINY);

        specsArea.addView(separ);

        TableLikeLayout quantView = new TableLikeLayout(this);
        quantView.setLeftText(R.string.detail_specs_quantity);
        quantView.setRightText("-");

        if (content_type == 1)
        {
            quantView.setRightText(Simple.getTrans(this,
                    R.string.detail_specs_quantity_pages,
                    String.valueOf(file_duration)));
        }

        if (content_type == 2)
        {
            int minutes = 1 + (file_duration / 60);

            quantView.setRightText(Simple.getTrans(this,
                    R.string.detail_specs_quantity_duration,
                    String.valueOf(minutes)));
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
                (mbytes < 1) ? "< 1" : Simple.formatDecimal(mbytes)));

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
        if (AssetsDownloadManager.connectDownload(Globals.displayContent, onDownloadProgressHandler))
        {
            downloadCenter.setVisibility(View.VISIBLE);
            downloadProgress.setProgress(0, 0);
        }

        int contentId = Json.getInt(Globals.displayContent, "id");
        int price = Json.getInt(Globals.displayContent, "price");
        boolean bought = ContentHandler.isContentBought(contentId);
        boolean gratis = (price == 0);

        String buyText = gratis
                ? Simple.getTrans(this, R.string.detail_buy_price, String.valueOf(price))
                : Simple.getTrans(this, R.string.detail_buy_gratis);

        if (bought || (gratis && Defines.isGiveAway))
        {
            buyText = Simple.getTrans(this, R.string.detail_buy_display);
        }

        buyButton.setText(buyText);

        if (bought || (gratis && Defines.isGiveAway))
        {
            if (ContentHandler.isCachedContent(Globals.displayContent))
            {
                typeIcon.setOnClickListener(startDisplay);
                buyButton.setOnClickListener(startDisplay);
            }
            else
            {
                downloadButton.setVisibility(View.VISIBLE);
                downloadButton.setOnClickListener(startDownload);

                typeIcon.setOnClickListener(startDownloadAndDisplay);
                buyButton.setOnClickListener(startDownloadAndDisplay);
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

    private final View.OnClickListener startDisplay = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Simple.startActivity(DetailActivity.this, ViewActivity.class);
        }
    };

    private final View.OnClickListener startDownload = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            shouldDisplay = false;

            Simple.setRoundedCorners(downloadButton, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_LTBLUE, true);

            downloadCenter.setVisibility(View.VISIBLE);
            downloadProgress.setProgress(0, 0);

            AssetsDownloadManager.getContentOrFetch(Globals.displayContent, onFileLoadedHandler, onDownloadProgressHandler);
        }
    };

    private final View.OnClickListener startDownloadAndDisplay = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            shouldDisplay = true;

            Simple.setRoundedCorners(downloadButton, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_LTBLUE, true);

            downloadCenter.setVisibility(View.VISIBLE);
            downloadProgress.setProgress(0, 0);

            AssetsDownloadManager.getContentOrFetch(Globals.displayContent, onFileLoadedHandler, onDownloadProgressHandler);
        }
    };

    private final AssetsDownloadManager.OnFileLoadedHandler onFileLoadedHandler
            = new AssetsDownloadManager.OnFileLoadedHandler()
    {
        @Override
        public void OnFileLoaded(JSONObject content, File file)
        {
            AppCompatActivity activity = ApplicationBase.getCurrentActivity(DetailActivity.this);

            int id_current = Json.getInt(Globals.displayContent, "id");
            int id_loaded = Json.getInt(content, "id");

            boolean doDisplay = (activity instanceof DetailActivity) && (id_current == id_loaded) && shouldDisplay;

            if (activity instanceof ContentBaseActivity)
            {
                ((ContentBaseActivity) activity).assetsAdapter.notifyDataSetChanged();
            }

            if (activity instanceof FullScreenActivity)
            {
                if ((file == null) || ! doDisplay)
                {
                    int titleRes = (file == null)
                            ? R.string.detail_download_failed
                            : R.string.detail_download_complete;

                    String text = Json.getString(content, "sub_title");

                    DialogView.errorAlert(((FullScreenActivity) activity).topFrame, titleRes, text);
                }
            }

            if (file == null)
            {
                Simple.setRoundedCorners(downloadButton, Defines.CORNER_RADIUS_BIGBUT, Color.RED, true);
            }
            else
            {
                downloadButton.setVisibility(View.GONE);

                buyButton.setText(Simple.getTrans(DetailActivity.this, R.string.detail_buy_display));

                typeIcon.setOnClickListener(startDisplay);
                buyButton.setOnClickListener(startDisplay);

                if (doDisplay) startDisplay.onClick(typeIcon);
            }

            downloadCenter.setVisibility(View.GONE);
        }
    };

    private final AssetsDownloadManager.OnDownloadProgressHandler onDownloadProgressHandler
            = new AssetsDownloadManager.OnDownloadProgressHandler()
    {
        @Override
        public void OnDownloadProgress(JSONObject content, long current, long total)
        {
            downloadProgressRunCurrent = current;
            downloadProgressRunTotal = total;

            ApplicationBase.handler.post(downloadProgressRun);
        }
    };

    private long downloadProgressRunCurrent;
    private long downloadProgressRunTotal;

    private final Runnable downloadProgressRun = new Runnable()
    {
        @Override
        public void run()
        {
            downloadProgress.setProgressLong(downloadProgressRunCurrent, downloadProgressRunTotal);
        }
    };
}
