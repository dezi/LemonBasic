package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

public class ContentHandler
{
    public static void getAllContent(final ViewGroup rootframe, final Runnable callback)
    {
        JSONObject params = new JSONObject();
        Json.put(params, "language", Globals.language);
        Json.put(params, "trainerName", Defines.TRAINER_NAME);

        RestApi.getPostThreaded("getCoursesAndContents", params, false, new RestApi.RestApiResultListener()
        {
            @Override
            public void OnRestApiResult(String what, JSONObject params, JSONObject result)
            {
                if ((result != null) && Json.equals(result, "Status", "OK"))
                {
                    JSONObject data = Json.getObject(result, "Data");

                    if (data != null)
                    {
                        Globals.courses = Json.getArray(data, "Courses");
                        Globals.courseContents = Json.getArray(data, "CourseContents");
                        Globals.contents = Json.getArray(data, "Contents");

                        if (Globals.contents != null)
                        {
                            Globals.displayMyContents = new JSONArray();
                            Globals.displayAllContents = new JSONArray();
                            Globals.displayCategories = new JSONObject();

                            JSONObject isCourseContent = new JSONObject();

                            if (Globals.courses != null)
                            {
                                for (int inx = 0; inx < Globals.courses.length(); inx++)
                                {
                                    JSONObject course = Json.getObject(Globals.courses, inx);
                                    if (course == null) continue;

                                    Json.put(course, "_isCourse", true);

                                    String category = Json.getString(course, "category");
                                    Json.put(Globals.displayCategories, category, true);

                                    Globals.displayAllContents.put(course);
                                }
                            }

                            for (int inx = 0; inx < Globals.contents.length(); inx++)
                            {
                                JSONObject content = Json.getObject(Globals.contents, inx);
                                if (content == null) continue;

                                Json.put(content, "_isCourse", false);

                                String category = Json.getString(content, "category");
                                Json.put(Globals.displayCategories, category, true);

                                Globals.displayAllContents.put(content);
                            }

                            Globals.contentsLoaded = true;

                            ApplicationBase.handler.post(callback);

                            return;
                        }
                    }
                }

                ApplicationBase.handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        DialogView.errorAlert(rootframe,
                                R.string.alert_content_title,
                                R.string.alert_content_fail);
                    }
                });
            }
        });
    }
}
