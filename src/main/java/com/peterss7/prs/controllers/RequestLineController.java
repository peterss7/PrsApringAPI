package com.peterss7.prs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

import com.peterss7.prs.entities.RequestLine;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineCreate;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineCreateResponse;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineDefaultResponse;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineUpdate;
import com.peterss7.prs.services.RequestLineService;

@RestController
@RequestMapping("/requestlines")
@CrossOrigin("http://localhost:4200")
public class RequestLineController {

	@Autowired
	private RequestLineService requestLineService;

	/*
	 * @GetMapping("") public List<RequestLine> findAll(){
	 * 
	 * List<RequestLine> requestLines = requestLineRepository.findAll();
	 * 
	 * return requestLines; }
	 */

	@GetMapping("/{id}")
	public ResponseEntity<RequestLineDefaultResponse> findById(@PathVariable Integer id) {

		return requestLineService.findRequestLineById(id);

	}
	@GetMapping("/findByRequest/{id}")
	public ResponseEntity<List<RequestLineDefaultResponse>> findByRequestId(@PathVariable Integer id) {
		return requestLineService.findByRequest(id);
		
	}
	
	@PostMapping("")
	public ResponseEntity<RequestLineCreateResponse> createRequestLine(@RequestBody RequestLineCreate newRequestLine){
		return requestLineService.createRequestLine(newRequestLine);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateRequestLine(@RequestBody RequestLineUpdate updateRequestLine){
		return requestLineService.updateRequestLine(updateRequestLine);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRequestLine(@PathVariable Integer id){
		return requestLineService.deleteRequestLine(id);
	}
	
	
}