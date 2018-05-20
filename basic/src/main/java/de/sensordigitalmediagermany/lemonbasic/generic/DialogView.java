package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    protected final static int padSize = Simple.isTablet() ? Defines.PADDING_XLARGE : Defines.PADDING_NORMAL;

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

        ApplicationBase.handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                dialogView.positiveButton.requestFocus();
            }
        });

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

    public static void yesnoAlert(final ViewGroup rootView, int titleres, String msgstr,
                                  View.OnClickListener onyesclick,
                                  View.OnClickListener onnoclick)
    {
        final DialogView dialogView = new DialogView(rootView.getContext());

        dialogView.setCloseButton(true, onnoclick);

        Simple.setRoundedCorners(dialogView.marginView, Defines.CORNER_RADIUS_DIALOG, Defines.COLOR_ALERT_BACK, true);

        dialogView.setTitleText(titleres);
        dialogView.setInfoText(msgstr);

        dialogView.setPositiveButton(R.string.button_ok, onyesclick);
        dialogView.setNegativeButton(R.string.button_cancel, onnoclick);

        ApplicationBase.handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                dialogView.positiveButton.requestFocus();
            }
        });

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

    protected RelativeLayout marginView;
    protected ImageView closeButton;
    protected LinearLayout padView;
    protected TextView titleView;
    protected TextView infoView;
    protected RelativeLayout customView;
    protected LinearLayout buttonFrame;
    protected DialogButton positiveButton;
    protected DialogButton negativeButton;

    protected OnClickListener positiveButtonOnClick;
    protected OnClickListener negativeButtonOnClick;
    protected OnClickListener closeButtonOnClick;

    public DialogView(Context context)
    {
        super(context);

        initFonts();

        ApplicationBase.hideActionBar(context);

        setFocusable(false);
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

        RelativeLayout closeButtonBox = new RelativeLayout(context);
        closeButtonBox.setGravity(Gravity.END);
        Simple.setSizeDip(closeButtonBox, Simple.MP, Simple.WC);

        boxView.addView(closeButtonBox);

        int size = Defines.CLOSE_ICON_SIZE + (Defines.PADDING_MEDIUM * 2);

        closeButton = new GenericImage(context);
        closeButton.setVisibility(GONE);
        closeButton.setImageResource(Screens.getCloseButtonRes());
        closeButton.setScaleType(ImageView.ScaleType.FIT_END);
        Simple.setSizeDip(closeButton, size, size);
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

        closeButtonBox.addView(closeButton);

        padView = new LinearLayout(context);
        padView.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(padView, Simple.WC, Simple.WC);
        Simple.setPaddingDip(padView, padSize);

        boxView.addView(padView);

        titleView = new TextView(context);
        titleView.setSingleLine(true);
        titleView.setVisibility(GONE);
        titleView.setAllCaps(Defines.isInfosAllCaps);
        titleView.setTypeface(titleFont);
        titleView.setTextColor(Defines.COLOR_DIALOG_TITLE);
        titleView.setMinEms(Defines.MIN_EMS_DIALOGS);
        titleView.setMaxEms(Defines.MAX_EMS_DIALOGS);
        Simple.setSizeDip(titleView, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(titleView, Defines.FS_DIALOG_TITLE);

        padView.addView(titleView);

        infoView = new TextView(context);
        infoView.setVisibility(GONE);
        infoView.setMinLines(2);
        infoView.setTextColor(Defines.COLOR_DIALOG_INFOS);
        infoView.setLineSpacing(0, Defines.FS_DIALOGS_LSMULT);
        infoView.setTypeface(infosFont);
        infoView.setMinEms(Defines.MIN_EMS_DIALOGS);
        infoView.setMaxEms(Defines.MAX_EMS_DIALOGS);
        Simple.setSizeDip(infoView, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(infoView, Defines.FS_DIALOG_INFO);
        Simple.setMarginTopDip(infoView, Defines.PADDING_SMALL);

        padView.addView(infoView);

        if (Defines.isDialogTextCenter)
        {
            Simple.setMarginBottomDip(titleView, Defines.PADDING_TINY);
            Simple.setMarginBottomDip(infoView, Defines.PADDING_NORMAL);

            titleView.setGravity(Gravity.CENTER_HORIZONTAL);
            infoView.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
            infoView.setAllCaps(false);
        }
        else
        {
            Simple.setMarginBottomDip(titleView, Defines.PADDING_LARGE);
            Simple.setMarginBottomDip(infoView, Defines.PADDING_XLARGE);

            titleView.setGravity(Gravity.START);
            infoView.setGravity(Gravity.START + Gravity.CENTER_VERTICAL);
            infoView.setAllCaps(true);
        }

        customView = new RelativeLayout(context);
        customView.setVisibility(GONE);
        Simple.setMarginBottomDip(customView, Defines.PADDING_NORMAL);
        Simple.setSizeDip(customView, Simple.WC, Simple.WC, 1.0f);

        padView.addView(customView);

        buttonFrame = new LinearLayout(context);
        buttonFrame.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(buttonFrame, Simple.MP, Simple.WC);

        padView.addView(buttonFrame);

        negativeButton = new DialogButton(context);
        negativeButton.setVisibility(GONE);
        negativeButton.setFocusable(false);

        negativeButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ViewGroup parent = (ViewGroup) DialogView.this.getParent();

                if (parent != null)
                {
                    parent.removeView(DialogView.this);
                }

                if (negativeButtonOnClick != null)
                {
                    negativeButtonOnClick.onClick(DialogView.this);
                }
            }
        });

        buttonFrame.addView(negativeButton);

        positiveButton = new DialogButton(context);
        positiveButton.setDefaultButton(true);
        positiveButton.setVisibility(GONE);
        positiveButton.setFocusable(false);

        positiveButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ViewGroup parent = (ViewGroup) DialogView.this.getParent();

                if (parent != null)
                {
                    parent.removeView(DialogView.this);
                }

                if (positiveButtonOnClick != null)
                {
                    positiveButtonOnClick.onClick(DialogView.this);
                }
            }
        });

        buttonFrame.addView(positiveButton);
    }

    @Override
    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();

        AppCompatActivity activity = ApplicationBase.getCurrentActivity(getContext());

        if (activity instanceof FullScreenActivity)
        {
            Log.d(LOGTAG, "onAttachedToWindow: saveFocusableViews.");

            ((FullScreenActivity) activity).saveFocusableViews(this);
        }
    }

    @Override
    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();

        AppCompatActivity activity = ApplicationBase.getCurrentActivity(getContext());

        if (activity instanceof FullScreenActivity)
        {
            Log.d(LOGTAG, "onDetachedFromWindow: restoreFocusableViews.");

            ((FullScreenActivity) activity).restoreFocusableViews();
        }
    }

    private void initFonts()
    {
        if (editsFont == null)
        {
            titleFont = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_DIALOG_TITLE);
            infosFont = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_DIALOG_INFOS);
            editsFont = Typeface.createFromAsset(getContext().getAssets(), Defines.FONT_DIALOG_EDITS);
        }
    }

    public void setButtonsVertical(boolean set)
    {
        if (set)
        {
            buttonFrame.setOrientation(LinearLayout.VERTICAL);

            Simple.setMarginLeftDip(positiveButton, Defines.PADDING_ZERO);
            Simple.setMarginTopDip(positiveButton, Defines.PADDING_SMALL);
        }
        else
        {
            buttonFrame.setOrientation(LinearLayout.HORIZONTAL);

            Simple.setMarginLeftDip(positiveButton, Defines.PADDING_ZERO);
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
        positiveButton.setFocusable(true);
        positiveButton.setVisibility(VISIBLE);
        positiveButtonOnClick = onClickListener;

        if ((positiveButton.getVisibility() == VISIBLE) && (negativeButton.getVisibility() == VISIBLE))
        {
            Simple.setMarginLeftDip(positiveButton, Defines.PADDING_NORMAL);
        }
    }

    public void setNegativeButton(int resid)
    {
        setNegativeButton(resid, null);
    }

    public void setNegativeButton(int resid, OnClickListener onClickListener)
    {
        negativeButton.setText(resid);
        negativeButton.setFocusable(true);
        negativeButton.setVisibility(VISIBLE);
        negativeButtonOnClick = onClickListener;

        if ((positiveButton.getVisibility() == VISIBLE) && (negativeButton.getVisibility() == VISIBLE))
        {
            Simple.setMarginLeftDip(positiveButton, Defines.PADDING_NORMAL);
        }
    }

    public void setCloseButton(boolean enable)
    {
        setCloseButton(enable, null);
    }

    public void setCloseButton(boolean enable, OnClickListener onClickListener)
    {
        if (enable)
        {
            Simple.setPaddingDip(padView, padSize, 0, padSize, padSize);
        }
        else
        {
            Simple.setPaddingDip(padView, padSize);
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

    protected String getHint(int resid)
    {
        String hint = Simple.getTrans(getContext(), resid);
        if (Defines.isHintsAllCaps) hint = hint.toUpperCase();

        return hint;
    }
}
