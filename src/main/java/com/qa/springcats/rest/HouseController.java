package com.qa.springcats.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.springcats.persistence.domain.HouseDomain;
import com.qa.springcats.persistence.dto.HouseDTO;
import com.qa.springcats.services.HouseService;

@RestController
@RequestMapping("/house")
public class HouseController {
	
	private HouseService service;

	@Autowired
	public HouseController(HouseService service) {
		super();
		this.service = service;
	}

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "hello world";
	}

	// GET
	@GetMapping("/readAll")
	public ResponseEntity<List<HouseDTO>> readAll() {
		return new ResponseEntity<List<HouseDTO>>(this.service.readAll(), HttpStatus.OK);
	}

//	// GET - read by id
//	@GetMapping("/read/{id}")
//	public ResponseEntity<HouseDTO> readOne(@PathVariable("id") Long id) {
//		return ResponseEntity.ok(this.service.readOne(id));
//	}

	// POST
	@PostMapping("/create")
	public ResponseEntity<HouseDTO> create(@RequestBody HouseDomain model) {
		return new ResponseEntity<HouseDTO>(this.service.create(model), HttpStatus.CREATED);
	}

	// POST
//	@PostMapping("/createAll")
//	public List<CatDomain> createAll(@RequestBody List<CatDomain> catList) {
//		return null;
//	}

	// PUT localhost:8080/cat/update/1
	@PutMapping("/update/{id}")
	public ResponseEntity<HouseDTO> update(@PathVariable Long id, @RequestBody HouseDomain model) {
		return new ResponseEntity<HouseDTO>(this.service.update(id, model), HttpStatus.ACCEPTED);
	}

	// DELETE
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object> deleteHouse(@PathVariable Long id) {

		return this.service.deleteHouse(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
