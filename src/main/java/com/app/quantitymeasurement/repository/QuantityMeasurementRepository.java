package com.app.quantitymeasurement.repository;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quantitymeasurement.model.Operation;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;

/**
 * Repository interface for QuantityMeasurementEntity.
 * Provides database access methods for CRUD operations and custom queries.
 * Extends JpaRepository for basic CRUD operations and pagination.
 */
@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long>{
	/**
	 * Find all quantity measurement records for a specific operation type.
	 * @param op the Operation type to filter by (COMPARE, CONVERT, ADD, SUBTRACT, DIVIDE, MULTIPLY)
	 * @return List of QuantityMeasurementEntity records matching the operation
	 */
	List<QuantityMeasurementEntity> findByOperation(Operation op);
}
