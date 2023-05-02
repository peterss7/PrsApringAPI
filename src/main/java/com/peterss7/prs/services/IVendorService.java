package com.peterss7.prs.services;



import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.peterss7.prs.entities.User;
import com.peterss7.prs.entities.Vendor;

public interface IVendorService {

	public abstract List<Vendor> findAllVendors();
	public abstract Vendor findVendorById(int id);
	public abstract List<Vendor> findVendorsByFields(Specification<Vendor> spec);
	public abstract Vendor createVendor(Vendor newVendor);
	public abstract Vendor updateVendor(Vendor updatedVendor);
	public abstract ResponseEntity<Void> deleteVendorById(int id);
	public abstract String validateVendorValues(Vendor vendor);
	
}
