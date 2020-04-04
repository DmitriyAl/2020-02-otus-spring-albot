DROP TABLE IF EXISTS BOOKS CASCADE;
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRES;
DROP TABLE IF EXISTS NOTES;

CREATE TABLE AUTHORS (
  id   BIGSERIAL CONSTRAINT authors_pkey PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE GENRES (
  id   BIGSERIAL  CONSTRAINT genres_pkey PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE BOOKS (
  id   BIGSERIAL CONSTRAINT books_pkey PRIMARY KEY,
  name VARCHAR(255),
  author_id BIGINT NOT NULL CONSTRAINT book_author_binding REFERENCES AUTHORS (id),
  genre_id BIGINT NOT NULL CONSTRAINT book_genre_binding REFERENCES GENRES (id)
);

CREATE TABLE NOTES (
  id   BIGSERIAL CONSTRAINT notes_pkey PRIMARY KEY,
  note VARCHAR(255),
  book_id BIGINT NOT NULL CONSTRAINT notes_book_binding REFERENCES BOOKS (id)
);