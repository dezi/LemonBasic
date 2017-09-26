package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.sax.RootElement;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BuyConfirmedDialog extends DialogView
{
    private static final String LOGTAG = BuyConfirmedDialog.class.getSimpleName();

    protected EditText passWord;

    public BuyConfirmedDialog(Context context)
    {
        super(context);

        setCloseButton(true, null);

        LinearLayout dialogItems = new LinearLayout(getContext());
        dialogItems.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(dialogItems, Simple.WC, Simple.WC);
        Simple.setMarginBottomDip(dialogItems, Defines.PADDING_LARGE);

        RelativeLayout imageFrame = new RelativeLayout(getContext());
        imageFrame.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
        Simple.setSizeDip(imageFrame, Simple.MP, Simple.WC);
        Simple.setPaddingDip(imageFrame, Defines.PADDING_XLARGE);

        dialogItems.addView(imageFrame);

        ImageView confirmedIcon = new ImageView(getContext());
        confirmedIcon.setImageResource(Screens.getConfirmedIconRes());
        confirmedIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Simple.setSizeDip(confirmedIcon, Simple.WC, Defines.CONFIRMED_ICON_SIZE);

        imageFrame.addView(confirmedIcon);

        String text = Simple.getTrans(getContext(), R.string.buy_confirmed_text1);
        text += Simple.isTablet() ? "\n" : " ";
        text += Simple.getTrans(getContext(), R.string.buy_confirmed_text2);

        TextView titleView = new TextView(getContext());
        titleView.setText(text);
        titleView.setAllCaps(true);
        titleView.setLineSpacing(0, Defines.FS_CONFIRMED_LSMULT);
        titleView.setTextColor(Color.WHITE);
        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleView.setTypeface(Typeface.createFromAsset(context.getAssets(), Defines.GOTHAM_BOLD));
        Simple.setTextSizeDip(titleView, Defines.FS_DIALOG_TITLE);
        Simple.setSizeDip(titleView, Simple.MP, Simple.WC);

        Simple.setPaddingDip(titleView,
                Defines.PADDING_LARGE, Defines.PADDING_TINY,
                Defines.PADDING_LARGE, Defines.PADDING_TINY
                );

        dialogItems.addView(titleView);

        setCustomView(dialogItems);
    }
}
