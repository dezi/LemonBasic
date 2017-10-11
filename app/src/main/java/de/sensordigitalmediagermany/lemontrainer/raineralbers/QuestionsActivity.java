package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Typeface;
import android.view.Gravity;
import android.os.Bundle;

import org.json.JSONObject;

import java.util.Random;

public class QuestionsActivity extends ContentBaseActivity
{
    private static final String LOGTAG = QuestionsActivity.class.getSimpleName();

    protected TextView counterView;
    protected TextView remainingView;
    protected ProgressBar progressBar;

    protected int totalQuestions;

    protected TextView questionText;

    protected LinearLayout[] answerlayViews = new LinearLayout[ Defines.TRAINING_NUM_QUESTIONS ];
    protected TextView[] answertxtViews = new TextView[ Defines.TRAINING_NUM_QUESTIONS ];
    protected ImageView[] answerchkViews = new ImageView[ Defines.TRAINING_NUM_QUESTIONS ];

    protected LinearLayout[] answerlayDisplay;
    protected TextView[] answertxtDisplay;
    protected ImageView[] answerchkDisplay;

    protected JSONObject question;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        naviFrame.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(naviFrame, Simple.MP, Simple.MP);

        Simple.setPaddingDip(naviFrame,
                Defines.PADDING_XLARGE, Defines.PADDING_NORMAL,
                Defines.PADDING_XLARGE, Defines.PADDING_XLARGE);

        //region Progress

        LinearLayout progressTitle = new LinearLayout(this);
        progressTitle.setOrientation(LinearLayout.HORIZONTAL);
        Simple.setSizeDip(progressTitle, Simple.MP, Simple.WC);

        Simple.setMarginTopDip(progressTitle, Defines.PADDING_NORMAL);
        Simple.setMarginBottomDip(progressTitle, Defines.PADDING_TINY);

        naviFrame.addView(progressTitle);

        counterView = new TextView(this);
        counterView.setMinEms(4);
        counterView.setAllCaps(true);
        counterView.setGravity(Gravity.START);
        counterView.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        counterView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setTextSizeDip(counterView, Defines.FS_QUESTIONS_TITLE);
        Simple.setSizeDip(counterView, Simple.WC, Simple.WC);

        progressTitle.addView(counterView);

        TextView progressText = new TextView(this);
        progressText.setText(R.string.questions_progress);
        progressText.setAllCaps(true);
        progressText.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        progressText.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        progressText.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setTextSizeDip(progressText, Defines.FS_QUESTIONS_TITLE);
        Simple.setSizeDip(progressText, Simple.MP, Simple.WC, 1.0f);

        progressTitle.addView(progressText);

