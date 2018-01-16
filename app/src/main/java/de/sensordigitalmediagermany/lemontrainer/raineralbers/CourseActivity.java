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
        String courseHeader = Json.getString(Globals.displayContent, "description_header");
        String courseDescription = Json.getString(Globals.displayContent, "description");

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

        TextView ctView = new TextView(this);
        ctView.setText(courseTitle);
        ctView.setAllCaps(true);
        ctView.setTextColor(Defines.COLOR_SENSOR_LTBLUE);
        ctView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setTextSizeDip(ctView, Defines.FS_DETAIL_HEADER);
        Simple.setSizeDip(ctView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(ctView, Defines.PADDING_SMALL);

        naviFrame.addView(ctView);

        TextView chView = new TextView(this);
        chView.setText(courseHeader);
        chView.setTextColor(Color.BLACK);
        chView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.ROONEY_REGULAR));
        Simple.setTextSizeDip(chView, Defines.FS_DETAIL_SUBHEAD);
        Simple.setSizeDip(chView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(chView, Defines.PADDING_TINY);

        naviFrame.addView(chView);

        LinearLayout infoArea = new LinearLayout(this);
        infoArea.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(chView, Simple.MP, Simple.WC);

        naviFrame.addView(infoArea);

        TextView cdView = new TextView(this);
        cdView.setText(courseDescription);
        cdView.setTextColor(Color.BLACK);
        cdView.setMinLines(2);
        cdView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.ROONEY_LIGHT));
        Simple.setTextSizeDip(cdView, Defines.FS_DETAIL_INFOS);
        Simple.setSizeDip(cdView, Simple.WC, Simple.MP, 1.0f);
        Simple.setMarginTopDip(cdView, Defines.PADDING_NORMAL);

        infoArea.addView(cdView);

        buyButton = new TextView(this);
        buyButton.setTextColor(Color.WHITE);
        buyButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(buyButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(buyButton, Defines.FS_DIALOG_BUTTON);
        Simple.setRoundedCorners(buyButton, Defines.CORNER_RADIUS_BIGBUT, Color.BLACK, true);

        Simple.setPaddingDip(buyButton,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL,
                Defines.PADDING_XLARGE * 2, Defines.PADDING_SMALL);


        infoArea.addView(buyButton);

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
