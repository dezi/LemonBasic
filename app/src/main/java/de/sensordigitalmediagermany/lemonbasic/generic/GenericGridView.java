package de.sensordigitalmediagermany.lemonbasic.generic;

import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class GenericGridView extends ScrollView implements GenericFocus
{
    private static final String LOGTAG = GenericGridView.class.getSimpleName();

    private FrameLayout contentView;
    private View focusedChild;
    private BaseAdapter adapter;

    private int horizontalSpacing;
    private int verticalSpacing;
    private int numColumns;

    private boolean dirty;

    private boolean focusable = false;
    private int backgroundColor = Color.TRANSPARENT;

    public GenericGridView(Context context)
    {
        super(context);

        contentView = new FrameLayout(context);
        Simple.setSizeDip(contentView, Simple.MP, Simple.WC);

        addView(contentView);
    }

    @Override
    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();

        if (focusedChild != null)
        {
            focusedChild.requestFocus();
        }
    }

    @Override
    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
    }

    private final Map<Object, View> views = new HashMap<>();
    private final Map<Object, OnFocusChangeListener> focse = new HashMap<>();

    public void buildContent()
    {
        Log.d(LOGTAG, "buildContent: start...");

        contentView.removeAllViews();

        int itemcount = adapter.getCount();

        for (int inx = 0; inx < itemcount; inx++)
        {
            Object item = adapter.getItem(inx);
            View view = views.get(item);

            if (view == null)
            {
                view = adapter.getView(inx, null, this);

                MarginLayoutParams lp = new MarginLayoutParams(Simple.MP, Simple.WC);
                view.setLayoutParams(lp);

                if (Simple.isTV())
                {
                    if (view instanceof GenericFocus)
                    {
                        GenericFocus gf = (GenericFocus) view;

                        if (gf.getOnFocusChangeListener() != Generic.genericOnFocusChangeListener)
                        {
                            focse.put(view, gf.getOnFocusChangeListener());
                        }

                        gf.setOnFocusChangeListener(onFocusChangeListener);
                    }
                }

                contentView.addView(view);
            }
        }

        Log.d(LOGTAG, "buildContent: done.");
    }

    public void positionContent()
    {
        Log.d(LOGTAG, "positionContent: start...");

        int ccount = contentView.getChildCount();

        int xpos = 0;
        int ypos = 0;

        MarginLayoutParams lp;
        View child;
        int height;

        for (int inx = 0; inx < ccount; inx++)
        {
            child = contentView.getChildAt(inx);
            height = child.getHeight();

            lp = (MarginLayoutParams) child.getLayoutParams();

            lp.leftMargin = xpos;
            lp.topMargin = ypos;

            ypos += height + verticalSpacing;
        }

        Log.d(LOGTAG, "positionContent: done.");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);

        if (changed) positionContent();
    }

    private View.OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener()
    {
        @Override
        public void onFocusChange(View view, boolean hasFocus)
        {
            if (hasFocus)
            {
                focusedChild = view;
            }

            if (view instanceof GenericFocus)
            {
                OnFocusChangeListener fcl = focse.get(view);

                if (fcl != null)
                {
                    fcl.onFocusChange(view, hasFocus);
                }
                else
                {
                    Generic.genericOnFocusChangeListener.onFocusChange(view, hasFocus);
                }
            }
        }
    };

    @Override
    public int getBackgroundColor()
    {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int color)
    {
        backgroundColor = color;

        super.setBackgroundColor(color);
    }

    @Override
    public boolean getFocusable()
    {
        return focusable;
    }

    @Override
    public void setFocusable(boolean focusable)
    {
        this.focusable = focusable;

        super.setFocusable(focusable);

        Generic.setupFocusChange(this, focusable);
    }

    public void setAdapter(BaseAdapter adapter)
    {
        this.adapter = adapter;
    }

    public BaseAdapter getAdapter()
    {
        return adapter;
    }

    public void setHorizontalSpacing(int spacing)
    {
        horizontalSpacing = spacing;
    }

    public int getHorizontalSpacing()
    {
        return horizontalSpacing;
    }

    public void setVerticalSpacing(int spacing)
    {
        verticalSpacing = spacing;
    }

    public int getVerticalSpacing()
    {
        return verticalSpacing;
    }

    public void setNumColums(int columns)
    {
        numColumns = columns;
    }

    public int getNumColums()
    {
        return numColumns;
    }
}
