package com.peterss7.prs.entities.dtos.requestLine;

public class RequestLineUpdate {
	
	private Integer id;
	private Integer requestId;
	private Integer productId;
	private Integer quantity;
	
	public RequestLineUpdate(Integer id, Integer requestId, Integer productId, Integer quantity) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public RequestLineUpdate() {
		super();
		// TODO Auto-generated constructor stub
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
