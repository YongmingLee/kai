package com.example.yongming.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;



/*
* 静态广播注册
*
* 1. 从BroadcastReceiver 派生子类，在onReceiver中处理逻辑，但切记，不要做太耗时的东西。只是系统给每个App
* 处理配置的机会。
* 2. 在AndroidManifest.xml 中配置 获取自启动的权限
* 3. 在<Application>标签中，配置<receiver>标签，name是你派生类的名字，action是
*
* 貌似Android8.0之后，不支持静态注册了。
*
* */

public class BaseBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "开, 自启动成功", Toast.LENGTH_SHORT).show();
    }
}
