package com.peterss7.prs.specifications;



import org.springframework.data.jpa.domain.Specification;

import com.peterss7.prs.entities.User;

public class UserSpecifications {
	public static Specification<User> usernameLike(String username){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("username"), "%" + username + "%");
	}
	public static Specification<User> passwordLike(String password){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("password"), "%" + password + "%");			
	}
	public static Specification<User> firstnameLike(String firstname){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(
					root.get("firstname"), "%" + firstname + "%");
	}
	public static Specification<User> lastnameLike(String lastname){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("lastname"), "%" + lastname + "%");
	}
	public static Specification<User> phoneLike(String phone){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("phone"), "%" + phone + "%");
	}
	public static Specification<User> emailLike(String email){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.get("email"), "%" + email + "%");
	}
	public static Specification<User> isReviewerLike(Boolean isReviewer){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("isReviewer"),isReviewer);
	}
	public static Specification<User> isAdminLike(Boolean isAdmin){
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("isAdmin"),isAdmin);
	}
	
	public static Specification<User> getUserSpecs(User user){		
		
		Specification<User> spec = Specification.where(null);
		
		if (user.getUsername() != null) {			
			spec = spec.and(UserSpecifications.usernameLike(user.getUsername()));
		}
		if (user.getFirstname() != null) {			
			spec = spec.and(UserSpecifications.firstnameLike(user.getFirstname()));
		}
		if (user.getLastname() != null) {
			spec = spec.and(UserSpecifications.lastnameLike(user.getLastname()));
		}
		if (user.getPhone() != null) {
			spec = spec.and(UserSpecifications.phoneLike(user.getPhone()));
		}
		if (user.getEmail() != null) {
			spec = spec.and(UserSpecifications.emailLike(user.getEmail()));
		}
		if (user.getIsReviewer()!= null) {
			spec = spec.and(UserSpecifications.isReviewerLike(user.getIsReviewer()));
		}
		if (user.getIsAdmin() != null) {
			spec = spec.and(UserSpecifications.isReviewerLike(user.getIsAdmin()));
		}
		
		
		return spec;
	}
	
	
}
