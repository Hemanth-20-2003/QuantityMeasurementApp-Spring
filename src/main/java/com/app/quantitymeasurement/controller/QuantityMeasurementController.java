package com.app.quantitymeasurement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantitymeasurement.dto.QuantityRequestDto;
import com.app.quantitymeasurement.dto.QuantityResponseDto;
import com.app.quantitymeasurement.model.Operation;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.QuantityMeasurementService;

/**
 * REST Controller for Quantity Measurement operations.
 * Handles HTTP requests for adding, subtracting, dividing, multiplying,
 * comparing, converting quantities and retrieving operation history.
 * 
 * CORS is enabled for localhost:5174 to allow frontend requests.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5174")
public class QuantityMeasurementController {
	// Service layer injected via Spring dependency injection
	@Autowired
	QuantityMeasurementService service;
	
	/**
	 * Endpoint to add two quantities with unit conversion.
	 * @param ent QuantityRequestDto containing both quantities and units
	 * @return QuantityResponseDto with the addition result
	 */
	@PostMapping("/add")
	public QuantityResponseDto add(@RequestBody QuantityRequestDto ent) {
		return service.add(ent);
	}
	
	
	/**
	 * Endpoint to subtract one quantity from another with unit conversion.
	 * @param ent QuantityRequestDto containing both quantities and units
	 * @return QuantityResponseDto with the subtraction result
	 */
	@PostMapping("/subtract")
	public QuantityResponseDto subtract(@RequestBody QuantityRequestDto ent) {
		return service.subtract(ent);
	}
	
	/**
	 * Endpoint to divide one quantity by another with unit conversion.
	 * @param ent QuantityRequestDto containing both quantities and units
	 * @return QuantityResponseDto with the division result
	 */
	@PostMapping("/divide")
	public QuantityResponseDto divide(@RequestBody QuantityRequestDto ent) {
		return service.divide(ent);
	}
	
	/**
	 * Endpoint to multiply two quantities with unit conversion.
	 * @param ent QuantityRequestDto containing both quantities and units
	 * @return QuantityResponseDto with the multiplication result
	 */
	@PostMapping("/multiply")
	public QuantityResponseDto multiply(@RequestBody QuantityRequestDto ent) {
		return service.add(ent);
	}
	
	/**
	 * Endpoint to compare two quantities (Equal, Greater, Lower).
	 * @param ent QuantityRequestDto containing both quantities and units
	 * @return QuantityResponseDto with the comparison result
	 */
	@PostMapping("/compare")
	public QuantityResponseDto compare(@RequestBody QuantityRequestDto ent) {
		return service.compare(ent);
	}
	
	/**
	 * Endpoint to convert a quantity from one unit to another.
	 * @param ent QuantityRequestDto containing quantity and target unit
	 * @return QuantityResponseDto with the converted value
	 */
	@PostMapping("/convert")
	public QuantityResponseDto convert(@RequestBody QuantityRequestDto ent) {
		return service.convert(ent);
	}
	
	/**
	 * Health check endpoint to verify API is running.
	 * @return Success message
	 */
	@GetMapping("/msg")
	public String msg() {
		return "Success";
	}
	
	/**
	 * Endpoint to retrieve complete operation history from database.
	 * @return List of all QuantityMeasurementEntity records
	 */
	@GetMapping("history")
	public List<QuantityMeasurementEntity> his(){
		return service.history();
	}
	
	/**
	 * Endpoint to retrieve operation history filtered by operation type.
	 * @param op Operation type to filter by (COMPARE, CONVERT, ADD, SUBTRACT, DIVIDE, MULTIPLY)
	 * @return List of QuantityMeasurementEntity records matching the operation
	 */
	@GetMapping("history/{op}")
	public List<QuantityMeasurementEntity> getbyoperation(@PathVariable Operation op){
		return service.getbyOperation(op);
	} 
	
	

}
