package com.peterss7.prs.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.peterss7.prs.entities.Request;
import com.peterss7.prs.entities.User;

import org.springframework.data.jpa.domain.Specification;

import com.peterss7.prs.entities.dtos.request.RequestNew;
import com.peterss7.prs.entities.dtos.request.RequestNewResponse;
import com.peterss7.prs.entities.dtos.request.RequestRejection;
import com.peterss7.prs.entities.dtos.request.RequestReviewResponse;
import com.peterss7.prs.entities.dtos.request.RequestUpdate;
import com.peterss7.prs.entities.dtos.request.RequestDefaultResponse;
import com.peterss7.prs.entities.dtos.request.RequestDefaultUserComponent;
import com.peterss7.prs.entities.dtos.user.UserSecureView;

public interface IRequestService {
	public abstract List<RequestDefaultResponse> findAllRequests();
	public abstract ResponseEntity<RequestDefaultResponse> findRequestById(int id);
	public abstract ResponseEntity<List<RequestDefaultResponse>> findRequestsByFields(Specification<Request> specs);
	public abstract ResponseEntity<RequestNewResponse> createRequest(RequestNew newRequestObject);
	public abstract ResponseEntity<String> updateRequest(RequestUpdate requestUpdate);
	public abstract ResponseEntity<String> deleteRequest(int id);	
	public abstract User getRequestUser(int id);
	public abstract ResponseEntity<Void> reviewRequest(int id);
	public abstract ResponseEntity<Void> approveRequest(int id);
	public abstract ResponseEntity<Void> rejectRequest(RequestRejection rejection);
	public abstract ResponseEntity<List<RequestReviewResponse>> findRequestsInReview(int userId);
	
}
