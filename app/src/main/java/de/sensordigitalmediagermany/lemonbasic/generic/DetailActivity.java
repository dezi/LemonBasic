package de.sensordigitalmediagermany.lemonbasic.generic;

import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.text.TextUtils;
import android.os.Bundle;
import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;

import static android.view.View.GONE;

public class DetailActivity extends ContentBaseActivity
{
    private static final String LOGTAG = DetailActivity.class.getSimpleName();

    protected GenericButton buyButton;
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

        assetGrid.setVisibility(GONE);
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
            if (Simple.isWideScreen())
            {
                Simple.setPaddingDip(imageFrame,
                        Defines.PADDING_ZERO, Defines.PADDING_ZERO,
                        Defines.PADDING_ZERO, Defines.PADDING_SMALL);
            }
            else
            {
                Simple.setPaddingDip(imageFrame,
                        Defines.PADDING_LARGE, Defines.PADDING_TINY,
                        Defines.PADDING_LARGE, Defines.PADDING_LARGE);
            }
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

        LinearLayout infoAreaVert = new LinearLayout(this);
        infoAreaVert.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(infoAreaVert, Simple.MP, Simple.MP);

        if (! Defines.isCompactDetails)
        {
            Simple.setMarginTopDip(infoAreaVert, Defines.PADDING_XLARGE);
        }

        Simple.setMarginRightDip(infoAreaVert, Defines.PADDING_LARGE);
        Simple.setMarginBottomDip(infoAreaVert, Defines.PADDING_LARGE);

        naviFrame.addView(infoAreaVert);

        LinearLayout infoAreaHorz = new LinearLayout(this);
        infoAreaHorz.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(infoAreaHorz, Simple.MP, Simple.MP);

        infoAreaVert.addView(infoAreaHorz);

        //region Detail description area.

        LinearLayout descFrame = new LinearLayout(this);
        descFrame.setOrientation(LinearLayout.VERTICAL);

        if (Simple.isTablet())
        {
            Simple.setSizeDip(descFrame, Simple.MP, Simple.MP, 0.3f);
            infoAreaHorz.addView(descFrame);
        }
        else
        {
            Simple.setSizeDip(descFrame, Simple.MP, Simple.WC);
            infoAreaVert.addView(descFrame, 0);
        }

        GenericScrollVert descScroll = new GenericScrollVert(this);
        descScroll.setSizeDependendFocusable(true);
        descScroll.setBackgroundColor(Defines.COLOR_FRAMES);
        Simple.setPaddingDip(descScroll, Defines.PADDING_NORMAL);
        Simple.setRoundedCorners(descScroll, Defines.CORNER_RADIUS_FRAMES, Defines.COLOR_FRAMES, true);

        descFrame.addView(descScroll);

        LinearLayout descScrollContent = new LinearLayout(this);
        descScrollContent.setOrientation(LinearLayout.VERTICAL);

        descScroll.addView(descScrollContent);

        if (Defines.isCompactDetails)
        {
            if (Simple.isWideScreen())
            {
                contentFrame.removeView(imageFrame);
                descFrame.addView(imageFrame, 0);
                Simple.setSizeDip(descScroll, Simple.MP, Simple.MP);
            }

            naviFrame.removeView(ctView);
            naviFrame.removeView(ciView);

            descScrollContent.addView(ctView);
            descScrollContent.addView(ciView);
        }

        TextView chView = new TextView(this);
        chView.setText(contentHeader);
        chView.setTextColor(Color.BLACK);
        chView.setTypeface(titleTF);
        chView.setAllCaps(Defines.isInfosAllCaps);
        Simple.setTextSizeDip(chView, Defines.FS_DETAIL_TITLE);
        Simple.setSizeDip(chView, Simple.MP, Simple.WC);

        if (! Simple.isEmpty(contentHeader)) descScrollContent.addView(chView);

        TextView cdView = new TextView(this);
        cdView.setText(contentDescription);
        cdView.setTextColor(Color.BLACK);
        cdView.setMinLines(2);
        cdView.setTypeface(infosTF);
        cdView.setAllCaps(Defines.isInfosAllCaps);
        Simple.setTextSizeDip(cdView, Defines.FS_DETAIL_INFOS);
        Simple.setSizeDip(cdView, Simple.WC, Simple.WC);

        if (! Simple.isEmpty(contentDescription)) descScrollContent.addView(cdView);

        if (Defines.isCompactDetails)
        {
            if (! Simple.isWideScreen())
            {
                Simple.setMarginTopDip(ctView, Defines.PADDING_NORMAL);
                Simple.setMarginTopDip(chView, Defines.PADDING_LARGE);
            }
        }
        else
        {
            Simple.setMarginTopDip(ctView, Defines.PADDING_SMALL);
            Simple.setMarginTopDip(ciView, Defines.PADDING_TINY);
            Simple.setMarginTopDip(cdView, Defines.PADDING_NORMAL);
        }

