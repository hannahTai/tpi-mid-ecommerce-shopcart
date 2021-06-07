package com.example.demo.shopcartms.application.services;

import com.example.demo.shopcartms.application.clients.goods.GoodsClient;
import com.example.demo.shopcartms.application.clients.goods.beans.UpdateGoodsInventoryRequest;
import com.example.demo.shopcartms.application.clients.order.OrderClient;
import com.example.demo.shopcartms.application.clients.order.beans.AddOrderRequest;
import com.example.demo.shopcartms.application.clients.order.beans.AddOrderResponse;
import com.example.demo.shopcartms.interfaces.rest.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CartOrderService {

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    public CartInfoViewDto addOrder(String cartId) {
        Boolean flag = Boolean.FALSE;
        // 取得參數
        CartInfoViewDto cartDto = cartService.getCart(cartId);
        List<CartItemInfoViewDto> itemDtos = cartDto.getGoods();
        // 更新存貨
        Map<String, UpdateGoodsInventoryRequest> inventoryReqs = itemDtos.stream()
                .collect(Collectors.toMap(CartItemInfoViewDto::getGoodsId, itemDto -> {
                    UpdateGoodsInventoryRequest req = UpdateGoodsInventoryRequest.createByInfoViewDto(itemDto);
                    req.setInventory(itemDto.getInventory() - itemDto.getCount());
                    return req;
                }));
        goodsClient.updateGoodsInventories(inventoryReqs);
        // 新增訂單
        AddOrderRequest orderReq = AddOrderRequest.createByInfoViewDto(cartDto, (itemDto, addOrderGoodsReq) -> {
            addOrderGoodsReq.setInventory(itemDto.getInventory() - itemDto.getCount());
        });
        AddOrderResponse orderResp = this.doAddOrder(orderReq);
        // 執行結果處理
        if (Objects.nonNull(orderResp)) {
            // 執行成功，回存訂單編號
            CartUpdateDto updateDto = new CartUpdateDto();
            updateDto.setOrderId(orderResp.getOrderId());
            cartService.editCart(cartId, updateDto);
        } else {
            // 若執行失敗，回復庫存數量
            Map<String, UpdateGoodsInventoryRequest> inventoryRevertReqs = itemDtos.stream()
                    .collect(Collectors.toMap(CartItemInfoViewDto::getGoodsId, UpdateGoodsInventoryRequest::createByInfoViewDto));
            goodsClient.updateGoodsInventories(inventoryRevertReqs);
        }
        // 重新查詢
        cartDto = cartService.getCart(cartId);
        return cartDto;
    }

    public AddOrderResponse doAddOrder(AddOrderRequest orderReq) {
        AddOrderResponse orderResp = null;
        try {
            orderResp = orderClient.addOrder(orderReq);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("CartOrderService.addOrder.error.");
        }
        return orderResp;
    }

}
