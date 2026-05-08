package com.app.quantitymeasurement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.quantitymeasurement.dto.QuantityRequestDto;
import com.app.quantitymeasurement.dto.QuantityResponseDto;
import com.app.quantitymeasurement.model.MeasurementType;
import com.app.quantitymeasurement.model.Operation;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.model.Unit;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

@ExtendWith(MockitoExtension.class)
class QuantityMeasurementServiceTest {

    @Mock
    private QuantityMeasurementRepository repository;

    @InjectMocks
    private QuantityMeasurementService service;

    @Test
    void add_lengthSameUnit_shouldComputeAndPersist() {
        QuantityRequestDto dto = new QuantityRequestDto();
        dto.thisValue = 2.0;
        dto.thisUnit = Unit.M;
        dto.thisMeasurementType = MeasurementType.LENGTH;
        dto.thatValue = 3.0;
        dto.thatUnit = Unit.M;
        dto.thatMeasurementType = MeasurementType.LENGTH;
        dto.resultUnit = Unit.M;

        when(repository.save(any(QuantityMeasurementEntity.class))).thenAnswer(i -> i.getArgument(0));

        QuantityResponseDto response = service.add(dto);

        assertNotNull(response);
        assertEquals(5.0, response.resultValue, 0.0001);
        assertEquals(Unit.M, response.resultUnit);
        assertEquals(MeasurementType.LENGTH, response.resultMeasurementType);

        ArgumentCaptor<QuantityMeasurementEntity> captor = ArgumentCaptor.forClass(QuantityMeasurementEntity.class);
        verify(repository).save(captor.capture());
        QuantityMeasurementEntity saved = captor.getValue();
        assertEquals(Operation.ADD, saved.operation);
    }

    @Test
    void subtract_lengthDifferentUnit_shouldNormalizeAndPersistSubtract() {
        QuantityRequestDto dto = new QuantityRequestDto();
        dto.thisValue = 500.0;
        dto.thisUnit = Unit.CM;
        dto.thisMeasurementType = MeasurementType.LENGTH;
        dto.thatValue = 2.0;
        dto.thatUnit = Unit.M;
        dto.thatMeasurementType = MeasurementType.LENGTH;
        dto.resultUnit = Unit.M;

        when(repository.save(any(QuantityMeasurementEntity.class))).thenAnswer(i -> i.getArgument(0));

        QuantityResponseDto response = service.subtract(dto);

        assertNotNull(response);
        assertEquals(3.0, response.resultValue, 0.0001);
        assertEquals(Unit.M, response.resultUnit);

        ArgumentCaptor<QuantityMeasurementEntity> captor = ArgumentCaptor.forClass(QuantityMeasurementEntity.class);
        verify(repository).save(captor.capture());
        assertEquals(Operation.SUBTRACT, captor.getValue().operation);
    }

    @Test
    void compare_whenThisGreater_shouldReturnGreaterInResultString() {
        QuantityRequestDto dto = new QuantityRequestDto();
        dto.thisValue = 3.0;
        dto.thisUnit = Unit.M;
        dto.thisMeasurementType = MeasurementType.LENGTH;
        dto.thatValue = 200.0;
        dto.thatUnit = Unit.CM;
        dto.thatMeasurementType = MeasurementType.LENGTH;

        when(repository.save(any(QuantityMeasurementEntity.class))).thenAnswer(i -> i.getArgument(0));

        QuantityResponseDto response = service.compare(dto);

        assertNotNull(response);
        assertEquals("Greater", response.resultString);

        ArgumentCaptor<QuantityMeasurementEntity> captor = ArgumentCaptor.forClass(QuantityMeasurementEntity.class);
        verify(repository).save(captor.capture());
        assertEquals(Operation.COMPARE, captor.getValue().operation);
    }

    @Test
    void convert_fromKmToM_shouldReturn1000() {
        QuantityRequestDto dto = new QuantityRequestDto();
        dto.thisValue = 1.0;
        dto.thisUnit = Unit.KM;
        dto.thisMeasurementType = MeasurementType.LENGTH;
        dto.resultUnit = Unit.M;

        when(repository.save(any(QuantityMeasurementEntity.class))).thenAnswer(i -> i.getArgument(0));

        QuantityResponseDto response = service.convert(dto);

        assertNotNull(response);
        assertEquals(1000.0, response.resultValue, 0.0001);

        ArgumentCaptor<QuantityMeasurementEntity> captor = ArgumentCaptor.forClass(QuantityMeasurementEntity.class);
        verify(repository).save(captor.capture());
        assertEquals(Operation.CONVERT, captor.getValue().operation);
    }

    @Test
    void historyDelegatesToRepositoryFindAll() {
        when(repository.findAll()).thenReturn(List.of());
        assertEquals(0, service.history().size());
        verify(repository).findAll();
    }

    @Test
    void getbyOperationDelegatesToRepository() {
        when(repository.findByOperation(Operation.ADD)).thenReturn(List.of());
        assertEquals(0, service.getbyOperation(Operation.ADD).size());
        verify(repository).findByOperation(Operation.ADD);
    }
}
