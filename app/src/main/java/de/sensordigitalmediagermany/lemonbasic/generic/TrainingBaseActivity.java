package de.sensordigitalmediagermany.lemonbasic.generic;

import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;

public class TrainingBaseActivity extends ContentBaseActivity
{
    private static final String LOGTAG = TrainingBaseActivity.class.getSimpleName();

    protected TextView titleView;
    protected TextView info1View;
    protected TextView courseView;
    protected TextView info2View;
    protected RelativeLayout contentCenter;
    protected TextView actionButton;
    protected TextView cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        naviFrame.setOrientation(LinearLayout.VERTICAL);
        naviFrame.setBackgroundColor(Defines.COLOR_SENSOR_LTBLUE);
        Simple.setSizeDip(naviFrame, Simple.MP, Simple.MP);
        Simple.setPaddingDip(naviFrame, Defines.PADDING_TRAINING);

        Simple.setPaddingDip(naviFrame,
                Defines.PADDING_XLARGE, Defines.PADDING_NORMAL,
                Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);

        String courseTitle = Json.getString(Globals.displayContent, "title");

        titleView = new TextView(this);
        titleView.setAllCaps(true);
        titleView.setTextColor(Color.WHITE);
        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(titleView, Defines.FS_TRAINING_TITLE);
        Simple.setSizeDip(titleView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(titleView, Defines.PADDING_TRAINING);

        naviFrame.addView(titleView);

        info1View = new TextView(this);
        info1View.setTextColor(Color.WHITE);
        info1View.setGravity(Gravity.CENTER_HORIZONTAL);
        info1View.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAMNARROW_LIGHT));
        Simple.setTextSizeDip(info1View, Defines.FS_TRAINING_INFO);
        Simple.setSizeDip(info1View, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(info1View, Defines.PADDING_TRAINING);
        Simple.setPaddingDip(info1View, Defines.PADDING_TRAINING, 0, Defines.PADDING_TRAINING, 0);

        naviFrame.addView(info1View);

        courseView = new TextView(this);
        courseView.setVisibility(View.GONE);
        courseView.setText(courseTitle);
        courseView.setTextColor(Color.WHITE);
        courseView.setGravity(Gravity.CENTER_HORIZONTAL);
        courseView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(courseView, Defines.FS_TRAINING_INFO);
        Simple.setSizeDip(courseView, Simple.MP, Simple.WC);
        Simple.setPaddingDip(courseView, Defines.PADDING_TRAINING, 0, Defines.PADDING_TRAINING, 0);

        naviFrame.addView(courseView);

        info2View = new TextView(this);
        info2View.setTextColor(Color.WHITE);
        info2View.setGravity(Gravity.CENTER_HORIZONTAL);
        info2View.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAMNARROW_LIGHT));
        Simple.setTextSizeDip(info2View, Defines.FS_TRAINING_INFO);
        Simple.setSizeDip(info2View, Simple.MP, Simple.WC);
        Simple.setPaddingDip(info2View, Defines.PADDING_TRAINING, 0, Defines.PADDING_TRAINING, 0);

        naviFrame.addView(info2View);

        contentCenter = new RelativeLayout(this);
        contentCenter.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(contentCenter, Simple.MP, Simple.WC);

        naviFrame.addView(contentCenter);

        RelativeLayout startCenter = new RelativeLayout(this);
        startCenter.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(startCenter, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(startCenter, Defines.PADDING_TRAINING);

        naviFrame.addView(startCenter);

        actionButton = new TextView(this);
        actionButton.setAllCaps(true);
        actionButton.setTextColor(Color.BLACK);
        actionButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        actionButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(actionButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(actionButton, Defines.FS_TRAINING_START);
        Simple.setRoundedCorners(actionButton, Defines.CORNER_RADIUS_BIGBUT, Color.WHITE, true);

        Simple.setPaddingDip(actionButton,
                Defines.PADDING_TRAINING, Defines.PADDING_NORMAL,
                Defines.PADDING_TRAINING, Defines.PADDING_NORMAL);

        startCenter.addView(actionButton);

        RelativeLayout cancelCenter = new RelativeLayout(this);
        cancelCenter.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(cancelCenter, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(cancelCenter, Defines.PADDING_LARGE);

        naviFrame.addView(cancelCenter);

        cancelButton = new TextView(this);
        cancelButton.setVisibility(View.GONE);
        cancelButton.setText(R.string.button_cancel);
        cancelButton.setAllCaps(true);
        cancelButton.setTextColor(Color.WHITE);
        cancelButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        cancelButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(cancelButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(cancelButton, Defines.FS_DIALOG_BUTTON);
        Simple.setRoundedCorners(cancelButton, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_LTBLUE, true);

        Simple.setPaddingDip(cancelButton,
                Defines.PADDING_XLARGE, Defines.PADDING_SMALL,
                Defines.PADDING_XLARGE, Defines.PADDING_SMALL);

        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });

        cancelCenter.addView(cancelButton);
    }
}
