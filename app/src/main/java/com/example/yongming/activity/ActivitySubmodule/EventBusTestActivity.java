package com.example.yongming.activity.ActivitySubmodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.module.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusTestActivity extends BaseActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_test);

        int btns[] = {R.id.aebt_btn};
        for (int btnId : btns) {
            Button button = findViewById(btnId);
            button.setOnClickListener(this);
        }

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aebt_btn:

                Intent intent = new Intent(EventBusTestActivity.this, EventBusChildTestActivity.class);
                intent.putExtra("name", "EventBus 激活");
                startActivity(intent);

                break;
            default:
                break;
        }
    }

    // EventBus 响应函数的作用域必须是Public!
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusEventHandler(MessageEvent msg)
    {
        Log.i("eventbuskey", "content is " + msg.name);
    }
}
