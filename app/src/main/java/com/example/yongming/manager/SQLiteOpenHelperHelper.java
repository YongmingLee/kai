package com.example.yongming.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SQLiteOpenHelperHelper extends SQLiteOpenHelper {

    /*
    * 构造函数
    * */
    public SQLiteOpenHelperHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    /*
    * 数据库初始化
    * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }


    /*
    * 数据库升级
    * */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
}
