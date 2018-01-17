package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseArray;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class ContentHandler
{
    private static final String LOGTAG = ContentHandler.class.getSimpleName();

    private static final SparseBooleanArray coursesBought = new SparseBooleanArray();
    private static final SparseBooleanArray contentsBought = new SparseBooleanArray();

    private static final SparseIntArray cont2sortMap = new SparseIntArray();
    private static final SparseIntArray cont2courseMap = new SparseIntArray();

    private static final SparseArray<JSONObject> courseId2objectMap = new SparseArray<>();
    private static final SparseArray<JSONObject> contentId2objectMap = new SparseArray<>();

    public static void getUserContentAndStart(final ViewGroup rootframe)
    {
        if (Defines.isBasic)
        {
            getAllContent(rootframe, new Runnable()
            {
                @Override
                public void run()
                {
                    Simple.startActivityFinish(rootframe.getContext(), ContentActivity.class);
                }
            });

            return;
        }

        Simple.startActivityFinish(rootframe.getContext(), ContentActivity.class);
    }

    private static void buildAndCountCategory(JSONObject asset)
    {
        String category = Json.getString(asset, "category");
        int categoryId = Json.getInt(asset, "category_id");

        JSONObject catjson = null;

        if (categoryId == 0)
        {
            //
            // We have no category id. Try to look up
            // existing category from list by name.
            //

            if (category == null) category = "Unknown";

            for (int inx = 0; inx < Globals.displayCategories.length(); inx++)
            {
                catjson = Json.getObject(Globals.displayCategories, inx);
                if (catjson == null) continue;

                if (Simple.equals(category, Json.getString(catjson, "name")))
                {
                    categoryId = Json.getInt(catjson, "id");

                    break;
                }

                catjson = null;
            }
        }
        else
        {
            //
            // We have a category id. It should be present
            // in the category list.
            //

            for (int inx = 0; inx < Globals.displayCategories.length(); inx++)
            {
                catjson = Json.getObject(Globals.displayCategories, inx);
                if (catjson == null) continue;

                if (Json.getInt(catjson, "id") == categoryId)
                {
                    break;
                }

                catjson = null;
            }
        }

        if (catjson == null)
        {
            //
            // A category id could not be resolved. Create
            // a virtual new category.
            //

            if (categoryId == 0)
            {
                //
                // Create even a virtual id which will hopefully
                // not collide with other real categories.
                //

                categoryId = 1000000000 + Globals.displayCategories.length();
            }

            catjson = new JSONObject();

            Json.put(catjson, "id", categoryId);
            Json.put(catjson, "sort", categoryId);
            Json.put(catjson, "name", category);

            Globals.displayCategories.put(catjson);
        }

        //
        // Now the category json is defined. Increment asset count.
        //

        if (! Json.has(catjson, "_count")) Json.put(catjson, "_count", 0);
        Json.put(catjson, "_count", Json.getInt(catjson, "_count") + 1);

        //
        // Get correct category name from category
        // list, as contradictory or missing category
        // strings in asset might exists.
        //

        category = Json.getString(catjson, "name");

        //
        // Write back category id and name into asset.
        //

        Json.put(asset, "category", category);
        Json.put(asset, "category_id", categoryId);

        //Log.d(LOGTAG, "buildAndCountCategory: category_id=" + categoryId + " category=" + category);
    }

    public static void getAllContent(final ViewGroup rootframe, final Runnable callback)
    {
        JSONObject params = new JSONObject();

        Json.put(params, "language", Globals.language);

        Json.put(params, Defines.SYSTEM_USER_PARAM, Defines.SYSTEM_USER_NAME);

        if (Defines.isBasic) Json.put(params, "accountId", Globals.accountId);

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
                        cont2sortMap.clear();
                        cont2courseMap.clear();
                        courseId2objectMap.clear();
                        contentId2objectMap.clear();

                        Globals.completeContents = new JSONArray();
                        Globals.displayMyContents = new JSONArray();
                        Globals.displayAllContents = new JSONArray();

                        Globals.courses = Json.getArray(data, "Courses");
                        Globals.contents = Json.getArray(data, "Contents");
                        Globals.ccontents = Json.getArray(data, "ContentsForCourses");
                        Globals.courseContents = Json.getArray(data, "CourseContents");

                        //
                        // Display categories either com from data
                        // or are created on the fly from contents.
                        //

                        Globals.displayCategories = Json.getArray(data, "Categories");

                        if (Globals.displayCategories != null)
                        {
                            //
                            // This version contains a category list. Sort it.
                            //

                            Globals.displayCategories = Json.sortInteger(Globals.displayCategories, "sort", false);
                        }
                        else
                        {
                            //
                            // This version does not contain a category list. Build it.
                            //

                            Globals.displayCategories = new JSONArray();
                        }

                        if (Globals.courseContents != null)
                        {
                            for (int inx = 0; inx < Globals.courseContents.length(); inx++)
                            {
                                JSONObject cc = Json.getObject(Globals.courseContents, inx);
                                if (cc == null) continue;

                                int course_id = Json.getInt(cc, "course_id");
                                int content_id = Json.getInt(cc, "content_id");
                                int sort = Json.getInt(cc, "sort");

                                cont2courseMap.put(content_id, course_id);
                                cont2sortMap.put(content_id, sort);
                            }
                        }

                        if (Globals.courses != null)
                        {
                            for (int inx = 0; inx < Globals.courses.length(); inx++)
                            {
                                JSONObject course = Json.getObject(Globals.courses, inx);
                                if (course == null) continue;

                                Globals.completeContents.put(course);

                                int id = Json.getInt(course, "id");

                                courseId2objectMap.put(id, course);

                                Json.put(course, "_isCourse", true);
                                Json.put(course, "_cc", new JSONArray());

                                Log.d(LOGTAG, "getAllContent: course=" + Json.getString(course, "title"));

                                //
                                // Make sure, a real or virtual category_id is present.
                                //

                                buildAndCountCategory(course);

                                Globals.displayAllContents.put(course);
                            }
                        }

                        addContent(Globals.contents, true);
                        addContent(Globals.ccontents, false);

                        Globals.contentsLoaded = true;

                        ApplicationBase.handler.post(callback);

                        return;
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

    private static void addContent(JSONArray contents, boolean addtop)
    {
        if (contents == null) return;

        for (int inx = 0; inx < contents.length(); inx++)
        {
            JSONObject content = Json.getObject(contents, inx);
            if (content == null) continue;

            Log.d(LOGTAG, "getAllContent: content=" + Json.getString(content, "title"));

            //
            // Make sure, a real or virtual category_id is present.
            //

            buildAndCountCategory(content);

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

                JSONObject course = courseId2objectMap.get(courseId);
                if (course == null) continue;

                JSONArray cc = Json.getArray(course, "_cc");
                if (cc == null) continue;

                Log.d(LOGTAG, "getAllContent: cc=" + Json.getString(content, "title"));

                Json.put(content, "_courseId", courseId);
                Json.put(cc, content);

                if (! addtop)
                {
                    //
                    // Course content is not added to global content list.
                    //

                    continue;
                }
            }

            Globals.displayAllContents.put(content);
        }
    }

    public static void registerOldPurchases()
    {
        if (Globals.customerContents != null)
        {
            coursesBought.clear();
            contentsBought.clear();

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

        if (source != null)
        {
            for (int inx = 0; inx < source.length(); inx++)
            {
                JSONObject item = Json.getObject(source, inx);
                if (item == null) continue;

                if ((showCategory != null) && !Simple.equals(showCategory, Json.getString(item, "category")))
                    continue;

                Json.put(result, item);
            }
        }

        return result;
    }

    public static JSONArray getBannerContent()
    {
        JSONArray result = new JSONArray();
        JSONArray source = Globals.completeContents;

        if (source != null)
        {
            for (int inx = 0; inx < source.length(); inx++)
            {
                JSONObject item = Json.getObject(source, inx);
                if (item == null) continue;

                int hasbanner = Json.getInt(item, "has_banner");
                if (hasbanner == 0) continue;

                Json.put(result, item);
            }

            if (result.length() == 0)
            {
                //
                // Fake some banners if they are desired.
                //

                for (int inx = 0; inx < source.length(); inx++)
                {
                    JSONObject item = Json.getObject(source, inx);
                    if (item == null) continue;

                    Json.put(result, item);

                    if (inx > 5) break;
                }
            }
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

                return (numLoaded > 0) && (numLoaded == cc.length());
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

    public static void deleteAllCachedFiles()
    {
        if (Globals.displayAllContents == null) return;

        for (int inx = 0; inx < Globals.displayAllContents.length(); inx++)
        {
            JSONObject content = Json.getObject(Globals.displayAllContents, inx);
            if (content == null) continue;

            deleteCachedFile(content);
        }
    }
}
