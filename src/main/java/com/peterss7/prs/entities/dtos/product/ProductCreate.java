package com.peterss7.prs.entities.dtos.product;

import com.peterss7.prs.entities.Vendor;

public class ProductCreate {

	private String partNumber;
	private String name;
	private Double price;
	private String unit;
	private String photopath;
	private Integer vendorId;
	
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
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
	public void setVendor(int vendorId) {
		this.vendorId = vendorId;
	}
	public ProductCreate(String partNumber, String name, double price, String unit, String photopath,
			Integer vendorId) {
		super();
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photopath = photopath;
		this.vendorId = vendorId;
	} 
	
}
