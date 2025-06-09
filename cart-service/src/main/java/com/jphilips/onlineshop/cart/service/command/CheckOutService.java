package com.jphilips.onlineshop.cart.service.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jphilips.onlineshop.cart.config.ItemServiceClient;
import com.jphilips.onlineshop.cart.dto.CheckOutCommand;
import com.jphilips.onlineshop.cart.entity.CartItem;
import com.jphilips.onlineshop.cart.service.helper.CartItemManager;
import com.jphilips.onlineshop.shared.dto.CheckoutDTO;
import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.CheckOutException;
import com.jphilips.onlineshop.shared.util.Command;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckOutService implements Command<CheckOutCommand, Void> {

    private final ItemServiceClient itemServiceClient;
    private final CartItemManager cartItemManager;
    private final ObjectMapper objectMapper;

    @Override
    public Void execute(CheckOutCommand command) {

        try {
            checkout(command);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); // will be handled by controller advice (Exception.class)
        }

        return null;
    }

    private void checkout(CheckOutCommand command) throws JsonProcessingException {

        List<CartItem> cartItems = command.itemIds().stream()
                .map(id -> cartItemManager.getByUserIdAndItemId(command.userId(), id))
                .toList();

        Map<Long, Integer> itemAndCount = cartItems.stream()
                .collect(Collectors.toMap(CartItem::getItemId, CartItem::getCount));

        try {
            itemServiceClient.checkoutItems(new CheckoutDTO(command.userId(), itemAndCount));
        }catch (FeignException ex){
            try {
                ExceptionResponseDTO errorResponse = objectMapper.readValue(ex.contentUTF8(), ExceptionResponseDTO.class);
                Map<Long, String> itemErrorsFromService = errorResponse.itemErrors();
                throw new CheckOutException(ErrorCode.CART_CHECKOUT_ERROR, itemErrorsFromService);
            } catch (JsonProcessingException  e) {
                throw new CheckOutException(ErrorCode.CART_CHECKOUT_ERROR, null);
            }
        }
    }
}



//        Map<Long, ItemResponseDTO> itemMap = itemServiceClient.getItemsByIds(command.itemIds())
//                .stream()
//                .collect(Collectors.toMap(ItemResponseDTO::id, Function.identity()));

//        Map<Long, String> itemErrors = new HashMap<>();
//
//        for (CartItem cartItem : cartItems) {
//            ItemResponseDTO item = itemMap.get(cartItem.getItemId());
//            // Item no longer available or stock is low
//            if (item == null) {
//                itemErrors.put(cartItem.getItemId(), ErrorCode.ITEM_NOT_AVAILABLE.getMessageCode());
//            } else if(cartItem.getCount() > item.stocks()) {
//                itemErrors.put(cartItem.getItemId(), ErrorCode.ITEM_LOW_STOCK.getMessageCode());
//            }
//        }
//
//        if(!itemErrors.isEmpty()){
//            throw new CheckOutException(ErrorCode.CART_CHECKOUT_ERROR, itemErrors);
//        }