package com.qa.springcats.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.springcats.persistence.domain.CatDomain;

@Repository 
public interface CatRepo extends JpaRepository<CatDomain, Long>{

	// CRUD -> h2 Database
	
}
