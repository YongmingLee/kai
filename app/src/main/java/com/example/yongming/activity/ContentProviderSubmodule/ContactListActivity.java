package com.example.yongming.activity.ContentProviderSubmodule;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.yongming.activity.BaseListActivity;

import java.util.ArrayList;

public class ContactListActivity extends BaseListActivity {

    private ArrayList<String> contacts = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadContacts();
    }

    @Override
    public ArrayList<String> CustomDataSource() {
        return contacts;
    }

    @Override
    public void OnListItemClick(int position) {
        super.OnListItemClick(position);
    }

    private void loadContacts()
    {
        Cursor cursor = null;

        try {

            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            while (cursor.moveToNext()) {

                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                String result = displayName + "\n" + phoneNumber;

                contacts.add(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        // 调用父类适配器更新数据源
        reloadData();

        Toast.makeText(this, "读取完成", Toast.LENGTH_SHORT).show();
    }
}
