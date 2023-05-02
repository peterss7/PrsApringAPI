package com.peterss7.prs.entities.dtos;

import java.sql.Date;

import com.peterss7.prs.entities.Request;

public class NewRequest {
	public String description;
	public String justification;
	public String rejectionReason;
	public String dateNeeded;
	public String deliveryMode;
	
	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	
	public int userId;
	
	public NewRequest(String description, String justification, String rejectionReason, String dateNeeded,
			int userId) {
		super();
		this.description = description;
		this.justification = justification;
		this.rejectionReason = rejectionReason;
		this.dateNeeded = dateNeeded;
		this.userId = userId;
	}
	
	public NewRequest getNewRequestObject(Request newRequest) {
		return new NewRequest(newRequest.getDescription(), newRequest.getJustification(),
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
	public String getDateNeeded() {
		return dateNeeded;
	}
	public void setDateNeeded(String dateNeeded) {
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
