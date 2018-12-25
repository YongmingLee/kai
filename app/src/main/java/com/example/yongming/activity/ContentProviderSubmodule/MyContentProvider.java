package com.example.yongming.activity.ContentProviderSubmodule;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.yongming.manager.SQLiteOpenHelperHelper;

/*
* ContentProvider 实际上实现了跨进程访问的一种低耦合机制
* 它提供了外部进程与自身 进行 增删查改 等方式进行数据共享
* ContentProvider的实现者，对增删查改可以有不同的实现，SQLite，MongoDB，or 其它方式
* 这也是它机制的低耦合优点，可以随时变换策略满足数据共享需求。
*
*
* ***************************
* 总结：
* ContentProvider 通过Uri的方式 实现了仅通过协议就可以CRUD其它进程。
* 被CRUD方：
* 1、先派生ContentProvider，重写其增删查改，里面到底用SQLite or 其它，你随便。
* 2、在Manifest 声明provider，并将exported = true。其它进程拥有权限
* 3、uri的规则在Android系统中是有规则的，按规则写就行，详细见下面代码
*
* 调用CRUD方：
* 1、通过Uri获得ContentResolver，然后进行CRUD操作即可
* 代码如下：
*               try {
                    // 添加数据
                    Uri uri = Uri.parse("content://com.example.yongming.kai.provider/book");
                    ContentValues values = new ContentValues();
                    values.put("name", "A Clash of Kings");
                    values.put("author", "George Martin");
                    values.put("pages", 1040);
                    values.put("price", 22.85);
                    Uri newUri = getContentResolver().insert(uri, values);
                    String newId = newUri.getPathSegments().get(1);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }

  扩展脑洞：
  可以专门一个进程负责数据库，主进程负责调度。
*
* ***************************
*
* */

public class MyContentProvider extends ContentProvider {

    private static UriMatcher uriMatcher;

    /*
    * 将希望匹配的通配符传到 UriMatcher 中
    * */
    static {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI("com.example.yongming.kai.provider", "book", 0);
        uriMatcher.addURI("com.example.yongming.kai.provider", "book#", 1);

    }


    private SQLiteOpenHelperHelper sqLiteOpenHelperHelper;


    /*
    * 完成数据库的创建
    * */
    @Override
    public boolean onCreate()
    {
        sqLiteOpenHelperHelper = new SQLiteOpenHelperHelper(getContext(), "BookStore.db", null, 1);

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        Cursor cursor = null;

        switch (uriMatcher.match(uri)) {
            case 0:
            case 1:

                SQLiteDatabase db = sqLiteOpenHelperHelper.getReadableDatabase();

                cursor = db.query("book", strings, s, strings1, null, null, s1);

                break;
            default:
                break;
        }

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues)
    {
        Uri uriReturn = null;

        switch (uriMatcher.match(uri)) {
            case 0:

                SQLiteDatabase db = sqLiteOpenHelperHelper.getWritableDatabase();

                // 此处调用SQLiteDatabase 的 insert
                long newBookId = db.insert("book", null, contentValues);

                uriReturn = Uri.parse("content://" + "com.example.yongming.kai.provider" + "/book/" + newBookId);

                break;
            default:
                break;
        }

        return uriReturn;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }


    /*
    * 除此之外，还有一个方法你会比较陌生，即 getType()方法。它是所有的内容提供器都必 须提供的一个方法，用于获取 Uri 对象所对应的 MIME 类型。
    * 一个内容 URI 所对应的 MIME 字符串主要由三部分组分，Android 对这三个部分做了如下格式规定。

    1. 必须以 vnd 开头。
    2. 如果内容 URI 以路径结尾，则后接 android.cursor.dir/，如果内容 URI 以 id 结尾，
    则后接 android.cursor.item/。
    3. 最后接上 vnd.<authority>.<path>。
    所以，对于 content://com.example.app.provider/table1 这个内容 URI，它所对应的 MIME
    类型就可以写成:
    vnd.android.cursor.dir/vnd.com.example.app.provider.table1
    对于 content://com.example.app.provider/table1/1 这个内容 URI，它所对应的 MIME 类型 就可以写成:
    vnd.android.cursor.item/vnd. com.example.app.provider.table1
    * */

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        String returnType = "";
        switch (uriMatcher.match(uri))
        {
            case 0:
                returnType = "vnd.android.cursor.dir/vnd.com.example.yongming.kai.provider.book";
                break;
            case 1:
                returnType = "vnd.android.cursor.item/vnd.com.example.yongming.kai.provider.book";
                break;
        }
        return returnType;
    }
}


/*
*
*
* com.example.yongming.kai.provider
*
* app 存了两张表   table1, table2
*
* 路径：com.example.yongming.kai.provider/table1, com.example.yongming.kai.provider/table2
* 这样很难看出是Content URI, 在前面加上协议 content://
*
* 于是，最终的content uri : content://com.example.yongming.kai.provider/table1, content://com.example.yongming.kai.provider/table2
*
* */
