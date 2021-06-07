package com.example.demo.shopcartms.application.clients.order.beans;

import com.example.demo.shopcartms.interfaces.rest.dto.CartInfoViewDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddOrderGoodsRequest {

    private Integer count;

    private String goodsId;

    private String goodsName;

    private Integer unitPrice;

    @JsonIgnore
    private Integer inventory;

}
