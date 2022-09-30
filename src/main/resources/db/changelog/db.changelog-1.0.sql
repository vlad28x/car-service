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
    name char(255) not null
);

--changeset vlad28x:000002-create-users-table
create table users
(
    id             bigserial primary key,
    username       char(255) not null,
    password       char(255) not null,
    email          char(255) not null,
    salary         bigint,
    role_id        bigint references role (id),
    car_service_id bigint references car_service (id)
);

--changeset vlad28x:000003-create-service-table
create table service
(
    id             bigserial primary key,
    name           char(255) not null,
    price          bigint    not null,
    car_service_id bigint references car_service (id)
);

--changeset vlad28x:000004-create-consumable-table
create table consumable
(
    id       bigserial primary key,
    name     char(255) not null,
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
    id     bigserial primary key,
    status char(255) not null
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
