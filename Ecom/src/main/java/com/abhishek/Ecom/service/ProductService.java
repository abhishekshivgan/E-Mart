package com.abhishek.Ecom.service;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
//	    System.out.println("Number of products found: " + products.size());
//	    products.forEach(product -> System.out.println(product));
	    return products;
	}

	public Product getProductById(int prodId) {
		return repo.findById(prodId).get();
	}

	public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(product);
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());
		return repo.save(product);
	}

	public Product updateProduct(int prodId, Product product, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());
		return repo.save(product);
	}

	public void deleteProduct(int prodId) {
		repo.deleteById(prodId);
	}	
}
