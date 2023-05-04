package com.peterss7.prs.entities.dtos.request;

import java.time.LocalDate;

import com.peterss7.prs.entities.Request;

public class RequestNewResponse {
	
	public int id;
	public String description;
	public String justification;
	public String rejectionReason;	
	public LocalDate dateNeeded;
	public LocalDate submittedDate;
	public String deliveryMode;
	public String status;
	public Double total;
	public Integer userId;

	public RequestNewResponse() {
		super();		
	}

	
	public static RequestNewResponse getResponse(Request request) {
		
		RequestNewResponse response = new RequestNewResponse();
		
		response.setId(request.getId());
		response.setDescription(request.getDescription());
		response.setJustification(request.getJustification());
		response.setRejectionReason(request.getRejectionReason());
		response.setSubmittedDate(request.getSubmittedDate());
		response.setDateNeeded(request.getDateNeeded());
		response.setDeliveryMode(request.getDeliveryMode());
		response.setStatus(request.getStatus());
		response.setTotal(request.getTotal());
		response.setUserId(request.getUser().getId());
		
		return response;
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getJustification() {
		return justification;
	}


	public void setJustification(String justification) {
		this.justification = justification;
	}


	public String getRejectionReason() {
		return rejectionReason;
	}


	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	public LocalDate getDateNeeded() {
		return dateNeeded;
	}


	public void setDateNeeded(LocalDate dateNeeded) {
		this.dateNeeded = dateNeeded;
	}


	public String getDeliveryMode() {
		return deliveryMode;
	}
	
	public LocalDate getSubmittedDate() {
		return submittedDate;
	}


	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
	}



	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
