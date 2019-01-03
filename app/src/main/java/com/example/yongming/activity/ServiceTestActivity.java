package com.example.yongming.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yongming.activity.ActivitySubmodule.AsyncTaskTestActivity;
import com.example.yongming.activity.ActivitySubmodule.MultiThreadTestActivity;
import com.example.yongming.activity.ActivitySubmodule.ServiceTestMoreActivity;

import java.util.ArrayList;

public class ServiceTestActivity extends BaseListActivity {

    private ArrayList<String> listDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        listDatas.add("多线程Thread子类-Runnable");
        listDatas.add("多线程AsyncTask");
        listDatas.add("服务");

        super.onCreate(savedInstanceState);
    }

    @Override
    public ArrayList<String> CustomDataSource() {
        return listDatas;
    }

    @Override
    public void OnListItemClick(int position) {
        super.OnListItemClick(position);

        Intent intent = null;

        switch (position)
        {
            case 0:

                intent = new Intent(ServiceTestActivity.this, MultiThreadTestActivity.class);
                break;

            case 1:

                intent = new Intent(ServiceTestActivity.this, AsyncTaskTestActivity.class);
                break;

            case 2:

                intent = new Intent(ServiceTestActivity.this, ServiceTestMoreActivity.class);
                break;
        }

        if (intent != null) {
            intent.putExtra("name", listDatas.get(position));
            startActivity(intent);
        }
    }
}
