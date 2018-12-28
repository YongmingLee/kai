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

        switch (position)
        {
            case 0:

                Intent webviewIntent = new Intent(this, WebviewTestActivity.class);
                startActivity(webviewIntent);

                break;

            case 1:

                Intent httpIntent = new Intent(this, HttpTestActivity.class);
                startActivity(httpIntent);

                break;
        }
    }
}

