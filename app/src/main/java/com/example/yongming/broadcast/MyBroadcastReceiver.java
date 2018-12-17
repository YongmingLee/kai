package com.example.yongming.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.yongming.activity.R;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, R.string.send_broadcast_test, Toast.LENGTH_SHORT).show();


        // 如果想中断广播，调用下面语句
//        abortBroadcast();
    }
}
