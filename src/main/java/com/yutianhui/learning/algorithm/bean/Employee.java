package com.yutianhui.learning.algorithm.bean;

/**
 * 员工类
 *
 * @author yutianhui
 * @date 2021/12/28 14:03
 */
public class Employee {

    private Integer id;
    private String name;
    private Integer salary;
    private String addr;

    public Employee() {
    }

    public Employee(Integer id, String name, Integer salary, String addr) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.addr = addr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", addr='" + addr + '\'' +
                '}';
    }
}
