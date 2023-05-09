package com.peterss7.prs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peterss7.prs.controllers.UserController;
import com.peterss7.prs.entities.Product;
import com.peterss7.prs.entities.Vendor;
import com.peterss7.prs.entities.dtos.product.ProductCreate;
import com.peterss7.prs.entities.dtos.product.ProductDefaultResponse;
import com.peterss7.prs.entities.dtos.product.ProductUpdate;
import com.peterss7.prs.entities.dtos.product.ProductWithVendorIdResponse;
import com.peterss7.prs.repositories.ProductRepository;
import com.peterss7.prs.specifications.ProductSpecifications;

@Service
public class ProductService implements IProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	private final ProductRepository productRepository;
	@Autowired
	VendorService vendorService;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<ProductWithVendorIdResponse> createProduct(ProductCreate newProductObject) {

		try {
			Product newProduct = new Product();

			newProduct.setName(newProductObject.getName());
			newProduct.setPartNumber(newProductObject.getPartNumber());
			newProduct.setUnit(newProductObject.getUnit());
			newProduct.setPrice(newProductObject.getPrice());
			newProduct.setPhotopath(newProductObject.getPhotopath());
			newProduct.setVendor(getProductVendor(newProductObject.getVendorId()));

			Product savedProduct = productRepository.save(newProduct);
			return new ResponseEntity<ProductWithVendorIdResponse>(new ProductWithVendorIdResponse(savedProduct),
					HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public Vendor getProductVendor(int id) {
		return vendorService.findVendorById(id).getBody();
	}

	@Override
	public ResponseEntity<Void> deleteProductById(int id) {

		Optional<Product> optionalProduct = productRepository.findById(id);

		if (!optionalProduct.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			productRepository.deleteById(id);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

	}

	@Override
	public ResponseEntity<List<ProductWithVendorIdResponse>> findAllProducts() {

		// LOGGER.warn("Entering findAllProducts()");

		try {

			List<Product> products = productRepository.findAll();

			return new ResponseEntity<List<ProductWithVendorIdResponse>>
					(ProductWithVendorIdResponse.getResponses(products),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<Product> findAllProductsDev() {

		return productRepository.findAll();

	}

	@Override
	public ResponseEntity<ProductWithVendorIdResponse> findProductById(int id) {

		try {

			Optional<Product> optionalProduct = productRepository.findById(id);

			if (optionalProduct.isPresent()) {
				return new ResponseEntity<ProductWithVendorIdResponse>(
						new ProductWithVendorIdResponse(optionalProduct.get()), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<List<ProductWithVendorIdResponse>> findProductsByFields(Specification<Product> spec) {

		try {

			Optional<List<Product>> optionalProducts = productRepository.findAll(spec);

			if (optionalProducts.isPresent()) {
				return new ResponseEntity<List<ProductWithVendorIdResponse>>(
						ProductWithVendorIdResponse.getResponses(optionalProducts.get()), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> updateProduct(ProductUpdate updatedProduct) {
		
		LOGGER.warn("updating with this: " + updatedProduct.toString());

		try {

			LOGGER.warn("in try");
			
			Optional<Product> optionalProduct = productRepository.findById(updatedProduct.getId());

			if (optionalProduct.isPresent()) {
				
				Product saveProduct = optionalProduct.get();
				
				saveProduct.setVendor(getProductVendor(updatedProduct.getVendorId()));
				saveProduct.setName(updatedProduct.getName());
				saveProduct.setPartNumber(updatedProduct.getPartNumber());
				saveProduct.setUnit(updatedProduct.getUnit());
				saveProduct.setPrice(updatedProduct.getPrice());
				saveProduct.setPhotopath(updatedProduct.getPhotopath());
				
				
				productRepository.save(saveProduct);
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public void productsDev() {
		
		List<Product> products = productRepository.findAll();
		
		LOGGER.warn("***DEV***");
		
		for (Product product : products) {			
			if (product.getVendor() == null) {
				LOGGER.warn(product.getVendor() + "?");
				productRepository.delete(product);
			}
			
		}
	}

}
