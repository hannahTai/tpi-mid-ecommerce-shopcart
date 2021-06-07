package com.example.demo.shopcartms.interfaces.rest.dto;

import com.example.demo.shopcartms.application.clients.goods.beans.GetGoodsByIdResponse;
import com.example.demo.shopcartms.domain.model.entites.CartEntity;
import com.example.demo.shopcartms.domain.model.entites.CartItemEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class CartItemInfoViewDto {

    private String goodsId;

    private Integer count;

    private String name;

    private Integer unitPrice;

    @JsonIgnore
    private Integer inventory;

    public static CartItemInfoViewDto createByEntityAndResponse(CartItemEntity entity, GetGoodsByIdResponse response) {
        CartItemInfoViewDto viewDto = new CartItemInfoViewDto();
        BeanUtils.copyProperties(entity, viewDto);
        viewDto.setUnitPrice(response.getUnitPrice());
        viewDto.setName(response.getGoodsName());
        viewDto.setInventory(response.getInventory());
        return viewDto;
    }

}
