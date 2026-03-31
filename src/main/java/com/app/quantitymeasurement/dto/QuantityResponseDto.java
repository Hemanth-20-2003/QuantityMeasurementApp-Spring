package com.app.quantitymeasurement.dto;

import com.app.quantitymeasurement.model.MeasurementType;
import com.app.quantitymeasurement.model.Unit;

import jakarta.persistence.Column;

public class QuantityResponseDto {
	
    public double resultValue;

    public Unit resultUnit;

    public MeasurementType resultMeasurementType;
    
    public String resultString;
}
