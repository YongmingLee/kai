package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.task.DownloadTestTask;

public class AsyncTaskTestActivity extends BaseActivity {

    private DownloadTestTask downloadTestTask = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_service);

        downloadTestTask = new DownloadTestTask();
        downloadTestTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        downloadTestTask.cancel(false);

        Log.i("dtt", "onDestroy " + Thread.currentThread().getName());
    }
}
