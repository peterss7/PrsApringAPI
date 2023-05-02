package com.peterss7.prs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.peterss7.prs.entities.User;
import com.peterss7.prs.services.UserService;
import com.peterss7.prs.specifications.UserSpecifications;

@RestController
@RequestMapping("/users")

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<List<User>> findUserByFields(
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String firstname,
			@RequestParam(required = false) String lastname,
			@RequestParam(required = false) String phone,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) Boolean isReviewer,
			@RequestParam(required = false) Boolean isAdmin){
		
		try {
			
			if ((username == null)  && 
				(firstname == null) &&
				(lastname == null)  &&
				(phone == null)     &&
				(email == null)     &&
				(isReviewer == null)&&
				(isAdmin == null)){
				
				List<User> users = userService.findAllUsers(); 
				
				return ResponseEntity.ok(users);
				
			} else{
				
				User searchTerm = new User();
				
				searchTerm.setFirstname(firstname);
				searchTerm.setLastname(lastname);
				searchTerm.setUsername(username);
				searchTerm.setPhone(phone);
				searchTerm.setEmail(email);
				searchTerm.setIsReviewer(isReviewer);
				searchTerm.setIsAdmin(isAdmin);
				
				List<User> users = userService.findUsersByFields(
						UserSpecifications.getUserSpecs(searchTerm));
				
				if (users == null) {
					return new ResponseEntity<List<User>>(users, HttpStatus.NOT_FOUND);
				}
				
				return ResponseEntity.ok(users);
				
			}	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}				
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(@PathVariable int id){
		try {
			User user = userService.findUserById(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("")
	public ResponseEntity<User> createUser(@RequestBody User newUser){	
		newUser.toString();
		try {
			User user = userService.createUser(newUser);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<User> updateUser(@RequestBody User updatedUser){
		return ResponseEntity.ok(userService.updateUser(updatedUser));
	}
	

	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable int id){
		return userService.deleteUserById(id);
	}
	
	/*
	@DeleteMapping("")
	public ResponseEntity<Void> deleteUserByFields(
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String firstname,
			@RequestParam(required = false) String lastname,
			@RequestParam(required = false) String phone,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) Boolean isReviewer,
			@RequestParam(required = false) Boolean isAdmin){
		
		try {
			
			if ((username != null)  || 
				(firstname != null) ||
				(lastname != null)  ||
				(phone != null)     ||
				(email != null)     ||
				(isReviewer != null)||
				(isAdmin != null)){
				
				 
				
				return userService.deleteUsersByFields(
							username, firstname, lastname, 
							phone, email, isReviewer, isAdmin);
			}
			else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
				
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}				
	}
	*/
	
}