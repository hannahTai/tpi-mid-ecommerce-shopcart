package com.example.demo.shopcartms.application.clients.order.beans;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddOrderGoodsResponse {

    private String goodsId;

    private String goodsName;

    private Integer unitPrice;

    private Integer count;

}
