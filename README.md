# Car-service

## Обзор
Car-service - это АИС для автосервиса, которое позволит автоматизировать:
* процесс обработки заявок автосервиса; 
* процесс заказа дополнительных расходников; 
* процесс распределения заработной платы работников.

## Stack

В ходе разработки приложения были использованы следующие технологии:

1. Java SE 1.8;
2. Spring Boot;
3. PostgreSQL;
4. Liquibase;
5. JUnit 5.


## Возможности

* Управление миграциями базы данных с использованием Liquibase;
* Сервисный уровень покрыт unit тестами.


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

Приложение будет запущено по адресу http://localhost:8080/.