package com.example.yongming.kai;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.i("ymac", "main - oncreate - " + this);

        Button btn = (Button)findViewById(R.id.btn_test);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "YOU click button le ", Toast.LENGTH_SHORT).show();
            }
        });


        if (savedInstanceState != null) {
            // 加载因为被系统回收而保存在Bundle里的临时数据

            String cacheString = savedInstanceState.getString("saveKey");
            Toast.makeText(MainActivity.this, cacheString, Toast.LENGTH_SHORT).show();
        }
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
