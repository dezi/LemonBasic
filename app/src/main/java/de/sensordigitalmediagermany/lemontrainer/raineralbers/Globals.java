package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

@SuppressWarnings({"WeakerAccess"})
public class Globals
{
    public static String language = Locale.getDefault().getLanguage();

    public static String UDID;

    public static String emailAddress;
    public static String passWord;
    public static String userName;
    public static String birthDay;
    public static String firstName;
    public static String lastName;
    public static String gender;

    public static String country;
    public static String city;
    public static String zip;
    public static String street;

    public static int accountId;

    //
    // JSON data.
    //

    public static boolean contentsLoaded;

    public static JSONArray courses;
    public static JSONArray courseContents;
    public static JSONArray contents;

    public static JSONObject displayCategories;

    public static JSONArray displayMyContents;
    public static JSONArray displayAllContents;

    public static JSONObject displayCourse;
}
