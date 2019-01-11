package com.example.yongming.activity.ActivitySubmodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.yongming.activity.BaseListActivity;
import java.util.ArrayList;

public class ViewPagerTestActivity extends BaseListActivity {

    private ArrayList<String>listDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        listDatas.add("ViewPager基本使用");
        listDatas.add("ViewPager+Fragment");
        listDatas.add("ViewPager 滑动冲突");
        listDatas.add("ViewPager 内嵌 ViewPager");

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

        switch (position) {

            case 0:
                intent = new Intent(this, ViewPagerNormalTestActivity.class);
                break;
            case 1:
                intent = new Intent(this, ViewPagerFragmentTestActivity.class);
                break;
            case 2:
                intent = new Intent(this, ViewPagerScrollTestActivity.class);
                break;
            case 3:
                intent = new Intent(this, VPIncludeVPActivity.class);
                break;
        }

        if (intent != null) {
            intent.putExtra("name", listDatas.get(position));
            startActivity(intent);
        }
    }
}
