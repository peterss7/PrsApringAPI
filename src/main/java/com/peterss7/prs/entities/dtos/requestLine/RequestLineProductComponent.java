package com.peterss7.prs.entities.dtos.requestLine;

import com.peterss7.prs.entities.Product;
import com.peterss7.prs.entities.dtos.product.ProductVendorDefault;

public class RequestLineProductComponent {

	private Integer id;
	private String name;
	private String partNumber;
	private String photopath;
	private double price;
	private String unit;
	private Integer vendorId;
	private ProductVendorDefault vendor;

	public RequestLineProductComponent(Product product) {
		super();
		this.id = product.getId();
		this.name = product.getName();
		this.partNumber = product.getPartNumber();
		this.photopath = product.getPhotopath();
		this.price = product.getPrice();
		this.unit = product.getUnit();
		this.vendorId = product.getVendor().getId();
		this.vendor = new ProductVendorDefault(product.getVendor());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
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

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public ProductVendorDefault getVendor() {
		return vendor;
	}

	public void setVendor(ProductVendorDefault vendor) {
		this.vendor = vendor;
	}

}
