package com.jphilips.onlineshop.item.service;

import com.jphilips.onlineshop.item.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemFacadeService {

    private final AddToCartService addToCartService;
    private final CreateItemService createItemService;
    private final UpdateItemService updateItemService;
    private final DeleteItemService deleteItemService;
    private final GetItemQueryService getItemQueryService;

    // Command: Item add to cart
    public void addToCart(AddToCartCommand command) {
        addToCartService.execute(command);
    }

    // Command: Create new item
    public ItemResponseDTO createItem(CreateItemCommand command) {
        return createItemService.execute(command);
    }

    // Command: Update existing item
    public ItemResponseDTO updateItem(UpdateItemCommand command) {
        return updateItemService.execute(command);
    }

    // Command: Delete existing item
    public void deleteItem(DeleteItemCommand command) {
        deleteItemService.execute(command);
    }

    // Query: Get all items
    public PagedResponse<ItemResponseDTO> getAllItems(Pageable pageable) {
        return getItemQueryService.getAllItems(pageable);
    }

    // Query: Get item by ID
    public ItemResponseDTO getItemById(Long id) {
        return getItemQueryService.getItemById(id);
    }

    // Query: Get item by SKU
    public ItemResponseDTO getItemBySku(String sku) {
        return getItemQueryService.getItemBySku(sku);
    }

    // Query: Search items based on some criteria
    public PagedResponse<ItemResponseDTO> searchItems(String query, Pageable pageable) {
        return getItemQueryService.searchItems(query, pageable);
    }

}
