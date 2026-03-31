package com.app.quantitymeasurement.dto;


import com.app.quantitymeasurement.model.MeasurementType;
import com.app.quantitymeasurement.model.Operation;
import com.app.quantitymeasurement.model.Unit;

import lombok.Data;

@Data
public class QuantityRequestDto {

    public double thisValue;
    public Unit thisUnit;
    public MeasurementType thisMeasurementType;

    public double thatValue;
    public Unit thatUnit;
    public MeasurementType thatMeasurementType;
    
    public Unit resultUnit;
    public MeasurementType resultMeasurementType;

    public Operation operation;
}