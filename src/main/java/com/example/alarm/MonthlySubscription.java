package com.example.alarm;

public class MonthlySubscription {
    private String userID;
    private String userName;
    private String orderMemo;
    private String deliveryAddress;
    private String phoneNo;
    private String totalCostMonthly;
    private String deliveredTill;

    public MonthlySubscription(String id, String userName, String orderMemo, String deliveryAddress, String phoneNo, String totalCostMonthly, String deliveredTill) {
        this.userID = id;
        this.userName = userName;
        this.orderMemo = orderMemo;
        this.deliveryAddress = deliveryAddress;
        this.phoneNo = phoneNo;
        this.totalCostMonthly = totalCostMonthly;
        this.deliveredTill = deliveredTill;
    }

    // Getters and setters for all fields


    public void setId(String id) {
        this.userID = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderMemo() {
        return orderMemo;
    }

    public void setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getTotalCostMonthly() {
        return totalCostMonthly;
    }

    public void setTotalCostMonthly(String totalCostMonthly) {
        this.totalCostMonthly = totalCostMonthly;
    }

    public String getDeliveredTill() {
        return deliveredTill;
    }

    public String getId() {    return userID;   }

    public void setDeliveredTill(String deliveredTill) {
        this.deliveredTill = deliveredTill;
    }
}
