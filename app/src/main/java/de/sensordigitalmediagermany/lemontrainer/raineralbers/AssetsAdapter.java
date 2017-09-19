package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        int imageWidth  = gridView.getColumnWidth();
        int imageHeight = Math.round(imageWidth / Defines.FS_ASSET_THUMBNAIL_ASPECT);

        Log.d(LOGTAG, "getView: imageWidth=" + imageWidth + " imageHeight=" +  + imageHeight);

        LinearLayout assetFrame = new LinearLayout(parent.getContext());
        assetFrame.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(assetFrame, Simple.MP, Simple.WC);
        Simple.setPaddingDip(assetFrame, Defines.PADDING_SMALL);

        JSONObject asset = (JSONObject) getItem(position % assets.length());

        String title = Json.getString(asset, "title");
        String subtitle = Json.getString(asset, "sub_title");
        String thumburl = Json.getString(asset, "thumbnail_url");

        LinearLayout imageBox = new LinearLayout(parent.getContext());
        imageBox.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(imageBox, Simple.MP, Simple.pxToDip(imageHeight));

        assetFrame.addView(imageBox);

        ImageView imageView = new ImageView(parent.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Simple.setSizeDip(imageView, Simple.MP, Simple.pxToDip(imageHeight));

        imageBox.addView(imageView);

        Bitmap bitmap = Simple.getBitmapFromHTTP(parent.getContext(), thumburl);

        if (bitmap != null)
        {
            float aspectX = bitmap.getWidth() / (float) imageWidth;
            float aspectY = bitmap.getHeight() / (float) imageHeight;

            int cornerRadius = Math.round(Defines.CORNER_RADIUS_ASSETS * ((aspectX + aspectY) / 2));

            bitmap = Simple.makeRoundedTopCornersBitmap(bitmap, cornerRadius);
        }

        imageView.setImageDrawable(new BitmapDrawable(parent.getContext().getResources(), bitmap));

        LinearLayout textBox = new LinearLayout(parent.getContext());
        textBox.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(imageView, Simple.MP, Simple.WC);

        int radiusdipse[] = new int[ 4 ];

        radiusdipse[ 0 ] = 0;
        radiusdipse[ 1 ] = 0;
        radiusdipse[ 2 ] = Defines.CORNER_RADIUS_ASSETS;
        radiusdipse[ 3 ] = Defines.CORNER_RADIUS_ASSETS;

        Simple.setRoundedCorners(textBox, radiusdipse, Color.WHITE, true);

        Simple.setPaddingDip(textBox,
                Defines.PADDING_NORMAL, Defines.PADDING_SMALL,
                Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);

        assetFrame.addView(textBox);

        TextView titleView = new TextView(parent.getContext());
        titleView.setText(title);
        titleView.setAllCaps(true);
        titleView.setMinLines(1);
        titleView.setMaxLines(1);
        titleView.setEllipsize(TextUtils.TruncateAt.END);
        titleView.setTextColor(Color.BLACK);
        titleView.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(titleView, Defines.FS_ASSET_TITLE);

        textBox.addView(titleView);

        TextView infoView = new TextView(parent.getContext());
        infoView.setText(subtitle);
        infoView.setMinLines(2);
        infoView.setMaxLines(2);
        infoView.setEllipsize(TextUtils.TruncateAt.END);
        infoView.setTextColor(Color.BLACK);
        infoView.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), Defines.GOTHAMNARROW_LIGHT));
        infoView.setLineSpacing(0.0f, Defines.FS_ASSET_INFO_LSMULT);
        Simple.setTextSizeDip(infoView, Defines.FS_ASSET_INFO);

        textBox.addView(infoView);

        return assetFrame;
    }
}
