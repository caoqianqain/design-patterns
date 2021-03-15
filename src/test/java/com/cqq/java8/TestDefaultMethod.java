package com.cqq.java8;

import com.cqq.java8.method.MyClass;
import com.cqq.java8.method.MyInterface;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/15 23:09
 */
public class TestDefaultMethod extends MyClass implements MyInterface{

    @Override
    public String getSomething() {
        return null;
    }

    public static void main(String[] args) {
        TestDefaultMethod tfm = new TestDefaultMethod();
        System.out.println( tfm.getName());//打印输出了李四,说明调用的是父类的方法

    }
}
