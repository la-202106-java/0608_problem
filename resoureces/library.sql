DROP DATABASE IF EXISTS library;
DROP USER IF EXISTS sd;
CREATE USER sd WITH PASSWORD 'himitu';
CREATE DATABASE library OWNER sd ENCODING 'UTF-8';
\c library

DROP TABLE IF EXISTS catalog;
CREATE TABLE catalog (
    isbn CHAR(13) NOT NULL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    code INTEGER NOT NULL,
    auther VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    publication_date DATE NOT NULL
);

ALTER TABLE catalog OWNER TO sd;

DROP TABLE IF EXISTS book;
CREATE TABLE book (
    id INTEGER NOT NULL PRIMARY KEY,
    isbn CHAR(13) NOT NULL,
    title VARCHAR(255) NOT NULL,
    arrival_date DATE NOT NULL,
    note TEXT
);

ALTER TABLE book OWNER TO sd;



DROP TABLE IF EXISTS discarded_book;
CREATE TABLE discarded_book (
    id INTEGER NOT NULL PRIMARY KEY,
    isbn CHAR(13) NOT NULL,
    title VARCHAR(255) NOT NULL,
    arrival_date DATE NOT NULL,
    discard_date DATE NOT NULL,
    note TEXT
);

ALTER TABLE discarded_book OWNER TO sd;



DROP TABLE IF EXISTS lending;
CREATE TABLE lending (
    id INTEGER NOT NULL PRIMARY KEY,
    book_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    lending_date DATE NOT NULL,
    deadline DATE NOT NULL,
    note TEXT
);

ALTER TABLE lending OWNER TO sd;



DROP TABLE IF EXISTS returned_lending;
CREATE TABLE returned_lending (
    id INTEGER NOT NULL PRIMARY KEY,
    book_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    lending_date DATE NOT NULL,
    deadline DATE NOT NULL,
    return_date DATE NOT NULL,
    note TEXT
);

ALTER TABLE returned_lending OWNER TO sd;


DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    join_date DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    tel VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL
);

ALTER TABLE user OWNER TO sd;


DROP TABLE IF EXISTS former_user;
CREATE TABLE former_user (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    join_date DATE NOT NULL,
    quit_date DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    tel VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL
);

ALTER TABLE former_user OWNER TO sd;


DROP TABLE IF EXISTS admin;
CREATE TABLE admin (
    id INTEGER NOT NULL PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(30) NOT NULL
);

ALTER TABLE admin OWNER TO sd;


DROP TABLE IF EXISTS reservation;
CREATE TABLE reservation (
    id INTEGER NOT NULL PRIMARY KEY,
    isbn CHAR NOT NULL,
    user_id INTEGER NOT NULL,
    reservation_time TIMESTAMP NOT NULL,
    reserved_date DATE
);

ALTER TABLE reservation OWNER TO sd;


DROP TABLE IF EXISTS reserved;
CREATE TABLE reserved (
    id INTEGER NOT NULL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    reserved_date DATE NOT NULL,
    lending_date DATE
);

ALTER TABLE reserved OWNER TO sd;


INSERT INTO catalog VALUES('4906638015','7 つの習慣',3,'スティーブン・R・コビー','キングベアー出版','1996-12-25');
INSERT INTO catalog VALUES('4480425993','よいこの君主論',1,'架神恭介/辰巳 一世','筑摩書房','2009-05-11');
