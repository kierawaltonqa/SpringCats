package com.qa.springcats.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springcats.persistence.domain.CatDomain;
import com.qa.springcats.persistence.dto.CatDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class CatControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private final int ID = 1;

	private CatDTO mapToDTO(CatDomain model) {
		return this.mapper.map(model, CatDTO.class);
	}

	// read
	@Test
	public void readAll() throws Exception {
		// resources
		List<CatDTO> expectedResult = new ArrayList<>();
		expectedResult.add(new CatDTO(1L, "Suki", 2.5f));
		expectedResult.add(new CatDTO(2L, "Percy", 3f));
		expectedResult.add(new CatDTO(3L, "Billy", 5.5f));
		expectedResult.add(new CatDTO(4L, "Coco", 1.5f));
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/cat/readAll");
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void readOne() throws Exception {
		CatDTO expectedResult = new CatDTO(1L, "Suki", 2.5f);
		// set up request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/cat/read/" + ID);
		// set up expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	// create
	@Test
	public void create() throws Exception {
		CatDomain contentBody = new CatDomain("Mango", 4, 5f, null);
		CatDTO expectedResult = mapToDTO(contentBody);
		expectedResult.setId(5L);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/cat/create").contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);
		// set up expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// perform action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	// update
	@Test
	public void updateCat() throws Exception {
		// resources
		CatDomain contentBody = new CatDomain(1L, "Suki", 1, 2.5f, null);
		CatDomain updatedBody = new CatDomain(contentBody.getName(), contentBody.getAge(),
				contentBody.getSpeechVolume(), contentBody.getMyHouse());
		updatedBody.setId(1L);
		CatDTO expectedResult = mapToDTO(updatedBody);
		// set up request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/cat/update/" + ID)
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);
		// set up expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// perform action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	// delete
	@Test
	public void deleteCat() throws Exception {
		// resources
		
		// mock request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/cat/delete/" + 2);
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		// perform
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}
