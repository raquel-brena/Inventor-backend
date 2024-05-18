package com.rb.auth.domain.product;

public enum Category {
    ACESSORIO ("acessorio"),
    NOTEBOOK ("notebook"),
    DESKTOP ("desktop"),
    MONITOR ("monitor");
    private String category;
    Category(String category) {
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

}
