-- create the table users

create table Users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(10) NOT NULL
);

