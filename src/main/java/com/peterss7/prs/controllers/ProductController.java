package com.peterss7.prs.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.peterss7.prs.entities.Product;
import com.peterss7.prs.entities.dtos.ProductCreate;
import com.peterss7.prs.entities.product.ProductResponseDefault;
import com.peterss7.prs.repositories.ProductRepository;

import com.peterss7.prs.services.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:4200")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	

	
	@GetMapping("")
	public ResponseEntity<List<ProductResponseDefault>> findProductsByFields(
			@RequestParam(required = false) String partNumber,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String unit,
			@RequestParam(required = false) Double price,
			@RequestParam(required = false) String photopath,
			@RequestParam(required = false) Integer vendorId){
		
		try {
			
			if ((partNumber == null)  && 
				(name == null) &&
				(unit == null)  &&
				(price == null)     &&
				(photopath == null)     &&
				(vendorId == null)){
				
				List<ProductResponseDefault> products = productService.findAllProducts(); 
				
				return ResponseEntity.ok(products);
				
			} else{
				List<ProductResponseDefault> products = productService.findProductsByFields(
						partNumber, name, price,unit,
						photopath, vendorId);
				
				if (products == null) {
					return new ResponseEntity<List<ProductResponseDefault>>(products, HttpStatus.NOT_FOUND);
				}
				
				return ResponseEntity.ok(products);
				
			}	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}				
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable int id){
		try {
			Product product = productService.findProductById(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("")
	public ResponseEntity<Product> createProduct(@RequestBody ProductCreate newProduct){		
		try {
			Product product = productService.createProduct(newProduct);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product updatedProduct){
		return ResponseEntity.ok(productService.updateProduct(updatedProduct));
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductById(@PathVariable int id){
		return productService.deleteProductById(id);
	}
	
	@DeleteMapping("")
	public ResponseEntity<Void> deleteProductsByFields(
			@RequestParam(required = false) String partNumber,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String unit,
			@RequestParam(required = false) Double price,
			@RequestParam(required = false) String photopath,
			@RequestParam(required = false) Integer vendorId){
		
		try {
			
			if ((partNumber != null)  || 
				(name != null) ||
				(unit != null)  ||
				(price != null)     ||
				(photopath != null)     ||
				(vendorId != null)){
				
				 
				
				return productService.deleteProductsByFields(
							partNumber, name, price, 
							unit, photopath, vendorId);
			}
			else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
				
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}				
	}
	
}