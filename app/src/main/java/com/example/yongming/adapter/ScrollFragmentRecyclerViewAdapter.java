package com.example.yongming.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.yongming.activity.R;
import java.util.List;

/*
* RecyclerView 基本用法
* 1. 派生自RecyclerView.Adapter
* 2. 派生自RecyclerView.ViewHolder，并标示为模板参数
* 3. 构造函数，设置数据源数组
* 4. 集成onCreateViewHolder, flat出每个子项的 layout，也就是每一项的子View，类似于iOS的cell
* 5. onBindViewHolder 给每个cell设置数据的回调
*
* */

public class ScrollFragmentRecyclerViewAdapter extends RecyclerView.Adapter<ScrollFragmentRecyclerViewAdapter.VH> {

    public static class VH extends RecyclerView.ViewHolder {
        public final TextView textView;
        public VH(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.fi_name);
        }
    }

    private List<String> mDatas;

    public ScrollFragmentRecyclerViewAdapter(List<String>data) {
        mDatas = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        return new VH(view);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.textView.setText(mDatas.get(position));
    }
}
