CREATE TABLE admins
(
    username varchar(255) primary key,
    password varchar(255) NOT NULL,
    token char(255)
);

INSERT INTO admins(username, password)
VALUES ('matt-jwb', '45c8951c55569e87664515822b49c99b');