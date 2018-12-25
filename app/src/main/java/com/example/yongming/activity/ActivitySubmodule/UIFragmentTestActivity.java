package com.example.yongming.activity.ActivitySubmodule;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;

public class UIFragmentTestActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_fragment);


        Button button = (Button)findViewById(R.id.fta_left_button);
        button.setOnClickListener(this);

        Button buttonCall = (Button)findViewById(R.id.fta_left_button_call);
        buttonCall.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        Log.i("ym", "onClick");

        switch (view.getId()) {
            case R.id.fta_left_button:

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FTAOtherFragment ftaOtherFragment = new FTAOtherFragment();

                fragmentTransaction.replace(R.id.fta_right_layout, ftaOtherFragment);

                /*
                * 加入返回栈，按下安卓的返回键会支持后退操作，否则，直接返回上一个Activity
                * 由于有返回栈，被替换掉(FragmentTransaction.replace)的Fragment还会停留在内存里。
                * 不添加到栈或者remove掉，fragment会被移除。
                * */
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                break;

            case R.id.fta_left_button_call:

                FTARightFragment ftaOtherFragment1 = (FTARightFragment) getFragmentManager().findFragmentById(R.id.fta_right_fragment);
                if (ftaOtherFragment1 != null) {
                    ftaOtherFragment1.testFunction();
                }

                break;

            default:
                break;
        }


    }
}
