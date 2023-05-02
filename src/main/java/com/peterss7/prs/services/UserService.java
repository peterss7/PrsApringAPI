package com.peterss7.prs.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peterss7.prs.entities.User;
import com.peterss7.prs.repositories.UserRepository;
import com.peterss7.prs.specifications.UserSpecifications;

@Service
public class UserService implements IUserService{

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	@Override 
	public User findUserById(int id) {
		User user = new User();
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		
		return user;
	}
	
	@Override
	public List<User> findUsersByFields(Specification<User> spec){
		
		List<User> users = new ArrayList<User>();
		
		Optional<List<User>> optionalUser = userRepository.findAll(spec);
		
		if (optionalUser.isPresent()) {
			users = optionalUser.get();
		}
		
		return users;
	}
	/*
	@Override
	public List<User> findUsersByFields(String username, String firstname, String lastname,
		String phone, String email, Boolean isReviewer, Boolean isAdmin){
		
		Specification<User> spec = Specification.where(null);
		
		if (username != null) {
			System.out.println("username is: " + username);
			spec = spec.and(UserSpecifications.usernameLike(username));
		}
		if (firstname != null) {
			System.out.println("first name is: " + firstname);
			spec = spec.and(UserSpecifications.firstnameLike(firstname));
		}
		if (lastname != null) {
			spec = spec.and(UserSpecifications.lastnameLike(lastname));
		}
		if (phone != null) {
			spec = spec.and(UserSpecifications.phoneLike(phone));
		}
		if (email != null) {
			spec = spec.and(UserSpecifications.emailLike(email));
		}
		if (isReviewer != null) {
			spec = spec.and(UserSpecifications.isReviewerLike(isReviewer));
		}
		if (isReviewer != null) {
			spec = spec.and(UserSpecifications.isReviewerLike(isReviewer));
		}
		
		System.out.println(spec.hashCode());
		
		// List<User> users = userRepository.findAll(spec);
		
		List<User> users = new ArrayList<User>();
		
		Optional<List<User>> optionalUser = userRepository.findAll(spec);
		
		if (optionalUser.isPresent()) {
			users = optionalUser.get();
		}
		else {
			return null;
		}
		
		
		return users;
	}
	*/
	@Override
	public User createUser(User newUser) {	
		
		newUser.toString();
		
		User savedUser = userRepository.save(newUser);
		newUser.toString();
		return savedUser;
	}
	
	@Override
	public User updateUser(User updatedUser) {
		
		User user = new User();
		
		Optional<User> optionalUser = userRepository.findById(updatedUser.getId());
		
		if (optionalUser.isPresent()) {
			user = userRepository.save(updatedUser);
		}
		
		return user;				
	}
	
	@Override
	public ResponseEntity<Void> deleteUserById(int id) {		
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (!optionalUser.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@Override
	public ResponseEntity<Void> deleteUsersByFields(String username, String firstname, String lastname,
		String phone, String email, Boolean isReviewer, Boolean isAdmin){
		
		Specification<User> spec = Specification.where(null);
		
		if (username != null) {
			System.out.println("username is: " + username);
			spec = spec.and(UserSpecifications.usernameLike(username));
		}
		if (firstname != null) {
			System.out.println("first name is: " + firstname);
			spec = spec.and(UserSpecifications.firstnameLike(firstname));
		}
		if (lastname != null) {
			spec = spec.and(UserSpecifications.lastnameLike(lastname));
		}
		if (phone != null) {
			spec = spec.and(UserSpecifications.phoneLike(phone));
		}
		if (email != null) {
			spec = spec.and(UserSpecifications.emailLike(email));
		}
		if (isReviewer != null) {
			spec = spec.and(UserSpecifications.isReviewerLike(isReviewer));
		}
		if (isReviewer != null) {
			spec = spec.and(UserSpecifications.isReviewerLike(isReviewer));
		}

		List<User> deleteUser = new ArrayList<User>();
		Optional<List<User>> optionalUser = userRepository.findAll(spec);
		
		
		if (optionalUser.isPresent()) {
			deleteUser = optionalUser.get();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}




}
