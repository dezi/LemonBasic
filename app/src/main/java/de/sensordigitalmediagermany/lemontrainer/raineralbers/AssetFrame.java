package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.util.Log;

import org.json.JSONObject;

public class AssetFrame extends LinearLayout
{
    private static final String LOGTAG = AssetFrame.class.getSimpleName();

    private FrameLayout imageBox;
    private ImageView imageView;
    private TextView titleView;
    private TextView summaryView;
    private ImageView iconView;
    private TextView ownedView;
    private ImageView loadedView;
    private ProgressBar downloadProgress;

    public static AssetFrame createAssetFrame(Context context, View convertView, int width, JSONObject asset,
                                              AssetsAdapter.OnAssetClickedHandler onAssetClickedHandler)
    {
        AssetFrame assetFrame;

        if ((convertView != null) && (convertView instanceof AssetFrame))
        {
            assetFrame = (AssetFrame) convertView;
        }
        else
        {
            assetFrame = new AssetFrame(context);
        }

        assetFrame.setAsset(width, asset, onAssetClickedHandler);

        return assetFrame;
    }

    public AssetFrame(Context context)
    {
        super(context);

        createGenericStyle();
    }

    private void createGenericStyle()
    {
        Typeface titleFont = Typeface.createFromAsset(getContext().getAssets(),Defines.FONT_ASSET_TITLE);
        Typeface summaryFont = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_ASSET_SUMMARY);

        this.setOrientation(LinearLayout.VERTICAL);
        this.setBackgroundColor(Defines.COLOR_CONTENT);
        Simple.setSizeDip(this, Simple.MP, Simple.WC);

        imageBox = new FrameLayout(getContext());

        this.addView(imageBox);

        imageView = new ImageView(getContext());
        imageView.setId(android.R.id.content);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        imageBox.addView(imageView);

        RelativeLayout courseBar = new RelativeLayout(getContext());
        Simple.setSizeDip(courseBar, Simple.MP, Simple.MP);
        imageBox.addView(courseBar);

        iconView = new ImageView(getContext());
        iconView.setId(android.R.id.icon);
        Simple.setSizeDip(iconView, Simple.WC, Defines.COURSE_ICON_SIZE);

        courseBar.addView(iconView);

        if (Defines.isOverlayAsset)
        {
            courseBar.setGravity(Gravity.START + Gravity.TOP);
            iconView.setScaleType(ImageView.ScaleType.FIT_START);

            Simple.setPaddingDip(iconView, Defines.PADDING_NORMAL);
        }
        else
        {
            courseBar.setGravity(Gravity.CENTER_VERTICAL + Gravity.CENTER_HORIZONTAL);
            iconView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        RelativeLayout overlayBar = new RelativeLayout(getContext());
        overlayBar.setGravity(Gravity.END);
        Simple.setSizeDip(overlayBar, Simple.MP, Simple.WC);
        Simple.setPaddingDip(overlayBar, Defines.PADDING_SMALL);

        imageBox.addView(overlayBar);

        ownedView = new TextView(getContext());
        ownedView.setId(android.R.id.icon1);
        ownedView.setText(R.string.asset_owned);
        ownedView.setAllCaps(true);
        ownedView.setTextColor(Color.BLACK);
        ownedView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(ownedView, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(ownedView, Defines.FS_ASSET_OWNED);
        Simple.setRoundedCorners(ownedView, Defines.CORNER_RADIUS_OVERLAY, Color.WHITE, true);

        Simple.setPaddingDip(ownedView,
                Defines.PADDING_NORMAL, Defines.PADDING_TINY,
                Defines.PADDING_NORMAL, Defines.PADDING_TINY);

        overlayBar.addView(ownedView);

        loadedView = new ImageView(getContext());
        loadedView.setId(android.R.id.icon2);
        loadedView.setImageResource(DefinesScreens.getReadMarkerRes());
        loadedView.setScaleType(ImageView.ScaleType.FIT_END);
        Simple.setSizeDip(loadedView, Simple.MP, Defines.READ_ICON_SIZE + (Defines.PADDING_SMALL * 2));
        Simple.setPaddingDip(loadedView, Defines.PADDING_SMALL);

        imageBox.addView(loadedView);

        RelativeLayout downloadCenter = new RelativeLayout(getContext());
        downloadCenter.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.BOTTOM);
        Simple.setSizeDip(downloadCenter, Simple.MP, Simple.MP);
        Simple.setPaddingDip(downloadCenter, Defines.PADDING_SMALL);

        imageBox.addView(downloadCenter);

        downloadProgress = new ProgressBar(getContext());
        downloadProgress.setId(android.R.id.custom);
        downloadProgress.setWidthDip(Simple.MP);
        downloadProgress.setColors(Color.GREEN, Color.YELLOW);

        downloadCenter.addView(downloadProgress);

        LinearLayout textBox = new LinearLayout(getContext());
        textBox.setOrientation(LinearLayout.VERTICAL);

        Simple.setPaddingDip(textBox,
                Defines.PADDING_NORMAL, Defines.PADDING_SMALL,
                Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);

        if (Defines.isRoundedAsset)
        {
            int radiusdipse[] = new int[4];

            radiusdipse[0] = 0;
            radiusdipse[1] = 0;
            radiusdipse[2] = Defines.CORNER_RADIUS_ASSETS;
            radiusdipse[3] = Defines.CORNER_RADIUS_ASSETS;

            Simple.setRoundedCorners(textBox, radiusdipse, Color.WHITE, true);
        }

        if (Defines.isOverlayAsset)
        {
            LinearLayout forceDown = new LinearLayout(getContext());
            Simple.setSizeDip(forceDown, Simple.MP, Simple.MP, 1.0f);

            textBox.addView(forceDown);

            imageBox.addView(textBox);
        }
        else
        {
            this.addView(textBox);
        }

        titleView = new TextView(getContext());
        titleView.setId(android.R.id.title);
        titleView.setAllCaps(true);
        titleView.setMinLines(1);
        titleView.setMaxLines(1);
        titleView.setEllipsize(TextUtils.TruncateAt.END);
        titleView.setTextColor(Defines.isOverlayAsset ? Color.WHITE : Color.BLACK);
        titleView.setTypeface(titleFont);

        Simple.setTextSizeDip(titleView, Defines.FS_ASSET_TITLE);

        textBox.addView(titleView);

        summaryView = new TextView(getContext());
        summaryView.setId(android.R.id.summary);
        summaryView.setAllCaps(Defines.isInfosAllCaps);
        summaryView.setMinLines(2);
        summaryView.setMaxLines(2);
        summaryView.setEllipsize(TextUtils.TruncateAt.END);
        summaryView.setLineSpacing(0.0f, Defines.FS_ASSET_INFO_LSMULT);
        summaryView.setTextColor(Defines.isOverlayAsset ? Color.WHITE : Color.BLACK);
        summaryView.setTypeface(summaryFont);

        Simple.setTextSizeDip(summaryView, Defines.FS_ASSET_INFO);

        textBox.addView(summaryView);
    }

