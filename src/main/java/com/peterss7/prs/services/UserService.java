package com.peterss7.prs.services;


import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peterss7.prs.entities.User;
import com.peterss7.prs.entities.dtos.user.UserAuthenticated;
import com.peterss7.prs.entities.dtos.user.UserSecureView;
import com.peterss7.prs.entities.dtos.user.UserCredentials;
import com.peterss7.prs.repositories.UserRepository;
import com.peterss7.prs.specifications.UserSpecifications;

@Service
public class UserService implements IUserService{	

	private final UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	@Override 
	public ResponseEntity<UserSecureView> findUserById(int id) {
		User user = new User();
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (optionalUser.isPresent()) {
			
			user = optionalUser.get();
			
			UserSecureView userSecure = new UserSecureView(user);
			
			return new ResponseEntity<UserSecureView>(userSecure, HttpStatus.OK);
			
		}
		
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		
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
	
	@Override
	public UserAuthenticated authenticate(UserCredentials credentials) {
		
		User authenticatingUser = new User();
		UserAuthenticated authenticatedUser = new UserAuthenticated();
		
		authenticatingUser.setUsername(credentials.getUsername());
		authenticatingUser.setPassword(credentials.getPassword());
		
		Specification<User> authenticatingSpec = UserSpecifications.getUserSpecs(authenticatingUser);
		
		Optional<List<User>> optionalUser = userRepository.findAll(authenticatingSpec);
		
		if (optionalUser.isPresent()) {
			
			User foundUser = optionalUser.get().get(0);
			
			/*
			if (foundUser.getUsername().equals(credentials.getUsername())
				&& foundUser.getPassword().equals(credentials.getPassword())) {
			*/
				
				authenticatedUser.setId(foundUser.getId());
				authenticatedUser.setFirstname(foundUser.getFirstname());
				authenticatedUser.setLastname(foundUser.getLastname());
				authenticatedUser.setIsReviewer(foundUser.getIsReviewer());
				authenticatedUser.setIsAdmin(foundUser.getIsAdmin());
				
				return authenticatedUser;
			// }
		}
		else {
			return null;
		}
		
	}
	
	
	@Override
	public ResponseEntity<UserSecureView> createUser(User newUser) {	
		
		newUser.toString();
		
		// Optional<List<User>> optionalUser = userRepository.findAll(UserSpecifications.getUserSpecs(newUser));
		
		Optional<User> optionalUser = userRepository.findByUsername(newUser.getUsername());
		
		try {
			if (optionalUser.isPresent()) {
				return new ResponseEntity<>(null,HttpStatus.CONFLICT);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		String validity = validateUserValues(newUser);
		
		if (!validity.equals("OK")) {			
			UserSecureView errorUser = new UserSecureView(validity);			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorUser);
		}
		
		UserSecureView createdUser = new UserSecureView(userRepository.save(newUser));
		
		return ResponseEntity.status(HttpStatus.OK).body(createdUser);
	}
	
	@Override
	public ResponseEntity<String> updateUser(User updatedUser) {
		
        String validity = validateUserValues(updatedUser);
        
        if (!validity.equals("OK")) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validity);
        }
        
		LOGGER.warn("Entering UserService userUpdate with id: " + updatedUser.getId() + ": " + updatedUser.getFirstname());
		
		try {
        
	        Optional<User> optionalUser = userRepository.findById(updatedUser.getId());
	        
	        if (optionalUser.isPresent()) {
	        	
	        	User targetUser = optionalUser.get();
	        	
	        	if (updatedUser.getUsername() != null) {
	    			targetUser.setUsername(updatedUser.getUsername());
	    		}    		
	    		if (updatedUser.getPassword()!= null) {
	    			targetUser.setPassword(updatedUser.getPassword());
	    		}    		
	    		if (updatedUser.getFirstname()!= null) {
	    			targetUser.setFirstname(updatedUser.getFirstname());
	    		}    		
	    		if (updatedUser.getLastname()!= null) {
	    			targetUser.setLastname(updatedUser.getLastname());
	    		}    		
	    		if (updatedUser.getPhone()!= null) {
	    			targetUser.setPhone(updatedUser.getPhone());
	    		}    		
	    		if (updatedUser.getEmail()!= null) {
	    			targetUser.setEmail(updatedUser.getEmail());
	    		}    		
	    		if (updatedUser.getIsReviewer()!= null) {
	    			targetUser.setIsReviewer(updatedUser.getIsReviewer());
	    		}    		
	    		if (updatedUser.getIsAdmin()!= null) {
	    			targetUser.setIsAdmin(updatedUser.getIsAdmin());
	    		}
	    		
	    		userRepository.save(targetUser);
	    		
	    		return ResponseEntity.status(HttpStatus.OK).body(null);
	        }				
	        else {
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}
	
	@Override
	public ResponseEntity<String> deleteUserById(int id) {		
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (!optionalUser.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USER NOT FOUND");
		}
		else {
			userRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		}
		
	}

	@Override
	public String validateUserValues(User user) {
		
		if (user.getUsername() != null) {
			
			if (user.getUsername().length() > 30) {
				return "INVALID: USERNAME TOO LONG";
			}
			
			Pattern usernamePattern1 = Pattern.compile("[A-Za-z]+[0-9]+");
			Pattern usernamePattern2 = Pattern.compile("[A-Za-z]+[0-9]+[A-Za-z]+");
			
			Matcher matcher1 = usernamePattern1.matcher(user.getUsername());
			Matcher matcher2 = usernamePattern2.matcher(user.getUsername());
			
			if (!matcher1.matches() && !matcher2.matches()) {
				return "INVALID: INCORRECT USERNAME FORMAT";
			}			
		}
		if (user.getPassword() != null) {
			if (user.getPassword().length() > 30) {
				return "INVALID: PASSWORD TOO LONG";
			}
			
			Pattern passwordPattern = Pattern.compile("(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\$%\\^&\\*\\(\\)\\+-])[A-Za-z\\d!@#\\$%\\^&\\*\\(\\)\\+-]+$");
			Matcher passwordMatcher = passwordPattern.matcher(user.getPassword());
			
			if (!passwordMatcher.matches()) {
				return "INVALID: INCORRECT PASSWORD FORMAT";
			}
		}
		if (user.getFirstname() != null) {
			if (user.getFirstname().length() > 30) {
				return "INVALID: FIRSTNAME TOO LONG";
			}
			
			Pattern firstnamePattern = Pattern.compile("^[A-Za-z]+$");
			Matcher firstnameMatcher = firstnamePattern.matcher(user.getFirstname());
			
			if (!firstnameMatcher.matches()) {
				return "INVALID: FIRSTNAME CAN ONLY CONTAIN LETTERS";
			}
			
		}
		if (user.getLastname() != null) {
			if (user.getLastname().length() > 30) {
				return "INVALID: LASTNAME TOO LONG";
			}
			
			Pattern lastnamePattern = Pattern.compile("^[A-Za-z]+$");
			Matcher lastnameMatcher = lastnamePattern.matcher(user.getLastname());
			
			if (!lastnameMatcher.matches()) {
				return "INVALID: LASTNAME CAN ONLY CONTAIN LETTERS";
			}
			
		}
		if (user.getPhone() != null) {
			Pattern phonePattern = Pattern.compile("[1-9]{3}-[0-9]{3}-[0-9]{4}$");
			Matcher phoneMatcher = phonePattern.matcher(user.getPhone());
			
			if (!phoneMatcher.matches()) {
				return "INVALID: INCORRECT PHONE NUMBER FORMAT";
			}
		}
		if (user.getEmail() != null) {
			
			Pattern emailPattern = Pattern.compile("^[A-Za-z\\d]+\\.?[A-Za-z\\d]+@[A-Za-z]+\\.[A-Za-z]+$");
			Matcher emailMatcher = emailPattern.matcher(user.getEmail());
			
			if (!emailMatcher.matches()) {
				return "INVALID: INCORRECT EMAIL FORMAT";
			}
		}
		
		return "OK";
		
	}
}
