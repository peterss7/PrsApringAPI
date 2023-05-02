package com.peterss7.prs.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peterss7.prs.entities.User;
import com.peterss7.prs.entities.Vendor;
import com.peterss7.prs.repositories.VendorRepository;
import com.peterss7.prs.specifications.VendorSpecifications;

@Service
public class VendorService implements IVendorService{

	private final VendorRepository vendorRepository;
	
	public VendorService(VendorRepository vendorRepository) {
		this.vendorRepository = vendorRepository;
	}
	
	@Override
	public List<Vendor> findAllVendors(){
		return vendorRepository.findAll();
	}
	
	@Override 
	public Vendor findVendorById(int id) {
		Vendor vendor = new Vendor();
		
		Optional<Vendor> optionalVendor = vendorRepository.findById(id);
		
		if (optionalVendor.isPresent()) {
			vendor = optionalVendor.get();
		}
		
		return vendor;
	}
	
	@Override
	public Vendor createVendor(Vendor newVendor) {		
		Vendor savedVendor = vendorRepository.save(newVendor);		
		return savedVendor;
	}
	
	@Override
	public Vendor updateVendor(Vendor updatedVendor) {
		
		Vendor vendor = new Vendor();
		
		Optional<Vendor> optionalVendor = vendorRepository.findById(updatedVendor.getId());
		
		if (optionalVendor.isPresent()) {
			vendor = vendorRepository.save(updatedVendor);
		}
		
		return vendor;				
	}
	
	@Override
	public ResponseEntity<Void> deleteVendorById(int id) {		
		
		Optional<Vendor> optionalVendor = vendorRepository.findById(id);
		
		if (!optionalVendor.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	
	@Override
	public List<Vendor> findVendorsByFields(Specification<Vendor> spec) {
		
	List<Vendor> vendors = new ArrayList<Vendor>();
		
		Optional<List<Vendor>> optionalVendor = vendorRepository.findAll(spec);
		
		if (optionalVendor.isPresent()) {
			vendors = optionalVendor.get();
		}
		
		return vendors;
	}

	@Override
	public String validateVendorValues(Vendor vendor) {
		// TODO Auto-generated method stub
		return null;
	}
}
