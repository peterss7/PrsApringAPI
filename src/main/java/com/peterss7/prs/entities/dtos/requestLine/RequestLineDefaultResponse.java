package com.peterss7.prs.entities.dtos.requestLine;

import java.util.ArrayList;
import java.util.List;

import com.peterss7.prs.entities.Product;
import com.peterss7.prs.entities.RequestLine;

public class RequestLineDefaultResponse {
	
	private Integer id;
	private Integer requestId;
	private Integer productId;
	private Integer quantity;
	private RequestLineProductComponent product;
	
	public RequestLineDefaultResponse(RequestLine requestLine) {
		super();	
		this.id = requestLine.getId();
		this.requestId = requestLine.getRequest().getId();
		this.productId = requestLine.getProduct().getId();
		this.quantity = requestLine.getQuantity();
		this.product = new RequestLineProductComponent(requestLine.getProduct());
	}
	
	public static List<RequestLineDefaultResponse> getResponses(List<RequestLine> requestLines){
	
		List<RequestLineDefaultResponse> responses = new ArrayList<RequestLineDefaultResponse>();
		
		for (RequestLine requestLine : requestLines) {
			responses.add(new RequestLineDefaultResponse(requestLine));
		}
		
		return responses;
		
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

	public RequestLineProductComponent getProduct() {
		return product;
	}

	public void setProduct(RequestLineProductComponent product) {
		this.product = product;
	}
	
	
	
	
	
	
	
	
}
