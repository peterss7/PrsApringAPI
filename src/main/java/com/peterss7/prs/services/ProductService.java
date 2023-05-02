package com.peterss7.prs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peterss7.prs.entities.Product;
import com.peterss7.prs.entities.dtos.ProductCreateObject;
import com.peterss7.prs.repositories.ProductRepository;
import com.peterss7.prs.specifications.ProductSpecifications;

@Service
public class ProductService implements IProductService{

	private final ProductRepository productRepository;
	@Autowired
	VendorService vendorService;
	
	
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public List<Product> findAllProducts(){
		return productRepository.findAll();
	}
	
	@Override 
	public Product findProductById(int id) {
		Product product = new Product();
		
		Optional<Product> optionalProduct = productRepository.findById(id);
		
		if (optionalProduct.isPresent()) {
			product = optionalProduct.get();
		}
		
		return product;
	}
	
	@Override
	public List<Product> findProductsByFields(Specification<Product> spec){
		
		List<Product> products = new ArrayList<Product>();
		
		Optional<List<Product>> optionalProducts = productRepository.findAll(spec);
		
		if (optionalProducts.isPresent()) {
			products = optionalProducts.get();
		}
		
		return products;
	}
	
	@Override
	public List<Product> findProductsByFields(String partNumber, String name, Double price,
		String unit, String photopath, Integer vendorId){
		
		Specification<Product> spec = Specification.where(null);
		
		if (partNumber != null) {
			System.out.println("partNumber is: " + partNumber);
			spec = spec.and(ProductSpecifications.partNumberLike(partNumber));
		}
		if (name != null) {
			System.out.println("first name is: " + name);
			spec = spec.and(ProductSpecifications.nameLike(name));
		}
		if (price != null) {
			spec = spec.and(ProductSpecifications.priceLike(price));
		}
		if (unit != null) {
			spec = spec.and(ProductSpecifications.unitLike(unit));
		}
		if (photopath != null) {
			spec = spec.and(ProductSpecifications.photopathLike(photopath));
		}
		if (vendorId != null) {
			spec = spec.and(ProductSpecifications.vendorIdLike(vendorId));
		}
		
		
		System.out.println(spec.hashCode());
		
		// List<Product> products = productRepository.findAll(spec);
		
		List<Product> products = new ArrayList<Product>();
		
		Optional<List<Product>> optionalProducts = productRepository.findAll(spec);
		
		if (optionalProducts.isPresent()) {
			products = optionalProducts.get();
		}
		else {
			return null;
		}
		
		
		return products;
	}
	
	@Override
	public Product createProduct(ProductCreateObject newProductObject) {
		
		Product newProduct = new Product();
		
		newProduct.setName(newProductObject.getName());
		newProduct.setPartNumber(newProductObject.getPartNumber());
		newProduct.setUnit(newProductObject.getUnit());
		newProduct.setPrice(newProductObject.getPrice());
		newProduct.setPhotoPath(newProductObject.getPhotopath());
		newProduct.setVendor(vendorService.findVendorById(newProductObject.getVendorId()));
		
		Product savedProduct = productRepository.save(newProduct);		
		return savedProduct;
	}
	
	@Override
	public Product updateProduct(Product updatedProduct) {
		
		Product product = new Product();
		
		Optional<Product> optionalProduct = productRepository.findById(updatedProduct.getId());
		
		if (optionalProduct.isPresent()) {
			product = productRepository.save(updatedProduct);
		}
		
		return product;				
	}
	
	@Override
	public ResponseEntity<Void> deleteProductById(int id) {		
		
		Optional<Product> optionalProduct = productRepository.findById(id);
		
		if (!optionalProduct.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else
		{
			productRepository.deleteById(id);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		
	}
	
	@Override
	public ResponseEntity<Void> deleteProductsByFields(String partNumber, String name, Double price,
		String unit, String photopath, Integer vendorId){
		
		Specification<Product> spec = Specification.where(null);
		
		if (partNumber != null) {
			System.out.println("partNumber is: " + partNumber);
			spec = spec.and(ProductSpecifications.partNumberLike(partNumber));
		}
		if (name != null) {
			System.out.println("first name is: " + name);
			spec = spec.and(ProductSpecifications.nameLike(name));
		}
		if (price != null) {
			spec = spec.and(ProductSpecifications.priceLike(price));
		}
		if (unit != null) {
			spec = spec.and(ProductSpecifications.unitLike(unit));
		}
		if (photopath != null) {
			spec = spec.and(ProductSpecifications.photopathLike(photopath));
		}
		if (vendorId != null) {
			spec = spec.and(ProductSpecifications.vendorIdLike(vendorId));
		}
		if (vendorId != null) {
			spec = spec.and(ProductSpecifications.vendorIdLike(vendorId));
		}

		List<Product> deleteProducts = new ArrayList<Product>();
		Optional<List<Product>> optionalProducts = productRepository.findAll(spec);
		
		
		if (optionalProducts.isPresent()) {
			deleteProducts = optionalProducts.get();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}


}
