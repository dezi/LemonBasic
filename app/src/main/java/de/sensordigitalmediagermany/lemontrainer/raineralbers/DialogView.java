package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.Gravity;
import android.view.View;
import android.os.Build;

public class DialogView extends RelativeLayout
{
    private static final String LOGTAG = DialogView.class.getSimpleName();

    public static void errorAlert(final ViewGroup rootView, int titleres, int msgres)
    {
        errorAlert(rootView, titleres, msgres, null);
    }

    public static void errorAlert(final ViewGroup rootView, int titleres, int msgres, View.OnClickListener onokclick)
    {
        errorAlert(rootView, titleres, rootView.getContext().getResources().getString(msgres), onokclick);
    }

    public static void errorAlert(final ViewGroup rootView, int titleres, String msgstr)
    {
        errorAlert(rootView, titleres, msgstr, null);
    }

    public static void errorAlert(final ViewGroup rootView, int titleres, String msgstr, View.OnClickListener onokclick)
    {
        final DialogView dialogView = new DialogView(rootView.getContext());

        dialogView.setTitleText(titleres);
        dialogView.setInfoText(msgstr);

        dialogView.setPositiveButton(R.string.button_ok, onokclick);

        if (Simple.isUIThread())
        {
            rootView.addView(dialogView);
        }
        else
        {
            ApplicationBase.handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    rootView.addView(dialogView);
                }
            });
        }
    }

    private ImageView closeButton;
    private LinearLayout padView;
    private TextView titleView;
    private TextView infoView;
    private RelativeLayout customView;
    private TextView positiveButton;
    private TextView negativeButton;

    private OnClickListener positiveButtonOnClick;
    private OnClickListener negativeButtonOnClick;
    private OnClickListener closeButtonOnClick;

    public DialogView(Context context)
    {
        super(context);

        setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        setBackgroundColor(0x22222222);
        Simple.setSizeDip(this, Simple.MP, Simple.MP);

        setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if ((negativeButton.getVisibility() == GONE) &&
                        (positiveButton.getVisibility() == GONE))
                {
                    ViewGroup parent = (ViewGroup) DialogView.this.getParent();

                    if (parent != null)
                    {
                        parent.removeView(DialogView.this);
                    }
                }
            }
        });

        RelativeLayout marginView = new RelativeLayout(context);
        Simple.setSizeDip(marginView, Simple.WC, Simple.WC);
        Simple.setPaddingDip(marginView, Defines.PADDING_MEDIUM);

        addView(marginView);

        LinearLayout boxView = new LinearLayout(context);
        boxView.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(boxView, Simple.WC, Simple.WC);
        Simple.setRoundedCorners(boxView, Defines.BUTTON_CORNER_RADIUS, Defines.COLOR_SENSOR_DKBLUE, true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            boxView.setElevation(Simple.dipToPx(20));
        }

        marginView.addView(boxView);

        closeButton = new ImageView(context);
        closeButton.setVisibility(GONE);
        closeButton.setImageResource(R.drawable.symbol_abbrechen_56x56_2x);
        closeButton.setScaleType(ImageView.ScaleType.FIT_END);
        closeButton.getDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        Simple.setSizeDip(closeButton, Simple.MP, Defines.CLOSE_ICON_SIZE + (Defines.PADDING_MEDIUM * 2));
        Simple.setPaddingDip(closeButton, Defines.PADDING_MEDIUM);

        closeButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (closeButtonOnClick != null)
                {
                    closeButtonOnClick.onClick(DialogView.this);
                }

                ViewGroup parent = (ViewGroup) DialogView.this.getParent();

                if (parent != null)
                {
                    parent.removeView(DialogView.this);
                }
            }
        });

        boxView.addView(closeButton);

        padView = new LinearLayout(context);
        padView.setOrientation(LinearLayout.VERTICAL);
        padView.setBackgroundColor(Color.WHITE);
        Simple.setSizeDip(padView, Simple.WC, Simple.WC);
        Simple.setPaddingDip(padView, Defines.PADDING_LARGE, Defines.PADDING_LARGE, Defines.PADDING_LARGE, 0);

        boxView.addView(padView);

        titleView = new TextView(context);
        titleView.setVisibility(GONE);
        titleView.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_LIGHT));
        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleView.setTextColor(Color.BLACK);
        Simple.setSizeDip(titleView, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(titleView, 36);

        padView.addView(titleView);

        infoView = new TextView(context);
        infoView.setVisibility(GONE);
        infoView.setMinLines(2);
        infoView.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_LIGHT));
        infoView.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        infoView.setLineSpacing(0.0f, 1.5f);
        Simple.setSizeDip(infoView, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(infoView, 28);

        padView.addView(infoView);

        customView = new RelativeLayout(context);
        customView.setVisibility(GONE);

        padView.addView(customView);

        LinearLayout buttonFrame = new LinearLayout(context);
        buttonFrame.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(buttonFrame, Simple.MP, Simple.WC);

        padView.addView(buttonFrame);

        negativeButton = new TextView(context);
        negativeButton.setSingleLine(true);
        negativeButton.setAllCaps(true);
        negativeButton.setVisibility(GONE);
        negativeButton.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_LIGHT));
        negativeButton.setGravity(Gravity.CENTER_HORIZONTAL);
        negativeButton.setTextColor(Color.BLACK);
        Simple.setTextSizeDip(negativeButton, 16);
        Simple.setSizeDip(negativeButton, Simple.WC, Simple.WC, 0.5f);
        Simple.setPaddingDip(negativeButton, Defines.PADDING_LARGE);

        negativeButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (negativeButtonOnClick != null)
                {
                    negativeButtonOnClick.onClick(DialogView.this);
                }

                ViewGroup parent = (ViewGroup) DialogView.this.getParent();

                if (parent != null)
                {
                    parent.removeView(DialogView.this);
                }
            }
        });

        buttonFrame.addView(negativeButton);

        positiveButton = new TextView(context);
        positiveButton.setSingleLine(true);
        positiveButton.setAllCaps(true);
        positiveButton.setVisibility(GONE);
        positiveButton.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_LIGHT));
        positiveButton.setGravity(Gravity.CENTER_HORIZONTAL);
        positiveButton.setTextColor(Color.BLACK);
        Simple.setTextSizeDip(positiveButton, 16);
        Simple.setSizeDip(positiveButton, Simple.WC, Simple.WC, 0.5f);
        Simple.setPaddingDip(positiveButton, Defines.PADDING_LARGE);

        positiveButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (positiveButtonOnClick != null)
                {
                    positiveButtonOnClick.onClick(DialogView.this);
                }

                ViewGroup parent = (ViewGroup) DialogView.this.getParent();

                if (parent != null)
                {
                    parent.removeView(DialogView.this);
                }
            }
        });

        buttonFrame.addView(positiveButton);
    }

    public void setTitleText(int resid)
    {
        if (resid > 0)
        {
            titleView.setText(resid);
            titleView.setVisibility(VISIBLE);
        }
    }

    public void setTitleText(String title)
    {
        titleView.setText(title);
        titleView.setVisibility(VISIBLE);
    }

    public void setInfoText(int resid)
    {
        if (resid > 0)
        {
            infoView.setText(resid);
            infoView.setVisibility(VISIBLE);
        }
    }

    public void setInfoText(String info)
    {
        infoView.setText(info);
        infoView.setVisibility(VISIBLE);
    }

    public void setCustomView(View view)
    {
        customView.addView(view);
        customView.setVisibility(VISIBLE);
    }

    public void setPositiveButton(int resid, OnClickListener onClickListener)
    {
        positiveButton.setText(resid);
        positiveButton.setVisibility(VISIBLE);
        positiveButtonOnClick = onClickListener;
    }

    public void setNegativeButton(int resid, OnClickListener onClickListener)
    {
        negativeButton.setText(resid);
        negativeButton.setVisibility(VISIBLE);
        negativeButtonOnClick = onClickListener;
    }

    public void setCloseButton(boolean enable, OnClickListener onClickListener)
    {
        if (enable)
        {
            Simple.setPaddingDip(padView, Defines.PADDING_LARGE, 0, Defines.PADDING_LARGE, 0);
        }
        else
        {
            Simple.setPaddingDip(padView, Defines.PADDING_LARGE, Defines.PADDING_LARGE, Defines.PADDING_LARGE, 0);
        }

        closeButton.setVisibility(enable ? VISIBLE : GONE);
        closeButtonOnClick = onClickListener;
    }
}
