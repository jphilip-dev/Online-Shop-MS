package com.jphilips.onlineshop.item.service;

import com.jphilips.onlineshop.item.dto.ItemResponseDTO;
import com.jphilips.onlineshop.item.dto.UpdateItemCommand;
import com.jphilips.onlineshop.item.enums.ItemCategory;
import com.jphilips.onlineshop.item.mapper.ItemMapper;
import com.jphilips.onlineshop.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateItemService implements Command<UpdateItemCommand, ItemResponseDTO> {

    private final ItemServiceHelper itemServiceHelper;
    private final ItemMapper itemMapper;

    @Override
    public ItemResponseDTO execute(UpdateItemCommand itemCommand) {
        // Extract Data
        var itemId = itemCommand.itemId();
        var userDetailsDto = itemCommand.userDetailsDTO();
        var itemRequestDto = itemCommand.itemRequestDTO();

        // Get existing item
        var item = itemServiceHelper.getItemById(itemId);

        // Ownership check
        itemServiceHelper.validateOwnership(userDetailsDto,item.getSellerId());

        // update fields
        item.setName(itemRequestDto.getName());
        item.setCategory(ItemCategory.fromString(itemRequestDto.getCategory())); // have a fallback
        item.setDescription(itemRequestDto.getDescription());
        item.setStocks(itemRequestDto.getStocks());
        item.setPrice(itemRequestDto.getPrice());

        // SKU check and validation
        if(!item.getSku().equals(itemRequestDto.getSku())){
            itemServiceHelper.validateNewSku(itemRequestDto.getSku());
            item.setSku(itemRequestDto.getSku());
        }

        // Extra validation for optional fields
        if (itemRequestDto.getBrand() != null && !itemRequestDto.getBrand().isBlank()){
            item.setBrand(itemRequestDto.getBrand());
        }
        if (itemRequestDto.getImageUrl() != null && !itemRequestDto.getImageUrl().isBlank()){
            item.setImageUrl(itemRequestDto.getImageUrl());
        }

        // Update the update date field
        item.setUpdatedAt(LocalDateTime.now());

        // Save call, optional
        itemServiceHelper.save(item);

        return itemMapper.toDto(item);
    }
}
