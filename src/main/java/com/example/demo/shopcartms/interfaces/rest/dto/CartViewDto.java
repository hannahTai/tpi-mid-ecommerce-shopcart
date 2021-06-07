package com.example.demo.shopcartms.interfaces.rest.dto;

import com.example.demo.shopcartms.domain.model.entites.CartEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class CartViewDto {

    private String cartId;

    private String customerName;

    public static CartViewDto createByEntity(CartEntity entity) {
        CartViewDto viewDto = new CartViewDto();
        BeanUtils.copyProperties(entity, viewDto);
        return viewDto;
    }

}
