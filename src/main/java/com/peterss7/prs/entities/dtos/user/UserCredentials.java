package com.peterss7.prs.entities.dtos.user;

public class UserCredentials {
	public String username;
	public String password;

	public UserCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	@Override
	public String toString() {
		return "UserCredentials [username=" + username + ", password=" + password + "]";
	}

}
