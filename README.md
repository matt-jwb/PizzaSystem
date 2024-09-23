# PizzaSystem
A system for managing a pizza restaurant. This includes a Laravel website for users, a MySQL database for storing info and a RESTful API written in SpringBoot for admin management. This was a project I made in university, so it will not be receiving updates.

## How to setup database
This system requires a MySQL database. You can set up a database locally using XAMPP by running Apache and MySQL. Add the database URL, username and password to admin-api/src/main/resources/application.properties. For the laravel website you will need to create user-website/.env. You can copy .env.example and rename it to ".env". Then input host, port, database name, username and password. The database will be automatically populated with tables when you run the admin api application. Finally run "php artisan migrate" for the laravel tables.

```bash
php artisan migrate
```

## How to use
COMING SOON

## Security concern
The Admin API uses a String Hasher. For security reasons the salt should be randomised, this is currently not done automatically. You can do it manually by changing the values in admin-api/src/main/java/com/example/assignment2springboot/util/StringHasher.java
