package com.qa.springcats.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.springcats.persistence.domain.HouseDomain;
import com.qa.springcats.persistence.dto.HouseDTO;
import com.qa.springcats.persistence.repos.HouseRepo;

@Service
public class HouseService {

	private HouseRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public HouseService(HouseRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private HouseDTO mapToDTO(HouseDomain model) {
		return this.mapper.map(model, HouseDTO.class);
	}
	
	//read all
	public List<HouseDTO> readAll() {
		List<HouseDomain> houseList = this.repo.findAll();
		List<HouseDTO> houseDTOList = houseList.stream().map(this::mapToDTO).collect(Collectors.toList());

		return houseDTOList;
	}
	
	//create
	public HouseDTO create(HouseDomain model) {
		return this.mapToDTO(this.repo.save(model));
	}
	
	//update
	public HouseDTO update(Long id, HouseDomain newDetails) {
		this.repo.findById(id).orElseThrow();
		// cat target
		newDetails.setId(id);
		HouseDTO result = this.mapToDTO(this.repo.save(newDetails));
		return result;
	}
	
	//delete
	public boolean deleteHouse(Long id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
}

