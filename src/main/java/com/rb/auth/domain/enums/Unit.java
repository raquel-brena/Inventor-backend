package com.rb.auth.domain.enums;


public enum Unit {
    EACH("each"),
    PACK("pack");

    private String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
