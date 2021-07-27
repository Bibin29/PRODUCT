package com.target.retail.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.target.retail.entities.ProductData;
import com.target.retail.exceptions.GenericApiException;
import com.target.retail.exceptions.ProductDataUpdateException;
import com.target.retail.exceptions.ProductNotFoundException;
import com.target.retail.model.ProductDataDTO;
import com.target.retail.model.ProductDataResponse;
import com.target.retail.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "products", description = "the products API")
public class ProductsController {
@Autowired
ProductService productService;
	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
	@ApiOperation(value = "Find product details by ID", nickname = "getProductById", notes = "Returns a single product", response = ProductDataResponse.class, tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ProductDataResponse.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Product not found") })
    @GetMapping(value = "/products/{id}",
        produces = { "application/json"})
	public ResponseEntity<ProductDataResponse> getProductById(
			@ApiParam(value = "ID of product", required = true) @PathVariable("id") Integer id) {
		logger.info("Product :{}", id);
		try {
		ProductDataDTO productDTO= new ProductDataDTO();
    	productDTO.setProductId(id);
    	ProductDataResponse pr=productService.getProductById(productDTO);
		return new ResponseEntity<>(pr,HttpStatus.OK);}
		catch(ProductNotFoundException e) {
			throw new ProductNotFoundException(String.valueOf(id), e);
		}
		catch(GenericApiException e) {
			throw new GenericApiException(String.valueOf(id), e);
		}

	}
	@ApiOperation(value = "Update an existing product details/price", nickname = "updateProduct", notes = "", tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Product not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    @PutMapping(value = "/products/{id}",
        produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> updateProduct(
			@ApiParam(value = "product id for which the details/price has to be updated", required = true) @PathVariable("id") Integer id,
			@ApiParam(value = "Product object that needs to be added to the store", required = true) @Valid @RequestBody ProductDataResponse body) {
		logger.info("Product update for:{}", id);
		try {
			ProductDataDTO productDTO= new ProductDataDTO();
			productDTO.setProductId(id);
			productDTO.setProductData(body);
			productService.updateProductData(productDTO);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (ProductDataUpdateException e) {
			throw new ProductDataUpdateException(String.valueOf(id), e);
		}
		catch (GenericApiException e) {
			throw new GenericApiException(String.valueOf(id), e);
		}
		
	}

}
