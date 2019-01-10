package com.example.yongming.activity.ActivitySubmodule;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.adapter.ViewPagerNormalAdapter;
import com.example.yongming.manager.YMFieldHelper;
import com.example.yongming.protocol.YMField;
import java.util.ArrayList;

/*
* ViewPager 类似ScrollView，里面内容通过Adapter指定。
* Google支持了常用的TabLayout，实现标题联动
* */

public class ViewPagerNormalTestActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ArrayList<String> lists = new ArrayList<>();

    @YMField(R.id.atnv_viewpager)
    public ViewPager viewPager;

    @YMField(R.id.atnv_tablayout)
    public TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_normal_viewpager);

        YMFieldHelper.processAnnotation(this.getClass(), this, this);

        for (int i = 0; i < 15; i ++) {
            String name = "第" + (i+1) + "页";
            lists.add(name);
        }

        // 关联ViewPager 和 Adapter
        viewPager.setAdapter(new ViewPagerNormalAdapter(this, lists));
        viewPager.addOnPageChangeListener(this);

        // 添加Tab item
        for (String name : lists) {

            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(name);
            tabLayout.addTab(tab);
        }

        // 设置关联联动
        tabLayout.setupWithViewPager(viewPager, false);

        // 设置转场动画
        viewPager.setPageTransformer(false, new ZoomOutPageTransformer());
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

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer
    {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;
        @SuppressLint("NewApi")
        public void transformPage(View view, float position)
        {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();
            Log.e("TAG", view + " , " + position + "");
            if (position < -1)
            { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0)
                {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else
                {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }
                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                        / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else
            { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
