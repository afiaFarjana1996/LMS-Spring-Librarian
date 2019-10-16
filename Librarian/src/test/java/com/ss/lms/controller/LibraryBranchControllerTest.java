package com.ss.lms.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.service.LibraryBranchService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@WebMvcTest(value=LibraryBranchController.class,secure=false)
public class LibraryBranchControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LibraryBranchService libraryBranchService;
	
	@Before
	public void setUp(){
		
	}
	
	
	@Test
	public void getBranchInfoById() throws Exception {
//		LibraryBranch mockLibraryBranch = new LibraryBranch(4,"Queens Library","Queens Central Library");
//		String inputJson = this.mapToJson(mockLibraryBranch);
//		String Uri = "lms/library-branch/getInformation/id/4";
//		Mockito.when(libraryBranchService.getLibraryBranchById(4)).thenReturn(mockLibraryBranch);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(Uri)
//				.accept(MediaType.APPLICATION_JSON).content(inputJson)
//				.contentType(MediaType.APPLICATION_JSON);
//		
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		MockHttpServletResponse response = result.getResponse();
//		
//		String outputJson = response.getContentAsString();
//		JSONAssert.assertEquals(outputJson,inputJson, false);
//		Assert.assertEquals(HttpStatus.FOUND.value(), response.getStatus());
		
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
		
	}
	
}
