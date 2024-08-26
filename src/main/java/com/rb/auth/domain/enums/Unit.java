package com.rb.auth.domain.enums;

public enum Unit {
    EACH ("each"),
    KILOGRAM("kg"),
    LITRE("litre"),
    GRAM("gram");

    private String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }  
}
