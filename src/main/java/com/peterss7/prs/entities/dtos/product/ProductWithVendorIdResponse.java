package com.peterss7.prs.entities.dtos.product;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.peterss7.prs.entities.Product;

@JsonPropertyOrder({"id", "name", "partNumber", "price", "unit", "photopath",
"vendorId"})
public class ProductWithVendorIdResponse {
	private Integer id;
	private String name;
	private String partNumber;
	private String photopath;
	private double price;
	private String unit;
	private Integer vendorId;

	public ProductWithVendorIdResponse(Product product) {
		super();
		this.id = product.getId();
		this.name = product.getName();
		this.partNumber = product.getPartNumber();
		this.photopath = product.getPhotopath();
		this.price = product.getPrice();
		this.unit = product.getUnit();
		this.vendorId = product.getVendor().getId();
	}
	
	public static List<ProductWithVendorIdResponse> getResponses(List<Product> products)
	{
		List<ProductWithVendorIdResponse> responses = new ArrayList<ProductWithVendorIdResponse>();
		
		for (Product product : products) {
			responses.add(new ProductWithVendorIdResponse(product));
		}
		
		return responses;
	}

	public Integer getId() {
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

	public Integer getVendorId() {
		return vendorId;
	}

	public void setId(Integer id) {
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

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

}
