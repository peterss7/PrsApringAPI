package com.peterss7.prs.entities.dtos.request;

import java.sql.Date;
import java.time.LocalDate;

import com.peterss7.prs.entities.Request;

public class RequestNew {
	public String description;
	public String justification;
	public String rejectionReason;
	public LocalDate dateNeeded;
	public String deliveryMode;
	
	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	
	public int userId;
	
	public RequestNew(String description, String justification, String rejectionReason, LocalDate dateNeeded,
			int userId) {
		super();
		this.description = description;
		this.justification = justification;
		this.rejectionReason = rejectionReason;
		this.dateNeeded = dateNeeded;
		this.userId = userId;
	}
	
	public RequestNew getNewRequestObject(Request newRequest) {
		return new RequestNew(newRequest.getDescription(), newRequest.getJustification(),
				newRequest.getRejectionReason(), newRequest.getDateNeeded(), newRequest.getUser().getId());
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "NewRequestObject [description=" + description + ", justification=" + justification
				+ ", rejectionReason=" + rejectionReason + ", dateNeeded=" + dateNeeded + ", userId=" + userId + "]";
	}
}
