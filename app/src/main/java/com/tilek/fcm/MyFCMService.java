package com.tilek.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tilek.R;
import com.tilek.ui.main.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class MyFCMService extends FirebaseMessagingService {

    String title;
    String message;
    String imageUrl;
    int notificationId;

    private Map<String, String> data;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("message", "message");
        onPostExecute(remoteMessage);
    }

    private void onPostExecute(RemoteMessage remoteMessage) {
        data = remoteMessage.getData();

//        title = data.get("title");
        title = remoteMessage.getNotification().getTitle();

//        if (remoteMessage.getData().containsKey("content")
//            && remoteMessage.getData().get("content") != null){
//            message = data.get("content");
//        }


        if (remoteMessage.getData().containsKey("id")
                && remoteMessage.getData().get("id") != null) {
            notificationId = Integer.valueOf(data.get("id"));
        }

        if (remoteMessage.getNotification().getImageUrl() != null) {
            imageUrl = remoteMessage.getNotification().getImageUrl().toString();
        }

        message = remoteMessage.getNotification().getBody();

        Intent intent = new Intent(this, MainActivity.class);
        String channelId = "Default";

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                notificationId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        Uri defaultSong = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_brain_v2)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSong)
                .setContentIntent(pendingIntent);

        Notification notification = builder.build();

        notification.tickerText = title;

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);

            manager.createNotificationChannel(channel);
        }

        manager.notify(notificationId, notification);
    }

}
