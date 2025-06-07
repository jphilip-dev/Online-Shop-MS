package com.jphilips.onlineshop.cart.service;

import com.jphilips.onlineshop.cart.config.ItemServiceClient;
import com.jphilips.onlineshop.cart.dto.CartItemResponseDTO;
import com.jphilips.onlineshop.cart.dto.GetAllCarItemsByUserQuery;
import com.jphilips.onlineshop.cart.entity.CartItem;
import com.jphilips.onlineshop.cart.mapper.CartItemMapper;
import com.jphilips.onlineshop.cart.repository.CartItemRepository;
import com.jphilips.onlineshop.shared.dto.ItemResponseDTO;
import com.jphilips.onlineshop.shared.dto.PagedResponse;
import com.jphilips.onlineshop.shared.util.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllCarItemsByUserService implements Query<GetAllCarItemsByUserQuery, PagedResponse<CartItemResponseDTO>> {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    private final ItemServiceClient itemServiceClient;


    @Override
    public PagedResponse<CartItemResponseDTO> execute(GetAllCarItemsByUserQuery query) {

        Page<CartItem> cartItemsPage = cartItemRepository.findByUserId(
                query.userId(),
                PageRequest.of(
                        query.pageable().getPageNumber(),
                        query.pageable().getPageSize(),
                        Sort.by(Sort.Direction.DESC, "lastUpdateAt")
                )
        );


        List<Long> itemIds = cartItemsPage
                .stream()
                .map(CartItem::getItemId)
                .toList();

        Map<Long, ItemResponseDTO> itemMap = itemServiceClient.getItemsByIds(itemIds)
                .stream()
                .collect(Collectors.toMap(ItemResponseDTO::id, Function.identity()));

        List<CartItemResponseDTO> responseList = cartItemsPage
                .stream()
                .map(cartItem -> {
                    ItemResponseDTO itemResponse = itemMap.get(cartItem.getItemId());
                    return cartItemMapper.toDto(cartItem, itemResponse);
                })
                .toList();

        return new PagedResponse<>(responseList, cartItemsPage);
    }
}
