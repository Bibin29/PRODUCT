package com.target.retail.exceptions;

public class ProductNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ProductNotFoundException(int i) {
		super("Could not find Product matching code " + i);
	}

}
