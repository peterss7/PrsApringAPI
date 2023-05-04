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
	public static Specification<Product> priceLike(Double price){
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
	public static Specification<Product> vendorIdLike(Integer vendorId){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("vendorId"), "%" + vendorId + "%");
	}
	
	public static Specification<Product> getProductSpecs(Product product){		
		
		Specification<Product> spec = Specification.where(null);
		
		if (product.getPartNumber() != null) {			
			spec = spec.and(ProductSpecifications.partNumberLike(product.getPartNumber()));
		}
		if (product.getName() != null) {			
			spec = spec.and(ProductSpecifications.nameLike(product.getName()));
		}
		if (product.getUnit() != null) {
			spec = spec.and(ProductSpecifications.unitLike(product.getUnit()));
		}
		if (product.getPrice() != null) {
			spec = spec.and(ProductSpecifications.priceLike(product.getPrice()));
		}
		if (product.getPhotopath() != null) {
			spec = spec.and(ProductSpecifications.photopathLike(product.getPhotopath()));
		}
		if (product.getVendor() != null) {
			spec = spec.and(ProductSpecifications.vendorIdLike(product.getVendor().getId()));
		}
		
		
		
		return spec;
	}
	
}
