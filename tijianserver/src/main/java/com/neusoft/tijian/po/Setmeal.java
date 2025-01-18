package com.neusoft.tijian.po;

import java.util.List;

public class Setmeal {

    private Integer smId;
    private String name;
    private Integer type;
    private Integer price;

    // 套餐表是一方，比如现在有 A、B、C 套餐
    // A 套餐对应 A 套餐的明细
    // B 套餐对应 B 套餐的明细
    // 此时：套餐与套餐明细是一对多的关系
    //一对多
    private List<SetmealDetailed> sdList;

    public List<SetmealDetailed> getSdList() {
        return sdList;
    }

    public void setSdList(List<SetmealDetailed> sdList) {
        this.sdList = sdList;
    }

    // getter & setter 方法

    public Integer getSmId() {
        return smId;
    }

    public void setSmId(Integer smId) {
        this.smId = smId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
