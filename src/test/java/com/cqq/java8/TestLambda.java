package com.cqq.java8;

import com.cqq.java8.lambda.Employee;
import com.cqq.java8.lambda.MyPredicate;
import com.cqq.java8.lambda.impl.*;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.*;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/6 8:41
 */
public class TestLambda {
//    private static boolean test(Object e) {
////        return e.getSalary() > 35;
////    }

    //原来的匿名内部类
    @Test
    public void test01(){
        //一个Comparator接口比较两个Integer的大小
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        //当用匿名内部类实现了一个接口以后我们就可以把这个匿名内部类的实例作为参数进行传递
        TreeSet<Integer> set = new TreeSet(com);
    }

    //Lambda表达式
    @Test
    public void test02(){
        //把上面的匿名内部类核心的一行代码Integer.compare(o1,o2)提取作为一个实现
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> set = new TreeSet(com);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三",68,9000),
            new Employee("李四",38,8000),
            new Employee("王五",50,4000),
            new Employee("赵六",18,3000),
            new Employee("田七",8,1000));
    //获取当前公司中员工年龄大于35的员工信息
    public List<Employee> filterEmployee(List<Employee> employees){
        List<Employee> emps = new ArrayList<>();
        for (Employee emp: employees) {
            if(emp.getAge() > 35){
                emps.add(emp);
            }
        }
        return emps;
    }
    //当前公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployee2(List<Employee> employees){
        List<Employee> emps = new ArrayList<>();
        for (Employee emp: employees) {
            if(emp.getSalary() > 5000){
                emps.add(emp);
            }
        }
        return emps;
    }
    @Test
    public void test03(){
        List<Employee> list = filterEmployee(employees);
        for (Employee employee:list ) {
            System.out.println(employee);
        }
    }

    //参数传进来一个MyPredicate接口的实现类em
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> em){
        List<Employee> emps = new ArrayList<>();

        for (Employee employee: list) {
            //实现类的判断方法
            if(em.test(employee)){
                emps.add(employee);
            }
        }

        return emps;
    }

    @Test
    public void test04(){
        System.out.println("========年龄大于35=========");
        //传进来的参数为接口年龄判断的实现类
        List<Employee> list = filterEmployee(employees,new FilterEmployeeByAge());
        for (Employee employee:list ) {
            System.out.println(employee);
        }
        System.out.println("========工资大于5000=========");
        //传进来的参数为工资年龄判断的实现类
        List<Employee> list2 = filterEmployee(employees,new FilterEmployeeBySalary());
        for (Employee employee:list2 ) {
            System.out.println(employee);
        }
    }

    @Test
    public void test05(){
       //传进来的参数为一个匿名内部类，不用每加一个判断条件再加一个接口的实现类了
       List<Employee> list =  filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee t) {
                return t.getSalary() < 5000;
            }
        });
        System.out.println("========工资小于5000=========");
        for (Employee employee:list ) {
            System.out.println(employee);
        }
    }

    @Test
    public void test06(){
         List<Employee> list = filterEmployee(employees, (e) -> e.getSalary() > 5000);
        list.forEach(System.out::println );
    }

    @Test
    public void test07() {
        //获取当前公司中员工工资不小于5000的员工信息
        employees.stream()
                .filter((e) -> e.getSalary() >= 5000)
                .forEach(System.out::println);
    }

    @Test
    public void test08() {
        //获取当前公司中员工工资不小于5000的员工信息
       String str = strParse("asdfg",(s) -> s.toLowerCase());
        String str2 = strParse("asdfg",(s) -> s.toUpperCase());
        String str3 = strParse("asdfg",(s) -> s.substring(2,4));

        System.out.println(str);
        System.out.println(str2);
        System.out.println(str3);

    }
    public String strParse(String str, MyFunctionPractise fun){
        return fun.getValue(str);
    }

    @Test
    public void test09() {
        Long num = operation(2L,3L,(x,y) -> x+y);
        System.out.println(num);

    }

    public Long operation(Long x, Long y, MyFunctionPractise2<Long,Long> fun){
        return fun.getValue(x,y);
    }
























}
