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
    protected int currentQuestion;
    protected boolean correctAnswers[];

    protected LinearLayout[] questlayViews = new LinearLayout[ Defines.TRAINING_NUM_QUESTIONS ];
    protected TextView[] questionViews = new TextView[ Defines.TRAINING_NUM_QUESTIONS ];
    protected ImageView[] checkimgViews = new ImageView[ Defines.TRAINING_NUM_QUESTIONS ];

    protected LinearLayout[] questlayDisplay;
    protected TextView[] questionDisplay;
    protected ImageView[] checkimgDisplay;

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

        //region Questions

        RelativeLayout questionsCenter = new RelativeLayout(this);
        questionsCenter.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);

        Simple.setSizeDip(questionsCenter, Simple.MP, Simple.MP, 1.0f);

        naviFrame.addView(questionsCenter);

        LinearLayout questionsBox = new LinearLayout(this);
        questionsBox.setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(questionsBox, Simple.MP, Simple.WC);

        questionsCenter.addView(questionsBox);

        for (int inx = 0; inx < questionViews.length; inx++)
        {
            LinearLayout questionLayout = new LinearLayout(this);
            questionLayout.setOrientation(LinearLayout.HORIZONTAL);
            Simple.setSizeDip(questionLayout, Simple.MP, Simple.WC);
            Simple.setRoundedCorners(questionLayout, Defines.CORNER_RADIUS_BIGBUT, Defines.COLOR_SENSOR_LTBLUE, true);
            Simple.setMarginTopDip(questionLayout, Defines.PADDING_NORMAL);

            questionsBox.addView(questionLayout);

            questionLayout.setOnClickListener(onQuestionClick);

            ImageView questionCheck = new ImageView(this);
            questionCheck.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Simple.setSizeDip(questionCheck, Defines.QUESTION_CHECK_SIZE, Simple.MP);
            Simple.setMarginLeftDip(questionCheck, Defines.PADDING_SMALL);
            Simple.setMarginRightDip(questionCheck, Defines.PADDING_SMALL);

            questionLayout.addView(questionCheck);

            RelativeLayout questionSplit = new RelativeLayout(this);
            questionSplit.setBackgroundColor(Color.GRAY);
            Simple.setSizeDip(questionSplit, 1, Simple.MP);

            questionLayout.addView(questionSplit);

            TextView questionButton = new TextView(this);
            questionButton.setTextColor(Color.WHITE);
            questionButton.setTypeface(Typeface.createFromAsset(getAssets(), Defines.GOTHAMNARROW_LIGHT));
            Simple.setTextSizeDip(questionButton, Defines.FS_DIALOG_BUTTON);
            Simple.setSizeDip(questionButton, Simple.MP, Simple.WC);

            Simple.setPaddingDip(questionButton,
                    Defines.PADDING_NORMAL, Defines.PADDING_SMALL,
                    Defines.PADDING_NORMAL, Defines.PADDING_SMALL);

            questionLayout.addView(questionButton);

            questlayViews[ inx ] = questionLayout;
            questionViews[ inx ] = questionButton;
            checkimgViews[ inx ] = questionCheck;
        }

        //endregion Questions

        //region Buttons

        LinearLayout buttonsArea = new LinearLayout(this);
        buttonsArea.setOrientation(LinearLayout.HORIZONTAL);

        Simple.setSizeDip(buttonsArea, Simple.MP, Simple.WC);

        naviFrame.addView(buttonsArea);

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

        RelativeLayout nextBox = new RelativeLayout(this);
        nextBox.setGravity(Gravity.END);
        Simple.setSizeDip(nextBox, Simple.MP, Simple.WC);

        buttonsArea.addView(nextBox);

        TextView nextButton = new TextView(this);
        nextButton.setText(R.string.questions_next);
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
                showNext();
            }
        });

        nextBox.addView(nextButton);

        //endregion Buttons

        totalQuestions = Globals.courseQuestions.length() * 10;
        currentQuestion = 0;

        correctAnswers = new boolean[ totalQuestions ];

        showNext();
    }

    private void updateProgress()
    {
        int remain = (totalQuestions - currentQuestion) + 1;

        String countStr = String.valueOf(currentQuestion) + "/" + String.valueOf(totalQuestions);
        counterView.setText(countStr);

        String remainStr = Simple.getTrans(this, R.string.questions_remain) + " " + String.valueOf(remain);
        remainingView.setText(remainStr);

        progressBar.setProgress(currentQuestion, totalQuestions);

        question = Json.getObject(Globals.courseQuestions, (currentQuestion - 1) % Globals.courseQuestions.length());

        int numThis = 1;

        if (Json.has(question, "answer_wrong1")) numThis = 2;
        if (Json.has(question, "answer_wrong2")) numThis = 3;
        if (Json.has(question, "answer_wrong3")) numThis = 4;
        if (Json.has(question, "answer_wrong4")) numThis = 5;
        if (Json.has(question, "answer_wrong5")) numThis = 6;
        if (Json.has(question, "answer_wrong6")) numThis = 7;

        if (numThis > Defines.TRAINING_NUM_QUESTIONS) numThis = Defines.TRAINING_NUM_QUESTIONS;

        questlayDisplay = new LinearLayout[ numThis ];
        questionDisplay = new TextView[ numThis ];
        checkimgDisplay = new ImageView[ numThis ];

        for (int inx = 0; inx < numThis; inx++)
        {
            questlayDisplay[ inx ] = questlayViews[ inx ];
            questionDisplay[ inx ] = questionViews[ inx ];
            checkimgDisplay[ inx ] = checkimgViews[ inx ];
        }

        Random rand = new Random();

        for (int inx = 0; inx < numThis; inx++)
        {
            int inx1 = rand.nextInt(numThis);
            int inx2 = rand.nextInt(numThis);

            LinearLayout tmp1 = questlayDisplay[ inx1 ];
            questlayDisplay[ inx1 ] = questlayDisplay[ inx2 ];
            questlayDisplay[ inx2 ] = tmp1;

            TextView tmp2 = questionDisplay[ inx1 ];
            questionDisplay[ inx1 ] = questionDisplay[ inx2 ];
            questionDisplay[ inx2 ] = tmp2;

            ImageView tmp3 = checkimgDisplay[ inx1 ];
            checkimgDisplay[ inx1 ] = checkimgDisplay[ inx2 ];
            checkimgDisplay[ inx2 ] = tmp3;
        }

        for (int inx = 0; inx < questionViews.length; inx++)
        {
            questlayDisplay[ inx ].setVisibility((inx < numThis) ? View.VISIBLE : View.GONE);

            questionDisplay[ inx ].setText(null);
            checkimgDisplay[ inx ].setImageDrawable(null);
        }

        String correct = Json.getString(question, "answer_correct");
        if (Defines.isDezi) correct += "*";

        questionDisplay[ 0 ].setText(correct);

        for (int inx = 1; inx < numThis; inx++)
        {
            questionDisplay[ inx ].setText(Json.getString(question, "answer_wrong" + inx));
        }
    }

    private void showNext()
    {
        currentQuestion++;

        if (currentQuestion <= totalQuestions)
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

    private final View.OnClickListener onQuestionClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            for (int inx = 0; inx < questlayDisplay.length; inx++)
            {
                if (questlayDisplay[ inx ] == view)
                {
                    checkimgDisplay[ inx ].setImageResource(R.drawable.lem_t_iany_generic_question_check);

                    correctAnswers[ currentQuestion - 1 ] = (inx == 0);
                }
                else
                {
                    checkimgDisplay[ inx ].setImageDrawable(null);
                }
            }
        }
    };
}
