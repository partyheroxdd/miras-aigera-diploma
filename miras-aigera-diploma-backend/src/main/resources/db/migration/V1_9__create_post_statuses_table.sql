create table post_statuses
(
    id   bigserial primary key,
    name varchar(255) not null unique,
    code varchar(255) not null unique
)