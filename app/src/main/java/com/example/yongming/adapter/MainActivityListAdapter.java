package com.example.yongming.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yongming.activity.R;
import com.example.yongming.module.MainActivityModule;

import org.w3c.dom.Text;

import java.util.List;

/*
* 类似iOS的 tableview 的 datasource
* */
public class MainActivityListAdapter extends ArrayAdapter<MainActivityModule> {


    private int resourceId;

    /*
    * 构造函数
    * */
    public MainActivityListAdapter(Context context, int resourceId, List<MainActivityModule>objects)
    {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
    }


    /*
    * getView  类似 UITableView 的 cellforrowatindex，重用每一个cell
    *
    * ListView 的每一个cell 会调用 这个 delegate
    *
    * 仔细观察，getView()方法中还有一个 convertView 参数，这个参数用于将之前加载好的 布局进行缓存，以便之后可以进行重用。
    *
    * 目前我们的这份代码还是可以继续优化的，虽然现在已经不会再重复去加载布局，
    * 但是每次在 getView()方法中还是会调用 View 的 findViewById()方法来获取一次控件的实例。 我们可以借助一个 ViewHolder 来对这部分性能进行优化
    *
    * */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // 1. get module
        MainActivityModule mainActivityModule = getItem(position);

        // 2. get 自定义的layout
        View view;
        MainListAdapterViewHolder mainListAdapterViewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(this.resourceId, null);

            mainListAdapterViewHolder = new MainListAdapterViewHolder();
            mainListAdapterViewHolder.indexTextView = (TextView)view.findViewById(R.id.ml_index);
            mainListAdapterViewHolder.nameTextView = (TextView)view.findViewById(R.id.ml_name);

            view.setTag(mainListAdapterViewHolder);

        } else {
            view = convertView;
            mainListAdapterViewHolder = (MainListAdapterViewHolder)view.getTag();
        }

        // 3. query the UI element pointer
        TextView indexTextView = mainListAdapterViewHolder.indexTextView;
        TextView nameTextView = mainListAdapterViewHolder.nameTextView;

        indexTextView.setText(position  + " 、");
        nameTextView.setText(mainActivityModule.getName());

//        nameTextView.setTextColor(Color.parseColor("#00ad51"));

        return view;
    }


    class MainListAdapterViewHolder
    {
        TextView indexTextView;
        TextView nameTextView;
    }
}
