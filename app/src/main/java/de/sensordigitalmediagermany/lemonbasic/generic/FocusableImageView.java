package de.sensordigitalmediagermany.lemonbasic.generic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class FocusableImageView extends ImageView
{
    private static final String LOGTAG = FocusableImageView.class.getSimpleName();

    public FocusableImageView(Context context)
    {
        super(context);
        setPadding(1,1,1,1);

        setOnFocusChangeListener(new OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasfocus)
            {
                if (hasfocus)
                {
                    Simple.setRoundedCorners(view, 0, Color.TRANSPARENT, Color.YELLOW);
                }
                else
                {
                    setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        Log.d(LOGTAG, "onMeasure:"
                + " width=" + MeasureSpec.getSize(widthMeasureSpec)
                + " height=" + MeasureSpec.getSize(heightMeasureSpec)
        );

        //noinspection SuspiciousNameCombination
        widthMeasureSpec = heightMeasureSpec;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
