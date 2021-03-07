package com.cqq.java8;

import com.cqq.java8.lambda.Employee;
import com.cqq.java8.lambda.impl.MyFunction;
import org.junit.Test;

import javax.print.DocFlavor;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.lang.Math.random;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/6 8:41
 */
public class TestLambda3 {
    @Test
    public void test01(){
        System.out.println(Math.random()*100);
        happy("一消费就很开心啦啦啦",str -> System.out.println(str));
    }
    public void happy(String str,Consumer<String> consumer){
        consumer.accept(str);
    }

    @Test
    public void test02(){
        List<Integer> numList = getValue(10,() -> (int)(Math.random()*100));
        for (Integer num :
                numList) {
            System.out.println(num);
        }
    }

    //产生指定个数的整数并放入集合中
    public List<Integer> getValue(int num,Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    @Test
    public void test03(){
        String returnStr = strHandler("\t\t\t我是一串有空格\t的字符串\t\t",(str) -> str.trim());
        System.out.println(returnStr);
    }

    public String strHandler(String str,Function<String,String> function){
        return function.apply(str);
    }

    @Test
    public void test04(){
        List<String> list = Arrays.asList("aa","bbb","ccccc","dddddd");
        //长度大于3的字符串放入集合中
        List<String> reList = filterStr(list,(s) -> s.length() >3);
        for (String str: reList) {
            System.out.println(str);
        }
    }

    public List<String> filterStr(List<String> strList,Predicate<String> pre){
        List<String> list = new ArrayList<>();
        for (String str: strList) {
            if(pre.test(str)){
                list.add(str);
            }
        }
        return list;
    }

    @Test
    public void test05(){
    }

    @Test
    public void test06(){

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
    }





























}
