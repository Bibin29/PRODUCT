package com.target.retail.exceptions;

public class ProductDataUpdateException extends GenericApiException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductDataUpdateException(String productId, Exception ex) {
		super(productId, ex);
	}

}
