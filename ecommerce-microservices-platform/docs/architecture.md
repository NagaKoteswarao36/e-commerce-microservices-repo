# Architecture Notes

## Synchronous flow
Client -> Gateway -> Order Service -> Feign -> User/Product Services.

## Asynchronous flow
Order Service -> Kafka `order-created` -> Payment Service and Inventory Service.
Payment Service -> Kafka `payment-completed` -> Notification Service.

## Data ownership
Each business service owns a separate MySQL database. Services do not directly query another service's database.

## Resilience
Order Service demonstrates a Resilience4j circuit breaker around order creation. Production systems should add explicit timeout, retry and bulkhead policies according to dependency behavior.
