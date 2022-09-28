# Car-service

## Overview
Car-service - это приложение, которое позволит автоматизировать:
* процесс обработки заявок автосервиса; 
* процесс заказа дополнительных расходников; 
* процесс распределения заработной платы работников.

## Возможности

* Управление миграциями базы данных с использованием Liquibase

## Настройка приложения

**1. Скопируйте репозиторий**

```
git clone https://github.com/vlad28x/car-service.git
```

**2. Обновите три конфигурационных поля в application.properties**

```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=postgres
```

**3. Запустите приложение с использованием Maven**

```
./mvnw clean install
./mvnw spring-boot:run
```

Приложение будет запущено по адресу http://localhost:8080/

## Dependencies

1. Spring Boot;
2. Liquibase - для отслеживания, управления и применения изменений схемы базы данных;
3. Lombok - для генерации часто повторяющегося кода;
4. JUnit 5.

## To Do
- [x] Подключить Liquibase
- [ ] Настроить Docker для развертывания