package com.peterss7.prs.entities.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.peterss7.prs.entities.Product;
import com.peterss7.prs.entities.Vendor;


@JsonPropertyOrder({"id", "partNumber", "name", "price", "unit", 
	"photoPath","vendorId"})
public class ProductResponseDefault {
	
	private int id;
	private String partNumber;
	private String name;
	private double price;
	private String unit;
	private String photopath;
	@JsonProperty("vendorId")
	private int vendorId;
	
	public ProductResponseDefault(int id, String partNumber, String name, double price, String unit, String photopath,
			Vendor vendor) {
		super();
		this.id = id;
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photopath = photopath;
		this.vendorId = vendor.getId();
	}
	
	public ProductResponseDefault (Product product) {
		super();
		this.id = product.getId();
		this.partNumber = product.getPartNumber();
		this.name = product.getName();
		this.price = product.getPrice();
		this.unit = product.getUnit();
		this.photopath = product.getPhotoPath();
		this.vendorId = product.getVendorId();
	}

	public ProductResponseDefault() {
		super();		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(Vendor vendor) {
		this.vendorId = vendor.getId();
	}
	
	
	
	
	
}
