package com.example.yongming.activity.UITest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.adapter.TestNineMsgAdapter;
import com.example.yongming.module.MsgModule;

import java.util.ArrayList;
import java.util.List;

/*
* 九图测试，一版九图使用起泡，EditText 在外层包一层起泡视图。大部分是这个场景。
*
* */

public class UITestNineActivity extends BaseActivity {

    private TestNineMsgAdapter adapter;
    private List<MsgModule> msgModuleList = new ArrayList<MsgModule>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_nine);

        initMsgs();

        // 初始化ListView

        final ListView listView = (ListView) findViewById(R.id.atn_msg_list);

        adapter = new TestNineMsgAdapter(this, R.layout.activity_msg_item_layout, msgModuleList);
        listView.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.atn_msg_sendbtn);

        final EditText editText = (EditText)findViewById(R.id.atn_msg_txt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String content = editText.getText().toString();
                if (!content.equals("")) {
                    MsgModule msgModule = new MsgModule(content, MsgModule.TYPE_SENT);
                    msgModuleList.add(msgModule);
                    adapter.notifyDataSetChanged();
                    listView.setSelection(msgModuleList.size());

                    editText.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        MsgModule msg1 = new MsgModule("你好 家伙.", MsgModule.TYPE_RECEIVED); msgModuleList.add(msg1);
        MsgModule msg2 = new MsgModule("你好，你是哪位啊？", MsgModule.TYPE_SENT); msgModuleList.add(msg2);
        MsgModule msg3 = new MsgModule("我是KAI客服，很高兴与你通话!", MsgModule.TYPE_RECEIVED); msgModuleList.add(msg3);
    }
}
