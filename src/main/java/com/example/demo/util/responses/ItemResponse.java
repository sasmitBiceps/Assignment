package com.example.demo.util.responses;

public class ItemResponse {
    private String userStatus;
    private String itemStatus;
    private String orderStatus;

    public ItemResponse(){

    }

    public ItemResponse(String userStatus, String itemStatus, String orderStatus) {
        this.userStatus = userStatus;
        this.itemStatus = itemStatus;
        this.orderStatus = orderStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
