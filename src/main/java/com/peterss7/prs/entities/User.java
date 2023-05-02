package com.peterss7.prs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class User {

	@Id	
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="Username")
	private String username;
	
	@Column(name="Password")
	private String password;
	@Column(name="Firstname")
	private String firstname;
	@Column(name="Lastname")
	private String lastname;
	@Column(name="Phone")
	private String phone;
	@Column(name="Email")
	private String email;
	
	@JsonProperty("isReviewer")
	@Column(name="IsReviewer")
	private Boolean isReviewer;

	@JsonProperty("isAdmin")
	@Column(name="IsAdmin")
	private Boolean isAdmin;

	
	
	
	public User(int id, String username, String password, String firstname, String lastname,
			String phone, String email, Boolean isReviewer, Boolean isAdmin) {
		this.id = id;
		this.username = username;		
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.isReviewer = isReviewer;
		this.isAdmin = isAdmin;
	}
	
	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsReviewer() {
		return isReviewer;
	}

	public void setIsReviewer(boolean isReviewer) {
		this.isReviewer = isReviewer;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstname + ", lastName=" + lastname + ", phone=" + phone + ", email=" + email
				+ ", isReviewer=" + isReviewer + ", isAdmin=" + isAdmin + "]";
	}
}