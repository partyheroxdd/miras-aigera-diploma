create unique index post_number_idx
    ON posts (number);

create index post_status_idx
    on posts (status_id);

create index post_city_idx
    on posts (city_id);

create index post_district_idx
    on posts (district_id);

create index post_date_idx
    on posts (date_time);

create index post_category_idx
    on posts (category_id);

create index post_user_idx
    on posts (user_id);