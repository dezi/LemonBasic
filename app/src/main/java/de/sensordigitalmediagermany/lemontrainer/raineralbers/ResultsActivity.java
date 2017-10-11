package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Typeface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;

public class ResultsActivity extends TrainingBaseActivity
{
    private static final String LOGTAG = ResultsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        titleView.setText(R.string.results_title);
        info1View.setText(R.string.results_info_1);
        info2View.setText(R.string.results_info_2);
        startButton.setText(R.string.results_start);

        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Simple.startActivityFinish(TrainingActivity.this, QuestionsActivity.class);
            }
        });
    }
}
