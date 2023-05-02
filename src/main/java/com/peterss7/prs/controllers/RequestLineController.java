package com.peterss7.prs.controllers;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peterss7.prs.entities.RequestLine;
import com.peterss7.prs.repositories.RequestLineRepository;

@RestController
@RequestMapping("/requestlines")
@CrossOrigin("http://localhost:8080")
public class RequestLineController {
	
	@Autowired
	private RequestLineRepository requestLineRepository;
	
	@GetMapping("")
	public List<RequestLine> findAll(){
		
		List<RequestLine> requestLines = requestLineRepository.findAll();
		
		return requestLines;		
	}
	
	@GetMapping("/{id}")
	public RequestLine findById(@PathVariable int id) {
		
		RequestLine requestLine = new RequestLine();
		Optional<RequestLine> optionalRequestLine = requestLineRepository.findById(id);
		
		if (optionalRequestLine.isPresent()) {
			requestLine = optionalRequestLine.get();
		}
		
		return requestLine;
		
	}
}