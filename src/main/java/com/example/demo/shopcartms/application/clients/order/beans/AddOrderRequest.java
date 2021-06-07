package com.example.demo.shopcartms.application.clients.order.beans;

import com.example.demo.shopcartms.application.clients.goods.beans.UpdateGoodsInventoryRequest;
import com.example.demo.shopcartms.interfaces.rest.dto.CartInfoViewDto;
import com.example.demo.shopcartms.interfaces.rest.dto.CartItemInfoViewDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

@Data
public class AddOrderRequest {

    private String cartId;

    private String customerName;

    private Integer totalAmount;

    private List<AddOrderGoodsRequest> goods = new ArrayList<>();

    public static AddOrderRequest createByInfoViewDto(CartInfoViewDto cartInfoViewDto, BiConsumer<CartItemInfoViewDto, AddOrderGoodsRequest> biConsumer){
        AddOrderRequest req = new AddOrderRequest();
        BeanUtils.copyProperties(cartInfoViewDto, req);
        cartInfoViewDto.getGoods().stream().forEach(itemInfoViewDto -> {
            AddOrderGoodsRequest goods = new AddOrderGoodsRequest();
            BeanUtils.copyProperties(itemInfoViewDto, goods);
            goods.setGoodsName(itemInfoViewDto.getName());
            biConsumer.accept(itemInfoViewDto, goods);
            req.getGoods().add(goods);
        });
        return req;
    }

}
