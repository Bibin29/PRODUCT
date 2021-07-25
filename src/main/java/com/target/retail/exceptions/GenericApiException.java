package com.target.retail.exceptions;

public class GenericApiException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private final String productId;

	public GenericApiException(String productId, Exception ex) {
		super(ex);
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}
}
