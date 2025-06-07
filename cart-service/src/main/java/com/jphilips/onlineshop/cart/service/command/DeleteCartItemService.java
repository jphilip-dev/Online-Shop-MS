package com.jphilips.onlineshop.cart.service.command;

import com.jphilips.onlineshop.cart.dto.DeleteCartItemCommand;
import com.jphilips.onlineshop.cart.service.helper.CartServiceHelper;
import com.jphilips.onlineshop.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCartItemService implements Command<DeleteCartItemCommand, Void> {

    private final CartServiceHelper cartServiceHelper;

    @Override
    public Void execute(DeleteCartItemCommand command) {
        var cartItem = cartServiceHelper.getByUserIdAndItemId(command.userId(), command.cartItemId());
        cartServiceHelper.delete(cartItem);

        return null; // nothing to return (Void), operation succeeded
    }
}
