package com.example.yongming.activity.ContentProviderTest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.yongming.activity.BaseListActivity;
import com.example.yongming.manager.SQLiteOpenHelperHelper;

import java.util.ArrayList;

public class ContentProviderTestActivity extends BaseListActivity {

    private ArrayList<String> listDatas = new ArrayList<String>();
    private SQLiteOpenHelperHelper sqLiteOpenHelperHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView.setVisibility(View.VISIBLE);

        sqLiteOpenHelperHelper = new SQLiteOpenHelperHelper(this, "BookStore.db", null, 1);
        textView.setText("自身提供ContentProvider，外部另外一个进程访问CP，并修改，下面的列表显示外部修改的结果。数据层使用SQLite。");

        loadData();
    }

    @Override
    public ArrayList<String> CustomDataSource() {
        return listDatas;
    }

    @Override
    public void OnListItemClick(int position) {
        super.OnListItemClick(position);
    }

    private void loadData() {
        SQLiteDatabase dbQuery = sqLiteOpenHelperHelper.getWritableDatabase();

        Cursor cursor = dbQuery.query("book", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            do {

                String nameQuery = cursor.getString(cursor.getColumnIndex("name"));
                Log.i("ymdb", nameQuery);
                listDatas.add(nameQuery);

            } while (cursor.moveToNext());
        }

        cursor.close();

        reloadData();
    }
}
