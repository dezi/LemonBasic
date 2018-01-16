package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;

public class CourseActivity extends ContentBaseActivity
{
    private static final String LOGTAG = CourseActivity.class.getSimpleName();

    protected TextView buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.d(LOGTAG, "onCreate: course=" + Json.toPretty(Globals.displayContent));

        naviFrame.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(naviFrame, Simple.MP, Simple.WC);

        Simple.setPaddingDip(naviFrame,
                Defines.PADDING_XLARGE, Defines.PADDING_NORMAL,
                Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);

        setBackButton(true);

        if (Globals.displayContent == null) return;

        String courseTitle = Json.getString(Globals.displayContent, "title");
        String courseInfo = Json.getString(Globals.displayContent, "sub_title");
        String courseHeader = Json.getString(Globals.displayContent, "description_header");
        String courseDescription = Json.getString(Globals.displayContent, "description");
        String detailUrl = Json.getString(Globals.displayContent, "detail_image_url");

        if ((courseHeader == null) || courseHeader.isEmpty())
        {
            courseHeader = "Hier fehlt der Text in der Datenbank. Gruss Dennis";
        }

        //
        // Setup navigation path.
        //

        if (navigationButton != null)
        {
            String navipath = "";

            if (Globals.category != null)
            {
                navipath = Globals.category + " | ";
            }

            navipath += courseTitle;

            navigationButton.setButtonText(Defines.PADDING_TINY, navipath);
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
        ctView.setAllCaps(true);
        ctView.setTextColor(Defines.COLOR_SENSOR_LTBLUE);
        ctView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setTextSizeDip(ctView, Defines.FS_DETAIL_HEADER);
        Simple.setSizeDip(ctView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(ctView, Defines.PADDING_SMALL);

        titleArea.addView(ctView);

        TextView ciView = new TextView(this);
        ciView.setText(courseInfo);
        ciView.setTextColor(Color.BLACK);
        ciView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.ROONEY_REGULAR));
        Simple.setTextSizeDip(ciView, Defines.FS_DETAIL_SUBHEAD);
        Simple.setSizeDip(ciView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(ciView, Defines.PADDING_TINY);

        titleArea.addView(ciView);

        LinearLayout infoAndButtonArea = new LinearLayout(this);
        infoAndButtonArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(infoAndButtonArea, Simple.MP, Simple.WC);

        naviFrame.addView(infoAndButtonArea);

        LinearLayout infoArea = new LinearLayout(this);
        infoArea.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(infoArea, Simple.MP, Simple.WC);

        infoAndButtonArea.addView(infoArea);

        TextView chView = new TextView(this);
        chView.setText(courseHeader);
        chView.setTextColor(Color.BLACK);
        chView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        chView.setAllCaps(Defines.isInfosAllCaps);
        Simple.setTextSizeDip(chView, Defines.FS_DETAIL_TITLE);
        Simple.setSizeDip(chView, Simple.MP, Simple.WC);

        infoArea.addView(chView);

        TextView cdView = new TextView(this);
        cdView.setText(courseDescription);
        cdView.setTextColor(Color.BLACK);
        cdView.setMinLines(2);
        cdView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.ROONEY_LIGHT));
        Simple.setTextSizeDip(cdView, Defines.FS_DETAIL_INFOS);
        Simple.setSizeDip(cdView, Simple.WC, Simple.MP, 1.0f);
        Simple.setMarginTopDip(cdView, Defines.PADDING_NORMAL);

        infoArea.addView(cdView);

        if (Defines.isCompactDetails)
        {
            imageFrame.addView(titleArea);
        }
        else
        {
            naviFrame.addView(titleArea);
        }

        buyButton = new TextView(this);
        buyButton.setTextColor(Color.WHITE);
        buyButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(buyButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(buyButton, Defines.FS_DIALOG_BUTTON);
        Simple.setRoundedCorners(buyButton, Defines.CORNER_RADIUS_BIGBUT, Color.BLACK, true);

        Simple.setPaddingDip(buyButton,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL);

        infoAndButtonArea.addView(buyButton);

        if (Defines.isGiveAway)
        {
            buyButton.setVisibility(View.GONE);
        }

        JSONArray cc = Json.getArray(Globals.displayContent, "_cc");

        assetsAdapter.setAssets(cc);

        updateContent();
    }

    public void updateContent()
    {
        int courseId = Json.getInt(Globals.displayContent, "id");
        int price = Json.getInt(Globals.displayContent, "price");
        boolean bought = ContentHandler.isCourseBought(courseId);

        String buyText = (price > 0)
                ? Simple.getTrans(this, R.string.course_buy_price, String.valueOf(price))
                : Simple.getTrans(this, R.string.course_buy_gratis);

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
        }
        else
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

        buyButton.setText(buyText);
    }
}
