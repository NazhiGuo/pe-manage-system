package com.neusoft.tijian.po;

public class OverallResult {

    private Integer orId;   // 报告ID
    private String title;   // 报告标题
    private String content; // 报告内容
    private Integer orderId; // 关联的订单号

    // getter & setter 方法

    public Integer getOrId() {
        return orId;
    }

    public void setOrId(Integer orId) {
        this.orId = orId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
