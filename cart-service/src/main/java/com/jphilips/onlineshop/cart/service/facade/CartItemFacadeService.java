package com.jphilips.onlineshop.cart.service.facade;

import com.jphilips.onlineshop.cart.dto.CartItemResponseDTO;
import com.jphilips.onlineshop.cart.dto.DeleteCartItemCommand;
import com.jphilips.onlineshop.cart.dto.GetAllCarItemsByUserQuery;
import com.jphilips.onlineshop.cart.service.command.DeleteCartItemService;
import com.jphilips.onlineshop.cart.service.query.GetAllCarItemsByUserService;
import com.jphilips.onlineshop.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemFacadeService {

    private final GetAllCarItemsByUserService getAllCarItemsByUserService;
    private final DeleteCartItemService deleteCartItemService;

    // Query: Get All Cart Items of Requestor User
    public PagedResponse<CartItemResponseDTO> fetchUserCartItems(GetAllCarItemsByUserQuery query){
        return getAllCarItemsByUserService.execute(query);
    }

    // Command: Delete Cart Item
    public void deleteCartItem(DeleteCartItemCommand command) {
        deleteCartItemService.execute(command);
    }

}
