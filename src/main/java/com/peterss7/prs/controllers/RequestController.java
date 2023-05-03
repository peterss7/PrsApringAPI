package com.peterss7.prs.controllers;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peterss7.prs.entities.Request;

import com.peterss7.prs.entities.dtos.NewRequest;
import com.peterss7.prs.repositories.RequestRepository;
import com.peterss7.prs.services.RequestService;

@RestController
@RequestMapping("/requests")
@CrossOrigin("http://localhost:4200")
public class RequestController {
	
	@Autowired
	private RequestService requestService;
	
	@GetMapping("")
	public ResponseEntity<List<Request>> findAll(
			@RequestParam(required = false) String deliveryMode,
			@RequestParam(required = false) String submittedDate,
			@RequestParam(required = false) String dateNeeded,
			@RequestParam(required = false) String Status,
			@RequestParam(required = false) int userId
			){
		
		List<Request> requests = requestService.findAllRequests();
		
		return requests;		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> findById(@PathVariable int id) {	
		
		return requestService.findRequestById(id);
	}
	
	
	@PostMapping("")
	public ResponseEntity<Request> createRequest(@RequestBody NewRequest newRequest){		
		try {
			Request request = requestService.createRequest(newRequest);
			return new ResponseEntity<>(request, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}