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
        final GridView gridView = (GridView) parent;

        int imageWidth = gridView.getColumnWidth();
        int imageHeight = Math.round(imageWidth / Defines.FS_ASSET_THUMBNAIL_ASPECT);

        Log.d(LOGTAG, "getView: imageWidth=" + imageWidth + " imageHeight=" + +imageHeight);

        LinearLayout assetFrame;
        ImageView imageView;
        TextView titleView;
        TextView summaryView;
        ImageView courseView;
        TextView ownedView;
        ImageView readView;

        if (convertView != null)
        {
            assetFrame = (LinearLayout) convertView;

            imageView = (ImageView) convertView.findViewById(android.R.id.content);
            titleView = (TextView) convertView.findViewById(android.R.id.title);
            summaryView = (TextView) convertView.findViewById(android.R.id.summary);
            courseView = (ImageView) convertView.findViewById(android.R.id.icon);
            ownedView = (TextView) convertView.findViewById(android.R.id.icon1);
            readView = (ImageView) convertView.findViewById(android.R.id.icon2);
        }
        else
        {
            assetFrame = new LinearLayout(parent.getContext());
            assetFrame.setOrientation(LinearLayout.VERTICAL);
            Simple.setSizeDip(assetFrame, Simple.MP, Simple.WC);
            Simple.setPaddingDip(assetFrame, Defines.PADDING_SMALL);

            FrameLayout imageBox = new FrameLayout(parent.getContext());
            Simple.setSizeDip(imageBox, Simple.MP, Simple.pxToDip(imageHeight));

            assetFrame.addView(imageBox);

            imageView = new ImageView(parent.getContext());
            imageView.setId(android.R.id.content);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Simple.setSizeDip(imageView, Simple.MP, Simple.pxToDip(imageHeight));

            imageBox.addView(imageView);

            RelativeLayout courseBar = new RelativeLayout(parent.getContext());
            courseBar.setGravity(Gravity.CENTER_VERTICAL + Gravity.CENTER_HORIZONTAL);
            Simple.setSizeDip(courseBar, Simple.MP, Simple.MP);
            imageBox.addView(courseBar);

            courseView = new ImageView(parent.getContext());
            courseView.setId(android.R.id.icon);
            courseView.setImageResource(Screens.getCourseMarkerRes());
            courseView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Simple.setSizeDip(courseView, Simple.WC, Defines.COURSE_ICON_SIZE);

            courseBar.addView(courseView);

            RelativeLayout overlayBar = new RelativeLayout(parent.getContext());
            overlayBar.setGravity(Gravity.END);
            Simple.setSizeDip(overlayBar, Simple.MP, Simple.WC);
            Simple.setPaddingDip(overlayBar, Defines.PADDING_SMALL);

            imageBox.addView(overlayBar);

            ownedView = new TextView(parent.getContext());
            ownedView.setId(android.R.id.icon1);
            ownedView.setText(R.string.asset_owned);
            ownedView.setAllCaps(true);
            ownedView.setTextColor(Color.BLACK);
            ownedView.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), Defines.GOTHAM_BOLD));
            Simple.setSizeDip(ownedView, Simple.WC, Simple.WC);
            Simple.setTextSizeDip(ownedView, Defines.FS_ASSET_OWNED);
            Simple.setRoundedCorners(ownedView, Defines.CORNER_RADIUS_OVERLAY, Color.WHITE, true);

            Simple.setPaddingDip(ownedView,
                    Defines.PADDING_NORMAL, Defines.PADDING_TINY,
                    Defines.PADDING_NORMAL, Defines.PADDING_TINY);

            overlayBar.addView(ownedView);

            readView = new ImageView(parent.getContext());
            readView.setId(android.R.id.icon2);
            readView.setImageResource(Screens.getReadMarkerRes());
            readView.setScaleType(ImageView.ScaleType.FIT_END);
            Simple.setSizeDip(readView, Simple.MP, Defines.READ_ICON_SIZE + (Defines.PADDING_SMALL * 2));
            Simple.setPaddingDip(readView, Defines.PADDING_SMALL);

            imageBox.addView(readView);

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

        final JSONObject asset = (JSONObject) getItem(position % assets.length());

        int id = Json.getInt(asset, "id");
        String title = Json.getString(asset, "title");
        String subtitle = Json.getString(asset, "sub_title");
        String thumburl = Json.getString(asset, "thumbnail_url");

        boolean isCourse = Json.getBoolean(asset, "_isCourse");

        imageView.setImageDrawable(
                AssetsImageManager.getDrawableOrFetch(
                        parent.getContext(),
                        imageView, thumburl,
                        imageWidth, imageHeight, true));

        titleView.setText(title);
        summaryView.setText(subtitle);

        ownedView.setVisibility(View.GONE);
        readView.setVisibility(View.GONE);

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

            if (Globals.coursesBought.get(id, false))
            {
                ownedView.setVisibility(View.VISIBLE);
            }
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

            if (Globals.contentsBought.get(id, false))
            {
                ownedView.setVisibility(View.VISIBLE);
            }
        }

        return assetFrame;
    }

    protected void openCourse(ViewGroup parent, JSONObject course)
    {
        Log.d(LOGTAG,"openCourse....");

        Globals.displayContent = course;

        Simple.startActivity(parent.getContext(), CourseActivity.class);
    }

    protected void openContent(ViewGroup parent, JSONObject content)
    {
        Log.d(LOGTAG,"openContent....");

        Globals.displayContent = content;

        Simple.startActivity(parent.getContext(), DetailActivity.class);
    }
}
