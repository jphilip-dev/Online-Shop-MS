package com.jphilips.onlineshop.cart.service.helper;

import com.jphilips.onlineshop.cart.entity.CartItem;
import com.jphilips.onlineshop.cart.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceHelper {

    private final CartItemRepository cartRepository;

    public CartItem save(CartItem cartItem){
        return cartRepository.save(cartItem);
    }

    public void delete(CartItem cartItem){
         cartRepository.delete(cartItem);
    }

    public CartItem getByUserIdAndItemId(Long userId, Long itemId){
        return cartRepository.findByUserIdAndItemId(userId, itemId)
                .orElseThrow();
    }
}