        remainingView = new TextView(this);
        remainingView.setMinEms(4);
        remainingView.setAllCaps(true);
        remainingView.setGravity(Gravity.END);
        remainingView.setTextColor(Defines.COLOR_SENSOR_DKBLUE);
        remainingView.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_MEDIUM));
        Simple.setTextSizeDip(remainingView, Defines.FS_QUESTIONS_TITLE);
        Simple.setSizeDip(remainingView, Simple.WC, Simple.WC);

        progressTitle.addView(remainingView);

        progressBar = new ProgressBar(this);

        Simple.setMarginTopDip(progressBar, Defines.PADDING_TINY);
        Simple.setMarginBottomDip(progressBar, Defines.PADDING_NORMAL);

        naviFrame.addView(progressBar);

        //endregion Progress

        //region Question

        questionText = new TextView(this);
        questionText.setTextColor(Color.BLACK);
        questionText.setGravity(Gravity.CENTER_HORIZONTAL);
        questionText.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAMNARROW_LIGHT));
        Simple.setTextSizeDip(questionText, Defines.FS_QUESTIONS_QUESTION);
        Simple.setSizeDip(questionText, Simple.MP, Simple.WC);
        Simple.setMarginTopDip(questionText, Defines.PADDING_LARGE);
        Simple.setMarginBottomDip(questionText, Defines.PADDING_LARGE);

        naviFrame.addView(questionText);

        //endregion Question

        //region Answers

        RelativeLayout answersCenter = new RelativeLayout(this);
        answersCenter.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);

        Simple.setSizeDip(answersCenter, Simple.MP, Simple.MP, 1.0f);

        naviFrame.addView(answersCenter);

        LinearLayout answersBox = new LinearLayout(this);
        answersBox.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(answersBox, Simple.MP, Simple.WC);

        answersCenter.addView(answersBox);

        for (int inx = 0; inx < answertxtViews.length; inx++)
        {
            LinearLayout answerLayout = new LinearLayout(this);
            answerLayout.setOrientation(LinearLayout.HORIZONTAL);
            Simple.setSizeDip(answerLayout, Simple.MP, Simple.WC);
            Simple.setRoundedCorners(answerLayout, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_LTBLUE, true);
            Simple.setMarginTopDip(answerLayout, Defines.PADDING_NORMAL);

            answersBox.addView(answerLayout);

            answerLayout.setOnClickListener(onQuestionClick);

            ImageView answerCheck = new ImageView(this);
            answerCheck.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Simple.setSizeDip(answerCheck, Defines.QUESTION_CHECK_SIZE, Simple.MP);
            Simple.setMarginLeftDip(answerCheck, Defines.PADDING_SMALL);
            Simple.setMarginRightDip(answerCheck, Defines.PADDING_SMALL);

            answerLayout.addView(answerCheck);

            RelativeLayout answerSplit = new RelativeLayout(this);
            answerSplit.setBackgroundColor(Color.GRAY);
            Simple.setSizeDip(answerSplit, 1, Simple.MP);

            answerLayout.addView(answerSplit);

            TextView answerText = new TextView(this);
            answerText.setTextColor(Color.WHITE);
            answerText.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAMNARROW_LIGHT));
            Simple.setTextSizeDip(answerText, Defines.FS_QUESTIONS_ANSWER);
            Simple.setSizeDip(answerText, Simple.MP, Simple.WC);

            Simple.setPaddingDip(answerText,
                    Defines.PADDING_NORMAL, Defines.PADDING_SMALL,
                    Defines.PADDING_NORMAL, Defines.PADDING_SMALL);

            answerLayout.addView(answerText);

            answerlayViews[ inx ] = answerLayout;
            answertxtViews[ inx ] = answerText;
            answerchkViews[ inx ] = answerCheck;
        }

        //endregion Answers

        //region Buttons

        LinearLayout buttonsArea = new LinearLayout(this);
        buttonsArea.setOrientation(LinearLayout.HORIZONTAL);

        Simple.setSizeDip(buttonsArea, Simple.MP, Simple.WC);

        naviFrame.addView(buttonsArea);

        if (! Globals.trainingFinished)
        {
            TextView cancelButton = new TextView(this);
            cancelButton.setText(R.string.button_cancel);
            cancelButton.setAllCaps(true);
            cancelButton.setTextColor(Color.WHITE);
            cancelButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
            Simple.setSizeDip(cancelButton, Simple.WC, Simple.WC);
            Simple.setTextSizeDip(cancelButton, Defines.FS_DIALOG_BUTTON);
            Simple.setRoundedCorners(cancelButton, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_CONTENT, true);

            Simple.setPaddingDip(cancelButton,
                    Defines.PADDING_NORMAL, Defines.PADDING_SMALL,
                    Defines.PADDING_NORMAL, Defines.PADDING_SMALL);

            cancelButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    onBackPressed();
                }
            });

            buttonsArea.addView(cancelButton);
        }

        RelativeLayout nextBox = new RelativeLayout(this);
        nextBox.setGravity(Gravity.END);
        Simple.setSizeDip(nextBox, Simple.MP, Simple.WC);

        buttonsArea.addView(nextBox);

        TextView nextButton = new TextView(this);
        nextButton.setText(Globals.trainingFinished ? R.string.questions_overview : R.string.questions_next);
        nextButton.setAllCaps(true);
        nextButton.setTextColor(Color.WHITE);
        nextButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAM_BOLD));
        Simple.setSizeDip(nextButton, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(nextButton, Defines.FS_DIALOG_BUTTON);
        Simple.setRoundedCorners(nextButton, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_DKBLUE, true);

        Simple.setPaddingDip(nextButton,
                Defines.PADDING_NORMAL, Defines.PADDING_SMALL,
                Defines.PADDING_NORMAL, Defines.PADDING_SMALL);

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (Globals.trainingFinished)
                {
                    showOverview();
                }
                else
                {
                    showNext();
                }
            }
        });

        nextBox.addView(nextButton);

        //endregion Buttons

        totalQuestions = (Globals.courseQuestions.length() * 10) + 1;

        if (! Globals.trainingFinished)
        {
            Globals.currentQuestion = 0;
            Globals.correctAnswers = new boolean[totalQuestions];
        }
        else
        {
            progressText.setVisibility(View.GONE);
            remainingView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }

        showNext();
    }

    private void updateProgress()
    {
        int remain = (totalQuestions - Globals.currentQuestion) + 1;

        String countStr = String.valueOf(Globals.currentQuestion) + "/" + String.valueOf(totalQuestions);
        counterView.setText(countStr);

        String remainStr = Simple.getTrans(this, R.string.questions_remain) + " " + String.valueOf(remain);
        remainingView.setText(remainStr);

        progressBar.setProgress(Globals.currentQuestion, totalQuestions);

        question = Json.getObject(Globals.courseQuestions, (Globals.currentQuestion - 1) % Globals.courseQuestions.length());

        questionText.setText(Json.getString(question, "question"));

        int numThis = 1;

        if (Json.has(question, "answer_wrong1")) numThis = 2;
        if (Json.has(question, "answer_wrong2")) numThis = 3;
        if (Json.has(question, "answer_wrong3")) numThis = 4;
        if (Json.has(question, "answer_wrong4")) numThis = 5;
        if (Json.has(question, "answer_wrong5")) numThis = 6;
        if (Json.has(question, "answer_wrong6")) numThis = 7;

        if (numThis > Defines.TRAINING_NUM_QUESTIONS) numThis = Defines.TRAINING_NUM_QUESTIONS;

        answerlayDisplay = new LinearLayout[ numThis ];
        answertxtDisplay = new TextView[ numThis ];
        answerchkDisplay = new ImageView[ numThis ];

        for (int inx = 0; inx < numThis; inx++)
        {
            answerlayDisplay[ inx ] = answerlayViews[ inx ];
            answertxtDisplay[ inx ] = answertxtViews[ inx ];
            answerchkDisplay[ inx ] = answerchkViews[ inx ];
        }

        Random rand = new Random();

        for (int inx = 0; inx < numThis; inx++)
        {
            int inx1 = rand.nextInt(numThis);
            int inx2 = rand.nextInt(numThis);

            LinearLayout tmp1 = answerlayDisplay[ inx1 ];
            answerlayDisplay[ inx1 ] = answerlayDisplay[ inx2 ];
            answerlayDisplay[ inx2 ] = tmp1;

            TextView tmp2 = answertxtDisplay[ inx1 ];
            answertxtDisplay[ inx1 ] = answertxtDisplay[ inx2 ];
            answertxtDisplay[ inx2 ] = tmp2;

            ImageView tmp3 = answerchkDisplay[ inx1 ];
            answerchkDisplay[ inx1 ] = answerchkDisplay[ inx2 ];
            answerchkDisplay[ inx2 ] = tmp3;
        }

        for (int inx = 0; inx < answertxtViews.length; inx++)
        {
            answerlayDisplay[ inx ].setVisibility((inx < numThis) ? View.VISIBLE : View.GONE);

            answertxtDisplay[ inx ].setText(null);
            answerchkDisplay[ inx ].setImageDrawable(null);
        }

        String correct = Json.getString(question, "answer_correct");
        if (Defines.isDezi) correct += "*";

        answertxtDisplay[ 0 ].setText(correct);

        for (int inx = 1; inx < numThis; inx++)
        {
            answertxtDisplay[ inx ].setText(Json.getString(question, "answer_wrong" + inx));
        }
    }

    private void showNext()
    {
        if (! Globals.trainingFinished) Globals.currentQuestion++;

        if (Globals.currentQuestion <= totalQuestions)
        {
            updateProgress();
        }
        else
        {
            showFinished();
        }
    }

    private void showFinished()
    {
        Simple.startActivityFinish(QuestionsActivity.this, ResultsActivity.class);
    }

    private void showOverview()
    {
        onBackPressed();
    }

    private final View.OnClickListener onQuestionClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            for (int inx = 0; inx < answerlayDisplay.length; inx++)
            {
                if (answerlayDisplay[ inx ] == view)
                {
                    answerchkDisplay[ inx ].setImageResource(R.drawable.lem_t_iany_generic_question_check_white);

                    Globals.correctAnswers[ Globals.currentQuestion - 1 ] = (inx == 0);
                }
                else
                {
                    answerchkDisplay[ inx ].setImageDrawable(null);
                }
            }
        }
    };
}
