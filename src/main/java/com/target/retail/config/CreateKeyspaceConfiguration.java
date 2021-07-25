package com.target.retail.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

@Configuration
@PropertySource(value = { "classpath:cassandra.yml" })
@ConfigurationProperties("spring.data.cassandra")
public class CreateKeyspaceConfiguration extends AbstractCassandraConfiguration implements BeanClassLoaderAware{
	
	  @Value("${keyspacename}")
	  private String cassandraKeyspace;
	@Override
	protected String getKeyspaceName() {
		return this.cassandraKeyspace;
	}
	
	
	
	@Override
	  protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

	    CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(cassandraKeyspace)
	        .with(KeyspaceOption.DURABLE_WRITES, true)
	        .withSimpleReplication().ifNotExists();

	    return Arrays.asList(specification);
	  }
	
	 @Override 
	 protected List<String> getStartupScripts() {
	    return Collections.singletonList("CREATE TABLE IF NOT EXISTS "+cassandraKeyspace+".productdata(productId int PRIMARY KEY, currencycode text, name text,productprice double,productype text) WITH default_time_to_live = 600;");
	  }

}
