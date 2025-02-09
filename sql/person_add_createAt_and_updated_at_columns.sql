USE db_springboot;

ALTER TABLE persons
  add column create_at datetime,
  add column updateAt  datetime;