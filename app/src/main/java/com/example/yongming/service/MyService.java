package com.example.yongming.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.yongming.activity.MainActivity;
import com.example.yongming.activity.MultiMediaTestActivity;
import com.example.yongming.activity.R;

import java.util.Timer;
import java.util.TimerTask;

/*
 * 生命:
 * 后台服务会由于优先级和内存因素被系统强行关闭，可以声明前台服务fix之。
 *
 * IBinder:
 * 一种IPC机制，类似于Windows 的共享内存，管道Pipe，信号量Semaphore
 *
 * Timer:
 * run 不在主线程
 *
 * 关闭时机:
 * 当调用了 startService()方法后，又去调用 stopService()方法，这时服务中的 onDestroy() 方法就会执行，表示服务
 * 已经销毁了。类似地，当调用了 bindService()方法后，又去调用 unbindService()方法，onDestroy()方法也会执行，
 * 这两种情况都很好理解。但是需要注意， 我们是完全有可能对一个服务既调用了 startService()方法，又调用了
 * bindService()方法的， 这种情况下该如何才能让服务销毁掉呢?根据 Android 系统的机制，一个服务只要被启动或
 * 者被绑定了之后，就会一直处于运行状态，必须要让以上两种条件同时不满足，服务才能被 销毁。所以，这种情况下要同时调用
 * stopService()和 unbindService()方法，onDestroy()方法才 会执行。
 *
* */

public class MyService extends Service {

    private DownloadBinder downloadBinder = new DownloadBinder();

    private int testSeed = 0;

    private Timer testTimer = new Timer();

    private MyTimerTask testTimerTask;

    private Callback callback = null;

    public class DownloadBinder extends Binder
    {
        public void startDownload()
        {
            Log.i("mys", "DownloadBinder - startDownload");
        }

        public int getProgress()
        {
            int nProgress = (int)(Math.random() * 1000);

            Log.i("mys", "DownloadBinder - getProgress(" + nProgress + ")");
            return nProgress;
        }

        public MyService getService()
        {
            return MyService.this;
        }
    }

    /*
    * 以下派生函数按声明周期排列
    * */

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("mys", "I am onCreate [" + this + "]");


        bringToForeground();

        loadAlarm();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 默认是在主线程的

        Log.i("mys", "onStartCommand(" + Thread.currentThread().getName() + ")");

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return downloadBinder;
    }

    @Override
    public void onDestroy() {

        Log.i("mys", "service onDestroy");

        testTimer.cancel();

        super.onDestroy();
    }

    private void bringToForeground()
    {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {

            Intent notificationTargetIntent = new Intent(this, MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationTargetIntent, 0);

            NotificationCompat.Builder notificationCompatBuilder = new NotificationCompat.Builder(getApplicationContext(), MultiMediaTestActivity.createNotificationChannel(this));
            Notification notification = notificationCompatBuilder
                    // Title for API <16 (4.0 and below) devices.
                    .setContentTitle("Hey")
                    // Content for API <24 (7.0 and below) devices.
                    .setContentText("我是前台服务")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource( getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                    .setCategory(Notification.CATEGORY_REMINDER)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build();

            startForeground(1, notification);
        }
    }

    private void loadAlarm()
    {
        // 一、启动定时器，定时通过callback 向 activity 汇报

        testTimerTask = new MyTimerTask();

        testTimer.schedule(testTimerTask, 0, 1000);


        // 二、Alarm机制
        // 1. 不会随着不同手机厂商的电池策略休眠。保持长期有效。Android Alarm机制

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // 2. 在3秒后执行
        long triggerAtTime = SystemClock.currentThreadTimeMillis();// + 3 * 1000;

//        Intent notificationTargetIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationTargetIntent, 0);

        // 这个PendingIntent 可以是Activity，Broadcast
        Intent mainActivityBroadcastReceiver = new Intent("com.example.yongming.kai.mainbroadcast");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, mainActivityBroadcastReceiver, 0);

//        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtTime, 1000, pendingIntent);

//        alarmManager.setWindow(AlarmManager.RTC_WAKEUP, triggerAtTime, 1000, pendingIntent);

    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public int testCallbackFunction()
    {
        Log.i("mys", "I am testCallbackFunction [" + this + "]");

        return (int)(Math.random() * 1000);
    }

    /*
    *
    * 定时器任务
    * */
    class MyTimerTask extends TimerTask
    {
        @Override
        public void run() {

            Log.i("mys", "Thread[" + Thread.currentThread().getName() + "] timer running: " + testSeed);

            testSeed ++;

            if (callback != null) {
                callback.getNum(testSeed);
            }
            else {
                Log.i("mys", "timer running but callback not ready!!!");
            }
        }
    }

    /*
    * 定义回调接口
    * */
    public static interface Callback {
        void getNum(int num);
    }
}
