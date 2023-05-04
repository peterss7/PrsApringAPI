package com.peterss7.prs.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.peterss7.prs.entities.RequestLine;

public class RequestLineSpecifications {

	public static Specification<RequestLine> getRequestLineSpecs(RequestLine requestLine) {

		Specification<RequestLine> spec = Specification.where(null);

		if (requestLine.getRequest().getId() != null) {
			spec = spec.and(RequestLineSpecifications.requestIdLike(requestLine.getRequest().getId()));
		}
		if (requestLine.getProduct().getId() != null) {
			spec = spec.and(RequestLineSpecifications.productIdLike(requestLine.getProduct().getId()));
		}
		if (requestLine.getQuantity() != null) {
			spec = spec.and(RequestLineSpecifications.quantityLike(requestLine.getQuantity()));
		}

		return spec;
	}

	public static Specification<RequestLine> productIdLike(Integer productId) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("productId"), "%" + productId + "%");
	}

	public static Specification<RequestLine> quantityLike(Integer quantity) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("quantity"), "%" + quantity + "%");
	}

	public static Specification<RequestLine> requestIdLike(Integer requestId) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("requestId"), "%" + requestId + "%");
	}
}