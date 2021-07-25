package com.target.retail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.target.retail.entities.ProductData;
import com.target.retail.model.ProductCurrentPrice;
import com.target.retail.model.ProductDataDTO;
import com.target.retail.model.ProductDataResponse;
import com.target.retail.properties.ProductNameApiProperties;
import com.target.retail.repository.ProductDataRepository;
import com.target.retail.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
	
	@MockBean
	ProductDataRepository pdRepo;

	@MockBean
	RestTemplate restTemplate;
	
	@Autowired
	ProductNameApiProperties productNameApiProperties;
	
	@Autowired
	ProductService productService;
	
	@Test
	public void testProductGetService() {
		ProductDataDTO  productDataDTO= new ProductDataDTO();
		productDataDTO.setProductId(8989898);
		ProductData product= new ProductData();
		product.setCurrencyCode("USD");
		product.setName("TEST");
		product.setProductId(8989898);
		product.setProductprice(800.80);
		ProductDataResponse pdata= new ProductDataResponse();
		pdata.setId(product.getProductId());
		pdata.setName(product.getName());
		Mockito.when(pdRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(product));
		ResponseEntity<ProductDataResponse> myEntity = new ResponseEntity<>(pdata, HttpStatus.ACCEPTED);
		Mockito.when(restTemplate.exchange(
				 Mockito.anyString(), Mockito.any(HttpMethod.class), ArgumentMatchers.any(), ArgumentMatchers.<Class<ProductDataResponse>>any()))
		        .thenReturn(myEntity);
		ProductDataResponse pd=productService.getProductById(productDataDTO);
		assertEquals(8989898, pd.getId().intValue());
		assertEquals("TEST",pd.getName());
		
	}
	
	
	@Test
	public void testProductPutService() {
		ProductDataDTO  productDataDTO= new ProductDataDTO();
		productDataDTO.setProductId(8989898);
		ProductData product= new ProductData();
		product.setCurrencyCode("USD");
		product.setName("TEST");
		product.setProductId(8989898);
		product.setProductprice(800.80);
        ProductCurrentPrice productCurrentPrice= new ProductCurrentPrice();
        productCurrentPrice.setCurrencyCode("USD");
        productCurrentPrice.setValue(1000.00);
        ProductDataResponse pdata= new ProductDataResponse();
		pdata.setId(product.getProductId());
		pdata.setName(product.getName());
		pdata.setCurrentPrice(productCurrentPrice);
		productDataDTO.setProductData(pdata);
		Mockito.doNothing().when(pdRepo).updateProductPrice(Mockito.anyDouble(),Mockito.anyInt());
		Exception excep = null;
		try {
			productService.updateProductData(productDataDTO);
		} catch (Exception ex) {
			excep = ex;
		}
		assertNull(excep);
		
	}

}
