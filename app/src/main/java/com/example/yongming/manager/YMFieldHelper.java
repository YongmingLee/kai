package com.example.yongming.manager;

import android.app.Activity;

import com.example.yongming.protocol.YMField;

import java.lang.reflect.Field;

public class YMFieldHelper {

    public static void processAnnotation(Class cls, Object obj, Activity context)
    {
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(YMField.class))
            {
                YMField ymField = field.getAnnotation(YMField.class);

                try {

                    field.set(obj, context.findViewById(ymField.value()));

                } catch (IllegalAccessException e) {

                    e.printStackTrace();
                }
            }
        }
    }
}