create table posts
(
    id              bigserial primary key,
    number          varchar(255)                            not null unique,
    status_id       bigint references post_statuses (id)   not null,
    city_id         bigint references cities (id)          not null,
    district_id     bigint references districts (id),
    date_time       timestamp with time zone               not null,
    category_id     bigint references post_categories (id) not null,
    description     varchar(100)                           not null,
    additional_info varchar(100),
    user_id         bigint references users (id)           not null,
    created_at      timestamp with time zone               not null default now(),
    updated_at      timestamp with time zone               not null default now()
)