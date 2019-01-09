package com.example.yongming.activity.ActivitySubmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yongming.activity.BaseActivity;

public class JavaTestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runTestCode();
    }

    private void runTestCode()
    {


/*
*
*

    Java有八种基本类型，其中每一种都有特定的格式和大小
    基本类型 	说明
    byte 	字节长度的整数，八位
    short 	短整数，十六位
    int 	整数，三十二位
    long 	长整数，六十四位
    float 	单精度浮点数，三十二位
    double 	双精度浮点数，六十四位
    char 	Unicode字符
    boolean 	布尔值


    java中为什么byte的取值范围是-128到+127

    概念：java中用补码表示二进制数，补码的最高位是符号位，最高位为“0”表示正数，最高位为“1”表示负数。
    正数补码为其本身；
    负数补码为其绝对值各位取反加1；
    例如：
    +21，其二进制表示形式是00010101，则其补码同样为00010101
    -21，按照概念其绝对值为00010101，各位取反为11101010，再加1为11101011，即-21的二进制表示形式为11101011

    步骤：
    1、byte为一字节8位，最高位是符号位，即最大值是01111111，因正数的补码是其本身，即此正数为01111111,十进制表示形式为127
    2、最大正数是01111111，那么最小负是10000000(最大的负数是11111111，即-1)
    3、10000000是最小负数的补码表示形式，我们把补码计算步骤倒过来就即可。10000000减1得01111111然后取反10000000
    因为负数的补码是其绝对值取反，即10000000为最小负数的绝对值，而10000000的十进制表示是128，所以最小负数是-128
    4、由此可以得出byte的取值范围是-128到+127
    5、说明：各个类型取值范围的计算方法与此大致相同

* */

        byte b = 1;
        int n = 127 + 2;

        b = (byte)n; // 此处会产生数据溢出


        //十进制
        int x=10;
        //十六进制数，即十进制的32
        int y=0x20;
        //八进制，即十进制的15
        int z=017;
        //二进制，即十进制的7
        int u=0b0111;


        // 如果整数字面值太长，可读性会受到影响。 从Java 7开始，可用在整数字面值中使用下划线将数字分隔开

        //十进制
        /*
        int x=1_000_000;
        //十六进制数，即十进制的32
        int y=0x2_0;
        //八进制，即十进制的15
        int z=0_17;
        //二进制，即十进制的7
        int u=0b0_111;
        */



        // Enum 遍历输出
        for (Color color : Color.values()) {
            System.out.println(color);
        }


        // 运算符、线程、异常处理、枚举


    }

    public enum Color {
        RED, GREEN, BLANK, YELLOW
    }
}
