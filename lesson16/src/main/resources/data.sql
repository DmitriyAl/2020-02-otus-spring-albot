insert into products (name) values ('laptop'), ('monitor'), ('mouse');
insert into orders default values;
insert into orders default values;
insert into orders default values;
insert into order_product (order_id, product_id) values (1,2), (1,3), (2,1), (3,2);
insert into notes (comment, rate, product_id) values ('Good', 4, 2), ('Bad', 1, 3), ('So-so', 3, 1);