        //endregion Detail description area.

        //region Misc area with specs and buy.

        LinearLayout miscAreaVert = new LinearLayout(this);
        miscAreaVert.setOrientation(LinearLayout.VERTICAL);

        if (Simple.isTablet())
        {
            Simple.setSizeDip(miscAreaVert, Simple.MP, Simple.MP, 0.7f);
            infoAreaHorz.addView(miscAreaVert);
        }
        else
        {
            Simple.setSizeDip(miscAreaVert, Simple.MP, Simple.MP);
            infoAreaHorz.addView(miscAreaVert);
        }

        LinearLayout miscAreaHorz = new LinearLayout(this);
        miscAreaHorz.setOrientation(LinearLayout.HORIZONTAL);

        miscAreaVert.addView(miscAreaHorz);

        //region Technical specs area.

        int file_duration = Json.getInt(Globals.displayContent, "file_duration");
        long file_size = Json.getLong(Globals.displayContent, "file_size");
        long mbytes = file_size / (1000 * 1024);

        String suitable_for = Json.getString(Globals.displayContent, "suitable_for");

        LinearLayout specsArea = new LinearLayout(this);
        specsArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setRoundedCorners(specsArea, Defines.CORNER_RADIUS_FRAMES, Defines.COLOR_FRAMES, true);

        if (Defines.isCompactDetails)
        {
            Simple.setPaddingDip(specsArea, Defines.PADDING_LARGE);
        }
        else
        {
            Simple.setPaddingDip(specsArea, Defines.PADDING_NORMAL);
        }

        if (Simple.isTablet())
        {
            Simple.setMarginLeftDip(miscAreaVert, Defines.PADDING_NORMAL);
            Simple.setSizeDip(specsArea, Simple.MP, Simple.MP, 1f);

            miscAreaHorz.setVisibility(GONE);
            miscAreaVert.addView(specsArea);
        }
        else
        {
            Simple.setMarginTopDip(miscAreaVert, Defines.PADDING_NORMAL);
            Simple.setSizeDip(specsArea, Simple.MP, Simple.MP, 1f);

            Simple.setSizeDip(miscAreaHorz, Simple.MP, Simple.MP);
            miscAreaHorz.addView(specsArea);
        }

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
                    (file_duration == 1)
                            ? R.string.detail_specs_quantity_onepage
                            : R.string.detail_specs_quantity_pages,
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
            Simple.setSizeDip(statusIcon, Defines.LOADED_ICON_SIZE, Defines.LOADED_ICON_SIZE);
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

