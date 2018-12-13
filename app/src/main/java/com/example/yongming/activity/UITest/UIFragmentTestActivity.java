package com.example.yongming.activity.UITest;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;

public class UIFragmentTestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_fragment);
    }
}
