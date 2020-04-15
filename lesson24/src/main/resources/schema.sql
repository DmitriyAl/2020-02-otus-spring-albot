drop table if exists notes;
drop table if exists order_product;
drop table if exists orders;
drop table if exists products;
drop table if exists user_authority;
drop table if exists users;
drop table if exists authorities;
drop table if exists acl_sid;
drop table if exists acl_class;
drop table if exists acl_entry;
drop table if exists acl_object_identity;

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

create table if not exists user_authority
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
    id   bigserial not null
        constraint products_pkey
            primary key,
    name varchar(255)
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
        constraint p_orders_fkey
            references orders,
    product_id bigint not null
        constraint o_products_fkey
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

CREATE TABLE IF NOT EXISTS acl_sid
(
    id        bigint primary key,
    principal smallint     NOT NULL,
    sid       varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS acl_class
(
    id    serial primary key,
    class varchar(255) NOT NULL unique
);

CREATE TABLE IF NOT EXISTS acl_entry
(
    id                  serial primary key,
    acl_object_identity bigint   NOT NULL,
    ace_order           int      NOT NULL,
    sid                 bigint   NOT NULL,
    mask                int      NOT NULL,
    granting            smallint NOT NULL,
    audit_success       smallint NOT NULL,
    audit_failure       smallint NOT NULL,
    constraint unique_uk_4 UNIQUE (acl_object_identity, ace_order)
);

CREATE TABLE IF NOT EXISTS acl_object_identity
(
    id                 serial primary key,
    object_id_class    bigint   NOT NULL,
    object_id_identity char   NOT NULL,
    parent_object      bigint DEFAULT NULL,
    owner_sid          bigint DEFAULT NULL,
    entries_inheriting smallint NOT NULL,
    constraint unique_uk_3 unique (object_id_class, object_id_identity)
);
