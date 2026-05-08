package com.app.quantitymeasurement.dto;


import com.app.quantitymeasurement.model.MeasurementType;
import com.app.quantitymeasurement.model.Operation;
import com.app.quantitymeasurement.model.Unit;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for Quantity Measurement requests.
 * Contains the input parameters for quantity operations including:
 * - First quantity (thisValue, thisUnit, thisMeasurementType)
 * - Second quantity (thatValue, thatUnit, thatMeasurementType) - optional for single quantity operations
 * - Result specifications (resultUnit, resultMeasurementType)
 * - Operation type (COMPARE, CONVERT, ADD, SUBTRACT, DIVIDE, MULTIPLY)
 * 
 * Uses Lombok @Data to auto-generate getters, setters, toString, equals, hashCode.
 */
@Data
public class QuantityRequestDto {

    // First quantity - the primary value
    public double thisValue;
    public Unit thisUnit;
    public MeasurementType thisMeasurementType;

    // Second quantity - used for binary operations (add, subtract, etc.)
    public double thatValue;
    public Unit thatUnit;
    public MeasurementType thatMeasurementType;
    
    // Result specification - defines the unit and measurement type for the result
    public Unit resultUnit;
    public MeasurementType resultMeasurementType;

    // The operation to perform
    public Operation operation;
}