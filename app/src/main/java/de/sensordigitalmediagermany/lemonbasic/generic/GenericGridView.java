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
    private BaseAdapter adapter;

    private int horizontalSpacing;
    private int verticalSpacing;
    private int columnWidth;

    private boolean dirty;

    private int numColumns = 1;
    private int focusedIndex = -1;
    private boolean focusable = false;
    private int backgroundColor = Color.TRANSPARENT;

    public GenericGridView(Context context)
    {
        super(context);

        Log.d(LOGTAG, "GenericGridView: new.");

        contentView = new FrameLayout(context);
        Simple.setSizeDip(contentView, Simple.MP, Simple.WC);

        addView(contentView);
    }

    @Override
    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();

        getHandler().post(restoreFocused);
    }

    private final Runnable restoreFocused = new Runnable()
    {
        @Override
        public void run()
        {
            if (adapter != null)
            {
                if (focusedIndex >= adapter.getCount())
                {
                    focusedIndex = adapter.getCount() - 1;
                }

                if (focusedIndex >= 0)
                {
                    Object item = adapter.getItem(focusedIndex);

                    if (item != null)
                    {
                        View view = views.get(item);

                        if (view != null)
                        {
                            Log.d(LOGTAG, "onAttachedToWindow: focusedIndex=" + focusedIndex + " view=" + view);
                            view.requestFocus();
                        }
                    }
                }
            }
        }
    };

    @Override
    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
    }

    private Map<Object, View> views = new HashMap<>();
    private Map<View, Integer> vpose = new HashMap<>();
    private Map<Object, OnFocusChangeListener> focse = new HashMap<>();

    public void updateContent()
    {
        ApplicationBase.handler.postDelayed(updateRunner, 10);
    }

    private final Runnable updateRunner = new Runnable()
    {
        @Override
        public void run()
        {
            if (columnWidth > 0)
            {
                buildContent();

                invalidate();
            }
            else
            {
                ApplicationBase.handler.postDelayed(updateRunner, 10);
            }
        }
    };

    private void buildContent()
    {
        Log.d(LOGTAG, "buildContent: start...");

        contentView.removeAllViews();

        Map<Object, View> newvs = new HashMap<>();

        int itemcount = adapter.getCount();

        for (int inx = 0; inx < itemcount; inx++)
        {
            Object item = adapter.getItem(inx);
            View view = views.get(item);

            if (view == null)
            {
                //
                // Create new view.
                //

                view = adapter.getView(inx, null, this);

                view.setLayoutParams(new MarginLayoutParams(Simple.MP, Simple.WC));

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
            }
            else
            {
                //
                // Reuse old view and thus update status etc.
                //

                view = adapter.getView(inx, null, this);
            }

            newvs.put(item, view);
            vpose.put(view, inx);

            contentView.addView(view);
        }

        views = newvs;
        dirty = true;

        Log.d(LOGTAG, "buildContent: done.");
    }

    private void positionContent()
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

            //Log.d(LOGTAG, "positionContent: xpos=" + xpos + " ypos=" + ypos);
            //Log.d(LOGTAG, "positionContent: width=" + columnWidth + " height=" + height);

            lp = (MarginLayoutParams) child.getLayoutParams();

            lp.width = columnWidth;
            lp.leftMargin = xpos;
            lp.topMargin = ypos;

            child.setLayoutParams(lp);

            if (((inx + 1) % numColumns) == 0)
            {
                ypos += height + verticalSpacing;
                xpos = 0;
            }
            else
            {
                xpos += columnWidth + horizontalSpacing;
            }
        }

        dirty = false;

        Log.d(LOGTAG, "positionContent: done.");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);

        if (changed || dirty) positionContent();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int nettoWidth = widthSize - getPaddingLeft() - getPaddingRight();
        columnWidth = (nettoWidth - (numColumns - 1) * verticalSpacing) / numColumns;

        Log.d(LOGTAG, "onMeasure:" + " widthMode=" + widthMode + " widthSize=" + widthSize);
        Log.d(LOGTAG, "onMeasure:" + " padLeft=" + getPaddingLeft() + " padright=" + getPaddingRight());
        Log.d(LOGTAG, "onMeasure:" + " nettoWidth=" + nettoWidth + " columnWidth=" + columnWidth);
    }

    private View.OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener()
    {
        @Override
        public void onFocusChange(View view, boolean hasFocus)
        {
            if (hasFocus)
            {
                Integer index = vpose.get(view);
                focusedIndex = (index == null) ? -1 : index;
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

    public void setNumColumns(int columns)
    {
        numColumns = columns;
    }

    public int getNumColumns()
    {
        return numColumns;
    }

    public int getColumnWidth()
    {
        return columnWidth;
    }
}
