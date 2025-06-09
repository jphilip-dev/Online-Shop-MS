package com.jphilips.onlineshop.cart.controller;

import com.jphilips.onlineshop.cart.service.command.AddToCartService;
import com.jphilips.onlineshop.shared.dto.AddToCartDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/cart")
@RequiredArgsConstructor
public class CartInternalController {

    private final AddToCartService addToCartService;

    @PostMapping
    public ResponseEntity<Void> addToCart(@RequestBody AddToCartDTO addToCartDTO){

        addToCartService.execute(addToCartDTO);

        return ResponseEntity.noContent().build();
    }
}
