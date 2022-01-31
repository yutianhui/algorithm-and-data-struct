package com.yutianhui.learning.algorithm.bean;

/**
 * TODO
 *
 * @author yutianhui
 * @date 2021/12/10 14:22
 */
public class Person {

    private String name;
    private Integer age;
    private Long number;

    public Person(String name, Integer age, Long number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", number=" + number +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
