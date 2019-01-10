package com.example.yongming.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yongming.activity.R;
import com.example.yongming.adapter.ScrollFragmentRecyclerViewAdapter;
import com.example.yongming.manager.YMFieldHelper;
import com.example.yongming.protocol.YMField;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PagerScrollFragment extends Fragment {

    public RecyclerView recyclerView;

    private List<String> mdatas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_scroll, container, false);
        recyclerView = view.findViewById(R.id.fts_recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData()
    {
        setupRecyclerView();
    }

    private void setupRecyclerView()
    {
        for (int i = 0; i < 20; i ++) {
            String name = "第" + i + "项";
            String finalName = name + " " + getRandomLenghtName("测试数据");
            mdatas.add(finalName);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new ScrollFragmentRecyclerViewAdapter(mdatas));
    }

    private String getRandomLenghtName(String name)
    {
        Random random = new Random();

        int length = random.nextInt(100) + 1;

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i ++) {
            builder.append(name);
        }

        return builder.toString();
    }
}
