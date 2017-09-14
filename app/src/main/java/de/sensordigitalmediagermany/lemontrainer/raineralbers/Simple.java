package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.support.annotation.Nullable;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.os.StrictMode;
import android.os.Build;
import android.os.Looper;
import android.util.TypedValue;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings({"unused", "WeakerAccess", "SameParameterValue"})
public class Simple
{
    private static final String LOGTAG = Simple.class.getSimpleName();

    public static final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    public static final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

    public static boolean isTablet()
    {
        Log.d(LOGTAG, "isTablet: " + (Resources.getSystem().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK));

        Log.d(LOGTAG, "isTablet: " + ((Resources.getSystem().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE));

        return ((Resources.getSystem().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE);
    }

    public static int dipToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDip(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static void setPaddingDip(View view, int pad)
    {
        view.setPadding(dipToPx(pad), dipToPx(pad), dipToPx(pad), dipToPx(pad));
    }

    public static void setPaddingDip(View view, int left, int top, int right, int bottom)
    {
        view.setPadding(dipToPx(left), dipToPx(top), dipToPx(right), dipToPx(bottom));
    }

    public static void setTextSizeDip(TextView textView, int size)
    {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
    }

    public static void setSizeDip(View view, int width, int height)
    {
        if (view.getLayoutParams() == null)
        {
            view.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        }

        view.getLayoutParams().width = width > 0 ? dipToPx(width) : width;
        view.getLayoutParams().height = height > 0 ? dipToPx(height) : height;
    }

    public static void setSizeDip(View view, int width, int height, float weight)
    {
        if (view.getLayoutParams() == null)
        {
            view.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        }

        view.getLayoutParams().width = width > 0 ? dipToPx(width) : width;
        view.getLayoutParams().height = height > 0 ? dipToPx(height) : height;

        if ((weight >= 0) && (view.getLayoutParams() instanceof LinearLayout.LayoutParams))
        {
            ((LinearLayout.LayoutParams) view.getLayoutParams()).weight = weight;
        }
    }

    public static void setWeight(View view, float weight)
    {
        if (view.getLayoutParams() == null)
        {
            view.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        }

        if ((weight >= 0) && (view.getLayoutParams() instanceof LinearLayout.LayoutParams))
        {
            ((LinearLayout.LayoutParams) view.getLayoutParams()).weight = weight;
        }
    }

    public static void setGravity(View view, int gravity)
    {
        if (view.getLayoutParams() == null)
            view.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));

        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams)
        {
            ((LinearLayout.LayoutParams) view.getLayoutParams()).gravity = gravity;
        }
    }

