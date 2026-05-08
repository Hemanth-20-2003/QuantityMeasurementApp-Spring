package com.app.quantitymeasurement.dto;

import com.app.quantitymeasurement.model.MeasurementType;
import com.app.quantitymeasurement.model.Unit;

import jakarta.persistence.Column;

/**
 * Data Transfer Object (DTO) for Quantity Measurement responses.
 * Contains the result of a quantity operation including:
 * - resultValue: The computed numeric result
 * - resultUnit: The unit of measurement for the result
 * - resultMeasurementType: The type of measurement (LENGTH, MASS, TEMPERATURE)
 * - resultString: For comparison operations ("Equal", "Greater", "Lower")
 */
public class QuantityResponseDto {
	
    // Numeric result of the operation
    public double resultValue;

    // Unit of the result
    public Unit resultUnit;

    // Type of measurement
    public MeasurementType resultMeasurementType;
    
    // String representation for comparison results or error messages
    public String resultString;
}
