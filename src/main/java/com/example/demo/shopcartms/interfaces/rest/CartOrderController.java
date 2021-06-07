package com.example.demo.shopcartms.interfaces.rest;

import com.example.demo.shopcartms.application.services.CartOrderService;
import com.example.demo.shopcartms.interfaces.rest.dto.CartInfoViewDto;
import com.example.demo.shopcartms.interfaces.rest.dto.CartItemViewDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags="購物車訂單")
@RestController
@RequestMapping("/carts/{cartId}/orders")
public class CartOrderController {

    @Autowired
    private CartOrderService cartOrderService;

    @ApiOperation("新增購物車訂單")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartInfoViewDto addOrder(@PathVariable("cartId") String cartId) {
        return cartOrderService.addOrder(cartId);
    }

}