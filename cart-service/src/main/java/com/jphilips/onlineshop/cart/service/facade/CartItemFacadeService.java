package com.jphilips.onlineshop.cart.service.facade;

import com.jphilips.onlineshop.cart.dto.CartItemResponseDTO;
import com.jphilips.onlineshop.cart.dto.CheckOutCommand;
import com.jphilips.onlineshop.cart.dto.DeleteCartItemCommand;
import com.jphilips.onlineshop.cart.dto.GetAllCarItemsByUserQuery;
import com.jphilips.onlineshop.cart.service.command.CheckOutService;
import com.jphilips.onlineshop.cart.service.command.DeleteCartItemService;
import com.jphilips.onlineshop.cart.service.query.GetAllCarItemsByUserService;
import com.jphilips.onlineshop.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemFacadeService {

    private final GetAllCarItemsByUserService getAllCarItemsByUserService;
    private final DeleteCartItemService deleteCartItemService;
    private final CheckOutService checkOutService;

    // Query: Get All Cart Items of Requestor User
    public PagedResponse<CartItemResponseDTO> fetchUserCartItems(GetAllCarItemsByUserQuery query) {
        return getAllCarItemsByUserService.execute(query);
    }

    // Command: Delete Cart Item
    public void deleteCartItem(DeleteCartItemCommand command) {
        deleteCartItemService.execute(command);
    }

    // Command: Checkout items
    public void checkoutItems(Long userId, List<Long> itemIds) {
        checkOutService.execute(new CheckOutCommand(userId, itemIds));
    }

}
