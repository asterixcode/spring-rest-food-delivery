insert into kitchen (id, name) values (1, 'Japanese');
insert into kitchen (id, name) values (2, 'Italian');
insert into kitchen (id, name) values (3, 'Thai');


insert into restaurant (name, delivery_fee, kitchen_id) values ('TopSalmon Sushi', 50, 1);
insert into restaurant (name, delivery_fee, kitchen_id) values ('Pasta & Pasta', 20, 2);
insert into restaurant (name, delivery_fee, kitchen_id) values ('Thai Delivery', 25, 3);
insert into restaurant (name, delivery_fee, kitchen_id) values ('ThaiFood Gourmet', 25, 3);


insert into payment_method (description) values ('Cash');
insert into payment_method (description) values ('Credit card');
insert into payment_method (description) values ('Debit card');


insert into permission (name, description) values ('view_products', 'User have access to view all products');
insert into permission (name, description) values ('add_products', 'User have access to add new products');
insert into permission (name, description) values ('update_products', 'User have access to update products');
insert into permission (name, description) values ('remove_products', 'User have access to remove products');


insert into state (id, name) values (1, 'Texas');
insert into state (id, name) values (2, 'Florida');
insert into state (id, name) values (3, 'California');


insert into city (name, state_id) values ('Houston', 1);
insert into city (name, state_id) values ('Orlando', 2);
insert into city (name, state_id) values ('Los Angeles', 3);
insert into city (name, state_id) values ('Miami', 2);
insert into city (name, state_id) values ('San Diego', 3);
