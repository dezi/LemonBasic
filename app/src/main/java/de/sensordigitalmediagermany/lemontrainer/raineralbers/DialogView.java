package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
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

        Simple.setRoundedCorners(dialogView.marginView, Defines.CORNER_RADIUS_DIALOG, Defines.COLOR_ALERT_BACK, true);

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

    public static void yesnoAlert(final ViewGroup rootView, int titleres, String msgstr, View.OnClickListener onokclick)
    {
        final DialogView dialogView = new DialogView(rootView.getContext());

        Simple.setRoundedCorners(dialogView.marginView, Defines.CORNER_RADIUS_DIALOG, Defines.COLOR_ALERT_BACK, true);

        dialogView.setTitleText(titleres);
        dialogView.setInfoText(msgstr);

        dialogView.setPositiveButton(R.string.button_ok, onokclick);
        dialogView.setNegativeButton(R.string.button_cancel, null);

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

    protected static Typeface titleFont;
    protected static Typeface infosFont;
    protected static Typeface editsFont;
    protected static Typeface buttonFont;

    protected RelativeLayout marginView;
    protected ImageView closeButton;
    protected LinearLayout padView;
    protected TextView titleView;
    protected TextView infoView;
    protected RelativeLayout customView;
    protected TextView positiveButton;
    protected TextView negativeButton;

    protected OnClickListener positiveButtonOnClick;
    protected OnClickListener negativeButtonOnClick;
    protected OnClickListener closeButtonOnClick;

    public DialogView(Context context)
    {
        super(context);

        initFonts();

        ApplicationBase.hideActionBar(context);

        setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        setBackgroundColor(Defines.COLOR_BACKGROUND_DIM);
        Simple.setSizeDip(this, Simple.MP, Simple.MP);

        setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if ((negativeButton.getVisibility() == GONE) &&
                        (positiveButton.getVisibility() == GONE) &&
                        (closeButton.getVisibility() != GONE))
                {
                    ViewGroup parent = (ViewGroup) DialogView.this.getParent();

                    if (parent != null)
                    {
                        parent.removeView(DialogView.this);
                    }
                }
            }
        });

        marginView = new RelativeLayout(context);
        Simple.setSizeDip(marginView, Simple.WC, Simple.WC);
        Simple.setPaddingDip(marginView, Defines.PADDING_TINY);
        Simple.setRoundedCorners(marginView, Defines.CORNER_RADIUS_DIALOG, Defines.COLOR_DIALOG_BACK, true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            marginView.setElevation(Simple.dipToPx(20));
        }

        addView(marginView);

        LinearLayout boxView = new LinearLayout(context);
        boxView.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(boxView, Simple.WC, Simple.WC);

        marginView.addView(boxView);

        closeButton = new ImageView(context);
        closeButton.setVisibility(GONE);
        closeButton.setImageResource(DefinesScreens.getCloseButtonRes());
        closeButton.setScaleType(ImageView.ScaleType.FIT_END);
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
        Simple.setSizeDip(padView, Simple.WC, Simple.WC);
        Simple.setPaddingDip(padView, Defines.PADDING_XLARGE);

        boxView.addView(padView);

        titleView = new TextView(context);
        titleView.setAllCaps(true);
        titleView.setVisibility(GONE);
        titleView.setTypeface(titleFont);
        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleView.setTextColor(Defines.COLOR_DIALOG_TITLE);
        Simple.setSizeDip(titleView, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(titleView, Defines.FS_DIALOG_TITLE);

        padView.addView(titleView);

        infoView = new TextView(context);
        infoView.setVisibility(GONE);
        infoView.setMinLines(2);
        infoView.setMaxEms(8);
        infoView.setTextColor(Defines.COLOR_DIALOG_INFOS);
        infoView.setTypeface(infosFont);
        infoView.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(infoView, Simple.MP, Simple.WC);
        Simple.setTextSizeDip(infoView, Defines.FS_DIALOG_INFO);
        Simple.setMarginTopDip(infoView, Defines.PADDING_SMALL);
        Simple.setMarginBottomDip(infoView, Defines.PADDING_NORMAL);

        padView.addView(infoView);

        if (Defines.isPierCardin)
        {
            titleView.setGravity(Gravity.START);
            infoView.setGravity(Gravity.START + Gravity.CENTER_VERTICAL);
            infoView.setAllCaps(true);
        }

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
        negativeButton.setMinEms(6);
        negativeButton.setVisibility(GONE);
        negativeButton.setTypeface(buttonFont);
        negativeButton.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(negativeButton, Simple.WC, Simple.WC, 0.5f);
        Simple.setTextSizeDip(negativeButton, Defines.FS_DIALOG_BUTTON);

        if (Defines.COLOR_BUTTON_BACK == Color.BLACK)
        {
            negativeButton.setTextColor(Color.BLACK);
            Simple.setRoundedCorners(negativeButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, false);
        }
        else
        {
            negativeButton.setTextColor(Color.WHITE);
            Simple.setRoundedCorners(negativeButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, true);
        }

        Simple.setPaddingDip(negativeButton,
                Defines.PADDING_NORMAL, Defines.PADDING_SMALL,
                Defines.PADDING_NORMAL, Defines.PADDING_SMALL);

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
        positiveButton.setMinEms(6);
        positiveButton.setTypeface(buttonFont);
        positiveButton.setGravity(Gravity.CENTER_HORIZONTAL);
        positiveButton.setTextColor(Color.WHITE);
        Simple.setSizeDip(positiveButton, Simple.WC, Simple.WC, 0.5f);
        Simple.setTextSizeDip(positiveButton, Defines.FS_DIALOG_BUTTON);
        Simple.setRoundedCorners(positiveButton, Defines.CORNER_RADIUS_BUTTON, Defines.COLOR_BUTTON_BACK, true);

        Simple.setPaddingDip(positiveButton,
                Defines.PADDING_NORMAL, Defines.PADDING_SMALL,
                Defines.PADDING_NORMAL, Defines.PADDING_SMALL);

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

    private void initFonts()
    {
        if (editsFont == null)
        {
            titleFont = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_DIALOG_TITLE);
            infosFont = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_DIALOG_INFOS);
            editsFont = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_DIALOG_EDITS);
            buttonFont = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_DIALOG_BUTTON);
        }
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

        if ((positiveButton.getVisibility() == VISIBLE) && (negativeButton.getVisibility() == VISIBLE))
        {
            Simple.setMarginLeftDip(positiveButton, Defines.PADDING_NORMAL);
        }
    }

    public void setNegativeButton(int resid, OnClickListener onClickListener)
    {
        negativeButton.setText(resid);
        negativeButton.setVisibility(VISIBLE);
        negativeButtonOnClick = onClickListener;

        if ((positiveButton.getVisibility() == VISIBLE) && (negativeButton.getVisibility() == VISIBLE))
        {
            Simple.setMarginLeftDip(positiveButton, Defines.PADDING_NORMAL);
        }
    }

    public void setCloseButton(boolean enable, OnClickListener onClickListener)
    {
        if (enable)
        {
            Simple.setPaddingDip(padView, Defines.PADDING_XLARGE, 0, Defines.PADDING_XLARGE, Defines.PADDING_XLARGE);
        }
        else
        {
            Simple.setPaddingDip(padView, Defines.PADDING_XLARGE);
        }

        closeButton.setVisibility(enable ? VISIBLE : GONE);
        closeButtonOnClick = onClickListener;
    }

    public ViewGroup dismissDialog()
    {
        ViewGroup parent = (ViewGroup) getParent();

        if (parent != null)
        {
            parent.removeView(DialogView.this);
        }

        return parent;
    }

    protected String getButtonText(int resid)
    {
        String text = Simple.getTrans(getContext(), resid);
        if (Defines.isButtonAllCaps) text = text.toUpperCase();

        return text;
    }

    protected String getHint(int resid)
    {
        String hint = Simple.getTrans(getContext(), resid);
        if (Defines.isHintsAllCaps) hint = hint.toUpperCase();

        return hint;
    }
}
