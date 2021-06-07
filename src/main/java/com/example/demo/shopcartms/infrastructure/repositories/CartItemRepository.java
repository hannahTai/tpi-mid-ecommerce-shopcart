package com.example.demo.shopcartms.infrastructure.repositories;

import com.example.demo.shopcartms.domain.model.entites.CartItemEntity;
import com.example.demo.shopcartms.domain.model.entites.CartItemEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, CartItemEntityPk>, JpaSpecificationExecutor<CartItemEntity> {

    Optional<List<CartItemEntity>> findByCartId(String cartId);

}
