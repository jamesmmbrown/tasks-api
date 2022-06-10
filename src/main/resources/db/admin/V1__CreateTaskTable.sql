CREATE TABLE program
(
    id   bigint PRIMARY KEY,
    name varchar(350) UNIQUE NOT NULL,
    status bit DEFAULT 0
);