    public void setAsset(int imageWidth, JSONObject asset)
    {
        setAsset(imageWidth, asset, null);
    }

    public void setAsset(int imageWidth,
                         final JSONObject asset,
                         final AssetsAdapter.OnAssetClickedHandler onAssetClickedHandler)
    {
        int imageHeight = Math.round(imageWidth / Defines.ASSET_THUMBNAIL_ASPECT);

        int id = Json.getInt(asset, "id");
        String title = Json.getString(asset, "title");
        String subtitle = Json.getString(asset, "sub_title");
        String thumburl = Json.getString(asset, "thumbnail_url");

        boolean isCourse = Json.getBoolean(asset, "_isCourse");

        Simple.setSizeDip(imageBox, Simple.MP, Simple.pxToDip(imageHeight));
        Simple.setSizeDip(imageView, Simple.MP, Simple.pxToDip(imageHeight));

        imageView.setImageDrawable(
                AssetsImageManager.getDrawableOrFetch(
                        getContext(),
                        imageView, thumburl,
                        imageWidth, imageHeight,
                        Defines.isRoundedAsset));

        titleView.setText(title);
        summaryView.setText(subtitle);

        ownedView.setVisibility(View.GONE);
        loadedView.setVisibility(View.GONE);
        downloadProgress.setVisibility(View.GONE);

        if (isCourse)
        {
            iconView.setImageResource(DefinesScreens.getCourseMarkerRes());
            iconView.setVisibility(View.VISIBLE);

            this.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Log.d(LOGTAG,"openCourse....");

                    if (onAssetClickedHandler != null)
                    {
                        onAssetClickedHandler.OnAssetClicked(asset);
                    }
                    else
                    {
                        Globals.displayContent = asset;

                        Simple.startActivity(getContext(), CourseActivity.class);
                    }
                }
            });

            if (ContentHandler.isCachedContent(asset))
            {
                loadedView.setVisibility(View.VISIBLE);
            }
            else
            {
                if (ContentHandler.isCourseBought(id))
                {
                    ownedView.setVisibility(View.VISIBLE);
                }
            }
        }
        else
        {
            int content_type = Json.getInt(asset, "content_type");

            int iconResid = 0;

            if (content_type == Defines.CONTENT_TYPE_PDF) iconResid = R.drawable.lem_t_iany_generic_type_pdf_mini;
            if (content_type == Defines.CONTENT_TYPE_ZIP) iconResid = R.drawable.lem_t_iany_generic_type_html5_mini;
            if (content_type == Defines.CONTENT_TYPE_VIDEO) iconResid = R.drawable.lem_t_iany_generic_type_video_mini;
            if (content_type == Defines.CONTENT_TYPE_AUDIO) iconResid = R.drawable.lem_t_iany_generic_type_audio_mini;

            if (iconResid > 0)
            {
                iconView.setImageResource(iconResid);
                iconView.setVisibility(View.VISIBLE);
            }
            else
            {
                iconView.setVisibility(View.GONE);
            }

            this.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Log.d(LOGTAG,"openContent....");

                    if (onAssetClickedHandler != null)
                    {
                        onAssetClickedHandler.OnAssetClicked(asset);
                    }
                    else
                    {
                        Globals.displayContent = asset;

                        Simple.startActivity(getContext(), DetailActivity.class);
                    }
                }
            });

            if (ContentHandler.isCachedContent(asset))
            {
                loadedView.setVisibility(View.VISIBLE);
            }
            else
            {
                if (ContentHandler.isContentBought(id))
                {
                    ownedView.setVisibility(View.VISIBLE);
                }
            }
        }

        if (AssetsDownloadManager.connectDownload(asset, null))
        {
            downloadProgress.setProgress(0,0);
            downloadProgress.setVisibility(View.VISIBLE);

            final ProgressBar dpFinal = downloadProgress;

            AssetsDownloadManager.connectDownload(asset, new AssetsDownloadManager.OnDownloadProgressHandler()
            {
                @Override
                public void OnDownloadProgress(JSONObject content, final long current, final long total)
                {
                    ApplicationBase.handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if (current == total)
                            {
                                dpFinal.setVisibility(View.GONE);
                            }
                            else
                            {
                                dpFinal.setProgressLong(current, total);
                            }
                        }
                    });
                }
            });
        }
    }
}
