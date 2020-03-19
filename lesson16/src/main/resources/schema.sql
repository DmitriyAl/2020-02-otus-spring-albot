drop table if exists notes;
drop table if exists order_product;
drop table if exists orders;
drop table if exists products;

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
