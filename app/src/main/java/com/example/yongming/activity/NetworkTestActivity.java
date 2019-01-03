package com.example.yongming.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yongming.activity.ActivitySubmodule.HttpTestActivity;
import com.example.yongming.activity.ActivitySubmodule.WebviewTestActivity;

import java.util.ArrayList;

public class NetworkTestActivity extends BaseListActivity {
    private ArrayList<String> listData = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        listData.add("WebView 测试");
        listData.add("HTTP 测试");

        super.onCreate(savedInstanceState);
    }

    @Override
    public ArrayList<String> CustomDataSource() {
        return listData;
    }

    @Override
    public void OnListItemClick(int position) {
        super.OnListItemClick(position);

        Intent intent = null;

        switch (position)
        {
            case 0:

                intent = new Intent(this, WebviewTestActivity.class);
                break;

            case 1:

                intent = new Intent(this, HttpTestActivity.class);
                break;
        }

        if (intent != null) {
            intent.putExtra("name", listData.get(position));
            startActivity(intent);
        }
    }
}

