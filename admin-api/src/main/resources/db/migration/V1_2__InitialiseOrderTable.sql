CREATE TABLE orders
(
    id int primary key AUTO_INCREMENT,
    user_id int NOT NULL,
    date_time timestamp NOT NULL,
    collection_delivery varchar(10) NOT NULL,
    delivery_address varchar(255),
    price decimal(4,2) NOT NULL,
    pizzas varchar(255) NOT NULL,
    deal varchar(13),
    deal_price decimal(4, 2)
);

CREATE TABLE link_table
(
    id int primary key AUTO_INCREMENT,
    order_id int NOT NULL,
    pizza_id int NOT NULL
);

INSERT INTO orders(user_id, date_time, collection_delivery, price, pizzas)
VALUES (1, CURRENT_TIMESTAMP, 'collection', 9, 'Medium Original,');
INSERT INTO link_table(order_id, pizza_id)
VALUES (1, 2);

INSERT INTO orders(user_id, date_time, collection_delivery, delivery_address, price, pizzas)
VALUES (1, CURRENT_TIMESTAMP, 'delivery', '12 Pizza Lane', 16, 'Small Original, Small Original,');
INSERT INTO link_table(order_id, pizza_id)
VALUES (2, 1);
INSERT INTO link_table(order_id, pizza_id)
VALUES (2, 1);

INSERT INTO orders(user_id, date_time, collection_delivery, delivery_address, price, pizzas)
VALUES(2, CURRENT_TIMESTAMP, 'delivery', '10 Pizza Lane', 31, 'Medium Original, Small Gimmie the Meat, Small Make Mine Hot,');
INSERT INTO link_table(order_id, pizza_id)
VALUES (3, 2);
INSERT INTO link_table(order_id, pizza_id)
VALUES (3, 4);
INSERT INTO link_table(order_id, pizza_id)
VALUES (3, 10);