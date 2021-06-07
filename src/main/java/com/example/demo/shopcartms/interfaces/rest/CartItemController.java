package com.example.demo.shopcartms.interfaces.rest;

import com.example.demo.shopcartms.application.services.CartItemService;
import com.example.demo.shopcartms.interfaces.rest.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags="購物車商品")
@RestController
@RequestMapping("/carts/{cartId}/items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @ApiOperation("取得單一購物車商品資訊")
    @GetMapping(path = "/{itemId}")
    public CartItemInfoViewDto getItem(@PathVariable("cartId") String cartId, @PathVariable("itemId") String itemId) {
        return cartItemService.getItem(cartId, itemId);
    }

    @ApiOperation("新增購物車商品")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemViewDto addItem(@PathVariable("cartId") String cartId, @Valid @RequestBody CartItemCreateDto createDto) {
        return cartItemService.addItem(cartId, createDto);
    }

    @ApiOperation("編輯購物車商品")
    @PatchMapping(path = "/{itemId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemViewDto editItem(@PathVariable("cartId") String cartId, @PathVariable("itemId") String itemId, @Valid @RequestBody CartItemUpdateDto updateDto) {
        return cartItemService.editItem(cartId, itemId, updateDto);
    }

    @ApiOperation("移除購物車商品")
    @DeleteMapping(path = "/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable("cartId") String cartId, @PathVariable("itemId") String itemId) {
        cartItemService.removeItem(cartId, itemId);
    }

}