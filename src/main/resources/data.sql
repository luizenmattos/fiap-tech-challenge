-- USERS
INSERT INTO user_table (id, login, password, role, created_at)
VALUES
(1, 'root', 'root', 'ADMIN', CURRENT_TIMESTAMP),
(2, 'johndoe', '1234', 'USER', CURRENT_TIMESTAMP),
(3, 'janedoe', 'abcd', 'USER', CURRENT_TIMESTAMP),
(4, 'maria', 'pass123', 'USER', CURRENT_TIMESTAMP),
(5, 'pedro', 'admin123', 'ADMIN', CURRENT_TIMESTAMP);

-- PERSONS
INSERT INTO person_table (id, user_id, email, first_name, last_name, phone, created_at)
VALUES
(1, 1, 'root@fiap.com', 'Root', 'Admin', '11999999999', CURRENT_TIMESTAMP),
(2, 2, 'john.doe@email.com', 'John', 'Doe', '21988887777', CURRENT_TIMESTAMP),
(3, 3, 'jane.doe@email.com', 'Jane', 'Doe', '21977776666', CURRENT_TIMESTAMP),
(4, 4, 'maria@email.com', 'Maria', 'Silva', '21966665555', CURRENT_TIMESTAMP),
(5, 5, 'pedro@email.com', 'Pedro', 'Souza', '21955554444', CURRENT_TIMESTAMP);

-- ADDRESSES
INSERT INTO address_table (id, user_id, street, number, city, state, postal_code, created_at)
VALUES
(1, 1, 'Av. Paulista', '1000', 'São Paulo', 'SP', '01310-100', CURRENT_TIMESTAMP),
(2, 2, 'Rua das Flores', '200', 'Rio de Janeiro', 'RJ', '22040-001', CURRENT_TIMESTAMP),
(3, 3, 'Rua das Palmeiras', '300', 'Belo Horizonte', 'MG', '30130-010', CURRENT_TIMESTAMP),
(4, 4, 'Av. Atlântica', '400', 'Curitiba', 'PR', '80010-010', CURRENT_TIMESTAMP),
(5, 5, 'Rua Central', '500', 'Porto Alegre', 'RS', '90010-001', CURRENT_TIMESTAMP);

-- Ajustar sequences para continuar após os inserts manuais
ALTER SEQUENCE user_table_id_seq RESTART WITH 6;
ALTER SEQUENCE person_table_id_seq RESTART WITH 6;
ALTER SEQUENCE address_table_id_seq RESTART WITH 6;