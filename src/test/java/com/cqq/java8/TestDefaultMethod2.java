package com.cqq.java8;

import com.cqq.java8.method.MyClass;
import com.cqq.java8.method.MyInterface;
import com.cqq.java8.method.MyInterface2;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/15 23:09
 */
public class TestDefaultMethod2  implements MyInterface, MyInterface2 {

    //同名方法的接口要求我们必须重写，不重写就会报错了
    @Override
    public String getName() {
        return MyInterface2.super.getName();
    }

    //普通方法
    @Override
    public String getSomething() {
        return null;
    }

    public static void main(String[] args) {
        TestDefaultMethod2 tfm2 = new TestDefaultMethod2();
        System.out.println(tfm2.getName());//打印输出了李四,说明调用的是父类的方法
    }

}
