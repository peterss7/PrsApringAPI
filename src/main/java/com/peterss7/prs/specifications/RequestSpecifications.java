package com.peterss7.prs.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.peterss7.prs.entities.Request;
import com.peterss7.prs.entities.Request;

public class RequestSpecifications {
	public static Specification<Request> deliveryModeLike(String deliveryMode){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("deliveryMode"), "%" + deliveryMode + "%");
	}
	public static Specification<Request> submittedDateLike(LocalDate submittedDate){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("submittedDate"), "%" + submittedDate + "%");			
	}
	public static Specification<Request> dateNeededLike(LocalDate dateNeeded){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(
					root.get("dateNeeded"), "%" + dateNeeded + "%");
	}
	public static Specification<Request> statusLike(String status){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("status"), "%" + status + "%");
	}
	public static Specification<Request> userIdLike(Integer userId){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("userId"), "%" + userId + "%");
	}
	public static Specification<Request> totalLike(Double total){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("total"), "%" + total + "%");
	}
	
	public static Specification<Request> getRequestSpecs(Request request){		
		
		Specification<Request> spec = Specification.where(null);
		
		if (request.getSubmittedDate() != null) {			
			spec = spec.and(RequestSpecifications.submittedDateLike(request.getSubmittedDate()));
		}
		if (request.getDateNeeded() != null) {			
			spec = spec.and(RequestSpecifications.dateNeededLike(request.getDateNeeded()));
		}
		if (request.getStatus() != null) {
			spec = spec.and(RequestSpecifications.statusLike(request.getStatus()));
		}
		if (request.getDeliveryMode() != null) {
			spec = spec.and(RequestSpecifications.deliveryModeLike(request.getDeliveryMode()));
		}
		if (request.getTotal() != null) {
			spec = spec.and(RequestSpecifications.totalLike(request.getTotal()));
		}
		if (request.getUser() != null) {
			spec = spec.and(RequestSpecifications.userIdLike(request.getUser().getId()));
		}
		
		
		
		return spec;
	}
}
