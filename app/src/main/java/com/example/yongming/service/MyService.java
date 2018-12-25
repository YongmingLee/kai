package com.example.yongming.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/*
* 当调用了 startService()方法后，又去调用 stopService()方法，这时服务中的 onDestroy() 方法就会执行，表示服务
* 已经销毁了。类似地，当调用了 bindService()方法后，又去调用 unbindService()方法，onDestroy()方法也会执行，
* 这两种情况都很好理解。但是需要注意， 我们是完全有可能对一个服务既调用了 startService()方法，又调用了
* bindService()方法的， 这种情况下该如何才能让服务销毁掉呢?根据 Android 系统的机制，一个服务只要被启动或
* 者被绑定了之后，就会一直处于运行状态，必须要让以上两种条件同时不满足，服务才能被 销毁。所以，这种情况下要同时调用
 * stopService()和 unbindService()方法，onDestroy()方法才 会执行。
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

        Log.i("mys", "onCreate");

        // 启动定时器，定时通过callback 向 activity 汇报

        testTimerTask = new MyTimerTask();

        testTimer.schedule(testTimerTask, 0, 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("mys", "onStartCommand");

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


    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public int testCallbackFunction()
    {
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

            Log.i("mys", "timer running: " + testSeed);



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
