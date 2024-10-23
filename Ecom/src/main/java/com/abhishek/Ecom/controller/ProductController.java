package com.abhishek.Ecom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/product/{prodId}")
	public ResponseEntity<Product> getProduct(@PathVariable int prodId) {
		Product product = service.getProductById(prodId);
		if (product != null)
			return new ResponseEntity<>(service.getProductById(prodId), HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart Product product, 
										@RequestPart MultipartFile imageFile) {
		System.out.println(product);
		try {
			Product addedProduct = service.addProduct(product, imageFile);
			return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/{prodId}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable int prodId) {
        Product product = service.getProductById(prodId);

        if (product == null || product.getImageData() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        byte[] imageFile = product.getImageData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(product.getImageType()));

        return new ResponseEntity<>(imageFile, headers, HttpStatus.OK);
    }
    
    @PutMapping("/product/{prodId}")
    public ResponseEntity<String> updateProduct(@PathVariable int prodId,
                                                @RequestPart Product product,
                                                @RequestPart(required = false) MultipartFile imageFile) {
        Product updatedProduct = null;
        try {
            updatedProduct = service.updateProduct(prodId, product, imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Product not updated due to IOException", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Product not updated due to an unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (updatedProduct != null) {
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not updated", HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("product/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int prodId) {
    	Product product = service.getProductById(prodId);
    	if (product == null) {
    		return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    	}
    	
    	service.deleteProduct(prodId);
    	return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }
}
