package com.cqq.java8.lambda.impl;

import com.cqq.java8.lambda.Employee;
import com.cqq.java8.lambda.MyPredicate;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/6 10:13
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 5000;
    }
}
