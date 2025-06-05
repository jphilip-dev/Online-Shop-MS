package com.jphilips.onlineshop.item.enums;

public enum ItemCategory {
    CLOTHING,
    FOOD,
    ELECTRONICS,
    HOME_APPLIANCES,
    FURNITURE,
    TOYS,
    BOOKS,
    BEAUTY,
    SPORTS,
    AUTOMOTIVE,
    STATIONERY,
    PET_SUPPLIES,
    HEALTH,
    GARDEN,
    JEWELRY,
    FOOTWEAR,
    ACCESSORIES,
    OTHERS;

    public static ItemCategory fromString(String value) {
        try {
            return ItemCategory.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return OTHERS;
        }
    }
}