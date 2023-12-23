package com.wolfcodea.test.aplication.firstexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wolfcodea.test.aplication.firstexample.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, String>{
    
}
