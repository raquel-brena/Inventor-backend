package com.rb.auth.domain.enums;

public enum Gender {

    MALE("male"),
    FEMALE("female"),
    UNISEX("unisex");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
    

