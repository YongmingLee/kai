package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.adapter.FruitAdapter;
import com.example.yongming.module.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewActivity extends BaseActivity {

    private List<Fruit> fruits = new ArrayList<Fruit>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_recyclerview);

        initFruits();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.at_recycler_view);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        FruitAdapter fruitAdapter = new FruitAdapter(fruits);

        recyclerView.setAdapter(fruitAdapter);
    }

    private void initFruits()
    {
        for (int i = 0; i < 100; i ++) {
            Fruit fi = new Fruit();
            fi.name = getRandomLenghtName("苹果");

            fruits.add(fi);
        }
    }

    private String getRandomLenghtName(String name)
    {
        Random random = new Random();

        int length = random.nextInt(20) + 1;

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i ++) {

            builder.append(name);
        }

        return builder.toString();
    }
}
