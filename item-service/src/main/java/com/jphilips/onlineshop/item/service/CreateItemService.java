package com.jphilips.onlineshop.item.service;

import com.jphilips.onlineshop.item.dto.CreateItemCommand;
import com.jphilips.onlineshop.shared.dto.ItemResponseDTO;
import com.jphilips.onlineshop.item.mapper.ItemMapper;
import com.jphilips.onlineshop.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateItemService implements Command<CreateItemCommand, ItemResponseDTO> {

    private final ItemServiceHelper itemServiceHelper;
    private final ItemMapper itemMapper;

    @Override
    public ItemResponseDTO execute(CreateItemCommand itemCommand) {

        var userDetailsDTO = itemCommand.userDetailsDTO();
        var itemRequestDTO = itemCommand.itemRequestDTO();

        var newItem = itemMapper.toEntity(itemRequestDTO);

        newItem.setSellerId(userDetailsDTO.userId());
        newItem.setSellerEmail(userDetailsDTO.userEmail());
        newItem.setSellerName(userDetailsDTO.userName());

        newItem.setActive(itemRequestDTO.getIsActive());

        newItem.setCreatedAt(LocalDateTime.now());
        newItem.setUpdatedAt(LocalDateTime.now());

        itemServiceHelper.save(newItem);

        return itemMapper.toDto(newItem);
    }
}
