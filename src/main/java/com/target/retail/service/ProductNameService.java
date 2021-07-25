package com.target.retail.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.retail.entities.ProductData;
import com.target.retail.model.ProductCurrentPrice;
import com.target.retail.model.ProductDataDTO;
import com.target.retail.model.ProductDataResponse;
import com.target.retail.repository.ProductDataRepository;

@Service
public class ProductNameService {

	@Autowired
	ProductDataRepository pdRepo;

	/**
	 * Method to return the Product details by productID
	 * 
	 * @param pDataDTO
	 * @return ProductData
	 */
	public ProductDataResponse getProductNameById(ProductDataDTO pDataDTO) {
		ProductData product = null;
		ProductDataResponse pd = new ProductDataResponse();
		Optional<ProductData> productDetails=pdRepo.findById(pDataDTO.getProductId());
		if (productDetails.isPresent()) {
			product = productDetails.get();
			if(pDataDTO.getProductFilter()!=null && !pDataDTO.getProductFilter().isEmpty()) {
				product = !pDataDTO.getProductFilter().contains(productDetails.get().getProductype())?product:null;
			}
			if (product!=null) {
				pd = populateProductData(product);
			}
			
		}
		return pd;

	}

	/**
	 * Populate the response data for the API
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
}
