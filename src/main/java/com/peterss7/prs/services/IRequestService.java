package com.peterss7.prs.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.peterss7.prs.entities.Request;
import org.springframework.data.jpa.domain.Specification;
import com.peterss7.prs.entities.dtos.NewRequest;
import com.peterss7.prs.entities.dtos.UserRequest;

public interface IRequestService {
	public abstract List<Request> findAllRequests();
	public abstract ResponseEntity<Request> findRequestById(int id);
	public abstract List<Request> findRequestsByFields(Specification<Request> specs);
	public abstract Request createRequest(NewRequest newRequestObject);
	public abstract ResponseEntity<Void> updateRequest(Request updatedRequest);
	public abstract ResponseEntity<Void> deleteRequestById(int id);
	public abstract List<Request> getReviews(int userId);
	
	
}
