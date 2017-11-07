package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class ViewVideoFrame extends FrameLayout
{
    private static final String LOGTAG = ViewVideoFrame.class.getSimpleName();

    private MediaController mediaController;
    private VideoView videoView;

    public  ViewVideoFrame(Context context)
    {
        super(context);

        setBackgroundColor(Color.BLACK);

        mediaController = new MediaController(getContext());

        videoView = new VideoView(getContext());
        videoView.setMediaController(mediaController);
        videoView.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_VERTICAL));

        addView(videoView);
    }

    @Override
    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();

        loadMedia();
    }

    @Override
    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();

        unloadMedia();
    }

    private void loadMedia()
    {
        try
        {
            File cacheFile = ContentHandler.getCachedFile(Globals.displayContent);

            if (cacheFile != null)
            {
                videoView.setVideoPath(cacheFile.toString());
                videoView.requestFocus();
                videoView.start();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void unloadMedia()
    {
        mediaController = null;

        if (videoView != null)
        {
            videoView.stopPlayback();
            videoView = null;
        }
    }
}
