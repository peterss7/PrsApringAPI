package com.peterss7.prs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peterss7.prs.entities.Request;
import com.peterss7.prs.entities.RequestLine;
import com.peterss7.prs.entities.User;

public interface RequestLineRepository extends JpaRepository<RequestLine, Integer> {
	
	Optional<List<RequestLine>> findAll(Specification<RequestLine> spec);
	
	@Query("SELECT r FROM RequestLine r WHERE r.request = :request")
    Optional<List<RequestLine>> findByRequest(@Param("request") Request request);
}


