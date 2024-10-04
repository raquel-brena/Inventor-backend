package com.rb.auth.controllers;

import com.rb.auth.domain.permission.dto.CreatePermissionsDTO;
import com.rb.auth.domain.roles.Role;
import com.rb.auth.domain.roles.dto.CreateRoleDTO;
import com.rb.auth.services.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleDTO dto) {
        return ResponseEntity.ok(roleService.createRole(dto.name()));
    }

    @Transactional
    @PostMapping("/{roleId}/permissions")
    public ResponseEntity<Role> addPermissionsToRole(@PathVariable Long roleId, @RequestBody CreatePermissionsDTO dto) {
        Role updatedRole = roleService.addPermissionsToRole(roleId, dto.permissions());
        return ResponseEntity.ok(updatedRole);
    }
}