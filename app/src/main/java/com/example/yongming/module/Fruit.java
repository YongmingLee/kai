package com.example.yongming.module;

import android.util.Log;

import com.example.yongming.protocol.MyAnnotation;

public class Fruit {

    public String name;
    public float price;

    public void testUU()
    {
        Log.i("ym", "haha");
    }


    @MyAnnotation("Yongming")
    public void printFruit(String name)
    {
        Log.i("iftaaa", "fruit name is (" + name + ")");
    }
}
