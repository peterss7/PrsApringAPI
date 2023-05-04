package com.peterss7.prs.entities.dtos.product;

public class ProductUpdate {

	private Integer id;
	private String partNumber;
	private String name;
	private Double price;
	private String unit;
	private String photopath;
	private Integer vendorId;

	public ProductUpdate() {
		super();
	}

	public ProductUpdate(Integer id, String partNumber, String name, Double price, String unit, String photopath,
			Integer vendorId) {
		super();
		this.id = id;
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photopath = photopath;
		this.vendorId = vendorId;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public void setVendorid(Integer vendorId) {
		this.vendorId = vendorId;
	}

}
