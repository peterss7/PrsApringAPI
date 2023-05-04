package com.peterss7.prs.entities.dtos.requestLine;

public class RequestLineCreate {

	private Integer requestId;
	private Integer productId;
	private Integer quantity;
	
	public RequestLineCreate(Integer requestId, Integer productId, Integer quantity) {
		super();
		this.requestId = requestId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public RequestLineCreate() {
		super();
		// TODO Auto-generated constructor stub
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
