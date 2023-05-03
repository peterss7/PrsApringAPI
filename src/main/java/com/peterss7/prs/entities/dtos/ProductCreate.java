package com.peterss7.prs.entities.dtos;

import com.peterss7.prs.entities.Vendor;

public class ProductCreate {

	private String partNumber;
	private String name;
	private double price;
	private String unit;
	private String photopath;
	private Vendor vendor;
	
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
		return vendor.getId();
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public ProductCreate(String partNumber, String name, double price, String unit, String photopath,
			Vendor vendor) {
		super();
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photopath = photopath;
		this.vendor = vendor;
	} 
	
}
