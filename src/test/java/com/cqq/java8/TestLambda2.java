package com.cqq.java8;

import com.cqq.java8.lambda.Employee;
import com.cqq.java8.lambda.MyPredicate;
import com.cqq.java8.lambda.impl.FilterEmployeeByAge;
import com.cqq.java8.lambda.impl.FilterEmployeeBySalary;
import com.cqq.java8.lambda.impl.MyFunction;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/6 8:41
 */
public class TestLambda2 {
    //Lambda表达式需要函数式接口的支持，我们的Runnable接口就是函数式接口
    @Test
    public void test01(){
        //这里fianl可以不加，jdk1.8开始给我们默认记上了final，不需要我们手动加了
       /*final*/ int num = 0;
        //通过匿名内部类的方式实现接口
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Runnable"+num);
            }
        };
        runnable.run();
        System.out.println("--------------------");
        //通过Lambda表达式实现接口
        Runnable runnable1 = () -> System.out.println("Hello Lambda"+num);
        runnable1.run();
    }

    //Lambda表达式
    @Test
    public void test02(){
        //只有一个参数，小括号写
        Consumer<String> consumer = (x) -> System.out.println(x);
        //只有一个参数，小括号不写
        Consumer consumer2 = x -> System.out.println(x);
        consumer.accept("==这是测试结果==");
    }

    @Test
    public void test03(){
        Comparator<Integer> comparator = (x,y) -> {
            //方法体中多条语句
            System.out.println("方法体中多条语句1");
            System.out.println("方法体中多条语句2");
            return Integer.compare(x,y);
        };
        System.out.println(comparator.compare(2,4));
        System.out.println(comparator.compare(4,2));
        System.out.println(comparator.compare(2,2));
    }

    @Test
    public void test04(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        System.out.println(comparator.compare(2,4));
    }

    @Test
    public void test05(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        //x，y的数据类型Integer可以不写，JVM根据上下文Comparator<Integer>这个泛型的Integer可以推断出来
        Comparator<Integer> comparator2 = (Integer x,Integer y) -> Integer.compare(x,y);
    }

    //需求对一个数进行运算
    @Test
    public void test06(){
        //平方运算
        Integer num = operation(10,(x) -> x*x);
        System.out.println(num);
        //加和运算
        Integer num2 = operation(100,(y) -> y+200);
        System.out.println(num2);

    }

    //运算方法
    public Integer operation(Integer num, MyFunction fun){
        return fun.getValue(num);
    }


    List<Employee> list = Arrays.asList(
            new Employee("张三",68,9000),
            new Employee("李四",38,8000),
            new Employee("王五",50,4000),
            new Employee("赵六",18,3000),
            new Employee("田七",8,1000));
    //调用Collections.sort()方法，通过定制排序比较两个Employee（先按年龄比，年龄相同按姓名比），使用Lambda作为参数传递。

    @Test
    public void test07(){
        Collections.sort(list,(x,y) -> {
            if(x.getAge()==y.getAge()){
                return x.getName().compareTo(y.getName());
            }else{
                return Integer.compare(x.getAge(),y.getAge());
            }
        });
        for (Employee employee:list) {
            System.out.println(employee);
        }
    }

    //1、声明一个函数式接口，接口中声明抽象方法，public String getValue(String str)
    //声明一个类




























}
