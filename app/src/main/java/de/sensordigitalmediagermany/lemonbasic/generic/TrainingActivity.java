package de.sensordigitalmediagermany.lemonbasic.generic;

import android.view.View;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrainingActivity extends TrainingBaseActivity
{
    private static final String LOGTAG = TrainingActivity.class.getSimpleName();

    protected boolean wasStartClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setBackButton(true);

        titleView.setText(R.string.training_title);
        info1View.setText(R.string.training_info_1);
        courseView.setVisibility(View.VISIBLE);
        info2View.setText(R.string.training_info_2);
        actionButton.setText(R.string.training_action);

        actionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                wasStartClicked = true;

                if (Globals.courseQuestions != null)
                {
                    Simple.startActivityFinish(TrainingActivity.this, QuestionsActivity.class);
                }
            }
        });

        cancelButton.setVisibility(View.VISIBLE);

        getQuestions();
    }

    private void getQuestions()
    {
        Globals.courseQuestions = null;
        Globals.trainingFinished = false;

        JSONObject params = new JSONObject();

        Json.put(params, "courseId", Json.getInt(Globals.displayContent, "id"));

        RestApi.getPostThreaded("getCourseQuestions", params, new RestApi.RestApiResultListener()
        {
            @Override
            public void OnRestApiResult(String what, JSONObject params, JSONObject result)
            {
                if ((result != null) && Json.equals(result, "Status", "OK"))
                {
                    JSONObject data = Json.getObject(result, "Data");

                    JSONArray questions = Json.getArray(data, "CourseQuestions");

                    if (questions != null)
                    {
                        //
                        // Fix integer answer values into strings.
                        //

                        for (int inx = 0; inx < questions.length(); inx++)
                        {
                            JSONObject question = Json.getObject(questions, inx);

                            Json.fixUpNumber2String(question, "answer_correct");
                            Json.fixUpNumber2String(question, "answer_wrong1");
                            Json.fixUpNumber2String(question, "answer_wrong2");
                            Json.fixUpNumber2String(question, "answer_wrong3");
                            Json.fixUpNumber2String(question, "answer_wrong4");
                            Json.fixUpNumber2String(question, "answer_wrong5");
                            Json.fixUpNumber2String(question, "answer_wrong6");
                            Json.fixUpNumber2String(question, "answer_wrong7");
                            Json.fixUpNumber2String(question, "answer_wrong8");
                            Json.fixUpNumber2String(question, "answer_wrong9");
                        }

                        Globals.courseQuestions = questions;

                        if (wasStartClicked)
                        {
                            Simple.startActivityFinish(TrainingActivity.this, QuestionsActivity.class);
                        }

                        return;
                    }
                }

                DialogView.errorAlert(topFrame,
                        R.string.alert_training_questions_title,
                        R.string.alert_training_questions_fail);
            }
        });
    }

}
