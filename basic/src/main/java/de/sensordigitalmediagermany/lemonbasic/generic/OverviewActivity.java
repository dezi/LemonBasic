package de.sensordigitalmediagermany.lemonbasic.generic;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class OverviewActivity extends TrainingBaseActivity
{
    private static final String LOGTAG = OverviewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setBackButton(true);

        titleView.setText(R.string.overview_title);
        info1View.setText(R.string.overview_info_1);
        actionButton.setText(R.string.overview_action);

        actionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Simple.startActivityFinish(OverviewActivity.this, TrainingActivity.class);
            }
        });

        Simple.setSizeDip(contentCenter, Simple.MP, Simple.WC, 1.0f);

        LinearLayout resultsBox = new LinearLayout(this);
        resultsBox.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(resultsBox, Simple.MP, Simple.WC);

        contentCenter.addView(resultsBox);

        //
        // Compute nice layout of result boxes.
        //

        int optimalRows = Globals.correctAnswers.length / Defines.RESULTS_NUM_COLUMNS;
        int optimalRest = Globals.correctAnswers.length % Defines.RESULTS_NUM_COLUMNS;

        int optimalCols = Defines.RESULTS_NUM_COLUMNS;

        if ((optimalRows > 0) && (optimalRest > 0))
        {
            //
            // Distribute left over cols evenly to rows.
            //

            optimalCols -= (Defines.RESULTS_NUM_COLUMNS - optimalRest) / (optimalRows + 1);
        }

        Log.d(LOGTAG, "onCreate:"
                + " optimalRows=" + optimalRows
                + " optimalRest=" + optimalRest
                + " optimalCols=" + optimalCols);

        LinearLayout rowBox = new LinearLayout(this); // Dummy fuck to satisfy compiler.

        for (int inx = 0; inx < Globals.correctAnswers.length; inx++)
        {
            if ((inx % optimalCols) == 0)
            {
                RelativeLayout rowCenter = new RelativeLayout(this);
                rowCenter.setGravity(Gravity.CENTER_HORIZONTAL);
                Simple.setSizeDip(rowCenter, Simple.MP, Simple.WC);

                resultsBox.addView(rowCenter);

                rowBox = new LinearLayout(this);
                rowBox.setOrientation(LinearLayout.HORIZONTAL);
                Simple.setSizeDip(rowBox, Simple.WC, Simple.WC);

                rowCenter.addView(rowBox);
            }

            QuestionResultView qresult = new QuestionResultView(this);
            qresult.setResult(inx + 1, Globals.correctAnswers[ inx ]);

            qresult.setOnClickListener(onResultClick);

            rowBox.addView(qresult);
        }
    }

    private final View.OnClickListener onResultClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Globals.currentQuestion = ((QuestionResultView) view).getResultNumber();
            Simple.startActivity(OverviewActivity.this, QuestionsActivity.class);
        }
    };
}
