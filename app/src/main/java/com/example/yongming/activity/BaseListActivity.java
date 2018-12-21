package com.example.yongming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BaseListActivity extends BaseActivity {

    private ListView listView;
    protected TextView textView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_list);

        listView = findViewById(R.id.base_listactivity_listview);
        textView = findViewById(R.id.base_listactivity_edit);

        textView.setVisibility(View.GONE);

        ArrayList<String> arrayList = this.CustomDataSource();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OnListItemClick(i);
            }
        });
    }

    public ArrayList<String> CustomDataSource()
    {
        return null;
    }

    public void OnListItemClick(int position)
    {}

    public void reloadData()
    {
        this.arrayAdapter.notifyDataSetChanged();
    }
}
