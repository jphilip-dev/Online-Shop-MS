package com.jphilips.onlineshop.item.controller;

import com.jphilips.onlineshop.item.service.CheckoutItemsService;
import com.jphilips.onlineshop.item.service.GetItemQueryService;
import com.jphilips.onlineshop.shared.dto.CheckoutDTO;
import com.jphilips.onlineshop.shared.dto.ItemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/items")
@RequiredArgsConstructor
public class ItemInternalController {

    private final GetItemQueryService getItemQueryService;
    private final CheckoutItemsService checkoutItemsService;

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getItemsByIds(@RequestParam List<Long> ids) {
        List<ItemResponseDTO> items = getItemQueryService.getItemsByIds(ids);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> checkoutItems(@RequestBody CheckoutDTO checkoutDTO) {

        checkoutItemsService.execute(checkoutDTO);

        return ResponseEntity.noContent().build();
    }
}
