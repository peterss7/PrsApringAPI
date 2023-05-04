package com.peterss7.prs.entities.dtos.request;

import java.time.LocalDate;

public class RequestUpdate {
	
	public int id;
	public String description;
	public String justification;
	public String rejectionReason;
	public LocalDate submittedDate;
	public LocalDate dateNeeded;
	public String deliveryMode;
	public String status;
	public Double total;
	public Integer userId;
	
	public RequestUpdate() {
		super();		
	}
	
	public RequestUpdate(int id, String description, String justification, String rejectionReason,
			LocalDate submittedDate, LocalDate dateNeeded, String deliveryMode, String status, Double total,
			Integer userId) {
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
		this.userId = userId;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
