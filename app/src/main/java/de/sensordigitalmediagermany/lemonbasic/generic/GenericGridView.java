package de.sensordigitalmediagermany.lemonbasic.generic;

import android.view.Gravity;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class GenericGridView extends FrameLayout implements GenericFocus
{
    private static final String LOGTAG = GenericGridView.class.getSimpleName();

    private BaseAdapter adapter;

    private ScrollView scrollView;
    private FrameLayout contentView;
    private RelativeLayout spinnerCenter;
    private ImageView spinnerIcon;

    private int horizontalSpacing;
    private int verticalSpacing;
    private int columnWidth;

    private int numColumns = 1;
    private int focusedIndex = -1;
    private boolean focusable = false;
    private int backgroundColor = Color.TRANSPARENT;

    private Map<Object, View> views = new HashMap<>();
    private Map<View, Integer> vpose = new HashMap<>();
    private Map<Object, OnFocusChangeListener> focse = new HashMap<>();

    private boolean dirty;

    private Runnable onUpdateStartedHandler;
    private Runnable onUpdateFinishedHandler;

    public GenericGridView(Context context)
    {
        super(context);

        scrollView = new ScrollView(context);
        scrollView.setFocusable(false);

        addView(scrollView);

        contentView = new FrameLayout(getContext());

        scrollView.addView(contentView);
    }

    private void startSpinner()
    {
        if (onUpdateStartedHandler != null)
        {
            onUpdateStartedHandler.run();
        }

        /*
        if (spinnerCenter == null)
        {
            spinnerCenter = new RelativeLayout(getContext());
            spinnerCenter.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
            Simple.setSizeDip(spinnerCenter, Simple.MP, Simple.MP);

            addView(spinnerCenter);

            spinnerIcon = new ImageView(getContext());
            spinnerIcon.setScaleType(ImageView.ScaleType.FIT_XY);
            spinnerIcon.setImageResource(R.drawable.lem_t_iany_generic_spinner);
            Simple.setSizeDip(spinnerIcon, Defines.SPINNER_ICON_SIZE, Defines.SPINNER_ICON_SIZE);

            spinnerCenter.addView(spinnerIcon);

            AnimationSet animSet = new AnimationSet(true);
            animSet.setInterpolator(new LinearInterpolator());
            animSet.setFillAfter(true);
            animSet.setFillEnabled(true);

            final RotateAnimation animRotate = new RotateAnimation(0.0f, -360.0f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f);

            animRotate.setFillAfter(true);
            animRotate.setDuration(1000);
            animRotate.setRepeatCount(1000);
            animSet.addAnimation(animRotate);

            spinnerIcon.startAnimation(animSet);
        }
        */
    }

    private final Runnable stopSpinner = new Runnable()
    {
        @Override
        public void run()
        {
            if (spinnerCenter != null)
            {
                spinnerIcon.clearAnimation();
                removeView(spinnerCenter);
                spinnerCenter = null;
            }

            if (onUpdateFinishedHandler != null)
            {
                ApplicationBase.handler.postDelayed(onUpdateFinishedHandler, 100);
            }
        }

    };

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

    public void updateContent()
    {
        if ((adapter == null) || (adapter.getCount() == 0))
        {
            if (onUpdateStartedHandler != null)
            {
                onUpdateStartedHandler.run();
            }

            views.clear();
            vpose.clear();
            focse.clear();

            contentView.removeAllViews();

            if (onUpdateFinishedHandler != null)
            {
                onUpdateFinishedHandler.run();
            }
        }
        else
        {
            if (adapter.getCount() > 100)
            {
                startSpinner();
            }

            ApplicationBase.handler.removeCallbacks(updateContentRunner);
            ApplicationBase.handler.postDelayed(updateContentRunner, 100);
        }
    }

    private final Runnable updateContentRunner = new Runnable()
    {
        @Override
        public void run()
        {
            if (columnWidth > 0)
            {
                buildContent();
            }
            else
            {
                ApplicationBase.handler.postDelayed(updateContentRunner, 10);
            }
        }
    };

    private void buildContent()
    {
        Log.d(LOGTAG, "buildContent: start...");

        dirty = false;

        int xpos = 0;
        int ypos = 0;

        MarginLayoutParams lp;
        int height = 0;

        Map<Object, View> newvs = new HashMap<>();

        int itemcount = adapter.getCount();

        Log.d(LOGTAG, "buildContent: itemcount=" + itemcount);

        for (int inx = 0; inx < itemcount; inx++)
        {
            Object item = adapter.getItem(inx);
            View view = views.remove(item);

            if (view == null)
            {
                //
                // Create new view.
                //

                view = adapter.getView(inx, null, this);

                if (view.getLayoutParams() instanceof MarginLayoutParams)
                {
                    lp = (MarginLayoutParams) view.getLayoutParams();

                    if (lp.width != columnWidth)
                    {
                        lp.width = columnWidth;

                        lp.leftMargin = xpos;
                        lp.topMargin = ypos;
                    }

                    if (lp.height > 0)
                    {
                        height = lp.height;
                    }

                    view.setLayoutParams(lp);
                }
                else
                {
                    lp = new MarginLayoutParams(columnWidth, Simple.WC);

                    view.setLayoutParams(lp);

                    dirty = true;
                }

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
            else
            {
                //
                // Reuse old view and thus update status etc.
                //

                view = adapter.getView(inx, view, this);
                height = view.getHeight();

                if (view.getLayoutParams() instanceof MarginLayoutParams)
                {
                    lp = (MarginLayoutParams) view.getLayoutParams();

                    if ((lp.width != columnWidth)
                            || (lp.height != height)
                            || (lp.leftMargin != xpos)
                            || (lp.topMargin != ypos))
                    {
                        lp.width = columnWidth;
                        lp.height = height;
                        lp.leftMargin = xpos;
                        lp.topMargin = ypos;

                        view.setLayoutParams(lp);
                    }
                }
            }

            newvs.put(item, view);
            vpose.put(view, inx);

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

        for (Map.Entry<Object, View> entry : views.entrySet())
        {
            contentView.removeView(entry.getValue());

            Log.d(LOGTAG, "remove=" + entry.getValue());
        }

        views = newvs;

        if (! dirty)
        {
            ApplicationBase.handler.post(stopSpinner);
        }

        ApplicationBase.handler.removeCallbacks(updateContentRunner);

        Log.d(LOGTAG, "buildContent: done dirty=" + dirty);
    }

    private void positionContent()
    {
        Log.d(LOGTAG, "positionContent: start...");

        int itemcount = contentView.getChildCount();

        Log.d(LOGTAG, "positionContent: itemcount=" + itemcount);

        int xpos = 0;
        int ypos = 0;

        MarginLayoutParams lp;
        View child;
        int height;

        for (int inx = 0; inx < itemcount; inx++)
        {
            child = contentView.getChildAt(inx);
            height = child.getHeight();

            lp = (MarginLayoutParams) child.getLayoutParams();

            if ((lp.width != columnWidth)
                    || (lp.height != height)
                    || (lp.leftMargin != xpos)
                    || (lp.topMargin != ypos))
            {
                lp.width = columnWidth;
                lp.height = height;
                lp.leftMargin = xpos;
                lp.topMargin = ypos;

                child.setLayoutParams(lp);
            }

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

        if (itemcount > 0)
        {
            ApplicationBase.handler.removeCallbacks(updateContentRunner);

            ApplicationBase.handler.post(stopSpinner);
        }

        Log.d(LOGTAG, "positionContent: done.");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);

        int width = (r - l);
        int nettoWidth = width - getPaddingLeft() - getPaddingRight();
        columnWidth = (nettoWidth - (numColumns - 1) * verticalSpacing) / numColumns;

        Log.d(LOGTAG, "onLayout:" + " changed=" + changed + " width=" + width);

        if ((contentView.getChildCount() > 0) && (changed || dirty))
        {
            ApplicationBase.handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    positionContent();
                }
            });
        }
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

    public void setOnUpdateStartedHandler(Runnable onUpdateStartedHandler)
    {
        this.onUpdateStartedHandler = onUpdateStartedHandler;
    }

    public void setOnUpdateFinishedHandler(Runnable onUpdateFinishedHandler)
    {
        this.onUpdateFinishedHandler = onUpdateFinishedHandler;
    }
}
