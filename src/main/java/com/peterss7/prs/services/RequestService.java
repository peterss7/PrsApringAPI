package com.peterss7.prs.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peterss7.prs.entities.Request;
import com.peterss7.prs.entities.dtos.NewRequestObject;
import com.peterss7.prs.repositories.RequestRepository;
import com.peterss7.prs.specifications.RequestSpecifications;

@Service
public class RequestService implements IRequestService{

	private final RequestRepository requestRepository;
	
	public RequestService(RequestRepository requestRepository) {
		this.requestRepository = requestRepository;
	}
	
	@Override
	public List<Request> findAllRequests(){
		return requestRepository.findAll();
	}
	
	@Override 
	public Request findRequestById(int id) {
		Request request = new Request();
		
		Optional<Request> optionalRequest = requestRepository.findById(id);
		
		if (optionalRequest.isPresent()) {
			request = optionalRequest.get();
		}
		
		return request;
	}
	
	@Override
	public List<Request> findRequestsByFields(Specification<Request> spec){
		
		List<Request> requests = new ArrayList<Request>();
		
		Optional<List<Request>> optionalRequests = requestRepository.findAll(spec);
		
		if (optionalRequests.isPresent()) {
			requests = optionalRequests.get();
		}
		
		return requests;
	}
	
	@Override
	public Request createRequest(NewRequestObject newRequestObject) {
		
		Request newRequest = new Request();
		DateTimeFormatter requestDateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		newRequest.setDescription(newRequestObject.getDescription());
		newRequest.setJustification(newRequestObject.getJustification());
		newRequest.setDeliveryMode(newRequestObject.getDeliveryMode());
		newRequest.setRejectionReason(null);
		newRequest.setDateNeeded(newRequestObject.getDateNeeded());
		newRequest.setSubmittedDate(LocalDate.now().format(requestDateFormatter));
		
		
		Request savedRequest = requestRepository.save(newRequest);		
		return savedRequest;
	}
	
	@Override
	public ResponseEntity<Void> updateRequest(Request updatedRequest) {
		
		Request request = new Request();
		
		Optional<Request> optionalRequest = requestRepository.findById(updatedRequest.getId());
		
		if (optionalRequest.isPresent()) {
			request = requestRepository.save(updatedRequest);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);				
	}
	
	@Override
	public ResponseEntity<Void> deleteRequestById(int id) {		
		
		Optional<Request> optionalRequest = requestRepository.findById(id);
		
		if (!optionalRequest.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@Override
	public ResponseEntity<Void> deleteRequestsByFields(String partNumber, String name, Double price,
		String unit, String photopath, Integer vendorId){
		
		Specification<Request> spec = Specification.where(null);
		
		/*
		if (partNumber != null) {
			System.out.println("partNumber is: " + partNumber);
			spec = spec.and(RequestSpecifications.partNumberLike(partNumber));
		}
		if (name != null) {
			System.out.println("first name is: " + name);
			spec = spec.and(RequestSpecifications.nameLike(name));
		}
		if (price != null) {
			spec = spec.and(RequestSpecifications.priceLike(price));
		}
		if (unit != null) {
			spec = spec.and(RequestSpecifications.unitLike(unit));
		}
		if (photopath != null) {
			spec = spec.and(RequestSpecifications.photopathLike(photopath));
		}
		if (vendorId != null) {
			spec = spec.and(RequestSpecifications.vendorIdLike(vendorId));
		}
		if (vendorId != null) {
			spec = spec.and(RequestSpecifications.vendorIdLike(vendorId));
		}

		List<Request> deleteRequests = new ArrayList<Request>();
		Optional<List<Request>> optionalRequests = requestRepository.findAll(spec);
		
		
		if (optionalRequests.isPresent()) {
			deleteRequests = optionalRequests.get();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		*/
		return null;
	}

	@Override
	public List<Request> getReviews(int userId) {
		// TODO Auto-generated method stub
		return null;
	}


}
