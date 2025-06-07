package com.jphilips.onlineshop.cart.service;

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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetAllCarItemsByUserService implements Query<GetAllCarItemsByUserQuery, PagedResponse<CartItemResponseDTO>> {

    private final CartItemRepository cartItemRepository;
    private final CartServiceHelper cartServiceHelper;
    private final CartItemMapper cartItemMapper;

    @Override
    public PagedResponse<CartItemResponseDTO> execute(GetAllCarItemsByUserQuery query) {

        Page<CartItem> cartItemsPage = cartItemRepository.findByUserId(query.userId(), query.pageable());

        List<Long> itemIds = cartItemsPage
                .stream()
                .map(CartItem::getItemId)
                .toList();

        // Fetch item details from item-service
//        Map<Long, ItemResponseDTO> itemMap = itemServiceClient.getItemsByIds(itemIds)
//                .stream()
//                .collect(Collectors.toMap(ItemResponseDTO::Id, Function.identity()));

        Map<Long, ItemResponseDTO> itemMap = new HashMap<>();
        for (Long itemId : itemIds) {
            itemMap.put(itemId, mockItem(itemId));
        }

        List<CartItemResponseDTO> responseList = cartItemsPage
                .stream()
                .map(cartItem -> {
                    ItemResponseDTO itemResponse = itemMap.get(cartItem.getItemId());
                    return cartItemMapper.toDto(cartItem, itemResponse);
                })
                .toList();

        return new PagedResponse<>(responseList, cartItemsPage);
    }

    private ItemResponseDTO mockItem(Long itemId) {
        return ItemResponseDTO.builder()
                .id(itemId)
                .sellerId(1L)
                .sellerName("Demo Seller")
                .sellerEmail("seller@example.com")
                .sku("SKU-" + itemId)
                .name("Mock Item " + itemId)
                .category("Category X")
                .description("This is a mock item.")
                .stocks(999)
                .price(BigDecimal.valueOf(99.99))
                .brand("MockBrand")
                .imageUrl("https://example.com/image.png")
                .build();
    }

}
