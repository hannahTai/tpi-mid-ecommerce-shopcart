package com.example.demo.shopcartms.application.clients.goods.beans;

import com.example.demo.shopcartms.application.clients.order.beans.AddOrderGoodsRequest;
import com.example.demo.shopcartms.application.clients.order.beans.AddOrderRequest;
import com.example.demo.shopcartms.interfaces.rest.dto.CartInfoViewDto;
import com.example.demo.shopcartms.interfaces.rest.dto.CartItemInfoViewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGoodsInventoryRequest {

    private Integer inventory;

    public static UpdateGoodsInventoryRequest createByInfoViewDto(CartItemInfoViewDto cartInfoViewDto){
        UpdateGoodsInventoryRequest req = new UpdateGoodsInventoryRequest();
        req.setInventory(cartInfoViewDto.getInventory());
        return req;
    }

}
