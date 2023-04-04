create table post_categories
(
    id   bigserial primary key,
    name varchar(255) not null unique,
    code varchar(255) not null unique
)