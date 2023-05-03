package com.peterss7.prs.services;



import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import com.peterss7.prs.entities.Product;
import com.peterss7.prs.entities.Vendor;
import com.peterss7.prs.entities.dtos.ProductCreate;
import com.peterss7.prs.entities.product.ProductResponseDefault;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.peterss7.prs.entities.Product;

public interface IProductService {
	public abstract List<ProductResponseDefault> findAllProducts();
	public abstract Product findProductById(int id);
	public abstract List<ProductResponseDefault> findProductsByFields(
			String partNumber, String name, Double price,
			String unit, String photoPath, Integer vendorId);
	public abstract List<Product> findProductsByFields(
			Specification<Product> spec);
	
	public abstract Product updateProduct(Product updatedProduct);
	public abstract ResponseEntity<Void> deleteProductById(int id);
	public abstract ResponseEntity<Void> deleteProductsByFields(
			String partNumber, String name, Double price,
			String unit, String photoPath, Integer vendorId);
	Product createProduct(ProductCreate newProduct);
	
}
