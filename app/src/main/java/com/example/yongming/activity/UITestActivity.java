package com.example.yongming.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;


import com.example.yongming.activity.UITest.UITestNineActivity;

import java.util.ArrayList;

public class UITestActivity extends BaseListActivity {

    private ArrayList<String> listDatas = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        listDatas.add("点九图测试");

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        this.setTitle(name);
    }

    public ArrayList<String> CustomDataSource()
    {
        return listDatas;
    }

    public void OnListItemClick(int position)
    {
//        Toast.makeText(this, "Click " + position, Toast.LENGTH_SHORT).show();

        switch (position)
        {
            case 0:
                Intent intent = new Intent(this, UITestNineActivity.class);
                startActivity(intent);
                break;
        }
    }
}
