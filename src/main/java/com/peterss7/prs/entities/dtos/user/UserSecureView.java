package com.peterss7.prs.entities.dtos.user;

import com.peterss7.prs.entities.User;

public class UserSecureView {
	public int id;
	public String username;
	public String firstname;
	public String lastname;
	public String phone;
	public String email;
	public Boolean isReviewer;
	public Boolean isAdmin;
	public String error;
	
	
	public UserSecureView(int id, String username, String firstname, String lastname, String phone, String email,
			Boolean isReviewer, Boolean isAdmin) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.isReviewer = isReviewer;
		this.isAdmin = isAdmin;
		error = "";
	}
	
	public UserSecureView(User user) {
		
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.isReviewer = user.getIsReviewer();
		this.isAdmin = user.getIsAdmin();
	}

	public UserSecureView() {
		super();	
	}
	
	public UserSecureView(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
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


	public void setIsReviewer(Boolean isReviewer) {
		this.isReviewer = isReviewer;
	}


	public Boolean getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	} 
	
	
	
	
	
	
	
}
