package com.peterss7.prs.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.peterss7.prs.entities.dtos.product.ProductCreate;
import com.peterss7.prs.entities.dtos.product.ProductDefaultResponse;
import com.peterss7.prs.entities.dtos.product.ProductUpdate;
import com.peterss7.prs.entities.dtos.product.ProductWithVendorIdResponse;
import com.peterss7.prs.services.ProductService;
import com.peterss7.prs.specifications.ProductSpecifications;

@RestController
@RequestMapping("/product")
@CrossOrigin("http://localhost:4200")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VendorController.class);

	@Autowired
	private ProductService productService;

	@PostMapping("")
	public ResponseEntity<ProductWithVendorIdResponse> createProduct(@RequestBody ProductCreate newProduct) {
		return productService.createProduct(newProduct);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
		return productService.deleteProductById(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductWithVendorIdResponse> findProductById(@PathVariable int id) {

		return productService.findProductById(id);
	}

	@GetMapping("")
	public ResponseEntity<List<ProductWithVendorIdResponse>> findProductsByFields(
			@RequestParam(required = false) String partNumber, @RequestParam(required = false) String name,
			@RequestParam(required = false) String unit, @RequestParam(required = false) Double price,
			@RequestParam(required = false) String photopath, @RequestParam(required = false) Integer vendorId) {

		try {

			if ((partNumber == null) && (name == null) && (unit == null) && (price == null) && (photopath == null)
					&& (vendorId == null)) {

				return productService.findAllProducts();

			} else {

				Product searchTerm = new Product();

				searchTerm.setPartNumber(partNumber);
				searchTerm.setName(name);
				searchTerm.setUnit(unit);
				searchTerm.setPrice(price);
				searchTerm.setPhotopath(photopath);
				searchTerm.setVendor(productService.getProductVendor(vendorId));

				return productService.findProductsByFields(ProductSpecifications.getProductSpecs(searchTerm));

			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateProduct(@RequestBody ProductUpdate updatedProduct) {
		LOGGER.warn("in product update");
		return productService.updateProduct(updatedProduct);
	}
	
	@DeleteMapping("/dev")
	public void devProducts() {
		productService.productsDev();
	}

}