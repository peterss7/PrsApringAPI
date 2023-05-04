package com.peterss7.prs.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peterss7.prs.entities.Request;

import com.peterss7.prs.entities.dtos.request.RequestDefaultResponse;
import com.peterss7.prs.entities.dtos.request.RequestNew;
import com.peterss7.prs.entities.dtos.request.RequestNewResponse;
import com.peterss7.prs.entities.dtos.request.RequestRejection;
import com.peterss7.prs.entities.dtos.request.RequestReviewResponse;
import com.peterss7.prs.entities.dtos.request.RequestUpdate;
import com.peterss7.prs.services.RequestService;
import com.peterss7.prs.specifications.RequestSpecifications;

@RestController
@RequestMapping("/requests")
@CrossOrigin("http://localhost:4200")
public class RequestController {

	@Autowired
	private RequestService requestService;

	@GetMapping("")
	public ResponseEntity<List<RequestDefaultResponse>> findAll(@RequestParam(required = false) String deliveryMode,
			@RequestParam(required = false) LocalDate submittedDate,
			@RequestParam(required = false) LocalDate dateNeeded, @RequestParam(required = false) String status,
			@RequestParam(required = false) Integer userId) {

		if (deliveryMode == null && submittedDate == null && dateNeeded == null && status == null && userId == null) {

			List<RequestDefaultResponse> requests = requestService.findAllRequests();
			return ResponseEntity.ok(requests);
		} else {

			Request searchTerm = new Request();

			if (deliveryMode != null) {
				searchTerm.setDeliveryMode(deliveryMode);
			}
			if (submittedDate != null) {
				searchTerm.setSubmittedDate(submittedDate);
			}
			if (dateNeeded != null) {
				searchTerm.setDateNeeded(dateNeeded);
			}
			if (status != null) {
				searchTerm.setStatus(status);
			}
			if (userId != null) {
				searchTerm.setUser(requestService.getRequestUser(userId));
			}

			return requestService.findRequestsByFields(RequestSpecifications.getRequestSpecs(searchTerm));
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<RequestDefaultResponse> findById(@PathVariable int id) {

		return requestService.findRequestById(id);
	}

	@PostMapping("")
	public ResponseEntity<RequestNewResponse> createRequest(@RequestBody RequestNew newRequest) {

		return requestService.createRequest(newRequest);

	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateRequest(@RequestBody RequestUpdate requestUpdate) {

		return requestService.updateRequest(requestUpdate);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRequest(@PathVariable int id) {
		return requestService.deleteRequest(id);
	}

	@PutMapping("/{id}/review")
	public ResponseEntity<Void> reviewRequest(@PathVariable int id) {
		return requestService.reviewRequest(id);
	}
	
	@PutMapping("/{id}/approve")
	public ResponseEntity<Void> approveRequest(@PathVariable int id) {
		return requestService.approveRequest(id);
	}
	
	@PutMapping("/{id}/reject")
	public ResponseEntity<Void> rejectRequest(@RequestBody RequestRejection requestRejection) {
		return requestService.rejectRequest(requestRejection);
	}
	
	@GetMapping("/review/{userId}")
	public ResponseEntity<List<RequestReviewResponse>> findRequestsInReview(@PathVariable int userId){
		return requestService.findRequestsInReview(userId);
	}

}