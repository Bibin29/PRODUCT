package com.target.retail.model;

import java.util.List;

import com.target.retail.entities.ProductData;

/**
 * @author Bibin Babu
 * Data transfer object for product data
 */
public class ProductDataDTO {
	private int productId;
	private String name;
	private double value;
	private String currencyCode;
	
	private List<String> productFilter;
	
	private ProductDataResponse productData;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public List<String> getProductFilter() {
		return productFilter;
	}

	public void setProductFilter(List<String> productFilter) {
		this.productFilter = productFilter;
	}

	public ProductDataResponse getProductData() {
		return productData;
	}

	public void setProductData(ProductDataResponse productData) {
		this.productData = productData;
	}
	
	

}
