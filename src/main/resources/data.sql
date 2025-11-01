-- USERS
INSERT INTO user_table (id, login, password, role, created_at, updated_at)
VALUES
(1, 'root', 'root', 'OWNER', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP );

-- PERSONS
INSERT INTO person_table (id, user_id, email, first_name, last_name, phone, created_at, updated_at)
VALUES
(1, 1, 'root@fiap.com', 'Root', 'Admin', '11999999999', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP );

-- ADDRESSES
INSERT INTO address_table (id, user_id, street, number, city, state, postal_code, created_at, updated_at)
VALUES
(1, 1, 'Av. Paulista', '1000', 'São Paulo', 'SP', '01310-100', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Ajustar sequences para continuar após os inserts manuais
ALTER SEQUENCE user_table_id_seq RESTART WITH 2;
ALTER SEQUENCE person_table_id_seq RESTART WITH 2;
ALTER SEQUENCE address_table_id_seq RESTART WITH 2;