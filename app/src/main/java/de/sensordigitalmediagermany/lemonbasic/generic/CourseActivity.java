package de.sensordigitalmediagermany.lemonbasic.generic;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;

public class CourseActivity extends ContentBaseActivity
{
    private static final String LOGTAG = CourseActivity.class.getSimpleName();

    protected RelativeLayout buyButtonCenter;
    protected GenericButton buyButton;

    protected DownloadAllManager downloadAllManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Log.d(LOGTAG, "onCreate: course=" + Json.toPretty(Globals.displayContent));

        naviFrame.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(naviFrame, Simple.MP, Simple.WC);

        setBackButton(true);

        if (Globals.displayContent == null) return;

        Typeface headerTF = Typeface.createFromAsset(getAssets(), Defines.FONT_DETAILS_HEADER);
        Typeface subheadTF = Typeface.createFromAsset(getAssets(), Defines.FONT_DETAILS_SUBHEAD);
        Typeface titleTF = Typeface.createFromAsset(getAssets(), Defines.FONT_DETAILS_TITLE);
        Typeface infosTF = Typeface.createFromAsset(getAssets(), Defines.FONT_DETAILS_INFOS);

        String courseTitle = Json.getString(Globals.displayContent, "title");
        String courseInfo = Json.getString(Globals.displayContent, "sub_title");
        String courseHeader = Json.getString(Globals.displayContent, "description_header");
        String courseDescription = Json.getString(Globals.displayContent, "description");
        String detailUrl = Json.getString(Globals.displayContent, "detail_image_url");

        //
        // Setup navigation path.
        //

        if (Simple.isTablet())
        {
            showNavigationPath(courseTitle, Simple.getTrans(this, R.string.course));
        }
        else
        {
            showNavigationPath(Simple.getTrans(this, R.string.course));
        }

        //region Image and type area.

        if (Defines.isCompactDetails)
        {
            int imageWidth = topFrame.getLayoutParams().width;
            int imageHeight = Math.round(imageWidth / Defines.ASSET_COURSE_ASPECT);

            Simple.setSizeDip(imageFrame, Simple.MP, Simple.pxToDip(imageHeight));
            Simple.setSizeDip(contentImage, Simple.MP, Simple.pxToDip(imageHeight));

            Simple.setPaddingDip(imageFrame,
                    Defines.PADDING_LARGE, Defines.PADDING_TINY,
                    Defines.PADDING_LARGE, 0);

            Log.d(LOGTAG, "onCreate: imageWidth=" + imageWidth + " imageHeight=" + imageHeight);

            contentImage.setImageDrawable(
                    AssetsImageManager.getDrawableOrFetch(
                            this,
                            contentImage, detailUrl,
                            imageWidth, imageHeight, false));
        }

        //endregion Image and type area.

        if (Defines.isCompactDetails)
        {
            naviFrame.setBackgroundColor(Defines.COLOR_FRAMES);

            Simple.setMarginLeftDip(naviFrame, Defines.PADDING_LARGE);
            Simple.setMarginRightDip(naviFrame, Defines.PADDING_LARGE);
        }

        LinearLayout titleArea = new LinearLayout(this);
        titleArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(titleArea, Simple.MP, Simple.WC);

