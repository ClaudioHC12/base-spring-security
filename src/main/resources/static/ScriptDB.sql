DROP TABLE IF EXISTS users;
CREATE TABLE users(
	id SERIAL PRIMARY KEY,
	user_name VARCHAR(50) NOT NULL,
	name VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	role VARCHAR(50) NOT NULL
);
DROP TABLE IF EXISTS products;
CREATE TABLE products(
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	price DECIMAL NOT NULL
);
INSERT INTO users(user_name, name, password, role)
	VALUES
		('clau', 'claudioHC', '$2a$10$KuwxKBy86EJVKrsvqRu9BubuMRgr3oWNXPy9pI3KMb7oyJpWA4H9u', 'CUSTOMER'),
		('clau2', 'claudioHC2', '$2a$10$f9zsVmvDy9EKtBD6ELImxOD7avPQ.AtD0oSvOHIHfW/cYmovdwf/W', 'ADMINISTRATOR');
INSERT INTO products(name, price)
	VALUES
		('iphone 15 plus', 15000.00),
		('MacBook Air', 35000.00),
		('Monitor 27" BenQ', 3000.00),
		('Silla Gamer', 2000.00),
		('Teclado HP', 1500.00);
SELECT * FROM public.users;
SELECT * FROM public.products;
TRUNCATE TABLE products RESTART IDENTITY;

