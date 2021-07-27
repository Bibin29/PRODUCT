package com.target.retail.exceptions;

public class ProductNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String productId;
	
	public ProductNotFoundException(String productId, Exception ex) {
		super(ex);
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}
}
