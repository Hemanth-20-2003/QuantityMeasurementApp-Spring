package com.app.quantitymeasurement.model;


public enum Unit {

    // Length
    CM(0.01),
    M(1),
    KM(1000),

    // Mass
    G(0.001),
    KG(1),

    // Temperature (factor is NOT used meaningfully ⚠️)
    C(1),
    F(1);

    public final double factor;

    Unit(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}