package com.qa.springcats.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.springcats.persistence.domain.CatDomain;
import com.qa.springcats.persistence.dto.CatDTO;
import com.qa.springcats.persistence.repos.CatRepo;

@SpringBootTest
public class CatServiceUnitTest {

	@MockBean // @mock annotation
	private ModelMapper mockedMapper;
	
	@MockBean
	private CatRepo mockedRepo;

	@Autowired // the same as inject mocks in test context in spring
	private CatService service;

	// CRUD tests
	@Test
	public void readAll() {
//		//the lists
//		List<CatDomain> test_cat_list = new ArrayList<>();
//		List<CatDTO> test_dto_list = new ArrayList<>();
//		//creating the objects to populate the lists
//		CatDomain test_cat1 = new CatDomain(1L, "Suki", 3, 2.5f, null);
//		CatDomain test_cat2 = new CatDomain(2L, "Coco", 9, 1f, null);
//		CatDTO test_dto1 = this.mockedMapper.map(test_cat1, CatDTO.class);
//		CatDTO test_dto2 = this.mockedMapper.map(test_cat2, CatDTO.class);
//		//adding to the lists
//		test_cat_list.add(test_cat1);
//		test_cat_list.add(test_cat2);
//		test_dto_list.add(test_dto1);
//		test_dto_list.add(test_dto2);
//		//rules
//		Mockito.when(this.mockedRepo.saveAll(test_cat_list)).thenReturn(test_cat_list);
//		//actions
//		List<CatDTO> resultList = this.service.readAll();
//		//assertions
//		Assertions.assertThat(resultList).isNotNull();
//		Assertions.assertThat(resultList).isEqualTo(test_dto_list);
//		
//		Mockito.verify(this.mockedRepo,Mockito.times(1)).saveAll(test_cat_list);
	}

	@Test
	public void readOne() {
		CatDomain test_cat = new CatDomain(1L, "Suki", 3, 2.5f, null);
		CatDTO test_dto = this.mockedMapper.map(test_cat, CatDTO.class);
		Mockito.when(this.mockedRepo.findById(test_cat.getId())).thenReturn(Optional.of(test_cat));
		CatDTO result = this.service.readOne(1L);
		Assertions.assertThat(result).isEqualTo(test_dto);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
	}

	@Test
	public void create() {
		// RESOURCE/S
		CatDomain test_cat = new CatDomain(1L, "Suki", 3, 2.5f, null);
		CatDTO test_dto = new CatDTO(1L, "Suki", 2.5f);
		// RULES
		Mockito.when(this.mockedRepo.save(Mockito.any(CatDomain.class))).thenReturn(test_cat);
		Mockito.when(this.mockedMapper.map(test_cat, CatDTO.class)).thenReturn(test_dto);
		// ACTIONS - perform (using the service)
		CatDTO result = this.service.create(test_cat);
		// ASSERTIONS
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(test_dto);
		Assertions.assertThat(result).usingRecursiveComparison()
				.isEqualTo(test_dto);

		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(test_cat, CatDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(CatDomain.class));
	}

	@Test
	public void update() {

	}

	@Test
	public void deleteCat() {
		//resources
		CatDomain test_cat = new CatDomain(1L, "Suki", 3, 2.5f, null);
		CatDTO test_dto = new CatDTO(1L, "Suki", 2.5f);
		//rules
		Mockito.when(this.mockedRepo.existsById(test_cat.getId())).thenReturn(true);
		
		//assertions
		Assertions.assertThat(this.service.deleteCat(test_dto.getId())).isEqualTo(true);
		//Assertions.assertThat(this.service.deleteCat(test_dto.getId())).isEqualTo(false);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).deleteById(test_cat.getId());
		Mockito.verify(this.mockedRepo, Mockito.times(1)).existsById(test_cat.getId());
	}

}
