package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class ViewActivity extends FullScreenActivity
{
    private static final String LOGTAG = ViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        int content_type = Json.getInt(Globals.displayContent, "content_type");

        if (content_type == Defines.CONTENT_TYPE_PDF)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                ViewPDFFrame pdfFrame = new ViewPDFFrame(this);
                topFrame.addView(pdfFrame);
            }
            else
            {
                DialogView.errorAlert(topFrame,
                        R.string.alert_no_pdf_title,
                        Simple.getTrans(this, R.string.alert_no_pdf_info),
                        new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                onBackPressed();
                            }
                        });
            }
        }

        if (content_type == Defines.CONTENT_TYPE_VIDEO)
        {
            ViewVideoFrame videoFrame = new ViewVideoFrame(this);
            topFrame.addView(videoFrame);
        }

        if (content_type == Defines.CONTENT_TYPE_ZIP)
        {
            ViewWebFrame webFrame = new ViewWebFrame(this);
            topFrame.addView(webFrame);
        }
    }
}
