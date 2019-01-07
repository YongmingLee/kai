package com.example.yongming.protocol;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* 注解
*
* 1、可对相似代码需求做运行时处理，提高效率，也作区分。比如自带的Deprecated标明是不再提供的Method
* 2、自定义空间较大，可以做一些抽象化的处理。
*
* 本示例仿ButterKnife，注解修饰属性(Field)，根据属性的注解值，快速关联UI XML，减少代码关联。同时提高可读性。
*
* */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface YMField {
    int value() default -1;
}

