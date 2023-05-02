package com.peterss7.prs.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.peterss7.prs.entities.Vendor;
import com.peterss7.prs.entities.Vendor;

public class VendorSpecifications {
	public static Specification<Vendor> codeLike(String code){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("code"), "%" + code + "%");
	}
	public static Specification<Vendor> nameLike(String name){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(
					root.get("name"), "%" + name + "%");
	}
	public static Specification<Vendor> addressLike(String address){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("address"), "%" + address + "%");
	}
	public static Specification<Vendor> cityLike(String city){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("city"), "%" + city + "%");
	}
	public static Specification<Vendor> stateLike(String state){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("state"), "%" + state + "%");
	}
	public static Specification<Vendor> zipLike(String zip){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("zip"),zip);
	}
	public static Specification<Vendor> phoneLike(String phone){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("phone"),phone);
	}
	public static Specification<Vendor> emailLike(String email){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("email"),email);
	}
	
	public static Specification<Vendor> getVendorSpecs(Vendor vendor){		
		
		Specification<Vendor> spec = Specification.where(null);
		
		if (vendor.getCode() != null) {			
			spec = spec.and(VendorSpecifications.codeLike(vendor.getCode()));
		}
		if (vendor.getName() != null) {			
			spec = spec.and(VendorSpecifications.nameLike(vendor.getName()));
		}
		if (vendor.getAddress() != null) {
			spec = spec.and(VendorSpecifications.addressLike(vendor.getAddress()));
		}
		if (vendor.getCity() != null) {
			spec = spec.and(VendorSpecifications.cityLike(vendor.getCity()));
		}
		if (vendor.getState() != null) {
			spec = spec.and(VendorSpecifications.stateLike(vendor.getState()));
		}
		if (vendor.getZip()!= null) {
			spec = spec.and(VendorSpecifications.zipLike(vendor.getZip()));
		}
		if (vendor.getPhone() != null) {
			spec = spec.and(VendorSpecifications.phoneLike(vendor.getPhone()));
		}
		if (vendor.getEmail() != null) {
			spec = spec.and(VendorSpecifications.emailLike(vendor.getEmail()));
		}
		
		
		return spec;
	}

}
