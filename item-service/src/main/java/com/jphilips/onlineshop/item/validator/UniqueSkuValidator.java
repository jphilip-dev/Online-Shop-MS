package com.jphilips.onlineshop.item.validator;

import com.jphilips.onlineshop.item.repository.ItemRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueSkuValidator implements ConstraintValidator<UniqueSku, String> {

    private final ItemRepository itemRepository;

    @Override
    public boolean isValid(String sku, ConstraintValidatorContext context) {
        return sku != null && itemRepository.findBySku(sku).isEmpty();
    }
}
