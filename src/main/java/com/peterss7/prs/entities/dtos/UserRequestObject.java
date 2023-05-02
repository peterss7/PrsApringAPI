package com.peterss7.prs.entities.dtos;

import com.peterss7.prs.entities.User;

public class UserRequestObject {
	private int id;
	private String firstName;
	private String lastName;
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
	public UserRequestObject(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public UserRequestObject getUserRequestObject(User user) {
		return new UserRequestObject(user.getId(), user.getFirstname(), user.getLastname());
	}
}
