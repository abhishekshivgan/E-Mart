package com.abhishek.Ecom.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String brand;
	private BigDecimal price;
	private String category;
	private Date releaseDate;
	private boolean available;
	private int quantity;
	
//	@Override
//	public String toString() {
//	    return "Product{" +
//	            "id=" + id +
//	            ", name='" + name + '\'' +
//	            ", description='" + description + '\'' +
//	            ", brand='" + brand + '\'' +
//	            ", price=" + price +
//	            ", category='" + category + '\'' +
//	            ", releaseDate=" + releaseDate +
//	            ", available=" + available +
//	            ", quantity=" + quantity +
//	            '}';
//	}

	
}