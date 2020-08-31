drop table if exists notes;
drop table if exists order_product;
drop table if exists orders;
drop table if exists products;
drop table if exists user_authority;
drop table if exists users;
drop table if exists authorities;

create table if not exists users
(
    id       bigserial primary key,
    login    varchar(255) not null,
    password varchar(255) not null
);

create table if not exists authorities
(
    id   bigserial primary key,
    name varchar(255) not null
);

create table user_authority
(
    user_id      bigint not null
        constraint users_fkey
            references users,
    authority_id bigint not null
        constraint authorities_fkey
            references authorities
);

create table if not exists products
(
    id          bigserial not null
        constraint products_pkey
            primary key,
    name        varchar(255),
    description text
);

create table if not exists orders
(
    id bigserial not null
        constraint orders_pkey
            primary key
);

create table if not exists order_product
(
    order_id   bigint not null
        constraint orders_fkey
            references orders,
    product_id bigint not null
        constraint products_fkey
            references products
);

create table if not exists notes
(
    id         bigserial not null
        constraint notes_pkey
            primary key,
    comment    text,
    rate       integer,
    product_id bigint
        constraint products_fkey
            references products
);