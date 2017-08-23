package com.savar_computer.healthassistants.Classes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.savar_computer.healthassistants.Menu;
import com.savar_computer.healthassistants.R;

public class notification {
    private static Context context;

    public notification(Context context){
        this.context=context;
    }

    public static void sendNotification(String message, String title, int id) {
        Intent intent = new Intent(context, Menu.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */,
                intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notif)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(id /* ID of notification */,
                notificationBuilder.build());
    }
}
