package com.peterss7.prs.services;


import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public ResponseEntity<List<Vendor>> findVendorsByFields(Specification<Vendor> spec){
		
		Optional<List<Vendor>> optionalVendors = vendorRepository.findAll(spec);
		
		if (optionalVendors.isPresent()) {
			return new ResponseEntity<List<Vendor>>(optionalVendors.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@Override
	public Vendor createVendor(Vendor newVendor) {		
		Vendor savedVendor = vendorRepository.save(newVendor);		
		return savedVendor;
	}
	
	@Override
	public ResponseEntity<String> updateVendor(Vendor updatedVendor) {
		
		String validity = validateVendorValues(updatedVendor);
		
		if (!validity.equals("OK")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validity);
		}
		
		Optional<Vendor> optionalVendor = vendorRepository.findById(updatedVendor.getId());
		
		if (optionalVendor.isPresent()) {
			vendorRepository.save(updatedVendor);
			return ResponseEntity.status(HttpStatus.OK).body("VENDOR UPDATED");
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VENDOR NOT FOUND");
		}
		
						
	}
	
	@Override
	public ResponseEntity<String> deleteVendorById(int id) {		
		
		Optional<Vendor> optionalVendor = vendorRepository.findById(id);
		
		if (!optionalVendor.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INVALID VENDOR ID");
		}
		else {
			vendorRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("USER DELETED");	
		}	
	}	
	
	@Override
	public String validateVendorValues(Vendor vendor) {
		
		if (vendor.getCode() != null) {			
			if (vendor.getCode().length() > 30) {
				return "INVALID: CODE TOO LONG";
			}			
		}
		if (vendor.getName() != null) {
			if (vendor.getName().length() > 30) {
				return "INVALID: NAME TOO LONG";
			}
			
			Pattern namePattern = Pattern.compile("^[A-Za-z ]+");
			Matcher nameMatcher = namePattern.matcher(vendor.getName());
			
			if (!nameMatcher.matches()) {
				return "INVALID: INCORRECT NAME FORMAT";
			}
		}
		if (vendor.getAddress() != null) {
			if (vendor.getAddress().length() > 100) {
				return "INVALID: ADDRESS TOO LONG";
			}
			
			Pattern addressPattern = Pattern.compile("^\\d{0,5}([ ]*[a-zA-Z]+)*$");
			Matcher addressMatcher = addressPattern.matcher(vendor.getAddress());
			
			if (!addressMatcher.matches()) {
				return "INVALID: INCORRECT ADDRESS FORMAT";
			}
			
		}
		if (vendor.getCity() != null) {
			if (vendor.getCity().length() > 30) {
				return "INVALID: CITY TOO LONG";
			}
			
			Pattern cityPattern = Pattern.compile("^[A-Za-z ]+$");
			Matcher cityMatcher = cityPattern.matcher(vendor.getCity());
			
			if (!cityMatcher.matches()) {
				return "INVALID: CITY CAN ONLY CONTAIN LETTERS";
			}
			
		}
		if (vendor.getState() != null) {
			Pattern statePattern = Pattern.compile("[A-Z]{2}$");
			Matcher stateMatcher = statePattern.matcher(vendor.getState());
			
			if (!stateMatcher.matches()) {
				return "INVALID: INVALID STATE ABV FORMAT";
			}
		}
		if (vendor.getZip() != null) {
			
			Pattern zipPattern = Pattern.compile("^[1-9]{1,5}$");
			Matcher zipMatcher = zipPattern.matcher(vendor.getZip());
			
			if (!zipMatcher.matches()) {
				return "INVALID: INCORRECT ZIPCODE FORMAT";
			}
		}
		if (vendor.getPhone() != null) {
			Pattern phonePattern = Pattern.compile("[1-9]{3}-[0-9]{3}-[0-9]{4}$");
			Matcher phoneMatcher = phonePattern.matcher(vendor.getPhone());
			
			if (!phoneMatcher.matches()) {
				return "INVALID: INCORRECT PHONE NUMBER FORMAT";
			}
		}
		if (vendor.getEmail() != null) {
			
			Pattern emailPattern = Pattern.compile("^[A-Za-z\\d]+\\.?[A-Za-z\\d]+@[A-Za-z]+\\.[A-Za-z]+$");
			Matcher emailMatcher = emailPattern.matcher(vendor.getEmail());
			
			if (!emailMatcher.matches()) {
				return "INVALID: INCORRECT EMAIL FORMAT";
			}
		}
		
		return "OK";
		
	}
	

}
