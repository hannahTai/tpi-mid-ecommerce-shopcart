package com.example.demo.shopcartms.application.clients.order.beans;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddOrderResponse {

    private String orderId;

    private String customerName;

    private Integer totalAmount;

    private List<AddOrderGoodsResponse> goods = new ArrayList<>();

}
