package com.jphilips.onlineshop.item.service;

import com.jphilips.onlineshop.item.entity.Item;
import com.jphilips.onlineshop.item.repository.ItemRepository;
import com.jphilips.onlineshop.shared.dto.CheckoutDTO;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.CheckOutException;
import com.jphilips.onlineshop.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CheckoutItemsService implements Command<CheckoutDTO, Void> {

    private final ItemRepository itemRepository;

    @Override
    public Void execute(CheckoutDTO checkoutDTO) {

        Map<Long, String> itemErrors = new HashMap<>();

        List<Item> items = itemRepository.findAllById(checkoutDTO.itemAndCount().keySet());

        for (var item : items){

            Integer count = checkoutDTO.itemAndCount().get(item.getId());

            if(!item.isActive()){
                itemErrors.put(item.getId(), ErrorCode.ITEM_NOT_AVAILABLE.getMessageCode());
                continue;
            } else if( count > item.getStocks()){
                itemErrors.put(item.getId(), ErrorCode.ITEM_LOW_STOCK.getMessageCode());
                continue;
            }

            // Deduct the stock
            item.setStocks(item.getStocks() - count);

        }

        if (!itemErrors.isEmpty()){
            // will this roll back the deduction of stocks ?
            throw new CheckOutException(ErrorCode.CHECKOUT_ERROR, itemErrors); // Handled by Controller advice
        }

        itemRepository.saveAll(items);


        return null;
    }

}
