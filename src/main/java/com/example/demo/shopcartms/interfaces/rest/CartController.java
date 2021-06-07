package com.example.demo.shopcartms.interfaces.rest;

import com.example.demo.shopcartms.application.services.CartService;
import com.example.demo.shopcartms.interfaces.rest.dto.CartInfoViewDto;
import com.example.demo.shopcartms.interfaces.rest.dto.CartCreateDto;
import com.example.demo.shopcartms.interfaces.rest.dto.CartViewDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags="購物車")
@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @ApiOperation("取得單一購物車資訊")
    @GetMapping(path = "/{cartId}")
    public CartInfoViewDto getCart(@PathVariable("cartId") String cartId) {
        return cartService.getCart(cartId);
    }

    @ApiOperation("建立購物車")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartViewDto addCart(@Valid @RequestBody CartCreateDto createDto) {
        return cartService.addCart(createDto);
    }

}