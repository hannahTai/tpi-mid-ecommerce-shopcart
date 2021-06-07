package com.example.demo.shopcartms.interfaces.rest.dto;

import com.example.demo.shopcartms.domain.model.entites.CartEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class CartCreateDto {

    @NotBlank
    private String customerName;

    @JsonIgnore
    public CartEntity getCartEntity() {
        CartEntity entity = new CartEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

}

