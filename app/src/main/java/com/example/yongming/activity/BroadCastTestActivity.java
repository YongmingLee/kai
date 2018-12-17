package com.example.yongming.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yongming.broadcast.MyBroadcastReceiver;

public class BroadCastTestActivity extends BaseActivity implements View.OnClickListener {

    private MyNetworkChangeReceiver networkChangeReceiver;
    private MyBroadcastReceiver myBroadcastReceiver;
    private LocalBroadcastReceiver localBroadcastReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_broadcast_test);

        initPrivateBroadcast();

        Button button = (Button)findViewById(R.id.broadcast_test_btn);
        button.setOnClickListener(this);

        button = (Button)findViewById(R.id.broadcast_test_btn2);
        button.setOnClickListener(this);
    }

    /*
    * 初始化自定义广播
    * */
    private void initPrivateBroadcast()
    {
        networkChangeReceiver = new MyNetworkChangeReceiver();
        myBroadcastReceiver = new MyBroadcastReceiver();

        IntentFilter sysIntentFilter = new IntentFilter();
        sysIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE"); // 系统广播
        registerReceiver(networkChangeReceiver, sysIntentFilter);


        IntentFilter ownFilter = new IntentFilter();
        ownFilter.setPriority(101);
        ownFilter.addAction("com.example.yongming.broadcast.MY_BROADCAST2"); // 自定义
        registerReceiver(myBroadcastReceiver, ownFilter);

        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("com.example.localtest");

        localBroadcastReceiver = new LocalBroadcastReceiver();

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(localBroadcastReceiver, localIntentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(networkChangeReceiver);
        unregisterReceiver(myBroadcastReceiver);

        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }

    class MyNetworkChangeReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager connectionManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LocalBroadcastReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Hi, This is local broadcast test", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.broadcast_test_btn:
                Intent intent = new Intent("com.example.yongming.broadcast.MY_BROADCAST2");
                sendOrderedBroadcast(intent, null);

                break;
            case R.id.broadcast_test_btn2:
                Intent localIntent = new Intent("com.example.localtest");
                localBroadcastManager.sendBroadcast(localIntent);
                break;
            default:
                break;
        }
    }
}
