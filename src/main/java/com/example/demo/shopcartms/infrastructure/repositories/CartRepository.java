package com.example.demo.shopcartms.infrastructure.repositories;

import com.example.demo.shopcartms.domain.model.entites.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartRepository extends JpaRepository<CartEntity, String>, JpaSpecificationExecutor<CartEntity> {

}
