package com.cqq.java8.time;

import javafx.scene.input.DataFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/17 7:35
 */
public class DateFormatThreadLocal {

    //threadlocal而是一个线程内部的存储类，可以在指定线程内存储数据，数据存储以后，
    // 只有指定线程可以得到存储数据,大致意思就是ThreadLocal提供了线程内存储变量的能力，
    // 这些变量不同之处在于每一个线程读取的变量是对应的互相独立的。通过get和set方法就可以得到当前线程对应的值。
    //相当于是加了锁的变量
    private static final ThreadLocal<DateFormat> sdf = new ThreadLocal<DateFormat>(){

        //ThreadLocal里面有一个initialValue()方法
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    public static Date covert(String str) throws ParseException {
        return sdf.get().parse(str);
    }
}
