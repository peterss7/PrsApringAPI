package com.peterss7.prs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.peterss7.prs.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{
	Optional<List<Product>> findAll(Specification<Product> spec);
}
