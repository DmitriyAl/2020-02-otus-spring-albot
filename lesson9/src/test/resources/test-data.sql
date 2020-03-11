INSERT INTO AUTHORS (name) VALUES ('Alexander Pushkin');
INSERT INTO AUTHORS (name) VALUES ('Leo Tolstoy');
INSERT INTO AUTHORS (name) VALUES ('Nikolai Gogol');
INSERT INTO AUTHORS (name) VALUES ('Maksim Gorky');

INSERT INTO GENRES (name) VALUES ('Satire');
INSERT INTO GENRES (name) VALUES ('Novel');
INSERT INTO GENRES (name) VALUES ('Fairytale');

INSERT INTO BOOKS (name, author_id, genre_id) VALUES ('Death souls', 3, 1);
INSERT INTO BOOKS (name, author_id, genre_id) VALUES ('War and peace', 2, 2);
INSERT INTO BOOKS (name, author_id, genre_id) VALUES ('The story about the fisherman and the golden fish', 1, 3);
INSERT INTO BOOKS (name, author_id, genre_id) VALUES ('Evgeniy Onegin', 1, 2);

INSERT INTO "comments" (comment, book_id) VALUES ('Good book', 1);
INSERT INTO "comments" (comment, book_id) VALUES ('Interesting book', 2);
INSERT INTO "comments" (comment, book_id) VALUES ('The best book I have ever read', 3);
INSERT INTO "comments" (comment, book_id) VALUES ('Boring book', 4);
INSERT INTO "comments" (comment, book_id) VALUES ('Do not waste your time', 1);
