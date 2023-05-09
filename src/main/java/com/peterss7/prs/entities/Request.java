package com.peterss7.prs.entities;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.peterss7.prs.entities.dtos.request.RequestDefaultUserComponent;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	@Column(name="Description")
	private String description;
	@Column(name="Justification")
	private String justification;
	@Column(name="RejectionReason")
	private String rejectionReason;
	@Column(name="DeliveryMode")
	private String deliveryMode;
	@Column(name="SubmittedDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate submittedDate;
	@Column(name="DateNeeded")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateNeeded;
	@Column(name="Status")
	private String status;
	
	@Column(name="Total")
	private Double total;
	
	@OneToMany(mappedBy = "request")	
	private List<RequestLine> requestLines;
	
	
	@ManyToOne
	@JoinColumn(name = "userId")	
	private User user;
	
	public Integer getId() {
		return id;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
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

	public LocalDate getSubmittedDate() {
		return submittedDate;
	}

	public LocalDate getDateNeeded() {
		return dateNeeded;
	}
	
	

	public String getStatus() {
		return status;
	}	
	
	public User getUser() {
		return user;
	}

	public Request() {	
		
		this.deliveryMode = "In-Store Pickup";
		this.submittedDate = LocalDate.now();
		this.status = "PENDING";
		this.total = 0.0;
		this.rejectionReason = null;
	}
	
	public void setId(Integer id) {
		this.id = id;
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

	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
	}

	public void setDateNeeded(LocalDate dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUser(User user) {
		this.user = user;
	}
	



	public Request(Integer id, String description, String justification, String rejectionReason, String deliveryMode,
			LocalDate submittedDate, LocalDate dateNeeded, String status, Double total, User user) {
		super();
		this.id = id;
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