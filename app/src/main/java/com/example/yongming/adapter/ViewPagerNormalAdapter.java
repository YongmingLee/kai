package com.example.yongming.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.yongming.activity.R;
import java.util.List;

/*
*
* 因为PagerAdapter 是通过Key-Value方式管理（Map）
* 所以，由 isViewFromObject 方法确认索引
*
* */

public class ViewPagerNormalAdapter extends PagerAdapter {

    private Context context;
    private List<String> datas;

    public ViewPagerNormalAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return this.datas.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = View.inflate(context, R.layout.item_normal_viewpager, null);

        TextView textView = view.findViewById(R.id.inv_title_textview);
        textView.setText(this.datas.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position);
    }
}
