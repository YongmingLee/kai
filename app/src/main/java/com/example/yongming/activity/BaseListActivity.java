package com.example.yongming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BaseListActivity extends BaseActivity {

    private ListView listView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_list);

        listView = (ListView)findViewById(R.id.base_listactivity_listview);

        ArrayList<String> arrayList = this.CustomDataSource();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
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
