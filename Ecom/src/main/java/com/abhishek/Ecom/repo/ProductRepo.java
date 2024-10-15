package com.abhishek.Ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.Ecom.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
