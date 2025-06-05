package com.jphilips.onlineshop.item.dto;

import com.jphilips.onlineshop.item.validator.UniqueSku;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemRequestDTO {

    @NotBlank
    @UniqueSku
    private String sku;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotBlank
    private String description;

    @NotNull
    @Min(1)
    private Integer stocks;

    @NotNull
    @DecimalMin("1.00")
    private BigDecimal price;

    private String brand;

    private String imageUrl;

}
