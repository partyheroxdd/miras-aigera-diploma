create table districts
(
    id      bigserial primary key,
    name    varchar(255) not null,
    city_id bigint references cities (id)
)