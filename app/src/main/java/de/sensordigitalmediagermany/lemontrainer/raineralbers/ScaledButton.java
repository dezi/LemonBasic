package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.media.Ringtone;
import android.widget.RelativeLayout;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;

public class ScaledButton extends RelativeLayout
{
    private static final String LOGTAG = ScaledButton.class.getSimpleName();

    protected Runnable onButtonClicked;

    public ScaledButton(Context context)
    {
        super(context);
    }

    public void setContent(Rect rect, int imageresid)
    {
        setLayoutParams(Simple.getScaledLayout(getContext(), rect, imageresid));

        setOnTouchListener(onButtonTouch);
        setOnClickListener(onButtonClick);
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
                Simple.setRoundedCorners(view, Defines.BUTTON_CORNER_RADIUS, Defines.COLOR_BUTTON_TOUCHED, true);
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
