insert into users (login, password)
values ('admin', '$2y$10$vr31PYc..uozm9PR2sTN7uPE4vkCRiwAyNjQOqo2T0gBlXgS2GUJC'),
       ('user', '$2y$10$o.3wINvWxj0CWi2pHI3rHeDjp1qymTkLGtDEt4ilZpuFfCh1PpyAi');
insert into authorities (name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');
insert into user_authority (user_id, authority_id)
VALUES (1, 1),
       (1, 2),
       (2, 2);
insert into products (name, description) values
('laptop', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem delectus eaque, ex excepturi incidunt inventore.'),
 ('monitor', 'Laboriosam minima mollitia necessitatibus nesciunt nobis perferendis ratione sit, sunt veritatis voluptas, voluptate.'),
 ('keyboard', 'Ad consequuntur cum deleniti doloribus dolorum eaque eos, fuga impedit libero magnam minus molestiae nam necessitatibus non, praesentium quaerat quos repellendus sunt.'),
  ('mouse', 'Asperiores aspernatur aut consectetur delectus dignissimos eius eligendi ipsa, iure minus nisi nobis numquam placeat qui repudiandae sed sunt vel veniam.');
insert into orders default values;
insert into orders default values;
insert into orders default values;
insert into order_product (order_id, product_id) values (1,2), (1,3), (2,1), (3,2);
insert into notes (comment, rate, product_id) values ('Good', 4, 2), ('Bad', 1, 3);