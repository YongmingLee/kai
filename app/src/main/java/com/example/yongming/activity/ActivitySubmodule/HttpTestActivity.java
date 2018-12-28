package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);

        int ids[] = {R.id.http_test_read_btn, R.id.http_test_httpclient_btn};

        for (int i = 0; i < ids.length; i ++) {
            Button button = findViewById(ids[i]);
            button.setOnClickListener(this);
        }
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

                    Log.i("hta", "response:" + response);

                    httpURLConnection.disconnect();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void readFromHttpClient()
    {
        
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.http_test_read_btn:

                readFromURLConnection();

                break;

            case R.id.http_test_httpclient_btn:

                readFromHttpClient();

                break;
        }
    }
}
