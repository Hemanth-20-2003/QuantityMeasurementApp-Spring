package com.app.quantitymeasurement.service;

import java.util.List;
import java.nio.channels.NonWritableChannelException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.dto.QuantityRequestDto;
import com.app.quantitymeasurement.dto.QuantityResponseDto;
import com.app.quantitymeasurement.model.Operation;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

/**
 * Service class for Quantity Measurement operations.
 * Handles business logic for converting, comparing, and performing arithmetic operations on quantities.
 * Provides mapping between DTOs and database entities, and persists operation history.
 */
@Service
public class QuantityMeasurementService {
	
	// Database repository for persistence operations
	@Autowired
	QuantityMeasurementRepository repository;
	
	/**
	 * Convert QuantityRequestDto to QuantityMeasurementEntity.
	 * Copies request parameters to entity for database persistence.
	 * 
	 * @param dto the request DTO containing quantity parameters
	 * @return QuantityMeasurementEntity with mapped values
	 */
	public QuantityMeasurementEntity mapper(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=new QuantityMeasurementEntity();
		entity.thisMeasurementType=dto.thisMeasurementType;
		entity.thisUnit=dto.thisUnit;
		entity.thisValue=dto.thisValue;
		entity.thatMeasurementType=dto.thatMeasurementType;
		entity.thatUnit=dto.thatUnit;
		entity.thatValue=dto.thatValue;
		entity.resultUnit=dto.resultUnit;
		entity.resultMeasurementType=dto.resultMeasurementType;
		return entity;
	}
	
	/**
	 * Convert QuantityMeasurementEntity to QuantityResponseDto.
	 * Extracts result values from entity to send to client.
	 * 
	 * @param entity the entity containing operation results
	 * @return QuantityResponseDto with result values
	 */
	public QuantityResponseDto remapper(QuantityMeasurementEntity entity) {
		QuantityResponseDto res=new QuantityResponseDto();
		res.resultUnit=entity.resultUnit;
		res.resultMeasurementType=entity.resultMeasurementType;
		res.resultValue=entity.resultValue;
		res.resultString=entity.resultString;
		return res;
	}
	
