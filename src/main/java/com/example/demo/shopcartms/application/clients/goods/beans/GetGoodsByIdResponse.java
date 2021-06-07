package com.example.demo.shopcartms.application.clients.goods.beans;

import lombok.Data;

@Data
public class GetGoodsByIdResponse {

    private String goodsId;

    private String goodsName;

    private Integer unitPrice;

    private Integer inventory;

}
