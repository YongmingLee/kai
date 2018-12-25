package com.example.yongming.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;

import com.example.yongming.activity.ActivitySubmodule.AlbumTestActivity;

import java.util.ArrayList;

public class MultiMediaTestActivity extends BaseListActivity {

    private ArrayList<String> listDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        listDatas.add("通知测试");
        listDatas.add("相机测试");
        listDatas.add("相册测试");
        listDatas.add("音视频测试");

        super.onCreate(savedInstanceState);
    }

    @Override
    public ArrayList<String> CustomDataSource() {
        return listDatas;
    }

    @Override
    public void OnListItemClick(int position) {
        super.OnListItemClick(position);

        switch (position)
        {
            case 0:
                loadNotification();
                break;

            case 1:
                Intent cameraIntent = new Intent(MultiMediaTestActivity.this, AlbumTestActivity.class);
                cameraIntent.putExtra("actionType", 0);
                startActivity(cameraIntent);
                break;

            case 2:
                Intent albumIntent = new Intent(MultiMediaTestActivity.this, AlbumTestActivity.class);
                albumIntent.putExtra("actionType", 1);
                startActivity(albumIntent);
                break;
        }
    }

    private void loadNotification()
    {
        // TODO: Android P 需要解决不弹出的问题

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {

            PendingIntent notifyPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

            NotificationCompat.Builder notificationCompatBuilder = new NotificationCompat.Builder(getApplicationContext(), createNotificationChannel(this));
            Notification notification = notificationCompatBuilder
                    // Title for API <16 (4.0 and below) devices.
                    .setContentTitle("通知")
                    // Content for API <24 (7.0 and below) devices.
                    .setContentText("高通、联发科等与阿里合作：将推内嵌国产操作系统芯片产品")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource( getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(notifyPendingIntent)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                    .setCategory(Notification.CATEGORY_REMINDER)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build();

            NotificationManagerCompat.from(getApplicationContext()).notify(1, notification);


        } else {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            Intent startIntent = new Intent(this, MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, startIntent, 0);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat
                    .Builder(this,"default")
                    .setContentTitle("测试notification")
                    .setContentText("This is content text")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher_round);

            Notification notification = notificationBuilder.build();

            notificationManager.notify(123, notification);
        }
    }


    public static String createNotificationChannel(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "yongmingapp";
            CharSequence channelName = "永明通道";
            String channelDescription ="一个通向魅力の通道";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, channelImportance);
            // 设置描述 最长30字符
            notificationChannel.setDescription(channelDescription);
            // 该渠道的通知是否使用震动
            notificationChannel.enableVibration(true);
            // 设置显示模式
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
            return channelId;
        }

        return null;
    }
}
