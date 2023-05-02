package com.peterss7.prs.services;


import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.peterss7.prs.entities.User;
import com.peterss7.prs.entities.dtos.user.UserAuthenticated;
import com.peterss7.prs.entities.dtos.user.UserSecureView;
import com.peterss7.prs.entities.dtos.user.UserCredentials;


public interface IUserService {	
	public abstract List<User> findAllUsers();
	public abstract ResponseEntity<UserSecureView> findUserById(int id);
	/*
	public abstract List<User> findUsersByFields(
		String username, String firstname, String lastname,
		String phone, String email, Boolean isReviewer,
		Boolean isAdmin);
		*/
	public abstract List<User> findUsersByFields(
			Specification<User> spec);
	public abstract ResponseEntity<UserSecureView> createUser(User newUser);
	public abstract ResponseEntity<String> updateUser(User updatedUser);
	public abstract ResponseEntity<String> deleteUserById(int id);
	
    public abstract UserAuthenticated authenticate(UserCredentials credentials);
    
    public abstract String validateUserValues(User user);
	
}
