package com.example.alarm;

import java.sql.Date;

public class Order {

    private String orderId;
    private String userName;
    private String orderDate;
    private String phoneNo;
    private String totalAmount;
    private String deliveryAddress;
    private String orderMemo;
    private String orderStatus;

    public Order(String orderId, String userName, Date orderDate, String phoneNo,
                 String totalAmount, String deliveryAddress, String orderMemo, String orderStatus) {
        this.orderId = orderId;
        this.userName = userName;
        this.orderDate = String.valueOf(orderDate);
        this.phoneNo = phoneNo;
        this.totalAmount = totalAmount;
        this.deliveryAddress = deliveryAddress;
        this.orderMemo = orderMemo;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserName() {
        return userName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getOrderMemo() {
        return orderMemo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
