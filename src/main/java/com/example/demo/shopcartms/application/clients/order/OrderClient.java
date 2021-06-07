package com.example.demo.shopcartms.application.clients.order;

import com.example.demo.shopcartms.application.clients.goods.beans.UpdateGoodsInventoryRequest;
import com.example.demo.shopcartms.application.clients.goods.beans.UpdateGoodsInventoryResponse;
import com.example.demo.shopcartms.application.clients.order.beans.AddOrderRequest;
import com.example.demo.shopcartms.application.clients.order.beans.AddOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderClient {

    @Value("${client.url.order}")
    private String URL;

    @Autowired
    private RestTemplate restTemplate;

    public AddOrderResponse addOrder(AddOrderRequest req) {
        String path = String.join("/", URL, "orders");
        AddOrderResponse resp = restTemplate.postForObject(path, req, AddOrderResponse.class);
        return resp;
    }

}
