package com.example.yongming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yongming.activity.ActivitySubmodule.EventBusTestActivity;

import java.util.ArrayList;

public class ThirdPartyTestActivity extends BaseListActivity {
    private ArrayList<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mDatas.add("EventBus 测试");

        super.onCreate(savedInstanceState);
    }

    @Override
    public ArrayList<String> CustomDataSource() {
        return mDatas;
    }

    @Override
    public void OnListItemClick(int position) {
        super.OnListItemClick(position);

        Intent intent = null;

        switch (position) {
            case 0:
                intent = new Intent(this, EventBusTestActivity.class);
                break;
        }

        if (intent != null) {
            intent.putExtra("name", mDatas.get(position));
            startActivity(intent);
        }
    }
}
