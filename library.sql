DROP DATABASE IF EXISTS library;
DROP USER IF EXISTS admin_user;
CREATE USER  admin_user WITH PASSWORD 'himitu';
CREATE DATABASE library OWNER admin_user ENCODING 'UTF8';
\c library


DROP TABLE IF EXISTS lending_ledger;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS material_ledger;
DROP TABLE IF EXISTS material_catalog;
DROP TABLE IF EXISTS material_category;

CREATE TABLE material_category
(
  category_code SERIAL PRIMARY KEY,
  category_name TEXT
);

CREATE TABLE material_catalog
(
  isbn TEXT PRIMARY KEY,
  title TEXT,
  category_code INTEGER,
  authur TEXT,
  publisher TEXT,
  publication_date DATE
);

CREATE TABLE material_ledger
(
  material_id INTEGER PRIMARY KEY,
  isbn TEXT,
  stock_date TIMESTAMP,
  disposal_date TIMESTAMP,
  remark TEXT
);

CREATE TABLE member
(
  member_id  SERIAL PRIMARY KEY,
  member_name TEXT,
  member_address TEXT,
  tel TEXT,
  email TEXT,
  birth DATE,
  join_date TIMESTAMP,
  withdrawal_date TIMESTAMP
);


CREATE TABLE lending_ledger
(
  ledger_id  SERIAL PRIMARY KEY,
  member_id INTEGER,
  material_id INTEGER,
  checkout_date TIMESTAMP,
  return_deadline TIMESTAMP,
  return_date TIMESTAMP
);


ALTER TABLE material_catalog ADD FOREIGN KEY (category_code) 
REFERENCES material_category (category_code);

ALTER TABLE material_ledger ADD FOREIGN KEY (isbn) 
REFERENCES material_catalog (isbn);

ALTER TABLE lending_ledger ADD FOREIGN KEY (member_id) 
REFERENCES member (member_id);

ALTER TABLE lending_ledger ADD FOREIGN KEY (material_id) 
REFERENCES material_ledger (material_id);


ALTER TABLE material_category OWNER TO admin_user;
ALTER TABLE material_catalog OWNER TO admin_user;
ALTER TABLE material_ledger OWNER TO admin_user;
ALTER TABLE member OWNER TO admin_user;
ALTER TABLE lending_ledger OWNER TO admin_user;

set client_encoding to 'utf8';

INSERT INTO material_category VALUES(0, '総記');
INSERT INTO material_category VALUES(1, '哲学');
INSERT INTO material_category VALUES(2, '歴史');
INSERT INTO material_category VALUES(3, '社会科学');
INSERT INTO material_category VALUES(4, '自然科学');
INSERT INTO material_category VALUES(5, '技術');
INSERT INTO material_category VALUES(6, '産業');
INSERT INTO material_category VALUES(7, '芸術');
INSERT INTO material_category VALUES(8, '言語');
INSERT INTO material_category VALUES(9, '文学');
