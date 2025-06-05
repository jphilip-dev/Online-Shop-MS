package com.jphilips.onlineshop.item.service;

import com.jphilips.onlineshop.item.entity.Item;
import com.jphilips.onlineshop.item.exception.custom.ItemNotFoundException;
import com.jphilips.onlineshop.item.repository.ItemRepository;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceHelper {

    private final ItemRepository itemRepository;

    public Item validateItemById(Long id){
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(ErrorCode.ITEM_NOT_FOUND));
    }

    public Item validateItemBySku(String sku){
        return itemRepository.findBySku(sku)
                .orElseThrow(() -> new ItemNotFoundException(ErrorCode.ITEM_NOT_FOUND));
    }
}
