package com.jphilips.onlineshop.cart.controller;

import com.jphilips.onlineshop.cart.dto.CartItemResponseDTO;
import com.jphilips.onlineshop.cart.dto.DeleteCartItemCommand;
import com.jphilips.onlineshop.cart.dto.GetAllCarItemsByUserQuery;
import com.jphilips.onlineshop.cart.service.facade.CartItemFacadeService;
import com.jphilips.onlineshop.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartItemFacadeService cartItemFacadeService;

    @GetMapping
    public PagedResponse<CartItemResponseDTO> getAllCartItemsByUser(
            @RequestHeader(value = "X-User-Id") Long userId,
            Pageable pageable){
        return cartItemFacadeService.fetchUserCartItems(new GetAllCarItemsByUserQuery(userId,pageable));
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(
            @RequestHeader(value = "X-User-Id") Long userId,
            @PathVariable Long cartItemId){

        cartItemFacadeService.deleteCartItem(new DeleteCartItemCommand(userId, cartItemId));

        return ResponseEntity.noContent().build();
    }

}
