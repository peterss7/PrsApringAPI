package com.peterss7.prs.services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peterss7.prs.controllers.UserController;
import com.peterss7.prs.entities.User;
import com.peterss7.prs.entities.Vendor;
import com.peterss7.prs.repositories.VendorRepository;
import com.peterss7.prs.specifications.VendorSpecifications;

@Service
public class VendorService implements IVendorService {

	private final VendorRepository vendorRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(VendorService.class);

	public VendorService(VendorRepository vendorRepository) {
		this.vendorRepository = vendorRepository;
	}

	@Override
	public List<Vendor> findAllVendors() {
		return vendorRepository.findAll();
	}

	@Override
	public ResponseEntity<Vendor> findVendorById(int id) {

		try {
			Optional<Vendor> optionalVendor = vendorRepository.findById(id);

			if (optionalVendor.isPresent()) {
				Vendor vendor = optionalVendor.get();
				return new ResponseEntity<Vendor>(vendor, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<List<Vendor>> findVendorsByFields(Specification<Vendor> spec) {

		Optional<List<Vendor>> optionalVendors = vendorRepository.findAll(spec);

		if (optionalVendors.isPresent()) {
			return new ResponseEntity<List<Vendor>>(optionalVendors.get(), HttpStatus.OK);
		} else {
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
		
		LOGGER.warn(validity);

		try {		

			if (!validity.equals("OK")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validity);
			}

			Optional<Vendor> optionalVendor = vendorRepository.findById(updatedVendor.getId());

			if (optionalVendor.isPresent()) {
				
				Vendor targetVendor = optionalVendor.get();
				
				if (updatedVendor.getCode() != null) {
					targetVendor.setCode(updatedVendor.getCode());
				}
				if (updatedVendor.getName() != null) {
					targetVendor.setName(updatedVendor.getName());
				}
				if (updatedVendor.getAddress() != null) {
					targetVendor.setAddress(updatedVendor.getAddress());
				}
				if (updatedVendor.getCity() != null) {
					targetVendor.setCity(updatedVendor.getCity());
				}
				if (updatedVendor.getState() != null) {
					targetVendor.setState(updatedVendor.getState());
				}
				if (updatedVendor.getZip() != null) {
					targetVendor.setZip(updatedVendor.getZip());
				}
				if (updatedVendor.getPhone() != null) {
					targetVendor.setPhone(updatedVendor.getPhone());
				}
				if (updatedVendor.getEmail() != null) {
					targetVendor.setEmail(updatedVendor.getEmail());
				}				
				
				vendorRepository.save(targetVendor);
				
				return ResponseEntity.status(HttpStatus.OK).body("VENDOR UPDATED");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VENDOR NOT FOUND");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR ERROR");
		}

	}

	@Override
	public ResponseEntity<String> deleteVendorById(int id) {
		try {
			Optional<Vendor> optionalVendor = vendorRepository.findById(id);

			if (!optionalVendor.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INVALID VENDOR ID");
			} else {
				vendorRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("USER DELETED");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL SERVER ERROR");
		}
	}

	@Override
	public String validateVendorValues(Vendor vendor) {
		
		LOGGER.warn(vendor.toString());

		if (vendor.getCode() != null) {
			if (vendor.getCode().length() > 30) {
				return "INVALID: CODE TOO LONG";
			}
		}
		if (vendor.getName() != null) {
			if (vendor.getName().length() > 100) {
				return "INVALID: NAME TOO LONG";
			}

			Pattern namePattern = Pattern.compile("^[A-Za-z ']+");
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
		if (vendor.getCity() != null && !vendor.getCity().equals("")) {
			if (vendor.getCity().length() > 30) {
				return "INVALID: CITY TOO LONG";
			}

			Pattern cityPattern = Pattern.compile("^[A-Za-z ]+$");
			Matcher cityMatcher = cityPattern.matcher(vendor.getCity());

			if (!cityMatcher.matches()) {
				return "INVALID: CITY CAN ONLY CONTAIN LETTERS";
			}

		}
		if (vendor.getState() != null && !vendor.getState().equals("")) {
			
			if (vendor.getState().length() > 2) {
				return "INVALID: INVALID STATE ABV FORMAT";
			}
			
			Pattern statePattern = Pattern.compile("[A-Z]{2}$");
			Matcher stateMatcher = statePattern.matcher(vendor.getState());

			if (!stateMatcher.matches()) {
				return "INVALID: INVALID STATE ABV FORMAT";
			}
		}
		if (vendor.getZip() != null) {

			Pattern zipPattern = Pattern.compile("^[0-9]{1,5}$");
			Matcher zipMatcher = zipPattern.matcher(vendor.getZip());

			if (!zipMatcher.matches()) {
				return "INVALID: INCORRECT ZIPCODE FORMAT";
			}
		}
		if (vendor.getPhone() != null && !vendor.getCity().equals("")) {
			Pattern phonePattern = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}$");
			Matcher phoneMatcher = phonePattern.matcher(vendor.getPhone());

			if (!phoneMatcher.matches()) {
				return "INVALID: INCORRECT PHONE NUMBER FORMAT";
			}
		}
		if (vendor.getEmail() != null && !vendor.getCity().equals("")) {

			Pattern emailPattern = Pattern.compile("^[A-Za-z\\d]+\\.?[A-Za-z\\d]+@[A-Za-z0-9.]+$");
			Matcher emailMatcher = emailPattern.matcher(vendor.getEmail());

			if (!emailMatcher.matches()) {
				return "INVALID: INCORRECT EMAIL FORMAT";
			}
		}

		return "OK";

	}

}
