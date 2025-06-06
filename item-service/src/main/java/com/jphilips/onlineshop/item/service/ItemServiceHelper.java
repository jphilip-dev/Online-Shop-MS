package com.jphilips.onlineshop.item.service;

import com.jphilips.onlineshop.item.entity.Item;
import com.jphilips.onlineshop.item.exception.custom.ItemNotFoundException;
import com.jphilips.onlineshop.item.exception.custom.ItemOwnerMismatchException;
import com.jphilips.onlineshop.item.exception.custom.SkuAlreadyExistsException;
import com.jphilips.onlineshop.item.repository.ItemRepository;
import com.jphilips.onlineshop.shared.dto.UserDetailsDTO;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceHelper {

    private final ItemRepository itemRepository;

    public Item save(Item item) {

        if (item.getId() == null) {
            log.info("Added new Item: {} by seller: {}", item.getSku(), item.getSellerEmail());
        } else {
            log.info("Updated Item: {} by seller: {}", item.getSku(), item.getSellerEmail());
        }

        return itemRepository.save(item);
    }

    public void delete(Item item) {
        log.info("Item: {} is deleted by: {}", item.getSku(), item.getSellerEmail());
        itemRepository.delete(item);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(ErrorCode.ITEM_NOT_FOUND));
    }

    public Item getItemBySku(String sku) {
        return itemRepository.findBySku(sku)
                .orElseThrow(() -> new ItemNotFoundException(ErrorCode.ITEM_NOT_FOUND));
    }

    public void validateNewSku(String sku) {
        if (itemRepository.findBySku(sku).isPresent()) {
            throw new SkuAlreadyExistsException(ErrorCode.ITEM_EXISTING_SKU);
        }
    }

    public <T> void validateOwnership(UserDetailsDTO userDetailsDTO, T seller) {
        if (seller instanceof Long sellerId) {
            if (!Objects.equals(userDetailsDTO.userId(), sellerId)) {
                throw new ItemOwnerMismatchException(ErrorCode.ITEM_OWNER_MISMATCH);
            }
        } else if (seller instanceof String sellerEmail) {
            if (!userDetailsDTO.userEmail().equals(sellerEmail)) {
                throw new ItemOwnerMismatchException(ErrorCode.ITEM_OWNER_MISMATCH);
            }
        } else {
            throw new ItemOwnerMismatchException(ErrorCode.ITEM_OWNER_MISMATCH);
        }
    }
}
