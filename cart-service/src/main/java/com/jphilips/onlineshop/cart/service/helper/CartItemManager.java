package com.jphilips.onlineshop.cart.service.helper;

import com.jphilips.onlineshop.cart.entity.CartItem;
import com.jphilips.onlineshop.cart.exceptions.custom.CartItemNotFoundException;
import com.jphilips.onlineshop.cart.repository.CartItemRepository;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartItemManager {

    private final CartItemRepository cartRepository;

    public CartItem save(CartItem cartItem){

        if(cartItem.getId() == null){
            log.info("User: {} add to cart item: {} count: {}", cartItem.getUserId(), cartItem.getItemId(), cartItem.getCount());
        } else {
            log.info("User: {} update to cart item: {} count: {}", cartItem.getUserId(), cartItem.getItemId(), cartItem.getCount());
        }

        return cartRepository.save(cartItem);
    }

    public void delete(CartItem cartItem){
        log.info("User: {} removed item: {} from own cart", cartItem.getUserId(), cartItem.getItemId());
         cartRepository.delete(cartItem);
    }

    public CartItem getByUserIdAndItemId(Long userId, Long itemId){
        return cartRepository.findByUserIdAndItemId(userId, itemId)
                .orElseThrow(() -> new CartItemNotFoundException(ErrorCode.CART_ITEM_NOT_FOUND, itemId.toString()));
    }
}
