package com.app.quantitymeasurement.model;

/**
 * Enumeration of supported units for quantity measurements.
 * Each unit has a conversion factor to the base unit:
 * - Length: base unit is meter (M = 1)
 * - Mass: base unit is kilogram (KG = 1)
 * - Temperature: factors are placeholders (not meaningful for conversions)
 */
public enum Unit {

    // Length units - conversion factor to meters
    /** Centimeter: 0.01 meters */
    CM(0.01),
    /** Meter: base unit for length */
    M(1),
    /** Kilometer: 1000 meters */
    KM(1000),

    // Mass units - conversion factor to kilograms
    /** Gram: 0.001 kilograms */
    G(0.001),
    /** Kilogram: base unit for mass */
    KG(1),

    // Temperature units (conversion factors are NOT meaningfully used)
    /** Celsius */
    C(1),
    /** Fahrenheit */
    F(1);

    /** Conversion factor to base unit */
    public final double factor;

    /**
     * Constructor for Unit enum.
     * @param factor conversion factor to the base unit
     */
    Unit(double factor) {
        this.factor = factor;
    }

    /**
     * Get the conversion factor for this unit.
     * @return the conversion factor
     */
    public double getFactor() {
        return factor;
    }
}