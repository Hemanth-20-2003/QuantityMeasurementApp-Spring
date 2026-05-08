package com.app.quantitymeasurement.model;

/**
 * Enumeration of supported operations on quantities.
 * 
 * COMPARE: Compare two quantities (returns Equal, Greater, or Lower)
 * CONVERT: Convert a quantity from one unit to another
 * ADD: Add two quantities together
 * SUBTRACT: Subtract one quantity from another
 * DIVIDE: Divide one quantity by another
 * MULTIPLY: Multiply two quantities together
 */
public enum Operation {
    /** Compare two quantities */
    COMPARE,
    /** Convert quantity from one unit to another */
    CONVERT,
    /** Add two quantities */
    ADD,
    /** Subtract two quantities */
    SUBTRACT,
    /** Divide two quantities */
    DIVIDE,
    /** Multiply two quantities */
    MULTIPLY
}