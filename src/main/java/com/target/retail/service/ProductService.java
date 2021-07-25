package com.target.retail.service;

import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.target.retail.entities.ProductData;
import com.target.retail.exceptions.GenericApiException;
import com.target.retail.exceptions.ProductNotFoundException;
import com.target.retail.model.ProductCurrentPrice;
import com.target.retail.model.ProductDataDTO;
import com.target.retail.model.ProductDataResponse;
import com.target.retail.properties.ProductNameApiProperties;
import com.target.retail.repository.ProductDataRepository;

/**
 * 
 * @author Bibin Babu
 *
 */
@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	ProductDataRepository pdRepo;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ProductNameApiProperties productNameApiProperties;

	/**
	 * Method to return the Product details by productID
	 * 
	 * @param pDataDTO
	 * @return ProductData
	 */
	public ProductDataResponse getProductById(ProductDataDTO pDataDTO) {
		logger.info("Excecuting product by ID process");
		ProductData product = null;
		ProductDataResponse pdres = null;
		Optional<ProductData> productDetails = pdRepo.findById(pDataDTO.getProductId());
		if (productDetails.isPresent()) {
			product = productDetails.get();
			getProductName(pDataDTO, product);
			pdres = populateProductData(product);
		} else {
			throw new ProductNotFoundException(pDataDTO.getProductId());
		}
		return pdres;

	}

	/**
	 * Get the product name for the product id
	 * 
	 * @param pDataDTO
	 * @param product
	 */
	private void getProductName(ProductDataDTO pDataDTO, ProductData product) {
		logger.info("Excecuting API call to get the Product name for ID {}", pDataDTO.getProductId());
		ProductDataResponse productdataRes = new ProductDataResponse();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<ProductDataResponse> entity = new HttpEntity<>(productdataRes, headers);
			UriComponentsBuilder builder = UriComponentsBuilder
					.fromHttpUrl(productNameApiProperties.getProductnameAPIURL() + "/" + pDataDTO.getProductId());
			ResponseEntity<ProductDataResponse> prd = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					ProductDataResponse.class);
			logger.info("Product records received from Target  API :{}", prd);
			saveProductName(product, prd);
		} catch (GenericApiException e) {
			throw new GenericApiException(String.valueOf(pDataDTO.getProductId()), e);
		}

	}

	private void saveProductName(ProductData product, ResponseEntity<ProductDataResponse> productJsonData) {
		if (productJsonData != null && productJsonData.getBody() != null) {
			product.setName(productJsonData.getBody().getName());
		}

	}

	/**
	 * Populate response data for the API
	 * 
	 * @param product
	 * @return
	 */
	private ProductDataResponse populateProductData(ProductData product) {

		ProductDataResponse pd = new ProductDataResponse();
		ProductCurrentPrice pcp = new ProductCurrentPrice();
		pcp.currencyCode(product.getCurrencyCode());
		pcp.setValue(product.getProductprice());
		pd.setId(product.getProductId());
		pd.setName(product.getName());
		pd.setCurrentPrice(pcp);
		return pd;

	}
	
	/**
	 * Update Product price
	 * @param dto
	 */
	public void updateProductData(ProductDataDTO dto) {
        pdRepo.updateProductPrice(dto.getProductData().getCurrentPrice().getValue(),dto.getProductId());
	}

}
