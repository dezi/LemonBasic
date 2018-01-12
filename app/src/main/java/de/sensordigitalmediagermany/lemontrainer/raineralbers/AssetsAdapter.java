package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.view.Gravity;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.View;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class AssetsAdapter extends BaseAdapter
{
    private static final String LOGTAG = AssetsAdapter.class.getSimpleName();

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

    private View getViewSettings(int position, View convertView, ViewGroup parent)
    {
        final GridView gridView = (GridView) parent;

        int imageWidth = Simple.dipToPx(Defines.ASSET_IMAGE_SIZE);
        int imageHeight = Math.round(imageWidth / Defines.ASSET_THUMBNAIL_ASPECT);

        Log.d(LOGTAG, "getView: imageWidth=" + imageWidth + " imageHeight=" + +imageHeight);

        LinearLayout assetFrame;
        ImageView imageView;
        TextView titleView;
        TextView summaryView;
        ImageView courseView;

        if (convertView != null)
        {
            assetFrame = (LinearLayout) convertView;

            imageView = (ImageView) convertView.findViewById(android.R.id.content);
            titleView = (TextView) convertView.findViewById(android.R.id.title);
            summaryView = (TextView) convertView.findViewById(android.R.id.summary);
            courseView = (ImageView) convertView.findViewById(android.R.id.icon);
        }
        else
        {
            assetFrame = new LinearLayout(parent.getContext());
            assetFrame.setOrientation(LinearLayout.HORIZONTAL);
            Simple.setSizeDip(assetFrame, Simple.MP, Simple.WC);

            FrameLayout imageBox = new FrameLayout(parent.getContext());
            Simple.setSizeDip(imageBox, Simple.pxToDip(imageWidth), Simple.pxToDip(imageHeight));

            assetFrame.addView(imageBox);

            imageView = new ImageView(parent.getContext());
            imageView.setId(android.R.id.content);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Simple.setSizeDip(imageView, Simple.pxToDip(imageWidth), Simple.pxToDip(imageHeight));

            imageBox.addView(imageView);

            RelativeLayout courseBar = new RelativeLayout(parent.getContext());
            courseBar.setGravity(Gravity.CENTER_VERTICAL + Gravity.CENTER_HORIZONTAL);
            Simple.setSizeDip(courseBar, Simple.MP, Simple.MP);
            imageBox.addView(courseBar);

            courseView = new ImageView(parent.getContext());
            courseView.setId(android.R.id.icon);
            courseView.setImageResource(DefinesScreens.getCourseMarkerRes());
            courseView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Simple.setSizeDip(courseView, Simple.WC, Simple.pxToDip(imageHeight) * 2 / 3);

            courseBar.addView(courseView);

            LinearLayout textBox = new LinearLayout(parent.getContext());
            textBox.setOrientation(LinearLayout.HORIZONTAL);
            Simple.setSizeDip(textBox, Simple.MP, Simple.MP);

            assetFrame.addView(textBox);

            titleView = new TextView(parent.getContext());
            titleView.setId(android.R.id.title);
            titleView.setSingleLine();
            titleView.setEllipsize(TextUtils.TruncateAt.END);
            titleView.setGravity(Gravity.CENTER_VERTICAL);
            titleView.setTextColor(Color.BLACK);
            titleView.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), Defines.GOTHAMNARROW_LIGHT));
            Simple.setTextSizeDip(titleView, Defines.FS_SETTINGS_LIST);
            Simple.setSizeDip(titleView, Simple.MP, Simple.MP, 1.0f);
            Simple.setMarginLeftDip(titleView, Defines.PADDING_SMALL);

            textBox.addView(titleView);

            summaryView = new TextView(parent.getContext());
            summaryView.setId(android.R.id.summary);
            summaryView.setSingleLine();
            summaryView.setGravity(Gravity.CENTER_VERTICAL);
            summaryView.setTextColor(Color.BLACK);
            summaryView.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), Defines.GOTHAMNARROW_LIGHT));
            Simple.setTextSizeDip(summaryView, Defines.FS_SETTINGS_LIST);
            Simple.setSizeDip(summaryView, Simple.WC, Simple.MP);
            Simple.setMarginLeftDip(summaryView, Defines.PADDING_SMALL);

            textBox.addView(summaryView);

            TextView moreView = new TextView(parent.getContext());
            moreView.setText(">");
            moreView.setSingleLine();
            moreView.setGravity(Gravity.CENTER_VERTICAL);
            moreView.setTextColor(Color.BLACK);
            moreView.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), Defines.GOTHAMNARROW_LIGHT));
            Simple.setTextSizeDip(moreView, Defines.FS_SETTINGS_MORE);
            Simple.setSizeDip(moreView, Simple.WC, Simple.MP);
            Simple.setMarginLeftDip(moreView, Defines.PADDING_SMALL);
            Simple.setMarginRightDip(moreView, Defines.PADDING_SMALL);

            textBox.addView(moreView);
        }

        final JSONObject asset = (JSONObject) getItem(position % assets.length());

        String title = Json.getString(asset, "title");
        String subtitle = Json.getString(asset, "sub_title");
        String thumburl = Json.getString(asset, "thumbnail_url");
        String displayTitle = title + " | " + subtitle;

        long file_size = Json.getLong(asset, "file_size");
        long mbytes = file_size / (1000 * 1024);

        boolean isCourse = Json.getBoolean(asset, "_isCourse");
        boolean isSelected = Json.getBoolean(asset, "_isSelected");

        assetFrame.setBackgroundColor(isSelected ? Defines.COLOR_SENSOR_LTBLUE : Defines.COLOR_SENSOR_NAVIBAR);

        imageView.setImageDrawable(
                AssetsImageManager.getDrawableOrFetch(
                        parent.getContext(),
                        imageView, thumburl,
                        imageWidth, imageHeight, false));


        titleView.setText(displayTitle);

        summaryView.setText(Simple.getTrans(parent.getContext(),
                R.string.detail_specs_size_mb,
                (mbytes < 1) ? "< 1" : Simple.formatDecimal(mbytes)));

        if (isCourse)
        {
            courseView.setVisibility(View.VISIBLE);

            assetFrame.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    openCourse(gridView, asset);
                }
            });
        }
        else
        {
            courseView.setVisibility(View.GONE);

            assetFrame.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    openContent(gridView, asset);
                }
            });
        }

        return assetFrame;
    }

    private View getViewContents(int position, View convertView, ViewGroup parent)
    {
        JSONObject asset = (JSONObject) getItem(position % assets.length());
        int width = ((GridView) parent).getColumnWidth();

        return AssetFrame.createAssetFrame(parent.getContext(), convertView, width, asset, onAssetClickedHandler);
    }

    private void openCourse(ViewGroup parent, JSONObject course)
    {
        Log.d(LOGTAG,"openCourse....");

        if (onAssetClickedHandler != null)
        {
            onAssetClickedHandler.OnAssetClicked(course);
        }
        else
        {
            Globals.displayContent = course;

            Simple.startActivity(parent.getContext(), CourseActivity.class);
        }
    }

    private void openContent(ViewGroup parent, JSONObject content)
    {
        Log.d(LOGTAG,"openContent....");

        if (onAssetClickedHandler != null)
        {
            onAssetClickedHandler.OnAssetClicked(content);
        }
        else
        {
            Globals.displayContent = content;

            Simple.startActivity(parent.getContext(), DetailActivity.class);
        }
    }

    public interface OnAssetClickedHandler
    {
        void OnAssetClicked(JSONObject content);
    }
}
