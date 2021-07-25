package com.target.retail.entities;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("productdata")
public class ProductData {
	@PrimaryKey
	@Column
	int productId;
	@Column
	private String currencyCode;
	@Column
	private String name;
	@Column
	private double productprice;
	@Column
	private String productype;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getProductprice() {
		return productprice;
	}

	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}

	public String getProductype() {
		return productype;
	}

	public void setProductype(String productype) {
		this.productype = productype;
	}

}
