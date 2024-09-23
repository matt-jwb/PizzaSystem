CREATE TABLE pizzas
(
    id int primary key AUTO_INCREMENT,
    pizza_name varchar(255) NOT NULL,
    ingredients varchar(255) NOT NULL,
    price decimal(4,2) NOT NULL
);

INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Small Original', 'Cheese, Tomato Sauce', 8);
INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Medium Original', 'Cheese, Tomato Sauce', 9);
INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Large Original', 'Cheese, Tomato Sauce', 11);

INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Small Gimmie the Meat', 'Cheese, Tomato Sauce, Pepperoni, Ham, Chicken, Minced beef, Sausage, Bacon', 11);
INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Medium Gimmie the Meat', 'Cheese, Tomato Sauce, Pepperoni, Ham, Chicken, Minced beef, Sausage, Bacon', 14.50);
INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Large Gimmie the Meat', 'Cheese, Tomato Sauce, Pepperoni, Ham, Chicken, Minced beef, Sausage, Bacon', 16.50);

INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Small Veggie Delight', 'Cheese, Tomato Sauce, Onions, Green peppers, Mushrooms, Sweetcorn', 10);
INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Medium Veggie Delight', 'Cheese, Tomato Sauce, Onions, Green peppers, Mushrooms, Sweetcorn', 13);
INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Large Veggie Delight', 'Cheese, Tomato Sauce, Onions, Green peppers, Mushrooms, Sweetcorn', 15);

INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Small Make Mine Hot', 'Cheese, Tomato Sauce, Chicken, Onions, Green peppers, Jalapeno peppers', 11);
INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Medium Make Mine Hot', 'Cheese, Tomato Sauce, Chicken, Onions, Green peppers, Jalapeno peppers', 13);
INSERT INTO pizzas(pizza_name, ingredients, price)
VALUES ('Large Make Mine Hot', 'Cheese, Tomato Sauce, Chicken, Onions, Green peppers, Jalapeno peppers', 15);