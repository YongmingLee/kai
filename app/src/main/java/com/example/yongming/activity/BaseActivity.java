package com.example.yongming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yongming.manager.ActivityLifeManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("ym", "class: " + getClass().getSimpleName());

        ActivityLifeManager.addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityLifeManager.removeActivity(this);
    }
}