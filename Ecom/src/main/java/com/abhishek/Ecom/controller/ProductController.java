package com.abhishek.Ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.Ecom.model.Product;
import com.abhishek.Ecom.service.ProductService;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
	
	private ProductService service;
	@Autowired
	public void setProductService(ProductService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public String greet() {
		return "Hello Ecom";
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}
}
