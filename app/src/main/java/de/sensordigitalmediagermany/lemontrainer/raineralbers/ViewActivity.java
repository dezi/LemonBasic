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

        if (content_type == 2)
        {
            ViewVideoFrame videoFrame = new ViewVideoFrame(this);
            topFrame.addView(videoFrame);
        }
    }
}
