package com.example.yongming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class second extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final Intent intent = getIntent();
        String param = intent.getStringExtra("mykey");
        Toast.makeText(second.this, param, Toast.LENGTH_SHORT).show();

        Button button = (Button)findViewById(R.id.second_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent();
                intent1.putExtra("mykey", "Hi, I'm result from second");
                setResult(RESULT_OK, intent1);

                finish();
            }
        });
    }
}
