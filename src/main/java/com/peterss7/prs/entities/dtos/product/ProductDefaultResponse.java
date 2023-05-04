package com.peterss7.prs.entities.dtos.product;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.peterss7.prs.entities.Product;
import com.peterss7.prs.services.ProductService;


public class ProductDefaultResponse {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDefaultResponse.class);

	private int id;
	private String name;
	private String partNumber;
	private String photopath;
	private double price;
	private String unit;
	private ProductVendorDefault vendor;

	public ProductDefaultResponse() {
		super();
	}

	public ProductDefaultResponse(Product product) {
		super();
		this.id = product.getId();
		this.partNumber = product.getPartNumber();
		this.name = product.getName();
		this.price = product.getPrice();
		this.unit = product.getUnit();
		this.photopath = product.getPhotopath();
		
		// LOGGER.warn("product vendor id in default response const: " + product.getVendor().getId());
		
		this.vendor = new ProductVendorDefault(product.getVendor());
	}
	
	public static List<ProductDefaultResponse> getResponses(List<Product> products){
		
		List<ProductDefaultResponse> responses = new ArrayList<ProductDefaultResponse>();
		
		for (Product product : products) {
			try {				
				responses.add(new ProductDefaultResponse(product));
			} catch(Exception e) {
				e.printStackTrace();
				LOGGER.warn("Product failure: " + product.getId());
			}
		}
		
		return responses;
	}

	public ProductVendorDefault getVendor() {
		return vendor;
	}

	public void setVendor(ProductVendorDefault vendor) {
		this.vendor = vendor;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public String getPhotopath() {
		return photopath;
	}

	public double getPrice() {
		return price;
	}

	public String getUnit() {
		return unit;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
