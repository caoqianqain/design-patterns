package com.cqq.java8.lambda.impl;

import com.cqq.java8.lambda.Employee;
import com.cqq.java8.lambda.MyPredicate;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/6 10:11
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() > 35;
    }
}
