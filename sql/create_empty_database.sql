DROP DATABASE IF EXISTS db_springboot;
CREATE DATABASE db_springboot;

USE db_springboot;

CREATE TABLE persons (
	id bigint not null auto_increment,
    name varchar(255),
    lastname varchar(255),
    programming_language varchar(255),
    PRIMARY KEY (id)
);