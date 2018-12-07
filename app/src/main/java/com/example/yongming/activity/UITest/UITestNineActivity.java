package com.example.yongming.activity.UITest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;

import org.w3c.dom.Text;

public class UITestNineActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_nine);

        TextView textView = (TextView) findViewById(R.id.uitest_nine_text);
        textView.setText("工信部公布的《办法》指出，鼓励道路机动车辆生产企业之间开展研发和产能合作，允许符合\n" +
                "规定条件的道路机动车辆生产企业委托加工生产 ... 鼓励道路机动车辆研发设计企业与生产企业\n" +
                "合作，允许符合规定条件的研发设计企业借用生产企业的生产能力申请道路机动车辆生产企业\n" +
                "及产品准入。这意味着，工信部首次明确，允许汽车「代工」生产。");
    }
}
