package com.yutianhui.learning.algorithm.bean;

/**
 * 商品
 *
 * @author yutianhui
 * @date 2022/1/9 18:10
 */
public class Commodity {
    // 名字
    private String name;
    // 重量
    private int weight;
    // 价值
    private int value;


    // 构造器
    public Commodity(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }
}
