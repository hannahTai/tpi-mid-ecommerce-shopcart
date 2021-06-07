package com.example.demo.shopcartms.domain.model.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemEntityPk implements Serializable {

    private String cartId;

    private String goodsId;

}
