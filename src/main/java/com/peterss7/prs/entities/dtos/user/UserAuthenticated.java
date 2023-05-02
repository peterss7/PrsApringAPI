package com.peterss7.prs.entities.dtos.user;

public class UserAuthenticated {
	public int id;
	public String firstname;
	public String lastname;
	public Boolean isReviewer;
	public Boolean isAdmin;
	
	public UserAuthenticated(int id, String firstname, String lastname, Boolean isReviewer, Boolean isAdmin) {
		super();
		
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.isReviewer = isReviewer;
		this.isAdmin = isAdmin;
	}
	
	public UserAuthenticated() {

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

