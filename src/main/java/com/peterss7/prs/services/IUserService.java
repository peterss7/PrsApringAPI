package com.peterss7.prs.services;


import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.peterss7.prs.entities.User;


public interface IUserService {	
	public abstract List<User> findAllUsers();
	public abstract User findUserById(int id);
	/*
	public abstract List<User> findUsersByFields(
		String username, String firstname, String lastname,
		String phone, String email, Boolean isReviewer,
		Boolean isAdmin);
		*/
	public abstract List<User> findUsersByFields(
			Specification<User> spec);
	public abstract User createUser(User newUser);
	public abstract User updateUser(User updatedUser);
	public abstract ResponseEntity<Void> deleteUserById(int id);
	public abstract ResponseEntity<Void> deleteUsersByFields(
		String username, String firstname, String lastname,
		String phone, String email, Boolean isReviewer,
		Boolean isAdmin);
	
}
