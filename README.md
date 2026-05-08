# Quantity Measurement Application

A Spring Boot REST API for performing arithmetic operations and unit conversions on physical quantities.

## Features
- **Operations**: ADD, SUBTRACT, MULTIPLY, DIVIDE, COMPARE, CONVERT
- **Measurement Types**: LENGTH, MASS, TEMPERATURE
- **Units**: 
  - Length: CM, M, KM
  - Mass: G, KG
  - Temperature: C, F

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/add` | Add two quantities |
| POST | `/subtract` | Subtract quantities |
| POST | `/multiply` | Multiply quantities |
| POST | `/divide` | Divide quantities |
| POST | `/compare` | Compare quantities |
| POST | `/convert` | Convert units |
| GET | `/msg` | Health check |
| GET | `/history` | Get all operations |
| GET | `/history/{operation}` | Get history by operation type |

## Project Structure

```
src/main/java/com/app/quantitymeasurement/
├── controller/          # REST endpoints
├── service/            # Business logic
├── model/              # Entities and enums
├── dto/                # Request/Response objects
└── repository/         # Database access
```

## Building and Running

### Prerequisites
- Java 11+
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

Application starts on `http://localhost:8080`

## Technologies
- Spring Boot Web
- Spring Data JPA
- Lombok
- Jakarta Persistence API

## CORS
Enabled for `http://localhost:5174`

## Version
1.0.0