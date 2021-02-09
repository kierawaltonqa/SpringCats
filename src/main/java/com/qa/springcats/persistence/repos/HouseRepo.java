package com.qa.springcats.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.springcats.persistence.domain.HouseDomain;

public interface HouseRepo extends JpaRepository<HouseDomain, Long>{

}
