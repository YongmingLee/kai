package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;

/*
* 异步消息处理机制
*
* 主要由四部分组成：Message, Handler, MessageQueue, Looper
*
*

1. Message
Message 是在线程之间传递的消息，它可以在内部携带少量的信息，用于在不同线
程之间交换数据。上一小节中我们使用到了 Message 的 what 字段，除此之外还可以使 用 arg1 和 arg2 字段来携带一些整型数据，使用 obj 字段携带一个 Object 对象。
2. Handler
Handler 顾名思义也就是处理者的意思，它主要是用于发送和处理消息的。发送消 息一般是使用 Handler 的 sendMessage()方法，而发出的消息经过一系列地辗转处理后，
最终会传递到 Handler 的 handleMessage()方法中。
3. MessageQueue
MessageQueue 是消息队列的意思，它主要用于存放所有通过 Handler 发送的消息。 这部分消息会一直存在于消息队列中，等待被处理。每个线程中只会有一个 MessageQueue 对象。
4. Looper
Looper 是每个线程中的 MessageQueue 的管家，调用 Looper 的 loop()方法后，就会 进入到一个无限循环当中，然后每当发现 MessageQueue 中存在一条消息，就会将它取 出，
并传递到 Handler 的 handleMessage()方法中。每个线程中也只会有一个 Looper 对象。

* */

public class MultiThreadTestActivity extends BaseActivity implements View.OnClickListener {

    public static final int UPDATE_TEXT = 1;

    private TextView textView;

    private Handler handler  = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case UPDATE_TEXT:
                    textView.setText((String)msg.obj);

                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multithread_test);

        textView = findViewById(R.id.thread_test_text);

        Button button;

        button = findViewById(R.id.thread_test_btn1);
        button.setOnClickListener(this);

        button = findViewById(R.id.thread_test_btn2);
        button.setOnClickListener(this);

        Log.i("ymt", "主线程" + Thread.currentThread().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("ymt", "onDestroy");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.thread_test_btn1:

                // 1 传统方式
                MyThread thread = new MyThread();
                thread.start();

                // 2 匿名类
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("ymt", "匿名类回调" + Thread.currentThread().getName());

                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        message.obj = "nice to meet you";
                        handler.sendMessage(message);
                    }
                }).start();

                break;


            case R.id.thread_test_btn2:
                break;
        }
    }

    class MyThread extends Thread
    {
        @Override
        public void run() {
            super.run();

            Log.i("ymt", "传统方式调用" + Thread.currentThread().getName());
        }
    }
}
