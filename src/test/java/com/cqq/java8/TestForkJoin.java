package com.cqq.java8;

import com.cqq.java8.parallel.ForkJoinCalculate;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.OptionalLong;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/14 8:17
 */
public class TestForkJoin {
    @Test
    public void test01(){

        //开始时间
        Instant start = Instant.now();
        //ForkJoin的执行需要一个ForkJoinPool的支持
        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoinCalculate((long) 0,1000000000L);
        Long invoke = pool.invoke(task);
        System.out.println(invoke);
        //结束时间
        Instant end = Instant.now();
        //计算一下时间用  耗时多少
        System.out.println(Duration.between(start,end).toMillis());

    }

    //一个普通for循环即传统的单线程的测试类 与Fork/Join的执行结果做对比
    @Test
    public void test02(){

        Instant start = Instant.now();
        long sum = 0L;

        for (long i = 0; i < 1000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());

    }

    @Test
    public void test03(){

        Instant start = Instant.now();

        //顺序流
        long reduce = LongStream.rangeClosed(0, 100000000L)
                .reduce(0, Long::sum);

        //使用parallel()并行流
        OptionalLong reduce1 = LongStream.rangeClosed(0, 100000000L)
                .parallel()//并行
                .reduce(Long::sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());

    }
}
