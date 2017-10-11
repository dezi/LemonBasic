package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.view.View;
import android.os.Bundle;

public class ResultsActivity extends TrainingBaseActivity
{
    private static final String LOGTAG = ResultsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Globals.trainingFinished = true;

        titleView.setText(R.string.results_title);
        info1View.setText(R.string.results_info_1);
        info2View.setText(R.string.results_info_2);
        actionButton.setText(R.string.results_action);

        actionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Simple.startActivityFinish(ResultsActivity.this, OverviewActivity.class);
            }
        });
    }
}
