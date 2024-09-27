package com.rb.auth.repositories;

import com.rb.auth.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    Optional<Store> findById (String id);
    Optional<Store> findByName(String name);


}
