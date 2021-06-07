package com.example.demo.shopcartms.interfaces.rest.dto;

import com.example.demo.shopcartms.domain.model.entites.CartItemEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CartItemCreateDto {

    @NotBlank
    private String goodsId;

    @NotNull
    @Min(value = 1)
    private Integer count;

    @JsonIgnore
    public CartItemEntity getCartItemEntity() {
        CartItemEntity entity = new CartItemEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

}
