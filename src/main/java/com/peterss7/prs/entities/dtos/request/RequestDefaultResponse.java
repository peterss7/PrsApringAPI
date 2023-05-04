package com.peterss7.prs.entities.dtos.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.peterss7.prs.entities.Request;

public class RequestDefaultResponse {
	public int id;
	public String description;
	public String justification;
	public String rejectionReason;
	public LocalDate submittedDate;
	public LocalDate dateNeeded;
	public String deliveryMode;
	public String status;
	public Double total;
	public RequestDefaultUserComponent user;
	
	public RequestDefaultResponse(int id, String description, String justification, String rejectionReason,
			LocalDate submittedDate, LocalDate dateNeeded, String deliveryMode, String status, Double total,
			RequestDefaultUserComponent user) {
		super();
		this.id = id;
		this.description = description;
		this.justification = justification;
		this.rejectionReason = rejectionReason;
		this.submittedDate = submittedDate;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = deliveryMode;
		this.status = status;
		this.total = total;
		this.user = user;
	}
	
	public static List<RequestDefaultResponse> getResponses(List<Request> requests){
		
		List<RequestDefaultResponse> responses = new ArrayList<RequestDefaultResponse>();
		
		for (Request request : requests) {
			
			RequestDefaultResponse response = new RequestDefaultResponse();
			RequestDefaultUserComponent user = new RequestDefaultUserComponent(request.getUser());
			
			response.setId(request.getId());
			response.setDescription(request.getDescription());
			response.setJustification(request.getJustification());
			response.setRejectionReason(request.getRejectionReason());
			response.setSubmittedDate(request.getSubmittedDate());
			response.setDateNeeded(request.getDateNeeded());
			response.setDeliveryMode(request.getDeliveryMode());
			response.setStatus(request.getStatus());
			response.setTotal(request.getTotal());
			response.setUser(user);
			
			responses.add(response);			
		}
		
		return responses;		
	}
	
	public static RequestDefaultResponse getResponse(Request request) {
		
		RequestDefaultResponse response = new RequestDefaultResponse();
		RequestDefaultUserComponent user = new RequestDefaultUserComponent(request.getUser());
		
		response.setId(request.getId());
		response.setDescription(request.getDescription());
		response.setJustification(request.getJustification());
		response.setRejectionReason(request.getRejectionReason());
		response.setSubmittedDate(request.getSubmittedDate());
		response.setDateNeeded(request.getDateNeeded());
		response.setDeliveryMode(request.getDeliveryMode());
		response.setStatus(request.getStatus());
		response.setTotal(request.getTotal());
		response.setUser(user);
		
		return response;
		
	}

	public RequestDefaultResponse() {
		super();		
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

	public LocalDate getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
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

	public RequestDefaultUserComponent getUser() {
		return user;
	}

	public void setUser(RequestDefaultUserComponent user) {
		this.user = user;
	}
	
	
	
	
	
}
