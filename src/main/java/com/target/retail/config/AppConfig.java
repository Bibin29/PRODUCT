package com.target.retail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
public class AppConfig {

	  /*
	   * Use the standard Cassandra driver API to create a com.datastax.oss.driver.api.core.CqlSession instance.
	   */
//	  public @Bean CqlSession session() {
//	    return CqlSession.builder().withKeyspace("tragetretailproducts").build();
//	  }
}
