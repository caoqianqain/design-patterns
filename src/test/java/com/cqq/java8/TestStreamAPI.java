package com.cqq.java8;

import com.cqq.java8.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/9 7:43
 */
public class TestStreamAPI {
    @Test
    public void test01(){
        //通过Collection系列集合提供的Stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Stream<String> stringStream = list.parallelStream();

        //通过数组Arrays 中的静态方法stream()获取数组
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        //通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc", "dd");

        //创建无限流
        //迭代   Stream.iterate()传俩参数，第一个是种子即起始值，第二个参数是Lambda表达式即对起始值进行的操作，
        //这里是生成偶数
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        //无限流就是没有限制，需要一个终止操作限制
        stream4.limit(4).forEach(System.out::println);
        //生成的方式
        Stream<Double> generate5 = Stream.generate(() -> Math.random());
        generate5.limit(5).forEach(System.out::println);

    }
    List<Employee> employees = Arrays.asList(
            new Employee("张三",68,9000, Employee.Status.BUSY),
            new Employee("李四",38,8000, Employee.Status.BUSY),
            new Employee("王五",50,4000, Employee.Status.VOCATION),
            new Employee("赵六",18,3000, Employee.Status.BUSY),
            new Employee("田七",8,1000, Employee.Status.FREE));
    @Test
    public void test02(){

        //中间操作，不会执行任何操作
        //filter(Predicate p) 接收Lambda，从流中排除某些元素
        Stream<Employee> employeeStream = employees.stream().filter((e) -> {
            System.out.println("StreamAPI的中间操作");
            return e.getAge() > 35;
        });

        //终止操作  当只有中间操作时执行是没有结果的，因为只有执行终止操作以后所有的中间操作才一次性全部处理，即惰性求值
        employeeStream.forEach(System.out::println);

    }
    //外部迭代，我们自己写的迭代
    @Test
    public void test03(){
        Iterator<Employee> iterator = employees.iterator();
        if(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //limit()过滤公司中薪资大于5000的雇员之后，获取其中前2个雇员的信息
    @Test
    public void test04(){
         employees.stream()
                .filter((e) -> {
                    System.out.println("===短路===");
                    return e.getSalary()>5000;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    //skip()过滤公司中薪资大于5000的雇员之后，跳过前2个雇员的信息
    @Test
    public void test05(){
        employees.stream()
                .filter((e) -> e.getSalary()>2000)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test06(){
        List<Employee> employees1 = Arrays.asList(
                new Employee("张三",68,9000),
                new Employee("李四",38,8000),
                new Employee("王五",50,4000),
                new Employee("赵六",18,3000),
                new Employee("赵六",18,3000),
                new Employee("赵六",18,3000),
                new Employee("赵六",18,3000),
                new Employee("田七",8,1000));
        employees1.stream()
                .filter((e) -> e.getSalary()>2000)
                .distinct()
                .forEach(System.out::println);
    }


    //map
    @Test
    public void test07(){
        //将字母转大写
        List<String> list = Arrays.asList("aa","bb","cc");
        list.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);

        //提取员工名字
        employees.stream()
                .map((e) -> e.getName())
                .forEach(System.out::println);
    }

    //faltMap
    @Test
    public void test08(){
        //提取list里的一个个字符串的每一个字符
        List<String> list = Arrays.asList("aa","bb","cc");

        //通过map提取到的是一个stream流里还是stream流
        Stream<Stream<Character>> streamStream = list.stream()
                .map(TestStreamAPI::filterCharacter);
        //这里得嵌套遍历才可以循环出每个字符串的结果
        streamStream.forEach(
                s -> s.forEach(System.out::println)
        );
        System.out.println("=============");

        //通过flatMap提取到的是一个stream流
        Stream<Character> streamStream2 = list.stream()
                .flatMap(TestStreamAPI::filterCharacter);
        //这样就不用了嵌套遍历了
        streamStream2.forEach(System.out::println);

    }
    public static Stream<Character> filterCharacter(String str){
        List<Character> list  = new ArrayList<>();
        for (Character c:str.toCharArray()) {
            list.add(c);
        }

        return list.stream();
    }


    //排序
    //sort() 自然排序
    //sorted(Comparator comp) 比较器排序
    @Test
    public void test09(){
        //将字母按自然醒顺序排序
        List<String> list = Arrays.asList("bb","aa","cc");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        //按年龄排序，如果年龄相等按姓名排
        employees.stream()
                .sorted((e1,e2) -> {
                    if(e1.getAge()== e2.getAge()){
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return Integer.compare(e1.getAge(),e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }

    List<Employee> employees1 = Arrays.asList(
            new Employee("张三",68,9000, Employee.Status.FREE),
            new Employee("李四",38,8000,Employee.Status.BUSY),
            new Employee("王五",50,4000,Employee.Status.VOCATION),
            new Employee("赵六",18,3000,Employee.Status.BUSY),
            new Employee("田七",8,1000,Employee.Status.FREE));
    @Test
    public void test10(){

        boolean b = employees1.stream()
                .allMatch(e -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println("===allMatch===="+b);

    }

    //anyMatch
    @Test
    public void test11(){
        boolean c = employees1.stream()
                .anyMatch(e -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println("===anyMatch===="+c);
    }

    //noneMatch
    @Test
    public void test12(){
        boolean d = employees1.stream()
                .noneMatch(e -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println("===anyMatch===="+d);

    }
    //findFirst
    @Test
    public void test13(){
        Optional<Employee> first = employees1.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(first.get());
    }

    //findAny
    @Test
    public void test14(){
        Optional<Employee> first = employees1.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findAny();
        System.out.println(first.get());
    }

    //count
    @Test
    public void test15(){
         long count = employees1.stream()
                .count();
        System.out.println(count);
    }

    //max
    @Test
    public void test16(){
        //两种方法
        Optional<Employee> max1 = employees1.stream()
                .max((e1,e2) -> Integer.compare(e1.getAge(),e2.getAge()));
        Optional<Employee> max2 = employees1.stream()
                .max(Comparator.comparingInt(Employee::getAge));
        System.out.println(max1);
    }
    //min
    @Test
    public void test17(){
        //两种方法
        Optional<Employee> max1 = employees1.stream()
                .min((e1,e2) -> Integer.compare(e1.getAge(),e2.getAge()));
        Optional<Employee> max2 = employees1.stream()
                .min(Comparator.comparingInt(Employee::getAge));
        System.out.println(max1);
    }


    //reduce()
    @Test
    public void test18(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //首先，需要传一个起始值，然后，传入的是一个二元运算。
        Integer count = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(count);
    }

    //reduce()
    @Test
    public void test19(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //没有起始值，则有可能结果为空，所以返回的值会被封装到Optional中。
        Optional<Integer> reduce = list.stream().reduce((x, y) -> x + y);
        //另一种写法
        Optional<Integer> reduce1 = list.stream().reduce(Integer::sum);

        System.out.println(reduce.get());
        System.out.println(reduce1.get());

    }

    //toList()
    @Test
    public void test20(){
        List<Integer> list = employees.stream().map(Employee::getAge).collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    //toSet()
    @Test
    public void test21(){
        Set<Integer> collect = employees.stream().map(Employee::getAge).collect(Collectors.toSet());
        collect.forEach(System.out::println);
    }

    //toCollection()
    @Test
    public void test22(){
        //换成HashSet
        HashSet<Integer> collect = employees.stream().map(Employee::getAge).collect(Collectors.toCollection(HashSet::new));
        collect.forEach(System.out::println);
    }

    //counting()
    @Test
    public void test23(){
        //总数
        Long collect = employees.stream().collect(Collectors.counting());
        System.out.println(collect);
    }

    //averagingDouble()
    @Test
    public void test24(){
        //平均值
        Double collect1 = employees.stream().collect(Collectors.averagingDouble(Employee::getAge));
        System.out.println(collect1);
    }

    //averagingDouble()
    @Test
    public void test25(){
        //平均值
        DoubleSummaryStatistics collect1 = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        IntSummaryStatistics collect = employees.stream().collect(Collectors.summarizingInt(Employee::getAge));
        System.out.println(collect);
        System.out.println(collect.getSum());
    }

    //summarizingInt()
    @Test
    public void test26(){
        //平均值  IntSummaryStatistics里包含{count=5, sum=182, min=8, average=36.400000, max=68}这几项需要哪个获取就行
        IntSummaryStatistics collect = employees.stream().collect(Collectors.summarizingInt(Employee::getAge));
        System.out.println(collect);
        //从IntSummaryStatistics里取sum
        System.out.println(collect.getSum());
    }

    //maxBy()
    @Test
    public void test27(){
        Optional<Employee> max = employees.stream().collect(Collectors.maxBy((x, y) -> Integer.compare(x.getAge(), y.getAge())));
        System.out.println(max.get());
        //等价于这种形式
        Optional<Employee> max1 = employees.stream().max((x, y) -> Integer.compare(x.getAge(), y.getAge()));
        System.out.println(max1.get());
    }

    //minBy()
    @Test
    public void test28(){
        Optional<Employee> min = employees.stream().collect(Collectors.minBy((x, y) -> Integer.compare(x.getAge(), y.getAge())));
        System.out.println(min.get());
    }

    //groupingBy()
    @Test
    public void test29(){
        Map<Employee.Status, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
    }
    //多级分组
    @Test
    public void test30(){
       Map<Employee.Status,Map<String,List<Employee>>> map =  employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy(e -> {
                    if(e.getAge()<35){
                        return "青年";
                    }else if(e.getAge()<50){
                        return "中年";
                    }else {
                        return "老年";
                    }
                })));
        System.out.println(map);
    }

    //partitioningBy()
    @Test
    public void test31(){
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));
        System.out.println(map);
    }

    //joining()
    @Test
    public void test32(){
        String collect = employees.stream().map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect);
    }




















}
