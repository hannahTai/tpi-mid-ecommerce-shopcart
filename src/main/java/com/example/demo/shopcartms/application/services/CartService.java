package com.example.demo.shopcartms.application.services;

import com.example.demo.shopcartms.application.clients.goods.GoodsClient;
import com.example.demo.shopcartms.domain.model.entites.CartEntity;
import com.example.demo.shopcartms.domain.model.entites.CartItemEntity;
import com.example.demo.shopcartms.infrastructure.repositories.CartRepository;
import com.example.demo.shopcartms.interfaces.rest.dto.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private GoodsClient goodsClient;

    public CartInfoViewDto getCart(String cartId) {
        CartEntity entity = cartRepository.findById(cartId).orElseThrow();
        List<CartItemEntity> itemEntities = cartItemService.findByCartId(cartId);

        List<CartItemInfoViewDto> itemViewDtos = itemEntities.stream()
                .map(itemEntity -> cartItemService.getItemInfo(itemEntity))
                .collect(Collectors.toList());
        Integer totalAmount = itemViewDtos.stream()
                .map(e -> e.getUnitPrice() * e.getCount())
                .reduce(0, Integer::sum);

        CartInfoViewDto cartViewDto = CartInfoViewDto.createByEntity(entity);
        cartViewDto.setGoods(itemViewDtos);
        cartViewDto.setTotalAmount(totalAmount);
        return cartViewDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public CartViewDto addCart(CartCreateDto createDto) {
        CartEntity entity = createDto.getCartEntity();
        entity.setCreatedDate(LocalDateTime.now());
        cartRepository.save(entity);
        CartViewDto viewDto = CartViewDto.createByEntity(entity);
        return viewDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public CartViewDto editCart(String cartId, CartUpdateDto updateDto) {
        CartViewDto viewDto = this.update(
                cartRepository -> cartRepository.findById(cartId).orElseThrow(),
                entity -> {
                    if (Optional.of(updateDto.getOrderId()).isPresent()) {
                        entity.setOrderId(updateDto.getOrderId());
                    }
                    entity.setLastModifiedDate(LocalDateTime.now());
                });
        return viewDto;
    }

    private CartViewDto update(Function<CartRepository, CartEntity> selector, Consumer<CartEntity> setter) {
        CartEntity entity = selector.apply(cartRepository);
        setter.accept(entity);
        CartViewDto viewDto = CartViewDto.createByEntity(entity);
        return viewDto;
    }

}
