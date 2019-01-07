package com.example.yongming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.example.yongming.activity.ActivitySubmodule.ReflectTestActivity;
import com.example.yongming.activity.ActivitySubmodule.RecyclerViewActivity;
import com.example.yongming.activity.ActivitySubmodule.UIFragmentTestActivity;
import com.example.yongming.activity.ActivitySubmodule.UITestNineActivity;

import java.util.ArrayList;

public class UITestActivity extends BaseListActivity {

    private ArrayList<String> listDatas = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        listDatas.add("点九图测试");
        listDatas.add("Fragment测试");
        listDatas.add("RecyclerView");
        listDatas.add("反射和注解");

        super.onCreate(savedInstanceState);
    }

    public ArrayList<String> CustomDataSource()
    {
        return listDatas;
    }

    public void OnListItemClick(int position)
    {
        Intent intent = null;

        switch (position)
        {
            case 0:
                intent = new Intent(this, UITestNineActivity.class);
                break;

            case 1:
                intent = new Intent(this, UIFragmentTestActivity.class);
                break;

            case 2:
                intent = new Intent(this, RecyclerViewActivity.class);
                break;

            case 3:
                intent = new Intent(this, ReflectTestActivity.class);
                break;
        }

        if (intent != null) {
            intent.putExtra("name", listDatas.get(position));
            startActivity(intent);
        }
    }
}
