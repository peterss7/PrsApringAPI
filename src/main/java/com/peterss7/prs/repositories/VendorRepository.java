package com.peterss7.prs.repositories;



import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.peterss7.prs.entities.Vendor;



public interface VendorRepository extends JpaRepository<Vendor, Integer>{
	List<Vendor> findAll(Specification<Vendor> spec); 
}
