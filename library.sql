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
  material_id SERIAL PRIMARY KEY,
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



INSERT INTO material_catalog VALUES('9780070131439', 'introduction to algorithms',5,'Thomas H. Cormen','MIT Press','1990-12-12' );
INSERT INTO material_catalog VALUES('9784061795792', '麻婆豆腐大全',3,'麻婆豆腐研究会','講談社','2021/06/23' );

INSERT INTO material_ledger (isbn,stock_date,remark) VALUES('9780070131439','1995-01-01','first' );
INSERT INTO material_ledger (isbn,stock_date,remark) VALUES(  '9784061795792','2021-06-24','second' );

INSERT INTO member (member_name,member_address,tel,email,birth,join_date) VALUES('鈴木','関東地方','000-0000-0000','abc@cba.mail.com','2021-06-21','2021-06-22');
INSERT INTO member (member_name,member_address,tel,email,birth,join_date) VALUES('佐々木','関東地方','000-0000-0000','qer@req.mail.com','2021-06-22','2021-06-23');

INSERT INTO lending_ledger (member_id,material_id,checkout_date,return_deadline) VALUES(1,1,'2021-05-24','2021-07-02');
INSERT INTO lending_ledger (member_id,material_id,checkout_date,return_deadline) VALUES(1,2,'2021-05-23','2021-06-02');

INSERT INTO lending_ledger (member_id,material_id,checkout_date,return_deadline,return_date)VALUES(1,1,'2021-05-22','2021-06-02','2021-06-01');
INSERT INTO lending_ledger (member_id,material_id,checkout_date,return_deadline,return_date)VALUES(2,2,'2021-05-21','2021-06-02','2021-06-01');

-- INSERT INTO material_catalog VALUES('123', '123',5,'123','123','1989-12-12' );
