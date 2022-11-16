CREATE TABLE IF NOT EXISTS post_categories
(
    id   bigint       NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE
);


INSERT INTO post_categories(id, name)
VALUES (1, 'lost thing'),
       (2, 'lost people');