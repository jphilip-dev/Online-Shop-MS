package com.jphilips.onlineshop.item.service;

import com.jphilips.onlineshop.item.dto.CreateItemCommand;
import com.jphilips.onlineshop.item.dto.ItemResponseDTO;
import com.jphilips.onlineshop.item.mapper.ItemMapper;
import com.jphilips.onlineshop.item.repository.ItemRepository;
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

        var itemRequestDTO = itemCommand.itemRequestDTO();

        var newItem = itemMapper.toEntity(itemRequestDTO);

        newItem.setSellerId(itemCommand.userId());
        newItem.setSellerEmail(itemCommand.userEmail());
        newItem.setSellerName(itemCommand.userName());

        newItem.setActive(true);

        newItem.setCreatedAt(LocalDateTime.now());
        newItem.setUpdatedAt(LocalDateTime.now());

        itemServiceHelper.save(newItem);

        return itemMapper.toDto(newItem);
    }
}
