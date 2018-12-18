package com.example.yongming.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DataTestActivity extends BaseActivity implements View.OnClickListener {

    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_test);

        editText = findViewById(R.id.data_test_edit);

        loadData();

        Button button = findViewById(R.id.data_test_read);
        button.setOnClickListener(this);

        button = findViewById(R.id.data_test_write);
        button.setOnClickListener(this);
    }

    private void saveData()
    {
        String inputText = editText.getText().toString();

        FileOutputStream out;
        BufferedWriter writer = null;

        try {

            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);

        }catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (writer != null) {

                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadData()
    {
        FileInputStream in;
        BufferedReader reader = null;

        try {

            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));


            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            String result = content.toString();

            editText.setText(result);
            editText.setSelection(result.length());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveData();
    }

    @Override
    public void onClick(View view) {

        EditText shareTestEdit = findViewById(R.id.data_test_edit_sharedpreferences);

        SharedPreferences sharedPreferences = getSharedPreferences("testshare", MODE_PRIVATE);

        switch (view.getId())
        {
            case R.id.data_test_read:

                String s = sharedPreferences.getString("shareKey", "");

                shareTestEdit.setText(s);

                break;

            case R.id.data_test_write:

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("shareKey", shareTestEdit.getText().toString());

                editor.commit();

                break;

            case R.id.data_test_read_sql:
                break;

            case R.id.data_test_write_sql:
                break;
        }
    }
}
