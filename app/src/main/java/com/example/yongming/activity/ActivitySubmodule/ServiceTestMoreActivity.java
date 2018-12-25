package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;

public class ServiceTestMoreActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
