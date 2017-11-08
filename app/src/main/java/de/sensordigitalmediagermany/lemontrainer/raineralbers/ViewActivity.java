package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.os.Bundle;

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
            ViewPDFFrame pdfFrame = new ViewPDFFrame(this);
            topFrame.addView(pdfFrame);
        }

        if (content_type == Defines.CONTENT_TYPE_VIDEO)
        {
            ViewVideoFrame videoFrame = new ViewVideoFrame(this);
            topFrame.addView(videoFrame);
        }
    }
}
