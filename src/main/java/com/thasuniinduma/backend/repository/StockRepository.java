package com.thasuniinduma.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thasuniinduma.backend.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByItemId(Long itemId);
    
}
