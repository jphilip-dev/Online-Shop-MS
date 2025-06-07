package com.jphilips.onlineshop.item.config;

import com.jphilips.onlineshop.shared.dto.AddToCartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cart-service", url = "http://cart-service:8083")
public interface CartServiceClient {

    @PostMapping("/internal/cart")
    void addToCart(@RequestBody AddToCartDTO addToCartDTO);
}
