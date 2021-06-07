package com.example.demo.shopcartms.application.services;

import com.example.demo.shopcartms.application.clients.goods.GoodsClient;
import com.example.demo.shopcartms.application.clients.goods.beans.GetGoodsByIdResponse;
import com.example.demo.shopcartms.domain.model.entites.CartEntity;
import com.example.demo.shopcartms.domain.model.entites.CartItemEntity;
import com.example.demo.shopcartms.domain.model.entites.CartItemEntityPk;
import com.example.demo.shopcartms.infrastructure.repositories.CartItemRepository;
import com.example.demo.shopcartms.infrastructure.repositories.CartRepository;
import com.example.demo.shopcartms.interfaces.rest.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private GoodsClient goodsClient;

    public CartItemInfoViewDto getItem(String cartId, String itemId) {
        CartItemEntityPk entityPk = new CartItemEntityPk(cartId, itemId);
        CartItemEntity entity = this.findById(entityPk);
        CartItemInfoViewDto viewDto = this.getItemInfo(entity);
        return viewDto;
    }

    public CartItemInfoViewDto getItemInfo(CartItemEntity entity) {
        GetGoodsByIdResponse response = goodsClient.getGoodsById(entity.getGoodsId());
        CartItemInfoViewDto viewDto = CartItemInfoViewDto.createByEntityAndResponse(entity, response);
        return viewDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public CartItemViewDto addItem(String cartId, CartItemCreateDto createDto) {
        CartItemEntity entity = createDto.getCartItemEntity();
        entity.setCartId(cartId);
        entity.setCreatedDate(LocalDateTime.now());
        cartItemRepository.save(entity);
        CartItemViewDto viewDto = CartItemViewDto.createByEntity(entity);
        return viewDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public CartItemViewDto editItem(String cartId, String itemId, CartItemUpdateDto updateDto) {
        CartItemEntityPk id = new CartItemEntityPk(cartId, itemId);
        CartItemViewDto viewDto = this.update(
                cartItemRepository -> cartItemRepository.findById(id).orElseThrow(),
                entity -> {
                    if (Optional.of(updateDto.getCount()).isPresent()) {
                        entity.setCount(updateDto.getCount());
                    }
                    entity.setLastModifiedDate(LocalDateTime.now());
                });

        return viewDto;
    }

    private CartItemViewDto update(Function<CartItemRepository, CartItemEntity> selector, Consumer<CartItemEntity> setter) {
        CartItemEntity entity = selector.apply(cartItemRepository);
        setter.accept(entity);
        CartItemViewDto viewDto = CartItemViewDto.createByEntity(entity);
        return viewDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeItem(String cartId, String itemId) {
        CartItemEntityPk entityPk = new CartItemEntityPk(cartId, itemId);
        cartItemRepository.deleteById(entityPk);
    }

    public CartItemEntity findById(CartItemEntityPk id){
        CartItemEntity entity = cartItemRepository.findById(id).orElseThrow();
        return entity;
    }

    public List<CartItemEntity> findByCartId(String cartId){
        List<CartItemEntity> entities = cartItemRepository.findByCartId(cartId).orElseThrow();
        return entities;
    }

}
