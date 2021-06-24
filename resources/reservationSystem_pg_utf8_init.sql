DROP DATABASE IF EXISTS reservationsystem;
DROP USER IF EXISTS adminuser;
CREATE USER adminuser WITH PASSWORD 'himitu';
CREATE DATABASE reservationsystem OWNER adminuser ENCODING 'UTF8';
\c reservationsystem
