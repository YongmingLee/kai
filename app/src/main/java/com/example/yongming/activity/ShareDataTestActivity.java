package com.example.yongming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yongming.activity.ContentProviderSubmodule.ContactListActivity;
import com.example.yongming.activity.ContentProviderSubmodule.ContentProviderTestActivity;

import java.util.ArrayList;

public class ShareDataTestActivity extends BaseListActivity {

    private ArrayList<String> listDatas = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        listDatas.add("读取通讯录");
        listDatas.add("自身提供ConetentProvider");

        super.onCreate(savedInstanceState);
    }

    @Override
    public ArrayList<String> CustomDataSource() {
        return listDatas;
    }

    @Override
    public void OnListItemClick(int position) {

        Intent intent = null;

        switch (position)
        {
            case 0:
                intent = new Intent(ShareDataTestActivity.this, ContactListActivity.class);
                break;

            case 1:
                intent = new Intent(ShareDataTestActivity.this, ContentProviderTestActivity.class);
                break;
        }

        if (intent != null) {
            intent.putExtra("name", listDatas.get(position));
            startActivity(intent);
        }
    }
}
