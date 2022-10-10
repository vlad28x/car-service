--liquibase formatted sql

--changeset vlad28x:000000-create-car_service-table
create table car_service
(
    id     bigserial primary key,
    name   varchar(255) not null,
    budget bigint       not null,
    created timestamp not null,
    updated timestamp not null
);

--changeset vlad28x:000001-create-role-table
create table role
(
    id   bigserial primary key,
    name varchar(255) not null unique,
    created timestamp not null,
    updated timestamp not null
);

--changeset vlad28x:000002-create-users-table
create table users
(
    id             bigserial primary key,
    username       varchar(255) not null unique,
    password       varchar(255) not null,
    email          varchar(255) not null unique,
    salary         bigint,
    role_id        bigint references role (id),
    car_service_id bigint references car_service (id),
    created timestamp not null,
    updated timestamp not null
);

--changeset vlad28x:000003-create-service-table
create table service
(
    id             bigserial primary key,
    name           varchar(255) not null unique,
    price          bigint       not null,
    car_service_id bigint references car_service (id),
    created timestamp not null,
    updated timestamp not null
);

--changeset vlad28x:000004-create-consumable-table
create table consumable
(
    id       bigserial primary key,
    name     varchar(255) not null unique,
    price    bigint       not null,
    quantity bigint       not null,
    created timestamp not null,
    updated timestamp not null
);

--changeset vlad28x:000005-create-service_consumable-table
create table service_consumable
(
    service_id    bigint references service (id),
    consumable_id bigint references consumable (id),
    count         bigint not null,
    primary key (service_id, consumable_id),
    created timestamp not null,
    updated timestamp not null
);

--changeset vlad28x:000006-create-order_status-table
create table order_status
(
    id   bigserial primary key,
    name varchar(255) not null unique,
    created timestamp not null,
    updated timestamp not null
);

--changeset vlad28x:000007-create-orders-table
create table orders
(
    id          bigserial primary key,
    price       bigint                              not null,
    status_id   bigint references order_status (id) not null,
    worker_id   bigint references users (id),
    manager_id  bigint references users (id),
    customer_id bigint references users (id),
    created timestamp not null,
    updated timestamp not null
);

--changeset vlad28x:000008-create-service_orders-table
create table service_orders
(
    service_id bigint references service (id),
    orders_id  bigint references orders (id),
    primary key (service_id, orders_id),
    created timestamp not null,
    updated timestamp not null
);

--changeset Anastasia7868:000009-insert-car_service
insert into car_service(name, budget, created, updated)
values ('FIXES', 10000000, now(), now());

--changeset Anastasia7868:000010-insert-role
insert into role(name, created, updated)
values ('MANAGER', now(), now()),
       ('WORKER', now(), now()),
       ('CUSTOMER', now(), now()),
       ('ADMIN', now(), now());

--changeset Anastasia7868:000011-insert-users
insert into users(username, password, email, salary, role_id, car_service_id, created, updated)
values ('customer1', 'customer1', 'сustomer1@mail.ru', null, 3, 1, now(), now()),
       ('manager1', 'manager1', 'manager1@mail.ru', 150000, 1, 1, now(), now()),
       ('worker1', 'worker1', 'worker1@mail.ru', 60000, 2, 1, now(), now()),
       ('admin', 'admin', 'admin@mail.ru', null, 4, 1, now(), now()),
       ('customer2', 'customer2', 'customer2@mail.ru', null, 3, 1, now(), now()),
       ('worker2', 'worker2', 'worker2@mail.ru', 50000, 2, 1, now(), now()),
       ('worker3', 'worker3', 'worker3@mail.ru', 70000, 2, 1, now(), now()),
       ('customer3', 'customer3', 'customer3@mail.ru', null, 3, 1, now(), now()),
       ('manager2', 'manager2', 'manager2@mail.ru', 150000, 1, 1, now(), now());

--changeset Anastasia7868:000012-insert-service
insert into service(name, price, car_service_id, created, updated)
values ('carwash', 1200, 1, now(), now()),
       ('tire fitting', 9600, 1, now(), now()),
       ('car inspection', 2700, 1, now(), now());

--changeset Anastasia7868:000013-insert-consumable
insert into consumable(name, price, quantity, created, updated)
values ('rags', 100, 100, now(), now()),
       ('tires', 2000, 50, now(), now()),
       ('gloves', 150, 200, now(), now()),
       ('detergents', 700, 30, now(), now()),
       ('screwdrivers', 400, 50, now(), now());

--changeset Anastasia7868:000014-insert-service_consumable
insert into service_consumable(service_id, consumable_id, count, created, updated)
values (1, 1, 2, now(), now()),
       (1, 3, 2, now(), now()),
       (1, 4, 1, now(), now()),
       (2, 1, 1, now(), now()),
       (2, 2, 4, now(), now()),
       (2, 3, 2, now(), now()),
       (2, 5, 3, now(), now()),
       (3, 1, 1, now(), now()),
       (3, 3, 4, now(), now()),
       (3, 5, 5, now(), now());

--changeset Anastasia7868:000015-insert-order_status
insert into order_status(name, created, updated)
values ('PENDING', now(), now()),
       ('ASSIGNED', now(), now()),
       ('CANCELED', now(), now()),
       ('IN PROGRESS', now(), now()),
       ('DONE', now(), now()),
       ('PAID', now(), now());

--changeset Anastasia7868:000016-insert-orders
insert into orders(price, status_id, worker_id, manager_id, customer_id, created, updated)
values (19200, 4, 3, 2, 1, now(), now()),
       (2400, 5, 6, 9, 5, now(), now()),
       (5400, 6, 7, 2, 1, now(), now()),
       (7800, 2, 3, 9, 8, now(), now());

--changeset Anastasia7868:000017-insert-service_orders
insert into service_orders(service_id, orders_id, created, updated)
values (2, 1, now(), now()),
       (1, 2, now(), now()),
       (3, 3, now(), now()),
       (2, 4, now(), now()),
       (3, 4, now(), now());

