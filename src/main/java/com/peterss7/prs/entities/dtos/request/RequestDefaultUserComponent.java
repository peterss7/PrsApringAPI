package com.peterss7.prs.entities.dtos.request;

import com.peterss7.prs.entities.User;

public class RequestDefaultUserComponent {
	
	private int id;
	private String firstName;
	private String lastName;
	
	public RequestDefaultUserComponent(User user) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstname();
		this.lastName = user.getLastname();
	}
	
	@Override
	public String toString() {
		return "UserRequestObject [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	
}
