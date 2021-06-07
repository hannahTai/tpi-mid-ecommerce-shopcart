package com.example.demo.shopcartms.domain.model.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "CART_ITEM")
@IdClass(CartItemEntityPk.class)
public class CartItemEntity implements Serializable {

    @Id
    @Column(name = "CART_ID", nullable = false)
    private String cartId;

    @Id
    @Column(name = "GOODS_ID", nullable = false)
    private String goodsId;

    @Column(name = "COUNT", nullable = false)
    private Integer count;

    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;

}