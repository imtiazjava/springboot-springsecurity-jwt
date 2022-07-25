package com.cgi.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.main.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

	Product findByProductName(String productName);
}
