package com.peterss7.prs.entities.dtos.product;

import com.peterss7.prs.entities.Vendor;

public class ProductVendorDefault {

	private Integer id;
	private String name;

	public ProductVendorDefault(Vendor vendor) {
		super();
		this.id = vendor.getId();
		this.name = vendor.getName();
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

}
