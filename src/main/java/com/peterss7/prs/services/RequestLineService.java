package com.peterss7.prs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peterss7.prs.controllers.UserController;
import com.peterss7.prs.entities.Product;
import com.peterss7.prs.entities.Request;
import com.peterss7.prs.entities.RequestLine;
import com.peterss7.prs.entities.dtos.product.ProductCreate;
import com.peterss7.prs.entities.dtos.product.ProductWithVendorIdResponse;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineCreate;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineCreateResponse;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineDefaultResponse;
import com.peterss7.prs.entities.dtos.requestLine.RequestLineUpdate;
import com.peterss7.prs.repositories.ProductRepository;
import com.peterss7.prs.repositories.RequestLineRepository;
import com.peterss7.prs.repositories.RequestRepository;
import com.peterss7.prs.specifications.RequestLineSpecifications;

@Service
public class RequestLineService implements IRequestLineService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestLineService.class);

	private final RequestLineRepository requestLineRepository;

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private ProductRepository productRepository;

	public RequestLineService(RequestLineRepository requestLineRepository) {
		this.requestLineRepository = requestLineRepository;
	}

	@Override
	public ResponseEntity<RequestLineDefaultResponse> findRequestLineById(Integer id) {

		try {

			Optional<RequestLine> optionalRequestLine = requestLineRepository.findById(id);

			if (optionalRequestLine.isPresent()) {

				RequestLineDefaultResponse response = new RequestLineDefaultResponse(optionalRequestLine.get());

				return new ResponseEntity<RequestLineDefaultResponse>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<RequestLineCreateResponse> createRequestLine(RequestLineCreate newRequestLine) {

		try {
			RequestLine tryRequestLine = new RequestLine();

			tryRequestLine.setRequest(requestRepository.findById(newRequestLine.getRequestId()).get());
			tryRequestLine.setProduct(productRepository.findById(newRequestLine.getProductId()).get());
			tryRequestLine.setQuantity(newRequestLine.getQuantity());

			RequestLine savedRequestLine = requestLineRepository.save(tryRequestLine);

			return new ResponseEntity<RequestLineCreateResponse>(
					RequestLineCreateResponse.getResponse(savedRequestLine), HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<String> deleteRequestLine(Integer id) {
		try {

			Optional<RequestLine> optionalRequestLine = requestLineRepository.findById(id);

			if (optionalRequestLine.isPresent()) {
				requestLineRepository.deleteById(id);

				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("REQUESTLINE DELETED");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("REQUESTLINE ID NOT FOUND");
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<RequestLineDefaultResponse>> findByRequest(Integer requestId){
		
		try {
			
			Optional<Request> optionalRequest = requestRepository.findById(requestId);
			
			if (optionalRequest.isPresent()) {				
				
				Optional<List<RequestLine>> optionalRequestLines = requestLineRepository.findByRequest(optionalRequest.get());
				
				if (optionalRequestLines.isPresent()) {
					
					List<RequestLine> requestLines = optionalRequestLines.get();
					
					return new ResponseEntity<List<RequestLineDefaultResponse>>(RequestLineDefaultResponse.getResponses(requestLines), HttpStatus.OK);
				}
				else {
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				}
				
				
			}
			else {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);	
			}
			
			
			
			// Optional<RequestLine> optionalRequestLinesOptional = requestLineRepository.findAll(RequestLineSpecification.getRequestLineSpecs())
				
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

	@Override
	public ResponseEntity<String> updateRequestLine(RequestLineUpdate updateRequestLine) {

		try {

			Optional<RequestLine> optionalRequestLine = requestLineRepository.findById(updateRequestLine.getId());

			if (optionalRequestLine.isPresent()) {

				RequestLine updatedRequestLine = optionalRequestLine.get();

				requestLineRepository.save(updatedRequestLine);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("RequestLine Updated");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("REQUESTLINE ID INVALID");
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
