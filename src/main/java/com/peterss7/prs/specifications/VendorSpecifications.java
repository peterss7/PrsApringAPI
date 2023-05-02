package com.peterss7.prs.specifications;

import org.springframework.data.jpa.domain.Specification;

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

}
