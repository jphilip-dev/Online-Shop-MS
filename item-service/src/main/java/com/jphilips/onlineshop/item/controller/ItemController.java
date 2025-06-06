package com.jphilips.onlineshop.item.controller;

import com.jphilips.onlineshop.item.dto.ItemResponseDTO;
import com.jphilips.onlineshop.item.dto.PagedResponse;
import com.jphilips.onlineshop.item.service.GetItemQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final GetItemQueryService getItemQueryService;

    @GetMapping
    public ResponseEntity<PagedResponse<ItemResponseDTO>> getItems(@PageableDefault(size = 10, sort = "name") Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getItemQueryService.getAllItems(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<PagedResponse<ItemResponseDTO>> searchItems(
            @RequestParam String query,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        return ResponseEntity.ok(getItemQueryService.searchItems(query, pageable));
    }

    @GetMapping("/sku")
    public  ResponseEntity<ItemResponseDTO> getItemBySku(@RequestParam String sku){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
