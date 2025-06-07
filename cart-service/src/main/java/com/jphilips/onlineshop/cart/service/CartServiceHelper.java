package com.jphilips.onlineshop.cart.service;

import com.jphilips.onlineshop.cart.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceHelper {

    private final CartItemRepository cartRepository;
}
