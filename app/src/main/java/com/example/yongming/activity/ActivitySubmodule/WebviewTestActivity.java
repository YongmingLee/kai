package com.example.yongming.activity.ActivitySubmodule;

import android.content.Context;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;

public class WebviewTestActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private EditText searchInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_webview);

        int []btns = {R.id.web_left_btn, R.id.web_right_btn, R.id.web_search_btn};
        for (int i = 0; i < btns.length; i ++) {
            Button button = findViewById(btns[i]);
            button.setOnClickListener(this);
        }

        webView = findViewById(R.id.web_webview);
        searchInput = findViewById(R.id.web_input);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.web_left_btn:

                if (webView.canGoBack()) {
                    webView.goBack();
                }

                break;

            case R.id.web_right_btn:

                if (webView.canGoForward()) {
                    webView.goForward();
                }

                break;

            case R.id.web_search_btn:

                String url = searchInput.getText().toString();
                webView.loadUrl(url);

                // 隐藏软键盘
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(searchInput.getWindowToken(), 0);
                }

                break;
        }
    }
}
