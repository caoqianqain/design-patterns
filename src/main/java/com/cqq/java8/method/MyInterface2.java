package com.cqq.java8.method;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/15 22:59
 */
public interface MyInterface2 {
    //默认方法
    default String getName(){
        return "张三2";
    }

}
