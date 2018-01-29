package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class ViewZoomFrame extends FrameLayout
{
    private static final String LOGTAG = ViewZoomFrame.class.getSimpleName();

    protected ScrollView vertScroll;
    protected HorizontalScrollView horzScroll;
    protected LinearLayout linearLayout;
    protected ScaleListener scaleListener;
    protected ScaleGestureDetector scaleDetector;

    protected float zoomFactor = 1.0f;

    public ViewZoomFrame(Context context)
    {
        super(context);

        setBackgroundColor(Color.WHITE);

        scaleListener = new ScaleListener();
        scaleDetector = new ScaleGestureDetector(context, scaleListener);

        vertScroll = new ScrollView(context)
        {
            @Override
            protected void onScrollChanged(int l, int t, int oldl, int oldt)
            {
                super.onScrollChanged(l, t, oldl, oldt);

                if (! scaleListener.isInScaling())
                {
                    ViewZoomFrame.this.onVertScrollChanged();
                }
            }

            @Override
            public boolean onTouchEvent(MotionEvent ev)
            {
                scaleDetector.onTouchEvent(ev);

                return scaleListener.isInScaling() || super.onTouchEvent(ev);
            }
        };

        addView(vertScroll);

        horzScroll = new HorizontalScrollView(context)
        {
            @Override
            public boolean onTouchEvent(MotionEvent ev)
            {
                scaleDetector.onTouchEvent(ev);

                return scaleListener.isInScaling() || super.onTouchEvent(ev);
            }
        };

        vertScroll.addView(horzScroll);

        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        horzScroll.addView(linearLayout);
    }

    public void addContentView(View view)
    {
        linearLayout.addView(view);
    }

    protected void onVertScrollChanged()
    {
        //
        // To be overwritten.
        //
    }

    protected void onZoomFactorChanged(float zoomFactor)
    {
        //
        // To be overwritten.
        //
    }

    protected void onZoomEnded(float zoomFactor)
    {
        //
        // To be overwritten.
        //
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        private int scaling;

        private float initialZoom;

        private int focusX;
        private int focusY;
        private int screenX;
        private int screenY;
        private int scrollX;
        private int scrollY;

        public boolean isInScaling()
        {
            return (scaling != 0);
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector)
        {
            if (! Defines.isPDFZoomable)
            {
                return true;
            }

            if (scaling == 0)
            {
                initialZoom = zoomFactor;

                focusX = (int) detector.getFocusX();
                focusY = (int) detector.getFocusY();

                scrollX = horzScroll.getScrollX();
                scrollY = vertScroll.getScrollY();

                screenX = focusX - scrollX;
                screenY = focusY - scrollY;

                scaling = 1;

                Log.d(LOGTAG, "onScale: initial initialZoom=" + initialZoom);
                Log.d(LOGTAG, "onScale: initial focus" + " x=" + focusX + " y=" + focusY);
                Log.d(LOGTAG, "onScale: initial scroll" + " x=" + scrollX + " y=" + scrollY);
                Log.d(LOGTAG, "onScale: initial screen" + " x=" + screenX + " y=" + screenY);
            }

            if (scaling == 1)
            {
                float scaleDelta = detector.getScaleFactor();
                float oldFactor = zoomFactor;

                zoomFactor *= scaleDelta;

                if (zoomFactor < 1.0f) zoomFactor = 1.0f;
                if (zoomFactor > 2.0f) zoomFactor = 2.0f;

                //Log.d(LOGTAG, "onScale: scaleDelta=" + scaleDelta + " zoomFactor=" + zoomFactor);

                if (oldFactor != zoomFactor)
                {
                    onZoomFactorChanged(zoomFactor);

                    Log.d(LOGTAG, "onScale: initialZoom=" + initialZoom);
                    Log.d(LOGTAG, "onScale: zoomFactor=" + zoomFactor);

                    int newFocusX = (int) ((focusX / initialZoom) * zoomFactor);
                    int newFocusY = (int) ((focusY / initialZoom) * zoomFactor);

                    Log.d(LOGTAG, "onScale: focus old" + " x=" + focusX + " y=" + focusY);
                    Log.d(LOGTAG, "onScale: focus new" + " x=" + newFocusX + " y=" + newFocusY);

                    int newScrollX = newFocusX - screenX;
                    int newScrollY = newFocusY - screenY;

                    horzScroll.setScrollX(newScrollX);
                    vertScroll.setScrollY(newScrollY);

                    Log.d(LOGTAG, "onScale: scroll old" + " x=" + scrollX + " y=" + scrollY);
                    Log.d(LOGTAG, "onScale: scroll new" + " x=" + newScrollX + " y=" + newScrollY);
                }

                getHandler().removeCallbacks(terminateScale);
                getHandler().postDelayed(terminateScale, 100);
            }

            return true;
        }

        private final Runnable terminateScale = new Runnable()
        {
            @Override
            public void run()
            {
                scaling = 0;

                onZoomEnded(zoomFactor);
            }
        };
    }
}