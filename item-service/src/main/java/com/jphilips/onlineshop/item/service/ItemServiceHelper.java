package com.jphilips.onlineshop.item.service;

import com.jphilips.onlineshop.item.entity.Item;
import com.jphilips.onlineshop.item.exception.custom.ItemNotFoundException;
import com.jphilips.onlineshop.item.exception.custom.SkuAlreadyExistsException;
import com.jphilips.onlineshop.item.repository.ItemRepository;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceHelper {

    private final ItemRepository itemRepository;

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public Item getItemById(Long id){
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(ErrorCode.ITEM_NOT_FOUND));
    }

    public Item getItemBySku(String sku){
        return itemRepository.findBySku(sku)
                .orElseThrow(() -> new ItemNotFoundException(ErrorCode.ITEM_NOT_FOUND));
    }

    public void validateNewSku(String sku){
        if (itemRepository.findBySku(sku).isPresent()){
            throw new SkuAlreadyExistsException(ErrorCode.ITEM_EXISTING_SKU);
        }
    }
}
