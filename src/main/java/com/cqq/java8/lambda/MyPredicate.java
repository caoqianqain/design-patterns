package com.cqq.java8.lambda;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/6 10:06
 */
//声明一个带泛型的接口
public interface MyPredicate<T> {

    //声明一个boolean的方法，传进来一个T，然后对T操作条件判断返回一个boolean
    public boolean test(T t);
}
