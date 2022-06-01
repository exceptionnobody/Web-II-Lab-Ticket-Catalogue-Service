DROP TABLE IF EXISTS ticket_catalogue CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE IF NOT EXISTS ticket_catalogue (
    id SERIAL PRIMARY KEY,
    price NUMERIC(6,2),
    ticket_type VARCHAR(255),
    zone VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    quantity INT NOT NULL,
    status VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    ticket_id INT REFERENCES ticket_catalogue(id) NOT NULL
);

INSERT INTO ticket_catalogue VALUES (1, 2.50, 'ordinal', 'Zone 1');
INSERT INTO ticket_catalogue VALUES (2, 3.52, 'weekend pass', 'Zone 2');
INSERT INTO ticket_catalogue VALUES (3, 12.65, 'under 18', 'Zone 4');
INSERT INTO ticket_catalogue VALUES (4, 1.51, 'ordinal', 'Zone 4');
INSERT INTO ticket_catalogue VALUES (5, 36.52, 'ordinal', 'Zone 3');
INSERT INTO ticket_catalogue VALUES (6, 65.53, 'over 60', 'Zone 2');

/*
Initializing the orders table causes errors inserting a new order in the db (duplicate id)

INSERT INTO orders VALUES (1, 5, 'PENDING', 'MarioRossi', 1);
INSERT INTO orders VALUES (2, 3, 'PENDING', 'JohnDoe', 2);
INSERT INTO orders VALUES (3, 1, 'PENDING', 'MarioRossi', 3);
INSERT INTO orders VALUES (4, 1, 'PENDING', 'Pietro', 4);
*/