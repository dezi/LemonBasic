package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.AbstractThreadedSyncAdapter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class AssetsAdapter extends BaseAdapter
{
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
        LinearLayout assetFrame = new LinearLayout(parent.getContext());
        assetFrame.setOrientation(LinearLayout.VERTICAL);
        assetFrame.setBackgroundResource(R.drawable.lem_t_iany_ralbers_assetframe_dim);
        Simple.setSizeDip(assetFrame, Simple.MP, 200);
        Simple.setPaddingDip(assetFrame, 0);

        JSONObject asset = (JSONObject) getItem(position % assets.length());

        ImageView imageView = new ImageView(parent.getContext());
        Simple.setSizeDip(imageView, Simple.MP, Simple.MP, 1.0f);
        imageView.setBackgroundColor(0x88880000);

        assetFrame.addView(imageView);

        LinearLayout textBox = new LinearLayout(parent.getContext());
        textBox.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(imageView, Simple.MP, Simple.WC);

        Simple.setPaddingDip(textBox,
                Defines.PADDING_LARGE, Defines.PADDING_SMALL,
                Defines.PADDING_LARGE, Defines.PADDING_LARGE);

        assetFrame.addView(textBox);

        TextView titleView = new TextView(parent.getContext());
        titleView.setText("Titel Mental Coaching");
        titleView.setAllCaps(true);
        titleView.setMaxLines(1);
        titleView.setEllipsize(TextUtils.TruncateAt.END);
        titleView.setTextColor(Color.BLACK);
        titleView.setTextColor(Color.BLACK);
        titleView.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(titleView, Defines.FS_ASSET_TITLE);

        textBox.addView(titleView);

        TextView infoView = new TextView(parent.getContext());
        infoView.setText("Bla bla mehr Text als zwei Zeilen schnideldum hurra pi pa po trallalla.");
        infoView.setTextColor(Color.BLACK);
        infoView.setMaxLines(2);
        infoView.setEllipsize(TextUtils.TruncateAt.END);
        infoView.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), Defines.GOTHAMNARROW_LIGHT));
        infoView.setLineSpacing(0.0f, Defines.FS_ASSET_INFO_LHMULT);
        Simple.setTextSizeDip(infoView, Defines.FS_ASSET_INFO);

        textBox.addView(infoView);

        return assetFrame;
    }
}
