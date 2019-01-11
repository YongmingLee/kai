package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.fragment.VPCloneFragment;
import com.example.yongming.manager.YMFieldHelper;
import com.example.yongming.protocol.YMField;

import java.util.ArrayList;
import java.util.List;

public class VPIncludeVPActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<VPCloneFragment> pagerFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_scroll_test);

        viewPager = findViewById(R.id.avst_viewpager);
        tabLayout = findViewById(R.id.avst_tablayout);

        setupViewPager();
    }

    private void setupViewPager()
    {
        final String[] tabnames = {"微信","通讯录","发现","我"};

        for (int i = 0; i < tabnames.length; i ++) {

            VPCloneFragment pagerFragment = new VPCloneFragment();
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
                return tabnames[position];
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
