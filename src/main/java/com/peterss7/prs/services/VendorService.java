package com.peterss7.prs.services;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	public List<Vendor> findVendorsByFields(
			String code, String name, String address,
			String city, String state, String zip, 
			String phone, String email){
		
		Specification<Vendor> spec = createSpecs(code, name, address, 
				city, state, zip, phone, email);
		
		List<Vendor> vendors = vendorRepository.findAll(spec);
		
		return vendors;
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
	public ResponseEntity<Void> deleteVendorsByFields(String code, String name, String address,
		String city, String state, String zip, String phone, String email){
		
		Specification<Vendor> spec = createSpecs(code, name, address, 
				city, state, zip, phone, email);
		
		List<Vendor> deleteVendors = vendorRepository.findAll(spec);
		
		boolean isNotNull = false;
		
		for (Vendor vendor : deleteVendors) {
			if (vendor != null) {
				vendorRepository.delete(vendor);
				isNotNull = true;
			}
		}
		
		if (isNotNull) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);	
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	public Specification<Vendor> createSpecs(String code, String name, String address,
			String city, String state, String zip, String phone,
			String email){

		Specification<Vendor> spec = Specification.where(null);
		
		if (code != null) {			
			spec = spec.and(VendorSpecifications.codeLike(code));
		}
		if (name != null) {
			System.out.println("first name is: " + name);
			spec = spec.and(VendorSpecifications.nameLike(name));
		}
		if (address != null) {
			spec = spec.and(VendorSpecifications.addressLike(address));
		}
		if (city != null) {
			spec = spec.and(VendorSpecifications.cityLike(city));
		}
		if (state != null) {
			spec = spec.and(VendorSpecifications.stateLike(state));
		}
		if (zip != null) {
			spec = spec.and(VendorSpecifications.zipLike(zip));
		}
		if (phone != null) {
			spec = spec.and(VendorSpecifications.phoneLike(phone));
		}
		if (email != null) {
			spec = spec.and(VendorSpecifications.emailLike(email));
		}
		
		return spec;
		
				
	}
}
