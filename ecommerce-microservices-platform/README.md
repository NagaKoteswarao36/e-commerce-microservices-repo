# ecommerce-microservices-platform

A complete Spring Boot microservices learning project built for Java 20 and IntelliJ IDEA.

## Architecture

Client -> API Gateway -> Eureka-discovered services

Services:
- service-registry (Eureka)
- config-server (Spring Cloud Config, native profile for local learning)
- api-gateway (Spring Cloud Gateway)
- auth-service (JWT token issuing demo)
- user-service (MySQL)
- product-service (MySQL + Redis cache)
- order-service (MySQL + OpenFeign + Kafka + Resilience4j)
- payment-service (MySQL + Kafka consumer/producer)
- inventory-service (MySQL + Kafka consumer)
- notification-service (Kafka consumer)
- common (shared DTO)

## Java / Spring
- Java 20
- Spring Boot 3.5.5
- Spring Cloud 2025.0.0
- Maven
- application.properties only

## Ports

| Component | Port |
|---|---:|
| API Gateway | 8080 |
| User | 8081 |
| Product | 8082 |
| Order | 8083 |
| Payment | 8084 |
| Inventory | 8085 |
| Auth | 8086 |
| Notification | 8087 |
| Eureka | 8761 |
| Config Server | 8888 |
| MySQL | 3306 |
| Redis | 6379 |
| Kafka | 9092 |

## IntelliJ startup order
1. Start MySQL, Redis and Kafka (or run docker compose).
2. Start service-registry.
3. Start config-server.
4. Start auth-service, user-service, product-service, order-service, payment-service, inventory-service, notification-service.
5. Start api-gateway.

## Docker

```bash
docker compose up --build
```

## Login demo

POST `http://localhost:8080/api/auth/login`

```json
{"username":"admin","password":"admin123"}
```

Demo users: admin/admin123 and user/user123.

## API examples

Create user:
```http
POST http://localhost:8080/api/users
Content-Type: application/json

{"name":"Koti","email":"koti@example.com","phone":"9999999999"}
```

Create product:
```http
POST http://localhost:8080/api/products
Content-Type: application/json

{"name":"Laptop","description":"Java developer laptop","price":75000,"stock":10}
```

Create order:
```http
POST http://localhost:8080/api/orders
Content-Type: application/json

{"userId":1,"productId":1,"quantity":2}
```

Order creation demonstrates synchronous Feign calls to User/Product and publishes an `order-created` Kafka event. Payment and Inventory consume the event independently. Payment publishes `payment-completed`, consumed by Notification.

## Learning coverage

API Gateway, Eureka Service Discovery, Config Server, REST, OpenFeign, MySQL/JPA, Kafka, Redis caching, Resilience4j Circuit Breaker, JWT authentication, Actuator, Docker, Maven and basic testing are represented in this single project. Advanced production topics such as Prometheus/Grafana, OpenTelemetry, Jenkins and Kubernetes can be added as later deployment modules without changing the service boundaries.
