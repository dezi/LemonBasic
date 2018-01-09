package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class ContentHandler
{
    private static final String LOGTAG = ContentHandler.class.getSimpleName();

    private static SparseBooleanArray coursesBought = new SparseBooleanArray();
    private static SparseBooleanArray contentsBought = new SparseBooleanArray();
    private static SparseIntArray cont2courseMap = new SparseIntArray();
    private static SparseArray<JSONObject> courseId2objectMap = new SparseArray<>();
    private static SparseArray<JSONObject> contentId2objectMap = new SparseArray<>();

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
                            Globals.completeContents = new JSONArray();
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

                                    courseId2objectMap.put(id, course);

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

                                Globals.completeContents.put(content);

                                int id = Json.getInt(content, "id");

                                contentId2objectMap.put(id, content);

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

                                    Json.put(content, "_courseId", courseId);
                                    Json.put(cc, content);

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

    public static void registerOldPurchases()
    {
        if (Globals.customerContents != null)
        {
            for (int inx = 0; inx < Globals.customerContents.length(); inx++)
            {
                JSONObject item = Json.getObject(Globals.customerContents, inx);
                if (item == null) continue;

                if (Json.getInt(item, "course_id") > 0)
                {
                    coursesBought.put(Json.getInt(item, "course_id"), true);
                }

                if (Json.getInt(item, "content_id") > 0)
                {
                    contentsBought.put(Json.getInt(item, "content_id"), true);
                }
            }

            for (int inx = 0; inx < Globals.displayAllContents.length(); inx++)
            {
                JSONObject item = Json.getObject(Globals.displayAllContents, inx);
                if (item == null) continue;

                int id = Json.getInt(item, "id");
                boolean isCourse = Json.getBoolean(item, "_isCourse");

                if (isCourse)
                {
                    if (coursesBought.get(id, false))
                    {
                        Json.put(Globals.displayMyContents, item);
                    }
                }
                else
                {
                    if (contentsBought.get(id, false))
                    {
                        Json.put(Globals.displayMyContents, item);
                    }
                }
            }
        }
    }

    public static void registerNewPurchase()
    {
        if (Globals.displayContent == null) return;

        int id = Json.getInt(Globals.displayContent, "id");
        boolean isCourse = Json.getBoolean(Globals.displayContent, "_isCourse");

        if (id <= 0) return;

        if (isCourse)
        {
            coursesBought.put(id, true);
        }
        else
        {
            contentsBought.put(id, true);
        }

        Json.put(Globals.displayMyContents, Globals.displayContent);
    }

    public static boolean isCourseBought(int courseId)
    {
        return coursesBought.get(courseId, false);
    }

    public static boolean isContentBought(int contentId)
    {
        if (cont2courseMap.get(contentId, 0) != 0)
        {
            int courseId = cont2courseMap.get(contentId);
            return coursesBought.get(courseId, false);
        }

        return contentsBought.get(contentId, false);
    }

    public static JSONArray getCachedContent()
    {
        JSONArray result = new JSONArray();
        JSONArray source = Globals.completeContents;

        for (int inx = 0; inx < source.length(); inx++)
        {
            JSONObject item = Json.getObject(source, inx);
            if (item == null) continue;

            if (! isCachedContent(item)) continue;

            Json.put(result, item);
        }

        return result;
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

            if ((showCategory != null) && ! Simple.equals(showCategory, Json.getString(item, "category"))) continue;

            Json.put(result, item);
        }

        return result;
    }

    public static JSONArray getBannerContent()
    {
        JSONArray result = new JSONArray();
        JSONArray source = Globals.displayAllContents;

        for (int inx = 0; inx < source.length(); inx++)
        {
            JSONObject item = Json.getObject(source, inx);
            if (item == null) continue;

            Json.put(result, item);

            if (inx > 5) break;
        }

        return result;
    }

    public static File getStorageDir()
    {
        File storageDir = new File(ApplicationBase.cacheDir, "download");

        if (! storageDir.exists()) storageDir.mkdirs();

        return storageDir;
    }

    public static boolean isCachedContent(JSONObject content)
    {
        boolean isCourse = Json.getBoolean(content, "_isCourse");

        if (! isCourse)
        {
            String fileName = Json.getString(content, "content_file_name");

            if (fileName != null)
            {
                File cacheFile = new File(getStorageDir(), fileName);

                return cacheFile.exists();
            }
        }
        else
        {
            JSONArray cc = Json.getArray(content, "_cc");

            if (cc != null)
            {
                int numLoaded = 0;

                for (int inx = 0; inx < cc.length(); inx++)
                {
                    JSONObject ccontent = Json.getObject(cc, inx);
                    if (ccontent == null) continue;

                    String fileName = Json.getString(ccontent, "content_file_name");

                    if (fileName != null)
                    {
                        File cacheFile = new File(getStorageDir(), fileName);

                        numLoaded += cacheFile.exists() ? 1 : 0;
                    }
                }

                return (numLoaded == cc.length());
            }
        }

        return false;
    }

    @Nullable
    public static File getCachedFile(JSONObject content)
    {
        String fileName = Json.getString(content, "content_file_name");

        if (fileName != null)
        {
            File cacheFile = new File(getStorageDir(), fileName);

            if (cacheFile.exists())
            {
                return cacheFile;
            }
        }

        return null;
    }

    public static boolean deleteCachedFile(JSONObject content)
    {
        String fileName = Json.getString(content, "content_file_name");

        if (fileName != null)
        {
            File cacheFile = new File(getStorageDir(), fileName);

            if (cacheFile.delete())
            {
                AssetsDownloadManager.removeFromCache(content);

                return true;
            }
        }

        return false;
    }
}
