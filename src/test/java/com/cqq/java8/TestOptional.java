package com.cqq.java8;

import com.cqq.java8.lambda.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/14 21:44
 */
public class TestOptional {

    @Test
    public void test01(){
        ////创建一个带泛型的Optional容器类
        Optional<Employee> employee = Optional.of(new Employee());
        System.out.println(employee.get());

        //会报空指针异常
        //Optional<Employee> employee1 = Optional.of(null);
        //System.out.println(employee1.get());
//        if(employee1.isPresent()){
//            System.out.println(employee1.get());
//        }else{
//            System.out.println("------------");
//        }

        //不会报空指针异常
        Optional<Employee> employee2 = Optional.ofNullable(null);
        //System.out.println(employee2.get());
        if(employee2.isPresent()){
            System.out.println(employee2.get());
        }else{
            System.out.println("------------");
        }

    }

    @Test
    public void test02(){
        //构建了一个空的Optional实例，里面没有任何东西
        Optional<Employee> employee = Optional.empty();
        System.out.println(employee.get());//输出 java.util.NoSuchElementException: No value present
    }

    @Test
    public void test03(){
        //不会报空指针异常
        //Optional类中的of()方法不允许包装null，但是ofNullable()方法可以，传递到该方法中的若为null，则构建一个空的Optional实例
        Optional<Employee> employee2 = Optional.ofNullable(null);
        if(employee2.isPresent()){
            System.out.println(employee2.get());
        }else{
            System.out.println("------------");
        }
    }

    @Test
    public void test04(){
        Optional<Employee> employee2 = Optional.ofNullable(new Employee());
        //如果对象不是空的，就执行传入的Lambda表达式。
        employee2.ifPresent(e -> System.out.println(e.getAge()));
    }

    @Test
    public void test05(){
        Optional<Employee> op = Optional.ofNullable(new Employee());
        Employee em = op.orElse(new Employee("默认姓名", 20, 1000));
        System.out.println(em);//打印Employee{name='null', age=0, salary=0.0}

        Optional<Employee> op1 = Optional.ofNullable(null);
        Employee em1 = op1.orElse(new Employee("默认姓名", 20, 1000));
        System.out.println(em1);//打印Employee{name='默认姓名', age=20, salary=1000.0}
    }

    @Test
    public void test06(){
        Optional<Employee> op = Optional.ofNullable(new Employee());
        Employee em = op.orElseGet(() -> new Employee("默认姓名", 20, 1000));
        System.out.println(em);//打印Employee{name='null', age=0, salary=0.0}

        Optional<Employee> op1 = Optional.ofNullable(null);
        Employee em1 = op1.orElseGet(() -> new Employee("默认姓名", 20, 1000));
        System.out.println(em1);//打印Employee{name='默认姓名', age=20, salary=1000.0}
    }


    //不同之处
    //不同之处
    @Test
    public void test07(){

        Employee e = null;
        Optional<Employee> op = Optional.ofNullable(e);
        System.out.println("-----orElse----");
        Employee em = op.orElse(createNewEmployee());
        System.out.println("-----orElseGet----");
        Employee em1 = op.orElseGet(() -> createNewEmployee());
    }
    @Test
    public void test08(){

        Employee e = new Employee("默认姓名", 20, 1000);
        Optional<Employee> op = Optional.ofNullable(e);
        System.out.println("-----orElse----");
        Employee em = op.orElse(createNewEmployee());
        System.out.println("-----orElseGet----");
        Employee em1 = op.orElseGet(() -> createNewEmployee());
    }
    public Employee createNewEmployee(){
        System.out.println("==========创建一个新对象====");
        return new Employee("默认姓名1", 20, 1000);
    }

    @Test
    public void test09(){

        String str = Optional.ofNullable(new Employee())
                .map(Employee::getName).orElse("张三");
        System.out.println(str);
        Employee e = new Employee("默认姓名", 20, 1000);
        String str2 = Optional.ofNullable(e)
                .map(Employee::getName).orElse("张三");
        System.out.println(str2);
    }

    @Test
    public void test10(){
        Employee em = new Employee("默认姓名", 20, 1000);

        //map
        Optional<String> s1 = Optional.ofNullable(em)
                .map(Employee::getName);
        System.out.println(s1);
        //flatMap  要求返回的是Optional  避免空指针
        Optional<String> s2 = Optional.ofNullable(em)
                .flatMap(e -> Optional.of(e.getName()));
        System.out.println(s2);

    }

}
