package com.peterss7.prs.services;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.peterss7.prs.entities.Product;
import com.peterss7.prs.entities.Vendor;
import com.peterss7.prs.entities.dtos.product.ProductCreate;
import com.peterss7.prs.entities.dtos.product.ProductDefaultResponse;
import com.peterss7.prs.entities.dtos.product.ProductUpdate;
import com.peterss7.prs.entities.dtos.product.ProductWithVendorIdResponse;

public interface IProductService {
	public abstract ResponseEntity<ProductWithVendorIdResponse> createProduct(ProductCreate newProduct);

	public abstract ResponseEntity<Void> deleteProductById(int id);	

	public abstract ResponseEntity<List<ProductWithVendorIdResponse>> findAllProducts();

	public abstract ResponseEntity<ProductWithVendorIdResponse> findProductById(int id);

	public abstract ResponseEntity<List<ProductWithVendorIdResponse>> findProductsByFields(Specification<Product> spec);	

	public abstract ResponseEntity<String> updateProduct(ProductUpdate updatedProduct);

	public abstract Vendor getProductVendor(int id);
}
