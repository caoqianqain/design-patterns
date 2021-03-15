package com.cqq.java8.method;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/15 22:59
 */
public interface MyInterface {
    //默认方法
    default String getName(){
        return "张三";
    }

    //默认方法
    default Integer getAge(){
        return 18;
    }

    //普通方法
    String getSomething();
    //静态方法
    public static void method(){
        System.out.println("这个是静态方法");
    }
}
