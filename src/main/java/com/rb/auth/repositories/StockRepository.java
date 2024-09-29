package com.rb.auth.repositories;

import com.rb.auth.domain.address.Address;
import com.rb.auth.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
