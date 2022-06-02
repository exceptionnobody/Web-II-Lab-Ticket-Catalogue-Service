DROP TABLE IF EXISTS ticket_catalogue CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE IF NOT EXISTS ticket_catalogue (
    id SERIAL PRIMARY KEY,
    ticket_type VARCHAR(255) NOT NULL,
    price NUMERIC(6,2) NOT NULL,
    zones VARCHAR(255) NOT NULL,
    minimum_age INT NOT NULL,
    maximum_age INT NOT NULL,
    duration INT NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    quantity INT NOT NULL,
    status VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    ticket_id INT REFERENCES ticket_catalogue(id) NOT NULL
);

INSERT INTO ticket_catalogue (ticket_type, price, zones, minimum_age, maximum_age, duration) VALUES ('ordinal', 2.50, 'ABC', 0, 100, 1);
INSERT INTO ticket_catalogue (ticket_type, price, zones, minimum_age, maximum_age, duration) VALUES ('weekend pass', 3.50, 'ABC', 0, 27, 48);

/*
Initializing the orders table causes errors inserting a new order in the db (duplicate id)

INSERT INTO orders VALUES (1, 5, 'PENDING', 'MarioRossi', 1);
INSERT INTO orders VALUES (2, 3, 'PENDING', 'JohnDoe', 2);
INSERT INTO orders VALUES (3, 1, 'PENDING', 'MarioRossi', 3);
INSERT INTO orders VALUES (4, 1, 'PENDING', 'Pietro', 4);
*/