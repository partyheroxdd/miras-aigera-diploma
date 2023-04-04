create table cities
(
    id   bigserial primary key,
    name varchar(255) not null unique
);