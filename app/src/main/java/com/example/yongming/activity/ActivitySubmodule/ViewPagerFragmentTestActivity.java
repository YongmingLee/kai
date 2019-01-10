package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.fragment.PagerFragment;
import com.example.yongming.manager.YMFieldHelper;
import com.example.yongming.protocol.YMField;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragmentTestActivity extends BaseActivity {

    @YMField(R.id.avft_viewpager)
    public ViewPager viewPager;

    @YMField(R.id.avft_tablayout)
    public TabLayout tabLayout;

    private List<PagerFragment> pagerFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_fragment_test);

        YMFieldHelper.processAnnotation(this.getClass(), this, this);

        setupViewPager();
    }

    private void setupViewPager()
    {
        for (int i = 0; i < 5; i ++) {

            PagerFragment pagerFragment = new PagerFragment();
            pagerFragments.add(pagerFragment);
        }

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return pagerFragments.get(position);
            }

            @Override
            public int getCount() {
                return pagerFragments.size();
            }

            /*
            * 这个接口方法必须实现，否则TabLayout显示空白
            * */
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "第" + position + "页";
            }
        });

        for (int i = 0; i < pagerFragments.size(); i ++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tabLayout.addTab(tab);
        }

        // 设置关联联动
        tabLayout.setupWithViewPager(viewPager, false);
    }
}
