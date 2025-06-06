package com.jphilips.onlineshop.item.service;

import com.jphilips.onlineshop.item.dto.AddToCartCommand;
import com.jphilips.onlineshop.item.exception.custom.ItemNotAvailableException;
import com.jphilips.onlineshop.item.exception.custom.ItemStockException;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.util.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddToCartService implements Command<AddToCartCommand, Void> {

    private final ItemServiceHelper itemServiceHelper;

    @Override
    public Void execute(AddToCartCommand command) {

        // Extract data
        var userDetailsDto = command.userDetailsDTO();
        var itemId = command.itemId();
        var count = command.count();

        // Retrieve item else throw error
        var item = itemServiceHelper.getItemById(itemId);

        // check if active and stock count
        if(!item.isActive()){
            throw new ItemNotAvailableException(ErrorCode.ITEM_NOT_AVAILABLE);
        }
        if(item.getStocks() < count){
            throw new ItemStockException(ErrorCode.ITEM_LOW_STOCK);
        }

        // Rest call to Cart service,
        log.info("Sending details to Cart Service..");

        return null; // nothing to return (Void), operation succeeded
    }
}
