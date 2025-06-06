package com.jphilips.onlineshop.item.controller;

import com.jphilips.onlineshop.item.dto.*;
import com.jphilips.onlineshop.item.service.GetItemQueryService;
import com.jphilips.onlineshop.item.service.ItemFacadeService;
import com.jphilips.onlineshop.shared.dto.UserDetailsDTO;
import com.jphilips.onlineshop.shared.validator.groups.OnCreate;
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
public class ItemController {

    private final ItemFacadeService itemFacadeService;

    /*
     * ----Queries-endpoints----
     */
    @GetMapping
    public ResponseEntity<PagedResponse<ItemResponseDTO>> getItems(@PageableDefault(size = 10, sort = "name") Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemFacadeService.getAllItems(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<PagedResponse<ItemResponseDTO>> searchItems(
            @RequestParam String query,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        return ResponseEntity.ok(itemFacadeService.searchItems(query, pageable));
    }

    @GetMapping("/sku")
    public  ResponseEntity<ItemResponseDTO> getItemBySku(@RequestParam String sku){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemFacadeService.getItemBySku(sku));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemFacadeService.getItemById(id));
    }

    /*
     * ----Command-endpoints----
     */
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
