DROP TABLE IF EXISTS Member;

CREATE TABLE Member
(
    id          IDENTITY        PRIMARY KEY,
    name        VARCHAR(255)    NOT NULL,
    password    VARCHAR(255)    NOT NULL,
    email       VARCHAR(255)
);