package com.cqq.java8.time;

import javafx.scene.input.DataFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author caoqianqian
 * @Description:这里就以传统日期时间格式化为例，看看它存在什么多线程安全问题？
 * @date 2021/3/16 7:58
 */
public class TestSimpleDateFormat {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//
//        //定义好如下一个任务，专门用于格式化日期和时间
//        Callable<Date> task = new Callable<Date>() {
//
//            @Override
//            public Date call() throws Exception {
//                return sdf.parse("20210317");
//            }
//        };

        //定义好如下一个任务，专门用于格式化日期和时间
        Callable<Date> task = new Callable<Date>() {

            @Override
            public Date call() throws Exception {
                //用的是SimpleDateFormat是被ThreadLocal给加了锁的
                return DateFormatThreadLocal.covert("20210317");
            }
        };

        //创建一个长度为10的线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
          
        List<Future<Date>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(pool.submit(task));
        }

        for (Future<Date> future:list) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
