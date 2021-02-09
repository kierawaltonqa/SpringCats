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

import com.qa.springcats.persistence.domain.CatDomain;
import com.qa.springcats.persistence.dto.CatDTO;
import com.qa.springcats.services.CatService;

@RestController
@RequestMapping("/cat")
public class CatController {

	private CatService service;

	@Autowired
	public CatController(CatService service) {
		super();
		this.service = service;
	}

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "hello world";
	}

	// GET
	@GetMapping("/readAll")
	public ResponseEntity<List<CatDTO>> readAll() {
		return new ResponseEntity<List<CatDTO>>(this.service.readAll(), HttpStatus.OK);
	}

	// GET - read by id
	@GetMapping("/read/{id}")
	public ResponseEntity<CatDTO> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

	// POST
	@PostMapping("/create")
	public ResponseEntity<CatDTO> create(@RequestBody CatDomain cat) {
		return new ResponseEntity<CatDTO>(this.service.create(cat), HttpStatus.CREATED);
	}

	// POST
	@PostMapping("/createAll")
	public List<CatDomain> createAll(@RequestBody List<CatDomain> catList) {
		return null;
	}

	// PUT localhost:8080/cat/update/1
	@PutMapping("/update/{id}")
	public ResponseEntity<CatDTO> updateCat(@PathVariable Long id, @RequestBody CatDomain cat) {
		return new ResponseEntity<CatDTO>(this.service.update(id, cat), HttpStatus.ACCEPTED);
	}

	// DELETE
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object> deleteCat(@PathVariable Long id) {

		return this.service.deleteCat(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
