package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.adapter.ViewPagerNormalAdapter;
import com.example.yongming.manager.YMFieldHelper;
import com.example.yongming.protocol.YMField;
import java.util.ArrayList;

public class ViewPagerNormalTestActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ArrayList<String> lists = new ArrayList<>();

    @YMField(R.id.atnv_viewpager)
    public ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_normal_viewpager);

        YMFieldHelper.processAnnotation(this.getClass(), this, this);

        lists.add("第一页");
        lists.add("第二页");
        lists.add("第三页");

        viewPager.setAdapter(new ViewPagerNormalAdapter(this, lists));
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageSelected(int position) {
        Log.i("vpnta", "position:" + position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i("vpnta", "position:" + position + " positionOffset:" + positionOffset);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.i("vpnta", "state:" + state);
    }
}
