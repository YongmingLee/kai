package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.yongming.activity.BaseActivity;
import com.example.yongming.activity.R;
import com.example.yongming.manager.YMFieldHelper;
import com.example.yongming.module.Fruit;
import com.example.yongming.protocol.MyAnnotation;
import com.example.yongming.protocol.YMField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTestActivity extends BaseActivity {

    @YMField(R.id.ati_text)
    public TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_inflact);


        try {
            testClass();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.i("iftaaa", "class not found");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        YMFieldHelper.processAnnotation(ReflectTestActivity.class, this, this);

        Log.i("iftaaa", "The text result:(" + textView.getText() + ")");
    }

    private void testClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class c = Class.forName("com.example.yongming.module.Fruit");

        Fruit fu = (Fruit)c.newInstance();

        Field[] fields = c.getDeclaredFields();

        for (Field field : fields) {
//            Log.i("iftaaa", "type is (" + field.getType().getSimpleName() + ")");
//            Log.i("iftaaa", "name is (" + field.getName() + ")");
        }

        Method[] methods = c.getMethods();

        for (Method method : methods) {
//            Log.i("iftaaa", "m type is (" + method.getReturnType().getSimpleName() + ")");
//            Log.i("iftaaa", "m name is (" + method.getName() + ")");


            if (!method.isAnnotationPresent(MyAnnotation.class)) {
                continue;
            }

            MyAnnotation bindGet = method.getAnnotation(MyAnnotation.class);
            String param = bindGet.value();
            method.invoke(fu, param);
        }


        fu.printFruit("alsjdfljskadlf");
    }
}


