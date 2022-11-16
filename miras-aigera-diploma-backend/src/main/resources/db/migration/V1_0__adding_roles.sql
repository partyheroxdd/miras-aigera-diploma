CREATE SEQUENCE IF NOT EXISTS roles_id_seq;

CREATE TABLE IF NOT EXISTS roles
(
    id   bigint       NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE
);

ALTER TABLE roles
    ALTER COLUMN id SET DEFAULT nextval('roles_id_seq');
INSERT INTO roles(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_POLICEMAN'),
       ('ROLE_USER');