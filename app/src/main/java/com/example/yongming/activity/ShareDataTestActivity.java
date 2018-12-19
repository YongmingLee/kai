package com.example.yongming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yongming.activity.ShareTest.ContactListActivity;

import java.util.ArrayList;

public class ShareDataTestActivity extends BaseListActivity {

    private ArrayList<String> listDatas = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        listDatas.add("读取通讯录");
        listDatas.add("其它");

        super.onCreate(savedInstanceState);
    }

    @Override
    public ArrayList<String> CustomDataSource() {
        return listDatas;
    }

    @Override
    public void OnListItemClick(int position) {
        switch (position)
        {
            case 0:

                Intent contactIntent = new Intent(ShareDataTestActivity.this, ContactListActivity.class);
                startActivity(contactIntent);

                break;
        }
    }


}
