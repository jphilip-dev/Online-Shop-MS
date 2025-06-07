package com.jphilips.onlineshop.cart.service.command;

import com.jphilips.onlineshop.cart.entity.CartItem;
import com.jphilips.onlineshop.cart.repository.CartItemRepository;
import com.jphilips.onlineshop.cart.service.helper.CartServiceHelper;
import com.jphilips.onlineshop.shared.dto.AddToCartDTO;
import com.jphilips.onlineshop.shared.util.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddToCartService implements Command<AddToCartDTO, Void> {

    private final CartServiceHelper cartServiceHelper;
    private final CartItemRepository cartItemRepository;

    @Override
    public Void execute(AddToCartDTO command) {

        // NOTE: Assumes data is always truthful as this is called internally in item-service

        Optional<CartItem> optionalCartItem= cartItemRepository.findByUserIdAndItemId(command.userId(), command.itemId());

        if(optionalCartItem.isPresent()){
            // Update count if cart item exists
            CartItem cartItem = optionalCartItem.get();
            cartItem.setCount(cartItem.getCount() + command.count());

            // fallback
            if (cartItem.getCount() < 1){
                cartItem.setCount(1);
            }

            cartServiceHelper.save(cartItem);

        } else {
            CartItem newCartItem = new CartItem(
                    null,
                    command.userId(),
                    command.itemId(),
                    command.count(),
                    LocalDateTime.now()
            );
            cartServiceHelper.save(newCartItem);
        }

        return null;
    }
}
