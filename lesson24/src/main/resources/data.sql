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
insert into products (name)
values ('available for all users'),
       ('only admin can rename the product'),
       ('only admin sees the product');
insert into orders default
values;
insert into orders default
values;
insert into orders default
values;
insert into order_product (order_id, product_id)
values (1, 2),
       (1, 3),
       (2, 1),
       (3, 2);
insert into notes (comment, rate, product_id)
values ('Good', 4, 2),
       ('Bad', 1, 3),
       ('So-so', 3, 1),
       ('Nice', 5, 1),
       ('Do not buy', 3, 1),
       ('Waste of money', 2, 1);

insert into acl_sid (id, principal, sid)
values (1, 0, 'ROLE_ADMIN'),
       (2, 0, 'ROLE_USER');

insert into acl_class (class)
values ('otus.spring.albot.lesson24.entity.Product');

insert into acl_object_identity (object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
values (1, 1, NULL, 1, 0),
       (1, 2, NULL, 1, 0),
       (1, 3, NULL, 1, 0),
       (1, 4, NULL, 1, 0);

insert into acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
VALUES (1, 1, 1, 1, 1, 1, 1),
       (1, 2, 1, 2, 1, 1, 1),
       (1, 3, 2, 1, 1, 1, 1),
       (1, 4, 2, 2, 1, 1, 1),
       (2, 1, 1, 1, 1, 1, 1),
       (2, 2, 1, 2, 1, 1, 1),
       (2, 3, 2, 1, 1, 1, 1),
       (2, 4, 2, 2, 0, 1, 1),
       (3, 1, 1, 1, 1, 1, 1),
       (3, 2, 1, 2, 1, 1, 1),
       (3, 3, 2, 1, 0, 1, 1),
       (3, 4, 2, 2, 0, 1, 1);
