package de.sensordigitalmediagermany.lemonbasic.generic;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.media.RingtoneManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FirebaseMessageService extends FirebaseMessagingService
{
    private static final String LOGTAG = FirebaseMessageService.class.getSimpleName();

    @Override
    public void onCreate()
    {
        //
        // The listener service is either started when the application
        // is started or by Android operating system, when a GCM message
        // is coming in (wake up).
        //

        super.onCreate();

        Log.d(LOGTAG, "onCreate...");

        if (Simple.isTV())
        {
            Simple.startActivity(getBaseContext(), MainActivity.class);
        }
    }

    @Override
    public void onMessageReceived(RemoteMessage message)
    {
        //
        // Extract message from bundle.
        //

        String from = message.getFrom();
        Map data = message.getData();

        Log.d(LOGTAG, "======================>onMessageReceived: from=" + from);
        Log.d(LOGTAG, "======================>onMessageReceived: data=" + data);

        if (data == null) return;

        for (Object key : data.keySet())
        {
            Log.d(LOGTAG, "======================>onMessageReceived: key=" + key);
        }

        String title = (String) data.get("title");
        String body = (String) data.get("body");

        doNotification(this, title, body);
    }

    public static void doNotification(Context context, String title, String body)
    {
        Intent loginStart = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, loginStart, 0);

        NotificationCompat.Builder nb = new NotificationCompat.Builder(context, "Dummy");

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        nb.setContentIntent(contentIntent);
        nb.setAutoCancel(true);
        nb.setContentTitle(title);
        nb.setContentText(body);
        nb.setSound(soundUri);

        nb.setSmallIcon(Screens.getNotifyIconSmallRes());
        nb.setLargeIcon(Simple.getBitmap(context, Screens.getNotifyIconLargeRes()));

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null) nm.notify(0, nb.build());
    }
}
