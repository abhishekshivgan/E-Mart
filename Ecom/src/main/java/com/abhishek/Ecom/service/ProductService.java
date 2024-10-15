package com.abhishek.Ecom.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.Ecom.model.Product;
import com.abhishek.Ecom.repo.ProductRepo;



@Service
public class ProductService {

	private ProductRepo repo;
	
	@Autowired
	public void setProductRepo(ProductRepo repo) {
		this.repo = repo;
	}
	

	
	public List<Product> getAllProducts() {
		List<Product> products = repo.findAll();
	    System.out.println("Number of products found: " + products.size());
	    products.forEach(product -> System.out.println(product));
	    return products;
	}

	
}
