package com.rb.auth.repositories;


import com.rb.auth.domain.permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByName(String name);
}
