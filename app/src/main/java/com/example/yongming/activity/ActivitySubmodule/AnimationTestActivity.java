package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.manager.YMFieldHelper;
import com.example.yongming.protocol.YMField;

public class AnimationTestActivity extends BaseActivity implements View.OnClickListener {

    @YMField(R.id.ata_textview)
    public TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);

        int[] btns = {R.id.ata_ani_btn0, R.id.ata_ani_btn1, R.id.ata_ani_btn2};
        for (int btn : btns) {
            Button button = findViewById(btn);
            button.setOnClickListener(this);
        }

        YMFieldHelper.processAnnotation(AnimationTestActivity.class, this, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ata_ani_btn0:

                // XML 定义了插值器，约定动画执行的加速度
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.base_anim);
                textView.startAnimation(animation);

                break;

            case R.id.ata_ani_btn1:



                break;

            case R.id.ata_ani_btn2:
                break;
        }
    }
}
