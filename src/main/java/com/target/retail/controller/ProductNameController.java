package com.target.retail.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.target.retail.model.ProductDataDTO;
import com.target.retail.model.ProductDataResponse;
import com.target.retail.service.ProductNameService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * 
 * @author Bibin Babu
 *
 */
@Api(value = "productname", description = "the productpricedetails API")
@RestController
public class ProductNameController {

	@Autowired
	ProductNameService productService;
	private static final Logger logger = LoggerFactory.getLogger(ProductNameController.class);

	@ApiOperation(value = "Find product price by ID", nickname = "getProductNameById", notes = "Returns a single product price details", response = ProductDataResponse.class, tags = {
			"productdetails", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = ProductDataResponse.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Product not found") })
	@GetMapping(value = "/productname/{id}", produces = { "application/json" })
	public ResponseEntity<ProductDataResponse> getProductNameById(
			@ApiParam(value = "ID of product", required = true) @PathVariable("id") Integer id,
			@ApiParam(value = "This parameter to use to filter specific products.") @Valid @RequestParam(value = "excludes", required = false) List<String> excludes) {
		logger.info("Product :{}", id);
		ProductDataDTO pDataDTO = new ProductDataDTO();
		pDataDTO.setProductId(id);
		pDataDTO.setProductFilter(excludes);
		ProductDataResponse pr = productService.getProductNameById(pDataDTO);
		return new ResponseEntity<>(pr, HttpStatus.OK);
	}
}
