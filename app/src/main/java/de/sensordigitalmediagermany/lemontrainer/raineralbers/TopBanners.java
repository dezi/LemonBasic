package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.json.JSONArray;

public class TopBanners extends FrameLayout
{
    private static final String LOGTAG = TopBanners.class.getSimpleName();

    private ImageView arrowLeftIcon;
    private ImageView arrowRightIcon;

    public TopBanners(Context context)
    {
        super(context);
    }

    @SuppressLint("RtlHardcoded")
    public void setAssets(View rootview, JSONArray assets)
    {
        if ((assets == null) || (assets.length() == 0)) return;

        setBackgroundColor(0xcccccccc);

        int screenWidth = rootview.getLayoutParams().width;
        int bannerHeight = Math.round(screenWidth / Defines.FS_ASSET_BANNER_ASPECT);

        int arrowPadding = Defines.PADDING_SMALL;
        int arrowWidth = Defines.BANNER_ARROW_WIDTH + (arrowPadding * 2);

        Log.d(LOGTAG, "setAssets: width=" + rootview.getWidth());

        Simple.setSizeDip(this, Simple.MP, bannerHeight);
        Simple.setMarginLeftDip(this, Defines.PADDING_LARGE);
        Simple.setMarginRightDip(this, Defines.PADDING_LARGE);

        arrowLeftIcon = new ImageView(getContext());
        arrowLeftIcon.setImageResource(Screens.getArrowBannerLeftRes());
        arrowLeftIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Simple.setSizeDip(arrowLeftIcon, arrowWidth, Simple.MP);
        Simple.setPaddingDip(arrowLeftIcon, arrowPadding);

        addView(arrowLeftIcon, new LayoutParams(arrowWidth, Simple.MP, Gravity.LEFT));

        arrowRightIcon = new ImageView(getContext());
        arrowRightIcon.setImageResource(Screens.getArrowBannerRightRes());
        arrowRightIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Simple.setSizeDip(arrowRightIcon, arrowWidth, Simple.MP);
        Simple.setPaddingDip(arrowRightIcon, arrowPadding);

        addView(arrowRightIcon, new LayoutParams(arrowWidth, Simple.MP, Gravity.RIGHT));
    }
}
