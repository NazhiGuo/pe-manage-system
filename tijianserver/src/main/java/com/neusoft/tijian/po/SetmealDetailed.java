package com.neusoft.tijian.po;

public class SetmealDetailed {

    private Integer sdId;
    private Integer smId;
    private Integer ciId;

    // 医院的检查套餐中含有明细
    // 明细中会标明对应的检查项目
    // 此时是：多对一
    private CheckItem checkItem;

    public CheckItem getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(CheckItem checkItem) {
        this.checkItem = checkItem;
    }

    // getter & setter 方法

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public Integer getSmId() {
        return smId;
    }

    public void setSmId(Integer smId) {
        this.smId = smId;
    }

    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }
}