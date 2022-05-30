CREATE TABLE IF NOT EXISTS ticket_catalogue (
    id SERIAL PRIMARY KEY,
    price NUMERIC(6,2),
    ticket_type VARCHAR(255),
    zone VARCHAR(255)
);

DELETE FROM ticket_catalogue;

INSERT INTO ticket_catalogue VALUES (1, 2.50, 'ordinal', 'Zone 1');
INSERT INTO ticket_catalogue VALUES (2, 3.52, 'weekend pass', 'Zone 2');
INSERT INTO ticket_catalogue VALUES (3, 12.65, 'under 18', 'Zone 4');
INSERT INTO ticket_catalogue VALUES (4, 1.51, 'ordinal', 'Zone 4');
INSERT INTO ticket_catalogue VALUES (5, 36.52, 'ordinal', 'Zone 3');
INSERT INTO ticket_catalogue VALUES (6, 65.53, 'over 60', 'Zone 2');