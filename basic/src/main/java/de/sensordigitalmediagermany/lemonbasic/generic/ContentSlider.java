package de.sensordigitalmediagermany.lemonbasic.generic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class ContentSlider extends LinearLayout
{
    private static final String LOGTAG = ContentSlider.class.getSimpleName();

    private HorizontalScrollView scrollView;
    private LinearLayout scrollContent;
    private TextView leftButton;

    private int sliderWidth;
    private int cellwidth;
    private int imageWidth;
    private int imageHeight;

    private int xDirTouch;
    private int xLastTouch;

    private String category;
    private JSONArray assets;

    public ContentSlider(Context context)
    {
        super(context);
    }

    @SuppressLint("RtlHardcoded")
    public ContentSlider(Context context, ViewGroup rootview)
    {
        super(context);

        Typeface catFont = Typeface.createFromAsset(getContext().getAssets(),Defines.FONT_SLIDER_CAT);
        Typeface allFont = Typeface.createFromAsset(getContext().getAssets(),Defines.FONT_SLIDER_ALL);

        int screenWidth = rootview.getLayoutParams().width;
        sliderWidth = screenWidth - (Simple.dipToPx(Defines.PADDING_SMALL) * 4);

        setOrientation(VERTICAL);

        Simple.setSizeDip(this, Simple.pxToDip(sliderWidth), Simple.WC);
        Simple.setMarginBottomDip(this, Defines.PADDING_LARGE);

        if (Defines.isTopBannerFull)
        {
            Simple.setMarginLeftDip(this, Defines.PADDING_SMALL * 2);
            Simple.setMarginRightDip(this, Defines.PADDING_SMALL * 2);
        }
        else
        {
            Simple.setMarginLeftDip(this, Defines.PADDING_SMALL);
            Simple.setMarginRightDip(this, Defines.PADDING_SMALL);
        }

        LinearLayout buttonLayout = new LinearLayout(context);
        buttonLayout.setOrientation(HORIZONTAL);
        Simple.setSizeDip(buttonLayout, Simple.MP, Simple.WC);

        addView(buttonLayout);

        RelativeLayout leftButtonBox = new RelativeLayout(context);
        leftButtonBox.setGravity(Gravity.LEFT);
        Simple.setSizeDip(leftButtonBox, Simple.WC, Simple.WC, 1.0f);

        buttonLayout.addView(leftButtonBox);

        RelativeLayout rightButtonBox = new RelativeLayout(context);
        rightButtonBox.setGravity(Gravity.RIGHT + Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(rightButtonBox, Simple.WC, Simple.MP);

        buttonLayout.addView(rightButtonBox);

        leftButton = new TextView(context);
        leftButton.setSingleLine(true);
        leftButton.setAllCaps(Defines.isInfosAllCaps || Defines.isSliderAllCaps);
        leftButton.setTypeface(catFont);
        leftButton.setTextColor(Defines.COLOR_CATEGORY_HEAD);
        Simple.setTextSizeDip(leftButton, Defines.FS_SLIDER_CATEGORY);
        Simple.setPaddingDip(leftButton, Defines.PADDING_TINY);

        leftButtonBox.addView(leftButton);

        GenericText rightButton = new GenericText(context)
        {
            @Override
            public int getFontSize()
            {
                return Defines.FS_SLIDER_SHOWMORE;
            }

            @Override
            public String getFontName()
            {
                return Defines.FONT_SLIDER_ALL;
            }
        };

        rightButton.setText(R.string.slider_more_button);
        rightButton.setFullWidth(false);
        rightButton.setSingleLine(true);
        rightButton.setAllCaps(Defines.isInfosAllCaps|| Defines.isSliderAllCaps);
        rightButton.setTextColor(Defines.COLOR_BUTTON_TEXT);
        Simple.setPaddingDip(rightButton, Defines.PADDING_TINY);

        rightButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Globals.category = category;
                Globals.categoryContents = assets;

                Simple.startActivity(getContext(), CategoryActivity.class);
            }
        });

        rightButtonBox.addView(rightButton);

        imageWidth = sliderWidth - ((Defines.ASSETS_NUM_COLUMNS - 1) * Simple.dipToPx(Defines.PADDING_LARGE));
        imageWidth = imageWidth / Defines.ASSETS_NUM_COLUMNS;

        if (Defines.isKaysHorzScroll && ! Simple.isTablet())
        {
            //
            // Reduce width of slider image so that a fractional
            // images becomes visible at right edge.
            //

            imageWidth = imageWidth * 90 / 100;
        }

        imageHeight = Math.round(imageWidth / Defines.ASSET_THUMBNAIL_ASPECT);

        cellwidth = imageWidth + Simple.dipToPx(Defines.PADDING_LARGE);

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

        scrollView.setFocusable(false);
        //Simple.setSizeDip(scrollView, Simple.MP, Simple.pxToDip(imageHeight));
        Simple.setSizeDip(scrollView, Simple.MP, Simple.WC);
        addView(scrollView);

        scrollContent = new LinearLayout(getContext());
        scrollContent.setOrientation(LinearLayout.HORIZONTAL);
        //Simple.setSizeDip(scrollContent, Simple.WC, Simple.MP);
        Simple.setSizeDip(scrollContent, Simple.WC, Simple.WC);

        scrollView.addView(scrollContent);
    }

    public void setAssets(String category, JSONArray assets)
    {
        this.category = category;
        this.assets = assets;

        leftButton.setText(category);

        if ((assets == null) || (assets.length() == 0)) return;

        scrollContent.removeAllViews();

        for (int inx = 0; inx < assets.length(); inx++)
        {
            JSONObject asset = Json.getObject(assets, inx);
            if (asset == null) continue;

            FrameLayout imageCell = new FrameLayout(getContext());
            //Simple.setSizeDip(imageCell, Simple.pxToDip(imageWidth), Simple.pxToDip(imageHeight));
            Simple.setSizeDip(imageCell, Simple.pxToDip(imageWidth), Simple.WC);
            if (inx > 0) Simple.setMarginLeftDip(imageCell, Defines.PADDING_LARGE);

            scrollContent.addView(imageCell);

            AssetFrame sliderImage = new AssetFrame(getContext());
            sliderImage.setAsset(imageWidth, asset);

            imageCell.addView(sliderImage);

            if ((inx + 1) > Defines.SLIDER_MAX_COLUMNS) break;
        }
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
            int leftrest = xoffset % cellwidth;
            int rightrest = cellwidth - leftrest;

            Log.d(LOGTAG, "onTouchEventCustomEnded: xoffset=" + xoffset + " leftrest=" + leftrest + " rightrest" + rightrest);

            scrollView.smoothScrollBy((xDirTouch > 0) ? -leftrest : rightrest, 0);
        }
    };

    public void updateContent()
    {
        for (int inx = 0; inx < scrollContent.getChildCount(); inx++)
        {
            View imageCell = scrollContent.getChildAt(inx);
            if (!(imageCell instanceof ViewGroup)) continue;

            ViewGroup imageCellVG = (ViewGroup) imageCell;
            if (imageCellVG.getChildCount() == 0) continue;

            View assetFrame = imageCellVG.getChildAt(0);
            if (!(assetFrame instanceof AssetFrame)) continue;

            ((AssetFrame) assetFrame).updateContent();
        }
    }
}
