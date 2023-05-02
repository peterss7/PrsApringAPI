package com.peterss7.prs.specifications;


import org.springframework.data.jpa.domain.Specification;

import com.peterss7.prs.entities.Product;

public class ProductSpecifications {
	public static Specification<Product> partNumberLike(String partNumber){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("partNumber"), "%" + partNumber + "%");
	}
	public static Specification<Product> nameLike(String name){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("name"), "%" + name + "%");			
	}
	public static Specification<Product> priceLike(double price){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(
					root.get("price"), "%" + price + "%");
	}
	public static Specification<Product> unitLike(String unit){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("unit"), "%" + unit + "%");
	}
	public static Specification<Product> photopathLike(String photopath){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("photopath"), "%" + photopath + "%");
	}
	public static Specification<Product> vendorIdLike(int vendorId){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("vendorId"), "%" + vendorId + "%");
	}
	
}
