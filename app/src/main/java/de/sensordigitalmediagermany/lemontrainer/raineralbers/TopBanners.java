package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.annotation.SuppressLint;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.content.Context;
import android.view.MotionEvent;
import android.view.Gravity;
import android.view.View;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class TopBanners extends FrameLayout
{
    private static final String LOGTAG = TopBanners.class.getSimpleName();

    private HorizontalScrollView scrollView;
    private ImageView arrowLeftIcon;
    private ImageView arrowRightIcon;

    private int bannerWidth;
    private int bannerHeight;
    private int xDirTouch;
    private int xLastTouch;

    private JSONArray assets;

    public TopBanners(Context context)
    {
        super(context);
    }

    @SuppressLint("RtlHardcoded")
    public void setAssets(View rootview, JSONArray assets)
    {
        this.assets = assets;

        if ((assets == null) || (assets.length() == 0)) return;

        setBackgroundColor(0xcccccccc);

        //
        // Adjust view to aspect height.
        //

        int screenWidth = rootview.getLayoutParams().width;

        bannerWidth = screenWidth - (Simple.dipToPx(Defines.PADDING_SMALL) * 4);
        bannerHeight = Math.round(bannerWidth / Defines.ASSET_BANNER_ASPECT);

        Simple.setSizeDip(this, Simple.pxToDip(bannerWidth), Simple.pxToDip(bannerHeight));
        Simple.setMarginLeftDip(this, Defines.PADDING_SMALL);
        Simple.setMarginRightDip(this, Defines.PADDING_SMALL);
        Simple.setMarginBottomDip(this, Defines.PADDING_LARGE);

        //
        // Add scroll views.
        //

        scrollView = new HorizontalScrollView(getContext())
        {
            @Override
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouchEvent(MotionEvent motionEvent)
            {
                onTouchEventCustom(motionEvent);

                return super.onTouchEvent(motionEvent);
            }
        };

        Simple.setSizeDip(scrollView, Simple.MP, Simple.MP);

        scrollView.setBackgroundColor(0x88880000);
        addView(scrollView);

        LinearLayout scrollContent = new LinearLayout(getContext());
        scrollContent.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(scrollContent, Simple.WC, Simple.MP);

        scrollView.addView(scrollContent);

        //
        // Add control arrows.
        //

        int arrowPadding = Defines.PADDING_SMALL;
        int arrowWidth = Defines.BANNER_ARROW_WIDTH + (arrowPadding * 2);

        arrowLeftIcon = new ImageView(getContext());
        arrowLeftIcon.setVisibility(INVISIBLE);
        arrowLeftIcon.setImageResource(DefinesScreens.getArrowBannerLeftRes());
        arrowLeftIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Simple.setSizeDip(arrowLeftIcon, arrowWidth, Simple.MP);
        Simple.setPaddingDip(arrowLeftIcon, arrowPadding);

        arrowLeftIcon.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickLeftArrow();
            }
        });

        addView(arrowLeftIcon, new LayoutParams(Simple.dipToPx(arrowWidth), Simple.MP, Gravity.LEFT));

        arrowRightIcon = new ImageView(getContext());
        arrowRightIcon.setVisibility(INVISIBLE);
        arrowRightIcon.setImageResource(DefinesScreens.getArrowBannerRightRes());
        arrowRightIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Simple.setSizeDip(arrowRightIcon, arrowWidth, Simple.MP);
        Simple.setPaddingDip(arrowRightIcon, arrowPadding);

        arrowRightIcon.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickRightArrow();
            }
        });

        addView(arrowRightIcon, new LayoutParams(Simple.dipToPx(arrowWidth), Simple.MP, Gravity.RIGHT));

        for (int inx = 0; inx < assets.length(); inx++)
        {
            JSONObject asset = Json.getObject(assets, inx);
            if (asset == null) continue;

            TopBannerImage bannerImage = new TopBannerImage(getContext(), bannerWidth, bannerHeight);
            bannerImage.setAsset(asset);

            scrollContent.addView(bannerImage);
        }

        adjustArrows.run();
    }

    private void onTouchEventCustom(MotionEvent motionEvent)
    {
        int xscreen = (int) motionEvent.getRawX();

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
        {
            xDirTouch = 0;
            xLastTouch = xscreen;
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE)
        {
            xDirTouch = (xLastTouch > xscreen) ? -1 : 1;
            xLastTouch = xscreen;
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_UP)
        {
            getHandler().post(onTouchEventCustomEnded);
        }
    }

    private final Runnable onTouchEventCustomEnded = new Runnable()
    {
        @Override
        public void run()
        {
            int xoffset = scrollView.getScrollX();
            int leftrest = xoffset % bannerWidth;
            int rightrest = bannerWidth - leftrest;

            scrollView.smoothScrollBy((xDirTouch > 0) ? -leftrest : rightrest, 0);

            getHandler().postDelayed(adjustArrows, 300);
        }
    };

    private void onClickRightArrow()
    {
        int current = scrollView.getScrollX() / bannerWidth;

        if (current < (assets.length() - 1))
        {
            scrollView.smoothScrollBy(bannerWidth, 0);

            getHandler().postDelayed(adjustArrows, 300);
        }
    }

    private void onClickLeftArrow()
    {
        int current = scrollView.getScrollX() / bannerWidth;

        if (current > 0)
        {
            scrollView.smoothScrollBy(-bannerWidth, 0);

            getHandler().postDelayed(adjustArrows, 300);
        }
    }

    private final Runnable adjustArrows = new Runnable()
    {
        @Override
        public void run()
        {
            int current = scrollView.getScrollX() / bannerWidth;

            Log.d(LOGTAG,"adjustArrows: current=" + current);

            arrowLeftIcon.setVisibility((current > 0) ? VISIBLE : INVISIBLE);
            arrowRightIcon.setVisibility((current < (assets.length() - 1)) ? VISIBLE : INVISIBLE);
        }
    };
}
