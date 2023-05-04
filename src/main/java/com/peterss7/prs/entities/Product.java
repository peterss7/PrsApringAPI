package com.peterss7.prs.entities;




import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
@JsonPropertyOrder({"id", "partNumber", "name", "price", "unit", 
	"photopath","vendorId"})
public class Product {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	@Column(name="PartNumber")	
	private String partNumber;
	@Column(name = "Name")
	private String name;
	@Column(name="Price")
	private Double price;
	@Column(name="Unit")
	private String unit;
	@Column(name="PhotoPath")
	private String photopath;
	
	
	@ManyToOne
	@JoinColumn(name = "vendorId")	
	private Vendor vendor;
	

	
	
	
		
	public Integer getId() {
		return id;
	}

	public Product() {
		super();
		
	}

	public Product(String partNumber, String name, Double price, String unit, String photopath, Vendor vendor, Integer id) {
		super();
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photopath = photopath;
		this.vendor = vendor;
		this.id = id;
		
		
	}

	@Override
	public String toString() {
		return "Product [partNumber=" + partNumber + ", name=" + name + ", price=" + price + ", unit=" + unit
				+ ", photopath=" + photopath + ", vendor=" + vendor + ", id=" + id + "]";
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Vendor getVendor() {

		return vendor;
	    
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

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;	
		
	}



}
