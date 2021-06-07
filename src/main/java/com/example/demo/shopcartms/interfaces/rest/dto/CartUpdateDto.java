package com.example.demo.shopcartms.interfaces.rest.dto;

import com.example.demo.shopcartms.domain.model.entites.CartEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
public class CartUpdateDto {

    private String customerName;

    private String orderId;

    @JsonIgnore
    public CartEntity getCartEntity() {
        CartEntity entity = new CartEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

}

