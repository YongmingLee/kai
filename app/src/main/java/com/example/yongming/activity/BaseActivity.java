package com.example.yongming.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yongming.manager.ActivityLifeManager;
import com.example.yongming.manager.YMFieldHelper;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("ym_base", "onCreate class: " + getClass().getSimpleName());

        ActivityLifeManager.addActivity(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (name != null) {
            this.setTitle(name);
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityLifeManager.removeActivity(this);

        Log.i("ym_base", "onDestroy class: " + getClass().getSimpleName());
    }
}
