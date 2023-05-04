package com.peterss7.prs.entities;

import com.peterss7.prs.entities.dtos.requestLine.RequestLineCreate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "RequestLine")
public class RequestLine {
	
	@Id	
	@Column(name = "Id")	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@OneToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	@Column(name="Quantity")
	private Integer quantity;
	
	
	@ManyToOne
	@JoinColumn(name = "requestId")
	private Request request;
	

	public RequestLine() {		
	}
	
	public RequestLine(Integer id, Request request, Product product, Integer quantity) {		
		this.id = id;
		this.request = request;
		this.product = product;
		this.quantity = quantity;
	}
	

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "RequestLine [ID=" + id + ", request=" + request + ", product=" + product + ", quantity=" + quantity
				+ "]";
	}


	
	
}
