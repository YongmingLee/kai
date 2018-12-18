package com.example.yongming.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yongming.manager.SQLiteOpenHelperHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DataTestActivity extends BaseActivity implements View.OnClickListener {

    private EditText editText;
    private SQLiteOpenHelperHelper sqLiteOpenHelperHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_test);

        editText = findViewById(R.id.data_test_edit);

        // sql 数据库路径: /data/data/com.example.yongming.kai/databases/BookStore.db
        sqLiteOpenHelperHelper = new SQLiteOpenHelperHelper(this, "BookStore.db", null, 1);

        loadData();

        Button button = findViewById(R.id.data_test_read);
        button.setOnClickListener(this);

        button = findViewById(R.id.data_test_write);
        button.setOnClickListener(this);

        button = findViewById(R.id.data_test_create_database);
        button.setOnClickListener(this);

        button = findViewById(R.id.data_test_insert_sql);
        button.setOnClickListener(this);

        button = findViewById(R.id.data_test_del_sql);
        button.setOnClickListener(this);

        button = findViewById(R.id.data_test_query_sql);
        button.setOnClickListener(this);

        button = findViewById(R.id.data_test_update_sql);
        button.setOnClickListener(this);
    }

    private void saveData()
    {
        String inputText = editText.getText().toString();

        FileOutputStream out;
        BufferedWriter writer = null;

        try {

            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);

        }catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (writer != null) {

                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadData()
    {
        FileInputStream in;
        BufferedReader reader = null;

        try {

            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));


            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            String result = content.toString();

            editText.setText(result);
            editText.setSelection(result.length());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveData();
    }

    @Override
    public void onClick(View view) {

        EditText shareTestEdit = findViewById(R.id.data_test_edit_sharedpreferences);

        SharedPreferences sharedPreferences = getSharedPreferences("testshare", MODE_PRIVATE);

        switch (view.getId())
        {
            case R.id.data_test_read:

                String s = sharedPreferences.getString("shareKey", "");

                shareTestEdit.setText(s);

                break;

            case R.id.data_test_write:

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("shareKey", shareTestEdit.getText().toString());

                editor.commit();

                break;

            case R.id.data_test_create_database:

                sqLiteOpenHelperHelper.getWritableDatabase();

                break;

            case R.id.data_test_insert_sql:

                EditText bookName = findViewById(R.id.data_test_edit_sqlite);
                String name = bookName.getText().toString();
                if (!isBooknameValid(name)) {
                    break;
                }

                SQLiteDatabase db = sqLiteOpenHelperHelper.getWritableDatabase();

                // 往表里加数据，Android除了拼sql，还有另外一个对象，简化sql操作。ContentValues，像个字典，提供put方法，指定字段即可。
                ContentValues values = new ContentValues();

                values.put("name", name);
                values.put("author", "吴承恩");
                values.put("pages", 860000);
                values.put("price", 42);

                // 此处调用SQLiteDatabase 的 insert
                db.insert("book", null, values);

                break;

            case R.id.data_test_del_sql:

                EditText bookName3 = findViewById(R.id.data_test_edit_sqlite);
                String delname = bookName3.getText().toString();
                if (!isBooknameValid(delname)) {
                    break;
                }

                SQLiteDatabase dbDel = sqLiteOpenHelperHelper.getWritableDatabase();
                dbDel.delete("book", "name = ?", new String[] {
                        delname
                });

                break;

            case R.id.data_test_query_sql:

                /*
                *
                * query()方法参数
                    对应 SQL 部分
                    描述
                    table
                    from table_name
                    指定查询的表名
                    columns
                    select column1, column2
                    指定查询的列名
                    selection
                    where column = value
                    指定 where 的约束条件
                    selectionArgs
                    -
                    为 where 中的占位符提供具体的值
                    groupBy
                    group by column
                    指定需要 group by 的列
                    having
                    having column = value
                    对 group by 后的结果进一步约束
                    orderBy
                    order by column1, column2
                    指定查询结果的排序方式

                    第N个参数的意义:
                    1. 表名
                    2. 哪几列？ 过滤列
                    3、4 约束哪几行
                    5、group by 的列
                    6、group by 之后进一步的过滤
                    7、排序方式

                * */

                SQLiteDatabase dbQuery = sqLiteOpenHelperHelper.getWritableDatabase();

                Cursor cursor = dbQuery.query("book", null, null, null, null, null, null);
                int nCount = 0;
                if (cursor.moveToFirst()) {

                    do {

                        String nameQuery = cursor.getString(cursor.getColumnIndex("name"));
                        Log.i("ymdb", nameQuery);
                        nCount ++;

                    } while (cursor.moveToNext());
                }

                cursor.close();

                String result = "共 " + nCount + " 条记录";
                Toast.makeText(DataTestActivity.this, result, Toast.LENGTH_SHORT).show();

                break;

            case R.id.data_test_update_sql:
                EditText bookName2 = findViewById(R.id.data_test_edit_sqlite);
                String name2 = bookName2.getText().toString();
                if (!isBooknameValid(name2)) {
                    break;
                }

                // 此处的ContentValues 是设置的更改的内容 键值对
                ContentValues updateValues = new ContentValues();
                updateValues.put("name", name2);

                SQLiteDatabase dbUpdate = sqLiteOpenHelperHelper.getWritableDatabase();

                // 此处调用SQLiteDatabase 的 update
                dbUpdate.update("book", updateValues, "price = ?", new String[] {
                        "42"
                });

                break;
        }
    }

    private boolean isBooknameValid(String name)
    {
        if (TextUtils.isEmpty(name)) {

            Toast.makeText(DataTestActivity.this, "请添加名字再试", Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;
    }
}
