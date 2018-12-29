package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class HttpTestActivity extends BaseActivity implements View.OnClickListener {

    private EditText editText;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    editText.setText(msg.obj.toString());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);

        int ids[] = {R.id.http_test_read_btn, R.id.http_test_httpclient_btn};

        for (int n : ids) {
            Button button = findViewById(n);
            button.setOnClickListener(this);
        }

        editText = findViewById(R.id.http_test_edit);
    }

    private void readFromURLConnection()
    {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {

                    URL url = new URL("https://www.baidu.com");

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setReadTimeout(8000);
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setRequestMethod("GET");

                    InputStream inputStream = httpURLConnection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder response = new StringBuilder();

                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

//                    Log.i("hta", "response:" + response);


                    Message message = new Message();
                    message.obj = response;
                    message.what = 0;
                    handler.sendMessage(message);

                    httpURLConnection.disconnect();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.http_test_read_btn:

                readFromURLConnection();

                break;

            case R.id.http_test_httpclient_btn:

                Toast.makeText(this, "HttpClient 已废弃", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
