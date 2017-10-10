package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.util.SparseBooleanArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

@SuppressWarnings({"WeakerAccess"})
public class Globals
{
    public static String language = Locale.getDefault().getLanguage();

    public static String UDID;

    public static String emailAddress;
    public static String firstName;
    public static String lastName;
    public static String company;
    public static String passWord;

    public static int accountId;
    public static int admin;
    public static int coins;
    public static int state;

    //
    // JSON data.
    //

    public static boolean contentsLoaded;

    public static JSONArray courses;
    public static JSONArray courseContents;
    public static JSONArray contents;

    public static JSONObject displayCategories;

    public static JSONArray completeContents;
    public static JSONArray displayMyContents;
    public static JSONArray displayAllContents;

    public static JSONObject displayContent;

    public static JSONArray customerContents;
    public static JSONArray courseQuestions;

    public static int coinsAdded;

    public static boolean showMyContent;
    public static String showCategory;
}
