package com.peterss7.prs.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


import com.peterss7.prs.entities.User;



public interface UserRepository extends JpaRepository<User, Integer>{

	// Optional<List<User>> findAll(Specification<User> spec);
	

//	List<User> findAll(Specification<User> spec);	
	Optional<List<User>> findAll(Specification<User> spec);


}
