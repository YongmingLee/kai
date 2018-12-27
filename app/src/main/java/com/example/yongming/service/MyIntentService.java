package com.example.yongming.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;


/*
* IntentService 提供一种即用即毁的模式，onHandleIntent处理你想要的逻辑，而且是在子线程工作下。
* 运行完，服务自动销毁。
*
* */

public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        // 工作在子线程

        Log.i("mys", "IS:I am running (" + Thread.currentThread().getName() + ")");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // 工作在主线程

        Log.i("mys", "IS:I am gone (" + Thread.currentThread().getName() + ")");
    }
}
