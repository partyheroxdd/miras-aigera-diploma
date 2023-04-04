create table users
(
    id           bigserial primary key,
    firstname    varchar(255)                  not null,
    lastname     varchar(255)                  not null,
    midname      varchar(255),
    username     varchar(12)                   not null unique,
    phone_number varchar(12) unique,
    city_id      bigint references cities (id) not null,
    district_id  bigint references districts (id),
    address      varchar(255),
    password     varchar(255),
    role_id      bigint references roles (id)  not null
);