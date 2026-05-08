# Quantity Measurement Application

## Overview
The Quantity Measurement Application is a Spring Boot-based REST API that performs arithmetic operations and conversions on physical quantities with different units. It supports three measurement types: **LENGTH**, **MASS**, and **TEMPERATURE**.

## Features

### Supported Operations
1. **ADD** - Add two quantities of the same measurement type
2. **SUBTRACT** - Subtract one quantity from another
3. **MULTIPLY** - Multiply two quantities together
4. **DIVIDE** - Divide one quantity by another
5. **COMPARE** - Compare two quantities (Equal, Greater, Lower)
6. **CONVERT** - Convert a quantity from one unit to another

### Supported Units

#### Length
- **CM** (Centimeter) - 0.01 meters
- **M** (Meter) - Base unit
- **KM** (Kilometer) - 1000 meters

#### Mass
- **G** (Gram) - 0.001 kilograms
- **KG** (Kilogram) - Base unit

#### Temperature
- **C** (Celsius)
- **F** (Fahrenheit)

### Measurement Types
- **LENGTH** - For distance and dimensional measurements
- **MASS** - For weight and mass measurements
- **TEMPERATURE** - For temperature values

## Project Structure

```
src/main/java/com/app/quantitymeasurement/
├── QuantityMeasurementAppApplication.java    # Main Spring Boot entry point
├── controller/
│   └── QuantityMeasurementController.java    # REST endpoint handlers
├── dto/
│   ├── QuantityRequestDto.java              # Input request DTO
│   └── QuantityResponseDto.java             # Output response DTO
├── model/
│   ├── MeasurementType.java                 # Enum for measurement types
│   ├── Operation.java                       # Enum for operation types
│   ├── OperationType.java                   # Reserved for future use
│   ├── Unit.java                            # Enum for units with conversion factors
│   └── QuantityMeasurementEntity.java       # JPA entity for database persistence
├── repository/
│   └── QuantityMeasurementRepository.java   # JPA repository for DB operations
└── service/
    └── QuantityMeasurementService.java      # Business logic and calculations
```

## API Endpoints

### 1. Add Quantities
**POST** `/add`
```json
Request:
{
  "thisValue": 1,
  "thisUnit": "M",
  "thisMeasurementType": "LENGTH",
  "thatValue": 1,
  "thatUnit": "M",
  "thatMeasurementType": "LENGTH",
  "resultUnit": "M",
  "resultMeasurementType": "LENGTH"
}

Response:
{
  "resultValue": 2.0,
  "resultUnit": "M",
  "resultMeasurementType": "LENGTH",
  "resultString": null
}
```

### 2. Subtract Quantities
**POST** `/subtract`
```json
Request: (same structure as add)
Response:
{
  "resultValue": 0.0,  // 1M - 1M = 0M
  "resultUnit": "M",
  "resultMeasurementType": "LENGTH",
  "resultString": null
}
```

### 3. Multiply Quantities
**POST** `/multiply`
```json
Request: (same structure as add)
Response:
{
  "resultValue": 1.0,  // 1M * 1M = 1M
  "resultUnit": "M",
  "resultMeasurementType": "LENGTH",
  "resultString": null
}
```

### 4. Divide Quantities
**POST** `/divide`
```json
Request: (same structure as add)
Response:
{
  "resultValue": 1.0,  // 1M / 1M = 1
  "resultUnit": "M",
  "resultMeasurementType": "LENGTH",
  "resultString": null
}
```

### 5. Compare Quantities
**POST** `/compare`
```json
Request: (same structure as add)
Response:
{
  "resultValue": 0.0,
  "resultUnit": null,
  "resultMeasurementType": "LENGTH",
  "resultString": "Equal"  // Can be "Equal", "Greater", or "Lower"
}
```

### 6. Convert Units
**POST** `/convert`
```json
Request:
{
  "thisValue": 1000,
  "thisUnit": "M",
  "thisMeasurementType": "LENGTH",
  "resultUnit": "KM",
  "resultMeasurementType": "LENGTH"
}

Response:
{
  "resultValue": 1.0,  // 1000M = 1KM
  "resultUnit": "KM",
  "resultMeasurementType": "LENGTH",
  "resultString": null
}
```

### 7. Health Check
**GET** `/msg`
```
Response: "Success"
```

### 8. Get Operation History
**GET** `/history`
```
Response: [
  {
    "id": 1,
    "thisValue": 1,
    "thisUnit": "M",
    "thisMeasurementType": "LENGTH",
    "operation": "ADD",
    "resultValue": 2.0,
    "resultUnit": "M",
    "resultMeasurementType": "LENGTH",
    "createdAt": "2024-01-15T10:30:00",
    ...
  },
  ...
]
```

### 9. Get History by Operation Type
**GET** `/history/{operation}`
```
Example: /history/ADD

Response: [
  // All ADD operations from history
]
```

## Key Components

### QuantityMeasurementController
REST controller that handles all HTTP requests and delegates to the service layer.
- CORS enabled for `http://localhost:5174`
- All endpoints return appropriate HTTP responses

### QuantityMeasurementService
Contains all business logic:
- `mapper()` - Converts DTO to Entity
- `remapper()` - Converts Entity to DTO
- `add()` - Addition logic
- `subtract()` - Subtraction logic
- `multiply()` - Multiplication logic
- `divide()` - Division logic
- `compare()` - Comparison logic
- `convert()` - Unit conversion logic
- `history()` - Get all operations
- `getbyOperation()` - Filter by operation type

### QuantityMeasurementEntity
JPA entity with database persistence:
- Stores both input and output values
- Maintains operation history
- Indexed by operation, measurement type, and creation date
- Tracks errors if operations fail

### Unit Conversion Logic
All arithmetic operations follow this formula:
```
result = ((thisValue × thisUnit.factor) ⊕ (thatValue × thatUnit.factor)) / resultUnit.factor
```
Where ⊕ is the operation (+, -, ×, ÷)

For conversion:
```
result = (thisValue × thisUnit.factor) / resultUnit.factor
```

## Building and Running

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- Spring Boot 3.x

### Build
```bash
mvn clean package
```

### Run
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Database
- Uses JPA/Hibernate for ORM
- Stores all operations in `quantity_measurement_entity` table
- Indexed for efficient querying by operation, measurement type, and creation date

## Error Handling
- Returns `null` if measurement types don't match for arithmetic operations
- Supports error tracking with `isError` flag and `errorMessage` field

## Dependencies
- Spring Boot Web
- Spring Data JPA
- Lombok
- Jakarta Persistence API

## CORS Configuration
- Enabled for `http://localhost:5174` to allow frontend requests

## Future Enhancements
- Extended temperature conversion (Celsius to Fahrenheit)
- Additional measurement types (VOLUME, TIME, etc.)
- Advanced error handling and validation
- Authentication/Authorization
- API documentation with Swagger

## Version
1.0.0
