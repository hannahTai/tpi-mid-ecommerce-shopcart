package com.example.demo.shopcartms.interfaces.rest.dto;

import com.example.demo.shopcartms.domain.model.entites.CartEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartInfoViewDto {

    private String cartId;

    private String customerName;

    private Integer totalAmount;

    private String orderId;

    private List<CartItemInfoViewDto> goods = new ArrayList<>();

    public static CartInfoViewDto createByEntity(CartEntity entity) {
        CartInfoViewDto viewDto = new CartInfoViewDto();
        BeanUtils.copyProperties(entity, viewDto);
        return viewDto;
    }

}
