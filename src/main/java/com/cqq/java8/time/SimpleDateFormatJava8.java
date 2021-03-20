package com.cqq.java8.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author caoqianqian
 * @Description:这里就以传统日期时间格式化为例，看看它存在什么多线程安全问题？
 * @date 2021/3/16 7:58
 */
public class SimpleDateFormatJava8 {
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
        //我们传统的API泛型是Date
        Callable<Date> task = new Callable<Date>() {

            @Override
            public Date call() throws Exception {
                //用的是SimpleDateFormat是被ThreadLocal给加了锁的
                return DateFormatThreadLocal.covert("20210317");
            }
        };

        //标准的日期时间格式写法
        //DateTimeFormatter df = DateTimeFormatter.ISO_DATE_TIME;
        //自己指定日期时间格式
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        //定义好如下一个任务，专门用于格式化日期和时间
        //我们新的API泛型就是LocalDate了
        Callable<LocalDate> taskNew = new Callable<LocalDate>() {

            @Override
            public LocalDate call() throws Exception {
                //用的是LocalDate了
                return LocalDate.parse("20210317",df);
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
