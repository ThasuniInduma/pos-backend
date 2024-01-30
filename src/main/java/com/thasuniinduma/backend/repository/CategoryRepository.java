package com.thasuniinduma.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thasuniinduma.backend.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