	/**
	 * Add two quantities together.
	 * Validates that both quantities have the same measurement type.
	 * Converts both values to base unit, adds them, then converts back to result unit.
	 * 
	 * Formula: ((thisValue * thisUnit.factor) + (thatValue * thatUnit.factor)) / resultUnit.factor
	 * 
	 * @param dto request containing two quantities and result unit
	 * @return QuantityResponseDto with the sum, or null if measurement types don't match
	 */
	public QuantityResponseDto add(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
		// Verify both quantities are same measurement type (e.g., both are LENGTH)
		if(entity.thatMeasurementType==entity.thisMeasurementType) {
			System.out.println("hh");
			// Default result unit to first quantity's unit if not specified
			if(entity.resultUnit==null) {
				entity.resultUnit=entity.thisUnit;
			}
			// Default result measurement type to measurement type of quantities
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;
			}

			// Convert to base units, add, then convert to result unit
			entity.resultValue=((entity.thisValue*entity.thisUnit.factor)+(entity.thatValue*entity.thatUnit.factor))/entity.resultUnit.factor;

			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.ADD;
			}
			// Persist operation to database
			repository.save(entity);
			return res;
		}
		// Return null if measurement types don't match
		return null;
	}
	
	/**
	 * Subtract one quantity from another.
	 * Validates that both quantities have the same measurement type.
	 * Converts both values to base unit, subtracts them, then converts back to result unit.
	 * 
	 * Formula: ((thisValue * thisUnit.factor) - (thatValue * thatUnit.factor)) / resultUnit.factor
	 * 
	 * @param dto request containing two quantities and result unit
	 * @return QuantityResponseDto with the difference, or null if measurement types don't match
	 */
	public QuantityResponseDto subtract(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
		if(entity.thatMeasurementType==entity.thisMeasurementType) {
			System.out.println("hh");
			if(entity.resultUnit==null) {
				entity.resultUnit=entity.thisUnit;
			}
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;
			}

			entity.resultValue=((entity.thisValue*entity.thisUnit.factor)-(entity.thatValue*entity.thatUnit.factor))/entity.resultUnit.factor;

			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.SUBTRACT;
			}
			repository.save(entity);
			return res;
		}
		return null;
	}
	
	/**
	 * Multiply two quantities together.
	 * Validates that both quantities have the same measurement type.
	 * Converts both values to base unit, multiplies them, then converts back to result unit.
	 * 
	 * Formula: ((thisValue * thisUnit.factor) * (thatValue * thatUnit.factor)) / resultUnit.factor
	 * 
	 * @param dto request containing two quantities and result unit
	 * @return QuantityResponseDto with the product, or null if measurement types don't match
	 */
	public QuantityResponseDto multiply(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
		if(entity.thatMeasurementType==entity.thisMeasurementType) {
			System.out.println("hh");
			if(entity.resultUnit==null) {
				entity.resultUnit=entity.thisUnit;
			}
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;
			}

			entity.resultValue=((entity.thisValue*entity.thisUnit.factor)*(entity.thatValue*entity.thatUnit.factor))/entity.resultUnit.factor;

			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.MULTIPLY;
			}
			repository.save(entity);
			return res;
		}
		return null;
	}
	
	/**
	 * Divide one quantity by another.
	 * Validates that both quantities have the same measurement type.
	 * Converts both values to base unit, divides them, then converts back to result unit.
	 * 
	 * Formula: ((thisValue * thisUnit.factor) / (thatValue * thatUnit.factor)) / resultUnit.factor
	 * 
	 * @param dto request containing two quantities and result unit
	 * @return QuantityResponseDto with the quotient, or null if measurement types don't match
	 */
	public QuantityResponseDto divide(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
		if(entity.thatMeasurementType==entity.thisMeasurementType) {
			System.out.println("hh");
			if(entity.resultUnit==null) {
				entity.resultUnit=entity.thisUnit;
			}
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;
			}

			entity.resultValue=((entity.thisValue*entity.thisUnit.factor)/(entity.thatValue*entity.thatUnit.factor))/entity.resultUnit.factor;

			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.DIVIDE;
			}
			repository.save(entity);
			return res;
		}
		return null;
	}
	/**
	 * Compare two quantities.
	 * Validates that both quantities have the same measurement type.
	 * Converts both to base unit and returns comparison result ("Equal", "Greater", or "Lower").
	 * 
	 * @param dto request containing two quantities to compare
	 * @return QuantityResponseDto with comparison result (resultString), or null if types don't match
	 */
	public QuantityResponseDto compare(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
		if(entity.thatMeasurementType==entity.thisMeasurementType) {
			System.out.println("hh");
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;
			}

			// Convert both quantities to base unit for comparison
			double thatval=entity.thatValue*entity.thatUnit.factor;
			double thisval=entity.thisValue*entity.thisUnit.factor;
			// Compare and set result string
			if(thisval==thatval) {
				entity.resultString="Equal";
			}
			if(thisval>thatval) {
				entity.resultString="Greater";
			}
			if(thisval<thatval) {
				entity.resultString="Lower";
			}
			
			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.COMPARE;
			}
			// Persist operation to database
			repository.save(entity);
			return res;
		}
		return null;
	}
	
	/**
	 * Convert a quantity from one unit to another.
	 * Converts the quantity to base unit, then converts to target result unit.
	 * No measurement type validation needed as conversion is within same measurement type.
	 * 
	 * Formula: (thisValue * thisUnit.factor) / resultUnit.factor
	 * 
	 * @param dto request containing quantity and target unit for conversion
	 * @return QuantityResponseDto with the converted value
	 */
	public QuantityResponseDto convert(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
			System.out.println("hh");
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;
			}

			// Convert to base unit then to result unit
			double thisval=entity.thisValue*entity.thisUnit.factor;
			entity.resultValue=thisval/entity.resultUnit.factor;
			
			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.CONVERT;
			}
			// Persist operation to database
			repository.save(entity);
			return res;
	}
	
	/**
	 * Retrieve the complete history of all quantity measurement operations.
	 * 
	 * @return List of all QuantityMeasurementEntity records from database
	 */
	public java.util.List<QuantityMeasurementEntity> history(){
		return repository.findAll();
	}
	
	
	
	public List<QuantityMeasurementEntity> getbyOperation(Operation op){
		return repository.findByOperation(op);
	}
	
}
