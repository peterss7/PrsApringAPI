package com.peterss7.prs.entities.dtos.requestLine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.peterss7.prs.controllers.UserController;
import com.peterss7.prs.entities.RequestLine;

public class RequestLineCreateResponse {

	private Integer id;
	private Integer requestId;
	private Integer productId;
	private Integer quantity;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	public RequestLineCreateResponse(Integer id, Integer requestId, Integer productId, Integer quantity) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public RequestLineCreateResponse() {
		super();
	}
	
	public static RequestLineCreateResponse getResponse(RequestLine requestLine) {
		
		RequestLineCreateResponse response = new RequestLineCreateResponse();
		
		LOGGER.warn("Creating rlc response.");
		
		response.setId(requestLine.getId());
		response.setRequestId(requestLine.getRequest().getId());
		response.setProductId(requestLine.getProduct().getId());
		response.setQuantity(requestLine.getQuantity());
		
		return response;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


}
