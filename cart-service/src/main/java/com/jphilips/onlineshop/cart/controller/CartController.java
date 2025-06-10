package com.jphilips.onlineshop.cart.controller;

import com.jphilips.onlineshop.cart.dto.CartItemResponseDTO;
import com.jphilips.onlineshop.cart.dto.DeleteCartItemCommand;
import com.jphilips.onlineshop.cart.dto.GetAllCarItemsByUserQuery;
import com.jphilips.onlineshop.cart.service.facade.CartItemFacadeService;
import com.jphilips.onlineshop.shared.dto.PagedResponse;
import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "Cart Management", description = "APIs for managing user cart items")
public class CartController {

    private final CartItemFacadeService cartItemFacadeService;

    @Operation(
            summary = "Get All Cart Items for User",
            description = "Retrieve all cart items for the authenticated user with pagination"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PagedResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content())
    })
    @GetMapping
    public PagedResponse<CartItemResponseDTO> getAllCartItemsByUser(
            @Parameter(description = "ID of the authenticated user", required = true)
            @RequestHeader(value = "X-User-Id") Long userId,
            Pageable pageable){
        return cartItemFacadeService.fetchUserCartItems(new GetAllCarItemsByUserQuery(userId,pageable));
    }

    @Operation(
            summary = "Delete Cart Item",
            description = "Delete a specific cart item for the authenticated user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cart item deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Cart item not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(
            @Parameter(description = "ID of the authenticated user", required = true)
            @RequestHeader(value = "X-User-Id") Long userId,
            @Parameter(description = "ID of the cart item to delete", required = true)
            @PathVariable Long cartItemId){

        cartItemFacadeService.deleteCartItem(new DeleteCartItemCommand(userId, cartItemId));
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Checkout Cart Items",
            description = "Checkout selected cart items for the authenticated user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Checkout successful"),
            @ApiResponse(responseCode = "400", description = "Invalid item IDs"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/checkout")
    public ResponseEntity<Void> checkoutItems(
            @Parameter(description = "ID of the authenticated user", required = true)
            @RequestHeader(value = "X-User-Id") Long userId,
            @Parameter(description = "List of item IDs to checkout", required = true)
            @RequestParam List<Long> itemIds
    ){
        cartItemFacadeService.checkoutItems(userId, itemIds);
        return ResponseEntity.noContent().build();
    }
}