            if (Simple.isTablet())
            {
                TextView suitableView = new TextView(this);
                suitableView.setText(suitable_for);
                suitableView.setMinLines(2);
                suitableView.setEllipsize(TextUtils.TruncateAt.END);
                suitableView.setTextColor(Color.BLACK);
                suitableView.setTypeface(infosTF);
                Simple.setTextSizeDip(suitableView, Defines.FS_DETAIL_SPECS);

                specsArea.addView(suitableView);
            }
        }

        //endregion Technical specs area.

        //region Download and buy area.

        LinearLayout buyloadAreaVert = new LinearLayout(this);
        buyloadAreaVert.setOrientation(LinearLayout.VERTICAL);

        if (Simple.isTablet())
        {
            Simple.setSizeDip(buyloadAreaVert, Simple.MP, Simple.WC);

            miscAreaVert.addView(buyloadAreaVert);
        }
        else
        {
            Simple.setSizeDip(buyloadAreaVert, Simple.MP, Simple.MP, 1f);
            Simple.setMarginLeftDip(buyloadAreaVert, Defines.PADDING_LARGE);

            miscAreaHorz.addView(buyloadAreaVert);
        }

        //region Suitable for area.

        if (Defines.isCompactDetails)
        {
            if (!Simple.isTablet())
            {
                //
                // Only shows up in phone version here.
                // Even if empty, make it eat all remaining
                // space to force down button.
                //

                RelativeLayout suitableArea = new RelativeLayout(this);
                Simple.setSizeDip(suitableArea, Simple.MP, Simple.MP, 1.0f);
                Simple.setPaddingDip(suitableArea, Defines.PADDING_LARGE);

                buyloadAreaVert.addView(suitableArea);

                if ((suitable_for != null) && ! suitable_for.isEmpty())
                {
                    Simple.setRoundedCorners(suitableArea, Defines.CORNER_RADIUS_FRAMES, Defines.COLOR_FRAMES, true);

                    TableLikeLayout suitableView = new TableLikeLayout(this, headerTF, infosTF, true);

                    suitableView.setLeftText(R.string.detail_specs_suitablefor);
                    suitableView.setRightText(suitable_for);

                    suitableArea.addView(suitableView);
                }
            }
        }

        //endregion Suitable for area.

        LinearLayout buyloadArea = new LinearLayout(this);
        buyloadArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(buyloadArea, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(buyloadArea, Defines.PADDING_LARGE);

        if (Defines.isCompactDetails)
        {
            if (Simple.isTablet())
            {
                //
                // For some reason the frame is
                // only colored in table version.
                //

                Simple.setRoundedCorners(buyloadArea, Defines.CORNER_RADIUS_FRAMES, Defines.COLOR_FRAMES, true);
            }

            Simple.setPaddingDip(buyloadArea, Defines.PADDING_LARGE);
        }

        buyloadAreaVert.addView(buyloadArea);

        downloadButton = new ImageView(this);
        downloadButton.setImageResource(R.drawable.lem_t_iany_ralbers_cloud_download);
        downloadButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        downloadButton.setVisibility(GONE);
        Simple.setSizeDip(downloadButton, Defines.CLOUD_ICON_SIZE, Simple.MP);
        Simple.setPaddingDip(downloadButton, Defines.PADDING_SMALL);
        Simple.setRoundedCorners(downloadButton, Defines.CORNER_RADIUS_BIGBUT, Color.WHITE, true);
        Simple.setMarginRightDip(downloadButton, Defines.PADDING_LARGE);

        buyloadArea.addView(downloadButton);

        if (Defines.isCompactDetails)
        {
            downloadButton.setVisibility(GONE);
        }

        buyButton = new GenericButton(this);
        buyButton.setDefaultButton(true);
        buyButton.setGravity(Gravity.CENTER_HORIZONTAL);

        buyButton.requestFocus();

        if (Simple.isTablet())
        {
            Simple.setSizeDip(buyButton, Simple.MP, Simple.WC, 1.0f);

            Simple.setPaddingDip(buyButton,
                    Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL,
                    Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL);
        }
        else
        {
            Simple.setSizeDip(buyButton, Simple.MP, Simple.WC);

            Simple.setPaddingDip(buyButton,
                    Defines.PADDING_XLARGE, Defines.PADDING_SMALL,
                    Defines.PADDING_XLARGE, Defines.PADDING_SMALL);
        }


        buyloadArea.addView(buyButton);

        //endregion Download and buy area.

        //endregion Misc area with specs and buy.

        //endregion Information area.

        updateContent();
    }

    private RelativeLayout createSeparator()
    {
        RelativeLayout separ = new RelativeLayout(this);

        if (Simple.isTablet())
        {
            //
            // Separator only visible in tablet version.
            //

            separ.setBackgroundColor(Defines.isCompactSettings ? Color.BLACK : Color.LTGRAY);
            Simple.setSizeDip(separ, Simple.MP, 1);
        }

        Simple.setMarginDip(separ, 0, Defines.PADDING_SMALL, 0, Defines.PADDING_SMALL);

        return separ;
    }

    public void updateContent()
    {
        if (AssetsDownloadManager.connectDownload(Globals.displayContent,
                onFileLoadedHandler, onDownloadProgressHandler))
        {
            downloadCancel = false;
            downloadProgress.setProgress(0, 0);

            if (Defines.isAskDownload)
            {
                downloadDialog = new DownloadDialog(DetailActivity.this);
                topFrame.addView(downloadDialog);
            }
            else
            {
                downloadCenter.setVisibility(View.VISIBLE);
            }
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
            int content_type = Json.getInt(Globals.displayContent, "content_type");

            if ((content_type == Defines.CONTENT_TYPE_PDF) && Defines.isPDFExternal && ! Simple.isTV())
            {
                File file = ContentHandler.getCachedFile(Globals.displayContent);

                if (file != null)
                {
                    Uri uri = FileProvider.getUriForFile(DetailActivity.this, BuildConfig.APPLICATION_ID, file);

                    Log.d(LOGTAG, "startDisplay: file=" + file.toString());
                    Log.d(LOGTAG, "startDisplay: uri=" + uri.toString());

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    if (intent.resolveActivity(getPackageManager()) != null)
                    {
                        Intent chooser = Intent.createChooser(intent, null);

                        startActivity(chooser);
                    }
                    else
                    {
                        DialogView.errorAlert(DetailActivity.this.topFrame,
                                R.string.alert_no_pdfapp_title,
                                R.string.alert_no_pdfapp_info);
                    }
                }
            }
            else
            {
                Simple.startActivity(DetailActivity.this, ViewActivity.class);
            }
        }
    };

    private final View.OnClickListener startDownload = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            shouldDisplay = false;

            Simple.setRoundedCorners(downloadButton, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_LTBLUE, true);

            downloadCancel = false;
            downloadProgress.setProgress(0, 0);

            if (Defines.isAskDownload)
            {
                downloadDialog = new DownloadDialog(DetailActivity.this);
                topFrame.addView(downloadDialog);
            }
            else
            {
                downloadCenter.setVisibility(View.VISIBLE);
            }

            AssetsDownloadManager.getContentOrFetch(Globals.displayContent, onFileLoadedHandler, onDownloadProgressHandler);
        }
    };

    private final View.OnClickListener askAndDownload = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if (Simple.isOnline(DetailActivity.this))
            {
                DialogView askdialog = new DialogView(DetailActivity.this);

                askdialog.setCloseButton(true, null);

                askdialog.setTitleText(R.string.ask_download_title);
                askdialog.setInfoText(R.string.ask_download_info);

                askdialog.setNegativeButton(R.string.ask_download_load_and_view, startDownloadAndDisplay);
                askdialog.negativeButton.setSingleLine(false);

                askdialog.setPositiveButton(R.string.ask_download_only_load, startDownload);
                askdialog.positiveButton.setSingleLine(false);

                askdialog.positiveButton.requestFocus();

                if (!Simple.isTablet())
                {
                    //
                    // Layout is too fucked up for button
                    // text to fit into this dialog.
                    //

                    Simple.setTextSizeDip(askdialog.positiveButton, Defines.FS_DIALOG_BUTTON - 4);
                    Simple.setTextSizeDip(askdialog.negativeButton, Defines.FS_DIALOG_BUTTON - 4);
                }

                Simple.setPaddingDip(askdialog.negativeButton,
                        0, Defines.PADDING_LARGE,
                        0, Defines.PADDING_LARGE);

                Simple.setPaddingDip(askdialog.positiveButton,
                        0, Defines.PADDING_LARGE,
                        0, Defines.PADDING_LARGE);

                topFrame.addView(askdialog);
            }
            else
            {
                DialogView.errorAlert(topFrame,
                        R.string.alert_no_internet_title,
                        R.string.alert_no_internet_info);
            }
        }
    };

    private final View.OnClickListener startDownloadAndDisplay = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            shouldDisplay = true;

            Simple.setRoundedCorners(downloadButton, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_LTBLUE, true);

            downloadCancel = false;
            downloadProgress.setProgress(0, 0);

            if (Defines.isAskDownload)
            {
                downloadDialog = new DownloadDialog(DetailActivity.this);
                topFrame.addView(downloadDialog);
            }
            else
            {
                downloadCenter.setVisibility(View.VISIBLE);
            }

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
                downloadDialog = null;
            }

            downloadCenter.setVisibility(GONE);

            int id_current = Json.getInt(Globals.displayContent, "id");
            int id_loaded = Json.getInt(content, "id");

            boolean doDisplay = (activity instanceof DetailActivity) && (id_current == id_loaded) && shouldDisplay;

            if (activity instanceof ContentBaseActivity)
            {
                ContentBaseActivity cbactivity = (ContentBaseActivity) activity;

                cbactivity.assetsAdapter.notifyDataSetChanged();
                cbactivity.assetGrid.updateContent();
            }

            if (activity instanceof FullScreenActivity)
            {
                if ((file == null) || ! doDisplay)
                {
                    int titleRes = R.string.detail_download_complete;
                    String text = Json.getString(content, "sub_title");

                    if (file == null)
                    {
                        titleRes = downloadCancel
                                ? R.string.detail_download_cancelled
                                : R.string.detail_download_failed;

                        if (! Simple.isOnline(DetailActivity.this))
                        {
                            text = Simple.getTrans(DetailActivity.this, R.string.detail_download_offline);
                        }
                    }

                    DialogView.errorAlert(((FullScreenActivity) activity).topFrame, titleRes, text);
                }
            }

            if (file == null)
            {
                Simple.setRoundedCorners(downloadButton, Defines.CORNER_RADIUS_BIGBUT, Color.RED, true);
            }
            else
            {
                downloadButton.setVisibility(GONE);

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
        }
    };

    private long downloadProgressRunCurrent;
    private long downloadProgressRunTotal;
    private boolean downloadCancel;

    private final AssetsDownloadManager.OnDownloadProgressHandler onDownloadProgressHandler
            = new AssetsDownloadManager.OnDownloadProgressHandler()
    {
        @Override
        public boolean OnDownloadProgress(JSONObject content, long current, long total)
        {
            downloadProgressRunCurrent = current;
            downloadProgressRunTotal = total;

            ApplicationBase.handler.post(downloadProgressRun);

            return downloadCancel;
        }
    };

    private final Runnable downloadProgressRun = new Runnable()
    {
        @Override
        public void run()
        {
            if (downloadDialog != null)
            {
                downloadCancel = downloadDialog.setProgressLong(downloadProgressRunCurrent, downloadProgressRunTotal);
            }

            downloadProgress.setProgressLong(downloadProgressRunCurrent, downloadProgressRunTotal);
        }
    };
}
