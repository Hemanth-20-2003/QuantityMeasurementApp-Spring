package com.app.quantitymeasurement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@RestController
public class QuantityMeasurementController {
	@Autowired
	QuantityMeasurementService service;
	
	@PostMapping("/add")
	public QuantityResponseDto add(@RequestBody QuantityRequestDto ent) {
		return service.add(ent);
	}
	
	
	@PostMapping("/subtract")
	public QuantityResponseDto subtract(@RequestBody QuantityRequestDto ent) {
		return service.subtract(ent);
	}
	
	@PostMapping("/divide")
	public QuantityResponseDto divide(@RequestBody QuantityRequestDto ent) {
		return service.divide(ent);
	}
	
	@PostMapping("/multiply")
	public QuantityResponseDto multiply(@RequestBody QuantityRequestDto ent) {
		return service.add(ent);
	}
	
	@PostMapping("/compare")
	public QuantityResponseDto compare(@RequestBody QuantityRequestDto ent) {
		return service.compare(ent);
	}
	
	@PostMapping("/convert")
	public QuantityResponseDto convert(@RequestBody QuantityRequestDto ent) {
		return service.convert(ent);
	}
	
	@GetMapping("/msg")
	public String msg() {
		return "Success";
	}
	
	@GetMapping("history")
	public List<QuantityMeasurementEntity> his(){
		return service.history();
	}
	
	@GetMapping("history/{op}")
	public List<QuantityMeasurementEntity> getbyoperation(@PathVariable Operation op){
		return service.getbyOperation(op);
	} 
	
	

}
