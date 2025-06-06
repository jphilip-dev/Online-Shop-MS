package com.jphilips.onlineshop.item.service;

import com.jphilips.onlineshop.item.dto.DeleteItemCommand;
import com.jphilips.onlineshop.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteItemService implements Command<DeleteItemCommand, Void> {

    private final ItemServiceHelper itemServiceHelper;

    @Override
    public Void execute(DeleteItemCommand command) {
        // Extract data
        var userDetailsDto = command.userDetailsDTO();
        var itemId = command.itemId();

        // Get item, throws error
        var item = itemServiceHelper.getItemById(itemId);

        // Ownership check, throws error
        itemServiceHelper.validateOwnership(userDetailsDto, item.getSellerId());

        // Delete
        itemServiceHelper.delete(item);

        // required return because of "Void"
        return null;
    }
}
