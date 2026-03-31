package com.app.quantitymeasurement.service;

import java.util.List;
import java.nio.channels.NonWritableChannelException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.dto.QuantityRequestDto;
import com.app.quantitymeasurement.dto.QuantityResponseDto;
import com.app.quantitymeasurement.model.Operation;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;


@Service
public class QuantityMeasurementService {
	
	@Autowired
	QuantityMeasurementRepository repository;
	
	public QuantityMeasurementEntity mapper(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=new QuantityMeasurementEntity();
		entity.thisMeasurementType=dto.thisMeasurementType;
		entity.thisUnit=dto.thisUnit;
		entity.thisValue=dto.thisValue;
		entity.thatMeasurementType=dto.thatMeasurementType;
		entity.thatUnit=dto.thatUnit;
		entity.thatValue=dto.thatValue;
		entity.resultUnit=dto.resultUnit;
		entity.resultMeasurementType=dto.resultMeasurementType;
		return entity;
	}
	
	public QuantityResponseDto remapper(QuantityMeasurementEntity entity) {
		QuantityResponseDto res=new QuantityResponseDto();
		res.resultUnit=entity.resultUnit;
		res.resultMeasurementType=entity.resultMeasurementType;
		res.resultValue=entity.resultValue;
		res.resultString=entity.resultString;
		return res;
	}
	
	public QuantityResponseDto add(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
		if(entity.thatMeasurementType==entity.thisMeasurementType) {
			System.out.println("hh");
			if(entity.resultUnit==null) {
				entity.resultUnit=entity.thisUnit;

			}
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;

			}

			entity.resultValue=((entity.thisValue*entity.thisUnit.factor)+(entity.thatValue*entity.thatUnit.factor))/entity.resultUnit.factor;

			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.ADD;
			}
			repository.save(entity);
			return res;
		}
		return null;
	}
	
	public QuantityResponseDto subtract(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
		if(entity.thatMeasurementType==entity.thisMeasurementType) {
			System.out.println("hh");
			if(entity.resultUnit==null) {
				entity.resultUnit=entity.thisUnit;

			}
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;

			}

			entity.resultValue=((entity.thisValue*entity.thisUnit.factor)-(entity.thatValue*entity.thatUnit.factor))/entity.resultUnit.factor;

			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.SUBTRACT;
			}
			repository.save(entity);
			return res;
		}
		return null;
	}
	
	public QuantityResponseDto multiply(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
		if(entity.thatMeasurementType==entity.thisMeasurementType) {
			System.out.println("hh");
			if(entity.resultUnit==null) {
				entity.resultUnit=entity.thisUnit;

			}
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;

			}

			entity.resultValue=((entity.thisValue*entity.thisUnit.factor)*(entity.thatValue*entity.thatUnit.factor))/entity.resultUnit.factor;

			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.MULTIPLY;
			}
			repository.save(entity);
			return res;
		}
		return null;
	}
	public QuantityResponseDto divide(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
		if(entity.thatMeasurementType==entity.thisMeasurementType) {
			System.out.println("hh");
			if(entity.resultUnit==null) {
				entity.resultUnit=entity.thisUnit;

			}
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;

			}

			entity.resultValue=((entity.thisValue*entity.thisUnit.factor)/(entity.thatValue*entity.thatUnit.factor))/entity.resultUnit.factor;

			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.DIVIDE;
			}
			repository.save(entity);
			return res;
		}
		return null;
	}
	public QuantityResponseDto compare(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
		if(entity.thatMeasurementType==entity.thisMeasurementType) {
			System.out.println("hh");
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;

			}

			double thatval=entity.thatValue*entity.thatUnit.factor;
			double thisval=entity.thisValue*entity.thisUnit.factor;
			if(thisval==thatval) {
				entity.resultString="Equal";
			}
			if(thisval>thatval) {
				entity.resultString="Greater";
			}
			if(thisval<thatval) {
				entity.resultString="Lower";
			}
			
			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.COMPARE;
			}
			repository.save(entity);
			return res;
		}
		return null;
	}
	
	public QuantityResponseDto convert(QuantityRequestDto dto) {
		QuantityMeasurementEntity entity=mapper(dto);
		System.out.println(entity.thisMeasurementType);
			System.out.println("hh");
			if(entity.resultMeasurementType==null) {
				entity.resultMeasurementType=entity.thisMeasurementType;

			}

			double thisval=entity.thisValue*entity.thisUnit.factor;
			entity.resultValue=thisval/entity.resultUnit.factor;
			
			QuantityResponseDto res=remapper(entity);
			if(entity.operation==null) {
				entity.operation=Operation.CONVERT;
			}
			repository.save(entity);
			return res;
	}
	
	public java.util.List<QuantityMeasurementEntity> history(){
		return repository.findAll();
	}
	
	public List<QuantityMeasurementEntity> getbyOperation(Operation op){
		return repository.findByOperation(op);
	}
	
}
