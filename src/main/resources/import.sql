insert into kitchen (name) values ('Japanese');
insert into kitchen (name) values ('Italian');
insert into kitchen (name) values ('Thai');


insert into restaurant (name, delivery_fee, kitchen_id) values ('TopSalmon Sushi', 50, 1);
insert into restaurant (name, delivery_fee, kitchen_id) values ('Pasta & Pasta', 20, 2);
insert into restaurant (name, delivery_fee, kitchen_id) values ('Thai Delivery', 25, 3);
insert into restaurant (name, delivery_fee, kitchen_id) values ('ThaiFood Gourmet', 25, 3);


insert into payment_method (description) values ("Cash");
insert into payment_method (description) values ("Credit card");
insert into payment_method (description) values ("Debit card");


insert into permission (name, description) values (view_products, "User have access to view all products");
insert into permission (name, description) values (add_products, "User have access to add new products");
insert into permission (name, description) values (update_products, "User have access to update products");
insert into permission (name, description) values (remove_products, "User have access to remove products");


insert into state (name) values ('Texas');
insert into state (name) values ('Florida');
insert into state (name) values ('California');


insert into city (name, state_id) values ('Houston', 'Texas');
insert into city (name, state_id) values ('Orlando', 'Florida');
insert into city (name, state_id) values ('Los Angeles', 'California');
