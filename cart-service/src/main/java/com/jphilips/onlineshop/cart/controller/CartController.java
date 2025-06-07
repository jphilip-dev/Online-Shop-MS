package com.jphilips.onlineshop.cart.controller;

import com.jphilips.onlineshop.cart.dto.CartItemResponseDTO;
import com.jphilips.onlineshop.cart.dto.GetAllCarItemsByUserQuery;
import com.jphilips.onlineshop.cart.service.GetAllCarItemsByUserService;
import com.jphilips.onlineshop.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final GetAllCarItemsByUserService getAllCarItemsByUserService;

    @GetMapping
    public PagedResponse<CartItemResponseDTO> getAllCartItems(
            @RequestHeader(value = "X-User-Id") Long userId,
            Pageable pageable){
        return getAllCarItemsByUserService.execute(new GetAllCarItemsByUserQuery(userId,pageable));
    }

}
