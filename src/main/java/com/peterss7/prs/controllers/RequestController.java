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
	/*
	@GetMapping("")
	public List<Request> findAll(){
		
		List<Request> requests = requestService.findAll();
		
		return requests;		
	}
	
	@GetMapping("/{id}")
	public Request findById(@PathVariable int id) {
		
		Request request = new Request();
		Optional<Request> optionalRequest = requestService.findById(id);
		
		if (optionalRequest.isPresent()) {
			request = optionalRequest.get();
		}
		
		return request;
		
	}
	*/
	
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