        TextView ctView = new TextView(this);
        ctView.setText(courseTitle);
        ctView.setAllCaps(Defines.isInfosAllCaps);
        ctView.setTypeface(headerTF);
        Simple.setTextSizeDip(ctView, Defines.FS_DETAIL_HEADER);
        Simple.setSizeDip(ctView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(ctView, Defines.PADDING_SMALL);

        titleArea.addView(ctView);

        TextView ciView = new TextView(this);
        ciView.setText(courseInfo);
        ciView.setAllCaps(Defines.isInfosAllCaps);
        ciView.setTypeface(subheadTF);
        Simple.setTextSizeDip(ciView, Defines.FS_DETAIL_SUBHEAD);
        Simple.setSizeDip(ciView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(ciView, Defines.PADDING_TINY);

        titleArea.addView(ciView);

        LinearLayout infoAndButtonArea = new LinearLayout(this);
        Simple.setSizeDip(infoAndButtonArea, Simple.MP, Simple.WC);

        naviFrame.addView(infoAndButtonArea);

        LinearLayout infoArea = new LinearLayout(this);
        infoArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(infoArea, Simple.WC, Simple.WC, 1.0f);

        infoAndButtonArea.addView(infoArea);

        if ((courseHeader != null) && ! courseHeader.isEmpty())
        {
            TextView chView = new TextView(this);
            chView.setText(courseHeader);
            chView.setTextColor(Color.BLACK);
            chView.setTypeface(titleTF);
            chView.setAllCaps(Defines.isInfosAllCaps);
            Simple.setTextSizeDip(chView, Defines.FS_DETAIL_TITLE);
            Simple.setSizeDip(chView, Simple.MP, Simple.WC);

            infoArea.addView(chView);
        }

        TextView cdView = new TextView(this);
        cdView.setText(courseDescription);
        cdView.setTextColor(Color.BLACK);
        cdView.setTypeface(infosTF);
        cdView.setAllCaps(Defines.isInfosAllCaps);
        Simple.setTextSizeDip(cdView, Defines.FS_DETAIL_INFOS);

        infoArea.addView(cdView);

        if (Defines.isCompactDetails)
        {
            Simple.setPaddingDip(naviFrame,
                    Defines.PADDING_NORMAL, Defines.PADDING_NORMAL,
                    Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);

            LinearLayout forceDown = new LinearLayout(this);
            Simple.setSizeDip(forceDown, Simple.MP, Simple.MP, 1.0f);

            titleArea.addView(forceDown, 0);

            ctView.setTextColor(Color.WHITE);
            ciView.setTextColor(Color.WHITE);

            Simple.setSizeDip(titleArea, Simple.MP, Simple.MP);
            Simple.setPaddingDip(titleArea, Defines.PADDING_LARGE);

            imageFrame.addView(titleArea);

            Simple.setSizeDip(cdView, Simple.WC, Simple.WC);
        }
        else
        {
            Simple.setPaddingDip(naviFrame,
                    Defines.PADDING_XLARGE, Defines.PADDING_NORMAL,
                    Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);

            ctView.setTextColor(Defines.COLOR_SENSOR_LTBLUE);
            ciView.setTextColor(Color.BLACK);

            naviFrame.addView(titleArea);

            Simple.setSizeDip(cdView, Simple.WC, Simple.MP, 1.0f);
            Simple.setMarginTopDip(cdView, Defines.PADDING_NORMAL);
        }

        buyButtonCenter = new RelativeLayout(this);
        buyButtonCenter.setGravity(RelativeLayout.CENTER_HORIZONTAL + RelativeLayout.CENTER_VERTICAL);
        buyButtonCenter.setVisibility(View.GONE);

        if (Simple.isTablet())
        {
            cdView.setMinLines(3);
            infoAndButtonArea.setOrientation(LinearLayout.HORIZONTAL);
            Simple.setSizeDip(buyButtonCenter, Simple.WC, Simple.MP);
        }
        else
        {
            infoAndButtonArea.setOrientation(LinearLayout.VERTICAL);
            Simple.setSizeDip(buyButtonCenter, Simple.MP, Simple.WC);
        }

        infoAndButtonArea.addView(buyButtonCenter);

        buyButton = new GenericButton(this);

        if (Simple.isTablet())
        {
            Simple.setMarginLeftDip(buyButtonCenter, Defines.PADDING_LARGE);
            buyButton.setFullWidth(false);
        }
        else
        {
            Simple.setMarginTopDip(buyButtonCenter, Defines.PADDING_LARGE);
            buyButton.setFullWidth(true);
        }

        buyButtonCenter.addView(buyButton);

        JSONArray cc = Json.getArray(Globals.displayContent, "_cc");

        assetsAdapter.setAssets(cc);

        updateContent();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        Log.d(LOGTAG, "onPause...");

        if (downloadAllManager != null)
        {
            downloadAllManager.requestCancel();
            downloadAllManager = null;
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        Log.d(LOGTAG, "onStop...");
    }

    @Override
    public void updateContent()
    {
        int courseId = Json.getInt(Globals.displayContent, "id");
        int price = Json.getInt(Globals.displayContent, "price");
        boolean bought = ContentHandler.isCourseBought(courseId);
        boolean loadme = ContentHandler.isOutdatedOrNotCachedContent(Globals.displayContent);

        String buyText = (price > 0)
                ? Simple.getTrans(this, R.string.course_buy_price, String.valueOf(price))
                : Simple.getTrans(this, R.string.course_buy_gratis);

        buyButtonCenter.setVisibility(View.GONE);

        if (Defines.isGiveAway)
        {
            if (loadme)
            {
                buyButtonCenter.setVisibility(View.VISIBLE);

                Simple.setPaddingDip(buyButton,
                        Defines.PADDING_XLARGE, Defines.PADDING_SMALL,
                        Defines.PADDING_XLARGE, Defines.PADDING_SMALL);

                buyText = Simple.getTrans(this, R.string.course_buy_load);

                buyButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        downloadAllManager = new DownloadAllManager();
                        downloadAllManager.askDownloadCourseContent(topFrame, Globals.displayContent);
                    }
                });
            }
        }
        else
        {
            buyButtonCenter.setVisibility(View.VISIBLE);

            Simple.setPaddingDip(buyButton,
                    Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL,
                    Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL);

            if (bought)
            {
                buyText = Simple.getTrans(this, R.string.course_buy_train);

                buyButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Simple.startActivity(CourseActivity.this, TrainingActivity.class);
                    }
                });
            } else
            {
                buyButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        topFrame.addView(new BuyContentDialog(CourseActivity.this));
                    }
                });
            }
        }

        buyButton.setText(buyText);

        assetGrid.updateContent();
    }
}
