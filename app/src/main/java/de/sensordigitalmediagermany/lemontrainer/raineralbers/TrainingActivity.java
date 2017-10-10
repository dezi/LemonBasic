package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TrainingActivity extends ContentBaseActivity
{
    private static final String LOGTAG = TrainingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setBackButton(true);

        naviFrame.setOrientation(LinearLayout.VERTICAL);
        naviFrame.setBackgroundColor(Defines.COLOR_SENSOR_DKBLUE);
        Simple.setSizeDip(naviFrame, Simple.MP, Simple.MP);
        Simple.setPaddingDip(naviFrame, Defines.PADDING_TRAINING);

        Simple.setPaddingDip(naviFrame,
                Defines.PADDING_XLARGE, Defines.PADDING_NORMAL,
                Defines.PADDING_NORMAL, Defines.PADDING_NORMAL);

        String courseTitle = Json.getString(Globals.displayContent, "title");

        TextView titleView = new TextView(this);
        titleView.setText(R.string.training_title);
        titleView.setAllCaps(true);
        titleView.setTextColor(Color.WHITE);
        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(titleView, Defines.FS_TRAINING_TITLE);
        Simple.setSizeDip(titleView, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(titleView, Defines.PADDING_TRAINING);

        naviFrame.addView(titleView);

        TextView info1View = new TextView(this);
        info1View.setText(R.string.training_info_1);
        info1View.setTextColor(Color.WHITE);
        info1View.setGravity(Gravity.CENTER_HORIZONTAL);
        info1View.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAMNARROW_LIGHT));
        Simple.setTextSizeDip(info1View, Defines.FS_TRAINING_INFO);
        Simple.setSizeDip(info1View, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(info1View, Defines.PADDING_TRAINING);
        Simple.setPaddingDip(info1View, Defines.PADDING_TRAINING, 0, Defines.PADDING_TRAINING, 0);

        naviFrame.addView(info1View);

        TextView courseView = new TextView(this);
        courseView.setText(courseTitle);
        courseView.setTextColor(Color.WHITE);
        courseView.setGravity(Gravity.CENTER_HORIZONTAL);
        courseView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(courseView, Defines.FS_TRAINING_INFO);
        Simple.setSizeDip(courseView, Simple.MP, Simple.WC);
        Simple.setPaddingDip(courseView, Defines.PADDING_TRAINING, 0, Defines.PADDING_TRAINING, 0);

        naviFrame.addView(courseView);

        TextView info2View = new TextView(this);
        info2View.setText(R.string.training_info_2);
        info2View.setTextColor(Color.WHITE);
        info2View.setGravity(Gravity.CENTER_HORIZONTAL);
        info2View.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAMNARROW_LIGHT));
        Simple.setTextSizeDip(info2View, Defines.FS_TRAINING_INFO);
        Simple.setSizeDip(info2View, Simple.MP, Simple.WC);
        Simple.setPaddingDip(info2View, Defines.PADDING_TRAINING, 0, Defines.PADDING_TRAINING, 0);

        naviFrame.addView(info2View);

        RelativeLayout startCenter = new RelativeLayout(this);
        startCenter.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(startCenter, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(startCenter, Defines.PADDING_TRAINING);

        naviFrame.addView(startCenter);

        TextView startButton = new TextView(this);
        startButton.setText(R.string.training_start);
        startButton.setAllCaps(true);
        startButton.setTextColor(Color.BLACK);
        startButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        startButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(startButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(startButton, Defines.FS_TRAINING_START);
        Simple.setRoundedCorners(startButton, Defines.CORNER_RADIUS_BUTTON, Color.WHITE, true);

        Simple.setPaddingDip(startButton,
                Defines.PADDING_TRAINING, Defines.PADDING_NORMAL,
                Defines.PADDING_TRAINING, Defines.PADDING_NORMAL);

        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        startCenter.addView(startButton);

        RelativeLayout cancelCenter = new RelativeLayout(this);
        cancelCenter.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(cancelCenter, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(cancelCenter, Defines.PADDING_LARGE);

        naviFrame.addView(cancelCenter);

        TextView cancelButton = new TextView(this);
        cancelButton.setText(R.string.button_cancel);
        cancelButton.setAllCaps(true);
        cancelButton.setTextColor(Color.WHITE);
        cancelButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        cancelButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(cancelButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(cancelButton, Defines.FS_DIALOG_BUTTON);
        Simple.setRoundedCorners(cancelButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_SENSOR_LTBLUE, true);

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
