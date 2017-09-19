package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;

public class ScaledButton extends RelativeLayout
{
    private static final String LOGTAG = ScaledButton.class.getSimpleName();

    protected TextView buttonText;
    protected Runnable onButtonClicked;

    public ScaledButton(Context context)
    {
        super(context);

        buttonText = new TextView(getContext());
        buttonText.setTextColor(Color.WHITE);
        buttonText.setGravity(Gravity.CENTER_VERTICAL);
        buttonText.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Defines.ROONEY_MEDIUM));
        Simple.setSizeDip(buttonText, Simple.MP, Simple.MP);
        Simple.setTextSizeDip(buttonText, Defines.FS_BUTTON_IMAGE);

        addView(buttonText);
    }

    public void setContent(View parent, Rect rect, int imageresid)
    {
        setLayoutParams(Simple.getScaledLayout(parent, rect, imageresid));

        setOnTouchListener(onButtonTouch);
        setOnClickListener(onButtonClick);
    }

    public void setContentHorz(View parent, Rect rect, int imageresid)
    {
        setLayoutParams(Simple.getScaledHorzLayout(parent, rect, imageresid));

        setOnTouchListener(onButtonTouch);
        setOnClickListener(onButtonClick);
    }

    public void setButtonText(int padleft, int textresid)
    {
        Simple.setPaddingDip(buttonText, padleft, 0, 0, 0);

        buttonText.setText(textresid);
    }

    public void setButtonText(int padleft, String text)
    {
        Simple.setPaddingDip(buttonText, padleft, 0, 0, 0);

        buttonText.setText(text);
    }

    public void setOnButtonClicked(Runnable onButtonClicked)
    {
        this.onButtonClicked = onButtonClicked;
    }

    private final View.OnTouchListener onButtonTouch = new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View view, MotionEvent event)
        {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                Simple.setRoundedCorners(view, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_TOUCHED, true);
            }

            if ((event.getAction() == MotionEvent.ACTION_UP) || (event.getAction() == MotionEvent.ACTION_CANCEL))
            {
                view.setBackground(null);
            }

            return false;
        }
    };

    private final View.OnClickListener onButtonClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if (onButtonClicked != null) onButtonClicked.run();
        }
    };
}
