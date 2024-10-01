package com.rb.auth.domain.user;


public enum UserRole {
    ADMIN("admin"),
    GERENTE("gerente"),
    FUNCIONARIO("funcionario"),
    USER("user");

    private String role;
    UserRole(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }
}
