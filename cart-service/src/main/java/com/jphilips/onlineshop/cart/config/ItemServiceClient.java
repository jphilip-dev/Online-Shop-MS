package com.jphilips.onlineshop.cart.config;

import com.jphilips.onlineshop.shared.dto.CheckoutDTO;
import com.jphilips.onlineshop.shared.dto.ItemResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "item-service", url = "http://item-service:8082")
public interface ItemServiceClient {

    @GetMapping("/internal/items")
    List<ItemResponseDTO> getItemsByIds(@RequestParam List<Long> ids);

    @PostMapping("/internal/items/checkout")
    void checkoutItems(@RequestBody CheckoutDTO checkoutDTO);
}
