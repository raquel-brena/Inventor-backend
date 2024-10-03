package com.rb.auth.services;

import com.rb.auth.domain.roles.Role;
import com.rb.auth.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public Role createRole(String name) {
        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleByName(String name) {
        return this.roleRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("Role doesnt exist"));
    }


}