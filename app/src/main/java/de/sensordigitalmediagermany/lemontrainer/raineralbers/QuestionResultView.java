package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuestionResultView extends LinearLayout
{
    protected TextView numberView;
    protected ImageView checkView;

    protected int number;

    public QuestionResultView(Context context)
    {
        super(context);

        setOrientation(VERTICAL);
        Simple.setSizeDip(this, Simple.WC, Simple.WC);
        Simple.setPaddingDip(this, Defines.PADDING_TINY);

        int radiusdipse[] = new int[4];

        RelativeLayout numberBox = new RelativeLayout(getContext());
        Simple.setSizeDip(numberBox, Simple.WC, Simple.WC);
        Simple.setPaddingDip(numberBox, Defines.PADDING_SMALL);

        radiusdipse[0] = Defines.CORNER_RADIUS_BIGBUT;
        radiusdipse[1] = Defines.CORNER_RADIUS_BIGBUT;
        radiusdipse[2] = 0;
        radiusdipse[3] = 0;

        Simple.setRoundedCorners(numberBox, radiusdipse, Defines.COLOR_SENSOR_DKBLUE, true);

        addView(numberBox);

        numberView = new TextView(getContext());
        numberView.setSingleLine(true);
        numberView.setTextColor(Color.WHITE);
        numberView.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        numberView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(numberView, Defines.FS_OVERVIEW_NUMBER);
        Simple.setSizeDip(numberView, Defines.QUESTION_CHECK_SIZE, Defines.QUESTION_CHECK_SIZE);

        numberBox.addView(numberView);

        RelativeLayout checkBox = new RelativeLayout(getContext());
        Simple.setSizeDip(checkBox, Simple.WC, Simple.WC);
        Simple.setPaddingDip(checkBox, Defines.PADDING_SMALL);

        radiusdipse[0] = 0;
        radiusdipse[1] = 0;
        radiusdipse[2] = Defines.CORNER_RADIUS_BIGBUT;
        radiusdipse[3] = Defines.CORNER_RADIUS_BIGBUT;

        Simple.setRoundedCorners(checkBox, radiusdipse, Defines.COLOR_SENSOR_CONTENT, true);

        addView(checkBox);

        checkView = new ImageView(getContext());
        checkView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Simple.setSizeDip(checkView, Defines.QUESTION_CHECK_SIZE, Defines.QUESTION_CHECK_SIZE);

        checkBox.addView(checkView);
    }

    public void setResult(int number, boolean correct)
    {
        this.number = number;

        numberView.setText(String.valueOf(number));

        if (correct)
        {
            checkView.setImageResource(R.drawable.lem_t_iany_generic_question_check_black);
        }
        else
        {
            checkView.setImageDrawable(null);
        }
    }

    public int getResultNumber()
    {
        return number;
    }
}
