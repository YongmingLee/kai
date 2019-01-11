package com.example.yongming.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yongming.activity.R;
import java.util.ArrayList;
import java.util.List;

public class VPCloneFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<PagerScrollFragment> pagerFragments = new ArrayList<>();
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {

            rootView = inflater.inflate(R.layout.activity_test_normal_viewpager, container, false);
        }
        else {

            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }

        Log.i("VPCF", "Fragment onCreateView:" + rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i("VPCF", "Fragment setupViewPager from View" + getView());

        viewPager = getView().findViewById(R.id.atnv_viewpager);
        tabLayout = getView().findViewById(R.id.atnv_tablayout);

        setupViewPager();
    }

    private void setupViewPager()
    {
        pagerFragments.clear();
        tabLayout.removeAllTabs();

        for (int i = 0; i < 5; i ++) {

            PagerScrollFragment pagerFragment = new PagerScrollFragment();
            pagerFragments.add(pagerFragment);
        }

        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
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
                return "第" + position + "子项";
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
