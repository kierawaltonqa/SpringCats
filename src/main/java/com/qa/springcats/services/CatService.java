package com.qa.springcats.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.springcats.persistence.domain.CatDomain;
import com.qa.springcats.persistence.dto.CatDTO;
import com.qa.springcats.persistence.repos.CatRepo;

@Service
public class CatService {
	
	private CatRepo repo;
	private ModelMapper mapper;

	@Autowired
	public CatService(CatRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	//performing CRUD
	//calling on repo to extract data to and from db

	private CatDTO mapToDTO(CatDomain model) {
		return this.mapper.map(model, CatDTO.class);
	}
	
	// GET - read all
	public List<CatDTO> readAll() {
		List<CatDomain> dbList = this.repo.findAll();
		List<CatDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());

		return resultList;
	}
	
	// GET - read by id
	public CatDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
		
	}

	// POST
	public CatDTO create(CatDomain model) {
		return this.mapToDTO(this.repo.save(model));
	}

	// POST - create all
	

	// PUT
	public CatDTO update(Long id, CatDomain newDetails) {
		this.repo.findById(id).orElseThrow();
		// cat target
		newDetails.setId(id);
		CatDTO result = this.mapToDTO(this.repo.save(newDetails));
		return result;
	}

	// DELETE
	public boolean deleteCat(Long id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
}
