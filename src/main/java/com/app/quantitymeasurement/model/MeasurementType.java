package com.app.quantitymeasurement.model;

/**
 * Enumeration of supported measurement types in the Quantity Measurement Application.
 * 
 * LENGTH: For distances and dimensions (cm, m, km)
 * MASS: For weights and masses (g, kg)
 * TEMPERATURE: For temperature values (C, F)
 */
public enum MeasurementType {
    /** Measurement type for lengths and distances */
    LENGTH,
    /** Measurement type for masses and weights */
    MASS,
    /** Measurement type for temperature */
    TEMPERATURE
}
