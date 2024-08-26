package com.rb.auth.domain.enums;

public enum Status {
    ACTIVE("active"),
    INACTIVE("inactive"),
    DRAFT("draft");

    private String status;

    Status(String status) {
        this.status = status;
    }


    public String getStatus (){
        return status;
    }
}
