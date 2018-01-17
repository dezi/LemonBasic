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
    protected ImageView statusIcon;
    protected TableLikeLayout statusView;
    protected DownloadDialog downloadDialog;
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
        naviFrame.setBackgroundColor(Defines.COLOR_CONTENT);
        Simple.setSizeDip(naviFrame, Simple.MP, Simple.MP, 1.0f);

        if (Defines.isCompactDetails)
        {
            Simple.setPaddingDip(naviFrame, Defines.PADDING_LARGE, 0, 0, 0);
        }
        else
        {
            Simple.setPaddingDip(naviFrame,
                    Defines.PADDING_XLARGE, Defines.PADDING_NORMAL,
                    Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);
        }

        setBackButton(true);

        if (Globals.displayContent == null) return;

        Typeface headerTF = Typeface.createFromAsset(getAssets(), Defines.FONT_DETAILS_HEADER);
        Typeface subheadTF = Typeface.createFromAsset(getAssets(), Defines.FONT_DETAILS_SUBHEAD);
        Typeface titleTF = Typeface.createFromAsset(getAssets(), Defines.FONT_DETAILS_TITLE);
        Typeface infosTF = Typeface.createFromAsset(getAssets(), Defines.FONT_DETAILS_INFOS);
        Typeface buttonsTF = Typeface.createFromAsset(getAssets(), Defines.FONT_DIALOG_BUTTON);

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

        showNavigationPath(contentTitle, contentInfo);

        //region Image and type area.

        int imageWidth = topFrame.getLayoutParams().width;
        int imageHeight = Math.round(imageWidth / Defines.ASSET_DETAIL_ASPECT);

        Simple.setSizeDip(imageFrame, Simple.MP, Simple.pxToDip(imageHeight));
        Simple.setSizeDip(contentImage, Simple.MP, Simple.pxToDip(imageHeight));

        if (Defines.isCompactDetails)
        {
            Simple.setPaddingDip(imageFrame,
                    Defines.PADDING_LARGE, Defines.PADDING_TINY,
                    Defines.PADDING_LARGE, Defines.PADDING_LARGE);
        }

        Log.d(LOGTAG, "onCreate: imageWidth=" + imageWidth + " imageHeight=" + imageHeight);

        contentImage.setImageDrawable(
                AssetsImageManager.getDrawableOrFetch(
                        this,
                        contentImage, detailUrl,
                        imageWidth, imageHeight, false));

        Simple.setSizeDip(typeIcon, Defines.TYPE_ICON_SIZE, Defines.TYPE_ICON_SIZE);

        int iconResid = 0;

        if (content_type == Defines.CONTENT_TYPE_PDF) iconResid = R.drawable.lem_t_iany_generic_type_pdf_gross;
        if (content_type == Defines.CONTENT_TYPE_ZIP) iconResid = R.drawable.lem_t_iany_generic_type_html5_gross;
        if (content_type == Defines.CONTENT_TYPE_VIDEO) iconResid = R.drawable.lem_t_iany_generic_type_video_gross;
        if (content_type == Defines.CONTENT_TYPE_AUDIO) iconResid = R.drawable.lem_t_iany_generic_type_audio_gross;

        if (iconResid > 0) typeIcon.setImageResource(iconResid);

        //endregion Image and type area.

        //region Header and title area.

        TextView ctView = new TextView(this);
        ctView.setText(contentTitle);
        ctView.setAllCaps(true);
        ctView.setTextColor(Defines.COLOR_DETAIL_TITLE);
        ctView.setTypeface(headerTF);
        ctView.setAllCaps(Defines.isInfosAllCaps);
        Simple.setTextSizeDip(ctView, Defines.FS_DETAIL_HEADER);
        Simple.setSizeDip(ctView, Simple.MP, Simple.WC);

        naviFrame.addView(ctView);

        TextView ciView = new TextView(this);
        ciView.setText(contentInfo);
        ciView.setTextColor(Color.BLACK);
        ciView.setTypeface(subheadTF);
        ciView.setAllCaps(Defines.isInfosAllCaps);
        Simple.setTextSizeDip(ciView, Defines.FS_DETAIL_SUBHEAD);
        Simple.setSizeDip(ciView, Simple.MP, Simple.WC);

        naviFrame.addView(ciView);

        //endregion Header and title area.

        //region Information area.

        LinearLayout infoArea = new LinearLayout(this);
        infoArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(infoArea, Simple.MP, Simple.MP);

        if (! Defines.isCompactDetails)
        {
            Simple.setMarginTopDip(infoArea, Defines.PADDING_XLARGE);
        }

        Simple.setMarginRightDip(infoArea, Defines.PADDING_LARGE);
        Simple.setMarginBottomDip(infoArea, Defines.PADDING_LARGE);

        naviFrame.addView(infoArea);

        //region Detail description area.

        ScrollView descScroll = new ScrollView(this);
        Simple.setSizeDip(descScroll, Simple.WC, Simple.MP, 1f);
        Simple.setPaddingDip(descScroll, Defines.PADDING_NORMAL);
        Simple.setRoundedCorners(descScroll, Defines.CORNER_RADIUS_FRAMES, Defines.COLOR_FRAMES, true);

        infoArea.addView(descScroll);

        LinearLayout descFrame = new LinearLayout(this);
        descFrame.setOrientation(LinearLayout.VERTICAL);

        descScroll.addView(descFrame);

        if (Defines.isCompactDetails)
        {
            naviFrame.removeView(ctView);
            naviFrame.removeView(ciView);

            descFrame.addView(ctView);
            descFrame.addView(ciView);
        }

        TextView chView = new TextView(this);
        chView.setText(contentHeader);
        chView.setTextColor(Color.BLACK);
        chView.setTypeface(titleTF);
        chView.setAllCaps(Defines.isInfosAllCaps);
        Simple.setTextSizeDip(chView, Defines.FS_DETAIL_TITLE);
        Simple.setSizeDip(chView, Simple.MP, Simple.WC);

        descFrame.addView(chView);

        TextView cdView = new TextView(this);
        cdView.setText(contentDescription);
        cdView.setTextColor(Color.BLACK);
        cdView.setMinLines(2);
        cdView.setTypeface(infosTF);
        cdView.setAllCaps(Defines.isInfosAllCaps);
        Simple.setTextSizeDip(cdView, Defines.FS_DETAIL_INFOS);
        Simple.setSizeDip(cdView, Simple.WC, Simple.WC);

        descFrame.addView(cdView);

        if (Defines.isCompactDetails)
        {
            Simple.setMarginTopDip(ctView, Defines.PADDING_NORMAL);
            Simple.setMarginTopDip(chView, Defines.PADDING_LARGE);
        }
        else
        {
            Simple.setMarginTopDip(ctView, Defines.PADDING_SMALL);
            Simple.setMarginTopDip(ciView, Defines.PADDING_TINY);
            Simple.setMarginTopDip(cdView, Defines.PADDING_NORMAL);
        }

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
        Simple.setRoundedCorners(specsArea, Defines.CORNER_RADIUS_FRAMES, Defines.COLOR_FRAMES, true);

        if (Defines.isCompactDetails)
        {
            Simple.setPaddingDip(specsArea, Defines.PADDING_LARGE);
        }
        else
        {
            Simple.setPaddingDip(specsArea, Defines.PADDING_NORMAL);
        }

        miscArea.addView(specsArea);

        TableLikeLayout fileView = new TableLikeLayout(this, headerTF, infosTF, true);
        fileView.setLeftText(R.string.detail_specs_file);

        Log.d(LOGTAG, "content_type=" + content_type);

        int typeResid = R.string.detail_specs_type_unknown;

        if (content_type == Defines.CONTENT_TYPE_PDF) typeResid = R.string.detail_specs_type_pdf;
        if (content_type == Defines.CONTENT_TYPE_ZIP) typeResid = R.string.detail_specs_type_zip;
        if (content_type == Defines.CONTENT_TYPE_AUDIO) typeResid = R.string.detail_specs_type_audio;
        if (content_type == Defines.CONTENT_TYPE_VIDEO) typeResid = R.string.detail_specs_type_video;

        fileView.setRightText(typeResid);

        specsArea.addView(fileView);

        specsArea.addView(createSeparator());

        TableLikeLayout quantView = new TableLikeLayout(this, headerTF, infosTF, true);
        quantView.setLeftText(R.string.detail_specs_quantity);
        quantView.setRightText("-");

        if (content_type == Defines.CONTENT_TYPE_PDF)
        {
            quantView.setRightText(Simple.getTrans(this,
                    R.string.detail_specs_quantity_pages,
                    String.valueOf(file_duration)));
        }

        if ((content_type == Defines.CONTENT_TYPE_AUDIO) || (content_type == Defines.CONTENT_TYPE_VIDEO))
        {
            int minutes = 1 + (file_duration / 60);

            quantView.setRightText(Simple.getTrans(this,
                    R.string.detail_specs_quantity_duration,
                    String.valueOf(minutes)));
        }

        specsArea.addView(quantView);

        specsArea.addView(createSeparator());

        if (Defines.isCompactDetails)
        {
            boolean isCached = ContentHandler.isCachedContent(Globals.displayContent);

            LinearLayout statusBox = new LinearLayout(this);
            statusBox.setOrientation(LinearLayout.HORIZONTAL);
            Simple.setSizeDip(statusBox, Simple.MP, Simple.WC);

            specsArea.addView(statusBox);

            statusIcon = new ImageView(this);
            statusIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Simple.setSizeDip(statusIcon, Defines.STATUS_ICON_SIZE, Defines.STATUS_ICON_SIZE);
            Simple.setPaddingDip(statusIcon, Defines.PADDING_TINY);
            Simple.setMarginRightDip(statusIcon, Defines.PADDING_SMALL);

            statusIcon.setImageResource(isCached
                    ? R.drawable.lem_t_iany_generic_content_online
                    : R.drawable.lem_t_iany_generic_content_offline
            );

            statusBox.addView(statusIcon);

            statusView = new TableLikeLayout(this, headerTF, infosTF, true);

            statusView.setLeftText(R.string.detail_specs_status);

            statusView.setRightText(isCached
                    ? R.string.detail_specs_status_online
                    : R.string.detail_specs_status_offline
            );

            statusBox.addView(statusView);
        }
        else
        {
            TableLikeLayout sizeView = new TableLikeLayout(this, headerTF, infosTF, true);
            sizeView.setLeftText(R.string.detail_specs_size);

            sizeView.setRightText(Simple.getTrans(this,
                    R.string.detail_specs_size_mb,
                    (mbytes < 1) ? "< 1" : Simple.formatDecimal(mbytes)));

            specsArea.addView(sizeView);

            specsArea.addView(createSeparator());

            TextView suitableView = new TextView(this);
            suitableView.setText(suitable_for);
            suitableView.setMinLines(2);
            suitableView.setEllipsize(TextUtils.TruncateAt.END);
            suitableView.setTextColor(Color.BLACK);
            suitableView.setTypeface(infosTF);
            Simple.setTextSizeDip(suitableView, Defines.FS_DETAIL_SPECS);

            specsArea.addView(suitableView);
        }

        //endregion Technical specs area.

        //region Download and buy area.

        LinearLayout buyloadArea = new LinearLayout(this);
        buyloadArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(buyloadArea, Simple.WC, Simple.WC, 0f);
        Simple.setMarginTopDip(buyloadArea, Defines.PADDING_LARGE);
        Simple.setRoundedCorners(buyloadArea, Defines.CORNER_RADIUS_FRAMES, Defines.COLOR_FRAMES, true);

        if (Defines.isCompactDetails)
        {
            Simple.setPaddingDip(buyloadArea, Defines.PADDING_LARGE);
        }

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

        if (Defines.isCompactDetails)
        {
            downloadButton.setVisibility(View.GONE);
        }

        buyButton = new TextView(this);
        buyButton.setTextColor(Color.WHITE);
        buyButton.setTypeface(buttonsTF);
        buyButton.setAllCaps(Defines.isButtonAllCaps);
        Simple.setSizeDip(buyButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(buyButton, Defines.FS_DIALOG_BUTTON);
        Simple.setRoundedCorners(buyButton, Defines.CORNER_RADIUS_BUTTON, Color.BLACK, true);

        Simple.setPaddingDip(buyButton,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL);

        buyloadArea.addView(buyButton);

        //endregion Download and buy area.

        //endregion Misc area with specs and buy.

        //endregion Information area.

        updateContent();
    }

    private RelativeLayout createSeparator()
    {
        RelativeLayout separ = new RelativeLayout(this);
        separ.setBackgroundColor(Defines.isCompactSettings ? Color.BLACK : Color.LTGRAY);
        Simple.setSizeDip(separ, Simple.MP, 1);
        Simple.setMarginDip(separ, 0, Defines.PADDING_SMALL, 0, Defines.PADDING_SMALL);

        return separ;
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
                if (! Defines.isCompactDetails)
                {
                    downloadButton.setVisibility(View.VISIBLE);
                    downloadButton.setOnClickListener(startDownload);
                }

                typeIcon.setOnClickListener(askAndDownload);
                buyButton.setOnClickListener(askAndDownload);
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

            downloadDialog = new DownloadDialog(DetailActivity.this);
            topFrame.addView(downloadDialog);

            AssetsDownloadManager.getContentOrFetch(Globals.displayContent, onFileLoadedHandler, onDownloadProgressHandler);
        }
    };

    private final View.OnClickListener askAndDownload = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            DialogView askdialog = new DialogView(DetailActivity.this);

            askdialog.setCloseButton(true, null);
            askdialog.setTitleText(R.string.ask_download_title);
            askdialog.setInfoText(R.string.ask_download_info);

            askdialog.setNegativeButton(R.string.ask_download_load_and_view, startDownloadAndDisplay);
            askdialog.negativeButton.setSingleLine(false);
            askdialog.negativeButton.setAllCaps(Defines.isButtonAllCaps);

            Simple.setPaddingDip(askdialog.negativeButton,
                    0, Defines.PADDING_LARGE,
                    0, Defines.PADDING_LARGE);

            askdialog.setPositiveButton(R.string.ask_download_only_load, startDownload);
            askdialog.positiveButton.setSingleLine(false);
            askdialog.positiveButton.setAllCaps(Defines.isButtonAllCaps);

            Simple.setPaddingDip(askdialog.positiveButton,
                    0, Defines.PADDING_LARGE,
                    0, Defines.PADDING_LARGE);

            topFrame.addView(askdialog);
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

            downloadDialog = new DownloadDialog(DetailActivity.this);
            topFrame.addView(downloadDialog);

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

            if (downloadDialog != null)
            {
                downloadDialog.dismissDialog();
            }

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

                if ((statusIcon != null) && (statusView != null))
                {
                    statusIcon.setImageResource(R.drawable.lem_t_iany_generic_content_online);
                    statusView.setRightText(R.string.detail_specs_status_online);
                }
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
            if (downloadDialog != null)
            {
                downloadDialog.setProgressLong(downloadProgressRunCurrent, downloadProgressRunTotal);
            }

            downloadProgress.setProgressLong(downloadProgressRunCurrent, downloadProgressRunTotal);
        }
    };
}
