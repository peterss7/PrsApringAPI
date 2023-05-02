package com.peterss7.prs.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.peterss7.prs.entities.Request;
import org.springframework.data.jpa.domain.Specification;
import com.peterss7.prs.entities.dtos.NewRequestObject;
import com.peterss7.prs.entities.dtos.UserRequestObject;

public interface IRequestService {
	public abstract List<Request> findAllRequests();
	public abstract Request findRequestById(int id);
	/*
	public List<Request> findRequestsByFields(int id,
		String description, String justification, String rejectionReason,
		String deliveryMode, String submittedDate, String dateNeeded,
		String status, double total, UserRequestObject userRequestObject);
	*/
	public abstract List<Request> findRequestsByFields(Specification<Request> specs);
	public abstract Request createRequest(NewRequestObject newRequestObject);
	public abstract ResponseEntity<Void> updateRequest(Request updatedRequest);
	public abstract ResponseEntity<Void> deleteRequestById(int id);
	public abstract ResponseEntity<Void> deleteRequestsByFields(
			String partNumber, String name, Double price,
			String unit, String photoPath, Integer vendorId);
	// return requests in review, excluding where rejection reason
	// is not null, and do not include requests belonging to the 
	// requesting user.
	public abstract List<Request> getReviews(int userId);
	
	
}
