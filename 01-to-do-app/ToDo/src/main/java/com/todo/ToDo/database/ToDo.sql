-- create the table users
CREATE TABLE Users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    rol VARCHAR(10) NOT NULL
);

CREATE TABLE Tasks (
    id SERIAL PRIMARY KEY,
    user_id BIGINT,
    complete BOOLEAN DEFAULT FALSE,
    nameOfTask VARCHAR(100) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);