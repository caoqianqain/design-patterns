package com.cqq.java8;

import com.cqq.java8.lambda.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/7 21:19
 */
public class TestMethodRef {
    /**
     * 对象::实例方法名
     */
    @Test
    public void test01(){
        //不适用方法引用
        Consumer<String> consumer = (x) -> System.out.println(x);
        PrintStream ps = System.out;
        //对象调实例方法
        Consumer<String> consumer2 = (x) -> ps.println(x);
        //当Lambda体中的功能已经有方法实现了的时候就可以使用方法引用了 即 对象::实例方法名
        Consumer<String> consumer3 = ps::println;

        consumer.accept("这是参数");
        consumer2.accept("这是参数");
        consumer3.accept("这是参数");

    }

    @Test
    public void test02(){
        //不适用方法引用
        Employee employee = new Employee();
        employee.setName("姓名");
        Supplier<String> supplier = () -> employee.getName();
        String str = supplier.get();

        //当Lambda体中的功能已经有方法实现了的时候就可以使用方法引用了 employee::getName
        Supplier<String> supplier2 = employee::getName;
        String str2 = supplier2.get();
        System.out.println(str);
        System.out.println(str2);

    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test03(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        //Integer中的compare是静态方法  静态方法可以直接用类名引用
        Comparator<Integer> comparator2 = Integer::compare;
        int result = comparator.compare(5,4);
        int result2 = comparator.compare(5,4);
        System.out.println(result);
        System.out.println(result2);
    }

    /**
     * 类::实例方法名
     */
    @Test
    public void test04(){

        BiPredicate<String,String> predicate = (x,y) -> x.equals(y);

        //当Lambda表达式参数列表的第一个参数你这个实例方法的调用者，第二个参数是你要调用的实例方法的参数时，
        // 可使用ClassName::method这种格式
        BiPredicate<String,String> predicate2 = String::equals;

    }

    /**
     * 构造器引用
     */
    @Test
    public void test05(){

        //此时调用的是Employee类中无参数的构造方法
        Supplier<Employee> supplier = () -> new Employee();
        //构造器引用
        Supplier<Employee> supplier2 = Employee::new;
        Employee e1 = supplier.get();

        //此时调用的是Employee类中一个参数的构造方法
        Function<Integer,Employee> function = (x) -> new Employee(x);
        Employee e2 = function.apply(18);
        Function<Integer,Employee> function2 = Employee::new;
        Employee e3 = function2.apply(20);

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);

    }


    /**
     * 数组引用
     */
    @Test
    public void test06(){

        Function<Integer,String[]> function = (x) ->  new String[x];
        String[] strs = function.apply(10);
        System.out.println(strs.length);

        Function<Integer,String[]> function2 = String[]::new;
        String[] strs2 = function2.apply(20);
        System.out.println(strs2.length);

    }


}
