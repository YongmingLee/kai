package com.example.yongming.module;

/*
* 数据Module
* */

public class MainActivityModule {

    private String name;
    private boolean istest;

    // maybe 构造函数
    public MainActivityModule(String name, boolean istest)
    {
        this.name = name;
        this.istest = istest;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean isIstest()
    {
        return this.istest;
    }
}
