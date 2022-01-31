package com.yutianhui.learning.algorithm.bean;

import java.util.ArrayList;

/**
 * 背包实现类
 *
 * @author yutianhui
 * @date 2022/1/9 18:59
 */
public class Packages {
    // 总的重量
    private int weight;
    // 存放的商品
    private ArrayList<Commodity> commodities = new ArrayList<>();
    // 总的价值
    private int maxValue = 0;


    public Packages(int weight) {
        this.weight = weight;
    }

    /**
     * 添加商品 <br>
     */
    public void addCommodity(Commodity commodity) {
        commodities.add(commodity);
        this.maxValue += commodity.getValue();
    }

    /**
     * 获取背包的总的重量
     */
    public int getMaxWeight() {
        int weight = 0;
        for (Commodity commodity : commodities) {
            weight += commodity.getValue();
        }
        return weight;
    }


    // 获取背包的总重量
    public int getPackageWeight() {
        return weight;
    }

    public int getMaxValue() {
        return maxValue;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        commodities.forEach(commodity -> {
            strb.append(commodity.getName()).append(",");
        });
        if (strb.length() != 0) strb.delete(strb.length() - 1, strb.length());
        return String.format("背包信息: [承重=(%s),商品=(%s),总的价值=(%s),总的重量=(%s)]", weight, strb.toString(), maxValue, getMaxWeight());
    }

}
