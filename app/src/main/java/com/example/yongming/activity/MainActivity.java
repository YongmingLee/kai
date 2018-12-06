package com.example.yongming.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yongming.adapter.MainActivityListAdapter;
import com.example.yongming.module.MainActivityModule;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


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
    }

    private void initMainListView()
    {
        ListView listView = (ListView)findViewById(R.id.main_listview);
        if (listView != null) {

            List<MainActivityModule> mainDatas = new ArrayList<MainActivityModule>();

            updateMainListData(mainDatas, "基础测试", false);
            updateMainListData(mainDatas, "UI测试", false);
            updateMainListData(mainDatas, "广播测试", false);
            updateMainListData(mainDatas, "数据测试", false);
            updateMainListData(mainDatas, "多媒体测试", false);
            updateMainListData(mainDatas, "服务测试", false);
            updateMainListData(mainDatas, "网络测试", false);
            updateMainListData(mainDatas, "传感器测试", false);

            for (int i = 0; i < 100; i ++) {
                updateMainListData(mainDatas, "填充数据测试 + " + i, true);
            }

            MainActivityListAdapter mainActivityListAdapter = new MainActivityListAdapter(MainActivity.this, R.layout.main_activity_list_item, mainDatas);

            listView.setAdapter(mainActivityListAdapter);
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
            case R.id.item_0:
                Toast.makeText(MainActivity.this, "You click item 0", Toast.LENGTH_SHORT).show();

                Intent intent3 = new Intent(Intent.ACTION_DIAL);
                intent3.setData(Uri.parse("tel:18610260751"));
                startActivity(intent3);

                break;
            case R.id.item_1:
//                Toast.makeText(MainActivity.this, "You click item 1", Toast.LENGTH_SHORT).show();
//                finish();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);

                break;

            case R.id.item_next:

                Intent intent2 = new Intent(MainActivity.this, second.class);
                intent2.putExtra("mykey", "hallo, param");
                startActivityForResult(intent2, 1);

                break;

            case R.id.item_dlg:

                Intent intent4 = new Intent(MainActivity.this, third.class);
                startActivity(intent4);

                break;

            case R.id.item_fourth:

                Intent intentFourth = new Intent(MainActivity.this, fourth.class);
                startActivity(intentFourth);

                break;

            case R.id.item_alert:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder
                        (MainActivity.this);
                alertDialog.setTitle("alert");
                alertDialog.setMessage("message");
                alertDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "result:" + i, Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "result:" + i, Toast.LENGTH_SHORT).show();
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

//        Toast.makeText(MainActivity.this, "Restart", Toast.LENGTH_SHORT).show();

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
}
