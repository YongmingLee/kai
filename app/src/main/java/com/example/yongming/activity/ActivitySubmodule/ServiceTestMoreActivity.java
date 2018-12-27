package com.example.yongming.activity.ActivitySubmodule;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.service.MyIntentService;
import com.example.yongming.service.MyService;


/*
* Activity 不能直接调用Service里的方法，要通过一个中间人：ServiceConnection
* 总结：
*
* 一、服务，是安卓的四大组件之一，类似Windows的服务，常驻于后台，可以做一些不需要UI交互的任务。
*
* 二、数据流：
* Activity 和 Service 之间通过 配置 ServiceConnection 子类 进行数据交互。
*
* 启动服务之时，完成两者的关联。 ServiceConnection 可以通过Binder拿到 Service实例，通过
* 设置Callback回调，来实现主动和被动的调用。
*
* 详细见以下代码：
*
* demo实现了 Activity主动启动Service，Service 启动后会默认开启一个定时器，定时的向注册回调接口的
* 类反馈。
*
* 同时Activity也可以调用 service实例里的方法。
*
* 达到了两者的数据互通。
*
* */

public class ServiceTestMoreActivity extends BaseActivity implements View.OnClickListener {

    private MyService.DownloadBinder downloadBinder = null;
    private TextView textView;


    // 显示从Service 的TimerTask 中传递出来的 内容，更新UI。原因是TimerTask 处于子线程。

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    textView.setText((String)msg.obj);
            }
        }
    };

    private ServiceConnection serviceConnection = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);

        Button button = findViewById(R.id.service_test_start_btn);
        button.setOnClickListener(this);

        button = findViewById(R.id.service_test_stop_btn);
        button.setOnClickListener(this);

        button = findViewById(R.id.service_test_bind_btn);
        button.setOnClickListener(this);

        button = findViewById(R.id.service_test_unbind_btn);
        button.setOnClickListener(this);

        button = findViewById(R.id.service_test_call_btn);
        button.setOnClickListener(this);

        button = findViewById(R.id.service_test_intent_btn);
        button.setOnClickListener(this);

        textView = findViewById(R.id.service_test_text);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.service_test_start_btn:

                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);

                break;

            case R.id.service_test_stop_btn:

                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);

                break;

            case R.id.service_test_bind_btn:

                serviceConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

                        Log.i("mys", "onServiceConnected - thread:(" + Thread.currentThread().getName() + "}");

                        downloadBinder = (MyService.DownloadBinder) iBinder;
                        downloadBinder.startDownload();
                        downloadBinder.getProgress();


                        MyService myService = downloadBinder.getService();

                        myService.setCallback(new MyService.Callback() {
                            @Override
                            public void getNum(int num) {

//                    Log.i("mys", "Activity("+ Thread.currentThread().getName() + ") value from service: " + num);

                                String obj = "Activity("+ Thread.currentThread().getName() + ") value from service: " + num;

                                Message msg = new Message();
                                msg.what = 0;
                                msg.obj = obj;
                                handler.sendMessage(msg);
                            }
                        });
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {

                        Log.i("mys", "onServiceDisconnected - thread:(" + Thread.currentThread().getName() + "}");
                    }

                    public void callFunction()
                    {
                        downloadBinder.getProgress();
                    }
                };

                Intent newStartIntent = new Intent(this, MyService.class);
                bindService(newStartIntent, serviceConnection, BIND_AUTO_CREATE);


                break;

            case R.id.service_test_unbind_btn:

                if (serviceConnection == null)
                    break;

                unbindService(serviceConnection);
                serviceConnection = null;

                break;

            case R.id.service_test_call_btn:

                if (downloadBinder == null) {

                    Toast.makeText(this, "Please bind service first", Toast.LENGTH_SHORT).show();

                    break;
                }

                MyService myService = downloadBinder.getService();
                int n = myService.testCallbackFunction();
                Log.i("mys", "call result:(" + n + ")");

                break;

            case R.id.service_test_intent_btn:

                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);

                break;
        }
    }

    @Override
    protected void onDestroy() {
        Log.i("mys", "Service activity onDestroy");
        super.onDestroy();
    }
}
