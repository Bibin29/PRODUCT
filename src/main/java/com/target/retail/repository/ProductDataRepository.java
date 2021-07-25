package com.target.retail.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.target.retail.entities.ProductData;


public interface ProductDataRepository extends CassandraRepository<ProductData, Integer>{

	@Query("update ProductData set productprice = :price where productId = :id")
	public void updateProductPrice(@Param("price") double price, @Param("id") int id);

}
