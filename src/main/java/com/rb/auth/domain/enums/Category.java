package com.rb.auth.domain.enums;

public enum Category {
    FOOTWEAR("accessory"),
    APPAREL("clothing"),
    BAG("accessory"),
    ACCESSORIES("accessory"),
    TOYS("entertainment"),
    BOOKS("entertainment"),
    BEAUTY("personal care"),
    PET_SUPPLIES("pet care");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
