package com.example.yongming.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yongming.activity.R;
import com.example.yongming.module.Fruit;

import java.util.List;


public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view)
        {
            super(view);

            fruitImage = (ImageView)view.findViewById(R.id.fi_image);
            fruitName = (TextView)view.findViewById(R.id.fi_name);
        }
    }

    public FruitAdapter(List<Fruit>fruitList)
    {
        mFruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.name);

        holder.fruitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