    public static void setMarginDip(View view, int margin)
    {
        if (view.getLayoutParams() == null)
            view.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));

        ((LinearLayout.LayoutParams) view.getLayoutParams()).leftMargin = dipToPx(margin);
        ((LinearLayout.LayoutParams) view.getLayoutParams()).topMargin = dipToPx(margin);
        ((LinearLayout.LayoutParams) view.getLayoutParams()).rightMargin = dipToPx(margin);
        ((LinearLayout.LayoutParams) view.getLayoutParams()).bottomMargin = dipToPx(margin);
    }

    public static void setMarginDip(View view, int left, int top, int right, int bottom)
    {
        if (view.getLayoutParams() == null)
            view.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));

        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin = dipToPx(left);
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin = dipToPx(top);
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin = dipToPx(right);
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin = dipToPx(bottom);
    }

    public static void setMarginLeftDip(View view, int margin)
    {
        if (view.getLayoutParams() == null)
            view.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));

        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin = dipToPx(margin);
    }

    public static void setMarginTopDip(View view, int margin)
    {
        if (view.getLayoutParams() == null)
            view.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));

        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin = dipToPx(margin);
    }

    public static void setMarginRightDip(View view, int margin)
    {
        if (view.getLayoutParams() == null)
            view.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));

        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin = dipToPx(margin);
    }

    public static void setMarginBottomDip(View view, int margin)
    {
        if (view.getLayoutParams() == null)
            view.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));

        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin = dipToPx(margin);
    }

    public static void setRoundedCorners(View view, int radiusdip, int color, boolean solid)
    {
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(dipToPx(radiusdip));

        if (solid)
        {
            shape.setColor(color);
        }
        else
        {
            shape.setStroke(2, color);
        }

        view.setBackground(shape);
    }

    public static void setRoundedCorners(View view, int[] radiusdipse, int color, boolean solid)
    {
        float[] radiuspxse = new float[radiusdipse.length * 2];

        for (int inx = 0; inx < radiuspxse.length; inx++)
        {
            radiuspxse[inx] = dipToPx(radiusdipse[inx >> 1]);
        }

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadii(radiuspxse);

        if (solid)
        {
            shape.setColor(color);
        }
        else
        {
            shape.setStroke(2, color);
        }

        view.setBackground(shape);
    }

    public static String formatPadding(int number, int digits)
    {
        String res = String.valueOf(number);

        while (res.length() < digits) res = "0" + res;

        return res;
    }

    public static String formatDecimal(int number)
    {
        String str = "" + number;
        String res = "";

        while (str.length() > 3)
        {
            if (res.length() > 0) res = "." + res;
            res = str.substring(str.length() - 3, str.length()) + res;
            str = str.substring(0, str.length() - 3);
        }

        if (str.length() > 0)
        {
            if (res.length() > 0)
            {
                res = str + "." + res;
            }
            else
            {
                res = str;
            }
        }

        return res;
    }

    public static boolean isUIThread()
    {
        return (Looper.getMainLooper().getThread() == Thread.currentThread());
    }

    public static String getAppVersion(Context context)
    {
        try
        {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

            return pInfo.versionName;
        }
        catch (Exception ignore)
        {
        }

        return "unknown";
    }

    public static String getUUID()
    {
        return UUID.randomUUID().toString().toUpperCase();
    }

    public static boolean isEmailValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isEmpty(CharSequence str1)
    {
        return (str1 == null) || equals(str1.toString(), "");
    }

    public static boolean isEmpty(String str1)
    {
        return (str1 == null) || equals(str1, "");
    }

    public static boolean equals(String str1, String str2)
    {
        return ((str1 == null) && (str2 == null))
                || ((str1 != null) && (str2 != null) && str1.equals(str2));
    }

    public static boolean equalsIgnoreCase(String str1, String str2)
    {
        return ((str1 == null) && (str2 == null))
                || ((str1 != null) && (str2 != null) && str1.equalsIgnoreCase(str2));
    }

    public static boolean startsWith(String str1, String str2)
    {
        return (str1 != null) && (str2 != null) && str1.startsWith(str2);
    }

    public static int compareTo(String str1, String str2)
    {
        if ((str1 != null) && (str2 != null)) return str1.compareTo(str2);

        return 0;
    }

    @Nullable
    public static byte[] getFileBytes(File file)
    {
        try
        {
            if (file.exists())
            {
                InputStream in = new FileInputStream(file);
                int len = (int) file.length();
                byte[] bytes = new byte[len];

                int xfer = 0;
                while (xfer < len) xfer += in.read(bytes, xfer, len - xfer);
                in.close();

                return bytes;
            }
        }
        catch (Exception ex)
        {
            Log.d(LOGTAG, ex.toString());
        }

        return null;
    }

    @Nullable
    public static String getFileContent(File file)
    {
        byte[] bytes = getFileBytes(file);
        return (bytes == null) ? null : new String(bytes);
    }

    @Nullable
    public static String getAllInputString(InputStream input)
    {
        StringBuilder string = new StringBuilder();
        byte[] buffer = new byte[ 4096 ];
        int xfer;

        try
        {
            while ((xfer = input.read(buffer)) > 0)
            {
                string.append(new String(buffer, 0, xfer));
            }
        }
        catch (IOException ex)
        {
            Log.d(LOGTAG, ex.toString());

            return null;
        }

        return string.toString();
    }

    @Nullable
    public static byte[] getAllInputBytes(InputStream input)
    {
        byte[] buffer = new byte[ 0 ];
        byte[] chunk = new byte[ 8192 ];
        int xfer;

        try
        {
            while ((xfer = input.read(chunk)) > 0)
            {
                buffer = appendBytes(buffer, chunk, 0, xfer);
            }
        }
        catch (IOException ex)
        {
            Log.d(LOGTAG, ex.toString());

            return null;
        }

        return buffer;
    }

    @Nullable
    public static byte[] appendBytes(byte[] buffer, byte[] append)
    {
        if (append == null) return buffer;

        return appendBytes(buffer, append, 0, append.length);
    }

    @Nullable
    public static byte[] appendBytes(byte[] buffer, byte[] append, int offset, int size)
    {
        if (append == null) return buffer;
        if (buffer == null) return null;

        byte[] newbuf = new byte[ buffer.length + size ];

        System.arraycopy(buffer, 0, newbuf, 0, buffer.length);
        System.arraycopy(append, offset, newbuf, buffer.length, size);

        return newbuf;
    }

    @Nullable
    public static JSONObject getFileJSONObject(File file)
    {
        byte[] bytes = getFileBytes(file);
        return (bytes == null) ? null : Json.fromStringObject(new String(bytes));
    }

    @Nullable
    public static JSONArray getFileJSONArray(File file)
    {
        byte[] bytes = getFileBytes(file);
        return (bytes == null) ? null : Json.fromStringArray(new String(bytes));
    }

    public static boolean putFileBytes(File file, byte[] bytes)
    {
        if (bytes == null) return false;

        try
        {
            OutputStream out = new FileOutputStream(file);
            out.write(bytes);
            out.close();

            return true;
        }
        catch (Exception ex)
        {
            Log.d(LOGTAG, ex.toString());
        }

        return false;
    }

    public static boolean putFileContent(File file, String content)
    {
        return (content != null) && putFileBytes(file, content.getBytes());
    }

    public static boolean putFileJSON(File file, JSONObject json)
    {
        return (json != null) && putFileContent(file, Json.toPretty(json));
    }

    public static boolean putFileJSON(File file, JSONArray json)
    {
        return (json != null) && putFileContent(file, Json.toPretty(json));
    }

    public static void setThreadPolicy()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public static void startActivity(Context context, Class<?> cls)
    {
        context.startActivity(new Intent(context, cls));
    }

    public static void startActivityTop(Context context, Class<?> cls)
    {
        if (context instanceof Activity) ((Activity) context).finishAffinity();

        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void startActivityFinish(Context context, Class<?> cls)
    {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);

        if (context instanceof Activity) ((Activity) context).finish();
    }

    public static String getTrans(Context context, int resid, Object... args)
    {
        return String.format(context.getResources().getString(resid), args);
    }

    public static void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);

        }
        catch (InterruptedException ignore)
        {
        }
    }

    public static long dezify(long number)
    {
        return number ^ 0x2905196228051998L;
    }

    @Nullable
    public static Drawable getDrawableFromHTTP(Context context, String urlstr)
    {
        try
        {
            URL url = new URL(urlstr);

            Log.d(LOGTAG, "getDrawableFromHTTP: url=" + url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStream inputStream = conn.getInputStream();
            byte[] rawimage = getAllInputBytes(inputStream);
            inputStream.close();

            if (rawimage != null)
            {
                Bitmap myBitmap = BitmapFactory.decodeByteArray(rawimage, 0, rawimage.length);

                if (myBitmap != null)
                {
                    return new BitmapDrawable(context.getResources(), myBitmap);
                }
            }
        }
        catch (FileNotFoundException ignore)
        {
        }
        catch (Exception ex)
        {
            Log.d(LOGTAG, ex.toString());
        }

        return null;
    }

    public static BitmapDrawable getDrawableFromResources(Context context, int id)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            return (BitmapDrawable) context.getResources().getDrawable(id, null);
        }

        //noinspection deprecation
        return (BitmapDrawable) context.getResources().getDrawable(id);
    }

    public static Bitmap makeCircleBitmap(Bitmap bitmap)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        Rect srcrect = new Rect(0, 0, width, height);
        Rect dstrect = new Rect(0, 0, width, height);
        RectF dstrectF = new RectF(dstrect);

        Bitmap cbm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(cbm);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawOval(dstrectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, srcrect, dstrect, paint);

        bitmap.recycle();

        return cbm;
    }

    private static final String LEMONSQLDATE = "yyyy-MM-dd' 'HH:mm:ss";

    public static long getTimeStamp(String sqldate)
    {
        if (sqldate == null) return 0;

        try
        {
            SimpleDateFormat df = new SimpleDateFormat(LEMONSQLDATE, Locale.getDefault());
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            return df.parse(sqldate).getTime();
        }
        catch (ParseException ignore)
        {
        }

        return 0;
    }

    public static String getLocalDate(long timeStamp)
    {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        df.setTimeZone(TimeZone.getDefault());
        return df.format(new Date(timeStamp));
    }

    public static Bitmap getBitmap(Context context, int resid)
    {
        return BitmapFactory.decodeResource(context.getResources(), resid);
    }

    public static FrameLayout.LayoutParams getLayoutFromRect(Rect rect)
    {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(rect.width(), rect.height());

        lp.leftMargin = rect.left;
        lp.topMargin = rect.top;

        return lp;
    }

    public static FrameLayout.LayoutParams getFittedLayout(Context context, int imageresid)
    {
        //
        // Get current screen dimensions in full screen mode.
        //

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getRealMetrics(metrics);

        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        //
        // Get unfucked real drawable dimensions.
        //

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity = DisplayMetrics.DENSITY_DEFAULT;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imageresid, options);

        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();

        Log.d(LOGTAG, "getFittedLayout: screenWidth=" + screenWidth + " screenHeight=" + screenHeight);
        Log.d(LOGTAG, "getFittedLayout: imageWidth=" + imageWidth + " imageHeight=" + imageHeight);

        //
        // Get aspect ratio and margins from fit image into screen.
        //

        float aspectRatio = screenWidth / (float) imageWidth;
        Log.d(LOGTAG, "getFittedLayout: aspectRatio=" + aspectRatio);

        //
        // Create bounding rectangle.
        //

        Rect rect = new Rect();

        rect.left = 0;
        rect.top = 0;
        rect.right = Math.round(imageWidth * aspectRatio);
        rect.bottom = Math.round(imageHeight * aspectRatio);

        Log.d(LOGTAG, "getFittedLayout: left=" + rect.left + " top=" + rect.top + " right=" + rect.right + " bottom=" + rect.bottom);

        return getLayoutFromRect(rect);
    }

    public static FrameLayout.LayoutParams getScaledLayout(Context context, Rect rect, int imageresid)
    {
        //
        // Get current screen dimensions in full screen mode.
        //

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getRealMetrics(metrics);

        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        //
        // Get unfucked real drawable dimensions.
        //

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity = DisplayMetrics.DENSITY_DEFAULT;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imageresid, options);

        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();

        Log.d(LOGTAG, "getScaledLayout: screenWidth=" + screenWidth + " screenHeight=" + screenHeight);
        Log.d(LOGTAG, "getScaledLayout: imageWidth=" + imageWidth + " imageHeight=" + imageHeight);

        //
        // Get aspect ratio and margins from fit image into screen.
        //

        float xAspectRatio = screenWidth / (float) imageWidth;
        float yAspectRatio = screenHeight / (float) imageHeight;

        Log.d(LOGTAG, "getScaledLayout: xAspect=" + xAspectRatio + " yAspect=" + yAspectRatio);

        float aspectRatio = Math.min(xAspectRatio, yAspectRatio);

        int leftMargin = (screenWidth - Math.round(imageWidth * aspectRatio)) / 2;
        int topMargin = (screenHeight - Math.round(imageHeight * aspectRatio)) / 2;

        Log.d(LOGTAG, "getScaledLayout: leftMargin=" + leftMargin + " topMargin=" + topMargin);

        //
        // Scale rectangle to final position.
        //

        Log.d(LOGTAG, "getScaledLayout: left=" + rect.left + " top=" + rect.top + " right=" + rect.right + " bottom=" + rect.bottom);

        rect.left = leftMargin + Math.round(rect.left * aspectRatio);
        rect.top = topMargin + Math.round(rect.top * aspectRatio);
        rect.right = leftMargin + Math.round(rect.right * aspectRatio);
        rect.bottom = topMargin + Math.round(rect.bottom * aspectRatio);

        Log.d(LOGTAG, "getScaledLayout: left=" + rect.left + " top=" + rect.top + " right=" + rect.right + " bottom=" + rect.bottom);

        return getLayoutFromRect(rect);
    }
}
