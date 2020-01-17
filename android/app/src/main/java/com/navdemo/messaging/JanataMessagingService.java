package com.navdemo.messaging;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.navdemo.R;

import java.util.Map;


public class JanataMessagingService extends FirebaseMessagingService {
    private static final int REQUEST_CODE = 1;
    private static final int NOTIFICATION_ID = 6578;
    public Boolean isInBackground;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Map<String, String> data = remoteMessage.getData();
        String title = notification.getTitle();
        String body = notification.getBody();
        WritableMap writableMap = Arguments.createMap();
        writableMap.putString("title", title);
        writableMap.putString("body",body);

        if(data != null)
        {
            for (Map.Entry<String, String> e : data
                    .entrySet()) {
                writableMap.putString(e.getKey(), e.getValue());
            }
        }

        showNotifications(title,body,writableMap);

    }

    private void showNotifications(String title, String msg, WritableMap data) {
        Intent i = new Intent(this, getApplicationContext().getClass());

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                i, PendingIntent.FLAG_UPDATE_CURRENT);

        String channelId = getString(R.string.default_notification_channel_id);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(msg)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

        ActivityManager.RunningAppProcessInfo myProcess = new ActivityManager.RunningAppProcessInfo();

        ActivityManager.getMyMemoryState(myProcess);
        isInBackground = myProcess.importance !=  ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;

        if(!isInBackground) {
            System.out.println("I am called not in background");

             FirebaseMessagingModule.getInstance().sendDataToReact(data);
        }
        else {

        }
    }

}
