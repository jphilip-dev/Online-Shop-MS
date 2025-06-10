package com.jphilips.onlineshop.item.controller;

import com.jphilips.onlineshop.item.dto.*;
import com.jphilips.onlineshop.item.service.ItemFacadeService;
import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.dto.ItemResponseDTO;
import com.jphilips.onlineshop.shared.dto.PagedResponse;
import com.jphilips.onlineshop.shared.dto.UserDetailsDTO;
import com.jphilips.onlineshop.shared.validator.groups.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Item Management", description = "APIs for managing items in the shop")
public class ItemController {

    private final ItemFacadeService itemFacadeService;

    /*
     * ----Queries-endpoints----
     */
    @Operation(
            summary = "Get Items",
            description = "Retrieve a paginated list of all items"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PagedResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<PagedResponse<ItemResponseDTO>> getItems(@PageableDefault(size = 10, sort = "name") Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemFacadeService.getAllItems(pageable));
    }

    @Operation(
            summary = "Search Items",
            description = "Search for items by query string"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Items found",
                    content = @Content(schema = @Schema(implementation = PagedResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/search")
    public ResponseEntity<PagedResponse<ItemResponseDTO>> searchItems(
            @RequestParam String query,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        return ResponseEntity.ok(itemFacadeService.searchItems(query, pageable));
    }

    @Operation(
            summary = "Get Item by SKU",
            description = "Retrieve an item by its SKU"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item found",
                    content = @Content(schema = @Schema(implementation = ItemResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Item not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/sku")
    public  ResponseEntity<ItemResponseDTO> getItemBySku(@RequestParam String sku){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemFacadeService.getItemBySku(sku));
    }

    @Operation(
            summary = "Get Item by ID",
            description = "Retrieve an item by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item found",
                    content = @Content(schema = @Schema(implementation = ItemResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Item not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")

    })
    @GetMapping("/{id}")
    public  ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemFacadeService.getItemById(id));
    }

    /*
     * ----Command-endpoints----
     */
    @Operation(
            summary = "Add Item to Cart",
            description = "Add a specific item to the user's cart"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item added to cart"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/{id}/add-to-cart")
    public ResponseEntity<Void> addToCart(
            @RequestHeader(value = "X-User-Id") Long headerId,
            @RequestHeader(value = "X-User-Email") String headerEmail,
            @RequestHeader(value = "X-User-Name") String headerName,
            @PathVariable Long id,
            @RequestParam Integer count){

        var userDetailsDto = new UserDetailsDTO(headerId, headerEmail,headerName);

        itemFacadeService.addToCart(new AddToCartCommand(userDetailsDto, id,count));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }
    @Operation(
            summary = "Create Item",
            description = "Create a new item"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Item created",
                    content = @Content(schema = @Schema(implementation = ItemResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<ItemResponseDTO> createItem(
            @RequestHeader(value = "X-User-Id") Long headerId,
            @RequestHeader(value = "X-User-Email") String headerEmail,
            @RequestHeader(value = "X-User-Name") String headerName,
            @Validated({Default.class, OnCreate.class}) @RequestBody ItemRequestDTO itemRequestDTO
            ){

        var userDetailsDto = new UserDetailsDTO(headerId, headerEmail,headerName);

        var itemResponseDto = itemFacadeService.createItem(new CreateItemCommand(userDetailsDto, itemRequestDTO));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemResponseDto);
    }
    @Operation(
            summary = "Update Item",
            description = "Update an existing item"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item updated",
                    content = @Content(schema = @Schema(implementation = ItemResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Item not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(
            @RequestHeader(value = "X-User-Id") Long headerId,
            @RequestHeader(value = "X-User-Email") String headerEmail,
            @RequestHeader(value = "X-User-Name") String headerName,
            @PathVariable Long id,
            @Validated(Default.class) @RequestBody ItemRequestDTO itemRequestDTO
    ){
        var userDetailsDto = new UserDetailsDTO(headerId, headerEmail,headerName);

        var itemResponseDto = itemFacadeService.updateItem(new UpdateItemCommand(id, userDetailsDto, itemRequestDTO));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemResponseDto);
    }

    @Operation(
            summary = "Delete Item",
            description = "Delete an item by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Item deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Item not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @RequestHeader(value = "X-User-Id") Long headerId,
            @RequestHeader(value = "X-User-Email") String headerEmail,
            @RequestHeader(value = "X-User-Name") String headerName,
            @PathVariable Long id
    ){
        var userDetailsDto = new UserDetailsDTO(headerId, headerEmail,headerName);

        itemFacadeService.deleteItem(new DeleteItemCommand(id, userDetailsDto));

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

}
