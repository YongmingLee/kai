package com.example.yongming.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yongming.manager.ActivityLifeManager;

/**
 *
 * 该类在AndroidManifest 中定义为SingleTask类型的Activity
 * 所以，start 该activity的时候，栈中在此之上的activity们都会被释放，调用他们的onDestroy
 *
 */

public class fourth extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Log.i("ymac", "fourth-oncreate- " + this);

        Button button = (Button) findViewById(R.id.fourth_btn_next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(fourth.this, MainActivity.class);
//                startActivity(intent);


                ActivityLifeManager.closeAllActivities();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("ymac", "fourth-ondestroy- " + this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("ymac", "fourth-onstop- " + this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("ymac", "fourth-onrestart- " + this);
    }
}
