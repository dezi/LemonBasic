package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    public void setAssets(JSONArray assets)
    {
        this.assets = assets;
    }

    @Override
    public int getCount()
    {
        return (assets == null) ? 0 : (assets.length() * 10);
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
        GridView gridView = (GridView) parent;

        int imageWidth = gridView.getColumnWidth();
        int imageHeight = Math.round(imageWidth / Defines.FS_ASSET_THUMBNAIL_ASPECT);

        Log.d(LOGTAG, "getView: imageWidth=" + imageWidth + " imageHeight=" + +imageHeight);

        LinearLayout assetFrame;
        ImageView imageView;
        TextView titleView;
        TextView summaryView;

        if (convertView != null)
        {
            assetFrame = (LinearLayout) convertView;

            imageView = (ImageView) convertView.findViewById(android.R.id.content);
            titleView = (TextView) convertView.findViewById(android.R.id.title);
            summaryView = (TextView) convertView.findViewById(android.R.id.summary);
        }
        else
        {
            assetFrame = new LinearLayout(parent.getContext());
            assetFrame.setOrientation(LinearLayout.VERTICAL);
            Simple.setSizeDip(assetFrame, Simple.MP, Simple.WC);
            Simple.setPaddingDip(assetFrame, Defines.PADDING_SMALL);

            LinearLayout imageBox = new LinearLayout(parent.getContext());
            imageBox.setOrientation(LinearLayout.VERTICAL);
            Simple.setSizeDip(imageBox, Simple.MP, Simple.pxToDip(imageHeight));

            assetFrame.addView(imageBox);

            imageView = new ImageView(parent.getContext());
            imageView.setId(android.R.id.content);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Simple.setSizeDip(imageView, Simple.MP, Simple.pxToDip(imageHeight));

            imageBox.addView(imageView);

            LinearLayout textBox = new LinearLayout(parent.getContext());
            textBox.setOrientation(LinearLayout.VERTICAL);
            Simple.setSizeDip(imageView, Simple.MP, Simple.WC);

            int radiusdipse[] = new int[4];

            radiusdipse[0] = 0;
            radiusdipse[1] = 0;
            radiusdipse[2] = Defines.CORNER_RADIUS_ASSETS;
            radiusdipse[3] = Defines.CORNER_RADIUS_ASSETS;

            Simple.setRoundedCorners(textBox, radiusdipse, Color.WHITE, true);

            Simple.setPaddingDip(textBox,
                    Defines.PADDING_NORMAL, Defines.PADDING_SMALL,
                    Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);

            assetFrame.addView(textBox);

            titleView = new TextView(parent.getContext());
            titleView.setId(android.R.id.title);
            titleView.setAllCaps(true);
            titleView.setMinLines(1);
            titleView.setMaxLines(1);
            titleView.setEllipsize(TextUtils.TruncateAt.END);
            titleView.setTextColor(Color.BLACK);
            titleView.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), Defines.GOTHAM_BOLD));
            Simple.setTextSizeDip(titleView, Defines.FS_ASSET_TITLE);

            textBox.addView(titleView);

            summaryView = new TextView(parent.getContext());
            summaryView.setId(android.R.id.summary);
            summaryView.setMinLines(2);
            summaryView.setMaxLines(2);
            summaryView.setEllipsize(TextUtils.TruncateAt.END);
            summaryView.setTextColor(Color.BLACK);
            summaryView.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), Defines.GOTHAMNARROW_LIGHT));
            summaryView.setLineSpacing(0.0f, Defines.FS_ASSET_INFO_LSMULT);
            Simple.setTextSizeDip(summaryView, Defines.FS_ASSET_INFO);

            textBox.addView(summaryView);
        }

        JSONObject asset = (JSONObject) getItem(position % assets.length());

        String title = Json.getString(asset, "title");
        String subtitle = Json.getString(asset, "sub_title");
        String thumburl = Json.getString(asset, "thumbnail_url");

        imageView.setImageDrawable(
                AssetsImageManager.getDrawableOrFetch(
                        parent.getContext(),
                        imageView, thumburl,
                        imageWidth, imageHeight)
        );

        titleView.setText(title);
        summaryView.setText(subtitle);

        return assetFrame;
    }
}
