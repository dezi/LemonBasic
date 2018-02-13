package de.sensordigitalmediagermany.lemonbasic.generic;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.Gravity;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

public class AssetsAdapter extends BaseAdapter
{
    private static Typeface settingsFont;

    private JSONArray assets;
    private boolean isSettings;

    private OnAssetClickedHandler onAssetClickedHandler;

    public void setAssets(JSONArray assets)
    {
        this.assets = assets;
    }

    public void setSettings(boolean settings)
    {
        this.isSettings = settings;
    }

    public void setOnAssetClickedHandler(OnAssetClickedHandler onAssetClickedHandler)
    {
        this.onAssetClickedHandler = onAssetClickedHandler;
    }

    @Override
    public int getCount()
    {
        return (assets == null) ? 0 : assets.length();
    }

    @Override
    public Object getItem(int position)
    {
        return (assets == null) ? null : Json.getObject(assets, position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return isSettings
                ? getViewSettings(position, convertView, parent)
                : getViewContents(position, convertView, parent)
                ;
    }

    private View getViewSettings(int position, View convertView, final ViewGroup parent)
    {
        if (settingsFont == null)
        {
            settingsFont = Typeface.createFromAsset(parent.getContext().getAssets(), Defines.FONT_SETTINGS_LIST);
        }

        int imageWidth = Simple.dipToPx(Defines.SETTINGS_IMAGE_SIZE);
        int imageHeight = Math.round(imageWidth / Defines.ASSET_THUMBNAIL_ASPECT);

        LinearLayout assetFrame;
        ImageView imageView;
        TextView titleView;
        TextView summaryView;
        TextView moreView;

        if (convertView != null)
        {
            assetFrame = (LinearLayout) convertView;

            imageView = (ImageView) convertView.findViewById(android.R.id.content);
            titleView = (TextView) convertView.findViewById(android.R.id.title);
            summaryView = (TextView) convertView.findViewById(android.R.id.summary);
            moreView = (TextView) convertView.findViewById(android.R.id.custom);
        }
        else
        {
            assetFrame = new GenericLinear(parent.getContext());

            assetFrame.setFocusable(true);
            assetFrame.setOrientation(LinearLayout.HORIZONTAL);
            assetFrame.setOnClickListener(onClickHandler);
            assetFrame.setLayoutParams(new ViewGroup.MarginLayoutParams(Simple.MP, imageHeight));

            imageView = new ImageView(parent.getContext());
            imageView.setId(android.R.id.content);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Simple.setSizeDip(imageView, Simple.pxToDip(imageWidth), Simple.pxToDip(imageHeight));

            assetFrame.addView(imageView);

            LinearLayout textBox = new LinearLayout(parent.getContext());
            textBox.setOrientation(LinearLayout.HORIZONTAL);
            Simple.setSizeDip(textBox, Simple.MP, Simple.MP);

            assetFrame.addView(textBox);

            titleView = new TextView(parent.getContext());
            titleView.setId(android.R.id.title);
            titleView.setSingleLine();
            titleView.setEllipsize(TextUtils.TruncateAt.END);
            titleView.setAllCaps(Defines.isInfosAllCaps);
            titleView.setGravity(Gravity.CENTER_VERTICAL);
            titleView.setTextColor(Color.BLACK);
            titleView.setTypeface(settingsFont);
            Simple.setTextSizeDip(titleView, Defines.FS_SETTINGS_LIST);
            Simple.setSizeDip(titleView, Simple.MP, Simple.MP, 1.0f);
            Simple.setMarginLeftDip(titleView, Defines.PADDING_SMALL);

            textBox.addView(titleView);

            summaryView = new TextView(parent.getContext());
            summaryView.setId(android.R.id.summary);
            summaryView.setSingleLine();
            summaryView.setAllCaps(Defines.isInfosAllCaps);
            summaryView.setGravity(Gravity.CENTER_VERTICAL);
            summaryView.setTypeface(settingsFont);
            Simple.setTextSizeDip(summaryView, Defines.FS_SETTINGS_LIST);
            Simple.setSizeDip(summaryView, Simple.WC, Simple.MP);
            Simple.setMarginLeftDip(summaryView, Defines.PADDING_SMALL);

            textBox.addView(summaryView);

            moreView = new TextView(parent.getContext());
            moreView.setId(android.R.id.custom);
            moreView.setText(">");
            moreView.setSingleLine();
            moreView.setGravity(Gravity.CENTER_VERTICAL);
            moreView.setTypeface(settingsFont);
            Simple.setTextSizeDip(moreView, Defines.FS_SETTINGS_MORE);
            Simple.setSizeDip(moreView, Simple.WC, Simple.MP);
            Simple.setMarginLeftDip(moreView, Defines.PADDING_SMALL);
            Simple.setMarginRightDip(moreView, Defines.PADDING_SMALL);

            textBox.addView(moreView);
        }

        final JSONObject asset = (JSONObject) getItem(position % assets.length());

        if (assetFrame.getTag() != asset)
        {
            assetFrame.setTag(asset);

            //
            // The following attributes never change.
            // Only set them, if the asset changed.
            //

            String title = Json.getString(asset, "title");
            String subtitle = Json.getString(asset, "sub_title");
            String thumburl = Json.getString(asset, "thumbnail_url");
            long file_size = Json.getLong(asset, "file_size");

            String displayTitle = subtitle;

            if (!Defines.isSectionDividers) displayTitle = title + " | " + displayTitle;

            AssetsImageManager.getDrawableOrFetch(
                    parent.getContext(),
                    imageView, thumburl,
                    imageWidth, imageHeight, false);

            titleView.setText(displayTitle);

            summaryView.setText(Simple.formatBytes(file_size));
        }

        if (Json.getBoolean(asset, "_isSelected"))
        {
            if (Defines.COLOR_SETTINGS_LIST_SEL == Color.BLACK)
            {
                titleView.setTextColor(Color.WHITE);
                summaryView.setTextColor(Color.WHITE);
                moreView.setTextColor(Color.WHITE);
            }
            else
            {
                titleView.setTextColor(Color.BLACK);
                summaryView.setTextColor(Color.BLACK);
                moreView.setTextColor(Color.BLACK);
            }

            assetFrame.setBackgroundColor(Defines.COLOR_SETTINGS_LIST_SEL);
        }
        else
        {
            titleView.setTextColor(Color.BLACK);
            summaryView.setTextColor(Color.BLACK);
            moreView.setTextColor(Color.BLACK);

            assetFrame.setBackgroundColor(Defines.COLOR_SETTINGS_LIST);
        }

        return assetFrame;
    }

    private View getViewContents(int position, View convertView, ViewGroup parent)
    {
        JSONObject asset = (JSONObject) getItem(position % assets.length());

        int width = parent.getWidth();

        if (parent instanceof GenericGridView)
        {
            width = ((GenericGridView) parent).getColumnWidth();
        }

        return AssetFrame.createAssetFrame(parent.getContext(), convertView, width, asset, onAssetClickedHandler);
    }

    private final View.OnClickListener onClickHandler = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Object asset = view.getTag();

            if ((asset instanceof JSONObject) && (onAssetClickedHandler != null))
            {
                onAssetClickedHandler.OnAssetClicked((JSONObject) asset);
            }
        }
    };

    public interface OnAssetClickedHandler
    {
        void OnAssetClicked(JSONObject content);
    }
}
