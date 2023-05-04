package com.peterss7.prs.entities.dtos.request;

public class RequestRejection {

	public Integer id;
	public String rejectionReason;

	public RequestRejection(Integer id, String rejectionReason) {
		super();
		this.id = id;
		this.rejectionReason = rejectionReason;
	}

	public RequestRejection() {
		super();		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

}
