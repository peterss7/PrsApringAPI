package com.peterss7.prs.entities;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	@OneToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	
	@ManyToOne
	@JoinColumn(name = "requestId")
	private Request request;
	

	@Column(name="Quantity")
	private int quantity;

	public RequestLine() {		
	}
	
	public RequestLine(int id, Request request, Product product, int quantity) {		
		this.id = id;
		this.request = request;
		this.product = product;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "RequestLine [ID=" + id + ", request=" + request + ", product=" + product + ", quantity=" + quantity
				+ "]";
	}


	
	
}
