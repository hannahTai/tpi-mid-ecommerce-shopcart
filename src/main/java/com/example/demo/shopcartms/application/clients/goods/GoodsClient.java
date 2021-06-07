package com.example.demo.shopcartms.application.clients.goods;

import com.example.demo.shopcartms.application.clients.goods.beans.GetGoodsByIdResponse;
import com.example.demo.shopcartms.application.clients.goods.beans.UpdateGoodsInventoryRequest;
import com.example.demo.shopcartms.application.clients.goods.beans.UpdateGoodsInventoryResponse;
import com.example.demo.shopcartms.interfaces.rest.dto.CartItemInfoViewDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsClient {

    @Value("${client.url.goods}")
    private String URL;

    @Autowired
    private RestTemplate restTemplate;

    public GetGoodsByIdResponse getGoodsById(String id) {
        String path = String.join("/", URL, "goods", id);
        GetGoodsByIdResponse resp = restTemplate.getForObject(path, GetGoodsByIdResponse.class);
        return resp;
    }

    public UpdateGoodsInventoryResponse updateGoodsInventory(String id, UpdateGoodsInventoryRequest req) {
        String path = String.join("/", URL, "goods", id, "inventory");
        UpdateGoodsInventoryResponse resp = restTemplate.patchForObject(path, req, UpdateGoodsInventoryResponse.class);
        return resp;
    }

    public List<UpdateGoodsInventoryResponse> updateGoodsInventories(Map<String, UpdateGoodsInventoryRequest> inventoryReqs) {
        List<UpdateGoodsInventoryResponse> resp = inventoryReqs.entrySet().stream()
                .map(entry -> updateGoodsInventory(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return resp;
    }

}
