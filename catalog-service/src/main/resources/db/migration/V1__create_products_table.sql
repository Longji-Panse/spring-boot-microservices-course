CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          code VARCHAR(255) NOT NULL UNIQUE,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          image_url VARCHAR(255),
                          price NUMERIC(19, 2) NOT NULL
);