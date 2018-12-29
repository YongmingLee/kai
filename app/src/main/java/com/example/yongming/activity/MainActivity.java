package com.example.yongming.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yongming.activity.ActivitySubmodule.SensorTestActivity;
import com.example.yongming.adapter.MainActivityListAdapter;
import com.example.yongming.module.MainActivityModule;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private MainActivityBroadcastReceiver mainActivityBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.i("ymac", "main - oncreate - " + this);

        initMainListView();

        if (savedInstanceState != null) {
            // 加载因为被系统回收而保存在Bundle里的临时数据
            String cacheString = savedInstanceState.getString("saveKey");
            Toast.makeText(MainActivity.this, cacheString, Toast.LENGTH_SHORT).show();
        }

        mainActivityBroadcastReceiver = new MainActivityBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.yongming.kai.mainbroadcast");
        registerReceiver(mainActivityBroadcastReceiver, intentFilter);
    }

    private void initMainListView()
    {
        ListView listView = (ListView)findViewById(R.id.main_listview);
        if (listView != null) {

            final List<MainActivityModule> mainDatas = new ArrayList<MainActivityModule>();

            updateMainListData(mainDatas, "基础测试", false);
            updateMainListData(mainDatas, "广播测试", false);
            updateMainListData(mainDatas, "数据测试", false);
            updateMainListData(mainDatas, "内容提供器测试", false);
            updateMainListData(mainDatas, "多媒体测试", false);
            updateMainListData(mainDatas, "服务测试", false);
            updateMainListData(mainDatas, "网络测试", false);
            updateMainListData(mainDatas, "传感器测试", false);

            MainActivityListAdapter mainActivityListAdapter = new MainActivityListAdapter(MainActivity.this, R.layout.main_activity_list_item, mainDatas);

            listView.setAdapter(mainActivityListAdapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                                    MainActivityModule mainActivityModule = mainDatas.get(i);

                                                    switch (i) {
                                                        case 0:
                                                            // 基础测试

                                                            Intent intent = new Intent(MainActivity.this, UITestActivity.class);
                                                            intent.putExtra("name", mainActivityModule.getName());
                                                            startActivity(intent);

                                                            break;

                                                        case 1:
                                                            // 广播测试

                                                            Intent broadcastTestIntent = new Intent(MainActivity.this, BroadCastTestActivity.class);
                                                            broadcastTestIntent.putExtra("name", mainActivityModule.getName());
                                                            startActivity(broadcastTestIntent);

                                                            break;

                                                        case 2:
                                                            // 数据测试

                                                            Intent dataTestIntent = new Intent(MainActivity.this, DataTestActivity.class);
                                                            dataTestIntent.putExtra("name", mainActivityModule.getName());
                                                            startActivity(dataTestIntent);


                                                            break;

                                                        case 3:
                                                            // 共享数据/内容提供器

                                                            Intent sharedataIntent = new Intent(MainActivity.this, ShareDataTestActivity.class);
                                                            sharedataIntent.putExtra("name", mainActivityModule.getName());
                                                            startActivity(sharedataIntent);

                                                            break;

                                                        case 4:
                                                            // 测试多媒体

                                                            Intent multimediaIntent = new Intent(MainActivity.this, MultiMediaTestActivity.class);
                                                            multimediaIntent.putExtra("name", mainActivityModule.getName());
                                                            startActivity(multimediaIntent);

                                                            break;

                                                        case 5:
                                                            // 服务测试

                                                            Intent serviceIntent = new Intent(MainActivity.this, ServiceTestActivity.class);
                                                            serviceIntent.putExtra("name", mainActivityModule.getName());
                                                            startActivity(serviceIntent);

                                                            break;

                                                        case 6:
                                                            // 网络测试

                                                            Intent netIntent = new Intent(MainActivity.this, NetworkTestActivity.class);
                                                            netIntent.putExtra("name", mainActivityModule.getName());
                                                            startActivity(netIntent);

                                                            break;

                                                        case 7:
                                                            // 传感器

                                                            Intent sensorIntent = new Intent(MainActivity.this, SensorTestActivity.class);
                                                            sensorIntent.putExtra("name", mainActivityModule.getName());
                                                            startActivity(sensorIntent);

                                                            break;
                                                    }

                                                }
                                            }
            );
        }
    }

    private void updateMainListData(List<MainActivityModule> list, String name, boolean istest)
    {
        MainActivityModule m8 = new MainActivityModule(name, istest);
        list.add(m8);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menutest, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.item_aboutme:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("关于");
                alertDialog.setMessage("一段Android 之旅");
                alertDialog.setPositiveButton("感谢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(MainActivity.this, "你是可以的！", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });
                alertDialog.setNegativeButton("加油！", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertDialog.show();


                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode)
        {
            case 1:

                if (resultCode == RESULT_OK) {

                    String r = data.getStringExtra("mykey");
                    Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("ymac", "main - onrestart - " + this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // 保存即将由于系统回收该Activity造成的数据丢失，存入到 Bundle里，在OnCreate 的Bundle中读取相应Key的内容。

        outState.putString("saveKey", "hello");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("ymac", "main - onstop - " + this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("ymac", "main - ondestroy - " + this);
    }

    public class MainActivityBroadcastReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i("mab", "MainActivityBroadcast received");
        }
    }
}
