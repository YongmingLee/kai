package com.example.yongming.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yongming.activity.R;
import com.example.yongming.module.MsgModule;

import org.w3c.dom.Text;

import java.util.List;

public class TestNineMsgAdapter extends ArrayAdapter<MsgModule> {


    private int resourceId;

    public TestNineMsgAdapter(Context context, int textViewResourceId, List<MsgModule>objects)
    {
        super (context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    // 重用 每个View
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        MsgModule msgModule = getItem(position);
        ViewHolder viewHolder;
        View view;

        if (convertView == null) {

            view = LayoutInflater.from(getContext()).inflate(resourceId, null);

            viewHolder = new ViewHolder();
            viewHolder.leftLayout = (LinearLayout)view.findViewById(R.id.msg_left_layout);
            viewHolder.rightLayout = (LinearLayout)view.findViewById(R.id.msg_right_layout);
            viewHolder.leftMsg = (TextView)view.findViewById(R.id.msg_left_text);
            viewHolder.rightMsg = (TextView)view.findViewById(R.id.msg_right_text);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if (msgModule.getType() == MsgModule.TYPE_RECEIVED) {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msgModule.getContent());
        } else {
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.rightMsg.setText(msgModule.getContent());
        }

        return view;
    }

    class ViewHolder {

        LinearLayout leftLayout;

        LinearLayout rightLayout;

        TextView leftMsg;

        TextView rightMsg;
    }
}
