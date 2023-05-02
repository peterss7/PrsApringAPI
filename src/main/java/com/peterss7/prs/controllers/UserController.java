package com.peterss7.prs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.peterss7.prs.entities.dtos.user.UserAuthenticated;
import com.peterss7.prs.entities.dtos.user.UserSecureView;
import com.peterss7.prs.entities.dtos.user.UserCredentials;
import com.peterss7.prs.services.UserService;
import com.peterss7.prs.specifications.UserSpecifications;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<List<UserSecureView>> findUserByFields(
			
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String firstname,
			@RequestParam(required = false) String lastname,
			@RequestParam(required = false) String phone,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) Boolean isReviewer,
			@RequestParam(required = false) Boolean isAdmin){
		
		List<UserSecureView> usersSecure = new ArrayList<>();
		
		try {
			
			if ((username == null)  && 
				(firstname == null) &&
				(lastname == null)  &&
				(phone == null)     &&
				(email == null)     &&
				(isReviewer == null)&&
				(isAdmin == null)){
				
				List<User> users = userService.findAllUsers();
				
				for (User user : users) {
					usersSecure.add(new UserSecureView(user));
				}
				
				return ResponseEntity.ok(usersSecure);
				
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
					return new ResponseEntity<List<UserSecureView>>(usersSecure, HttpStatus.NOT_FOUND);
				}
				
				for (User user : users) {
					usersSecure.add(new UserSecureView(user));
				}
				
				return ResponseEntity.ok(usersSecure);
				
			}	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}				
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserSecureView> findUserById(@PathVariable int id){
		try {
			
			/*
			
			User user = userService.findUserById(id);
			
			UserSecureView userSecure = new UserSecureView(user);
			
			return new ResponseEntity<UserSecureView>(userSecure, HttpStatus.OK);
			*/
			
			return userService.findUserById(id);
			
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("")
	public ResponseEntity<UserSecureView> createUser(@RequestBody User newUser){	
		newUser.toString();
		try {			
			return userService.createUser(newUser);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ResponseBody
	@PostMapping("/login")
	public ResponseEntity<UserAuthenticated> userLogin(@RequestBody UserCredentials credentials){
		
	    try {
	    	
	        UserAuthenticated authenticatedUser = userService.authenticate(credentials);
	        
	        if (authenticatedUser != null) {
	            return new ResponseEntity<UserAuthenticated>(authenticatedUser, HttpStatus.OK);    
	        } else {
	            return new ResponseEntity<UserAuthenticated>(HttpStatus.NOT_FOUND);
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<UserAuthenticated>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<String> updateUser(@RequestBody User updatedUser){
		
        //LOGGER.warn("UserController updateUser put called.");        
		
		return userService.updateUser(updatedUser);
	}
		
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable int id){
		return userService.deleteUserById(id);
	}
}