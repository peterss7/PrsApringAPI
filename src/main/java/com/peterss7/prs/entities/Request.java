package com.peterss7.prs.entities;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;

import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.peterss7.prs.entities.dtos.UserRequestObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Request")
@JsonPropertyOrder({"id", "description", "justification", "rejectionReason", "deliveryMode", "submittedDate",
	"deliveryDate", "dateNeeded", "status", "total", "user"})
public class Request {

	@JsonProperty("id")	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int requestId;	
	@Column(name="Description")
	private String description;
	@Column(name="Justification")
	private String justification;
	@Column(name="RejectionReason")
	private String rejectionReason;
	@Column(name="DeliveryMode")
	private String deliveryMode;
	@Column(name="SubmittedDate")
	private String submittedDate;
	@Column(name="DateNeeded")
	private String dateNeeded;
	@Column(name="Status")
	private String status;
	
	private double total;
	
	@OneToMany(mappedBy = "request")	
	private List<RequestLine> requestLines;
	
	
	@ManyToOne
	@JoinColumn(name = "userId")	
	private User user;
	
	public int getId() {
		return requestId;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}

	public String getDescription() {
		return description;
	}

	public String getJustification() {
		return justification;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public String getSubmittedDate() {
		return submittedDate;
	}

	public String getDateNeeded() {
		return dateNeeded;
	}
	
	

	public String getStatus() {
		return status;
	}

	public UserRequestObject getUser() {
		
		return new UserRequestObject(user.getId(), user.getFirstname(), user.getLastname());
	}

	public Request() {		
	}
	public void setId(int id) {
		this.requestId = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}

	public void setDateNeeded(String dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Request(int id, String description, String justification, String rejectionReason, String deliveryMode,
			String submittedDate, String dateNeeded, String status, double total, User user) {
		super();
		this.requestId = id;
		this.description = description;
		this.justification = justification;
		this.rejectionReason = rejectionReason;
		this.deliveryMode = deliveryMode;
		this.submittedDate = submittedDate;
		this.dateNeeded = dateNeeded;
		this.status = status;
		this.total = total;
		this.user = user;
	}


	
}