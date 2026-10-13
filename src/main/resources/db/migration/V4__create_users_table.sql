
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT NOT NULL AUTO_INCREMENT,
                                     username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_role ENUM('ADMIN', 'CUSTOMER', 'STAFF') NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_users_username (username)
    );
