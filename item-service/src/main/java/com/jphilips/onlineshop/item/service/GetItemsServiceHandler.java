package com.jphilips.onlineshop.item.service;

import com.jphilips.onlineshop.item.dto.ItemResponseDTO;
import com.jphilips.onlineshop.item.dto.PagedResponse;
import com.jphilips.onlineshop.item.mapper.ItemMapper;
import com.jphilips.onlineshop.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetItemsServiceHandler {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ItemServiceHelper itemServiceHelper;

    public PagedResponse<ItemResponseDTO> getAllItems(Pageable pageable) {
        Page<ItemResponseDTO> dtoPage = itemRepository.findAll(pageable)
                .map(itemMapper::toDto);
        return new PagedResponse<>(dtoPage.getContent(), dtoPage);
    }

    public PagedResponse<ItemResponseDTO> searchItems(String query, Pageable pageable) {
        Page<ItemResponseDTO> dtoPage = itemRepository
                .findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query, pageable)
                .map(itemMapper::toDto);

        return new PagedResponse<>(dtoPage.getContent(), dtoPage);
    }

    public ItemResponseDTO getItemBySku(String sku){
        return itemMapper.toDto(itemServiceHelper.getItemBySku(sku));
    }

    public ItemResponseDTO getItemById(Long id){
        return itemMapper.toDto(itemServiceHelper.getItemById(id));
    }
}
