CREATE TABLE IF NOT EXISTS users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  surname VARCHAR(255),
  person_id VARCHAR(12) NOT NULL UNIQUE,
  uuid VARCHAR(36) NOT NULL UNIQUE
);
