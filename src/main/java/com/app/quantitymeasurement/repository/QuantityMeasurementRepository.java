package com.app.quantitymeasurement.repository;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quantitymeasurement.model.Operation;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long>{
	List<QuantityMeasurementEntity> findByOperation(Operation op);
}
