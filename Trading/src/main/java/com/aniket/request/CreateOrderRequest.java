package com.aniket.request;

import com.aniket.domain.OrderType;



public class CreateOrderRequest {
    private String coinId;
    private double quantity;
    private OrderType orderType;

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(String coinId, double quantity, OrderType orderType) {
        this.coinId = coinId;
        this.quantity = quantity;
        this.orderType = orderType;
    }
}
