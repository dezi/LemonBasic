package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.KeyboardShortcutGroup;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContentHandler
{
    private static final String LOGTAG = ContentHandler.class.getSimpleName();

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
                        SparseArray<JSONObject> courseMap = new SparseArray<>();
                        SparseIntArray cont2courseMap = new SparseIntArray();

                        Globals.courses = Json.getArray(data, "Courses");
                        Globals.courseContents = Json.getArray(data, "CourseContents");
                        Globals.contents = Json.getArray(data, "Contents");

                        if (Globals.courseContents != null)
                        {
                            for (int inx = 0; inx < Globals.courseContents.length(); inx++)
                            {
                                JSONObject cc = Json.getObject(Globals.courseContents, inx);
                                if (cc == null) continue;

                                int course_id = Json.getInt(cc, "course_id");
                                int content_id = Json.getInt(cc, "content_id");

                                cont2courseMap.put(content_id, course_id);
                            }
                        }

                        if (Globals.contents != null)
                        {
                            Globals.displayMyContents = new JSONArray();
                            Globals.displayAllContents = new JSONArray();
                            Globals.displayCategories = new JSONObject();

                            if (Globals.courses != null)
                            {
                                for (int inx = 0; inx < Globals.courses.length(); inx++)
                                {
                                    JSONObject course = Json.getObject(Globals.courses, inx);
                                    if (course == null) continue;

                                    int id = Json.getInt(course, "id");

                                    Json.put(course, "_isCourse", true);
                                    Json.put(course, "_cc", new JSONArray());

                                    Log.d(LOGTAG, "getAllContent: course=" + Json.getString(course, "title"));

                                    String category = Json.getString(course, "category");
                                    Json.put(Globals.displayCategories, category, true);

                                    Globals.displayAllContents.put(course);

                                    courseMap.put(id, course);
                                }
                            }

                            for (int inx = 0; inx < Globals.contents.length(); inx++)
                            {
                                JSONObject content = Json.getObject(Globals.contents, inx);
                                if (content == null) continue;

                                Log.d(LOGTAG, "getAllContent: content=" + Json.getString(content, "title"));

                                int id = Json.getInt(content, "id");
                                Json.put(content, "_isCourse", false);

                                if (cont2courseMap.get(id, 0) != 0)
                                {
                                    //
                                    // Content belongs to course.
                                    //

                                    int courseId = cont2courseMap.get(id);

                                    JSONObject course = courseMap.get(courseId);
                                    if (course == null) continue;

                                    JSONArray cc = Json.getArray(course, "_cc");
                                    if (cc == null) continue;

                                    Log.d(LOGTAG, "getAllContent: cc=" + Json.getString(content, "title"));

                                    cc.put(content);

                                    continue;
                                }

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

    public static void registerNewPurchase()
    {
        if (Globals.displayContent == null) return;

        int id = Json.getInt(Globals.displayContent, "id");
        boolean isCourse = Json.getBoolean(Globals.displayContent, "_isCourse");

        if (id <= 0) return;

        if (isCourse)
        {
            Globals.coursesBought.put(id, true);
        }
        else
        {
            Globals.contentsBought.put(id, true);
        }

        Json.put(Globals.displayMyContents, Globals.displayContent);
    }

    public static JSONArray getFilteredContent()
    {
        return getFilteredContent(Globals.showMyContent, Globals.showCategory);
    }

    public static JSONArray getFilteredContent(boolean showMy, String showCategory)
    {
        JSONArray result = new JSONArray();
        JSONArray source = showMy ? Globals.displayMyContents : Globals.displayAllContents;

        for (int inx = 0; inx < source.length(); inx++)
        {
            JSONObject item = Json.getObject(source, inx);
            if (item == null) continue;

            if ((showCategory == null) || Simple.equals(showCategory, Json.getString(item, "category")))
            {
                Json.put(result, item);
            }
        }

        return result;
    }


}
