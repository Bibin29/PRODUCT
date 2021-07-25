package com.target.retail;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.retail.controller.ProductsController;
import com.target.retail.model.ProductDataResponse;
import com.target.retail.properties.ProductNameApiProperties;
import com.target.retail.repository.ProductDataRepository;
import com.target.retail.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductsController.class)
public class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ProductsController productsController;
	
	@MockBean
	ProductDataRepository pdRepo;

	@MockBean
	RestTemplate restTemplate;
	
	@MockBean
	ProductNameApiProperties productNameApiProperties;
	
	@MockBean
	ProductService productService;
	
	@Autowired
	private ObjectMapper mapper;

	
	
	@Test
	public void testProductControllerGetTest() throws Exception {
		ProductDataResponse prd= new ProductDataResponse();
		prd.setId(9090909);
		prd.setName("TEST");;
		Mockito.when(productService.getProductById(Mockito.any())).thenReturn(prd);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/9090909").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		ProductDataResponse value= mapper.readValue(result.getResponse().getContentAsString(), ProductDataResponse.class);

		assertNotNull(value);
		
	}
	
	@Test
	public void testProductDataTest() throws Exception {
		ProductDataResponse prd= new ProductDataResponse();
		prd.setId(9090909);
		prd.setName("TEST");;
		Mockito.when(productService.getProductById(Mockito.any())).thenReturn(prd);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/9090909").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		ProductDataResponse value= mapper.readValue(result.getResponse().getContentAsString(), ProductDataResponse.class);

		assertEquals(9090909,value.getId());
		assertEquals("TEST", value.getName());
	}

}
