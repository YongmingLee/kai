package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.module.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class EventBusChildTestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_child_test);

        EventBus.getDefault().post(new MessageEvent("hello eventbus", "haha"));
    }
}
