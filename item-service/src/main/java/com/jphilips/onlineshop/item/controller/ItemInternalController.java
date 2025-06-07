package com.jphilips.onlineshop.item.controller;

import com.jphilips.onlineshop.item.service.GetItemQueryService;
import com.jphilips.onlineshop.shared.dto.ItemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal/items")
@RequiredArgsConstructor
public class ItemInternalController {

    private final GetItemQueryService getItemQueryService;

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getItemsByIds(@RequestParam List<Long> ids) {
        List<ItemResponseDTO> items = getItemQueryService.getItemsByIds(ids);
        return ResponseEntity.ok(items);
    }
}
