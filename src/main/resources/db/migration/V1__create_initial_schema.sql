CREATE TABLE categories (
                            id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description VARCHAR(255)
);

CREATE TABLE products (
                          id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          price DECIMAL(38, 2) NOT NULL,
                          quantity INT NOT NULL,
                          category_id BIGINT,
                          status ENUM('ACTIVE', 'INACTIVE', 'DISCONTINUED'),
                          created_at DATETIME(6),
                          updated_at DATETIME(6),
                          FOREIGN KEY (category_id) REFERENCES categories(id)
);