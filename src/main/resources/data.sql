
#PASSWORD FOR ALL USERS :
user: admin  password:admin
user: buyer password:admin
user: seller password:admin

#roles insertion
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('SELLER');
INSERT INTO roles (name) VALUES ('BUYER');

#product categories insertion
INSERT INTO categories (name) VALUES ('Food');
INSERT INTO categories (name) VALUES ('Toys');
INSERT INTO categories (name) VALUES ('Electronics');
INSERT INTO categories (name) VALUES ('Fashion');
INSERT INTO categories (name) VALUES ('Furniture');

#users insertions
INSERT INTO users (city ,  country ,  state ,  street ,  zipcode ,  email ,  enabled ,  first_name ,  last_name ,  password ,  phone_number ,  photo ,  username ) VALUES
( NULL, NULL, NULL, NULL, NULL, 'admin@example.com', b'1', 'Admin', 'Admin', '$2a$10$JirblkU.nrFQVWpRJkeTcev6J1I/UoZvIDdjPUiIHKAeBrtZq8iYe', NULL, NULL, 'admin');
INSERT INTO users (city ,  country ,  state ,  street ,  zipcode ,  email ,  enabled ,  first_name ,  last_name ,  password ,  phone_number ,  photo ,  username ) VALUES
( NULL, NULL, NULL, NULL, NULL, 'seller@example.com', b'1', 'Seller', 'Seller', '$2a$10$JirblkU.nrFQVWpRJkeTcev6J1I/UoZvIDdjPUiIHKAeBrtZq8iYe', NULL, NULL, 'seller');
INSERT INTO users (city ,  country ,  state ,  street ,  zipcode ,  email ,  enabled ,  first_name ,  last_name ,  password ,  phone_number ,  photo ,  username ) VALUES
( NULL, NULL, NULL, NULL, NULL, 'buyer@example.com', b'1', 'Buyer', 'Buyer', '$2a$10$JirblkU.nrFQVWpRJkeTcev6J1I/UoZvIDdjPUiIHKAeBrtZq8iYe', NULL, NULL, 'buyer');

#user roles insertion
INSERT INTO USERS_ROLES(user_id,role_id)
SELECT (SELECT ID FROM USERS WHERE USERNAME='ADMIN' LIMIT 1) , (SELECT ID FROM ROLES WHERE NAME='ADMIN' LIMIT 1);
INSERT INTO USERS_ROLES(user_id,role_id)
SELECT (SELECT ID FROM USERS WHERE USERNAME='BUYER' LIMIT 1) , (SELECT ID FROM ROLES WHERE NAME='BUYER' LIMIT 1);
INSERT INTO USERS_ROLES(user_id,role_id)
SELECT (SELECT ID FROM USERS WHERE USERNAME='SELLER' LIMIT 1) , (SELECT ID FROM ROLES WHERE NAME='SELLER' LIMIT 1);

#Admins insertion #Sellers insertion #Buyers insertion
insert into admins (level,user_id) values(1,1);
insert into sellers (approved,user_id) values(1,2);
insert into buyers (user_id,accumulated_points)  values(3,0);







INSERT INTO payments (id, card_holder, payment_amount, payment_date, payment_method) VALUES
(1, 'John', 10, '2021-08-13', 'Credit'),
(2, 'John', 10, '2021-08-13', 'Credit'),
(3, 'John', 10, '2021-08-13', 'Credit'),
(4, 'John', 10, '2021-08-13', 'Credit'),
(5, 'John', 10, '2021-08-13', 'Credit'),
(6, 'John', 10, '2021-08-13', 'Credit'),
(7, 'John', 10, '2021-08-13', 'Credit'),
(8, 'John', 10, '2021-08-13', 'Credit'),
(9, 'John', 10, '2021-08-13', 'Credit'),
(10, 'John', 10, '2021-08-13', 'Credit'),
(11, 'John', 10, '2021-08-13', 'Credit'),
(12, 'John', 10, '2021-08-13', 'Credit'),
(13, 'John', 10, '2021-08-13', 'Credit'),
(14, 'John', 10, '2021-08-13', 'Credit'),
(15, 'John', 10, '2021-08-13', 'Credit'),
(16, 'John', 10, '2021-08-13', 'Credit'),
(17, 'John', 10, '2021-08-13', 'Credit'),
(18, 'John', 10, '2021-08-13', 'Credit');


INSERT INTO shippings (id, delivered_date, receiver_city, receiver_country, receiver_first_name, receiver_last_name, receiver_phone, receiver_state, receiver_street, receiver_zipcode) VALUES
(1, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(2, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(3, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(4, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(5, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(6, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(7, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(8, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(9, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(10, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(11, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(12, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(13, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(14, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(15, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(16, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(17, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(18, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557');

INSERT INTO shippings (id, delivered_date, receiver_city, receiver_country, receiver_first_name, receiver_last_name, receiver_phone, receiver_state, receiver_street, receiver_zipcode) VALUES
(1, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(2, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(3, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(4, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(5, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(6, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(7, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(8, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(9, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(10, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(11, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(12, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(13, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(14, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(15, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(16, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(17, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557'),
(18, '2021-08-13', 'Fairfield', 'USA', 'Buyer', 'Buyer', NULL, 'IOWA', '1000 N 4th Street', '52557');


INSERT INTO orders (id, current_status, order_date, total_money, buyer_id, payment_id, shipping_id) VALUES
(1, 'NEW', '2021-08-13', 3453, 1, 14, 14),
(2, 'NEW', '2021-08-13', 3453, 1, 15, 15),
(3, 'NEW', '2021-08-13', 3453, 1, 16, 16),
(4, 'NEW', '2021-08-13', 3453, 1, 17, 17),
(5, 'NEW', '2021-08-13', 300, 1, 18, 18);

#add project
INSERT INTO orderlines (id, line_total, price, quantity, order_id, product_id) VALUES
(1, 234, 23455, 1, 1, 1),
(2, 234, 23455, 1, 2, 1),
(3, 234, 23455, 1, 3, 1),
(4, 234, 23455, 1, 4, 1),
(5, 300, 300, 1, 5, 1);

INSERT INTO shoppingcarts (id, cart_date, completed, total_money, buyer_id) VALUES
(1, '2021-08-10', b'1', 300, 1);





