package com.example.yongming.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class SQLiteOpenHelperHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";
    private Context mContext;

    /*
    * 构造函数
    * */
    public SQLiteOpenHelperHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }


    /*
    * 数据库初始化
    * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_BOOK);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }


    /*
    * 数据库升级
    *
    * 这里请注意一个非常重要的细节，switch 中每一个 case 的最后都是没有使用 break 的， 为什么要这么做呢?这是为了保证在跨版本升级的时候，
    * 每一次的数据库修改都能被全部执 行到。比如用户当前是从第二版程序升级到第三版程序的，那么 case 2 中的逻辑就会执行。 而如果用户是直接
    * 从第一版程序升级到第三版程序的，那么 case 1 和 case 2 中的逻辑都会执 行。使用这种方式来维护数据库的升级，不管版本怎样更新，都可以
    * 保证数据库的表结构是 最新的，而且表中的数据也完全不会丢失了。
    *
    * */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        switch (oldVersion) {
            case 1:
                // 根据版本做响应的sql处理，保障升级数据库or表的同时，保留数据
                // sqLiteDatabase.execSQL("balabala...");
            case 2:

                // sqLiteDatabase.execSQL("alter table book add column category_id integer");
            case 3:

                default:
                    break;
        }
    }
}
