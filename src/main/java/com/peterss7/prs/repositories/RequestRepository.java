package com.peterss7.prs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.peterss7.prs.entities.Request;

public interface RequestRepository extends JpaRepository<Request, Integer>{
	Optional<List<Request>> findAll(Specification<Request> spec);
	
	
	
}
