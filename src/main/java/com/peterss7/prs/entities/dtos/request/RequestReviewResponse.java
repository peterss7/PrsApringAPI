package com.peterss7.prs.entities.dtos.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.peterss7.prs.entities.Request;
import com.peterss7.prs.entities.User;

public class RequestReviewResponse {

	public int id;
	public String description;
	public String justification;
	public LocalDate submittedDate;
	public LocalDate dateNeeded;
	public String deliveryMode;
	public String status;
	public Double total;
	public RequestDefaultUserComponent user;

	public RequestReviewResponse(Request request) {
		super();
		this.id = request.getId();
		this.description = request.getDescription();
		this.justification = request.getJustification();
		this.submittedDate = request.getSubmittedDate();
		this.dateNeeded = request.getDateNeeded();
		this.deliveryMode = request.getDeliveryMode();
		this.status = request.getStatus();
		this.total = request.getTotal();
		this.user = new RequestDefaultUserComponent(request.getUser());
	}



	public RequestReviewResponse() {
		super();
	}
	
	public static List<RequestReviewResponse> getReviewResponses(List<Request> requests){
		
		List<RequestReviewResponse> responses = new ArrayList<RequestReviewResponse>();
		
		for (Request request : requests) {
			responses.add(new RequestReviewResponse(request));
		}
		
		return responses;
		
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
