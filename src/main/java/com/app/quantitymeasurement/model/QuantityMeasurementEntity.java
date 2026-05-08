package com.app.quantitymeasurement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;



/**
 * JPA Entity representing a quantity measurement operation record.
 * Stores details of all quantity operations (add, subtract, multiply, divide, compare, convert)
 * with input values, units, and results for auditing and history purposes.
 */
@Entity // Marks this class as a JPA entity
@Table(name = "quantity_measurement_entity", indexes = {
    @Index(name = "idx_operation", columnList = "operation"),
    @Index(name = "idx_measurement_type", columnList = "this_measurement_type"),
    @Index(name = "idx_created_at", columnList = "created_at")
})
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-arguments constructor
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "this_value", nullable = false)
    public double thisValue;

    
    @Column(name = "this_unit", nullable = false)
    @Enumerated(EnumType.STRING)
    public Unit thisUnit;

    @Column(name = "this_measurement_type")
    @Enumerated(EnumType.STRING)
    public MeasurementType thisMeasurementType;

    @Column(name = "that_value")
    public double thatValue;

    @Column(name = "that_unit")
    @Enumerated(EnumType.STRING)
    public Unit thatUnit;

    @Column(name = "that_measurement_type")
    @Enumerated(EnumType.STRING)
    public MeasurementType thatMeasurementType;

    // e.g., "COMPARE", "CONVERT", "ADD", "SUBTRACT", "DIVIDE"
    @Column(name = "operation", nullable = false)
    @Enumerated(EnumType.STRING)
    public Operation operation;

    @Column(name = "result_value")
    public double resultValue;

    @Column(name = "result_unit")
    @Enumerated(EnumType.STRING)
    public Unit resultUnit;

    @Column(name = "result_measurement_type")
    @Enumerated(EnumType.STRING)
    public MeasurementType resultMeasurementType;

    // For comparison results like "Equal" or "Not Equal"
    @Column(name = "result_string")
    public String resultString;

    // Flag to indicate if an error occurred during the operation
    @Column(name = "is_error")
    public boolean isError;

    // For capturing any error messages during operations
    @Column(name = "error_message")
    public String errorMessage;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public QuantityMeasurementEntity() {
    	
    }
    
    
}