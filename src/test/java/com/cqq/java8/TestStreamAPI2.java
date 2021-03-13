package com.cqq.java8;

import com.cqq.java8.lambda.Employee;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/13 14:43
 */
public class TestStreamAPI2 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三",68,9000, Employee.Status.BUSY),
            new Employee("李四",38,8000, Employee.Status.BUSY),
            new Employee("王五",50,4000, Employee.Status.VOCATION),
            new Employee("赵六",18,3000, Employee.Status.BUSY),
            new Employee("田七",8,1000, Employee.Status.FREE));
    @Test
    public void test01(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> collect = list.stream().map(x -> x * x).collect(Collectors.toList());

        System.out.println(collect);

    }

    @Test
    public void test02(){
        Optional<Integer> reduce = employees.stream().map(e -> 1).reduce(Integer::sum);
        System.out.println(reduce.get());
    }

    @Test
    public void test03(){
       Thread t;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("========1=============");
            }
        }){
            @Override
            public void run() {
                System.out.println("========================22===");
                super.run();

            }
        }.start();


    }




}
