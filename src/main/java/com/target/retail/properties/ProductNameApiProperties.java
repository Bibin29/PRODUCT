package com.target.retail.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "api-endpoints")
public class ProductNameApiProperties {
	private String productnameAPIURL;
	
	private String apiKey;

	public String getProductnameAPIURL() {
		return productnameAPIURL;
	}

	public void setProductnameAPIURL(String productnameAPIURL) {
		this.productnameAPIURL = productnameAPIURL;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	
	
	
}
