DROP TABLE IF EXISTS cars;

CREATE TABLE cars (
  id INT PRIMARY KEY,
  model VARCHAR(250) NOT NULL,
  brand VARCHAR(250) NOT NULL,
  color VARCHAR(250) NOT NULL
);

INSERT INTO cars (id, model, brand, color) VALUES
  (1, 'X1', 'BMW', 'Black'),
  (2, 'RS6', 'AUDI', 'Blue'),
  (3, 'Civic SI', 'HONDA', 'Red');