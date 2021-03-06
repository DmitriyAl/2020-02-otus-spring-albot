DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS GENRES;

CREATE TABLE AUTHORS
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE BOOKS
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE GENRES
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

