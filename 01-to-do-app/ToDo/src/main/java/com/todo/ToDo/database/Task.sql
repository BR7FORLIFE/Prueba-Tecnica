-- Tasks table
CREATE TABLE Tasks (
    id SERIAL PRIMARY KEY,
    user_id BIGINT,
    complete BOOLEAN DEFAULT FALSE,
    nameOfTask VARCHAR(100) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);