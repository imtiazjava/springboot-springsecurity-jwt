package com.cgi.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.main.entity.Product;
import com.cgi.main.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAllProducts(){
		return this.productRepository.findAll();
	}
	
	public Product getProductByName(String name) {
		return this.productRepository.findByProductName(name);
	}
}
