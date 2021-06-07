package com.example.demo.shopcartms.interfaces.rest.dto;

import com.example.demo.shopcartms.domain.model.entites.CartItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemViewDto {

    private String cartId;

    private String goodsId;

    private Integer count;

    public static CartItemViewDto createByEntity(CartItemEntity entity) {
        CartItemViewDto viewDto = new CartItemViewDto();
        BeanUtils.copyProperties(entity, viewDto);
        return viewDto;
    }

}
