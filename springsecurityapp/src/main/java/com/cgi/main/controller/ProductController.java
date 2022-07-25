package com.cgi.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.main.ProductService;
import com.cgi.main.entity.Product;

@RestController
@RequestMapping("/products/")
//@EnableGlobalMethodSecurity(prePostEnabled =true)
public class ProductController {

	@Autowired
	private ProductService productService;
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("msg")
	public String message(Authentication auth) {
		return "Hello:"+auth.getName();
	}
	
	
	@PostMapping("/save")
	public Product  saveProduct(@RequestBody Product product,Authentication auth) {
		System.out.println(auth.isAuthenticated());
		System.out.println(auth.getDetails());
		System.out.println(auth.getName());
		System.out.println(auth.getPrincipal());
		return this.productService.saveProduct(product);
	}
	
	@GetMapping("/all")
	public List<Product> getAllProducts(){
		return this.productService.getAllProducts();
	}
	
	@GetMapping("/{name}")
	public Product getProductByName(@PathVariable("name") String name)
	{
		return this.productService.getProductByName(name);
	}

}
