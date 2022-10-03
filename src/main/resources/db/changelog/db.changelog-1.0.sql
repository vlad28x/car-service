--liquibase formatted sql

--changeset vlad28x:000000-create-car_service-table
create table car_service
(
    id     bigserial primary key,
    name   char(255) not null,
    budget bigint    not null
);

--changeset vlad28x:000001-create-role-table
create table role
(
    id   bigserial primary key,
    name char(255) not null unique
);

--changeset vlad28x:000002-create-users-table
create table users
(
    id             bigserial primary key,
    username       char(255) not null unique,
    password       char(255) not null,
    email          char(255) not null unique,
    salary         bigint,
    role_id        bigint references role (id),
    car_service_id bigint references car_service (id)
);

--changeset vlad28x:000003-create-service-table
create table service
(
    id             bigserial primary key,
    name           char(255) not null unique,
    price          bigint    not null,
    car_service_id bigint references car_service (id)
);

--changeset vlad28x:000004-create-consumable-table
create table consumable
(
    id       bigserial primary key,
    name     char(255) not null unique,
    price    bigint    not null,
    quantity bigint    not null
);

--changeset vlad28x:000005-create-service_consumable-table
create table service_consumable
(
    service_id    bigint references service (id),
    consumable_id bigint references consumable (id),
    count         bigint not null,
    primary key (service_id, consumable_id)
);

--changeset vlad28x:000006-create-order_status-table
create table order_status
(
    id   bigserial primary key,
    name char(255) not null unique
);

--changeset vlad28x:000007-create-orders-table
create table orders
(
    id          bigserial primary key,
    price       bigint not null,
    status_id   bigint references order_status (id),
    worker_id   bigint references users (id),
    manager_id  bigint references users (id),
    customer_id bigint references users (id)
);

--changeset vlad28x:000008-create-service_orders-table
create table service_orders
(
    service_id bigint references service (id),
    orders_id  bigint references orders (id),
    primary key (service_id, orders_id)
);

--changeset Anastasia7868:000009-insert-car_service
insert into car_service(name, budget)
values ('FIXES', 10000000);

--changeset Anastasia7868:000010-insert-role
insert into role(name)
values ('MANAGER'),
       ('WORKER'),
       ('CUSTOMER'),
       ('ADMIN');

--changeset Anastasia7868:000011-insert-users
insert into users(username, password, email, salary, role_id, car_service_id)
values ('Customer1', 'password1', 'user1@mail.ru', null, 3, 1),
       ('Manager1', 'password2', 'user2@mail.ru', 150000, 1, 1),
       ('Worker1', 'password3', 'user3@mail.ru', 60000, 2, 1),
       ('Admin', 'password4', 'user4@mail.ru', 50000, 4, 1),
       ('Customer2', 'password5', 'user5@mail.ru', null, 3, 1),
       ('Worker2', 'password6', 'user6@mail.ru', 50000, 2, 1),
       ('Worker3', 'password7', 'user7@mail.ru', 70000, 2, 1),
       ('Customer3', 'password8', 'user8@mail.ru', null, 3, 1),
       ('Manager2', 'password9', 'user9@mail.ru', 150000, 1, 1);

--changeset Anastasia7868:000012-insert-service
insert into service(name, price, car_service_id)
values ('carwash', 1200, 1),
       ('tire fitting', 9600, 1),
       ('car inspection', 2700, 1);

--changeset Anastasia7868:000013-insert-consumable
insert into consumable(name, price, quantity)
values ('rags', 100, 1000),
       ('tires', 2000, 200),
       ('gloves', 150, 2000),
       ('detergents', 700, 300),
       ('screwdrivers', 400, 500);

--changeset Anastasia7868:000014-insert-service_consumable
insert into service_consumable(service_id, consumable_id, count)
values (1, 1, 2),
       (1, 3, 2),
       (1, 4, 1),
       (2, 1, 1),
       (2, 2, 4),
       (2, 3, 2),
       (2, 5, 3),
       (3, 1, 1),
       (3, 3, 4),
       (3, 5, 5);

--changeset Anastasia7868:000015-insert-order_status
insert into order_status(name)
values ('PENDING'),
       ('ASSIGNED'),
       ('CANCELED'),
       ('IN PROGRESS'),
       ('DONE'),
       ('PAID');

--changeset Anastasia7868:000016-insert-orders
insert into orders(price, status_id, worker_id, manager_id, customer_id)
values (19200, 4, 3, 2, 1),
       (2400, 5, 6, 9, 5),
       (5400, 6, 7, 2, 1),
       (7800, 2, 3, 9, 8);

--changeset Anastasia7868:000017-insert-service_orders
insert into service_orders(service_id, orders_id)
values (2, 1),
       (1, 2),
       (3, 3),
       (2, 4),
       (3, 4);

